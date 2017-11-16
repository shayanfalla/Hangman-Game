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
package server.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class fileReader {

    private static final String FILENAME = "C:\\Users\\Shayan\\Documents\\NetBeansProjects\\Hangman-Game\\src\\server\\model\\words.txt";
    
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