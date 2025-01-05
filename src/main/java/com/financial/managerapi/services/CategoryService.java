package com.financial.managerapi.services;

import com.financial.managerapi.dto.CategoryRequest;
import com.financial.managerapi.entities.Category;
import com.financial.managerapi.entities.User;
import com.financial.managerapi.exception.NotFoundException;
import com.financial.managerapi.interfaces.CategoryInterface;
import com.financial.managerapi.interfaces.UserInterface;
import com.financial.managerapi.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class CategoryService implements CategoryInterface, UserInterface {

    private final CategoryRepository categoryRepository;



    @Override
    public Category findCategoryById(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public List<Category> findAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAllCategoriesPageable(Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {

         Category category = Category.builder()
                 .name(categoryRequest.getName())
                 .user(getConnectedUser())
                 .build();

         this.categoryRepository.save(category);

         return category;

    }

    @Override
    public Category updateCategory(CategoryRequest categoryRequest, Long id) {

        Category category = this.findCategoryById(id);

        category.setName(categoryRequest.getName());
        this.categoryRepository.save(category);

        return category;

    }

    @Override
    public Category deleteCategory(Long id) {
        Category category = this.findCategoryById(id);
        this.categoryRepository.delete(category);
        return category;
    }

    @Override
    public User getConnectedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
