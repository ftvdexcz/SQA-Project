package com.sqa.g06.n03.WaterBilling;

import com.sqa.g06.n03.WaterBilling.entity.Bill;
import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.entity.Role;
import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.repository.ClientRepository;
import com.sqa.g06.n03.WaterBilling.repository.RoleRepository;
import com.sqa.g06.n03.WaterBilling.service.WaterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WaterBillingApplicationTests {
	@Autowired
	ClientRepository clientRepository;

	@Autowired
	RoleRepository roleRepository;

	@Test
	public void testCreate(){
//		Bill b = waterService.findOneBill(7, 2022, "KH0XHuxdU7QH");

		Optional<Role> r = roleRepository.findByName("ROLE_CLIENT");

		User u = new User("test username", "123", "test first name", "test last name", "0123456789", "test@gmail.com", r.get());
		Client c = new Client("Test", "test", u);
		clientRepository.save(c);
		assertNotNull(clientRepository.findById("Test").get());
	}
}
