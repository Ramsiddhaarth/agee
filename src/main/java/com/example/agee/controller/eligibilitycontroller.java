package com.example.agee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class eligibilitycontroller {

    // Serves the initial form page
    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    // Handles the form submission logic
    @PostMapping("/checkEligibility")
    public String checkEligibility(@RequestParam("age") int age, Model model) {
        String message;
        boolean isEligible = false;

        if (age < 0) {
            message = "❌ Invalid input: Age cannot be negative!";
        } else if (age >= 18) {
            message = "✅ You are eligible to vote! Make your voice heard.";
            isEligible = true;
        } else {
            int yearsLeft = 18 - age;
            message = "⏳ You are not eligible to vote yet. You must wait " + yearsLeft + " more year(s).";
        }

        // Pass results back to the frontend template
        model.addAttribute("message", message);
        model.addAttribute("isEligible", isEligible);
        model.addAttribute("enteredAge", age);

        return "index";
    }
}
