package com.example.csvanalyzer;

import java.util.List;

public class CsvStatistics {
    private int totalLines;
    private int invalidLines;
    private double min;
    private double max;
    private double mean;
    private double stdDev;
    private int uniqueValues;
    private List<String> invalidLinesDetails;

    public CsvStatistics(int totalLines, int invalidLines, double min, double max, double mean, double stdDev, int uniqueValues) {
        this.totalLines = totalLines;
        this.invalidLines = invalidLines;
        this.min = min;
        this.max = max;
        this.mean = mean;
        this.stdDev = stdDev;
        this.uniqueValues = uniqueValues;
    }

    public int getTotalLines() { return totalLines; }
    public int getInvalidLines() { return invalidLines; }
    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getMean() { return mean; }
    public double getStdDev() { return stdDev; }
    public int getUniqueValues() { return uniqueValues; }
    public void setInvalidLinesDetails(List<String> details) {
        this.invalidLinesDetails = details;
    }

    public List<String> getInvalidLinesDetails() {
        return invalidLinesDetails;
    }
}
