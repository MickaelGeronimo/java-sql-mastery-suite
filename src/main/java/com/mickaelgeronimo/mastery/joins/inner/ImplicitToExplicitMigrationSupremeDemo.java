package com.mickaelgeronimo.mastery.joins.inner;

import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 22 (PARTE 3 - FINAL): DEMONSTRAÇÃO PRÁTICA EM JAVA
 * Tema: O Exercício Oficial de Refatoração de Pagamentos, Simulação de DBA (KILL),
 *       e a Proibição de Produtos Cartesianos no JPA/Hibernate (MultipleBagFetch)
 * ==============================================================================
 */
public class ImplicitToExplicitMigrationSupremeDemo {

    // Entidades do Módulo de Faturamento (sql_invoicing)
    static class Payment {
        int paymentId;
        int clientId;
        int invoiceId;
        String date;
        double amount;
        int paymentMethod;

        public Payment(int paymentId, int clientId, int invoiceId, String date, double amount, int paymentMethod) {
            this.paymentId = paymentId;
            this.clientId = clientId;
            this.invoiceId = invoiceId;
            this.date = date;
            this.amount = amount;
            this.paymentMethod = paymentMethod;
        }
    }

    static class Client {
        int clientId;
        String name;

        public Client(int clientId, String name) {
            this.clientId = clientId;
            this.name = name;
        }
    }

    static class PaymentMethod {
        int id;
        String name;

        public PaymentMethod(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: GRAND FINALE CAPÍTULO 22 - REFATORAÇÃO & MONITORAMENTO DBA");
        System.out.println("=========================================================================================\n");

        // 1. CARREGAMENTO DOS DADOS (sql_invoicing)
        List<Client> clients = Arrays.asList(
            new Client(1, "Vinte"),
            new Client(2, "Myworks"),
            new Client(3, "Topiclounge"),
            new Client(4, "Kwam"),
            new Client(5, "Yadel")
        );

        List<PaymentMethod> methods = Arrays.asList(
            new PaymentMethod(1, "Credit Card"),
            new PaymentMethod(2, "Cash"),
            new PaymentMethod(3, "PayPal"),
            new PaymentMethod(4, "Wire Transfer")
        );

        List<Payment> payments = Arrays.asList(
            new Payment(1, 1, 2, "2019-02-12", 8.18, 1),
            new Payment(2, 1, 1, "2019-01-03", 74.55, 1),
            new Payment(3, 3, 3, "2019-01-11", 0.03, 1),
            new Payment(4, 1, 4, "2019-01-26", 87.44, 1),
            new Payment(5, 4, 5, "2019-01-15", 80.31, 1),
            new Payment(6, 1, 7, "2019-01-15", 40.53, 1),
            new Payment(7, 1, 8, "2019-01-08", 32.77, 1),
            new Payment(8, 5, 10, "2019-01-08", 20.79, 1)
        );

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: O EXERCÍCIO OFICIAL DO MOSH HAMEDANI (3 TABELAS REFATORADAS)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. O EXERCÍCIO OFICIAL DO MOSH: PAGAMENTOS + CLIENTES + FORMAS DE PAGAMENTO ---");
        System.out.println("+------------+------------+----------+--------------------+----------------------+");
        System.out.println("| date       | invoice_id | amount   | client_name        | payment_method       |");
        System.out.println("+------------+------------+----------+--------------------+----------------------+");

        int paymentMatches = 0;
        for (Payment p : payments) {
            for (Client c : clients) {
                for (PaymentMethod pm : methods) {
                    // Condições de amarração (p.clientId == c.clientId AND p.paymentMethod == pm.id)
                    if (p.clientId == c.clientId && p.paymentMethod == pm.id) {
                        System.out.printf("| %-10s | %-10d | $%-7.2f | %-18s | %-20s |\n",
                                p.date, p.invoiceId, p.amount, c.name, pm.name);
                        paymentMatches++;
                    }
                }
            }
        }
        System.out.println("+------------+------------+----------+--------------------+----------------------+");
        System.out.println("Total de pagamentos associados com sucesso: " + paymentMatches + " (EXATAMENTE os 8 pagamentos!)\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: O GUARDIÃO DBA (KILL DE PROCESSO CARTESIANO TRAVADO)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. SIMULAÇÃO DE MONITORAMENTO DBA (DETECTANDO E MATANDO QUERY CARTESIANA) ---");
        System.out.println("• Um usuário júnior executou uma query implícita sem WHERE em produção!");
        System.out.println("• Servidor MySQL: Thread ID #98721 em estado 'Sending data' gerando milhões de linhas...");

        long rowsProcessed = 0;
        long maxSafeThreshold = 100_000; // Limite de segurança de 100 mil iterações
        boolean killed = false;

        for (int i = 1; i <= 1_000_000; i++) {
            rowsProcessed++;
            if (rowsProcessed >= maxSafeThreshold) {
                System.out.println("\n🚨 ALERTA DO DBA (Performance Schema):");
                System.out.println("   Processo #98721 já examinou " + rowsProcessed + " linhas sem filtro!");
                System.out.println("   Ação Automática de Proteção: Executando comando -> KILL 98721;");
                killed = true;
                break;
            }
        }

        if (killed) {
            System.out.println("   ✅ SUCESSO: Processo 98721 abortado com segurança pelo DBA. Servidor salvo da queda!\n");
        }

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: POR QUE O HIBERNATE / JPA PROÍBE PRODUTOS CARTESIANOS MÚLTIPLOS?
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. A REGRA DO JPA / HIBERNATE: MultipleBagFetchException ---");
        System.out.println("• No Spring Boot com JPA, você NUNCA pode fazer fetch em duas coleções ao mesmo tempo:");
        System.out.println("  Exemplo Proibido: SELECT c FROM Customer c JOIN FETCH c.orders JOIN FETCH c.reviews;");
        System.out.println("  Motivo: Isso causaria um PRODUTO CARTESIANO no nível Java gerando réplicas em memória.");
        System.out.println("  Exceção Lançada pelo Hibernate: org.hibernate.loader.MultipleBagFetchException");
        System.out.println("  Solução Recomendada: Usar duas consultas separadas ou java.util.Set em vez de List.");

        System.out.println("\n=========================================================================================");
        System.out.println("   FIM DO CAPÍTULO 22! DOMÍNIO COMPLETO: SINTAXE IMPLÍCITA NUNCA MAIS EM CÓDIGO NOVO!");
        System.out.println("=========================================================================================");
    }
}
