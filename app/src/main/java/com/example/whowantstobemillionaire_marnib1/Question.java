package com.example.whowantstobemillionaire_marnib1;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String questionText, String option1, String option2, String option3, String option4, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = new ArrayList<>();
        this.options.add(option1);
        this.options.add(option2);
        this.options.add(option3);
        this.options.add(option4);
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}
