package org.example.baitaptuan5_16_9.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class FileStorageService {
    @Value("${app.upload.root}")
    private String uploadRoot;

    @Value("${app.upload.images-dir}")
    private String imagesDir;

    @Value("${app.upload.videos-dir}")
    private String videosDir;

    private Path rootPath;
    private Path imagesPath;
    private Path videosPath;

    @PostConstruct
    public void init() throws IOException {
        rootPath = Path.of(uploadRoot).toAbsolutePath().normalize();
        imagesPath = rootPath.resolve(imagesDir);
        videosPath = rootPath.resolve(videosDir);
        Files.createDirectories(imagesPath);
        Files.createDirectories(videosPath);
    }

    public String saveImage(MultipartFile file) throws IOException {
        return save(file, imagesPath);
    }

    public String saveVideo(MultipartFile file) throws IOException {
        return save(file, videosPath);
    }

    public void deleteImage(String fileName) throws IOException {
        delete(imagesPath.resolve(fileName));
    }

    public void deleteVideo(String fileName) throws IOException {
        delete(videosPath.resolve(fileName));
    }

    private String save(MultipartFile file, Path targetDir) throws IOException {
        if (file == null || file.isEmpty()) return null;

        // Lấy đuôi file an toàn
        String original = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = "";
        int i = original.lastIndexOf('.');
        if (i >= 0) ext = original.substring(i).toLowerCase();

        // Tên file duy nhất: yyyyMMddHHmmss-UUID.ext
        String unique = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + "-" + UUID.randomUUID() + ext;

        // Chặn path traversal
        if (unique.contains("..")) throw new IOException("Invalid filename");

        // Ghi file
        Path dest = targetDir.resolve(unique).normalize();
        Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
        return unique; // chỉ trả về tên file để lưu DB
    }

    private void delete(Path path) throws IOException {
        if (path == null) return;
        try {
            Files.deleteIfExists(path.normalize());
        } catch (IOException e) {
            System.err.println("Could not delete file: " + path.toString());
        }
    }
}
