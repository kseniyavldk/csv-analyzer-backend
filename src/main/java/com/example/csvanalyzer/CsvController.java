package com.example.csvanalyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CsvController {

    @Autowired
    private CsvService csvService;

    @Autowired
    private CsvAnalysisRepository repository;

    @PostMapping("/upload")
    public ResponseEntity<CsvStatistics> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            CsvStatistics stats = csvService.analyzeCsv(file);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/history")
    public List<CsvAnalysis> getHistory() {
        return repository.findTop10ByOrderByProcessedAtDesc();
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<CsvAnalysis> getDetails(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
