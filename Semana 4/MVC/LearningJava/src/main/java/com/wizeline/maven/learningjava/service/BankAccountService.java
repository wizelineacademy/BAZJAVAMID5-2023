/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.maven.learningjava.service;

import java.util.List;

import com.wizeline.maven.learningjava.model.BankAccountDTO;

/**
 * 
 * Created by jose.vazquez on 07/09/22
 */
public interface BankAccountService {

    /**
     * Gets accounts.
     *
     * @return todas las cuentas existentes en formato de lista.
     */
    List<BankAccountDTO> getAccounts();

    /**
     * Gets account details.
     *
     * @param user      nombre de usuario.
     * @param lastUsage último uso de la cuenta.
     * @return detalles de una sola cuenta.
     */
    BankAccountDTO getAccountDetails(String user, String lastUsage);

}
