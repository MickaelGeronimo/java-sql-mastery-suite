package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.arrays.Matrix2DMasteryDemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Capítulo 35: Testes Automatizados de Matrizes (2D Arrays)")
class Matrix2DTest {

    @Test
    @DisplayName("Deve modelar a matriz do teclado telefônico 4x3 com os caracteres esperados")
    void shouldModelTelephoneKeypadCorrectly() {
        char[][] keypad = Matrix2DMasteryDemo.getTelephoneKeypad();

        assertThat(keypad).hasDimensions(4, 3);
        assertThat(keypad[0]).containsExactly('1', '2', '3');
        assertThat(keypad[1]).containsExactly('4', '5', '6');
        assertThat(keypad[2]).containsExactly('7', '8', '9');
        assertThat(keypad[3]).containsExactly('*', '0', '#');
    }

    @Test
    @DisplayName("Deve formatar matriz bidimensional de strings com quebras de linha e espaçamento")
    void shouldFormatStringGrid() {
        String[][] groceries = {
                {"apple", "orange", "banana"},
                {"potato", "onion", "carrot"}
        };

        String formatted = Matrix2DMasteryDemo.formatGrid(groceries);

        assertThat(formatted)
                .contains("apple orange banana \n")
                .contains("potato onion carrot \n");
    }

    @Test
    @DisplayName("Deve formatar teclado numérico em grade perfeitamente alinhada")
    void shouldFormatTelephoneKeypad() {
        char[][] keypad = Matrix2DMasteryDemo.getTelephoneKeypad();
        String display = Matrix2DMasteryDemo.formatKeypad(keypad);

        assertThat(display)
                .contains("1 2 3 \n")
                .contains("4 5 6 \n")
                .contains("7 8 9 \n")
                .contains("* 0 # \n");
    }

    @Test
    @DisplayName("Deve lidar com matrizes nulas ou vazias de forma segura")
    void shouldHandleNullOrEmptyMatrix() {
        assertThat(Matrix2DMasteryDemo.formatGrid(null)).isEmpty();
        assertThat(Matrix2DMasteryDemo.formatGrid(new String[][]{})).isEmpty();
    }

    @Test
    @DisplayName("Deve executar main demo de 2D Arrays sem lançar exceções")
    void shouldRunMainWithoutExceptions() {
        Matrix2DMasteryDemo.main(new String[]{});
    }
}
