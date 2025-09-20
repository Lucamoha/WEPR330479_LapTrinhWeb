package org.example.baitaptuan5_16_9.controller.admin;

import lombok.RequiredArgsConstructor;
import org.example.baitaptuan5_16_9.entity.Account;
import org.example.baitaptuan5_16_9.entity.Category;
import org.example.baitaptuan5_16_9.service.IAccountService;
import org.example.baitaptuan5_16_9.storage.FileStorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService iAccountService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       Model model) {
        Page<Account> accountPage = iAccountService.findAll(PageRequest.of(page, size));
        model.addAttribute("accountPage", accountPage);
        return "admin/user/searchPaging";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("account", new Account());
        return "admin/user/addOrEdit";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Account account = iAccountService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));
        model.addAttribute("account", account);
        return "admin/user/addOrEdit";
    }

    // Lưu hoặc cập nhật
    @PostMapping("/save")
    public String save(@ModelAttribute Account account,
                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        // THÊM MỚI (id = 0)
        if (account.getId() == 0) {
            if (imageFile != null && !imageFile.isEmpty()) {
                String saved = fileStorageService.saveImage(imageFile); // trả về tên file
                account.setImage(saved); // lưu tên file vào DB
            }
            iAccountService.save(account);
            return "redirect:/admin/user";
        }

        // CẬP NHẬT
        Account accountOld = iAccountService.findById(account.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        accountOld.setUsername(account.getUsername());
        accountOld.setPassword(account.getPassword());
        accountOld.setFullName(account.getFullName());

        if (imageFile != null && !imageFile.isEmpty()) {
            String newImg = fileStorageService.saveImage(imageFile);
            String oldImg = accountOld.getImage();
            accountOld.setImage(newImg);
            iAccountService.save(accountOld);

            // xoá ảnh cũ sau khi lưu thành công
            if (oldImg != null && !oldImg.isBlank()) {
                fileStorageService.deleteImage(oldImg);
            }
        } else {
            iAccountService.save(accountOld);
        }

        return "redirect:/admin/user";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws IOException {
        Account accountOld = iAccountService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));
        String oldImage = accountOld.getImage();
        iAccountService.deleteById(id);

        if (oldImage != null && !oldImage.isBlank()) {
            fileStorageService.deleteImage(oldImage);
        }

        return "redirect:/admin/user";
    }

    // Tìm kiếm + phân trang
    @GetMapping("/search")
    public String search(@RequestParam String keyword,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size,
                         Model model) {
        Page<Account> accountPage = iAccountService.search(keyword, keyword, PageRequest.of(page, size));
        model.addAttribute("accountPage", accountPage);
        model.addAttribute("keyword", keyword);
        return "admin/user/searchPaging";
    }
}
