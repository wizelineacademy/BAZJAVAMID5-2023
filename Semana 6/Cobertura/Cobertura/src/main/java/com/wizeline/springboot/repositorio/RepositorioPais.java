/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.repositorio;

/**
 * @author orlando.rincon@wizeline.com
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wizeline.springboot.entidad.EntidadPais;

@Repository
public interface RepositorioPais extends JpaRepository<EntidadPais, Long>{

}
