package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.oop.superkeyword.Employee;
import com.mickaelgeronimo.mastery.core.oop.superkeyword.Person;
import com.mickaelgeronimo.mastery.core.oop.superkeyword.Student;
import com.mickaelgeronimo.mastery.core.oop.superkeyword.SuperKeywordDemo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Capítulo 45: Suíte de Testes da Palavra-chave super")
class SuperKeywordTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Person: inicialização direta de atributos e método showName")
    void testPersonInitialization() {
        Person person = new Person("Tom", "Riddle");

        assertThat(person.first).isEqualTo("Tom");
        assertThat(person.last).isEqualTo("Riddle");
        assertThat(person.getFirst()).isEqualTo("Tom");
        assertThat(person.getLast()).isEqualTo("Riddle");
        assertThat(person.getFullName()).isEqualTo("Tom Riddle");

        person.showName();
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo("Tom Riddle");
    }

    @Test
    @DisplayName("Student: encadeamento de construtor super(first, last) e método showGPA")
    void testStudentConstructorChaining() {
        Student student = new Student("Harry", "Potter", 3.25);

        // Atributos herdados inicializados pelo construtor de Person via super(...)
        assertThat(student.first).isEqualTo("Harry");
        assertThat(student.last).isEqualTo("Potter");
        assertThat(student.getFullName()).isEqualTo("Harry Potter");

        // Atributo próprio do Student
        assertThat(student.gpa).isEqualTo(3.25);
        assertThat(student.getGpa()).isEqualTo(3.25);
        assertThat(student.getGpaFormatted()).isEqualTo("Harry's gpa is: 3.25");

        student.showGPA();
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo("Harry's gpa is: 3.25");
    }

    @Test
    @DisplayName("Employee: encadeamento de construtor super(first, last) e método showSalary")
    void testEmployeeConstructorChaining() {
        Employee employee = new Employee("Rubeus", "Hagrid", 50000.0);

        // Atributos herdados inicializados pelo construtor de Person via super(...)
        assertThat(employee.first).isEqualTo("Rubeus");
        assertThat(employee.last).isEqualTo("Hagrid");
        assertThat(employee.getFullName()).isEqualTo("Rubeus Hagrid");

        // Atributo próprio do Employee
        assertThat(employee.salary).isEqualTo(50000.0);
        assertThat(employee.getSalary()).isEqualTo(50000.0);
        assertThat(employee.getSalaryFormatted()).isEqualTo("Rubeus's salary is $50000");

        employee.showSalary();
        assertThat(outputStreamCaptor.toString().trim()).isEqualTo("Rubeus's salary is $50000");
    }

    @Test
    @DisplayName("Polimorfismo e Hierarquia: Student e Employee são instâncias de Person")
    void testPolymorphismAndHierarchy() {
        Person p1 = new Student("Hermione", "Granger", 4.0);
        Person p2 = new Employee("Albus", "Dumbledore", 120000.0);

        assertThat(p1).isInstanceOf(Person.class);
        assertThat(p1).isInstanceOf(Student.class);
        assertThat(p2).isInstanceOf(Person.class);
        assertThat(p2).isInstanceOf(Employee.class);

        List<Person> people = List.of(p1, p2);
        List<String> fullNames = people.stream().map(Person::getFullName).toList();

        assertThat(fullNames).containsExactly("Hermione Granger", "Albus Dumbledore");
    }

    @Test
    @DisplayName("Validação de nulos e integridade estrutural")
    void testNullValidation() {
        assertThatThrownBy(() -> new Person(null, "Riddle"))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("First name cannot be null");

        assertThatThrownBy(() -> new Person("Tom", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Last name cannot be null");

        assertThatThrownBy(() -> new Student(null, "Potter", 3.5))
                .isInstanceOf(NullPointerException.class);

        assertThatThrownBy(() -> new Employee("Rubeus", null, 40000))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("SuperKeywordDemo: execução do método main sem exceções")
    void testDemoExecution() {
        SuperKeywordDemo.main(new String[]{});
        String output = outputStreamCaptor.toString();

        assertThat(output)
                .contains("Capítulo 45: Palavra-chave super")
                .contains("Tom Riddle")
                .contains("Harry Potter")
                .contains("Harry's gpa is: 3.25")
                .contains("Rubeus Hagrid")
                .contains("Rubeus's salary is $50000");
    }
}
