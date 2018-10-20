package ru.bmstu.bioinf.lab2;

import org.junit.Test;

import java.io.File;

/**
 * ТОЛЬКО функциональное тестирование SequenceReader'a
 */
public class SequenceReaderTest {
    private static final String TEST_FILE = new File("src/test/java/ru/bmstu/bioinf/lab2/test.txt").getAbsolutePath();

    @Test
    public void readerNoExceptionTest() {
        SequenceReader reader = new SequenceReader(TEST_FILE);
        while (reader.hasNext()) {
            System.out.println(reader.next());
        }
    }
}
