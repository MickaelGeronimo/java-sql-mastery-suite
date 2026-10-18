package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.arrays.VarargsCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Capítulo 34: Testes Automatizados de Varargs em Java")
class VarargsTest {

    @Test
    @DisplayName("Deve somar quantidades variadas de inteiros via varargs")
    void shouldAddVaryingNumberOfIntegers() {
        assertThat(VarargsCalculator.add(1, 2)).isEqualTo(3);
        assertThat(VarargsCalculator.add(1, 2, 3, 4)).isEqualTo(10);
        assertThat(VarargsCalculator.add(1, 2, 3, 4, 5, 6)).isEqualTo(21);
        assertThat(VarargsCalculator.add(42)).isEqualTo(42);
    }

    @Test
    @DisplayName("Deve retornar 0 ao somar sem nenhum argumento ou passar nulo")
    void shouldHandleEmptyAndNullInAdd() {
        assertThat(VarargsCalculator.add()).isEqualTo(0);
        assertThat(VarargsCalculator.add((int[]) null)).isEqualTo(0);
    }

    @Test
    @DisplayName("Deve calcular a média de múltiplos números decimais")
    void shouldCalculateAverageCorrectly() {
        assertThat(VarargsCalculator.average(1.0, 2.0, 3.0, 4.0)).isCloseTo(2.5, within(0.0001));
        assertThat(VarargsCalculator.average(10.0, 20.0, 30.0)).isCloseTo(20.0, within(0.0001));
        assertThat(VarargsCalculator.average(100.0)).isCloseTo(100.0, within(0.0001));
    }

    @Test
    @DisplayName("Deve acionar a cláusula de guarda e retornar 0.0 em vez de NaN ao chamar average sem argumentos")
    void shouldTriggerGuardClauseAndAvoidNaN() {
        double resultEmpty = VarargsCalculator.average();
        double resultNull = VarargsCalculator.average((double[]) null);

        assertThat(resultEmpty).isEqualTo(0.0);
        assertThat(Double.isNaN(resultEmpty)).isFalse();

        assertThat(resultNull).isEqualTo(0.0);
        assertThat(Double.isNaN(resultNull)).isFalse();
    }

    @Test
    @DisplayName("Deve executar main demo do varargs sem lançar exceções")
    void shouldRunMainWithoutExceptions() {
        VarargsCalculator.main(new String[]{});
    }
}
