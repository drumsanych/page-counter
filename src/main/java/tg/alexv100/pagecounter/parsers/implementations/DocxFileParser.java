package tg.alexv100.pagecounter.parsers.implementations;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import tg.alexv100.pagecounter.parsers.FileParser;

import java.io.File;

@Component
@Qualifier("fileparser")
@Slf4j
public class DocxFileParser implements FileParser {

    @Override
    public long getPagesCount(File file) {
        if (file == null || !file.isFile()) throw new RuntimeException("Wrong file path");
        int pagesCount = 0;
        if (file.getName().endsWith(".docx")) {
            try {
                XWPFDocument xwpfDocument = new XWPFDocument(POIXMLDocument.openPackage(file.getPath()));
                pagesCount = xwpfDocument.getProperties().getExtendedProperties().getPages();
            } catch (Exception e) {
                log.error(e.getMessage());
                return 1; // couldn't count pages, default return (1 docx = 1 page)
            }
        }
        return pagesCount;
    }


}
