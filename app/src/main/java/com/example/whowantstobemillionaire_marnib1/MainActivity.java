package com.example.whowantstobemillionaire_marnib1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.graphics.drawable.Drawable;

import android.content.DialogInterface;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView buttonOption1, buttonOption2, buttonOption3, buttonOption4;
    private Button buttonNext;
    private TextView textViewScore;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int totalScore = 0;

    private int selected=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        buttonOption1 = findViewById(R.id.textViewOptionA);
        buttonOption2 = findViewById(R.id.textViewOptionB);
        buttonOption3 = findViewById(R.id.textViewOptionC);
        buttonOption4 = findViewById(R.id.textViewOptionD);
        buttonNext = findViewById(R.id.buttonSubmit);
        textViewScore = findViewById(R.id.textViewScore);

        // Initialize questionList with 10 questions
        questionList = new ArrayList<>();
        initializeQuestions();

        displayQuestion();

        buttonOption1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                selected =0;
                handleOptionClicked(buttonOption1);
            }
        });

        buttonOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=1;
                handleOptionClicked(buttonOption2);
            }
        });

        buttonOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=2;
                handleOptionClicked(buttonOption3);
            }
        });

        buttonOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected=3;
                handleOptionClicked(buttonOption4);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonOption1.setBackground(getDrawable(R.drawable.option_background));
                buttonOption2.setBackground(getDrawable(R.drawable.option_background));
                buttonOption3.setBackground(getDrawable(R.drawable.option_background));
                buttonOption4.setBackground(getDrawable(R.drawable.option_background));
                checkAnswer(selected);
                currentQuestionIndex++;
                if (currentQuestionIndex < questionList.size()) {
                    displayQuestion();
                } else {
                    endGame();
                }

            }
        });
    }

    private void initializeQuestions() {
        questionList.add(new Question("What is the capital of France?", "Paris", "London", "Berlin", "Rome", 0));
        questionList.add(new Question("Who wrote 'Romeo and Juliet'?", "William Shakespeare", "Jane Austen", "Charles Dickens", "Leo Tolstoy", 0));
        questionList.add(new Question("Which planet is known as the Red Planet?", "Mars", "Venus", "Jupiter", "Saturn", 0));
        questionList.add(new Question("What is the chemical symbol for water?", "H2O", "CO2", "NaCl", "O2", 0));
        questionList.add(new Question("Who painted the Mona Lisa?", "Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo", 0));
        questionList.add(new Question("What is the largest ocean on Earth?", "Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean", 0));
        questionList.add(new Question("Which country is famous for its pyramids?", "Egypt", "Italy", "Greece", "Mexico", 0));
        questionList.add(new Question("Who invented the telephone?", "Alexander Graham Bell", "Thomas Edison", "Albert Einstein", "Nikola Tesla", 0));
        questionList.add(new Question("What is the national sport of Japan?", "Sumo wrestling", "Baseball", "Karate", "Judo", 0));
        questionList.add(new Question("What is the tallest mountain in the world?", "Mount Everest", "K2", "Kangchenjunga", "Lhotse", 0));
    }
    private void displayQuestion() {
        Question currentQuestion = questionList.get(currentQuestionIndex);
        textViewQuestion.setText(currentQuestion.getQuestionText());
        buttonOption1.setText(currentQuestion.getOptions().get(0));
        buttonOption2.setText(currentQuestion.getOptions().get(1));
        buttonOption3.setText(currentQuestion.getOptions().get(2));
        buttonOption4.setText(currentQuestion.getOptions().get(3));
        textViewScore.setText("You Earned : $" + totalScore);
    }
    private void checkAnswer(int selectedOptionIndex) {
        Question currentQuestion = questionList.get(currentQuestionIndex);
        if (selectedOptionIndex == currentQuestion.getCorrectOptionIndex()) {
            totalScore += calculateScore();
            Toast.makeText(this, "This is the CORRECT Answer! you earned "+totalScore, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect Answer! you earned so far "+totalScore, Toast.LENGTH_SHORT).show();
        }
    }
    private int calculateScore() {
        // Calculate score based on question difficulty, time taken, etc.
        int[] numbers = {100,150,250,500,1000,5000,10000,33000,25000,25000};
        return numbers[currentQuestionIndex]; // For simplicity, returning a fixed score for now
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Over");
        if(totalScore<100000){
            builder.setMessage("Sorry you lost the game. you can exit or retake the game.");
        }
        else{
            builder.setMessage("Congrats!! Your Final Score: " + totalScore);
        }
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restartGame();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void restartGame() {
        currentQuestionIndex = 0;
        totalScore = 0;
        displayQuestion();
    }
    private void handleOptionClicked(TextView optionTextView) {
        // Reset background color of all options
        buttonOption1.setBackground(getDrawable(R.drawable.option_background));
        buttonOption2.setBackground(getDrawable(R.drawable.option_background));
        buttonOption3.setBackground(getDrawable(R.drawable.option_background));
        buttonOption4.setBackground(getDrawable(R.drawable.option_background));

        // Highlight the clicked option
        Drawable highlight = getResources().getDrawable(R.drawable.option_highlight_background);
        optionTextView.setBackground(highlight);
    }
}

