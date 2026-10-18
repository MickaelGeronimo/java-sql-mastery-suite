package com.mickaelgeronimo.mastery.core;

import com.mickaelgeronimo.mastery.core.oop.ArrayOfObjectsDemo;
import com.mickaelgeronimo.mastery.core.oop.GarageFleetManager;
import com.mickaelgeronimo.mastery.core.oop.GarageVehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Capítulo 42: Testes Automatizados de Array de Objetos (Array of Objects)")
class ArrayOfObjectsTest {

    @Test
    @DisplayName("Deve inicializar a frota canônica com 3 veículos e cores de fábrica")
    void shouldInitializeCanonicalFleetWithAnonymousObjects() {
        GarageVehicle[] fleet = GarageFleetManager.createCanonicalFleet();

        assertThat(fleet).hasSize(3);
        assertThat(fleet[0].model).isEqualTo("Mustang");
        assertThat(fleet[0].color).isEqualTo("Red");

        assertThat(fleet[1].model).isEqualTo("Corvette");
        assertThat(fleet[1].color).isEqualTo("Blue");

        assertThat(fleet[2].model).isEqualTo("Charger");
        assertThat(fleet[2].color).isEqualTo("Yellow");
    }

    @Test
    @DisplayName("Deve gerar as mensagens exatas de condução para cada veículo")
    void shouldGenerateAccurateDriveMessages() {
        GarageVehicle[] fleet = GarageFleetManager.createCanonicalFleet();
        List<String> messages = GarageFleetManager.collectDriveMessages(fleet);

        assertThat(messages).containsExactly(
                "You drive the Red Mustang",
                "You drive the Blue Corvette",
                "You drive the Yellow Charger"
        );
    }

    @Test
    @DisplayName("Deve repintar toda a frota em lote para 'black' via laço for-each")
    void shouldRepaintFleetInBatchWithForEach() {
        GarageVehicle[] fleet = GarageFleetManager.createCanonicalFleet();

        // Modificação em lote
        GarageFleetManager.repaintFleet(fleet, "black");

        assertThat(fleet).allMatch(v -> "black".equals(v.color));

        List<String> repaintedMessages = GarageFleetManager.collectDriveMessages(fleet);
        assertThat(repaintedMessages).containsExactly(
                "You drive the black Mustang",
                "You drive the black Corvette",
                "You drive the black Charger"
        );
    }

    @Test
    @DisplayName("Deve comprovar equivalência de iteração entre laço for clássico e for-each")
    void shouldSupportTraditionalForLoopAndForEachEquivalently() {
        GarageVehicle[] fleet = GarageFleetManager.createCanonicalFleet();

        StringBuilder classicOutput = new StringBuilder();
        for (int i = 0; i < fleet.length; i++) {
            classicOutput.append(fleet[i].getDriveMessage()).append("\n");
        }

        StringBuilder enhancedOutput = new StringBuilder();
        for (GarageVehicle vehicle : fleet) {
            enhancedOutput.append(vehicle.getDriveMessage()).append("\n");
        }

        assertThat(classicOutput.toString()).isEqualTo(enhancedOutput.toString());
    }

    @Test
    @DisplayName("Deve proteger operações em lote contra arrays nulos ou elementos vazios")
    void shouldHandleNullArrayOrElementsSafely() {
        GarageVehicle[] fleetWithNulls = new GarageVehicle[]{
                new GarageVehicle("Mustang", "Red"),
                null,
                new GarageVehicle("Charger", "Yellow")
        };

        GarageFleetManager.repaintFleet(fleetWithNulls, "Silver");

        assertThat(fleetWithNulls[0].color).isEqualTo("Silver");
        assertThat(fleetWithNulls[1]).isNull();
        assertThat(fleetWithNulls[2].color).isEqualTo("Silver");

        assertThat(GarageFleetManager.collectDriveMessages(null)).isEmpty();
    }

    @Test
    @DisplayName("Deve rejeitar parâmetros nulos na criação do GarageVehicle")
    void shouldRejectNullArgumentsOnVehicleCreation() {
        assertThatThrownBy(() -> new GarageVehicle(null, "Red"))
                .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new GarageVehicle("Mustang", null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Deve executar a demonstração completa ArrayOfObjectsDemo sem erros")
    void shouldRunArrayOfObjectsDemoSuccessfully() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8));
            ArrayOfObjectsDemo.runDemo();

            String output = outputStream.toString(StandardCharsets.UTF_8);

            assertThat(output)
                    .contains("CAPÍTULO 42: ARRAY DE OBJETOS")
                    .contains("You drive the Red Mustang")
                    .contains("You drive the Blue Corvette")
                    .contains("You drive the Yellow Charger")
                    .contains("You drive the black Mustang")
                    .contains("You drive the black Corvette")
                    .contains("You drive the black Charger");
        } finally {
            System.setOut(originalOut);
        }
    }
}
