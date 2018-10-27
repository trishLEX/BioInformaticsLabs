package ru.bmstu.bioinf.lab1;

import org.junit.Test;

/**
 * Тесты для лабораторной работы
 */
public class TableTest {
    private static final String UP = "up";
    private static final String LEFT = "left";

    @Test
    public void test1() {
        Sequence sequence1 = new Sequence(UP, "ATTGC");
        Sequence sequence2 = new Sequence(LEFT, "ATGC");
        FineTable fineTable = new DNAfullTable();
        new Table(sequence1, sequence2, fineTable).printResult();
    }

    @Test
    public void test2() {
        Sequence sequence1 = new Sequence(UP, "AGGAATA");
        Sequence sequence2 = new Sequence(LEFT, "AGCATT");
        FineTable fineTable = new DNAfullTable(-3);
        new Table(sequence1, sequence2, fineTable).printResult();
    }

    @Test
    public void test3() {
        Sequence sequence1 = new Sequence(UP, "AKLMNCMDAE");
        Sequence sequence2 = new Sequence(LEFT, "LMBACMDTERD");
        FineTable fineTable = new Blosum62Table();
        new Table(sequence1, sequence2, fineTable).printResult();
    }

    @Test
    public void test4() {
        Sequence sequence1 = new Sequence(UP, "KKLRTNFYKY");
        Sequence sequence2 = new Sequence(LEFT, "KNFCQCY");
        FineTable fineTable = new Blosum62Table(-3);
        new Table(sequence1, sequence2, fineTable).printResult();
    }

    @Test
    public void test5() {
        SequenceReader reader = new SequenceReader("src/main/resources/test.txt");
        FineTable fineTable = new DefaultFineTable();
        new Table(reader.next(), reader.next(), fineTable).printResult();
    }

    @Test
    public void test6() {
        Sequence sequence1 = new Sequence(UP, "MKMRFFSSPCGKAAVDPADRCKEVQQIRDQHPSKIPVIIERYKGEKQCGKAAVDPADRKTKFLVPDHVNMSELVKI" +
                "IRRRLQLNPTQAFFLLVNQHSMVSVSTPIADIYEQEKDEDGFLYMVYASQETFGFIRENE");
        Sequence sequence2 = new Sequence(LEFT, "CGKAAVDPADRARBNCGKAAVDPADRQGHYQHSMVSVSTPIADIYEQEKRENE");
        FineTable fineTable = new Blosum62Table();
        new Table(sequence1, sequence2, fineTable).printResult();
    }
}
