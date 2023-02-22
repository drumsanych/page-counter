package tg.alexv100.pagecounter.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tg.alexv100.pagecounter.data.RequestEntity.RequestDTO;
import tg.alexv100.pagecounter.data.dto.DocumentsAndPagesCountDTO;
import tg.alexv100.pagecounter.parsers.FileParser;
import tg.alexv100.pagecounter.service.PageCountService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PageCountServiceImpl implements PageCountService {

    private List<FileParser> fileParsers;

    public PageCountServiceImpl(@Qualifier("fileparser") List<FileParser> fileParsers) {
        this.fileParsers = fileParsers;
    }

    @Override
    public DocumentsAndPagesCountDTO searchDocuments(RequestDTO requestObj) {
        String url = requestObj.getUrl();
        ArrayList<File> files = new ArrayList<>();
        long count = 0;

        searchFiles(new File(url), files, requestObj.getFormats());
        for (File file : files) {
            for (FileParser fileParser : fileParsers) {
                count += fileParser.getPagesCount(file);
            }
        }

        return new DocumentsAndPagesCountDTO(url, files.size(), count);
    }

    private static void searchFiles(File rootFile, List<File> filesList, String[] formats) {
        if (rootFile.isDirectory()) {
            File[] allFiles = rootFile.listFiles();
            if (allFiles != null) {
                for (File file : allFiles) {
                    if (file.isDirectory()) {
                        searchFiles(file, filesList, formats);
                    } else {
                        for (int i = 0; i < formats.length; i++) {
                            if (file.getName().toLowerCase().endsWith(formats[i])) {
                                filesList.add(file);
                            }
                        }
                    }
                }
            }
        } else {
            log.info("Wrong file path");
            throw new RuntimeException("Wrong file path");
        }
    }
}
