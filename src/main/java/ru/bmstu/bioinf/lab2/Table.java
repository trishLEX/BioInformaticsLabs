package ru.bmstu.bioinf.lab2;

import java.io.*;

import static ru.bmstu.bioinf.lab2.FineTable.GAP;

public class Table {
    private TableElement[][] table;
    private FineTable fineTable;

    private Sequence sequenceLeft;
    private Sequence sequenceUp;

    public Table(Sequence sequenceUp, Sequence sequenceLeft, TaskType type) {
        this.sequenceLeft = sequenceLeft;
        this.sequenceUp = sequenceUp;

        fineTable = new FineTable(type);
        table = new TableElement[sequenceLeft.size() + 1][sequenceUp.size() + 1];

        fillTable();
    }

    public Table(Sequence sequenceUp, Sequence sequenceLeft, int gap, TaskType type) {
        this.sequenceLeft = sequenceLeft;
        this.sequenceUp = sequenceUp;

        fineTable = new FineTable(type, gap);
        table = new TableElement[sequenceLeft.size() + 1][sequenceUp.size() + 1];

        fillTable();
    }

    private void fillTable() {
        table[0][0] = new TableElement(0, 0, 0);
        for (int i = 1; i < sequenceLeft.size() + 1; i++) {
            table[i][0] = new TableElement(GAP, sequenceLeft.get(i - 1), i, 0, fineTable.getE() * i, table[i - 1][0]);
        }

        for (int i = 1; i < sequenceUp.size() + 1; i++) {
            table[0][i] = new TableElement(sequenceUp.get(i - 1), GAP, 0, i, fineTable.getE() * i,  table[0][i - 1]);
        }

        for (int i = 1; i < sequenceLeft.size() + 1; i++) {
            for (int j = 1; j < sequenceUp.size() + 1; j++) {
                int diagFine = fineTable.get(sequenceLeft.get(i - 1), sequenceUp.get(j - 1));

                int upFine = fineTable.get(sequenceLeft.get(i - 1), GAP);
                int leftFine = fineTable.get(GAP, sequenceUp.get(j - 1));

                TableElement diag = table[i - 1][j - 1].addFine(diagFine);
                TableElement up = table[i - 1][j].addFine(upFine);
                TableElement left = table[i][j - 1].addFine(leftFine);

                TableElement max = TableElement.max(diag, up, left);

                char elementLeft;
                if (max.getI() == i) {
                    elementLeft = GAP;
                } else {
                    elementLeft = sequenceLeft.get(i - 1);
                }

                char elementUp;
                if (max.getJ() == j) {
                    elementUp = GAP;
                } else {
                    elementUp = sequenceUp.get(j - 1);
                }

                table[i][j] = new TableElement(elementUp, elementLeft, i, j, max.getScore(), max);
            }
        }
    }

    public void printResult() {
        System.out.println("score: " + table[sequenceLeft.size()][sequenceUp.size()].getScore());
        table[sequenceLeft.size()][sequenceUp.size()].printSequences(System.out);
        System.out.println();
    }
    public void printResult(File file) {
        try (PrintStream writer = new PrintStream(file)) {
            writer.println("score: " + table[sequenceLeft.size()][sequenceUp.size()].getScore());
            table[sequenceLeft.size()][sequenceUp.size()].printSequences(writer);
            writer.println();
        } catch (IOException e) {
            throw new IllegalStateException("Can't write in file", e);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("\t ");
        for (int i = 0; i < sequenceUp.size(); i++) {
            res.append(" ").append(sequenceUp.get(i)).append(" ");
        }

        res.append("\n");

        for (int i = 0; i < sequenceLeft.size() + 1; i++) {
            if (i > 0) {
                res.append(sequenceLeft.get(i - 1));
            } else {
                res.append(" ");
            }

            for (int j = 0; j < sequenceUp.size() + 1; j++) {
                res.append(table[i][j]);
            }

            res.append("\n");
        }

        return res.toString();
    }
}
