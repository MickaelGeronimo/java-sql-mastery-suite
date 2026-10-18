package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.oop.ConstructorsDemo;
import com.mickaelgeronimo.mastery.core.oop.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Capítulo 40: Testes Automatizados de Construtores em Java (Student)")
class ConstructorsStudentTest {

    @Test
    @DisplayName("Deve inicializar Spongebob com os valores canônicos via construtor")
    void shouldInitializeStudentWithCanonicalConstructor() {
        Student student = new Student("Spongebob", 30, 3.2);

        assertThat(student.name).isEqualTo("Spongebob");
        assertThat(student.age).isEqualTo(30);
        assertThat(student.gpa).isEqualTo(3.2);
        assertThat(student.isEnrolled).isTrue();
    }

    @Test
    @DisplayName("Deve instanciar múltiplos alunos com identidades e estados independentes")
    void shouldInstantiateMultipleStudentsIndependently() {
        Student student1 = new Student("Spongebob", 30, 3.2);
        Student student2 = new Student("Patrick", 34, 1.5);
        Student student3 = new Student("Sandy", 27, 4.0);

        assertThat(student1.name).isEqualTo("Spongebob");
        assertThat(student2.name).isEqualTo("Patrick");
        assertThat(student3.name).isEqualTo("Sandy");

        assertThat(student1.gpa).isEqualTo(3.2);
        assertThat(student2.gpa).isEqualTo(1.5);
        assertThat(student3.gpa).isEqualTo(4.0);
    }

    @Test
    @DisplayName("Deve ativar automaticamente a matrícula (isEnrolled = true) na criação")
    void shouldAutomaticallyActivateEnrollment() {
        Student student = new Student("Gary", 5, 4.0);
        assertThat(student.isEnrolled).isTrue();
    }

    @Test
    @DisplayName("Deve permitir matrícula inativa em construtor sobrecarregado")
    void shouldSupportOverloadedConstructorWithEnrollment() {
        Student student = new Student("Squidward", 45, 2.8, false);

        assertThat(student.name).isEqualTo("Squidward");
        assertThat(student.age).isEqualTo(45);
        assertThat(student.gpa).isEqualTo(2.8);
        assertThat(student.isEnrolled).isFalse();
        assertThat(student.getSummary()).contains("Matriculado: Não");
    }

    @Test
    @DisplayName("Deve imprimir mensagem personalizada no método study() usando this.name")
    void shouldPrintPersonalizedStudyMessage() {
        Student s1 = new Student("Spongebob", 30, 3.2);
        Student s2 = new Student("Patrick", 34, 1.5);
        Student s3 = new Student("Sandy", 27, 4.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));

            s1.study();
            s2.study();
            s3.study();

            String output = outputStream.toString(StandardCharsets.UTF_8);

            assertThat(output)
                    .contains("Spongebob is studying")
                    .contains("Patrick is studying")
                    .contains("Sandy is studying");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("Deve rejeitar nome nulo com NullPointerException defensiva")
    void shouldRejectNullNameDefensively() {
        assertThatThrownBy(() -> new Student(null, 20, 3.5))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Nome do estudante não pode ser nulo");
    }

    @Test
    @DisplayName("Deve executar a demonstração completa ConstructorsDemo sem falhas")
    void shouldRunConstructorsDemoWithoutErrors() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
            ConstructorsDemo.runDemo();

            String output = outputStream.toString(StandardCharsets.UTF_8);

            assertThat(output)
                    .contains("CAPÍTULO 40: CONSTRUTORES EM JAVA")
                    .contains("Spongebob")
                    .contains("Patrick")
                    .contains("Sandy")
                    .contains("is studying");
        } finally {
            System.setOut(originalOut);
        }
    }
}
