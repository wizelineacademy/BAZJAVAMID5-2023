/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.otd;

import java.util.Objects;

/**
 * @author orlando.rincon@wizeline.com
 */
public class OTDPais {
    private String nombre;

    private Integer poblacion;

    public OTDPais(String nombre, Integer poblacion) {
        this.nombre = nombre;
        this.poblacion = poblacion;
    }

    // Getter y Setters para permitir la serialización por Jackson
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(Integer poblacion) {
        this.poblacion = poblacion;
    }

    // Sobreescrito para que las comparaciones en los tests sean iguales
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OTDPais)) {
            return false;
        }

        OTDPais otro = (OTDPais) obj;
        return this.nombre != null && this.poblacion != null && this.nombre.equals(otro.nombre)
                && this.poblacion.equals(otro.poblacion);
    }

    @Override public int hashCode() {
        return Objects.hash(nombre, poblacion);
    }
}
