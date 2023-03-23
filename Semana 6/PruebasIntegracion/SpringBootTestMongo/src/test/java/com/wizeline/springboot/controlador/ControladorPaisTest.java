/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.controlador;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wizeline.springboot.otd.OTDPais;
import com.wizeline.springboot.servicio.ServicioPais;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @author orlando.rincon@wizeline.com
 */
@ExtendWith(MockitoExtension.class)
public class ControladorPaisTest {

    @Mock
    private ServicioPais servicioPais;

    @InjectMocks
    private ControladorPais controladorPais;

    @Test
    public void DadoServicioPaisEntregaUnaListaDePaises_CuandoObtieneTodosLosPaises_EntoncesRegresaMismaLista() {
        List<OTDPais> otdPaises =
                List.of(new OTDPais("PAIS 1", 12345), new OTDPais("PAIS 2", 112233));
        when(servicioPais.obtenerTodos()).thenReturn(otdPaises);

        List<OTDPais> paises = controladorPais.obtenerTodosLosPaises();

        assertEquals(otdPaises, paises);
    }

    @Test
    public void DadoServicioPaisEntregaUnPaisPorId_CuandoObtienePaisPorId_EntoncesRegresaPaisValido() {
        OTDPais otdPais = new OTDPais("PAIS 1", 12345);
        when(servicioPais.obtenerPorId(1L)).thenReturn(otdPais);

        final ResponseEntity<OTDPais> responseEntity = controladorPais.obtenerPorId(1L);

        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals(otdPais, responseEntity.getBody())
        );
    }

    @Test
    public void DadoServicioPaisPorIdEntregaNull_CuandoObtienePaisPorId_EntoncesRegresaNoEncontrado() {
        when(servicioPais.obtenerPorId(1L)).thenReturn(null);

        final ResponseEntity<OTDPais> responseEntity = controladorPais.obtenerPorId(1L);

        assertAll(
                () -> assertNotNull(responseEntity),
                () -> assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode())
        );
    }
}
