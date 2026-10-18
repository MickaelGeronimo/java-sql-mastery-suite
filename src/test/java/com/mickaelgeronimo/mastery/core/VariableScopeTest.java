package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.methods.VariableScopeDemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Capítulo 28: Testes Unitários de Escopo de Variáveis (Variable Scope)")
class VariableScopeTest {

    @Test
    @DisplayName("Deve demonstrar o sombreamento (shadowing) onde a variável local prevalece sobre a de classe")
    void shouldDemonstrateLocalShadowing() {
        int result = VariableScopeDemo.resolveWithLocalShadowing();
        // A variável local x = 1 deve prevalecer sobre classX = 3
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Deve acessar a variável de classe explicitamente contornando o sombreamento")
    void shouldResolveExplicitClassVariable() {
        int result = VariableScopeDemo.resolveExplicitClassVariable();
        // Acesso qualificado pela classe deve retornar 3
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Deve resolver a variável de classe quando não existir variável local no método")
    void shouldResolveWithoutLocalVariable() {
        int result = VariableScopeDemo.resolveWithoutLocalVariable();
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Deve demonstrar isolamento de escopo local entre métodos distintos")
    void shouldDemonstrateLocalScopeIsolation() {
        int localMethod1 = VariableScopeDemo.resolveWithLocalShadowing(); // x = 1
        int localMethod2 = VariableScopeDemo.doSomethingLocal();          // x = 2

        assertThat(localMethod1).isEqualTo(1);
        assertThat(localMethod2).isEqualTo(2);
        assertThat(VariableScopeDemo.classX).isEqualTo(3);
    }

    @Test
    @DisplayName("Deve executar main demo de escopo sem lançar exceções")
    void shouldRunMainWithoutExceptions() {
        VariableScopeDemo.main(new String[]{});
    }
}
