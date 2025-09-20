package org.example.baitaptuan5_16_9.controller.admin;

import lombok.RequiredArgsConstructor;
import org.example.baitaptuan5_16_9.entity.Category;
import org.example.baitaptuan5_16_9.service.ICategoryService;
import org.example.baitaptuan5_16_9.storage.FileStorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService icategoryService;
    private final FileStorageService fileStorageService;

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
    public String save(@ModelAttribute Category category,
                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        // THÊM MỚI (id = 0)
        if (category.getId() == 0) {
            if (imageFile != null && !imageFile.isEmpty()) {
                String saved = fileStorageService.saveImage(imageFile); // trả về tên file
                category.setImage(saved); // lưu tên file vào DB
            }
            icategoryService.save(category);
            return "redirect:/admin/categories";
        }

        // CẬP NHẬT
        Category categoryOld = icategoryService.findById(category.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        categoryOld.setName(category.getName());

        if (imageFile != null && !imageFile.isEmpty()) {
            String newImg = fileStorageService.saveImage(imageFile);
            String oldImg = categoryOld.getImage();
            categoryOld.setImage(newImg);
            icategoryService.save(categoryOld);

            // xoá ảnh cũ sau khi lưu thành công
            if (oldImg != null && !oldImg.isBlank()) {
                fileStorageService.deleteImage(oldImg);
            }
        } else {
            icategoryService.save(categoryOld);
        }

        return "redirect:/admin/categories";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws IOException {
        Category categoryOld = icategoryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        String oldImage = categoryOld.getImage();
        icategoryService.deleteById(id);

        if (oldImage != null && !oldImage.isBlank()) {
            fileStorageService.deleteImage(oldImage);
        }

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
