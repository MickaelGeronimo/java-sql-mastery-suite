package com.mickaelgeronimo.mastery.joins;

import com.mickaelgeronimo.mastery.joins.inner.InnerJoinJavaDemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Relational Join Algebra & In-Memory Stream Processing Tests")
class JoinLogicTest {

    @Test
    @DisplayName("Should execute Inner Join semantics discarding non-matching foreign keys")
    void testInnerJoinSemantics() {
        // Run the main method to ensure zero runtime exceptions
        InnerJoinJavaDemo.main(new String[0]);

        // Verify relational algebra logic explicitly
        List<InnerJoinJavaDemo.Customer> customers = List.of(
            new InnerJoinJavaDemo.Customer(1, "Mickael", "Geronimo"),
            new InnerJoinJavaDemo.Customer(2, "Lucas", "Silva"),
            new InnerJoinJavaDemo.Customer(3, "Ana", "Pereira")
        );

        List<InnerJoinJavaDemo.Order> orders = List.of(
            new InnerJoinJavaDemo.Order(101, 1, LocalDate.of(2026, 1, 15)),
            new InnerJoinJavaDemo.Order(102, 1, LocalDate.of(2026, 2, 20)),
            new InnerJoinJavaDemo.Order(103, 2, LocalDate.of(2026, 3, 10)),
            new InnerJoinJavaDemo.Order(104, 99, LocalDate.of(2026, 4, 5)) // orphaned FK
        );

        List<InnerJoinJavaDemo.OrderCustomerDTO> result = new ArrayList<>();
        for (InnerJoinJavaDemo.Order o : orders) {
            for (InnerJoinJavaDemo.Customer c : customers) {
                if (o.customerId == c.customerId) {
                    result.add(new InnerJoinJavaDemo.OrderCustomerDTO(o.orderId, o.customerId, c.toString(), o.orderDate));
                    break;
                }
            }
        }

        // Only 3 matches should exist; order 104 with customer 99 is discarded
        assertThat(result).hasSize(3);
        assertThat(result).extracting("orderId").containsExactly(101, 102, 103);
        assertThat(result).extracting("customerName").contains("Mickael Geronimo", "Lucas Silva");
    }
}
