package ru.bmstu.bioinf.lab2;

import java.util.HashMap;
import java.util.Map;

public class DNAfullTable implements FineTable {
    private int openE = -2;
    private int extendE = -1;
    private Map<Character, Integer> keys;

    private final int[][] DNAfull = {
            //       A   T   G   C   S   W   R   Y   K   M   B   V   H   D   N  -
            /* A */{ 5, -4, -4, -4, -4,  1,  1, -4, -4,  1, -4, -1, -1, -1, -2, openE},
            /* T */{-4,  5, -4, -4, -4,  1, -4,  1,  1, -4, -1, -4, -1, -1, -2, openE},
            /* G */{-4, -4,  5, -4,  1, -4,  1, -4,  1, -4, -1, -1, -4, -1, -2, openE},
            /* C */{-4, -4, -4,  5,  1, -4, -4,  1, -4,  1, -1, -1, -1, -4, -2, openE},
            /* S */{-4, -4,  1,  1, -1, -4, -2, -2, -2, -2, -1, -1, -3, -3, -1, openE},
            /* W */{ 1,  1, -4, -4, -4, -1, -2, -2, -2, -2, -3, -3, -1, -1, -1, openE},
            /* R */{ 1, -4,  1, -4, -2, -2, -1, -4, -2, -2, -3, -1, -3, -1, -1, openE},
            /* Y */{-4,  1, -4,  1, -2, -2, -4, -1, -2, -2, -1, -3, -1, -3, -1, openE},
            /* K */{-4,  1,  1, -4, -2, -2, -2, -2, -1, -4, -1, -3, -3, -1, -1, openE},
            /* M */{ 1, -4, -4,  1, -2, -2, -2, -2, -4, -1, -3, -1, -1, -3, -1, openE},
            /* B */{-4, -1, -1, -1, -1, -3, -3, -1, -1, -3, -1, -2, -2, -2, -1, openE},
            /* V */{-1, -4, -1, -1, -1, -3, -1, -3, -3, -1, -2, -1, -2, -2, -1, openE},
            /* H */{-1, -1, -4, -1, -3, -1, -3, -1, -3, -1, -2, -2, -1, -2, -1, openE},
            /* D */{-1, -1, -1, -4, -3, -1, -1, -3, -1, -3, -2, -2, -2, -1, -1, openE},
            /* N */{-2, -2, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, openE},
            /* - */{ openE,  openE,  openE,  openE,  openE,  openE,  openE,  openE,  openE,  openE,  openE,  openE,  openE,  openE,  openE, openE}
    };

    public DNAfullTable() {
        this.keys = new HashMap<>();

        keys.put('A', 0);
        keys.put('T', 1);
        keys.put('G', 2);
        keys.put('C', 3);
        keys.put('S', 4);
        keys.put('W', 5);
        keys.put('R', 6);
        keys.put('Y', 7);
        keys.put('K', 8);
        keys.put('M', 9);
        keys.put('B', 10);
        keys.put('V', 11);
        keys.put('H', 12);
        keys.put('D', 13);
        keys.put('N', 14);
        keys.put(GAP, 15);
    }

    public DNAfullTable(Integer openGap, Integer extendGap) {
        this();

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

        return DNAfull[keys.get(x)][keys.get(y)];
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
