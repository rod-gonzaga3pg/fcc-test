package com.rodgonzaga.testapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DieTests {
    @Test
    public void test6SidedRoll() {
        testNSidedRoll(6, 10000);
    }

    @Test
    public void test24SidedRoll() {
        testNSidedRoll(24, 10000);
    }

    @Test
    public void test3SidedRoll() {
        testNSidedRoll(3, 10000);
    }

    @Test
    public void test2SidedRoll() {
        testNSidedRoll(2, 10000);
    }

    @Test
    public void test1SidedRoll() {
        testNSidedRoll(1, 10000);
    }

    private void testNSidedRoll(int numSides, int numRolls) {
        Die die = new Die();
        int roll;
        for(int i=0; i < numRolls; i++) {
            roll = die.doRoll();
            assertTrue(roll >=1 && roll <= numSides, numSides + " sided die rolled outside of range: " + roll);
        }
    }
}
