package com.example.webhookapp.service;

import org.springframework.stereotype.Service;

/**
 * Holds SQL solution logic. Question is known; returns final SQL query string only.
 * Odd last-two digits of regNo → Question 1, even → Question 2.
 */
@Service
public class SqlService {

    /**
     * Returns the final SQL query for the given question.
     * Replace placeholders with your actual solution from the question PDF.
     *
     * @param isQuestion1 true if last two digits of regNo are odd (Q1), false for even (Q2)
     * @return the final SQL query string
     */
    public String getFinalQuery(boolean isQuestion1) {
        if (isQuestion1) {
            // Question 1 (odd): replace with actual solution
            return "SELECT 1"; // TODO: your Question 1 SQL
        } else {
            // Question 2 (even): replace with actual solution
            return "SELECT 1"; // TODO: your Question 2 SQL
        }
    }
}
