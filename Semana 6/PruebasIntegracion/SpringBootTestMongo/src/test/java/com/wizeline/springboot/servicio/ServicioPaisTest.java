/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.servicio;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wizeline.springboot.entidad.EntidadPais;
import com.wizeline.springboot.otd.OTDPais;
import com.wizeline.springboot.repositorio.RepositorioPais;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

/**
 * @author orlando.rincon@wizeline.com
 */
@ExtendWith(MockitoExtension.class)
public class ServicioPaisTest {
    @Mock
    private RepositorioPais repositorioPais;

    @InjectMocks
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
                () -> assertEquals(112233, otdPais.get(1).getPoblacion())
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
