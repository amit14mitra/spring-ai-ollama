package com.example.spring_ai_ollama.tools;

import org.springframework.ai.tool.annotation.Tool;

public class EmployeeTool {

    @Tool(description = "Get employee information using employee ID")
    public String getEmployee(String employeeId) {

        // In real application, call DB/repository here
        if (employeeId.equals("101")) {
            return "Employee: Rahul, Role: Java Developer, Experience: 5 years";
        }else if (employeeId.equals("102")) {
            return "Employee: Priya, Role: Tester, Experience: 4 years";
        } else if (employeeId.equals("103")) {
            return "Employee: Amit, Role: Dev, Experience: 6 years";
        }

        return "Employee not found";
    }
}