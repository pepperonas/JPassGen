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
import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    private static final int AMOUNT_OF_DICTIONARIES = 96;

    @Test
    public void testDicts() {
        BufferedReader r;
        for (int dict = 0; dict < AMOUNT_OF_DICTIONARIES; dict++) {
            try {
                r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(
                            "assets/dict_" + String.format("%02d", dict) + ".txt")));

                String line;
                List<String> words = new ArrayList<>();
                while ((line = r.readLine()) != null) {
                    words.add(line);
                }

                System.out.println("'dict__" + dict + ".txt' contains " + words.size() + " words.");
                Assert.assertTrue(words.size() >= 500);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testSpecialCharGen() {
        for (int i = 0; i < 10000; i++) {
            String s = "6;<2=>?3{9}[8]7-_*#4,%$§5/():!.'+01";
            String value = String.valueOf(s.charAt(getRndInt(s.length(), 0)));
            if (i % 20 == 0) {
                System.out.println("");
            }
            System.out.print(value + "\t");
            Assert.assertTrue(value.length() == 1);
        }
    }

    private static int getRndInt(int amount, int offset) {
        return new Random().nextInt(amount) + offset;
    }

}