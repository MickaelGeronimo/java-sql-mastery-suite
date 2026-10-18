package com.mickaelgeronimo.mastery.joins.inner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * InnerJoinJavaDemo
 * 
 * Demonstração prática para iniciantes de como o INNER JOIN do SQL funciona
 * no mundo da Programação Orientada a Objetos (POO) em Java.
 * 
 * No SQL: Você conecta duas tabelas usando Chave Primária (PK) e Chave Estrangeira (FK).
 * No Java: O objeto Order possui uma referência direta para o objeto Customer (@ManyToOne).
 */
public class InnerJoinJavaDemo {

    // 1. Tabela 'customers' representada como Classe Java
    public static class Customer {
        public int customerId; // Primary Key
        public String firstName;
        public String lastName;

        public Customer(int customerId, String firstName, String lastName) {
            this.customerId = customerId;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    // 2. Tabela 'orders' representada como Classe Java
    public static class Order {
        public int orderId;    // Primary Key
        public int customerId; // Foreign Key (Chave Estrangeira que aponta para Customer)
        public LocalDate orderDate;

        public Order(int orderId, int customerId, LocalDate orderDate) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.orderDate = orderDate;
        }
    }

    // 3. Resultado do INNER JOIN (DTO - Data Transfer Object)
    public static class OrderCustomerDTO {
        public int orderId;
        public int customerId;
        public String customerName;
        public LocalDate orderDate;

        public OrderCustomerDTO(int orderId, int customerId, String customerName, LocalDate orderDate) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.customerName = customerName;
            this.orderDate = orderDate;
        }

        @Override
        public String toString() {
            return String.format("| %8d | %11d | %-20s | %10s |", 
                orderId, customerId, customerName, orderDate);
        }
    }

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: COMO O 'INNER JOIN' FUNCIONA NA MEMÓRIA");
        System.out.println("==========================================================================\n");

        // Populando a "tabela" customers
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Mickael", "Geronimo"));
        customers.add(new Customer(2, "Lucas", "Silva"));
        customers.add(new Customer(3, "Ana", "Pereira"));

        // Populando a "tabela" orders
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(101, 1, LocalDate.of(2026, 1, 15)));
        orders.add(new Order(102, 1, LocalDate.of(2026, 2, 20)));
        orders.add(new Order(103, 2, LocalDate.of(2026, 3, 10)));
        orders.add(new Order(104, 99, LocalDate.of(2026, 4, 5))); // Pedido com cliente inexistente!

        System.out.println("Executando: orders JOIN customers ON orders.customerId = customers.customerId\n");
        System.out.println("+----------+-------------+----------------------+------------+");
        System.out.println("| order_id | customer_id | customer_name        | order_date |");
        System.out.println("+----------+-------------+----------------------+------------+");

        // Execução manual do INNER JOIN em Java:
        // Para cada pedido, procuramos o cliente correspondente.
        // Se encontrar, junta os dois! Se não encontrar (ex: id 99), descarta!
        List<OrderCustomerDTO> joinResult = new ArrayList<>();

        for (Order o : orders) {
            for (Customer c : customers) {
                if (o.customerId == c.customerId) { // Condição ON o.customer_id = c.customer_id
                    joinResult.add(new OrderCustomerDTO(o.orderId, o.customerId, c.toString(), o.orderDate));
                    break;
                }
            }
        }

        joinResult.forEach(System.out::println);
        System.out.println("+----------+-------------+----------------------+------------+");
        System.out.println("Total de registros combinados: " + joinResult.size() + " pedidos.");
        System.out.println("Nota: O pedido 104 (cliente 99) foi descartado porque não tem par no INNER JOIN!\n");

        System.out.println("==========================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! O INNER JOIN É A INTERSEÇÃO DAS DUAS TABELAS!");
        System.out.println("==========================================================================");
    }
}
