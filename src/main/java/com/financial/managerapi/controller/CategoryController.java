package com.financial.managerapi.controller;

import com.financial.managerapi.dto.CategoryRequest;
import com.financial.managerapi.entities.Category;
import com.financial.managerapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getListCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findCategoryById(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Category>>getListCategory() {
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Category>> getListCategoryPage(Pageable pageable) {
        return new ResponseEntity<>(categoryService.findAllCategoriesPageable(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest category) {
        return new ResponseEntity<>(categoryService.createCategory(category),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryRequest category,@PathVariable  Long id) {
        return new ResponseEntity<>(categoryService.updateCategory(category,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
       return new ResponseEntity<>(categoryService.deleteCategory(id),HttpStatus.OK) ;
    }


}
