package com.mickaelgeronimo.mastery.dml;

import com.mickaelgeronimo.mastery.dml.update.UpdatingMultipleRowsPart1Demo;
import com.mickaelgeronimo.mastery.dml.update.UpdatingSingleRowPart2AdvancedDemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("DML Concurrency, Optimistic Locking, and Safe Updates Tests")
class OptimisticLockingAndDmlTest {

    @Test
    @DisplayName("Should increment version on successful optimistic update")
    void testOptimisticLockingSuccess() {
        UpdatingSingleRowPart2AdvancedDemo.InvoiceEntity invoice =
            new UpdatingSingleRowPart2AdvancedDemo.InvoiceEntity(1, "INV-2026-001", BigDecimal.ZERO, 1);

        boolean success = invoice.updateOptimistic(new BigDecimal("150.00"), 1);

        assertThat(success).isTrue();
        assertThat(invoice.getVersion()).isEqualTo(2);
        assertThat(invoice.getPaymentTotal()).isEqualByComparingTo("150.00");
    }

    @Test
    @DisplayName("Should detect collision and reject update when version mismatch occurs (Lost Update prevention)")
    void testOptimisticLockingCollision() {
        UpdatingSingleRowPart2AdvancedDemo.InvoiceEntity invoice =
            new UpdatingSingleRowPart2AdvancedDemo.InvoiceEntity(1, "INV-2026-001", BigDecimal.ZERO, 1);

        // First worker updates successfully, bumping version to 2
        boolean firstUpdate = invoice.updateOptimistic(new BigDecimal("100.00"), 1);
        assertThat(firstUpdate).isTrue();
        assertThat(invoice.getVersion()).isEqualTo(2);

        // Concurrent worker tries to update with stale version 1
        boolean staleUpdate = invoice.updateOptimistic(new BigDecimal("200.00"), 1);
        assertThat(staleUpdate).isFalse();
        // Payment total remains the value from the winning transaction
        assertThat(invoice.getPaymentTotal()).isEqualByComparingTo("100.00");
        assertThat(invoice.getVersion()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should throw SafeUpdateException when updating without primary key under safe mode")
    void testSafeUpdatesModeProtection() {
        UpdatingMultipleRowsPart1Demo.InvoiceDatabase db = new UpdatingMultipleRowsPart1Demo.InvoiceDatabase();
        db.addInvoice(new UpdatingMultipleRowsPart1Demo.Invoice(
            1, "INV-101", 3, new BigDecimal("500.00"), BigDecimal.ZERO,
            LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 15), null
        ));

        // Default safeUpdates is true
        assertThatThrownBy(() -> db.updateByClientId(3, new BigDecimal("500.00")))
            .isInstanceOf(UpdatingMultipleRowsPart1Demo.SafeUpdateException.class)
            .hasMessageContaining("Error Code: 1175");
    }

    @Test
    @DisplayName("Should successfully update records when safe mode is toggled off")
    void testSafeUpdatesDisabledExecution() {
        UpdatingMultipleRowsPart1Demo.InvoiceDatabase db = new UpdatingMultipleRowsPart1Demo.InvoiceDatabase();
        db.setSafeUpdates(false);
        db.addInvoice(new UpdatingMultipleRowsPart1Demo.Invoice(
            1, "INV-101", 3, new BigDecimal("500.00"), BigDecimal.ZERO,
            LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 15), null
        ));
        db.addInvoice(new UpdatingMultipleRowsPart1Demo.Invoice(
            2, "INV-102", 3, new BigDecimal("300.00"), BigDecimal.ZERO,
            LocalDate.of(2026, 1, 2), LocalDate.of(2026, 1, 16), null
        ));

        int rows = db.updateByClientId(3, new BigDecimal("100.00"));
        assertThat(rows).isEqualTo(2);

        List<UpdatingMultipleRowsPart1Demo.Invoice> clientInvoices = db.findByClientId(3);
        assertThat(clientInvoices).hasSize(2);
        assertThat(clientInvoices.get(0).getPaymentTotal()).isEqualByComparingTo("100.00");
        assertThat(clientInvoices.get(0).getPaymentDate()).isEqualTo(LocalDate.of(2026, 1, 15));
    }
}
