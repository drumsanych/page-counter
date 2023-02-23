package tg.alexv100.pagecounter.parsers.implementations;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PdfFileParserTest {

    @Test
    void pdf_getPagesCount_success() throws IOException {
        PdfFileParser pdfFileParser = new PdfFileParser();
        File file = Mockito.mock(File.class);
        Mockito.doReturn(true)
                .when(file)
                .isFile();
        Mockito.doReturn("/helloworld.pdf")
                .when(file)
                .getName();
        PDDocument pdDocument = Mockito.mock(PDDocument.class);
        Mockito.doReturn(10)
                .when(pdDocument)
                .getNumberOfPages();

        try (MockedStatic<PDDocument> pddoc = Mockito.mockStatic(PDDocument.class)) {
            pddoc.when((MockedStatic.Verification) PDDocument.load(file)).thenReturn(pdDocument);
            assertEquals(10, pdfFileParser.getPagesCount(file));
        }
    }

    @Test
    void isNotFile_getPagesCount_fail() {
        PdfFileParser pdfFileParser = new PdfFileParser();
        File file = Mockito.mock(File.class);
        Mockito.doReturn(false)
                .when(file)
                .isFile();
        PDDocument pdDocument = Mockito.mock(PDDocument.class);
            assertThrows(RuntimeException.class, () -> pdfFileParser.getPagesCount(file));
    }

    @Test
    void anotherFormat_getPagesCount_emptyReturn() {
        PdfFileParser pdfFileParser = new PdfFileParser();
        File file = Mockito.mock(File.class);
        Mockito.doReturn(true)
                .when(file)
                .isFile();
        Mockito.doReturn("/helloworld.jpeg")
                .when(file)
                .getName();
        assertEquals(0, pdfFileParser.getPagesCount(file));
    }
}