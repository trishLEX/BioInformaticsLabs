package ru.bmstu.bioinf.lab2;

import org.junit.Test;

public class TableTest {
    private static final String UP = "up";
    private static final String LEFT = "left";

    @Test
    public void test1() {
        Sequence sequence1 = new Sequence(UP, "ATTGC");
        Sequence sequence2 = new Sequence(LEFT, "ATGC");
        new Table(sequence1, sequence2, TaskType.DNA_FULL).printResult();
    }

    @Test
    public void test2() {
        Sequence sequence1 = new Sequence(UP, "AGGAATA");
        Sequence sequence2 = new Sequence(LEFT, "AGCATT");
        new Table(sequence1, sequence2, -3, TaskType.DNA_FULL).printResult();
    }

    @Test
    public void test3() {
        Sequence sequence1 = new Sequence(UP, "AKLMNCMDAE");
        Sequence sequence2 = new Sequence(LEFT, "LMBACMDTERD");
        new Table(sequence1, sequence2, TaskType.BLOSUM62).printResult();
    }

    @Test
    public void test4() {
        Sequence sequence1 = new Sequence(UP, "KKLRTNFYKY");
        Sequence sequence2 = new Sequence(LEFT, "KNFCQCY");
        new Table(sequence1, sequence2, TaskType.BLOSUM62).printResult();
    }

    @Test
    public void test5() {
        SequenceReader reader = new SequenceReader("src/main/resources/test.txt");
        new Table(reader.next(), reader.next(), TaskType.DEFAULT).printResult();
    }
}
