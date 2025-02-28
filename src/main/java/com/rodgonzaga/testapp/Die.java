package com.rodgonzaga.testapp;

import java.util.Objects;

public class Die implements Comparable<Die> {

    private final int NUM_SIDES = 6;

    private int currentRoll;

    public int doRoll() {
        currentRoll = (int)(Math.random() * NUM_SIDES +1);
        return currentRoll;
    }

    public int getCurrentRoll() {
        return currentRoll;
    }

    @Override
    public int compareTo(Die o) {
        return Integer.compare(currentRoll, ((Die)o).currentRoll);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Die die = (Die) o;
        return currentRoll == die.currentRoll;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(currentRoll);
    }
}
