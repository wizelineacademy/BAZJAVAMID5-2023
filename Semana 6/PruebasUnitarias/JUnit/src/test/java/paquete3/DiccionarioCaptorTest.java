/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package paquete3;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * @author orlando.rincon@wizeline.com
 */
@ExtendWith(MockitoExtension.class)
public class DiccionarioCaptorTest {
    @Spy
    private Map<String, String> listaEspiada = new HashMap<>();

    @InjectMocks
    private Diccionario dic;

    @Captor
    private ArgumentCaptor<String> argumentoCapturado;

    @Test
    void DadaUnaPalabraValida_CuandoSeUtilizaObtenerSignificadoMayusculas_EntoncesElMetodoInternoSeInvocaConMayusculas() {
        // Organizar
        listaEspiada.put("A", "b");
        // Ejecutar
        dic.obtenerSignificadoMayusculas("a");
        // Verificar
        verify(listaEspiada).get(argumentoCapturado.capture());
        assertEquals("A", argumentoCapturado.getValue());
    }
}
