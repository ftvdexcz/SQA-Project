package com.sqa.g06.n03.WaterBilling.service;

import com.sqa.g06.n03.WaterBilling.entity.Admin;
import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.entity.Role;
import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.handler.AppError;
import com.sqa.g06.n03.WaterBilling.model.ClientDTO;
import com.sqa.g06.n03.WaterBilling.repository.ClientRepository;
import com.sqa.g06.n03.WaterBilling.repository.RoleRepository;
import com.sqa.g06.n03.WaterBilling.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles({"test"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientServiceTest {
    @Autowired
    ClientService clientService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RoleRepository roleRepository;

    @BeforeAll
    public void setupAll() {
        System.out.println("Should Print Before All Tests");
        userService.signup(new Client("167 Phương Mai, Hà Nội", new User("longdq", "123456", "Quốc Long", "Đặng", "0362957746", "longdq.ptit@gmail.com", null)));
        userService.signup(new Client("167 Phương Mai, Hà Nội", new User("haitt", "123456", "Tuấn Hải", "Trần", "0983064183", "haitt.ptit@gmail.com", null)));
        userService.signup(new Admin(new User("admin", "123456", "Văn Tuấn", "Phùng", "0979964409", "tuanpv.ptit@gmail.com", null)));
    }

    @Test
    @DisplayName("Tìm khách hàng dựa theo username (gần đúng)")
    public void findClients(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<ClientDTO> page = clientService.findClients("long", pageable);
        assertEquals(1, page.getTotalElements());
        assertEquals(1, page.getTotalPages());

        ClientDTO clientDTO = page.getContent().get(0);
        assertEquals("167 Phương Mai, Hà Nội", clientDTO.getAddress());
        assertEquals("longdq", clientDTO.getUsername());
        assertEquals("Quốc Long", clientDTO.getFirstname());
        assertEquals("Đặng", clientDTO.getLastname());
        assertEquals("longdq.ptit@gmail.com", clientDTO.getEmail());
        assertEquals("0362957746", clientDTO.getPhone());
    }

    @Test
    @DisplayName("Tìm khách hàng theo mã khách hàng - 1.Mã khách hàng tồn tại")
    public void findClientByClientId1(){
        Optional<Role> r = roleRepository.findByName("ROLE_CLIENT");
        clientRepository.save(new Client("KH0Nv2jDP89Q", "167 Phương Mai, Hà Nội", new User("linhtp", "123456", "Phương Linh", "Trần", "0962821728", "linhtp.ptit@gmail.com", r.get())));
        Client client = clientService.findById("KH0Nv2jDP89Q");

        assertNotNull(client);
        assertEquals("167 Phương Mai, Hà Nội", client.getAddress());
        assertEquals("linhtp", client.getUser().getUsername());
        assertEquals("Phương Linh", client.getUser().getFirstname());
        assertEquals("Trần", client.getUser().getLastname());
        assertEquals("linhtp.ptit@gmail.com", client.getUser().getEmail());
        assertEquals("0962821728", client.getUser().getPhone());

        clientRepository.deleteById("KH0Nv2jDP89Q");
    }

    @Test
    @DisplayName("Tìm khách hàng theo mã khách hàng - 2.Mã khách hàng không tồn tại")
    public void findClientByClientId2(){
        Client client = null;
        try{
            client = clientService.findById("KH0Nv2jDP89Q");
        }catch(AppError e){
            assertEquals("Client not found!", e.getMessage());
            assertEquals(404, e.getStatusCode());
        }
    }

    @Test
    @DisplayName("Tìm khách hàng theo username - 1.Username tồn tại")
    public void findClientByUsername1(){

        Client client = clientService.findClientByUsername("longdq");

        assertNotNull(client);
        assertEquals("167 Phương Mai, Hà Nội", client.getAddress());
        assertEquals("longdq", client.getUser().getUsername());
        assertEquals("Quốc Long", client.getUser().getFirstname());
        assertEquals("Đặng", client.getUser().getLastname());
        assertEquals("longdq.ptit@gmail.com", client.getUser().getEmail());
        assertEquals("0362957746", client.getUser().getPhone());
    }

    @Test
    @DisplayName("Tìm khách hàng theo mã khách hàng - 2.Username không tồn tại")
    public void findClientByUsername2(){
        Client client = null;
        try{
            client = clientService.findClientByUsername("tuanpv");
        }catch(AppError e){
            assertEquals("Client not found!", e.getMessage());
            assertEquals(404, e.getStatusCode());
        }
    }

    @AfterAll
    public void tearDownAll() {
        System.out.println("Should be executed at the end of the Test");
        userRepository.deleteAll();
    }
}