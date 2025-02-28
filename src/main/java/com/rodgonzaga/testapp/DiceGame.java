package com.rodgonzaga.testapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DiceGame {

    private List<Die> dice;

    private int numDice;

    public DiceGame() {
        this.numDice = 5;
    }

    public DiceGame(int numDice) {
        this.numDice = numDice;
    }

    public int play() {
        initDice();
        int rollSum = 0;
        while(!dice.isEmpty()) {
            rollDice();
            int score = scoreRolls();
            rollSum += score;
        }

        return rollSum;
    }

    private void rollDice() {
        for (Die die : dice) {
            die.doRoll();
        }
    }

    private int scoreRolls() {
        int score = 0;
        boolean hasThrees = checkForThrees();
        if(hasThrees) {
            score = 0;
        } else {
            Die minDie = Collections.min(dice);;
            score = findAndRemoveMinDie();
        }
        return score;
    }

    private int findAndRemoveMinDie() {
        Die minDie = Collections.min(dice);;
        int minRoll = minDie.getCurrentRoll();

        for (Iterator<Die> iter = dice.iterator(); iter.hasNext() ;) {
            Die die = iter.next();
            if(die.getCurrentRoll() == minRoll) {
                iter.remove();
                break; // If there are ties, only remove one die.
            }
        }

        return minRoll;

    }

    private boolean checkForThrees() {
        boolean hasThrees = false;
        for (Iterator<Die> iter = dice.iterator(); iter.hasNext() ;) {
            Die die = iter.next();
            if(die.getCurrentRoll() == 3) {
                hasThrees = true;
                iter.remove();
            }
        }
        return hasThrees;
    }


    private void initDice() {
        dice = new ArrayList<>();
        for (int i = 0; i < numDice; i++) {
            dice.add(new Die());
        }
    }
}
