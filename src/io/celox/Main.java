/*
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

package io.celox;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int AMOUNT_OF_DICTIONARIES = 96;
    private static final int WORDS_IN_DICT = 500;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.println(generatePassword(30));
        }
        System.out.println("executed in " + (System.currentTimeMillis() - start) + "ms.");
    }


    private static String generatePassword(int length) {
        BufferedReader r;
        String pw = "";
        while (pw.length() < length) {

            int dict = getRndInt(AMOUNT_OF_DICTIONARIES, 0);
            try {
                FileInputStream fis =
                    new FileInputStream("assets/dict_" + String.format("%02d", dict) + ".txt");

                r = new BufferedReader(new InputStreamReader(fis));
                List<String> words = new ArrayList<>();
                int i;
                for (i = 0; i < WORDS_IN_DICT; i++) {
                    words.add(r.readLine());
                }
                String part = getWord(words);

                pw = addPart(length, pw, words, part);
                if (pw.length() > length) {
                    pw = pw.substring(0, length);
                }

                fis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return pw;
    }

    private static String addPart(int length, String pw, List<String> words, String part) {
        while (part.length() >= length || part.length() >= 7) {
            part = getWord(words);
        }
        pw += part + generateSpecialChar();
        return pw;
    }

    private static String generateSpecialChar() {
        String s = "6;<2=>?3{9}[8]7-_*#4,%$§5/():!.'+01";
        return String.valueOf(s.charAt(getRndInt(s.length(), 0)));
    }

    private static String getWord(List<String> words) {
        return words.get(getRndInt(WORDS_IN_DICT, 0));
    }

    private static int getRndInt(int amount, int offset) {
        return new Random().nextInt(amount) + offset;
    }

}