package com.example.demo.Service;

import com.example.demo.Dto.KnowledgeEntryRequest;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Model.ArticleEntry;
import com.example.demo.Model.KnowledgeEntry;
import com.example.demo.Model.VideoEntry;
import com.example.demo.Repository.KnowledgeEntryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class KnowledgeTest {
    @Mock
    private KnowledgeEntryRepository repository;

    @InjectMocks
    private KnowledgeEntryService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ----------------- CREATE -----------------
    @Test
    void createArticle_shouldReturnSavedArticle() {
        KnowledgeEntryRequest req = new KnowledgeEntryRequest();
        req.setType("ARTICLE");
        req.setTitle("Test Article");
        req.setContent("Some content");
        req.setAuthor("Niraj");

        ArticleEntry saved = new ArticleEntry();
        saved.setId(1L);
        saved.setTitle(req.getTitle());
        saved.setContent(req.getContent());
        saved.setAuthor(req.getAuthor());

        when(repository.save(any(ArticleEntry.class))).thenReturn(saved);

        KnowledgeEntry result = service.create(req);

        assertTrue(result instanceof ArticleEntry);
        assertEquals("Test Article", result.getTitle());
        verify(repository, times(1)).save(any(ArticleEntry.class));
    }

    @Test
    void createVideo_shouldReturnSavedVideo() {
        KnowledgeEntryRequest req = new KnowledgeEntryRequest();
        req.setType("VIDEO");
        req.setTitle("Test Video");
        req.setUrl("https://example.com");
        req.setDurationInMinutes(10);

        VideoEntry saved = new VideoEntry();
        saved.setId(2L);
        saved.setTitle(req.getTitle());
        saved.setUrl(req.getUrl());
        saved.setDurationInMinutes(req.getDurationInMinutes());

        when(repository.save(any(VideoEntry.class))).thenReturn(saved);

        KnowledgeEntry result = service.create(req);

        assertTrue(result instanceof VideoEntry);
        assertEquals("Test Video", result.getTitle());
        verify(repository, times(1)).save(any(VideoEntry.class));
    }

    @Test
    void createInvalidType_shouldThrowException() {
        KnowledgeEntryRequest req = new KnowledgeEntryRequest();
        req.setType("INVALID");

        assertThrows(IllegalArgumentException.class, () -> service.create(req));
        verify(repository, never()).save(any());
    }

    // ----------------- GET BY ID -----------------
    @Test
    void getById_existingId_shouldReturnEntry() {
        ArticleEntry article = new ArticleEntry();
        article.setId(1L);
        article.setTitle("Test Article");

        when(repository.findById(1L)).thenReturn(Optional.of(article));

        KnowledgeEntry result = service.getById(1L);
        assertEquals("Test Article", result.getTitle());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void getById_nonExistingId_shouldThrowResourceNotFound() {
        when(repository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getById(999L));
        verify(repository, times(1)).findById(999L);
    }

    // ----------------- SEARCH -----------------
    @Test
    void search_shouldReturnMatchingEntries() {
        ArticleEntry article = new ArticleEntry();
        article.setTitle("Spring Boot Article");

        when(repository.searchByTitle("Spring")).thenReturn(List.of(article));

        List<KnowledgeEntry> results = service.search("Spring");
        assertEquals(1, results.size());
        assertEquals("Spring Boot Article", results.get(0).getTitle());
        verify(repository, times(1)).searchByTitle("Spring");
    }
}
