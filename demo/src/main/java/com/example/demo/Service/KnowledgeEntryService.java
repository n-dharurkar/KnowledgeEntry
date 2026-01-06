package com.example.demo.Service;

import com.example.demo.Dto.KnowledgeEntryRequest;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Model.ArticleEntry;
import com.example.demo.Model.KnowledgeEntry;
import com.example.demo.Model.VideoEntry;
import com.example.demo.Repository.KnowledgeEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeEntryService {

    private final KnowledgeEntryRepository repository;

    public KnowledgeEntryService(KnowledgeEntryRepository repository) {
        this.repository = repository;
    }

    public KnowledgeEntry create(KnowledgeEntryRequest req) {
        if ("ARTICLE".equals(req.getType())) {
            ArticleEntry a = new ArticleEntry();
            a.setTitle(req.getTitle());
            a.setContent(req.getContent());
            a.setAuthor(req.getAuthor());
            return repository.save(a);
        }

        if ("VIDEO".equals(req.getType())) {
            VideoEntry v = new VideoEntry();
            v.setTitle(req.getTitle());
            v.setUrl(req.getUrl());
            v.setDurationInMinutes(req.getDurationInMinutes());
            return repository.save(v);
        }

        throw new IllegalArgumentException("Invalid knowledge type");
    }

    public KnowledgeEntry getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entry not found"));
    }

    public List<KnowledgeEntry> search(String keyword) {
        return repository.searchByTitle(keyword);
    }
}
