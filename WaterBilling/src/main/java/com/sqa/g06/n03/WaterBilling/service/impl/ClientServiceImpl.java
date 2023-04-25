package com.sqa.g06.n03.WaterBilling.service.impl;
import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.handler.AppError;
import com.sqa.g06.n03.WaterBilling.model.ClientDTO;
import com.sqa.g06.n03.WaterBilling.repository.ClientRepository;
import com.sqa.g06.n03.WaterBilling.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Override
    public Page<ClientDTO> findClients(String name, Pageable pageable){

        return clientRepository.findClients(name, pageable);
    }

    @Override
    public Client findById(String id){
        Optional<Client> client = clientRepository.findById(id);

        if(client.isEmpty())
            throw new AppError("Client not found!", 404);

        return client.get();
    }

    @Override
    public Client findClientByUser(User user){
        // không test vì chức năng được gọi trong AuthService, test trong AuthService
        Client client = clientRepository.findClientByUser(user);

        if(client == null)
            throw new AppError("Client not found!", 404);

        return client;
    }

    @Override
    public Client findClientByUsername(String username){
        Client client = clientRepository.findClientByUsername(username);

        if(client == null)
            throw new AppError("Client not found!", 404);

        return client;
    }
}
