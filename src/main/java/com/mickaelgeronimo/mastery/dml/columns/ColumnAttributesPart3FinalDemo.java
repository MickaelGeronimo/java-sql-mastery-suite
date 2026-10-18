package com.mickaelgeronimo.mastery.dml.columns;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

/**
 * ==============================================================================
 * CAPÍTULO 30 (PARTE 3 FINAL): DEMONSTRAÇÃO PRÁTICA EM JAVA 17
 * Tema: Colunas Geradas (G / Virtual / Stored), Restrições CHECK,
 *       Auditoria Temporal (TIMESTAMP) e Inspeção do information_schema
 * ==============================================================================
 */
public class ColumnAttributesPart3FinalDemo {

    // 1. Simulação de Coluna Gerada (G - Generated Column / @Formula no Hibernate)
    public record OrderItem(int orderId, int productId, int quantity, BigDecimal unitPrice) {
        // Coluna Gerada Virtual: Não ocupa espaço físico adicional, calculada sob demanda!
        public BigDecimal lineTotal() {
            return unitPrice.multiply(BigDecimal.valueOf(quantity));
        }

        @Override
        public String toString() {
            return String.format("| %8d | %10d | %8d | %10s | %12s |",
                    orderId, productId, quantity, unitPrice, lineTotal());
        }
    }

    // 2. Simulação de Entidade com CHECK Constraint e Auditoria Temporal (TIMESTAMP)
    public static class CustomerAccount {
        private final int id;
        private final String name;
        private int points;
        private final Instant createdAt; // TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        private Instant updatedAt;       // TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

        public CustomerAccount(int id, String name, int points) {
            this.id = id;
            this.name = Objects.requireNonNull(name, "Nome não pode ser nulo!");
            
            // Simulação da RESTRIÇÃO CHECK (points >= 0):
            if (points < 0) {
                throw new IllegalStateException("ERROR 3819 (HY000): Check constraint 'check_points_positive' is violated. Pontos não podem ser negativos!");
            }
            this.points = points;
            this.createdAt = Instant.now();
            this.updatedAt = this.createdAt;
        }

        public void addPoints(int earnedPoints) {
            if (this.points + earnedPoints < 0) {
                throw new IllegalStateException("ERROR 3819 (HY000): Check constraint violada! Saldo não pode ficar negativo.");
            }
            this.points += earnedPoints;
            this.updatedAt = Instant.now();
        }

        @Override
        public String toString() {
            return String.format("| %3d | %-12s | %6d | %-24s | %-24s |",
                    id, name, points, createdAt, updatedAt);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("   DEMONSTRAÇÃO JAVA: CAPÍTULO 30 (PARTE 3 FINAL) - COLUNAS GERADAS, CHECK E AUDITORIA   ");
        System.out.println("=========================================================================================\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 1: COLUNA GERADA (G) CALCULADA AUTOMATICAMENTE
        // --------------------------------------------------------------------------------------
        System.out.println("--- 1. COLUNA GERADA (GENERATED COLUMN 'G'): CÁLCULO AUTOMÁTICO DE VALOR ---");
        System.out.println("+----------+------------+----------+------------+--------------+");
        System.out.println("| order_id | product_id | quantity | unit_price | line_total   |");
        System.out.println("+----------+------------+----------+------------+--------------+");

        OrderItem item1 = new OrderItem(1, 4, 4, new BigDecimal("1.66"));
        OrderItem item2 = new OrderItem(2, 1, 2, new BigDecimal("9.10"));
        System.out.println(item1);
        System.out.println(item2);
        System.out.println("+----------+------------+----------+------------+--------------+\n");

        // --------------------------------------------------------------------------------------
        // CENÁRIO 2: VALIDAÇÃO DE RESTRIÇÃO CHECK (CHECK CONSTRAINT NO MYSQL 8.0.16+)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 2. TESTE DE RESTRIÇÃO CHECK: BLOQUEIO DE PONTOS NEGATIVOS ---");
        CustomerAccount account = new CustomerAccount(1, "Mickael", 100);
        System.out.println("Conta criada com sucesso: " + account);

        try {
            System.out.println("Tentando criar conta com -50 pontos (violando CHECK constraint)...");
            new CustomerAccount(2, "Invasor", -50);
        } catch (IllegalStateException ex) {
            System.out.println("   [BLOQUEIO CONFIRMADO] " + ex.getMessage());
        }
        System.out.println();

        // --------------------------------------------------------------------------------------
        // CENÁRIO 3: INSPEÇÃO VIA information_schema.COLUMNS (COMO OS ORMs ENXERGAM)
        // --------------------------------------------------------------------------------------
        System.out.println("--- 3. CATÁLOGO DO SISTEMA: information_schema.COLUMNS (sql_store.customers) ---");
        System.out.println("+------------------+-------------+---------+-------------+----------------+----------------+");
        System.out.println("| column_name      | data_type   | max_len | is_nullable | column_default | extra          |");
        System.out.println("+------------------+-------------+---------+-------------+----------------+----------------+");
        System.out.println("| customer_id      | int         | NULL    | NO          | NULL           | auto_increment |");
        System.out.println("| first_name       | varchar     | 50      | NO          | NULL           |                |");
        System.out.println("| last_name        | varchar     | 50      | NO          | NULL           |                |");
        System.out.println("| birth_date       | date        | NULL    | YES         | NULL           |                |");
        System.out.println("| phone            | varchar     | 50      | YES         | NULL           |                |");
        System.out.println("| address          | varchar     | 50      | NO          | NULL           |                |");
        System.out.println("| city             | varchar     | 50      | NO          | NULL           |                |");
        System.out.println("| state            | char        | 2       | NO          | NULL           |                |");
        System.out.println("| points           | int         | NULL    | NO          | 0              |                |");
        System.out.println("+------------------+-------------+---------+-------------+----------------+----------------+");
        System.out.println("\nCAPÍTULO 30 100% CONCLUÍDO! PRONTO PARA O COMANDO INSERT NO CAPÍTULO 31!");
    }
}
