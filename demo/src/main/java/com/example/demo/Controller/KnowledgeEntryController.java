package com.example.demo.Controller;

import com.example.demo.Dto.KnowledgeEntryRequest;
import com.example.demo.Model.KnowledgeEntry;
import com.example.demo.Service.KnowledgeEntryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge")
public class KnowledgeEntryController {

    private final KnowledgeEntryService service;

    public KnowledgeEntryController(KnowledgeEntryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody KnowledgeEntryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping("/{id}")
    public KnowledgeEntry get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<KnowledgeEntry> search(@RequestParam String keyword) {
        return service.search(keyword);
    }
}
