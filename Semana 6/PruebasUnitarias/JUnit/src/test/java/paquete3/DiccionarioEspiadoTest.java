/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package paquete3;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author orlando.rincon@wizeline.com
 */
@ExtendWith(MockitoExtension.class)
public class DiccionarioEspiadoTest {
    public static final String CASA = "casa";
    public static final String LUGAR_PARA_VIVIR = "lugar para vivir";
    @Mock
    private Map<String, String> imitadorMapaDePalabras;

    private Diccionario diccionarioEspiado;

    // Mockito no puede inyectar un Imitador en un Espía,
    // la inyección tendrá que hacerse manualmente
    @BeforeEach
    public void init() {
        diccionarioEspiado =
                spy(new Diccionario(imitadorMapaDePalabras));
    }

    @Test
    public void CuandoLaPalabraExisteEnElDiccionario_EntoncesLaBusquedaRegresaElSignificado() {
        // Organizar
        when(imitadorMapaDePalabras.get(CASA))
                .thenReturn(LUGAR_PARA_VIVIR);
        // Actuar
        String resultado = diccionarioEspiado.obtenerSignificado(CASA);
        // Verificar
        assertEquals(LUGAR_PARA_VIVIR, resultado,
                "Error al obtener significado del diccionario");
    }
}
