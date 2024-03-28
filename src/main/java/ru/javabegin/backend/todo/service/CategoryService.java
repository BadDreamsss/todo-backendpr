package ru.javabegin.backend.todo.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.javabegin.backend.todo.Entity.Category;
import ru.javabegin.backend.todo.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public List<Category> findAll(String email) {
        return repository.findByUserEmailOrderByTitleAsc(email);
    }

    public Category add(Category category){
        return  repository.save(category);
    }

    public Category update(Category category){
        return repository.save(category);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public List<Category> findByTitle(String text, String email){
        return repository.findByTitle(text, email);
    }

}
