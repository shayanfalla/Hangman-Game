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

    private static final String FILENAME = "C:\\Users\\Shayan\\Documents\\NetBeansProjects\\Hangman-Game\\src\\hangman\\words.txt";

    public static void main(String[] args) {
        hangman hang = new hangman();
        Scanner input = new Scanner(System.in);
        String word; String [] hangmanWord;
            ArrayList<String> hey;
     // do {
            System.out.println("Game starting...");
           word = hang.readFile();
             hey = new ArrayList<String>();
            
            //hangmanWord = new String[word.length()];
            for (int i = 0; i < word.length(); i++) {
              //   hangmanWord[i] = "_";
              hey.add("_");
            }
            System.out.println(word);
            System.out.println(Arrays.toString(hey.toArray()));
      // }
        
    }

    String readFile() {
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
