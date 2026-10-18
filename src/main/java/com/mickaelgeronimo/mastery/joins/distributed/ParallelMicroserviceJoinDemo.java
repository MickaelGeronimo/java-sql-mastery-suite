package com.mickaelgeronimo.mastery.joins.distributed;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * ParallelMicroserviceJoinDemo
 * 
 * Demonstração prática para iniciantes de:
 * Como a arquitetura moderna de microsserviços em Java (Spring Boot) realiza
 * a "Junção de Múltiplas Tabelas" quando os dados residem em microsserviços
 * e servidores físicos separados, utilizando chamadas paralelas assíncronas
 * (CompletableFuture) e consolidando em um DTO final (Padrão BFF - Backend for Frontend).
 */
public class ParallelMicroserviceJoinDemo {

    // Record consolidado (DTO do Relatório 360°)
    record ConsolidatedOrderReport(
            int orderId,
            String customerName,
            String customerCity,
            String productName,
            double totalAmount,
            String status,
            String shipperName
    ) {
        @Override
        public String toString() {
            return String.format(
                    "| Pedido #%-4d | Cliente: %-16s (%-11s) | Produto: %-18s | Total: R$ %6.2f | Status: %-9s | Envio: %-8s |",
                    orderId, customerName, customerCity, productName, totalAmount, status, shipperName);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: JUNÇÃO DE MÚLTIPLOS SERVIÇOS EM PARALELO (COMPLETABLEFUTURE)");
        System.out.println("=========================================================================================\n");

        int targetOrderId = 1001;
        System.out.println("Iniciando a busca paralela para consolidar o Pedido #" + targetOrderId + "...\n");

        long start = System.currentTimeMillis();

        // 1. Chamada assíncrona ao Serviço de Vendas (sql_store.orders)
        CompletableFuture<String> orderService = CompletableFuture.supplyAsync(() -> {
            simulateNetworkDelay(120);
            return "Processado"; // Status do pedido
        });

        // 2. Chamada assíncrona ao Serviço de Clientes (sql_store.customers)
        CompletableFuture<String[]> customerService = CompletableFuture.supplyAsync(() -> {
            simulateNetworkDelay(150);
            return new String[]{"Mickael Geronimo", "Hampton"}; // Nome e Cidade
        });

        // 3. Chamada assíncrona ao Serviço de Estoque (sql_inventory.products)
        CompletableFuture<String> productService = CompletableFuture.supplyAsync(() -> {
            simulateNetworkDelay(110);
            return "Foam Dinner Plate"; // Nome do produto
        });

        // 4. Chamada assíncrona ao Serviço de Logística (sql_store.shippers)
        CompletableFuture<String> shipperService = CompletableFuture.supplyAsync(() -> {
            simulateNetworkDelay(140);
            return "FedEx Express"; // Transportadora
        });

        // Barreira de sincronização: Aguarda todos os 4 serviços responderem em paralelo!
        CompletableFuture.allOf(orderService, customerService, productService, shipperService).join();

        // Montagem do DTO Consolidado (O "JOIN" em Memória)
        String status = orderService.get();
        String[] customerInfo = customerService.get();
        String product = productService.get();
        String shipper = shipperService.get();

        ConsolidatedOrderReport report = new ConsolidatedOrderReport(
                targetOrderId,
                customerInfo[0],
                customerInfo[1],
                product,
                182.50,
                status,
                shipper
        );

        long end = System.currentTimeMillis();

        System.out.println("--- RELATÓRIO EXECUTIVO CONSOLIDADO (MULTI-JOIN EM MEMÓRIA) ---");
        System.out.println(report);
        System.out.println("\nTempo total de execução paralela: " + (end - start) + " ms.");
        System.out.println("Nota de Performance:");
        System.out.println("Se as 4 consultas fossem sequenciais (uma após a outra), demoraria ~520 ms.");
        System.out.println("Com execução paralela multithread, terminou no tempo da requisição mais lenta (~150 ms)!\n");

        System.out.println("=========================================================================================");
        System.out.println("   CONCLUÍDO COM SUCESSO! O MULTI-JOIN EM MEMÓRIA É O PADRÃO DE MICROSSERVIÇOS!");
        System.out.println("=========================================================================================");
    }

    private static void simulateNetworkDelay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
