package com.rodgonzaga.testapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class DiceExamApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(DiceExamApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DiceExamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.debug("Launching Dice Game...");

		int numGames = promptNumberOfGames();
		runGameSimulation(5, numGames);
	}

	public void runGameSimulation(int numDice, int numGames) {
		System.out.println("Running " + numGames + " games...");
		Map<Integer, Integer> scoreCounter = new HashMap<>();
		for(int i = 0; i < numGames; i++) {
			int score = new DiceGame(numDice).play();
			if(!scoreCounter.containsKey(score)) {
				scoreCounter.put(score, 1);
			} else {
                scoreCounter.compute(score, (k, currentCount) -> currentCount + 1);
			}
		}

		System.out.printf("Number of simulations was %s using %s dice.%n", numGames, numDice);
		final String scoreCountMsg = "Total %s occurs %s occurred %s times.";
		for(Map.Entry<Integer, Integer> entry : scoreCounter.entrySet()) {
			int score = entry.getKey();
			int scoreCount = entry.getValue();
			float scorePercentage = (float)scoreCount / numGames;
			System.out.printf((scoreCountMsg) + "%n", score, scorePercentage, scoreCount);
		}
	}

	private static void reportSimulationResults(Map<Integer, Integer> scoreCounter) {
	}

	private static int promptNumberOfGames() {
		boolean awaitingInput = true;
		final String invalidInputMessage = "Invalid input. Please enter 1 or more.";
		while(awaitingInput) {
			Scanner userInputScanner = new Scanner(System.in);
			System.out.print("How many games to simulate? ");
			int numGames;
			try {
				numGames = userInputScanner.nextInt();
				if (numGames <= 0) {
					System.out.println(invalidInputMessage);
					continue;
				} else {
					return numGames;
				}
			} catch (InputMismatchException ex) {
				System.out.println(invalidInputMessage);
				continue;
			}
		}
		return 1;
	}
}
