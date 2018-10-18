package ru.bmstu.bioinf.lab2;

import java.util.List;

public class Table {
    private TableElement[][] table;

    public Table(Sequence sequence1,Sequence sequence2, FineTable fines) {
        table = new TableElement[sequence1.size()][sequence2.size()];

        for (int i = 0; i < sequence1.size(); i++) {
            //table[0][i] = new TableElement(fines.get());
        }
    }
}
