package ru.bmstu.bioinf.lab2;

import java.util.HashMap;
import java.util.Map;

public class Blosum62Table implements FineTable {
    private int openE = -2;
    private int extendE = -1;
    private Map<Character, Integer> keys;

    private final int[][] BLOSUM62 = {
            //        A   R   N   D   C   Q   E   G   H   I   L   K   M   F   P   S   T   W   Y   V   B   Z   X  -
            /* A */ { 4, -1, -2, -2,  0, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -3, -2,  0, -2, -1,  0, openE},
            /* R */ {-1,  5,  0, -2, -3,  1,  0, -2,  0, -3, -2,  2, -1, -3, -2, -1, -1, -3, -2, -3, -1,  0 ,-1, openE},
            /* N */ {-2,  0,  6,  1, -3,  0,  0,  0,  1, -3, -3,  0, -2, -3, -2,  1,  0, -4, -2, -3,  3,  0, -1, openE},
            /* D */ {-2, -2,  1,  6, -3,  0,  2, -1, -1, -3, -4, -1, -3, -3, -1,  0, -1, -4, -3, -3,  4,  1, -1, openE},
            /* C */ { 0, -3, -3, -3,  9, -3, -4, -3, -3, -1, -1, -3, -1, -2, -3, -1, -1, -2, -2, -1, -3, -3, -2, openE},
            /* Q */ {-1,  1,  0,  0, -3,  5,  2, -2,  0, -3, -2,  1,  0, -3, -1,  0, -1, -2, -1, -2,  0,  3, -1, openE},
            /* openE */ {-1,  0,  0,  2, -4,  2,  5, -2,  0, -3, -3,  1, -2, -3, -1,  0, -1, -3, -2, -2,  1,  4, -1, openE},
            /* G */ { 0, -2,  0, -1, -3, -2, -2,  6, -2, -4, -4, -2, -3, -3, -2,  0, -2, -2, -3, -3, -1, -2, -1, openE},
            /* H */ {-2,  0,  1, -1, -3,  0,  0, -2,  8, -3, -3, -1, -2, -1, -2, -1, -2, -2,  2, -3,  0,  0, -1, openE},
            /* I */ {-1, -3, -3, -3, -1, -3, -3, -4, -3,  4,  2, -3,  1,  0, -3, -2, -1, -3, -1,  3, -3, -3, -1, openE},
            /* L */ {-1, -2, -3, -4, -1, -2, -3, -4, -3,  2,  4, -2,  2,  0, -3, -2, -1, -2, -1,  1, -4, -3, -1, openE},
            /* K */ {-1,  2,  0, -1, -3,  1,  1, -2, -1, -3, -2,  5, -1, -3, -1,  0, -1, -3, -2, -2,  0,  1, -1, openE},
            /* M */ {-1, -1, -2, -3, -1,  0, -2, -3, -2,  1,  2, -1,  5,  0, -2, -1, -1, -1, -1,  1, -3, -1, -1, openE},
            /* F */ {-2, -3, -3, -3, -2, -3, -3, -3, -1,  0,  0, -3,  0,  6, -4, -2, -2,  1,  3, -1, -3, -3, -1, openE},
            /* P */ {-1, -2, -2, -1, -3, -1, -1, -2, -2, -3, -3, -1, -2, -4,  7, -1, -1, -4, -3, -2, -2, -1, -2, openE},
            /* S */ { 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -2,  0, -1, -2, -1,  4,  1, -3, -2, -2,  0,  0,  0, openE},
            /* T */ { 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  1,  5, -2, -2,  0, -1, -1,  0, openE},
            /* W */ {-3, -3, -4, -4, -2, -2, -3, -2, -2, -3, -2, -3, -1,  1, -4, -3, -2, 11,  2, -3, -4, -3, -2, openE},
            /* Y */ {-2, -2, -2, -3, -2, -1, -2, -3,  2, -1, -1, -2, -1,  3, -3, -2, -2,  2,  7, -1, -3, -2, -1, openE},
            /* V */ { 0, -3, -3, -3, -1, -2, -2, -3, -3,  3,  1, -2,  1, -1, -2, -2,  0, -3, -1,  4, -3, -2, -1, openE},
            /* B */ {-2, -1,  4,  4, -3,  0,  1, -1,  0, -3, -4,  0, -3, -3, -2,  0, -1, -4, -3, -3,  4,  1, -1, openE},
            /* Z */ {-1,  0,  0,  1, -3,  4,  4, -2,  0, -3, -3,  1, -1, -3, -1,  0, -1, -3, -2, -2,  1,  4, -1, openE},
            /* X */ { 0, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2,  0,  0, -2, -1, -1, -1, -1, -1, openE},
            /* - */ {openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, openE, 1}
    };

    public Blosum62Table() {
        this.keys = new HashMap<>();

        keys.put('A', 0);
        keys.put('R', 1);
        keys.put('N', 2);
        keys.put('D', 3);
        keys.put('C', 4);
        keys.put('Q', 5);
        keys.put('E', 6);
        keys.put('G', 7);
        keys.put('H', 8);
        keys.put('I', 9);
        keys.put('L', 10);
        keys.put('K', 11);
        keys.put('M', 12);
        keys.put('F', 13);
        keys.put('P', 14);
        keys.put('S', 15);
        keys.put('T', 16);
        keys.put('W', 17);
        keys.put('Y', 18);
        keys.put('V', 19);
        keys.put('B', 20);
        keys.put('Z', 21);
        keys.put('X', 22);
        keys.put(GAP, 23);
    }

    public Blosum62Table(Integer openGap, Integer extendGap) {
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
        
        return BLOSUM62[keys.get(x)][keys.get(y)];
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
