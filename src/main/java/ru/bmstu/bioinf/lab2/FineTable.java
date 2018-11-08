package ru.bmstu.bioinf.lab2;

/**
 * Таблица штрафов
 */
public interface FineTable {
    char GAP = '-';

    int get(char x, char y);

    default int getOpenE() {
        return -2;
    }

    default int getExtendE() {
        return -1;
    }
}
