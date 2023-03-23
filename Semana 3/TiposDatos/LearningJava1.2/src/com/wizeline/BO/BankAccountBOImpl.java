/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.BO;

import com.wizeline.DTO.BankAccountDTO;
import com.wizeline.enums.AccountType;

/**
 * Class Description goes here.
 * Created by jose.vazquez on 07/09/22
 */
public class BankAccountBOImpl implements BankAccountBO{

    @Override
    public BankAccountDTO getAccountDetails(String user) {
        return buildBankAccount(user, true);
    }

    // Creación de tipo de dato BankAccount
    private BankAccountDTO buildBankAccount(String user, boolean isActive) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountNumber(123L);
        bankAccountDTO.setAccountName("Dummy Account");
        bankAccountDTO.setUser(user);
        bankAccountDTO.setAccountBalance(843.24);
        bankAccountDTO.setAccountType(AccountType.NOMINA);
        bankAccountDTO.setCountry("Mexico");
        bankAccountDTO.setAccountActive(isActive);
        return bankAccountDTO;
    }
}
