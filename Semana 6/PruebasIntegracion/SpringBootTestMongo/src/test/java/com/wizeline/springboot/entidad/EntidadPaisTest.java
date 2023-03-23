/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.entidad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author orlando.rincon@wizeline.com
 */
public class EntidadPaisTest {
    @Test
    public void DadoUnPaisValido_CuandoSeObtieneNombre_RegresaValorDeConstruccion() {
        EntidadPais entidadPais = new EntidadPais(1L, "PAIS", 123456);

        String nombre = entidadPais.obtenerNombre();

        assertEquals ("PAIS", nombre);
    }

    @Test
    public void DadoUnPaisValido_CuandoSeObtienePoblacion_RegresaValorDeConstruccion() {
        EntidadPais entidadPais = new EntidadPais(1L, "PAIS", 123456);

        Integer poblacion = entidadPais.obtenerPoblacion();

        assertEquals (123456, poblacion);
    }
}
