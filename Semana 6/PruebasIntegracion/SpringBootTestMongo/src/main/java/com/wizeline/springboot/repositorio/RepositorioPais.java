/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wizeline.springboot.entidad.EntidadPais;

/**
 * @author orlando.rincon@wizeline.com
 */
@Repository
public interface RepositorioPais extends MongoRepository<EntidadPais, Long> {

}
