package com.company.controller;

import com.company.dto.AuthorizationDTO;
import com.company.dto.ConfirmCodeDTO;
import com.company.dto.RegistrationDTO;
import com.company.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@Api(tags = "Users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @ApiOperation("registration in the system")
    @PostMapping("/registration")
    public ResponseEntity<?> registration(@Valid @RequestBody RegistrationDTO dto){
        return ResponseEntity.ok(usersService.registration(dto));
    }


    @ApiOperation("authorization")
    @PostMapping("/authorization")
    public ResponseEntity<?> authorization(@Valid @RequestBody AuthorizationDTO dto){
        return ResponseEntity.ok(usersService.authorization(dto));
    }

    @ApiOperation("add admin")
    @PutMapping("/addAdmin/{id}")
    public ResponseEntity<?> addAdmin(@PathVariable Long id){
        return ResponseEntity.ok(usersService.addAdmin(id));
    }
}
