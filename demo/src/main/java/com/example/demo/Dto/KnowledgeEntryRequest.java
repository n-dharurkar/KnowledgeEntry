package com.example.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
public class KnowledgeEntryRequest {
    @NotBlank
    private String type; // ARTICLE or VIDEO

    @NotBlank
    private String title;

    // Article fields
    private String content;
    private String author;

    // Video fields
    private String url;
    private Integer durationInMinutes;
}
