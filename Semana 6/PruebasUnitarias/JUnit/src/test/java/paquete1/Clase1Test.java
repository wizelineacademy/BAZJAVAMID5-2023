/*
 * Copyright (c) 2022 Wizeline
 * All rights reserved.
 */

package paquete1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author orlando.rincon@wizeline.com
 */
// Esta anotación permite que el @BeforeAll y @AfterAll sean métodos regulares en vez de estáticos
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Clase1Test {
    int data = 1;

    @BeforeEach
    void antesDeCadaPrueba() {
        data = 0;
        System.out.println("Antes de cada prueba: " + data);
    }

    @BeforeAll
    void antesDeTodasLasPruebas() {
        data = 0;
        System.out.println("Antes de todas las pruebas: " + data);
    }

    // Sólo se puede usar una anotación de descripción de pruebas
    // @Test
    @ParameterizedTest
    @ValueSource(shorts = {1, 2, 3})
    public void DadaLaAnotacionParameterizedTest_CuandoSeEjecuteLaPrueba_EntoncesDebeRepetirseConCadaParametro (short s) {
        System.out.println("Parámetro: " + s);
    }

    // @Test
    @DisplayName("Nombre personalizado de prueba")
    @RepeatedTest(5)
    public void DadaLaAnotacionRepeatedTest_CuandoSeEjecuteLaPrueba_EntoncesDebeRepetirseElNumeroDeRepeticionesIndicado () {
        // Note como data se inicializa a 0 en cada repeticion
        System.out.println("repetición: " + data++);
    }

    @Test
    @Disabled
    public void DadaLaAnotacionDisabled_CuandoSeIntenteEjecutarLaPrueba_EntoncesLaEjecucionDebeIgnorarse () {
        System.out.println("repetición: " + data++);
    }
}
