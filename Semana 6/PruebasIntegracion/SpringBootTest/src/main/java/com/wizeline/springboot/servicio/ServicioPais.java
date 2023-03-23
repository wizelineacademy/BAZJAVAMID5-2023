/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package com.wizeline.springboot.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wizeline.springboot.entidad.EntidadPais;
import com.wizeline.springboot.otd.OTDPais;
import com.wizeline.springboot.repositorio.RepositorioPais;

/**
 * @author orlando.rincon@wizeline.com
 */
@Service
public class ServicioPais {
    private RepositorioPais repositorioPais;

    public ServicioPais(RepositorioPais repositorioPais) {
        this.repositorioPais = repositorioPais;
    }

    public List<OTDPais> obtenerTodos() {
        List<EntidadPais> paises = repositorioPais.findAll();
        return paises
                .stream()
                .map(paisEntidad -> new OTDPais(paisEntidad.obtenerNombre(), paisEntidad.obtenerPoblacion()))
                .collect(Collectors.toList());
    }

    public OTDPais obtenerPorId(Long id) {
        Optional<EntidadPais> paisOpcional = repositorioPais.findById(id);
        if (paisOpcional.isPresent()) {
            EntidadPais entidadPais = paisOpcional.get();
            return new OTDPais(entidadPais.obtenerNombre(), entidadPais.obtenerPoblacion());
        }
        return null;
    }
}
