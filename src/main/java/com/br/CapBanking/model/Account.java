package com.br.CapBanking.model;

import com.br.CapBanking.exception.AppException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tb_account")
public class Account {

    private static final String MSG_ERRO = "Saldo indisponível";

    private static final String ACCOUNT_NAME = "account";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 15)
    @Column(name = "num_account")
    private String accountNumber;

    @NotNull
    @Size(max = 11)
    @Column(name = "num_cpf")
    private String cpf;

    @NotNull
    @Column(name = "dsc_name")
    private String name;

    @NotNull
    @Column(name = "val_available", scale = 2)
    private BigDecimal availableValue;

    @ManyToOne
    @JoinColumn(name = "id_agency")
    private Agency agency;

    public void sumAvaiableValue(BigDecimal value) {
        this.availableValue = this.availableValue.add(value);
    }

    public void subtractAvaiableValue(BigDecimal value) {
        this.availableValue = this.availableValue.subtract(value);
    }

    public void verifyAvailableValue(BigDecimal value) {
        if(this.availableValue.compareTo(value) < 0) {
            throw new AppException(MSG_ERRO);
        }
    }

}
