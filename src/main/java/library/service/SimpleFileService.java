package library.service;

import library.model.File;
import library.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class SimpleFileService implements FileService {

    private final FileRepository fileRepository;

    public SimpleFileService(FileRepository sql2oFileRepository) {
       this.fileRepository = sql2oFileRepository;
    }

//    private void createStorageDirectory(String path) {
//        try {
//            Files.createDirectories(Path.of(path));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public Optional<File> getFileById(int id) {
        return fileRepository.findById(id);
    }

}
