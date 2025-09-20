package org.example.baitaptuan5_16_9.service;

import org.example.baitaptuan5_16_9.entity.Account;
import org.example.baitaptuan5_16_9.entity.Category;
import org.example.baitaptuan5_16_9.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IVideoService {
    Video save(Video video);
    Optional<Video> findById(int id);
    void deleteById(int id);
    Page<Video> findAll(Pageable pageable);
    Page<Video> search(String title, String description, Pageable pageable);
}
