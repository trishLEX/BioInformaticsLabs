package ru.bmstu.bioinf.lab2;

public class DefaultFineTable implements FineTable {
    private int openE = -2;
    private int extendE = -1;

    public DefaultFineTable() {}

    public DefaultFineTable(Integer openGap, Integer extendGap) {
        if (openGap != null) {
            openE = openGap;
        }

        if (extendGap != null) {
            extendE = extendGap;
        }
    }

    @Override
    public int get(char x, char y) {
        if (x != GAP && !Character.isLetter(x)) {
            throw new IllegalStateException("Wrong character in sequence: " + x);
        } else if (y != GAP && !Character.isLetter(y)) {
            throw new IllegalStateException("Wrong character in sequence: " + y);
        }

        if (x == GAP || y == GAP) {
            return openE;
        } else if (x == y) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int getOpenE() {
        return openE;
    }

    @Override
    public int getExtendE() {
        return extendE;
    }
}
