package com.sqa.g06.n03.WaterBilling.service;

import com.sqa.g06.n03.WaterBilling.entity.Admin;
import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.repository.ClientRepository;
import com.sqa.g06.n03.WaterBilling.repository.RoleRepository;
import com.sqa.g06.n03.WaterBilling.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles({"test"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthServiceTest {
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
    @DisplayName("")
    public void checkRole1(){

    }

    @AfterAll
    public void tearDownAll() {
        System.out.println("Should be executed at the end of the Test");
        userRepository.deleteAll();
    }
}