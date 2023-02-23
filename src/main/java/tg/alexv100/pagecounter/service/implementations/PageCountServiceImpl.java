package tg.alexv100.pagecounter.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tg.alexv100.pagecounter.data.requestDto.RequestDTO;
import tg.alexv100.pagecounter.data.dto.DocumentsAndPagesCountDTO;
import tg.alexv100.pagecounter.parsers.FileParser;
import tg.alexv100.pagecounter.service.FileSystemService;
import tg.alexv100.pagecounter.service.PageCountService;

import java.io.File;
import java.util.List;

@Service
@Slf4j
public class PageCountServiceImpl implements PageCountService {

    private FileSystemService fileSystemService;
    private List<FileParser> fileParsers;

    public PageCountServiceImpl(@Qualifier("fileparser") List<FileParser> fileParsers,
                                FileSystemService fileSystemService) {
        this.fileParsers = fileParsers;
        this.fileSystemService = fileSystemService;
    }

    @Override
    public DocumentsAndPagesCountDTO searchDocuments(RequestDTO requestObj) {
        String url = requestObj.getUrl();
        List<File> files = fileSystemService.searchFiles(new File(requestObj.getUrl()), requestObj.getFormats());
        long count = 0;
        for (File file : files) {
            for (FileParser fileParser : fileParsers) {
                count += fileParser.getPagesCount(file);
            }
        }
        return new DocumentsAndPagesCountDTO(url, files.size(), count);
    }
}
