package ru.bmstu.bioinf.lab1;

public class DefaultFineTable implements FineTable {
    private int E = -2;

    public DefaultFineTable() { }

    public DefaultFineTable(Integer gap) {
        if (gap != null) {
            E = gap;
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
            return E;
        } else if (x == y) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int getE() {
        return E;
    }
}
