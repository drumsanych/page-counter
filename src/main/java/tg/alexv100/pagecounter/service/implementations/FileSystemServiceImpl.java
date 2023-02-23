package tg.alexv100.pagecounter.service.implementations;

import org.springframework.stereotype.Service;
import tg.alexv100.pagecounter.service.FileSystemService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileSystemServiceImpl implements FileSystemService {
    @Override
    public List<File> searchFiles(File directory, String[] formats) {
        List<File> filesList = new ArrayList<>();
        fillList(filesList, directory, formats);
        return filesList;
    }


    private void fillList(List<File> filesList, File rootFile, String[] formats) {
        if (rootFile.isDirectory()) {
            File[] allFiles = rootFile.listFiles();
            if (allFiles != null) {
                for (File file : allFiles) {
                    if (file.isDirectory()) {
                        fillList(filesList, file, formats);
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
            throw new RuntimeException("Wrong file path");
        }
    }
}
