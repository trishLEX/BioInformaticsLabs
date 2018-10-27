package ru.bmstu.bioinf.lab1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Функциональные тесты работы таблицы штрафов
 */
public class FineTableTest {

    @Test
    public void getFromBlosumTableTest() {
        FineTable table = new Blosum62Table();
        assertEquals(-2, table.get('A', 'Y'));
    }

    @Test
    public void getFromBlosumTableGapTest() {
        FineTable table = new Blosum62Table();
        assertEquals(-2, table.get('-', 'A'));
    }

    @Test
    public void getFromBlosumCustomGapTest() {
        FineTable table = new Blosum62Table(-17);
        assertEquals(-17, table.get('A', '-'));
    }

    @Test
    public void getFromDNAfullTest() {
        FineTable table = new DNAfullTable();
        assertEquals(-4, table.get('T', 'R'));
    }

    @Test
    public void getFromDNAfullGapTest() {
        FineTable table = new DNAfullTable();
        assertEquals(-2, table.get('-', 'A'));
    }

    @Test
    public void getFromDNAfullCustomGapTest() {
        FineTable table = new DNAfullTable(-17);
        assertEquals(-17, table.get('B', '-'));
    }

    @Test
    public void getDefaultTest() {
        FineTable table = new DefaultFineTable();
        assertEquals(1, table.get('A', 'A'));
    }

    @Test
    public void getDefaultGapTest() {
        FineTable table = new DefaultFineTable();
        assertEquals(-2, table.get('A', '-'));
    }

    @Test
    public void getDefaultCustomGapTest() {
        FineTable table = new DefaultFineTable(-17);
        assertEquals(-17, table.get('-', 'B'));
    }
}
