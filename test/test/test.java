/*
 * Copyright (C) 2017 Shayan Fallahian shayanf@kth.se
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test;

import hangman.hangman;
import java.util.Scanner;

/**
 *
 * @author Shayan Fallahian, shayanf@kth.se
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        hangman hang = new hangman();
        boolean weArePlaying = true;

        while (weArePlaying) {
            System.out.println("Welcome!\n");
            String word = hang.readFile().toLowerCase();
            char[] randomWordToGuess = word.toCharArray();
            int amountOfGuesses = randomWordToGuess.length;
            char[] playerGuess = new char[amountOfGuesses]; // Kommer se ut såhär: _ _ _ _ _ etc
           
            for (int i = 0; i < playerGuess.length; i++) {
                playerGuess[i] = '_';
            }

            boolean wordIsGuessed = false;
            int tries = 0;
            while (!wordIsGuessed && tries != amountOfGuesses) {
                System.out.print("current guesses: ");
                printArray(playerGuess);
                System.out.printf("You have %d tries left.\n", amountOfGuesses - tries);
                System.out.println("Enter a letter");
                char input = scanner.nextLine().charAt(0);
                tries++;

                if (input == '-') {
                    weArePlaying = false;
                    wordIsGuessed = true;
                } else {
                    for (int i = 0; i < randomWordToGuess.length; i++) {
                        if (randomWordToGuess[i] == input) {
                            playerGuess[i] = input;
                        }
                    }
                    if (isWordGuessed(playerGuess)) {
                        wordIsGuessed = true;
                        System.out.println("Congratulations you won!");
                    }
                    if(tries == amountOfGuesses) wordIsGuessed = true;
                }
            }
            if (wordIsGuessed) {
                System.out.println("You ran out of guesses :/");
                System.out.println("Word was: " + word);
            }
            System.out.println("Do you want to play another game? (yes/no)");
            String anothergame = scanner.nextLine();
            if (anothergame.equals("no")) {
                weArePlaying = false;
            }
        }

    }

    public static void printArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static boolean isWordGuessed(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '_') {
                return false;
            }
        }
        return true;
    }
}
