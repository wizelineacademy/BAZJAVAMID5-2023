/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package paquete3;

import java.util.Map;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author orlando.rincon@wizeline.com
 */
@ExtendWith(MockitoExtension.class)
public class DiccionarioArgumentMatchersTest {
    private final static Logger LOGGER = Logger.getLogger(DiccionarioArgumentMatchersTest.class.getName());
    public static final String CASA = "casa";
    public static final String LUGAR_PARA_VIVIR = "lugar para vivir";
    public static final String ALEATORIA = "aleatoria";

    @Mock
    private Map<String, String> imitadorMapaDePalabras;

    @InjectMocks
    private Diccionario dic;

    @Test
    public void DadoUnImitadorGenerico_CuandoSeBusqueElSignifadoDeCualquierPalabra_EntoncesEntregaraElMismoResultado() {
        // Organizar
        LOGGER.info("Regresaremos el mismo resultado para cualquier valor");
        when(imitadorMapaDePalabras.get(anyString())).thenReturn(LUGAR_PARA_VIVIR);

        // Actuar
        LOGGER.info("Obteniendo significado para " + CASA);
        String resultado1 = dic.obtenerSignificado(CASA);
        LOGGER.info("Signifado obtenido: " + resultado1);

        LOGGER.info("Obteniendo significado para " + ALEATORIA);
        String resultado2 = dic.obtenerSignificado(ALEATORIA);
        LOGGER.info("Signifado obtenido: " + resultado2);

        // Verificar
        assertEquals(LUGAR_PARA_VIVIR, resultado1,
                "Error al obtener significado del diccionario");

        assertEquals(LUGAR_PARA_VIVIR, resultado2,
                "Error al obtener significado del diccionario");
    }

    @Test
    public void DadoUnImitadorGenerico_CuandoSeInserteUnaNuevaPalabra_EntoncesEntregaraUnResultadoValido() {
        // Organizar
        LOGGER.info("Permitiremos el ingreso de cualesquiera palabras al diccionario");
        // Válido
        when(imitadorMapaDePalabras.put(anyString(), anyString())).thenReturn("RETORNO");
        // Válido (aún más específico, si es necesario)
        //when(imitadorMapaDePalabras.put(anyString(), eq(LUGAR_PARA_VIVIR))).thenReturn("RETORNO");
        // Válido (no recomendado, demasiado genérico)
        //when(imitadorMapaDePalabras.put(anyString(), any())).thenReturn("RETORNO");
        // Válido (no recomendado, es mejor utilizar la versión específica anyString())
        //when(imitadorMapaDePalabras.put(anyString(), any(String.class))).thenReturn("RETORNO");
        // Inválido
        // when(imitadorMapaDePalabras.put(anyString(), LUGAR_PARA_VIVIR)).thenReturn("RETORNO");

        // Actuar
        LOGGER.info("Agregando '" + CASA + "' con significado '" + LUGAR_PARA_VIVIR + "' al diccionario");
        String resultado1 = dic.agregarPalabra(CASA, LUGAR_PARA_VIVIR);
        LOGGER.info("Valor de retorno: " + resultado1);

        // Verificar
        assertEquals("RETORNO", resultado1,
                "Error al agregar palabra al diccionario");
    }


    @Test
    public void DadoUnMapaImitado_CuandoSeInvocaUnMetodoVoid_EntoncesLimpiarDiccionarioNoLanzaExcepcion() {
        // Organizar
        doNothing().when(imitadorMapaDePalabras).clear();
        // Actuar
        Executable ejecucion = () -> dic.limpiarDiccionario();
        // Verificar
        assertDoesNotThrow(ejecucion);
    }
}
