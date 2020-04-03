package com.br.CapBanking.config;

import com.br.CapBanking.model.Account;
import com.br.CapBanking.model.Agency;
import com.br.CapBanking.model.Bank;
import com.br.CapBanking.repository.AccountRepository;
import com.br.CapBanking.repository.AgencyRepository;
import com.br.CapBanking.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Configuration
public class ApplicationDataloader implements ApplicationRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        Bank bank = Bank.builder()
                .codeBank("1234")
                .build();

        Bank bankDb = bankRepository.save(bank);

        Agency agency = Agency.builder()
                .bank(bankDb)
                .codeAgency("12347-4")
                .build();

        Agency agencyDb = agencyRepository.save(agency);

        Account account = Account.builder()
                .accountNumber("123456")
                .agency(agencyDb)
                .cpf("12345678911")
                .name("David Alvares")
                .availableValue(BigDecimal.TEN)
                .build();


        accountRepository.save(account);

    }

}
