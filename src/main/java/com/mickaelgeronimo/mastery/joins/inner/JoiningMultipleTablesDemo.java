package com.mickaelgeronimo.mastery.joins.inner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JoiningMultipleTablesDemo
 * 
 * Demonstração prática para iniciantes de como o JOIN de Múltiplas Tabelas
 * (Joining Multiple Tables) funciona no Java ao combinar 3 tabelas
 * (payments + clients + payment_methods) em um único DTO consolidado.
 */
public class JoiningMultipleTablesDemo {

    // 1. Tabela clients
    static class Client {
        int clientId;
        String name;

        Client(int clientId, String name) {
            this.clientId = clientId;
            this.name = name;
        }
    }

    // 2. Tabela payment_methods
    static class PaymentMethod {
        int methodId;
        String name;

        PaymentMethod(int methodId, String name) {
            this.methodId = methodId;
            this.name = name;
        }
    }

    // 3. Tabela payments (Tabela central que conecta tudo!)
    static class Payment {
        int paymentId;
        int clientId;       // FK para clients
        int invoiceId;
        LocalDate date;
        double amount;
        int paymentMethod;  // FK para payment_methods (repare no nome diferente do ID!)

        Payment(int paymentId, int clientId, int invoiceId, LocalDate date, double amount, int paymentMethod) {
            this.paymentId = paymentId;
            this.clientId = clientId;
            this.invoiceId = invoiceId;
            this.date = date;
            this.amount = amount;
            this.paymentMethod = paymentMethod;
        }
    }

    // 4. DTO de Relatório Corporativo (Resultado do Multi-Join)
    static class PaymentReportDTO {
        LocalDate date;
        int invoiceId;
        double amount;
        String clientName;
        String paymentMethodName;

        PaymentReportDTO(LocalDate date, int invoiceId, double amount, String clientName, String paymentMethodName) {
            this.date = date;
            this.invoiceId = invoiceId;
            this.amount = amount;
            this.clientName = clientName;
            this.paymentMethodName = paymentMethodName;
        }

        @Override
        public String toString() {
            return String.format("| %10s | %10d | R$ %7.2f | %-16s | %-16s |",
                    date, invoiceId, amount, clientName, paymentMethodName);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: JUNÇÃO DE 3 TABELAS (payments + clients + payment_methods)");
        System.out.println("=========================================================================================\n");

        // 1. Tabela clients
        Map<Integer, Client> clients = new HashMap<>();
        clients.put(1, new Client(1, "Vinte"));
        clients.put(2, new Client(2, "Topiclounge"));
        clients.put(3, new Client(3, "Yadel"));
        clients.put(5, new Client(5, "Kwambee"));

        // 2. Tabela payment_methods
        Map<Integer, PaymentMethod> methods = new HashMap<>();
        methods.put(1, new PaymentMethod(1, "Credit Card"));
        methods.put(2, new PaymentMethod(2, "Cash"));
        methods.put(3, new PaymentMethod(3, "PayPal"));
        methods.put(4, new PaymentMethod(4, "Wire Transfer"));

        // 3. Tabela payments
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1, 1, 2, LocalDate.of(2019, 1, 11), 10.00, 1));
        payments.add(new Payment(2, 3, 11, LocalDate.of(2019, 1, 15), 158.44, 1));
        payments.add(new Payment(3, 2, 5, LocalDate.of(2019, 1, 26), 169.88, 1));
        payments.add(new Payment(6, 5, 9, LocalDate.of(2019, 7, 7), 100.00, 2));
        payments.add(new Payment(7, 5, 4, LocalDate.of(2019, 7, 9), 10.00, 2));

        System.out.println("Executando a consulta SQL:");
        System.out.println("SELECT p.date, p.invoice_id, p.amount, c.name AS client, pm.name AS payment_method");
        System.out.println("FROM payments p");
        System.out.println("JOIN clients c ON p.client_id = c.client_id");
        System.out.println("JOIN payment_methods pm ON p.payment_method = pm.payment_method_id;\n");

        System.out.println("+------------+------------+-----------+------------------+------------------+");
        System.out.println("| date       | invoice_id | amount    | client           | payment_method   |");
        System.out.println("+------------+------------+-----------+------------------+------------------+");

        List<PaymentReportDTO> report = new ArrayList<>();

        for (Payment p : payments) {
            Client c = clients.get(p.clientId);
            PaymentMethod pm = methods.get(p.paymentMethod);

            if (c != null && pm != null) {
                report.add(new PaymentReportDTO(p.date, p.invoiceId, p.amount, c.name, pm.name));
            }
        }

        report.forEach(System.out::println);
        System.out.println("+------------+------------+-----------+------------------+------------------+");
        System.out.println("Total de pagamentos consolidados com sucesso: " + report.size());
        System.out.println("\nNota Arquitetural:");
        System.out.println("- 'p.client_id' ligou payments a clients.");
        System.out.println("- 'p.payment_method' ligou payments a payment_methods.");
        System.out.println("- Em vez de códigos incompreensíveis, o usuário vê nomes legíveis!\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! MULTI-TABLE JOIN EXECUTADO COM 100% DE SUCESSO!");
        System.out.println("=========================================================================================");
    }
}
