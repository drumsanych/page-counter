package tg.alexv100.pagecounter.parsers.implementations;


import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import tg.alexv100.pagecounter.parsers.FileParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
@Qualifier("fileparser")
public class DocxFileParser implements FileParser {
    @Override
    public long getPagesCount(File file) {
        int pagesCount = 0;
        if (file.getName().endsWith(".docx")) {
            try (FileInputStream fis = new FileInputStream(file.getAbsolutePath())) {
                XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(file.getAbsolutePath()));
                pagesCount = document.getProperties().getExtendedProperties().getPages();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return pagesCount;
    }
}
