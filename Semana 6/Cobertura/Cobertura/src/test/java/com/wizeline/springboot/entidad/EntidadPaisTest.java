/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
