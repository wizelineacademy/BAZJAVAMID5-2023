/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.entidad;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author orlando.rincon@wizeline.com
 */
@Document
public class EntidadPais {
    @Id
    private Long id;

    private String nombre;

    private Integer poblacion;

    // Added for tests only
    public EntidadPais () {
    }

    public EntidadPais (Long id, String nombre, Integer poblacion) {
        this.id = id;
        this.nombre = nombre;
        this.poblacion = poblacion;
    }

    public String obtenerNombre () {
        return this.nombre;
    }

    public Integer obtenerPoblacion () {
        return this.poblacion;
    }
}
