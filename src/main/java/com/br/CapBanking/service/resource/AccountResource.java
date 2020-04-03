package com.br.CapBanking.service.resource;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResource {

    private Long id;

    private BigDecimal availableValue;

    private String cpf;

    private String name;

    private String accountNumber;
}
