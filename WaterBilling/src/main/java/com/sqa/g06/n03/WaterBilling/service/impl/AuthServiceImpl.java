package com.sqa.g06.n03.WaterBilling.service.impl;

import com.sqa.g06.n03.WaterBilling.config.security.AuthenticationJwt;
import com.sqa.g06.n03.WaterBilling.config.security.JwtTokenProvider;
import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.handler.AppError;
import com.sqa.g06.n03.WaterBilling.service.AuthService;
import com.sqa.g06.n03.WaterBilling.service.ClientService;
import com.sqa.g06.n03.WaterBilling.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationJwt authenticationJwt;

    @Autowired
    UserService userService;

    @Autowired
    ClientService clientService;

    private String authenticate(HttpServletRequest request) {
        String token = authenticationJwt.getTokenFromRequest(request);

        return jwtTokenProvider.validateToken(token);
    }

    @Override
    public boolean checkRole(HttpServletRequest request, String role) {
        // check authen
        String decoded = authenticate(request);

        if(decoded == null){
            throw new AppError("Forbidden", 403);
        };

        // get role from jwt token
        User user = userService.findByUsername(decoded);

        return role.equalsIgnoreCase(user.getRole().getName());
    }

    @Override
    public boolean checkClientHasRoleAccessResource(HttpServletRequest request, String resourceId){
        // check authen
        String decoded = authenticate(request);

        if(decoded == null){
            throw new AppError("Forbidden", 403);
        };

        User user = userService.findByUsername(decoded);

        String role = user.getRole().getName();
        if (role.equalsIgnoreCase("ROLE_ADMIN")) return true;
        else if(role.equalsIgnoreCase("ROLE_CLIENT")){
            Client client = clientService.findClientByUser(user);

            return client.getId().equalsIgnoreCase(resourceId);
        }
        return false;
    }
}
