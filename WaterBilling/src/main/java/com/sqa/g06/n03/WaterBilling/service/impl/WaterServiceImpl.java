package com.sqa.g06.n03.WaterBilling.service.impl;

import com.sqa.g06.n03.WaterBilling.config.Config;
import com.sqa.g06.n03.WaterBilling.config.Utils;
import com.sqa.g06.n03.WaterBilling.entity.Bill;
import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.handler.AppError;
import com.sqa.g06.n03.WaterBilling.model.BillDTO;
import com.sqa.g06.n03.WaterBilling.model.CreateBillDTO;
import com.sqa.g06.n03.WaterBilling.repository.BillRepository;
import com.sqa.g06.n03.WaterBilling.repository.ClientRepository;
import com.sqa.g06.n03.WaterBilling.repository.ConfigRepository;
import com.sqa.g06.n03.WaterBilling.service.ClientService;
import com.sqa.g06.n03.WaterBilling.service.WaterService;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WaterServiceImpl implements WaterService {
    @Autowired
    private ConfigRepository config;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ClientService clientService;

    @Override
    @Transactional
    public BillDTO createBill(CreateBillDTO createBillDTO) {
        Optional<Config> cfg = config.findById(1);

        if(cfg.isEmpty())
            throw new AppError("No config for app!", 500);


        String clientId = createBillDTO.getClientId();

        Optional<Client> c = clientRepository.findById(clientId);

        if(c.isEmpty())
            throw new AppError("Client not found!", 400);

        Client client = c.get();

        // Tinh tien nuoc
        int meterConsum = createBillDTO.getMeterConsum();
        double amount = calcAmount(meterConsum);
        double tax = calcTax(amount, cfg.get().getTaxRate());
        double environmentRate = calcEnvironmentFee(amount, cfg.get().getEnvironmentRate());

        Bill bill =  billRepository.save(new Bill(false, createBillDTO.getMonth(), createBillDTO.getYear(), tax, environmentRate, amount, meterConsum, client));

        return new BillDTO(bill, null);
    }

    @Override
    public Page<BillDTO> findBillsByClientIdAndStatus(String clientId, int status, Pageable pageable) {
        // check client id exist
        clientService.findById(clientId);

        if(status == -1){
            return billRepository.findBillsByClientId(clientId, pageable);
        }else if(status == 0){
            return billRepository.findBillsByClientIdAndStatus(clientId, false, pageable);
        }else if(status == 1){
            return billRepository.findBillsByClientIdAndStatus(clientId, true, pageable);
        }

        throw new AppError("Bad Request!", 400);
    }

    @Override
    public Page<BillDTO> findAllBillsByStatus(int status, Pageable pageable) {
        if(status == -1){
            return billRepository.findBills(pageable);
        }else if(status == 0){
            return billRepository.findBillsByStatus(false, pageable);
        }else if(status == 1){
            return billRepository.findBillsByStatus(true, pageable);
        }

        throw new AppError("Bad Request!", 400);
    }

    private double calcAmount(int meterConsume) {
        double s = 0;
        Optional<Config> c = config.findById(1);

        if(c.isEmpty())
            throw new AppError("No config for app!", 500);

        if(meterConsume <= 0) return s;

        Config config = c.get();
        double l1 = config.getLevel1();
        double l2 = config.getLevel2();
        double l3 = config.getLevel3();
        double l4 = config.getLevel4();

        if (meterConsume <= 10) {
            s = meterConsume * l1;
        } else if (meterConsume <= 20) {
            s = 10 * l1 + (meterConsume - 10) * l2;
        } else if (meterConsume <= 30) {
            s = 10 * l1 + 10 * 7.052 + (meterConsume - 20) * l3;
        } else {
            s = 10 * l1 + 10 * l2 + 10 * l3 + (meterConsume - 30) * l4;
        }
        return s;
    }


    private double calcTax(double amount, int taxRate) {
        return Utils.roundDouble(amount * ((double) taxRate / 100));
    }

    private double calcEnvironmentFee(double amount, int environmentRate) {
        return Utils.roundDouble(amount * ((double) environmentRate / 100));
    }

    private double totalAmount(double amount, double taxFee, double environmentFee){
        return Utils.roundDouble(amount + taxFee + environmentFee);
    }
}
