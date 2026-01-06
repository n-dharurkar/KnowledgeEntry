package com.example.demo.Repository;

import com.example.demo.Model.ArticleEntry;
import com.example.demo.Model.KnowledgeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KnowledgeEntryRepository extends JpaRepository<KnowledgeEntry, Long> {
    @Query("select k from KnowledgeEntry k where lower(k.title) like lower(concat('%', :keyword, '%'))")
    List<KnowledgeEntry> searchByTitle(@Param("keyword") String keyword);
}
