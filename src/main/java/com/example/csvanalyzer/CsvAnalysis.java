package com.example.csvanalyzer;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CsvAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private long fileSize;
    private LocalDateTime processedAt;

    private int totalLines;
    private int invalidLines;
    private double min;
    private double max;
    private double mean;
    private double stdDev;
    private int uniqueValues;

    private String filePath;

    public CsvAnalysis() {}
    public CsvAnalysis(String fileName, long fileSize, LocalDateTime processedAt,
                       int totalLines, int invalidLines, double min, double max,
                       double mean, double stdDev, int uniqueValues, String filePath) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.processedAt = processedAt;
        this.totalLines = totalLines;
        this.invalidLines = invalidLines;
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.stdDev = stdDev;
        this.uniqueValues = uniqueValues;
        this.filePath = filePath;
    }

    public Long getId() { return id; }
    public String getFileName() { return fileName; }
    public long getFileSize() { return fileSize; }
    public LocalDateTime getProcessedAt() { return processedAt; }
    public int getTotalLines() { return totalLines; }
    public int getInvalidLines() { return invalidLines; }
    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getMean() { return mean; }
    public double getStdDev() { return stdDev; }
    public int getUniqueValues() { return uniqueValues; }
    public String getFilePath() { return filePath; }

    public void setId(Long id) { this.id = id; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setFileSize(long fileSize) { this.fileSize = fileSize; }
    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }
    public void setTotalLines(int totalLines) { this.totalLines = totalLines; }
    public void setInvalidLines(int invalidLines) { this.invalidLines = invalidLines; }
    public void setMin(double min) { this.min = min; }
    public void setMax(double max) { this.max = max; }
    public void setMean(double mean) { this.mean = mean; }
    public void setStdDev(double stdDev) { this.stdDev = stdDev; }
    public void setUniqueValues(int uniqueValues) { this.uniqueValues = uniqueValues; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}
