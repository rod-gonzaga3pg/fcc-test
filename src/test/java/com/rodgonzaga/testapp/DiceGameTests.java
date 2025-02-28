package com.rodgonzaga.testapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DiceGameTests {

    @Test
    public void testDiceInitialization() {
        DiceGame diceGame = new DiceGame();
        diceGame.play();
        assertTrue(diceGame.getDice().size() == diceGame.numDice);
    }


}
