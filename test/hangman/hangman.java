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
package hangman;

import java.io.*;
import java.util.*;

public class hangman {

    private static final String FILENAME = "C:\\Users\\Shayan\\Documents\\NetBeansProjects\\Hangman-Game\\test\\hangman\\words.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        hangman hang = new hangman();
        boolean playing = true;

        while (playing) {
            System.out.println("Welcome!\n");
            String word = hang.readFile().toLowerCase();
            char[] guessWord = word.toCharArray();
            int totalTries = guessWord.length + 3;
            char[] playerGuess = new char[totalTries]; // Kommer se ut såhär: _ _ _ _ _ etc

            for (int i = 0; i < playerGuess.length; i++) {
                playerGuess[i] = '_';
            }

            boolean isWordGuessed = false;
            int tries = 0;
            while (!isWordGuessed && tries != totalTries) {
                System.out.print("current guesses: ");
                printArray(playerGuess);
                System.out.printf("You have %d tries left.\n", totalTries - tries);
                System.out.println("Enter a letter");
                char input = scanner.nextLine().charAt(0);
                tries++;

                if (input == '-') {
                    playing = false;
                    isWordGuessed = true;
                } else {
                    for (int i = 0; i < guessWord.length; i++) {
                        if (guessWord[i] == input) {
                            playerGuess[i] = input;
                        }
                    }
                    if (isWordGuessed(playerGuess)) {
                        isWordGuessed = true;
                        System.out.println("Congratulations you won!");
                    }
                    if (tries == totalTries) {
                        isWordGuessed = true;
                    }
                }
            }
            if (isWordGuessed) {
                System.out.println("You ran out of guesses :/");
                System.out.println("Word was: " + word);
            }
            System.out.println("Do you want to play another game? (yes/no)");
            String anothergame = scanner.nextLine();
            if (anothergame.equals("no")) {
                playing = false;
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

    public String readFile() {
        BufferedReader br = null;
        FileReader fr = null;
        ArrayList<String> words = new ArrayList<String>();

        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words.get((int) (Math.random() * words.size()));
    }

}
