package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.methods.MethodsMasteryDemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Capítulo 26: Testes Unitários de Métodos em Java")
class MethodsMasteryTest {

    @Test
    @DisplayName("Deve gerar mensagem de aniversário formatada corretamente")
    void shouldBuildBirthdayMessage() {
        String message = MethodsMasteryDemo.buildBirthdayMessage("Bro", 25);
        assertThat(message)
                .contains("Happy birthday dear Bro!")
                .contains("You are 25 years old!");
    }

    @Test
    @DisplayName("Deve lançar exceção ao passar nome nulo ou em branco para mensagem de aniversário")
    void shouldThrowExceptionForInvalidName() {
        assertThatThrownBy(() -> MethodsMasteryDemo.buildBirthdayMessage(null, 25))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> MethodsMasteryDemo.buildBirthdayMessage("   ", 25))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Deve calcular corretamente quadrado e cubo com métodos matemáticos")
    void shouldCalculateSquareAndCube() {
        assertThat(MethodsMasteryDemo.square(3.0)).isCloseTo(9.0, within(0.0001));
        assertThat(MethodsMasteryDemo.cube(3.0)).isCloseTo(27.0, within(0.0001));
        assertThat(MethodsMasteryDemo.square(4.0)).isCloseTo(16.0, within(0.0001));
        assertThat(MethodsMasteryDemo.cube(4.0)).isCloseTo(64.0, within(0.0001));
    }

    @Test
    @DisplayName("Deve concatenar nome completo de forma robusta e tratar nulos")
    void shouldConcatenateFullName() {
        assertThat(MethodsMasteryDemo.getFullName("Spongebob", "Squarepants"))
                .isEqualTo("Spongebob Squarepants");
        assertThat(MethodsMasteryDemo.getFullName("Patrick", null))
                .isEqualTo("Patrick");
        assertThat(MethodsMasteryDemo.getFullName(null, "Star"))
                .isEqualTo("Star");
        assertThat(MethodsMasteryDemo.getFullName(null, null))
                .isEmpty();
    }

    @Test
    @DisplayName("Deve verificar maioridade legal corretamente com predicado booleano")
    void shouldCheckLegalAge() {
        assertThat(MethodsMasteryDemo.isLegalAge(21)).isTrue();
        assertThat(MethodsMasteryDemo.isLegalAge(18)).isTrue();
        assertThat(MethodsMasteryDemo.isLegalAge(17)).isFalse();
        assertThat(MethodsMasteryDemo.isLegalAge(0)).isFalse();
    }

    @Test
    @DisplayName("Deve executar main demo sem lançar exceções")
    void shouldRunMainWithoutExceptions() {
        MethodsMasteryDemo.main(new String[]{});
    }
}
