/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.BO;

import com.wizeline.DTO.BankAccountDTO;

/**
 * Class Description goes here.
 * Created by jose.vazquez on 07/09/22
 */
public interface BankAccountBO {
    BankAccountDTO getAccountDetails(String user);
}
