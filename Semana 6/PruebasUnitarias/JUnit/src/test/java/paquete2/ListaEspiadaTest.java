/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package paquete2;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

/**
 * @author orlando.rincon@wizeline.com
 */
@ExtendWith(MockitoExtension.class)
public class ListaEspiadaTest {
    @Spy
    List<String> listaEspiada = new ArrayList<>();

    @Test
    public void DadoUnaListaEspiada_CuandoSeSustityaElMetodoSize_DebeRegresarElValorSustituido() {
        listaEspiada.add("Ejemplo 1");
        listaEspiada.add("Ejemplo 2");
        assertEquals(2, listaEspiada.size());

        doReturn(20).when(listaEspiada).size();
        assertEquals(20, listaEspiada.size());
    }

    @Test
    public void DadoUnaListaEspiada_CuandoElMetodoSizeLanzaUnaExcepcion_DebeObtenerseUnaExcepcion() {
        // Organizar
        doThrow(NullPointerException.class).when(listaEspiada).size();
        // Actuar
        Executable accion = () -> listaEspiada.size();
        // Verificar
        assertThrows(NullPointerException.class, accion);
    }
}
