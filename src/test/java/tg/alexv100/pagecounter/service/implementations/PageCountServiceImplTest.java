package tg.alexv100.pagecounter.service.implementations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tg.alexv100.pagecounter.data.requestDto.RequestDTO;
import tg.alexv100.pagecounter.data.dto.DocumentsAndPagesCountDTO;
import tg.alexv100.pagecounter.parsers.FileParser;
import tg.alexv100.pagecounter.service.FileSystemService;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PageCountServiceImplTest {
    private final String url = "someurl.pdf";
    @Mock
    private FileSystemService fileSystemService;
    @Mock
    private List<FileParser> fileParserList;
    @Mock
    private RequestDTO requestDTO;
    @Mock
    private File file;
    @InjectMocks
    private PageCountServiceImpl pageCountService;

    @Test
    void searchDocuments_shouldReturnDto() {
        when(requestDTO.getUrl()).thenReturn(url);
        when(requestDTO.getFormats()).thenReturn(new String[]{"pdf"});

        Iterator<FileParser> iterator = mock(Iterator.class);
        when(fileParserList.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        FileParser fileParser = Mockito.mock(FileParser.class);
        when(iterator.next()).thenReturn(fileParser);
        when(fileParser.getPagesCount(file)).thenReturn(50L);
        when(fileParserList.iterator()).thenReturn(iterator);
        List<File> files = new ArrayList<>();
        files.add(file);
        when(fileSystemService.searchFiles(new File(requestDTO.getUrl()), requestDTO.getFormats())).thenReturn(files);

        DocumentsAndPagesCountDTO documentsAndPagesCountDTO = pageCountService.searchDocuments(requestDTO);

        assertNotNull(documentsAndPagesCountDTO);
        assertEquals(50L, documentsAndPagesCountDTO.getPages());
        assertEquals(1, documentsAndPagesCountDTO.getDocuments());
        assertEquals(url, documentsAndPagesCountDTO.getUrl());
    }
}