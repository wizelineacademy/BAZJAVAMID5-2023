/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.BO;

import java.util.ArrayList;
import java.util.List;

import com.wizeline.DTO.BankAccountDTO;
import com.wizeline.enums.Country;

import static com.wizeline.utils.Utils.getCountry;
import static com.wizeline.utils.Utils.pickRandomAccountType;
import static com.wizeline.utils.Utils.randomAcountNumber;
import static com.wizeline.utils.Utils.randomBalance;
import static com.wizeline.utils.Utils.randomInt;

/**
 * Class Description goes here.
 * Created by jose.vazquez on 07/09/22
 */
public class BankAccountBOImpl implements BankAccountBO{
    @Override
    public List<BankAccountDTO> getAccounts() {
        // Definicion de lista con la informacion de las cuentas existentes.
        List<BankAccountDTO> accountDTOList = new ArrayList<>();
        accountDTOList.add(buildBankAccount("user3@wizeline.com", true, Country.MX, "08-09-2021"));
        accountDTOList.add(buildBankAccount("user1@wizeline.com", false, Country.FR, "07-09-2021"));
        accountDTOList.add(buildBankAccount("user2@wizeline.com" ,false, Country.US, "16-09-2021"));
        return accountDTOList;
    }

    @Override
    public BankAccountDTO getAccountDetails(String user, String lastUsage) {
        return buildBankAccount(user, true, Country.MX, lastUsage);
    }

    // Creación de tipo de dato BankAccount
    private BankAccountDTO buildBankAccount(String user, boolean isActive, Country country, String lastUsage) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountNumber(randomAcountNumber());
        bankAccountDTO.setAccountName("Dummy Account ".concat(randomInt()));
        bankAccountDTO.setUser(user);
        bankAccountDTO.setAccountBalance(randomBalance());
        bankAccountDTO.setAccountType(pickRandomAccountType());
        bankAccountDTO.setCountry(getCountry(country));
        bankAccountDTO.setLastUsage(lastUsage);
        bankAccountDTO.setAccountActive(isActive);
        return bankAccountDTO;
    }
}
