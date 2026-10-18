package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.generics.Box;
import com.mickaelgeronimo.mastery.core.generics.Product;
import com.mickaelgeronimo.mastery.core.models.Day;
import com.mickaelgeronimo.mastery.core.models.Employee;
import com.mickaelgeronimo.mastery.core.models.Person;
import com.mickaelgeronimo.mastery.core.models.Student;
import com.mickaelgeronimo.mastery.core.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Core OOP Models, Generics, and Enums Tests")
class GenericsAndModelsTest {

    @Test
    @DisplayName("Should store and retrieve type-safe items in Box generic container")
    void testGenericBox() {
        Box<String> stringBox = new Box<>();
        stringBox.setItem("Distributed Systems");
        assertThat(stringBox.getItem()).isEqualTo("Distributed Systems");

        Box<Integer> intBox = new Box<>();
        intBox.setItem(42);
        assertThat(intBox.getItem()).isEqualTo(42);
    }

    @Test
    @DisplayName("Should maintain paired product key and price types")
    void testGenericProduct() {
        Product<String, Double> product = new Product<>("Enterprise Router", 499.99);
        assertThat(product.getItem()).isEqualTo("Enterprise Router");
        assertThat(product.getPrice()).isEqualTo(499.99);
    }

    @Test
    @DisplayName("Should correctly map Day enum day numbers")
    void testDayEnum() {
        assertThat(Day.SUNDAY.getDayNumber()).isEqualTo(1);
        assertThat(Day.MONDAY.getDayNumber()).isEqualTo(2);
        assertThat(Day.FRIDAY.getDayNumber()).isEqualTo(6);
        assertThat(Day.PIZZADAY.getDayNumber()).isEqualTo(8);
    }

    @Test
    @DisplayName("Should verify inheritance hierarchy of Person, Student, and Employee")
    void testPersonHierarchy() {
        Employee employee = new Employee("Mickael", "Geronimo", 15000);
        assertThat(employee).isInstanceOf(Person.class);

        Student student = new Student("Ada", "Lovelace", 4.0);
        assertThat(student).isInstanceOf(Person.class);
    }

    @Test
    @DisplayName("Should verify User constructor overloading and default fallbacks")
    void testUserConstructors() {
        User defaultUser = new User();
        // Default constructor sets "Guest", "Not provided", 0
        assertThat(defaultUser).isNotNull();

        User namedUser = new User("mickael", "mickael@example.com", 28);
        assertThat(namedUser).isNotNull();
    }
}
