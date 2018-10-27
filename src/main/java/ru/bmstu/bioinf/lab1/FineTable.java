package ru.bmstu.bioinf.lab1;

import java.util.HashMap;
import java.util.Map;

/**
 * Таблица штрафов
 */
public interface FineTable {
    char GAP = '-';

    int get(char x, char y);

    default int getE() {
        return -2;
    }
}
