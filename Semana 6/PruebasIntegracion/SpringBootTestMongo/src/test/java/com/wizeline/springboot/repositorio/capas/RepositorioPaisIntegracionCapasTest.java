/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.repositorio.capas;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
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
@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RepositorioPaisIntegracionCapasTest {
    @Autowired
    private RepositorioPais repositorioPais;

    @BeforeAll
    public void inicializar() {
        repositorioPais.save(new EntidadPais(1L, "Mexico", 130497248));
        repositorioPais.save(new EntidadPais(2L, "Espana", 49067981));
    }

    @AfterAll
    public void limpiar() {
        repositorioPais.deleteAll();
    }

    @Test
    public void DadaBDConDatos_CuandoRepositorioObtieneTodosLosDatos_EntoncesSeEntreganTodosLosRegistros() {
        // Organizar
        // Esto lo hace @BeforeEach
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
