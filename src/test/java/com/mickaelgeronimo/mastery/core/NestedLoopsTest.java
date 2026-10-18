package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.loops.NestedLoopsGridDemo;
import com.mickaelgeronimo.mastery.core.loops.NestedLoopsMatrixGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Capítulo 25: Laços Aninhados (Nested Loops) Tests")
class NestedLoopsTest {

    @Test
    @DisplayName("Should execute NestedLoopsGridDemo main without runtime errors")
    void testGridDemoExecution() {
        NestedLoopsGridDemo.main(new String[0]);
    }

    @Test
    @DisplayName("Should generate a 5x8 matrix of dollar signs ($)")
    void testCreateDollarMatrix() {
        char[][] matrix = NestedLoopsMatrixGenerator.createMatrix(5, 8, '$');

        assertThat(matrix).hasDimensions(5, 8);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                assertThat(matrix[i][j]).isEqualTo('$');
            }
        }

        String formatted = NestedLoopsMatrixGenerator.formatMatrix(matrix);
        String expectedLine = "$$$$$$$$\n";
        assertThat(formatted).isEqualTo(expectedLine.repeat(5));
    }

    @Test
    @DisplayName("Should generate a 3x6 matrix of at signs (@)")
    void testCreateAtSignMatrix() {
        char[][] matrix = NestedLoopsMatrixGenerator.createMatrix(3, 6, '@');

        assertThat(matrix).hasDimensions(3, 6);
        String formatted = NestedLoopsMatrixGenerator.formatMatrix(matrix);
        String expectedLine = "@@@@@@\n";
        assertThat(formatted).isEqualTo(expectedLine.repeat(3));
    }

    @Test
    @DisplayName("Should generate a 5x3 matrix of ampersands (&)")
    void testCreateAmpersandMatrix() {
        char[][] matrix = NestedLoopsMatrixGenerator.createMatrix(5, 3, '&');

        assertThat(matrix).hasDimensions(5, 3);
        String formatted = NestedLoopsMatrixGenerator.formatMatrix(matrix);
        String expectedLine = "&&&\n";
        assertThat(formatted).isEqualTo(expectedLine.repeat(5));
    }

    @Test
    @DisplayName("Should safely return empty matrix when non-positive dimensions are provided")
    void testBoundaryDimensions() {
        char[][] zeroRows = NestedLoopsMatrixGenerator.createMatrix(0, 10, '#');
        assertThat(zeroRows).isEmpty();

        char[][] zeroCols = NestedLoopsMatrixGenerator.createMatrix(5, 0, '#');
        assertThat(zeroCols).isEmpty();

        char[][] negativeDim = NestedLoopsMatrixGenerator.createMatrix(-2, -5, '#');
        assertThat(negativeDim).isEmpty();

        assertThat(NestedLoopsMatrixGenerator.formatMatrix(null)).isEmpty();
        assertThat(NestedLoopsMatrixGenerator.formatMatrix(new char[0][0])).isEmpty();
    }
}
