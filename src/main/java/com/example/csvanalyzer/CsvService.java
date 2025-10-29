package com.example.csvanalyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CsvService {

    @Autowired
    private CsvAnalysisRepository repository;

    public CsvStatistics analyzeCsv(MultipartFile file) throws Exception {

        int totalLines = 0;
        int invalidLines = 0;
        Set<Double> uniqueValues = new HashSet<>();
        double sum = 0;
        double sumSquares = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        Path tempFile = Files.createTempFile("csv_", ".tmp");
        Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Files.newInputStream(tempFile), StandardCharsets.UTF_8))) {

            String header = reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                totalLines++;
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    invalidLines++;
                    continue;
                }

                try {
                    double value = Double.parseDouble(parts[1]);
                    sum += value;
                    sumSquares += value * value;
                    min = Math.min(min, value);
                    max = Math.max(max, value);
                    uniqueValues.add(value);
                } catch (NumberFormatException e) {
                    invalidLines++;
                }
            }
        }

        double mean = sum / (totalLines - invalidLines);
        double stdDev = Math.sqrt(sumSquares / (totalLines - invalidLines) - mean * mean);

        CsvStatistics stats = new CsvStatistics(totalLines, invalidLines, min, max, mean, stdDev, uniqueValues.size());

        CsvAnalysis analysis = new CsvAnalysis(
                file.getOriginalFilename(),
                file.getSize(),
                LocalDateTime.now(),
                stats.getTotalLines(),
                stats.getInvalidLines(),
                stats.getMin(),
                stats.getMax(),
                stats.getMean(),
                stats.getStdDev(),
                stats.getUniqueValues(),
                tempFile.toString()
        );
        repository.save(analysis);

        List<CsvAnalysis> all = repository.findAll();
        if (all.size() > 10) {
            all.stream()
                    .sorted((a,b) -> a.getProcessedAt().compareTo(b.getProcessedAt()))
                    .limit(all.size() - 10)
                    .forEach(repository::delete);
        }

        return stats;
    }
}
