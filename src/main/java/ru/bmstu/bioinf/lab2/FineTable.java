package ru.bmstu.bioinf.lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FineTable {
    private int[][] table;
    private char[] keys;

    public FineTable(String pathToTable) {
        try (Scanner scanner = new Scanner(new File(pathToTable))) {
            String[] columns = scanner.nextLine().trim().split("\\s+");
            this.keys = new char[columns.length];
            this.table = new int[columns.length][columns.length];

            for (int i = 0; i < columns.length; i++) {
                this.keys[i] = columns[i].charAt(0);
            }

            int lineCount = 0;
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().trim().split("\\s+");
                for (int i = 0; i < line.length; i++) {
                    if (i != 0) {
                        table[lineCount][i - 1] = Integer.parseInt(line[i]);
                    }
                }

                lineCount++;
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File not found: " + pathToTable, e);
        }
    }

    public int get(int i, int j) {
        return table[i][j];
    }

    public int get(char x, char y) {
        return table[index(x)][index(y)];
    }

    private int index(char key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == key) {
                return i;
            }
        }

        throw new IllegalArgumentException("No row: " + key);
    }
}
