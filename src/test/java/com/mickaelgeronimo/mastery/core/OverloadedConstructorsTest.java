package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.oop.OverloadedConstructorsDemo;
import com.mickaelgeronimo.mastery.core.oop.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Capítulo 41: Testes Automatizados de Construtores Sobrecarregados (User)")
class OverloadedConstructorsTest {

    @Test
    @DisplayName("Deve inicializar User com 1 parâmetro (apenas username) e valores padrão")
    void shouldInstantiateUserWithOneParameter() {
        User user = new User("Spongebob");

        assertThat(user.username).isEqualTo("Spongebob");
        assertThat(user.email).isEqualTo("Not provided");
        assertThat(user.age).isZero();
    }

    @Test
    @DisplayName("Deve inicializar User com 2 parâmetros (username e email) e idade zerada")
    void shouldInstantiateUserWithTwoParameters() {
        User user = new User("Patrick", "ps@gmail.com");

        assertThat(user.username).isEqualTo("Patrick");
        assertThat(user.email).isEqualTo("ps@gmail.com");
        assertThat(user.age).isZero();
    }

    @Test
    @DisplayName("Deve inicializar User com 3 parâmetros (username, email e age) preenchidos")
    void shouldInstantiateUserWithThreeParameters() {
        User user = new User("Sandy", "sc@gmail.com", 27);

        assertThat(user.username).isEqualTo("Sandy");
        assertThat(user.email).isEqualTo("sc@gmail.com");
        assertThat(user.age).isEqualTo(27);
        assertThat(user.getProfile()).contains("Username: Sandy").contains("Age: 27");
    }

    @Test
    @DisplayName("Deve inicializar User sem parâmetros no modo Guest com valores padrão")
    void shouldInstantiateUserWithZeroParametersAsGuest() {
        User user = new User();

        assertThat(user.username).isEqualTo("Guest");
        assertThat(user.email).isEqualTo("Not provided");
        assertThat(user.age).isZero();
    }

    @Test
    @DisplayName("Deve tratar email nulo substituindo por 'Not provided'")
    void shouldHandleNullEmailGracefully() {
        User user = new User("Plankton", null, 42);

        assertThat(user.username).isEqualTo("Plankton");
        assertThat(user.email).isEqualTo("Not provided");
        assertThat(user.age).isEqualTo(42);
    }

    @Test
    @DisplayName("Deve normalizar idade negativa para zero de forma defensiva")
    void shouldSanitizeNegativeAge() {
        User user = new User("MrKrabs", "krabs@krusty.com", -10);

        assertThat(user.age).isZero();
    }

    @Test
    @DisplayName("Deve rejeitar username nulo lançando NullPointerException")
    void shouldRejectNullUsername() {
        assertThatThrownBy(() -> new User(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Nome de usuário não pode ser nulo");
    }

    @Test
    @DisplayName("Deve executar a demonstração OverloadedConstructorsDemo sem exceções")
    void shouldRunOverloadedConstructorsDemoSuccessfully() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
            OverloadedConstructorsDemo.runDemo();

            String output = outputStream.toString(StandardCharsets.UTF_8);

            assertThat(output)
                    .contains("CAPÍTULO 41: CONSTRUTORES SOBRECARREGADOS")
                    .contains("Spongebob")
                    .contains("Patrick")
                    .contains("Sandy")
                    .contains("Guest")
                    .contains("Not provided");
        } finally {
            System.setOut(originalOut);
        }
    }
}
