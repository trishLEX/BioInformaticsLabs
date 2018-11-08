package ru.bmstu.bioinf.Common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Элемент таблицы
 */
public class TableElement implements Comparable<TableElement> {
    /**
     * Соответсвующий символ в верхней и левой последовательностях в таблице
     */
    private char elementUp;
    private char elementLeft;

    /**
     * Координаты в таблице
     */
    private int i;
    private int j;

    private int score;
    private TableElement parent;

    public TableElement(int score, int i, int j) {
        this.score = score;

        this.i = i;
        this.j = j;
    }

    public TableElement(int score, int i, int j, TableElement parent) {
        this(score, i, j);

        this.parent = parent;
    }

    public TableElement(char elementUp, char elementLeft, int score, int i, int j) {
        this(score, i, j);

        this.elementUp = elementUp;
        this.elementLeft = elementLeft;
    }

    public TableElement(char elementUp, char elementLeft, int i, int j, int score, TableElement parent) {
        this(elementUp, elementLeft, score, i, j);

        this.parent = parent;
    }

    public int getScore() {
        return score;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public int compareTo(TableElement o) {
        return Integer.compare(this.score, o.score);
    }

    public static TableElement max(TableElement ... elements) {
        if (elements.length == 0) {
            throw new IllegalStateException("Max of no elements");
        } else if (elements.length == 1) {
            return elements[0];
        } else {
            TableElement max = max(elements[0], elements[1]);
            for (int i = 2; i < elements.length; i++) {
                max = max(max, elements[i]);
            }

            return max;
        }
    }

    public static TableElement max(TableElement a, TableElement b) {
        if (a.compareTo(b) >= 0) {
            return a;
        } else {
            return b;
        }
    }

    public TableElement addFine(int fine) {
        return new TableElement(elementUp, elementLeft, i, j, score + fine, parent);
    }

    @Override
    public String toString() {
        return " " + (score < 0 ? Integer.toString(score) : " " + score);
    }

    /**
     * Восстанваливает последовательности, начиная с данного элемента в {@param out}
     * @param out - поток, в который пишутся последовательности
     */
    public void printSequences(PrintStream out) {
        String first = getUpPath().reverse().toString();
        String second = getLeftPath().reverse().toString();

        if (first.length() > 100 || second.length() > 100) {
            out.println("1st:");
            String[] lines = splitToNChar(first, 100);
            for (int i = 0; i < lines.length; i++) {
                out.println(lines[i]);
            }

            out.println("2nd:");
            lines = splitToNChar(second, 100);
            for (int i = 0; i < lines.length; i++) {
                out.println(lines[i]);
            }
        } else {
            out.println("1st:" + first);
            out.println("2nd:" + second);
        }
    }

    private String[] splitToNChar(String text, int size) {
        List<String> parts = new ArrayList<>();

        int length = text.length();
        for (int i = 0; i < length; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts.toArray(new String[0]);
    }

    private StringBuilder getUpPath() {
        StringBuilder res = new StringBuilder();
        res.append(elementUp);

        if (parent != null) {
            res.append(parent.getUpPath());
        }

        return res;
    }

    private StringBuilder getLeftPath() {
        StringBuilder res = new StringBuilder();
        res.append(elementLeft);

        if (parent != null) {
            res.append(parent.getLeftPath());
        }

        return res;
    }
}
