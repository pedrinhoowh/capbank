package com.br.CapBanking.service;

import com.br.CapBanking.repository.AccountRepository;
import com.br.CapBanking.service.factory.AccountFactory;
import com.br.CapBanking.service.resource.AccountResource;
import com.br.CapBanking.exception.ResourceNotFoundException;
import com.br.CapBanking.model.Account;
import com.br.CapBanking.service.resource.AccountOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountResource findByCpf(String cpf) {
        Optional<Account> accountOptional = Optional.ofNullable(accountRepository.findAccountByCpf(cpf));

        Account account = accountOptional.orElseThrow(() -> new ResourceNotFoundException("account", "cpf", cpf));

        return AccountFactory.create(account.getId(), account.getAvailableValue(), account.getCpf(), account.getName(), account.getAccountNumber());
    }

    public BigDecimal findAvailableValue(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);

        Account account = accountOptional.orElseThrow(() -> new ResourceNotFoundException("account", "id", id));

        return account.getAvailableValue();
    }

    public AccountResource operationDeposit(AccountOperation accountOperation) {
        Optional<Account> accountOptional = Optional.ofNullable(accountRepository.findAccountByAccountNumber(accountOperation.getAccountTarget()));

        Account account = accountOptional.orElseThrow(() -> new ResourceNotFoundException("account", "accountNumber", accountOperation.getAccountTarget()));

        account.sumAvaiableValue(accountOperation.getAvailableValue());

        Account accountDb = accountRepository.save(account);

        return AccountFactory.create(accountDb.getId(), accountDb.getAvailableValue(), accountDb.getCpf(), accountDb.getName(), accountDb.getAccountNumber());
    }

    public AccountResource operationGetOut(AccountOperation accountOperation) {
        Optional<Account> accountOptional = accountRepository.findById(accountOperation.getIdAccount());

        Account account = accountOptional.orElseThrow(() -> new ResourceNotFoundException("account", "id", accountOperation.getIdAccount()));

        account.verifyAvailableValue(accountOperation.getAvailableValue());
        account.subtractAvaiableValue(accountOperation.getAvailableValue());

        Account accountDb = accountRepository.save(account);

        return AccountFactory.create(accountDb.getId(), accountDb.getAvailableValue(), accountDb.getCpf(), accountDb.getName(), accountDb.getAccountNumber());
    }

}
