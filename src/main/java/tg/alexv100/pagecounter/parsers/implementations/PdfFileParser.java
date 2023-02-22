package tg.alexv100.pagecounter.parsers.implementations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import tg.alexv100.pagecounter.parsers.FileParser;

import java.io.File;

@Component
@Qualifier("fileparser")
public class PdfFileParser implements FileParser {
    @Override
    public long getPagesCount(File file) {
        return 0;
    }
}
