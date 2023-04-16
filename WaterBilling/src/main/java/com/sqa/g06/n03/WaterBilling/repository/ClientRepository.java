package com.sqa.g06.n03.WaterBilling.repository;

import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.model.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

public interface ClientRepository extends JpaRepository<Client, String> {
    @Query(value = "SELECT new com.sqa.g06.n03.WaterBilling.model.ClientDTO(c)" +
            " FROM Client c WHERE  LOWER(c.user.username) LIKE LOWER(CONCAT('%', :name,'%'))")
    Page<ClientDTO> findClients(@Param("name") String name, Pageable pageable);

    Client findClientByUser(User user);

//    Page<Client> findAll(Pageable pageable);
}
