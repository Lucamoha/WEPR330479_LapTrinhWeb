package org.example.baitaptuan5_17_09.controller;

import lombok.RequiredArgsConstructor;
import org.example.baitaptuan5_17_09.entity.Category;
import org.example.baitaptuan5_17_09.service.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService icategoryService;

    // Danh sách + phân trang
    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       Model model) {
        Page<Category> categoryPage = icategoryService.findAll(PageRequest.of(page, size));
        model.addAttribute("categoryPage", categoryPage);
        return "admin/categories/searchPaging";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/categories/addOrEdit";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Category category = icategoryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID:" + id));
        model.addAttribute("category", category);
        return "admin/categories/addOrEdit";
    }

    // Lưu hoặc cập nhật
    @PostMapping("/save")
    public String save(@ModelAttribute Category category) {
        icategoryService.save(category);
        return "redirect:/admin/categories";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        icategoryService.deleteById(id);
        return "redirect:/admin/categories";
    }

    // Tìm kiếm + phân trang
    @GetMapping("/search")
    public String search(@RequestParam String keyword,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size,
                         Model model) {
        Page<Category> categoryPage = icategoryService.search(keyword, PageRequest.of(page, size));
        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("keyword", keyword);
        return "admin/categories/searchPaging";
    }
}
