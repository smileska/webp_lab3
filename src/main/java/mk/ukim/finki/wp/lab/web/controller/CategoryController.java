package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(
            @RequestParam(required = false) String searchText,
            Model model) {
        model.addAttribute("categories", categoryService.searchCategories(searchText));
        model.addAttribute("searchText", searchText);
        return "listCategories";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
        model.addAttribute("category", category);
        return "addCategory";
    }

    @PostMapping("/save")
    public String saveCategory(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description) {
        try {
            System.out.println("Received save request - Name: " + name + ", Description: " + description);
            if (id == null) {
                Category saved = categoryService.save(name, description);
                System.out.println("Category saved with ID: " + saved.getId()); // Debug log
            } else {
                categoryService.update(id, name, description);
            }
            return "redirect:/categories";
        } catch (Exception e) {
            System.out.println("Error saving category: ");
            e.printStackTrace();
            return "redirect:/categories?error=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}