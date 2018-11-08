package ru.bmstu.bioinf.lab2;

import ru.bmstu.bioinf.Common.Sequence;
import ru.bmstu.bioinf.Common.TableElement;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static ru.bmstu.bioinf.lab2.FineTable.GAP;

public class Table {
    private TableElement[][] mTable;
    private TableElement[][] iTable;
    private TableElement[][] dTable;
    private TableElement[][] resTable;
    private FineTable fineTable;

    private final int MAX;

    private Sequence sequenceLeft;
    private Sequence sequenceUp;

    public Table(Sequence sequenceUp, Sequence sequenceLeft, FineTable fineTable) {
        this.sequenceLeft = sequenceLeft;
        this.sequenceUp = sequenceUp;

        this.fineTable = fineTable;

        mTable = new TableElement[sequenceLeft.size() + 1][sequenceUp.size() + 1];
        iTable = new TableElement[sequenceLeft.size() + 1][sequenceUp.size() + 1];
        dTable = new TableElement[sequenceLeft.size() + 1][sequenceUp.size() + 1];
        resTable = new TableElement[sequenceLeft.size() + 1][sequenceUp.size() + 1];

        MAX = 2 * fineTable.getOpenE() + (sequenceLeft.size() + sequenceUp.size() + 2) * fineTable.getExtendE() + 1;

        fillTable();
    }

    public void printResult() {
        System.out.println("score: " + resTable[sequenceLeft.size()][sequenceUp.size()].getScore());
        resTable[sequenceLeft.size()][sequenceUp.size()].printSequences(System.out);
        System.out.println();
    }

    public void printResult(File file) {
        try (PrintStream writer = new PrintStream(file)) {
            writer.println("score: " + resTable[sequenceLeft.size()][sequenceUp.size()].getScore());
            resTable[sequenceLeft.size()][sequenceUp.size()].printSequences(writer);
            writer.println();
        } catch (IOException e) {
            throw new IllegalStateException("Can't write in file", e);
        }
    }

    private void fillTable() {
        initMTable();
        initITable();
        initDTable();

        for (int i = 1; i < sequenceLeft.size() + 1; i++) {
            for (int j = 1; j < sequenceUp.size() + 1; j++) {
                fillMTableElement(i, j);
                fillITableElement(i, j);
                fillDTableElement(i, j);
                fillResTable(i, j);
            }
        }
    }

    private void fillMTableElement(int i, int j) {
        int fine = fineTable.get(sequenceLeft.get(i - 1), sequenceUp.get(j - 1));

        TableElement mElement = mTable[i - 1][j - 1].addFine(fine);
        TableElement iElement = iTable[i - 1][j - 1].addFine(fine);
        TableElement dElement = dTable[i - 1][j - 1].addFine(fine);

        TableElement max = TableElement.max(mElement, iElement, dElement);

        char elementLeft = sequenceLeft.get(i - 1);
        char elementUp = sequenceUp.get(j - 1);

        mTable[i][j] = new TableElement(elementUp, elementLeft, i, j, max.getScore(), max);
    }

    private void fillITableElement(int i, int j) {
        TableElement mElement = mTable[i][j - 1].addFine(fineTable.getOpenE());
        TableElement iElement = iTable[i][j - 1].addFine(fineTable.getExtendE());
        TableElement dElement = dTable[i][j - 1].addFine(fineTable.getOpenE());

        TableElement max = TableElement.max(mElement, iElement, dElement);

        char elementUp = sequenceUp.get(j - 1);

        iTable[i][j] = new TableElement(elementUp, GAP, i, j, max.getScore(), max);
    }

    private void fillDTableElement(int i, int j) {
        TableElement mElement = mTable[i - 1][j].addFine(fineTable.getOpenE());
        TableElement iElement = iTable[i - 1][j].addFine(fineTable.getOpenE());
        TableElement dElement = dTable[i - 1][j].addFine(fineTable.getExtendE());

        TableElement max = TableElement.max(mElement, iElement, dElement);

        char elementLeft = sequenceLeft.get(i - 1);

        dTable[i][j] = new TableElement(GAP, elementLeft, i, j, max.getScore(), max);
    }

    private void fillResTable(int i, int j) {
        TableElement max = TableElement.max(mTable[i][j], iTable[i][j], dTable[i][j]);
        resTable[i][j] = max;
    }

    private void initMTable() {
        mTable[0][0] = new TableElement(0, 0, 0);
        for (int i = 1; i < sequenceLeft.size() + 1; i++) {
            mTable[i][0] = new TableElement(GAP, sequenceLeft.get(i - 1), i, 0, MAX, mTable[i - 1][0]);
        }
        for (int j = 1; j < sequenceUp.size() + 1; j++) {
            mTable[0][j] = new TableElement(GAP, sequenceUp.get(j - 1), 0, j, MAX, mTable[0][j - 1]);
        }
    }

    private void initITable() {
        iTable[0][0] = new TableElement(MAX, 0 ,0);
        for (int i = 1; i < sequenceLeft.size() + 1; i++) {
            iTable[i][0] = new TableElement(GAP, sequenceLeft.get(i - 1), i, 0, fineTable.getOpenE() + fineTable.getExtendE() * (i - 1), iTable[i - 1][0]);
        }
        for (int j = 1; j < sequenceUp.size() + 1; j++) {
            iTable[0][j] = new TableElement(GAP, sequenceUp.get(j - 1), 0, j, MAX, iTable[0][j - 1]);
        }
    }

    private void initDTable() {
        dTable[0][0] = new TableElement(MAX, 0, 0);
        for (int i = 1; i < sequenceLeft.size() + 1; i++) {
            dTable[i][0] = new TableElement(GAP, sequenceLeft.get(i - 1), i, 0, MAX, dTable[i - 1][0]);
        }
        for (int j = 1; j < sequenceUp.size() + 1; j++) {
            dTable[0][j] = new TableElement(GAP, sequenceUp.get(j - 1), j, 0, fineTable.getOpenE() + fineTable.getExtendE() * (j - 1), dTable[0][j - 1]);
        }
    }
}
