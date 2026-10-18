package com.mickaelgeronimo.mastery.aggregate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Aggregation Functions, DTO Projections, and Null-Safety Tests")
class AggregatePitfallsTest {

    @Test
    @DisplayName("Should correctly calculate revenue metrics projection DTO")
    void testRevenueMetricsProjection() {
        List<AggregateAdvancedPitfallsAndSpringDemo.Invoice> invoices =
            AggregateAdvancedPitfallsAndSpringDemo.getInvoices();

        assertThat(invoices).hasSize(17);

        BigDecimal totalSum = invoices.stream()
            .map(AggregateAdvancedPitfallsAndSpringDemo.Invoice::invoiceTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal average = totalSum.divide(BigDecimal.valueOf(invoices.size()), 2, RoundingMode.HALF_UP);

        BigDecimal min = invoices.stream()
            .map(AggregateAdvancedPitfallsAndSpringDemo.Invoice::invoiceTotal)
            .min(BigDecimal::compareTo)
            .orElse(BigDecimal.ZERO);

        BigDecimal max = invoices.stream()
            .map(AggregateAdvancedPitfallsAndSpringDemo.Invoice::invoiceTotal)
            .max(BigDecimal::compareTo)
            .orElse(BigDecimal.ZERO);

        AggregateAdvancedPitfallsAndSpringDemo.RevenueMetricsDTO dto =
            new AggregateAdvancedPitfallsAndSpringDemo.RevenueMetricsDTO(
                invoices.size(), min, max, average, totalSum
            );

        assertThat(dto.totalInvoices()).isEqualTo(17);
        assertThat(dto.minInvoice()).isEqualByComparingTo("101.79");
        assertThat(dto.maxInvoice()).isEqualByComparingTo("189.12");
        assertThat(dto.sumInvoice()).isGreaterThan(new BigDecimal("2000.00"));
        assertThat(dto.avgInvoice()).isGreaterThan(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Should simulate COALESCE(SUM, 0) defensively returning BigDecimal.ZERO on empty data")
    void testCoalesceSumDefenseAgainstNPE() {
        List<AggregateAdvancedPitfallsAndSpringDemo.Invoice> invoices =
            AggregateAdvancedPitfallsAndSpringDemo.getInvoices();

        // Filtering for dates that do not exist (future year 2050)
        List<AggregateAdvancedPitfallsAndSpringDemo.Invoice> emptySet = invoices.stream()
            .filter(i -> i.invoiceDate().isAfter(LocalDate.of(2050, 1, 1)))
            .toList();

        BigDecimal safeSum = emptySet.stream()
            .map(AggregateAdvancedPitfallsAndSpringDemo.Invoice::invoiceTotal)
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);

        assertThat(emptySet).isEmpty();
        assertThat(safeSum).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Should accurately isolate records above the calculated average")
    void testFilterAboveAverage() {
        List<AggregateAdvancedPitfallsAndSpringDemo.Invoice> invoices =
            AggregateAdvancedPitfallsAndSpringDemo.getInvoices();

        BigDecimal totalSum = invoices.stream()
            .map(AggregateAdvancedPitfallsAndSpringDemo.Invoice::invoiceTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal average = totalSum.divide(BigDecimal.valueOf(invoices.size()), 2, RoundingMode.HALF_UP);

        List<AggregateAdvancedPitfallsAndSpringDemo.Invoice> aboveAvg = invoices.stream()
            .filter(i -> i.invoiceTotal().compareTo(average) > 0)
            .toList();

        assertThat(aboveAvg).isNotEmpty();
        assertThat(aboveAvg).allMatch(i -> i.invoiceTotal().compareTo(average) > 0);
    }
}
