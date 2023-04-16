package com.sqa.g06.n03.WaterBilling.service;

import com.sqa.g06.n03.WaterBilling.entity.Admin;
import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.model.UserDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface UserService {
    public Map<String, String> signup(Admin admin);
    public Map<String, String> signup(Client client);

    public Map<String, String> login(UserDTO userDTO);

    public User findByUsername(String username);

}
