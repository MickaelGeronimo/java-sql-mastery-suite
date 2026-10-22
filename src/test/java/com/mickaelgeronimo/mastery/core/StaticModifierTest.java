package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.oop.Friend;
import com.mickaelgeronimo.mastery.core.oop.MathUtilityDemo;
import com.mickaelgeronimo.mastery.core.oop.StaticDemo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Capítulo 43: Testes Automatizados do Modificador Static em Java")
class StaticModifierTest {

    @BeforeEach
    void setUp() {
        Friend.resetFriendsCount();
    }

    @Test
    @DisplayName("Deve incrementar a variável estática numOfFriends a cada nova instância de Friend")
    void shouldIncrementStaticCounterGloballyAcrossAllInstances() {
        assertThat(Friend.numOfFriends).isZero();

        new Friend("Spongebob");
        assertThat(Friend.numOfFriends).isEqualTo(1);

        new Friend("Patrick");
        assertThat(Friend.numOfFriends).isEqualTo(2);

        new Friend("Squidward");
        assertThat(Friend.numOfFriends).isEqualTo(3);

        new Friend("Sandy");
        assertThat(Friend.numOfFriends).isEqualTo(4);

        new Friend("Gary");
        assertThat(Friend.numOfFriends).isEqualTo(5);
    }

    @Test
    @DisplayName("Deve manter os atributos de instância únicos enquanto compartilha o contador estático")
    void shouldMaintainIndependentInstanceAttributesWhileSharingStaticVariable() {
        Friend f1 = new Friend("Spongebob");
        Friend f2 = new Friend("Patrick");

        assertThat(f1.name).isEqualTo("Spongebob");
        assertThat(f2.name).isEqualTo("Patrick");
        assertThat(Friend.numOfFriends).isEqualTo(2);
    }

    @Test
    @DisplayName("Deve imprimir a mensagem oficial no método estático Friend.showFriends()")
    void shouldPrintCanonicalShowFriendsMessage() {
        new Friend("Spongebob");
        new Friend("Patrick");
        new Friend("Squidward");
        new Friend("Sandy");
        new Friend("Gary");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
            Friend.showFriends();

            String output = outputStream.toString(StandardCharsets.UTF_8);
            assertThat(output).contains("You have 5 total friends");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("Deve disponibilizar funções utilitárias puras e constantes na classe MathUtilityDemo")
    void shouldProvideStaticUtilityMathMethodsWithoutInstantiation() {
        assertThat(MathUtilityDemo.round(3.99)).isEqualTo(4L);
        assertThat(MathUtilityDemo.round(3.14)).isEqualTo(3L);
        assertThat(MathUtilityDemo.square(5.0)).isEqualTo(25.0);
        assertThat(MathUtilityDemo.max(10, 42)).isEqualTo(42);
        assertThat(MathUtilityDemo.PI).isCloseTo(3.14159265, org.assertj.core.data.Offset.offset(0.00001));
    }

    @Test
    @DisplayName("Deve impedir a instanciação da classe utilitária mesmo via reflexão")
    void shouldPreventInstantiationOfUtilityClassViaReflection() throws NoSuchMethodException {
        Constructor<MathUtilityDemo> constructor = MathUtilityDemo.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        assertThatThrownBy(constructor::newInstance)
                .isInstanceOf(InvocationTargetException.class)
                .hasCauseInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Deve rejeitar nome nulo de amigo com NullPointerException")
    void shouldRejectNullFriendName() {
        assertThatThrownBy(() -> new Friend(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Nome do amigo não pode ser nulo");
    }

    @Test
    @DisplayName("Deve executar a demonstração completa StaticDemo sem erros")
    void shouldRunStaticDemoSuccessfully() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
            StaticDemo.runDemo();

            String output = outputStream.toString(StandardCharsets.UTF_8);
            assertThat(output)
                    .contains("CAPÍTULO 43: O MODIFICADOR STATIC EM JAVA")
                    .contains("Total de amigos contados: 5")
                    .contains("You have 5 total friends")
                    .contains("Math.round(3.99) = 4");
        } finally {
            System.setOut(originalOut);
        }
    }
}
