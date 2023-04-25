package com.sqa.g06.n03.WaterBilling.controller;

import com.sqa.g06.n03.WaterBilling.entity.Admin;
import com.sqa.g06.n03.WaterBilling.entity.Client;
import com.sqa.g06.n03.WaterBilling.entity.User;
import com.sqa.g06.n03.WaterBilling.handler.AppError;
import com.sqa.g06.n03.WaterBilling.handler.ResponseObject;
import com.sqa.g06.n03.WaterBilling.model.ClientDTO;
import com.sqa.g06.n03.WaterBilling.model.UserDTO;
import com.sqa.g06.n03.WaterBilling.service.AuthService;
import com.sqa.g06.n03.WaterBilling.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/clients/signup")
    public ResponseEntity<ResponseObject> createClient(@RequestBody Client client){
        return ResponseEntity.status(201).body(new ResponseObject(
                "Ok!", "Created!", userService.signup(client)
        ));
    }

    @PostMapping("/admins/signup")
    public ResponseEntity<ResponseObject> createAdmin(HttpServletRequest request, @RequestBody Admin admin){
        if(!authService.checkRole(request, "ROLE_ADMIN")) throw new AppError("Unauthorized!", 401);

        return ResponseEntity.status(201).body(new ResponseObject(
                "Ok!", "Created!", userService.signup(admin)
        ));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseObject> login(@RequestBody UserDTO userDTO){

        Map<String, String> map = userService.login(userDTO);

        if(map == null){
            return ResponseEntity.status(400).body(new ResponseObject(
                    "Failed!", "Bad credentials!", null
            ));
        }

        return ResponseEntity.status(200).body(new ResponseObject(
                "Ok!", "Login Success!", map
        ));
    }

    @PostMapping("/verify_token")
    public ResponseEntity<ResponseObject> verifyToken(HttpServletRequest request){
        User user = authService.verifyToken(request);

        System.out.println("token valid");
        return ResponseEntity.status(200).body(new ResponseObject(
                "Ok!", "Success!", user
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseObject> logout(HttpServletRequest request){
        String newToken = authService.logout(request);

        return ResponseEntity.status(200).body(new ResponseObject(
                "Ok!", "Logout!", newToken
        ));
    }

}
