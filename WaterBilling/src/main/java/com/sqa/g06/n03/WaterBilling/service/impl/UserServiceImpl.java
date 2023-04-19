package com.sqa.g06.n03.WaterBilling.service.impl;

import com.sqa.g06.n03.WaterBilling.config.Utils;
import com.sqa.g06.n03.WaterBilling.config.security.JwtTokenProvider;
import com.sqa.g06.n03.WaterBilling.entity.Admin;
import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.entity.Role;
import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.handler.AppError;
import com.sqa.g06.n03.WaterBilling.model.UserDTO;
import com.sqa.g06.n03.WaterBilling.repository.AdminRepository;
import com.sqa.g06.n03.WaterBilling.repository.ClientRepository;
import com.sqa.g06.n03.WaterBilling.repository.RoleRepository;
import com.sqa.g06.n03.WaterBilling.repository.UserRepository;
import com.sqa.g06.n03.WaterBilling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Transactional
    @Override
    public Map<String, String> signup(Admin admin) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseThrow(() -> new AppError("Error!", 500));

        User u = admin.getUser();
        u.setRole(adminRole);

        String randId = "AD" + Utils.generateRandomId();
        admin.setId(randId);

        u.setPassword(Utils.encryptPassword(u.getPassword()));

        System.out.println(admin);

        Map<String, String> map = jwtTokenProvider.signToken(u);

        Admin savedAdmin = adminRepository.save(admin);
        map.put("admin_id", savedAdmin.getId());

        return map;
    }

    @Transactional
    @Override
    public Map<String, String> signup(Client client) {
        Role clientRole = roleRepository.findByName("ROLE_CLIENT").orElseThrow(() -> new AppError("Error!", 500));

        User u = client.getUser();
        u.setRole(clientRole);

        String randId = "KH" + Utils.generateRandomId();
        client.setId(randId);

        u.setPassword(Utils.encryptPassword(u.getPassword()));

        System.out.println(client);

        Map<String, String> map = jwtTokenProvider.signToken(u);

        Client savedClient = clientRepository.save(client);
        map.put("client_id", savedClient.getId());

        return map;
    }

    @Override
    @Transactional
    public Map<String, String> login(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.getUsername());

        if(user != null){
            if(userDTO.getPassword() == null) return null;
            boolean matchPassword = Utils.decryptPassword(userDTO.getPassword(), user.getPassword());
            if(matchPassword){
                return jwtTokenProvider.signToken(user);
            }
        }

        return null;
    }

    @Override
    public User findByUsername(String username) {
        User u = userRepository.findByUsername(username);

        if(u == null)
            throw new AppError("User not found!", 404);

        return u;
    }
}
