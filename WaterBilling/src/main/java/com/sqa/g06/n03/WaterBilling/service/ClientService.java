package com.sqa.g06.n03.WaterBilling.service;

import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.model.BillDTO;
import com.sqa.g06.n03.WaterBilling.model.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;


public interface ClientService {
    Page<ClientDTO> findClients(String name, Pageable pageable);

    Client findById(String id);

    Client findClientByUser(User user);

    Client findClientByUsername(String username);
}
