/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.controlador.integracion.completa;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wizeline.springboot.controlador.ControladorPais;
import com.wizeline.springboot.otd.OTDPais;
import com.wizeline.springboot.servicio.ServicioPais;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author orlando.rincon@wizeline.com
 */
@SpringBootTest
// Automáticamente crea y configura una instancia de MockMvc
@AutoConfigureMockMvc
// Poner este script al inicio de la clase, hace que se ejecute antes de cada prueba
@Sql("/init.sql")
// Y este script provoca que despues de cada test, se limpie la BD
@Sql(scripts = "/limpiar.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ControladorPaisIntegracionCompletaTest {

    @Autowired
    private ControladorPais controladorPais;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void DadoServicioPaisEntregaUnaListaDePaises_CuandoObtieneTodosLosPaises_RegresaMismaLista()
            throws Exception {
        MvcResult resultadoPeticion =
                mockMvc.perform(get("/api/paises"))
                        .andExpect(status().isOk())
                        .andReturn();
        OTDPais[] otdPaisesArreglo =
                mapper.readValue(resultadoPeticion.getResponse().getContentAsString(), OTDPais[].class);
        List<OTDPais> otdPaisesResultado = Arrays.asList(otdPaisesArreglo);

        assertTrue(
                otdPaisesResultado.stream()
                        .map(OTDPais::getNombre)
                        .collect(Collectors.toList())
                        .containsAll(List.of("Mexico", "Espana")));
    }

    @Test
    public void DadoServicioPaisEntregaUnPaisPorId_CuandoObtienePaisPorId_RegresaPaisValido() throws Exception {
        OTDPais otdPais = new OTDPais("Mexico", 130497248);
        MvcResult resultadoPeticion =
                mockMvc.perform(get("/api/paises/{id}", "1"))
                        .andExpect(status().isOk())
                        .andReturn();

        OTDPais otdPaisResultado =
                mapper.readValue(resultadoPeticion.getResponse().getContentAsString(), OTDPais.class);

        assertAll(
                () -> assertNotNull(otdPaisResultado),
                () -> assertEquals(otdPais, otdPaisResultado)
        );
    }

    @Test
    public void DadoServicioPaisPorIdEntregaNull_CuandoObtienenPaisPorId_RegresaNoEncontrado() throws Exception {
        mockMvc.perform(get("/api/paises/{id}", "4"))
                .andExpect(status().isNotFound());
    }
}
