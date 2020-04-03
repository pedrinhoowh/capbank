package com.br.CapBanking.service.resource;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountOperation {

    private Long idAccount;

    private BigDecimal availableValue;

    private String cpf;

    private String name;

    private String accountTarget;

}
