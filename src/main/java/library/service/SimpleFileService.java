package library.service;

import library.model.File;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SimpleFileService implements FileService {

    @Override
    public Optional<File> getFileById(int id) {
        return Optional.empty();
    }

}
