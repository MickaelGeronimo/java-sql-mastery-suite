package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.arrays.ArraysMasteryDemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Capítulo 31: Testes Automatizados de Arrays em Java")
class ArraysMasteryTest {

    @Test
    @DisplayName("Deve acessar elementos por índice e reportar o tamanho correto")
    void shouldAccessElementsByIndexAndLength() {
        String[] fruits = {"apple", "orange", "banana", "coconut"};

        assertThat(fruits).hasSize(4);
        assertThat(fruits[0]).isEqualTo("apple");
        assertThat(fruits[1]).isEqualTo("orange");
        assertThat(fruits[2]).isEqualTo("banana");
        assertThat(fruits[3]).isEqualTo("coconut");
    }

    @Test
    @DisplayName("Deve lançar ArrayIndexOutOfBoundsException ao acessar índice inválido")
    void shouldThrowArrayIndexOutOfBoundsException() {
        String[] fruits = {"apple", "orange", "banana", "coconut"};

        assertThatThrownBy(() -> {
            String ignored = fruits[4];
        }).isInstanceOf(ArrayIndexOutOfBoundsException.class);

        assertThatThrownBy(() -> {
            String ignored = fruits[-1];
        }).isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("Deve ordenar cópia de array sem alterar o original (Imutabilidade defensiva)")
    void shouldSortArrayCopyDefensively() {
        String[] original = {"orange", "apple", "coconut", "banana"};
        String[] sorted = ArraysMasteryDemo.sortCopy(original);

        assertThat(sorted).containsExactly("apple", "banana", "coconut", "orange");
        assertThat(original).containsExactly("orange", "apple", "coconut", "banana");
    }

    @Test
    @DisplayName("Deve preencher novo array com valor especificado via Arrays.fill")
    void shouldCreateFilledArray() {
        String[] filled = ArraysMasteryDemo.createFilledArray(5, "pineapple");

        assertThat(filled).hasSize(5);
        assertThat(filled).containsOnly("pineapple");
    }

    @Test
    @DisplayName("Deve concatenar itens com laço for-each")
    void shouldJoinWithForEach() {
        String[] items = {"Maçã", "Pera", "Uva"};
        String joined = ArraysMasteryDemo.joinWithForEach(items);

        assertThat(joined).isEqualTo("Maçã, Pera, Uva");
        assertThat(ArraysMasteryDemo.joinWithForEach(new String[]{})).isEmpty();
        assertThat(ArraysMasteryDemo.joinWithForEach(null)).isEmpty();
    }

    @Test
    @DisplayName("Deve executar main demo de arrays sem lançar exceções")
    void shouldRunMainWithoutExceptions() {
        ArraysMasteryDemo.main(new String[]{});
    }
}
