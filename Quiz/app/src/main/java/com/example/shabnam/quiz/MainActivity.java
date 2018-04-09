package com.example.shabnam.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        //Question 1

    public void onfirstQuestionRbClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Switch Case to check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_two:
                if (checked)
                    break;
            case R.id.rb_eight:
                if (checked)
                    score = score + 1;
                break;
            case R.id.rb_twenty:
                if (checked)
                    break;
            default:
                score = 0;
                break;
        }
    }

    //Question 4
    public void onfourthQuestionRbClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Switch Case to check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb_four_yes:
                if (checked)
                    score = score + 1;
                break;
            case R.id.rb_four_no:
                if (checked)
                    score = score + 0;
                break;
            default:
                score = 0;
                break;
        }
    }

    public void submitQuiz(View view) {

        String str_toast_score = getString(R.string.toast_score);
        String str_by_four = getString(R.string.by_four);

        //Question 2
        CheckBox textInSp = (CheckBox) findViewById(R.id.text_in_sp);
        boolean selectedTextInSp = textInSp.isChecked();

        CheckBox lengthInDp = (CheckBox) findViewById(R.id.length_in_dp);
        boolean selectedLengthInDp = lengthInDp.isChecked();

        CheckBox noneOfTheAbove = (CheckBox) findViewById(R.id.none_of_the_above);
        boolean selectedNoneOfTheAbove = noneOfTheAbove.isChecked();

        if (selectedNoneOfTheAbove) {
            score = score + 0;
        } else if (selectedTextInSp && selectedLengthInDp) {
            score = score + 1;
        } else {
            score = score + 0;
        }

        //Question 3
        EditText inputAns = (EditText) findViewById(R.id.quiz_3_text);
        String enteredAns = inputAns.getText().toString();

        //If No text is entered then updating score to 0 and skip checking
        if (inputAns.getText().toString().trim().length() == 0) {
            score = score + 0;
        } else {

            //Converting to boolean to check for String or Integer
            boolean integerCheck = TextUtils.isDigitsOnly(inputAns.getText());

            if (integerCheck) {
                //String to integer conversion
                int numericValue = Integer.parseInt(enteredAns);

                if (numericValue == 4) {
                    score = score + 1;
                } else if (numericValue < 4 || numericValue > 10) {
                    score = score + 0;
                }
            } else {
                //Check for Lower Case and Upper Case string entered
                String uppercaseAnsThree = enteredAns.toUpperCase();
                String lowercaseAnsThree = enteredAns.toLowerCase();

                if (enteredAns == uppercaseAnsThree || enteredAns == lowercaseAnsThree) {
                    score = score + 1;
                } else {
                    score = score + 0;
                }
            }
        }

        //Toast the Final Score in the Display below
        Toast.makeText(getApplicationContext(), str_toast_score + score + str_by_four, Toast.LENGTH_SHORT).show();

        //Reset all values/boxes after Submit
        resetAll();
    }

    private void resetAll() {
        score = 0;

        //Clearing the Radio Boxes for Que 1 and 4
        RadioGroup radioGroupOne = findViewById(R.id.rg_one);
        radioGroupOne.clearCheck();

        RadioGroup radioGroupFour = findViewById(R.id.rg_four);
        radioGroupFour.clearCheck();

        //Clearing the Check Boxes for Que 2
        CheckBox textInDp = (CheckBox) findViewById(R.id.text_in_sp);
        if (textInDp.isChecked()) {
            textInDp.setChecked(false);
        }

        CheckBox lengthInSp = (CheckBox) findViewById(R.id.length_in_dp);
        if (lengthInSp.isChecked()) {
            lengthInSp.setChecked(false);
        }

        CheckBox noneOfTheAbove = (CheckBox) findViewById(R.id.none_of_the_above);
        if (noneOfTheAbove.isChecked()) {
            noneOfTheAbove.setChecked(false);
        }

        //Clearing the Edit Text Field for Que 3
        EditText inputAns = (EditText) findViewById(R.id.quiz_3_text);
        inputAns.getText().clear();
    }
}