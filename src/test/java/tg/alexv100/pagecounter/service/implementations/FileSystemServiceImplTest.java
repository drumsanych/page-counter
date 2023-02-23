package tg.alexv100.pagecounter.service.implementations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FileSystemServiceImplTest {
    private final String[] formats = new String[]{"pdf"};
    @Mock
    private File directory;
    @Mock
    private File file;
    @InjectMocks
    private FileSystemServiceImpl fileSystemService;

    @Test
    void searchFiles_shouldReturnFilesList() {
        File[] allFiles = new File[]{file};
        when(file.isDirectory()).thenReturn(false);
        when(file.getName()).thenReturn("some.pdf");
        when(directory.isDirectory()).thenReturn(true);
        when(directory.listFiles()).thenReturn(allFiles);

        List<File> files = fileSystemService.searchFiles(directory, formats);
        assertNotNull(files);
        assertEquals(1, files.size());
        assertEquals("some.pdf", files.get(0).getName());
    }

    @Test
    void searchFiles_whenFileIsNotDirectory_shouldThrowException() {
        when(directory.isDirectory()).thenReturn(false);
        assertThrows(RuntimeException.class, () -> fileSystemService.searchFiles(directory, formats));
    }
}