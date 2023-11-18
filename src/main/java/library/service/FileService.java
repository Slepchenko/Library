package library.service;

import library.model.File;

import java.util.Optional;

public interface FileService {

    Optional<File> getFileById(int id);

}
