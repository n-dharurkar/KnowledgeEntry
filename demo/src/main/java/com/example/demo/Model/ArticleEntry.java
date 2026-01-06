package com.example.demo.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@DiscriminatorValue("ARTICLE")
@Data
public class ArticleEntry extends KnowledgeEntry {

    @NotBlank
    private String content;


    @NotBlank
    private String author;
}
