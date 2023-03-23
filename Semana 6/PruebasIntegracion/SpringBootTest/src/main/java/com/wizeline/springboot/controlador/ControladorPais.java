/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.controlador;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wizeline.springboot.otd.OTDPais;
import com.wizeline.springboot.servicio.ServicioPais;

/**
 * @author orlando.rincon@wizeline.com
 */
@RestController
@RequestMapping("/api")
public class ControladorPais {
    private final ServicioPais servicioPais;

    public ControladorPais(ServicioPais servicioPais) {
        this.servicioPais = servicioPais;
    }

    @GetMapping("/paises")
    public List<OTDPais> obtenerTodosLosPaises() {
        return servicioPais.obtenerTodos();
    }

    @GetMapping(value = "/paises/{id}")
    public ResponseEntity<OTDPais> obtenerPorId(@PathVariable("id") Long id) {
        OTDPais OTDPais = servicioPais.obtenerPorId(id);
        if (OTDPais == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(OTDPais);
    }
}
