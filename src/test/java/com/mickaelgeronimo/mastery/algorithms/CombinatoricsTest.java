package com.mickaelgeronimo.mastery.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Combinatorics & Algorithmic Problem Solving Tests")
class CombinatoricsTest {

    @Test
    @DisplayName("Should correctly calculate board member election permutations excluding couples (ENEM 2025)")
    void testEnem2025CombinatoricsCalculation() {
        int totalCouples = 10;
        int totalPersons = totalCouples * 2; // 20

        // President can be any of the 20 persons
        int optionsPresident = totalPersons; // 20
        // Secretary cannot be the President or the President's spouse (excl 2)
        int optionsSecretary = totalPersons - 2; // 18
        // Treasurer cannot be President, Secretary, or their respective spouses (excl 4)
        int optionsTreasurer = totalPersons - 4; // 16

        int totalBoards = optionsPresident * optionsSecretary * optionsTreasurer;

        assertThat(totalPersons).isEqualTo(20);
        assertThat(optionsPresident).isEqualTo(20);
        assertThat(optionsSecretary).isEqualTo(18);
        assertThat(optionsTreasurer).isEqualTo(16);
        assertThat(totalBoards).isEqualTo(5760);
    }

    @Test
    @DisplayName("Should verify minimum threshold constraints for committee formation")
    void testCommitteeConstraints() {
        int minimalCouples = 3;
        int minimalPersons = minimalCouples * 2; // 6
        int optionsP = minimalPersons; // 6
        int optionsS = minimalPersons - 2; // 4
        int optionsT = minimalPersons - 4; // 2

        int boards = optionsP * optionsS * optionsT;
        assertThat(boards).isEqualTo(48);
    }
}
