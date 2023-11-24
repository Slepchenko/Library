package library.service;

import library.model.File;
import library.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SimpleFileService implements FileService {

    private final FileRepository fileRepository;

    public SimpleFileService(FileRepository sql2oFileRepository) {
       this.fileRepository = sql2oFileRepository;
    }

    @Override
    public Optional<File> getFileById(int id) {
        return fileRepository.findById(id);
    }

}
