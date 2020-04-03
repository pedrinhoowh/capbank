package com.br.CapBanking.service.factory;

import com.br.CapBanking.service.resource.AccountResource;

import java.math.BigDecimal;

public final class AccountFactory {

    private AccountFactory(){

    }

    public static AccountResource create(Long id, BigDecimal availableValue, String cpf, String name, String accountNumber) {
        return AccountResource.builder()
                .id(id)
                .availableValue(availableValue)
                .cpf(cpf)
                .name(name)
                .accountNumber(accountNumber)
                .build();
    }

}
