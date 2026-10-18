package com.mickaelgeronimo.mastery.dml.columns;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 30 (PARTE 2): DEMONSTRAÇÃO PRÁTICA AVANÇADA EM JAVA 17
 * Tema: Atributos de Coluna Avançados — DECIMAL vs Double, Chaves Compostas,
 *       Enums (TINYINT) e a Simulação do ERRO 1452 (Foreign Key Constraint Fail)
 * ==============================================================================
 */
public class ColumnAttributesPart2AdvancedDemo {

    // 1. Simulação do Enum mapeado para a coluna 'status' TINYINT com Default 1
    public enum OrderStatus {
        PROCESSED((byte) 1, "Processed"),
        SHIPPED((byte) 2, "Shipped"),
        DELIVERED((byte) 3, "Delivered");

        private final byte code;
        private final String description;

        OrderStatus(byte code, String description) {
            this.code = code;
            this.description = description;
        }

        public byte getCode() { return code; }
        public String getDescription() { return description; }

        public static OrderStatus fromCode(byte code) {
            for (OrderStatus s : values()) {
                if (s.code == code) return s;
            }
            throw new IllegalArgumentException("Código de status inválido: " + code);
        }
    }

    // 2. Chave Primária Composta (Composite PK da tabela order_items)
    public record OrderItemId(int orderId, int productId) {
        public OrderItemId {
            if (orderId <= 0 || productId <= 0) {
                throw new IllegalArgumentException("Erro SQL: Componentes da chave primária composta devem ser positivos e não nulos!");
            }
        }
    }

    // 3. Item do Pedido com BigDecimal (Espelhando DECIMAL(4,2))
    public record OrderItem(OrderItemId id, int quantity, BigDecimal unitPrice) {
        public OrderItem {
            Objects.requireNonNull(id, "Chave primária composta (order_id, product_id) é obrigatória!");
            if (quantity <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero!");
            Objects.requireNonNull(unitPrice, "unit_price não pode ser nulo!");
            if (unitPrice.scale() > 2) {
                throw new IllegalArgumentException("DECIMAL(4,2) aceita no máximo 2 casas decimais!");
            }
        }

        public BigDecimal totalAmount() {
            return unitPrice.multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
        }
    }

    // 4. Entidade Pedido com validação de Foreign Key
    public record Order(int orderId, int customerId, LocalDate orderDate, OrderStatus status, String comments) {}

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 30 (PARTE 2) - DECIMAL, CHAVES COMPOSTAS E FOREIGN KEYS    ");
        System.out.println("=========================================================================================\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: A ARMADILHA FATAL DO DOUBLE vs A PRECISÃO EXATA DO DECIMAL (BigDecimal)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. POR QUE USAMOS DECIMAL(4,2) EM VEZ DE FLOAT/DOUBLE PARA DINHEIRO? ---");
        double doubleVal1 = 0.1;
        double doubleVal2 = 0.2;
        double doubleSum = doubleVal1 + doubleVal2;
        System.out.println("Cálculo com double primitivo: 0.1 + 0.2 = " + doubleSum);
        System.out.println("Aviso: O double gera dízimas residuais (IEEE 754) que causam rombos em relatórios contábeis!");

        BigDecimal decVal1 = new BigDecimal("0.10");
        BigDecimal decVal2 = new BigDecimal("0.20");
        BigDecimal decSum = decVal1.add(decVal2);
        System.out.println("Cálculo com BigDecimal / DECIMAL(4,2): 0.10 + 0.20 = " + decSum + " (Precisão cirúrgica!)\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: INSERÇÃO EM TABELA COM CHAVE PRIMÁRIA COMPOSTA (order_items)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. CHAVE PRIMÁRIA COMPOSTA (order_items): SEM AUTO-INCREMENT! ---");
        System.out.println("+----------+------------+----------+------------+--------------+");
        System.out.println("| order_id | product_id | quantity | unit_price | total_amount |");
        System.out.println("+----------+------------+----------+------------+--------------+");

        OrderItem item1 = new OrderItem(new OrderItemId(2, 1), 1, new BigDecimal("9.10"));
        OrderItem item2 = new OrderItem(new OrderItemId(2, 4), 4, new BigDecimal("1.66"));
        OrderItem item3 = new OrderItem(new OrderItemId(2, 6), 2, new BigDecimal("2.94"));

        List<OrderItem> items = List.of(item1, item2, item3);
        for (OrderItem item : items) {
            System.out.printf("| %8d | %10d | %8d | %10s | %12s |\n",
                    item.id().orderId(), item.id().productId(), item.quantity(), item.unitPrice(), item.totalAmount());
        }
        System.out.println("+----------+------------+----------+------------+--------------+\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: SIMULAÇÃO DO ERRO 1452 DO MYSQL (FOREIGN KEY CONSTRAINT VIOLATION)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. TESTE DE INTEGRIDADE REFERENCIAL: ERRO 1452 (FOREIGN KEY FAIL) ---");
        Set<Integer> registeredCustomers = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int attemptedCustomerId = 9999; // Cliente que NÃO existe na tabela customers
        System.out.println("Tentando inserir pedido com customer_id = " + attemptedCustomerId + "...");

        try {
            if (!registeredCustomers.contains(attemptedCustomerId)) {
                throw new IllegalStateException(
                    "ERROR 1452 (23000): Cannot add or update a child row: a foreign key constraint fails " +
                    "(`sql_store`.`orders`, CONSTRAINT `fk_orders_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`))"
                );
            }
        } catch (IllegalStateException ex) {
            System.out.println("   [BLOQUEIO DO MOTOR CONFIRMADO]");
            System.out.println("   " + ex.getMessage());
        }

        System.out.println("\nDemostração concluída com 100% de sucesso!");
    }
}
