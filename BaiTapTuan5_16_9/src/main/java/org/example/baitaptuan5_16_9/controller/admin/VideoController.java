package org.example.baitaptuan5_16_9.controller.admin;

import lombok.RequiredArgsConstructor;
import org.example.baitaptuan5_16_9.entity.Video;
import org.example.baitaptuan5_16_9.service.IVideoService;
import org.example.baitaptuan5_16_9.storage.FileStorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin/video")
@RequiredArgsConstructor
public class VideoController {
    private final IVideoService iVideoService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       Model model) {
        Page<Video> videoPage = iVideoService.findAll(PageRequest.of(page, size));
        model.addAttribute("videoPage", videoPage);
        return "admin/video/searchPaging";
    }

    // Form thêm mới
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("video", new Video());
        return "admin/video/addOrEdit";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Video video = iVideoService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Video ID:" + id));
        model.addAttribute("video", video);
        return "admin/video/addOrEdit";
    }

    // Lưu hoặc cập nhật
    @PostMapping("/save")
    public String save(@ModelAttribute Video video,
                       @RequestParam(value = "videoFileUpload", required = false) MultipartFile videoFileUpload) throws IOException {
        // THÊM MỚI (id = 0)
        if (video.getId() == 0) {
            if (videoFileUpload != null && !videoFileUpload.isEmpty()) {
                String saved = fileStorageService.saveVideo(videoFileUpload); // trả về tên file
                video.setUrl(saved); // lưu tên file vào DB
            }
            iVideoService.save(video);
            return "redirect:/admin/video";
        }

        // CẬP NHẬT
        Video videoOld = iVideoService.findById(video.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Video ID"));

        videoOld.setTitle(video.getTitle());
        videoOld.setDescription(video.getDescription());

        if (videoFileUpload != null && !videoFileUpload.isEmpty()) {
            String saved = fileStorageService.saveVideo(videoFileUpload); // trả về tên file
            String old = videoOld.getUrl();
            videoOld.setUrl(saved);
            iVideoService.save(videoOld);

            // xoá ảnh cũ sau khi lưu thành công
            if (old != null && !old.isBlank()) {
                fileStorageService.deleteVideo(old);
            }
        } else {
            iVideoService.save(videoOld);
        }

        return "redirect:/admin/video";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) throws IOException {
        Video videoOld = iVideoService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid video ID"));
        String oldUrl = videoOld.getUrl();
        iVideoService.deleteById(id);

        if (oldUrl != null && !oldUrl.isBlank()) {
            fileStorageService.deleteVideo(oldUrl);
        }

        return "redirect:/admin/video";
    }

    // Tìm kiếm + phân trang
    @GetMapping("/search")
    public String search(@RequestParam String keyword,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size,
                         Model model) {
        Page<Video> videoPage = iVideoService.search(keyword, keyword, PageRequest.of(page, size));
        model.addAttribute("videoPage", videoPage);
        model.addAttribute("keyword", keyword);
        return "admin/video/searchPaging";
    }
}
