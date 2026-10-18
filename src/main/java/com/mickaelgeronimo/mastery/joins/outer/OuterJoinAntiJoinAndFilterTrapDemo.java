package com.mickaelgeronimo.mastery.joins.outer;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 23 (PARTE 2): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: Padrão Anti-Join (WHERE IS NULL), A Armadilha do Filtro no WHERE vs ON,
 *       e a Diferença Crucial entre COUNT(*) e COUNT(coluna)
 * ==============================================================================
 */
public class OuterJoinAntiJoinAndFilterTrapDemo {

    static class Customer {
        int id;
        String name;
        String phone;

        public Customer(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }
    }

    static class Order {
        int orderId;
        int customerId;
        String orderDate;

        public Order(int orderId, int customerId, String orderDate) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.orderDate = orderDate;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: ANTI-JOIN, ARMADILHA DE FILTRO NO WHERE E CONTADORES DE NULO");
        System.out.println("=========================================================================================\n");

        List<Customer> customers = Arrays.asList(
            new Customer(1, "Babara MacCaffrey", "781-932-9754"),
            new Customer(2, "Ines Brushfield", "804-427-9456"),
            new Customer(3, "Freddi Boagey", "719-724-7869"),
            new Customer(4, "Ambur Roseburgh", "407-231-8017"),
            new Customer(5, "Clemmie Betchley", "415-144-6037"),
            new Customer(6, "Elka Twiddell", "312-480-8498"),
            new Customer(7, "Ilene Palamis", "615-641-4759"),
            new Customer(8, "Levy Mynett", "970-362-9528"),
            new Customer(9, "Reggy Gabarn", "555-019-2834"),
            new Customer(10, "Babette Larriman", "901-725-5468")
        );

        List<Order> orders = Arrays.asList(
            new Order(1, 6, "2019-01-30"),
            new Order(2, 7, "2018-08-02"),
            new Order(3, 8, "2017-12-01"),
            new Order(4, 2, "2017-01-22"),
            new Order(5, 5, "2017-08-25"),
            new Order(6, 10, "2018-11-18"),
            new Order(7, 2, "2018-09-22"),
            new Order(8, 5, "2018-06-08"),
            new Order(9, 10, "2017-07-05"),
            new Order(10, 6, "2018-04-22")
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O PADRÃO ANTI-JOIN (ISOLANDO CLIENTES QUE NUNCA COMPRARAM)
        // SQL: SELECT ... FROM customers c LEFT JOIN orders o ON ... WHERE o.order_id IS NULL;
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. O PADRÃO ANTI-JOIN (CLIENTES INATIVOS / WHERE order_id IS NULL) ---");
        System.out.println("+-------------+----------------------+--------------------+---------------------+");
        System.out.println("| customer_id | first_name           | phone              | Status de Venda     |");
        System.out.println("+-------------+----------------------+--------------------+---------------------+");

        int antiJoinCount = 0;
        for (Customer c : customers) {
            boolean hasOrder = false;
            for (Order o : orders) {
                if (c.id == o.customerId) {
                    hasOrder = true;
                    break;
                }
            }
            // Anti-Join: Somente quem NÃO tem correspondência entra!
            if (!hasOrder) {
                System.out.printf("| %-11d | %-20s | %-18s | %-19s |\n",
                        c.id, c.name, c.phone, "0 Pedidos (Inativo)");
                antiJoinCount++;
            }
        }
        System.out.println("+-------------+----------------------+--------------------+---------------------+");
        System.out.println("Total de clientes inativos resgatados pelo Anti-Join: " + antiJoinCount + " clientes.\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: A ARMADILHA DO FILTRO NO WHERE (TRANSFORMAÇÃO EM INNER JOIN)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. A ARMADILHA DO FILTRO NO WHERE vs A CORREÇÃO NO ON ---");
        System.out.println("Pergunta: Queremos todos os clientes, e se tiverem pedidos de 2018 em diante, exibir a data.");

        // Abordagem Errada (Filtro no WHERE):
        int wrongFilterCount = 0;
        for (Customer c : customers) {
            for (Order o : orders) {
                if (c.id == o.customerId) {
                    // WHERE o.order_date >= '2018-01-01' (Elimina os nulos sem querer!)
                    if (o.orderDate.compareTo("2018-01-01") >= 0) {
                        wrongFilterCount++;
                    }
                }
            }
        }
        System.out.println("• Abordagem Errada (Filtro no WHERE): Retornou " + wrongFilterCount + " linhas. (Clientes sem pedidos SUMIRAM!)");

        // Abordagem Correta (Filtro no ON):
        int rightFilterCount = 0;
        for (Customer c : customers) {
            boolean matched2018 = false;
            for (Order o : orders) {
                // Filtro dentro da condição de junção (ON):
                if (c.id == o.customerId && o.orderDate.compareTo("2018-01-01") >= 0) {
                    rightFilterCount++;
                    matched2018 = true;
                }
            }
            if (!matched2018) {
                rightFilterCount++; // Preserva o cliente com NULL!
            }
        }
        System.out.println("• Abordagem Correta (Filtro no ON): Retornou " + rightFilterCount + " linhas. (TODOS os clientes preservados!)\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: A ARMADILHA DO COUNT(*) vs COUNT(coluna)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. A ARMADILHA DO COUNT(*) vs COUNT(order_id) EM OUTER JOINS ---");
        System.out.println("+-------------+----------------------+--------------------+--------------------+");
        System.out.println("| customer_id | first_name           | COUNT(order_id)    | COUNT(*) (ERRADO!) |");
        System.out.println("+-------------+----------------------+--------------------+--------------------+");

        for (Customer c : customers) {
            int countOrderId = 0;
            int countStar = 0;

            boolean hasAnyOrder = false;
            for (Order o : orders) {
                if (c.id == o.customerId) {
                    countOrderId++;
                    countStar++;
                    hasAnyOrder = true;
                }
            }
            if (!hasAnyOrder) {
                // A linha existe no resultado do LEFT JOIN (c com order=null)
                countStar++; // O asterisco CONTA A LINHA COM NULL!
                // countOrderId continua 0 porque order_id é NULL!
            }

            System.out.printf("| %-11d | %-20s | %-18d | %-18d |\n",
                    c.id, c.name, countOrderId, countStar);
        }
        System.out.println("+-------------+----------------------+--------------------+--------------------+");
        System.out.println("🚨 Veja os clientes 1, 3, 4 e 9: COUNT(*) diz que eles têm 1 pedido! MENTIRA!");
        System.out.println("✅ COUNT(order_id) ignora os valores NULL e diz corretamente que eles têm 0 pedidos!\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO: CONTADORES EM LEFT JOIN DEVEM SEMPRE USAR A COLUNA!");
        System.out.println("=========================================================================================");
    }
}
