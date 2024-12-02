package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(String name, String description);
    Optional<Category> update(Long id, String name, String description);
    void delete(Long id);
    List<Category> searchCategories(String searchText);
}
