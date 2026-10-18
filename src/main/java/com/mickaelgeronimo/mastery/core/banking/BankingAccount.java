package com.mickaelgeronimo.mastery.core.banking;

/**
 * Motor de domínio e lógica de negócios para a conta bancária do Capítulo 29 (Banking Program).
 * <p>
 * Implementa métodos desacoplados e puros para:
 * <ul>
 *   <li>Consulta e formatação de saldo (showBalance)</li>
 *   <li>Depósito com validação de valor estritamente positivo (deposit)</li>
 *   <li>Saque com validação dupla: valor positivo e saldo suficiente (withdraw)</li>
 * </ul>
 *
 * @author Mickael Geronimo
 * @version 1.0.0
 */
public class BankingAccount {

    private double balance;

    public BankingAccount() {
        this.balance = 0.0;
    }

    public BankingAccount(double initialBalance) {
        if (initialBalance < 0.0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo.");
        }
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Formata o saldo atual dentro da moldura oficial do terminal Bro Code.
     *
     * @return texto formatado com moldura e valor com duas casas decimais
     */
    public String formatBalanceDisplay() {
        return String.format(java.util.Locale.US, "****************%n"
                + "$%.2f%n"
                + "****************", balance);
    }

    /**
     * Executa depósito na conta bancária.
     *
     * @param amount quantia a depositar
     * @return quantia aceita depositada (0 se inválida)
     */
    public double deposit(double amount) {
        if (amount <= 0) {
            return 0.0;
        }
        this.balance += amount;
        return amount;
    }

    /**
     * Executa saque na conta bancária.
     *
     * @param amount quantia a sacar
     * @return quantia sacada (0 se inválida ou fundos insuficientes)
     */
    public double withdraw(double amount) {
        if (amount <= 0) {
            return 0.0; // Valor não positivo
        }
        if (amount > this.balance) {
            return 0.0; // Fundos insuficientes
        }
        this.balance -= amount;
        return amount;
    }
}
