/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package paquete2;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author orlando.rincon@wizeline.com
 */
@ExtendWith(MockitoExtension.class)
public class ListaImitadaTest {

    @Mock
    List<String> listaImitada;

    @Test
    public void DadoUnImitadorEnLaClase_CuandoSeInvocaUnMetodo_EntoncesSeObtieneElValorPorDefecto() {
        // Organizar

        // Actuar
        listaImitada.add("agregando un elemento al azar");
        listaImitada.add("aceptado pero no tiene efecto en el mock");
        // Verificar
        assertEquals(0, listaImitada.size());
    }

    @Test
    // También es posible obtener el objeto por inyección como parámetro
    public void DadoUnImitadorComoParametro_CuandoSeInvocaUnMetodo_EntoncesSeObtieneElValorPorDefecto(
            @Mock List<String> imitadorLista
    ) {
        // Organizar

        // Actuar
        imitadorLista.add("agregando un elemento al azar");
        imitadorLista.add("aceptado pero no tiene efecto en el mock");
        // Verificar
        assertEquals(0, imitadorLista.size());
    }

    @Test
    public void DadaUnaListaImitada_CuandoSeObtieneUnValor_RegresaLaCorrespondencia() {
        // Organizar
        when(listaImitada.get(0)).thenReturn("primero");
        // Actuar
        String resultado = listaImitada.get(0);
        // Verificar
        assertEquals ("primero", resultado);
    }

    @Test
    public void DadaUnaListaImitada_CuandoSeObtieneUnValorInvalido_LanzaUnaExcepcion() {
        // Organizar
        when(listaImitada.get(0)).thenThrow(new RuntimeException());
        // Actuar
        Executable resultado = () -> listaImitada.get(0);
        // Verificar
        assertThrows(RuntimeException.class, resultado);
    }
}
