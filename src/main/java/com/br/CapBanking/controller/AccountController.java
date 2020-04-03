package com.br.CapBanking.controller;

import com.br.CapBanking.service.AccountService;
import com.br.CapBanking.service.resource.AccountResource;
import com.br.CapBanking.service.resource.AccountOperation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/conta")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/cpf")
    @ApiOperation(value = "Returns the resume of account by cpf", response = AccountResource.class, responseContainer = "Object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval AccountResource by cpf",
                    response = AccountResource.class, responseContainer = "Object"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 204, message = "No content")
    })
    public ResponseEntity<AccountResource> findByCpf (@RequestHeader(name = "cpf") String cpf){
       return ResponseEntity.ok(accountService.findByCpf(cpf));
   }

    @GetMapping(value = "/{id}/saldo-disponivel")
    @ApiOperation(value = "Returns the resume available value of account", response = AccountResource.class, responseContainer = "Object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval BigDecimal by id",
                    response = AccountResource.class, responseContainer = "Object"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 204, message = "No content")
    })
    public ResponseEntity<BigDecimal> findByCpf (@PathVariable("id") Long id){
        return ResponseEntity.ok(accountService.findAvailableValue(id));
    }

    @PutMapping(path = "/deposito")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval BigDecimal by id",
                    response = AccountResource.class, responseContainer = "Object"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 204, message = "No content")
    })
    public ResponseEntity<AccountResource> operationDeposit (@Valid @RequestBody AccountOperation accountOperation){
        return ResponseEntity.ok(accountService.operationDeposit(accountOperation));
    }

    @PutMapping(path = "/saque")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval BigDecimal by id",
                    response = AccountResource.class, responseContainer = "Object"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 204, message = "No content")
    })
    public ResponseEntity<AccountResource> operationTransfer (@Valid @RequestBody AccountOperation accountOperation){
        return ResponseEntity.ok(accountService.operationGetOut(accountOperation));
    }

}
