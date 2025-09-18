package org.example.baitaptuan5_17_09.service;

import org.example.baitaptuan5_17_09.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    Category save(Category category);
    Optional<Category> findById(int id);
    void deleteById(int id);
    Page<Category> findAll(Pageable pageable);
    Page<Category> search(String keyword, Pageable pageable);
    List<Category> search(String keyword);
}
