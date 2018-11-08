package ru.bmstu.bioinf.lab2;

import org.junit.Test;
import ru.bmstu.bioinf.Common.Sequence;
import ru.bmstu.bioinf.Common.SequenceReader;

public class TableTest {
    private static final String UP = "up";
    private static final String LEFT = "left";

    @Test
    public void test1() {
        Sequence sequence1 = new Sequence(UP, "ACGT");
        Sequence sequence2 = new Sequence(LEFT, "ACGGCTT");
        FineTable fineTable = new DNAfullTable();
        new Table(sequence1, sequence2, fineTable).printResult();
    }

    @Test
    public void test2() {
        Sequence sequence1 = new Sequence(UP, "AGGAATA");
        Sequence sequence2 = new Sequence(LEFT, "AGCATT");
        FineTable fineTable = new DNAfullTable(-10, -1);
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
        FineTable fineTable = new Blosum62Table(-10, -1);
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
