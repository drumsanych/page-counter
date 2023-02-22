package tg.alexv100.pagecounter.parsers.implementations;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import tg.alexv100.pagecounter.parsers.FileParser;

import java.io.File;
import java.io.IOException;

@Component
@Qualifier("fileparser")
public class PdfFileParser implements FileParser {
    @Override
    public long getPagesCount(File file) {
        int pagesCount = 0;
        if (file.getName().endsWith(".pdf")) {
            try (PDDocument pdDocument = PDDocument.load(file)) {
                pagesCount = pdDocument.getNumberOfPages();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return pagesCount;
    }
}
