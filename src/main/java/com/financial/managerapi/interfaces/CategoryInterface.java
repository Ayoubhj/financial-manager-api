package com.financial.managerapi.interfaces;

import com.financial.managerapi.dto.CategoryRequest;
import com.financial.managerapi.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface CategoryInterface {

    Category findCategoryById(Long id);

    List<Category> findAllCategories();

    Page<Category> findAllCategoriesPageable(Pageable pageable);

    Category createCategory(CategoryRequest categoryRequest);

    Category updateCategory(CategoryRequest categoryRequest,Long id);

    Category deleteCategory(Long id);


}
