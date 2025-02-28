package com.rodgonzaga.testapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DiceGame {

    private List<Die> dice;

    int numDice;

    public DiceGame() {
        this.numDice = 5;
        initDice();
    }

    public DiceGame(int numDice) {
        this.numDice = numDice;
        initDice();
    }

    public int play() {
        int rollSum = 0;
        while(!dice.isEmpty()) {
            rollDice();
            int score = scoreRolls();
            rollSum += score;
        }

        return rollSum;
    }

    void rollDice() {
        for (Die die : dice) {
            die.doRoll();
        }
    }

    int scoreRolls() {
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

    int findAndRemoveMinDie() {
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

    boolean checkForThrees() {
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


    void initDice() {
        dice = new ArrayList<>();
        for (int i = 0; i < numDice; i++) {
            dice.add(new Die());
        }
    }

    List<Die> getDice() {
        return dice;
    }
}
