package ru.bmstu.bioinf.lab1;

import java.util.HashMap;
import java.util.Map;

/**
 * Таблица штрафов
 */
public class FineTable {
    private int E = -2;
    private TaskType type;
    private Map<Character, Integer> keys;

    public static final char GAP = '-';

    private final int[][] DNAfull = {
            //       A   T   G   C   S   W   R   Y   K   M   B   V   H   D   N  -
            /* A */{ 5, -4, -4, -4, -4,  1,  1, -4, -4,  1, -4, -1, -1, -1, -2, E},
            /* T */{-4,  5, -4, -4, -4,  1, -4,  1,  1, -4, -1, -4, -1, -1, -2, E},
            /* G */{-4, -4,  5, -4,  1, -4,  1, -4,  1, -4, -1, -1, -4, -1, -2, E},
            /* C */{-4, -4, -4,  5,  1, -4, -4,  1, -4,  1, -1, -1, -1, -4, -2, E},
            /* S */{-4, -4,  1,  1, -1, -4, -2, -2, -2, -2, -1, -1, -3, -3, -1, E},
            /* W */{ 1,  1, -4, -4, -4, -1, -2, -2, -2, -2, -3, -3, -1, -1, -1, E},
            /* R */{ 1, -4,  1, -4, -2, -2, -1, -4, -2, -2, -3, -1, -3, -1, -1, E},
            /* Y */{-4,  1, -4,  1, -2, -2, -4, -1, -2, -2, -1, -3, -1, -3, -1, E},
            /* K */{-4,  1,  1, -4, -2, -2, -2, -2, -1, -4, -1, -3, -3, -1, -1, E},
            /* M */{ 1, -4, -4,  1, -2, -2, -2, -2, -4, -1, -3, -1, -1, -3, -1, E},
            /* B */{-4, -1, -1, -1, -1, -3, -3, -1, -1, -3, -1, -2, -2, -2, -1, E},
            /* V */{-1, -4, -1, -1, -1, -3, -1, -3, -3, -1, -2, -1, -2, -2, -1, E},
            /* H */{-1, -1, -4, -1, -3, -1, -3, -1, -3, -1, -2, -2, -1, -2, -1, E},
            /* D */{-1, -1, -1, -4, -3, -1, -1, -3, -1, -3, -2, -2, -2, -1, -1, E},
            /* N */{-2, -2, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, E},
            /* - */{ E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E, E}
    };

