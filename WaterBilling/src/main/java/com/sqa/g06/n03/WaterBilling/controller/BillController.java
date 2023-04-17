package com.sqa.g06.n03.WaterBilling.controller;

import com.sqa.g06.n03.WaterBilling.handler.AppError;
import com.sqa.g06.n03.WaterBilling.handler.ResponseObject;
import com.sqa.g06.n03.WaterBilling.model.BillDTO;
import com.sqa.g06.n03.WaterBilling.model.ClientDTO;
import com.sqa.g06.n03.WaterBilling.model.CreateBillDTO;
import com.sqa.g06.n03.WaterBilling.service.AuthService;
import com.sqa.g06.n03.WaterBilling.service.WaterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {
    @Autowired
    WaterService waterService;

    @Autowired
    AuthService authService;

    @PostMapping("")
    public ResponseEntity<ResponseObject> createBill(@Valid @RequestBody CreateBillDTO createBillDTO, HttpServletRequest request){
        if(!authService.checkRole(request, "ROLE_ADMIN")) throw new AppError("Unauthorized!", 401);

        System.out.println(createBillDTO);

        BillDTO billDTO = waterService.createBill(createBillDTO);
        return ResponseEntity.status(201).body(
                new ResponseObject("Ok!", "Created!", billDTO)
        );
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> findAllBills(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "-1") int status,
            HttpServletRequest request
    ){
        if(!authService.checkRole(request, "ROLE_ADMIN"))
            throw new AppError("Unauthorized!", 401);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<BillDTO> bills = waterService.findAllBillsByStatus(status, pageable);
        return ResponseEntity.status(200).body(new ResponseObject(
                "Ok!", "Success!", bills
        ));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ResponseObject> findBillsByClientId(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "-1") int status,
            HttpServletRequest request,
            @PathVariable String clientId
    ){
        if(!authService.checkClientHasRoleAccessResource(request, clientId))
            throw new AppError("Unauthorized!", 401);

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<BillDTO> bills = waterService.findBillsByClientIdAndStatus(clientId, status, pageable);
        return ResponseEntity.status(200).body(new ResponseObject(
                "Ok!", "Success!", bills
        ));
    }
}
