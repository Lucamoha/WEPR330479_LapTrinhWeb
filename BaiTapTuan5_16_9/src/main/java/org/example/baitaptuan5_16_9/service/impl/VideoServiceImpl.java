package org.example.baitaptuan5_16_9.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.baitaptuan5_16_9.entity.Video;
import org.example.baitaptuan5_16_9.repository.VideoRepository;
import org.example.baitaptuan5_16_9.service.IVideoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements IVideoService {
    private final VideoRepository videoRepository;

    @Override
    public Video save(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public Optional<Video> findById(int id) {
        return videoRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        videoRepository.deleteById(id);
    }

    @Override
    public Page<Video> findAll(Pageable pageable) {
        return videoRepository.findAll(pageable);
    }

    @Override
    public Page<Video> search(String title, String description, Pageable pageable) {
        return videoRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(title, description, pageable);
    }
}
