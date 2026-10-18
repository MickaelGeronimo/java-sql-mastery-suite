package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.methods.OverloadedMethodsMasteryDemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Capítulo 27: Testes Unitários de Sobrecarga de Métodos (Overloaded Methods)")
class OverloadedMethodsMasteryTest {

    @Test
    @DisplayName("Deve somar corretamente números com sobrecargas de 2, 3 e 4 inteiros")
    void shouldAddIntegersWithOverloadedSignatures() {
        assertThat(OverloadedMethodsMasteryDemo.add(1, 2)).isEqualTo(3);
        assertThat(OverloadedMethodsMasteryDemo.add(1, 2, 3)).isEqualTo(6);
        assertThat(OverloadedMethodsMasteryDemo.add(1, 2, 3, 4)).isEqualTo(10);
    }

    @Test
    @DisplayName("Deve somar corretamente doubles na sobrecarga decimal")
    void shouldAddDoubles() {
        assertThat(OverloadedMethodsMasteryDemo.add(2.5, 3.5)).isCloseTo(6.0, within(0.0001));
        assertThat(OverloadedMethodsMasteryDemo.add(10.15, 20.85)).isCloseTo(31.0, within(0.0001));
    }

    @Test
    @DisplayName("Deve assar pizzas com assinaturas sobrecarregadas de ingredientes crescentes")
    void shouldBakePizzasWithOverloadedSignatures() {
        String basic = OverloadedMethodsMasteryDemo.bakePizza("Massa Pan", "Molho de Tomate");
        assertThat(basic)
                .contains("massa 'Massa Pan'")
                .contains("molho 'Molho de Tomate'")
                .doesNotContain("queijo");

        String withCheese = OverloadedMethodsMasteryDemo.bakePizza("Massa Fina", "Pesto", "Mozzarella");
        assertThat(withCheese)
                .contains("massa 'Massa Fina'")
                .contains("molho 'Pesto'")
                .contains("queijo 'Mozzarella'")
                .doesNotContain("cobertura");

        String complete = OverloadedMethodsMasteryDemo.bakePizza("Borda Recheada", "Barbecue", "Gouda", "Calabresa");
        assertThat(complete)
                .contains("massa 'Borda Recheada'")
                .contains("molho 'Barbecue'")
                .contains("queijo 'Gouda'")
                .contains("cobertura 'Calabresa'");
    }

    @Test
    @DisplayName("Deve executar main demo de sobrecargas sem lançar exceções")
    void shouldRunMainWithoutExceptions() {
        OverloadedMethodsMasteryDemo.main(new String[]{});
    }
}
