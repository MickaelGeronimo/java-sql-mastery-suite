package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.geometry.Circle;
import com.mickaelgeronimo.mastery.core.geometry.Rectangle;
import com.mickaelgeronimo.mastery.core.geometry.Shape;
import com.mickaelgeronimo.mastery.core.geometry.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Object-Oriented Geometry & Polymorphism Tests")
class GeometryTest {

    @Test
    @DisplayName("Should correctly calculate circle area")
    void testCircleArea() {
        Circle circle = new Circle(5.0);
        double expected = Math.PI * 25.0;
        assertThat(circle.area()).isCloseTo(expected, within(0.0001));
    }

    @Test
    @DisplayName("Should correctly calculate rectangle area")
    void testRectangleArea() {
        Rectangle rectangle = new Rectangle(4.0, 7.0);
        assertThat(rectangle.area()).isEqualTo(28.0);
    }

    @Test
    @DisplayName("Should correctly calculate triangle area")
    void testTriangleArea() {
        Triangle triangle = new Triangle(6.0, 3.0);
        assertThat(triangle.area()).isEqualTo(9.0);
    }

    @Test
    @DisplayName("Should handle polymorphic collection of shapes")
    void testPolymorphicShapes() {
        List<Shape> shapes = List.of(
            new Rectangle(2.0, 5.0), // 10.0
            new Triangle(4.0, 5.0),  // 10.0
            new Circle(2.0)          // ~12.566
        );

        double totalArea = shapes.stream().mapToDouble(Shape::area).sum();
        assertThat(totalArea).isGreaterThan(32.5);
    }
}
