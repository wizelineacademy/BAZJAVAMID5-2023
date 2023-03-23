/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.otd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author orlando.rincon@wizeline.com
 */
public class OTDPaisTest {
    @Test
    public void DadoUnOTDPaisValido_CuandoSeObtieneNombre_RegresaValorDeConstruccion() {
        OTDPais otdPais = new OTDPais("PAIS", 12345);

        String nombre = otdPais.getNombre();

        assertEquals ("PAIS", nombre);
    }

    @Test
    public void DadoUnOTDPaisNuevo_CuandoSeAgregaNombre_RegresaNombreAgregado() {
        OTDPais otdPais = new OTDPais("PAIS", 12345);
        otdPais.setNombre("NUEVO PAIS");

        String nombre = otdPais.getNombre();

        assertEquals ("NUEVO PAIS", nombre);
    }

    @Test
    public void DadoUnOTDPaisValido_CuandoSeObtienePoblacion_RegresaValorDeConstruccion() {
        OTDPais otdPais = new OTDPais("PAIS", 12345);

        Integer poblacion = otdPais.getPoblacion();

        assertEquals (12345, poblacion);
    }

    @Test
    public void DadoUnOTDPaisNuevo_CuandoSeAgregaNombre_RegresaPoblacionAgregada() {
        OTDPais otdPais = new OTDPais("PAIS", 12345);
        otdPais.setPoblacion(54321);

        Integer poblacion = otdPais.getPoblacion();

        assertEquals (54321, poblacion);
    }
}
