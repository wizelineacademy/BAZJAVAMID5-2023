/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.servicio.integracion;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.wizeline.springboot.entidad.EntidadPais;
import com.wizeline.springboot.otd.OTDPais;
import com.wizeline.springboot.repositorio.RepositorioPais;
import com.wizeline.springboot.servicio.ServicioPais;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author orlando.rincon@wizeline.com
 */
@SpringBootTest
public class ServicioPaisIntegracionTest {
    @MockBean
    private RepositorioPais repositorioPais;

    @Autowired
    private ServicioPais servicioPais;

    @Test
    public void DadoRepositorioPaisEntregaUnaListaDePaises_CuandoObtieneTodosLosPaises_RegresaMismosElementos() {
        List<EntidadPais> listaEntidadPais =
                List.of(new EntidadPais(1L, "PAIS 1", 12345),
                        new EntidadPais(2L, "PAIS 2", 112233));
        when(repositorioPais.findAll()).thenReturn(listaEntidadPais);

        List<OTDPais> otdPais = servicioPais.obtenerTodos();

        assertAll(
                () -> assertEquals(2, otdPais.size()),
                () -> assertEquals("PAIS 1", otdPais.get(0).getNombre()),
                () -> assertEquals(12345, otdPais.get(0).getPoblacion()),
                () -> assertEquals("PAIS 2", otdPais.get(1).getNombre()),
                () -> assertEquals(112233, otdPais.get(1).getPoblacion()),
                // Integración con verificaciones de Mockito
                () -> verify(repositorioPais, times(1)).findAll()
        );
    }

    @Test
    public void DadoRepositorioPaisEntregaUnPaisPorId_CuandoObtienePaisPorId_RegresaPaisValido() {
        EntidadPais entidadPais = new EntidadPais(1L, "PAIS 1", 12345);
        when(repositorioPais.findById(1L)).thenReturn(Optional.of(entidadPais));

        OTDPais otdPais = servicioPais.obtenerPorId(1L);

        assertAll(
                () -> assertEquals("PAIS 1", otdPais.getNombre()),
                () -> assertEquals(12345, otdPais.getPoblacion())
        );
    }

    @Test
    public void DadoRepositorioPaisEntregaNull_CuandoObtienePaisPorId_RegresaNull() {
        when(repositorioPais.findById(1L)).thenReturn(Optional.ofNullable(null));

        OTDPais otdPais = servicioPais.obtenerPorId(1L);

        assertNull(otdPais);
    }
}
