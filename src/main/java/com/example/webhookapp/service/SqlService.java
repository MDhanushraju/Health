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
            // Question 1 (odd): Highest salaried employee per department;
            // exclude payments on LAST day of month. Output: DEPARTMENT_NAME, SALARY, EMPLOYEE_NAME, AGE.
            return "WITH valid_payments AS ("
                    + " SELECT p.emp_id, p.amount"
                    + " FROM payments p"
                    + " WHERE p.payment_time < DATE_TRUNC('month', p.payment_time)"
                    + " + INTERVAL '1 month' - INTERVAL '1 day'"
                    + " ),"
                    + " employee_salary AS ("
                    + " SELECT e.emp_id, e.first_name, e.last_name, e.dob, e.department_id,"
                    + " SUM(vp.amount) AS total_salary"
                    + " FROM employee e"
                    + " JOIN valid_payments vp ON vp.emp_id = e.emp_id"
                    + " GROUP BY e.emp_id, e.first_name, e.last_name, e.dob, e.department_id"
                    + " ),"
                    + " ranked_employees AS ("
                    + " SELECT es.*, d.department_name,"
                    + " ROW_NUMBER() OVER (PARTITION BY es.department_id ORDER BY es.total_salary DESC) AS rn"
                    + " FROM employee_salary es"
                    + " JOIN department d ON d.department_id = es.department_id"
                    + " )"
                    + " SELECT department_name AS DEPARTMENT_NAME, total_salary AS SALARY,"
                    + " first_name || ' ' || last_name AS EMPLOYEE_NAME,"
                    + " DATE_PART('year', AGE(dob)) AS AGE"
                    + " FROM ranked_employees WHERE rn = 1";
        } else {
            // Question 2 (even): replace with actual solution from Q2 PDF
            return "SELECT 1"; // TODO: your Question 2 SQL
        }
    }
}
