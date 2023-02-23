package tg.alexv100.pagecounter.service;

import java.io.File;
import java.util.List;

public interface FileSystemService {
    List<File> searchFiles(File directory, String[] formats);
}
