package com.sqa.g06.n03.WaterBilling.controller;

import com.sqa.g06.n03.WaterBilling.handler.AppError;
import com.sqa.g06.n03.WaterBilling.handler.ResponseObject;
import com.sqa.g06.n03.WaterBilling.model.ClientDTO;
import com.sqa.g06.n03.WaterBilling.service.AuthService;
import com.sqa.g06.n03.WaterBilling.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    @Autowired
    ClientService clientService;

    @Autowired
    AuthService authService;

    @GetMapping("")
//    @CrossOrigin
    public ResponseEntity<ResponseObject> getAllUserPagination(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String name,
            HttpServletRequest request
    ){
        if(!authService.checkRole(request, "ROLE_ADMIN")) throw new AppError("Unauthorized!", 401);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ClientDTO> clients = clientService.findClients(name, pageable);

        return ResponseEntity.status(200).body(new ResponseObject(
                "Ok!", "Success!", clients
        ));
    }
}
