package org.example.baitaptuan5_17_09.repository;

import org.example.baitaptuan5_17_09.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameContainingIgnoreCase(String keyword);

    Page<Category> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

}
