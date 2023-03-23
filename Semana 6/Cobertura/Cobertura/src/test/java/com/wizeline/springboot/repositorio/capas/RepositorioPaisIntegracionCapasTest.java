/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.repositorio.capas;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.wizeline.springboot.entidad.EntidadPais;
import com.wizeline.springboot.repositorio.RepositorioPais;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author orlando.rincon@wizeline.com
 */
// Configura sólo los repositorios y el acceso a BD
// Incluye @AutoConfigureTestDatabase
@DataJpaTest
public class RepositorioPaisIntegracionCapasTest {
    @Autowired
    private RepositorioPais repositorioPais;

    @Test
    @Sql("/init.sql")
    public void DadaBDConDatos_CuandoRepositorioObtieneTodosLosDatos_EntoncesSeEntreganTodosLosRegistros() {
        // Organizar
        // Esto lo hace @Sql
        // Actuar
        List<EntidadPais> paises = repositorioPais.findAll();
        // Verificar
        assertAll(
                () -> assertEquals(2, paises.size()),
                () -> assertTrue(
                        paises.stream()
                                .map(EntidadPais::obtenerNombre)
                                .collect(Collectors.toList())
                                .containsAll(List.of("Mexico", "Espana")))
        );
    }
}
