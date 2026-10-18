package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.banking.BankingAccount;
import com.mickaelgeronimo.mastery.core.banking.BankingProgramCLI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Capítulo 29: Testes Automatizados do Banking Program (ATM)")
class BankingProgramTest {

    @Test
    @DisplayName("Deve inicializar a conta com saldo zero por padrão")
    void shouldInitializeWithZeroBalance() {
        BankingAccount account = new BankingAccount();
        assertThat(account.getBalance()).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Deve inicializar a conta com saldo inicial positivo")
    void shouldInitializeWithCustomBalance() {
        BankingAccount account = new BankingAccount(150.75);
        assertThat(account.getBalance()).isCloseTo(150.75, within(0.001));
    }

    @Test
    @DisplayName("Deve rejeitar saldo inicial negativo com exceção")
    void shouldRejectNegativeInitialBalance() {
        assertThatThrownBy(() -> new BankingAccount(-50.0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Deve realizar depósitos válidos e acumular no saldo")
    void shouldDepositValidAmounts() {
        BankingAccount account = new BankingAccount(100.0);
        double deposited1 = account.deposit(50.25);
        double deposited2 = account.deposit(20.50);

        assertThat(deposited1).isCloseTo(50.25, within(0.001));
        assertThat(deposited2).isCloseTo(20.50, within(0.001));
        assertThat(account.getBalance()).isCloseTo(170.75, within(0.001));
    }

    @Test
    @DisplayName("Deve rejeitar depósitos nulos ou negativos sem alterar o saldo")
    void shouldRejectInvalidDeposits() {
        BankingAccount account = new BankingAccount(100.0);
        double zeroDeposit = account.deposit(0.0);
        double negativeDeposit = account.deposit(-35.0);

        assertThat(zeroDeposit).isEqualTo(0.0);
        assertThat(negativeDeposit).isEqualTo(0.0);
        assertThat(account.getBalance()).isCloseTo(100.0, within(0.001));
    }

    @Test
    @DisplayName("Deve realizar saques válidos e diminuir o saldo")
    void shouldWithdrawValidAmounts() {
        BankingAccount account = new BankingAccount(200.0);
        double withdrawn = account.withdraw(75.50);

        assertThat(withdrawn).isCloseTo(75.50, within(0.001));
        assertThat(account.getBalance()).isCloseTo(124.50, within(0.001));
    }

    @Test
    @DisplayName("Deve rejeitar saques que excedam o saldo disponível (Insufficient Funds)")
    void shouldRejectWithdrawExceedingBalance() {
        BankingAccount account = new BankingAccount(50.0);
        double withdrawn = account.withdraw(100.0);

        assertThat(withdrawn).isEqualTo(0.0);
        assertThat(account.getBalance()).isCloseTo(50.0, within(0.001));
    }

    @Test
    @DisplayName("Deve rejeitar saques negativos ou nulos sem alterar o saldo")
    void shouldRejectNegativeOrZeroWithdrawals() {
        BankingAccount account = new BankingAccount(50.0);
        double zeroWithdraw = account.withdraw(0.0);
        double negativeWithdraw = account.withdraw(-10.0);

        assertThat(zeroWithdraw).isEqualTo(0.0);
        assertThat(negativeWithdraw).isEqualTo(0.0);
        assertThat(account.getBalance()).isCloseTo(50.0, within(0.001));
    }

    @Test
    @DisplayName("Deve formatar o saldo corretamente com moldura e duas casas decimais")
    void shouldFormatBalanceDisplay() {
        BankingAccount account = new BankingAccount(1234.50);
        String display = account.formatBalanceDisplay();

        assertThat(display)
                .contains("****************")
                .contains("$1234.50");
    }

    @Test
    @DisplayName("Deve simular a CLI do caixa eletrônico com depósitos, saques e saída limpa")
    void shouldSimulateCLIWorkflow() {
        // Simulação de entrada:
        // 1 (ver saldo)
        // 2 (depositar 100)
        // 3 (sacar 40)
        // 4 (sair)
        String simulatedInput = "1\n2\n100,0\n3\n40,0\n4\n";
        Scanner mockScanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));

        BankingProgramCLI.setScanner(mockScanner);
        BankingProgramCLI.main(new String[]{});
    }
}
