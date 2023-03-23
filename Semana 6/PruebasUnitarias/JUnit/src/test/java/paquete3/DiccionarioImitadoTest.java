/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package paquete3;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author orlando.rincon@wizeline.com
 */
@ExtendWith(MockitoExtension.class)
public class DiccionarioImitadoTest {
    public static final String CASA = "casa";
    public static final String LUGAR_PARA_VIVIR = "lugar para vivir";

    @Mock
    private Map<String, String> imitadorMapaDePalabras;

    @InjectMocks
    private Diccionario dic;

    @Test
    public void Debe_InyectarDependencias_Cuando_UtilizaInjectMocks() {
        // Organizar
        when(imitadorMapaDePalabras.get(CASA))
                .thenReturn(LUGAR_PARA_VIVIR);
        // Actuar
        String resultado = dic.obtenerSignificado(CASA);
        // Verificar
        assertEquals(LUGAR_PARA_VIVIR, resultado,
                "Error al obtener significado del diccionario");
    }
}
