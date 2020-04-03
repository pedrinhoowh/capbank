package com.br.CapBanking.repository;

import com.br.CapBanking.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findAccountByCpf(String cpf);

    Account findAccountByAccountNumber(String accountNumer);

}
