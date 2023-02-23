package tg.alexv100.pagecounter.parsers.implementations;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class DocxFileParserTest {

    @Test
    void isNotFile_getPagesCount_fail() {
        DocxFileParser docxFileParser = new DocxFileParser();
        File file = Mockito.mock(File.class);
        Mockito.doReturn(false)
                .when(file)
                .isFile();
        assertThrows(RuntimeException.class, () -> docxFileParser.getPagesCount(file));
    }

    @Test
    void anotherFormat_getPagesCount_ReturnZero() {
        DocxFileParser docxFileParser = new DocxFileParser();
        File file = Mockito.mock(File.class);
        Mockito.doReturn(true)
                .when(file)
                .isFile();
        Mockito.doReturn("/hello.jpg")
                .when(file)
                .getName();
        assertEquals(0, docxFileParser.getPagesCount(file));
    }
}