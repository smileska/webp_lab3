package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.repository.CategoryRepository;
import mk.ukim.finki.wp.lab.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        System.out.println("CategoryServiceImpl initialized with repository: " + (categoryRepository != null));
    }

    @Override
    public Category save(String name, String description) {
        try {
            System.out.println("Attempting to save category - Name: " + name); // Debug log
            Category category = new Category();
            category.setName(name);
            category.setDescription(description);
            Category saved = categoryRepository.save(category);
            System.out.println("Category saved successfully with ID: " + saved.getId()); // Debug log
            return saved;
        } catch (Exception e) {
            System.out.println("Error in service while saving category: "); // Debug log
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

//    @Override
//    public Category save(String name, String description) {
//        Category category = new Category();
//        category.setName(name);
//        category.setDescription(description);
//        return categoryRepository.save(category);
//    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> update(Long id, String name, String description) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(name);
                    category.setDescription(description);
                    return categoryRepository.save(category);
                });
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            return findAll();
        }
        return categoryRepository.findByNameContainingIgnoreCase(searchText);
    }
}