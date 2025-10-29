package com.example.csvanalyzer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CsvAnalysisRepository extends JpaRepository<CsvAnalysis, Long> {
    List<CsvAnalysis> findTop10ByOrderByProcessedAtDesc();
}
