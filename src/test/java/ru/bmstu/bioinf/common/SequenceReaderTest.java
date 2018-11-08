package ru.bmstu.bioinf.common;

import org.junit.Test;
import ru.bmstu.bioinf.Common.SequenceReader;

import java.io.File;

/**
 * ТОЛЬКО функциональное тестирование SequenceReader'a
 */
public class SequenceReaderTest {
    private static final String TEST_FILE = new File("src/test/java/ru/bmstu/bioinf/common/test.txt").getAbsolutePath();

    @Test
    public void readerNoExceptionTest() {
        SequenceReader reader = new SequenceReader(TEST_FILE);
        while (reader.hasNext()) {
            System.out.println(reader.next());
        }
    }
}
