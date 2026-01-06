package com.example.demo.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@DiscriminatorValue("VIDEO")
@Data
public class VideoEntry extends KnowledgeEntry {

    @NotBlank
    private String url;


    private int durationInMinutes;
}
