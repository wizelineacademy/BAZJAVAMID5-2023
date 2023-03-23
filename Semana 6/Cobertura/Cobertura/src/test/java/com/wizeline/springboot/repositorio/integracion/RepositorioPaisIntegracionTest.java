/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.repositorio.integracion;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.wizeline.springboot.entidad.EntidadPais;
import com.wizeline.springboot.repositorio.RepositorioPais;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author orlando.rincon@wizeline.com
 */
@SpringBootTest
// Automáticamente configura la BD embebida, disponible en el classpath (H2, HSQLDB, Derby)
// En este caso no es necesario porque ya definimos la configuración en src/test/resources/application.properties
@AutoConfigureTestDatabase
public class RepositorioPaisIntegracionTest {
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
