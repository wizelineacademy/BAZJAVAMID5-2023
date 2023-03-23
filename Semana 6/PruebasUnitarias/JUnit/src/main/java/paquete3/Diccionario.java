/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package paquete3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author orlando.rincon@wizeline.com
 */
public class Diccionario {
    Map<String, String> mapaDePalabras;

    public Diccionario() {
        mapaDePalabras = new HashMap<>();
    }

    public Diccionario(Map<String, String> mapaDePalabras) {
        this.mapaDePalabras = mapaDePalabras;
    }

    public String obtenerSignificado(String palabra) {
        return mapaDePalabras.get(palabra);
    }

    public String agregarPalabra(String palabra, String significado) {
        return mapaDePalabras.put(palabra, significado);
    }

    public void limpiarDiccionario () {
        this.mapaDePalabras.clear();
    }

    public String obtenerSignificadoMayusculas (String palabra) {
        return mapaDePalabras.get(palabra.toUpperCase()).toUpperCase();
    }
}
