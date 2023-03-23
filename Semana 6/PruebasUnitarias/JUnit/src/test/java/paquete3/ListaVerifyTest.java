/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package paquete3;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author orlando.rincon@wizeline.com
 */
@ExtendWith(MockitoExtension.class)
public class ListaVerifyTest {
    @Mock
    private List<String> listaImitada;

    @Test
    public void DadaUnaListaImitada_CuandoSeInvocanMetodos_EntoncesVerifyIdentificaLasEjecuciones() {
        listaImitada.size();
        // Verificando que el método size fue invocado en la lista
        verify(listaImitada).size();

        // Verificando que el método get fue invocado exactamente con el parámetro 1
        listaImitada.get(1);
        verify(listaImitada).get(1);
        verify(listaImitada).get(anyInt()); // ArgumentMatchers

        listaImitada.get(2);
        // Verificando que el método get fue invocado en total 2 veces con cualquier entero (en este caso 1 y 2)
        verify(listaImitada, times(2)).get(anyInt());

        // Verificando que el método clear jamás fue invocado en la lista
        verify(listaImitada, never()).clear();
    }
}
