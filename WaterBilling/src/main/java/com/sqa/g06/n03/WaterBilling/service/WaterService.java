package com.sqa.g06.n03.WaterBilling.service;

import com.sqa.g06.n03.WaterBilling.entity.Bill;
import com.sqa.g06.n03.WaterBilling.model.BillDTO;
import com.sqa.g06.n03.WaterBilling.model.CreateBillDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WaterService {

    public BillDTO createBill(CreateBillDTO billDTO);
    Page<BillDTO> findBillsByClientIdAndStatus(String clientId, int status, Pageable pageable);
}