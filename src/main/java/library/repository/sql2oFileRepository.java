package library.repository;

import library.model.File;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class sql2oFileRepository implements FileRepository {

    @Override
    public Optional<File> findById(int id) {
        return Optional.empty();
    }

}
