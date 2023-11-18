package library.repository;

import library.model.File;

import java.util.Optional;

public interface FileRepository {

    Optional<File> findById(int id);

}