    private final int[][] BLOSUM62 = {
            //        A   R   N   D   C   Q   E   G   H   I   L   K   M   F   P   S   T   W   Y   V   B   Z   X  -
            /* A */ { 4, -1, -2, -2,  0, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -3, -2,  0, -2, -1,  0, E},
            /* R */ {-1,  5,  0, -2, -3,  1,  0, -2,  0, -3, -2,  2, -1, -3, -2, -1, -1, -3, -2, -3, -1,  0 ,-1, E},
            /* N */ {-2,  0,  6,  1, -3,  0,  0,  0,  1, -3, -3,  0, -2, -3, -2,  1,  0, -4, -2, -3,  3,  0, -1, E},
            /* D */ {-2, -2,  1,  6, -3,  0,  2, -1, -1, -3, -4, -1, -3, -3, -1,  0, -1, -4, -3, -3,  4,  1, -1, E},
            /* C */ { 0, -3, -3, -3,  9, -3, -4, -3, -3, -1, -1, -3, -1, -2, -3, -1, -1, -2, -2, -1, -3, -3, -2, E},
            /* Q */ {-1,  1,  0,  0, -3,  5,  2, -2,  0, -3, -2,  1,  0, -3, -1,  0, -1, -2, -1, -2,  0,  3, -1, E},
            /* E */ {-1,  0,  0,  2, -4,  2,  5, -2,  0, -3, -3,  1, -2, -3, -1,  0, -1, -3, -2, -2,  1,  4, -1, E},
            /* G */ { 0, -2,  0, -1, -3, -2, -2,  6, -2, -4, -4, -2, -3, -3, -2,  0, -2, -2, -3, -3, -1, -2, -1, E},
            /* H */ {-2,  0,  1, -1, -3,  0,  0, -2,  8, -3, -3, -1, -2, -1, -2, -1, -2, -2,  2, -3,  0,  0, -1, E},
            /* I */ {-1, -3, -3, -3, -1, -3, -3, -4, -3,  4,  2, -3,  1,  0, -3, -2, -1, -3, -1,  3, -3, -3, -1, E},
            /* L */ {-1, -2, -3, -4, -1, -2, -3, -4, -3,  2,  4, -2,  2,  0, -3, -2, -1, -2, -1,  1, -4, -3, -1, E},
            /* K */ {-1,  2,  0, -1, -3,  1,  1, -2, -1, -3, -2,  5, -1, -3, -1,  0, -1, -3, -2, -2,  0,  1, -1, E},
            /* M */ {-1, -1, -2, -3, -1,  0, -2, -3, -2,  1,  2, -1,  5,  0, -2, -1, -1, -1, -1,  1, -3, -1, -1, E},
            /* F */ {-2, -3, -3, -3, -2, -3, -3, -3, -1,  0,  0, -3,  0,  6, -4, -2, -2,  1,  3, -1, -3, -3, -1, E},
            /* P */ {-1, -2, -2, -1, -3, -1, -1, -2, -2, -3, -3, -1, -2, -4,  7, -1, -1, -4, -3, -2, -2, -1, -2, E},
            /* S */ { 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -2,  0, -1, -2, -1,  4,  1, -3, -2, -2,  0,  0,  0, E},
            /* T */ { 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  1,  5, -2, -2,  0, -1, -1,  0, E},
            /* W */ {-3, -3, -4, -4, -2, -2, -3, -2, -2, -3, -2, -3, -1,  1, -4, -3, -2, 11,  2, -3, -4, -3, -2, E},
            /* Y */ {-2, -2, -2, -3, -2, -1, -2, -3,  2, -1, -1, -2, -1,  3, -3, -2, -2,  2,  7, -1, -3, -2, -1, E},
            /* V */ { 0, -3, -3, -3, -1, -2, -2, -3, -3,  3,  1, -2,  1, -1, -2, -2,  0, -3, -1,  4, -3, -2, -1, E},
            /* B */ {-2, -1,  4,  4, -3,  0,  1, -1,  0, -3, -4,  0, -3, -3, -2,  0, -1, -4, -3, -3,  4,  1, -1, E},
            /* Z */ {-1,  0,  0,  1, -3,  4,  4, -2,  0, -3, -3,  1, -1, -3, -1,  0, -1, -3, -2, -2,  1,  4, -1, E},
            /* X */ { 0, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2,  0,  0, -2, -1, -1, -1, -1, -1, E},
            /* - */ { E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E,  E, 1}
    };

    public FineTable(TaskType type) {
        this.type = type;
        this.keys = new HashMap<>();

        switch (type) {
            case DNA_FULL:
                setDNAfullKeys();
                break;
            case BLOSUM62:
                setBlosumKeys();
                break;
        }
    }

    public FineTable(TaskType type, int gap) {
        this(type);

        E = gap;
        if (type == TaskType.DNA_FULL) {
            for (int i = 0; i < DNAfull.length; i++) {
                DNAfull[15][i] = DNAfull[i][15] = gap;
            }
        } else if (type == TaskType.BLOSUM62) {
            for (int i = 0; i < BLOSUM62.length; i++) {
                BLOSUM62[i][23] = BLOSUM62[23][i] = gap;
            }
        }
    }
    
    private void setDNAfullKeys() {
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

    private void setBlosumKeys() {
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

    public int get(char x, char y) {
        if (x != GAP && !Character.isLetter(x)) {
            throw new IllegalStateException("Wrong character in sequence: " + x);
        } else if (y != GAP && !Character.isLetter(y)) {
            throw new IllegalStateException("Wrong character in sequence: " + y);
        }

        switch (type) {
            case BLOSUM62:
                return BLOSUM62[keys.get(x)][keys.get(y)];
            case DNA_FULL:
                return DNAfull[keys.get(x)][keys.get(y)];
            default:
                if (x == GAP || y == GAP) {
                    return E;
                } else if (x == y) {
                    return 1;
                } else {
                    return -1;
                }
        }
    }

    public int getE() {
        return E;
    }
}
