package ru.bmstu.bioinf.lab1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Функциональные тесты работы таблицы штрафов
 */
public class FineTableTest {

    @Test
    public void getFromBlosumTableTest() {
        FineTable table = new FineTable(TaskType.BLOSUM62);
        assertEquals(-2, table.get('A', 'Y'));
    }

    @Test
    public void getFromBlosumTableGapTest() {
        FineTable table = new FineTable(TaskType.BLOSUM62);
        assertEquals(-2, table.get('-', 'A'));
    }

    @Test
    public void getFromBlosumCustomGapTest() {
        FineTable table = new FineTable(TaskType.BLOSUM62, -17);
        assertEquals(-17, table.get('A', '-'));
    }

    @Test
    public void getFromDNAfullTest() {
        FineTable table = new FineTable(TaskType.DNA_FULL);
        assertEquals(-4, table.get('T', 'R'));
    }

    @Test
    public void getFromDNAfullGapTest() {
        FineTable table = new FineTable(TaskType.DNA_FULL);
        assertEquals(-2, table.get('-', 'A'));
    }

    @Test
    public void getFromDNAfullCustomGapTest() {
        FineTable table = new FineTable(TaskType.DNA_FULL, -17);
        assertEquals(-17, table.get('B', '-'));
    }

    @Test
    public void getDefaultTest() {
        FineTable table = new FineTable(TaskType.DEFAULT);
        assertEquals(1, table.get('A', 'A'));
    }

    @Test
    public void getDefaultGapTest() {
        FineTable table = new FineTable(TaskType.DEFAULT);
        assertEquals(-2, table.get('A', '-'));
    }

    @Test
    public void getDefaultCustomGapTest() {
        FineTable table = new FineTable(TaskType.DEFAULT, -17);
        assertEquals(-17, table.get('-', 'B'));
    }
}
