package com.example.android.quiz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int score = 0;

    /**
     * This method is called when the SUBMIT button is clicked.
     */
    public void submit(View view) {

        int Q1Results = 0;
        int Q2Results = 0;
        int Q3Results = 0;
        int Q4Results = 0;

        //Store participants name in "participantName" variable
        EditText getName = (EditText)findViewById(R.id.name);
        String participantName = getName.getText().toString();

        //Checks if participant checked checkbox one, two, or three of question one
        CheckBox q1one = (CheckBox) findViewById(R.id.qone1);
        boolean checkBoxOne1 = q1one.isChecked();

        //Checks if participant checked checkbox two of question two
        CheckBox q1two = (CheckBox) findViewById(R.id.qone2);
        boolean checkBoxOne2 = q1two.isChecked();

        //Checks if participant checked checkbox three of question three
        CheckBox q1three = (CheckBox) findViewById(R.id.qone3);
        boolean checkBoxOne3 = q1three.isChecked();

        EditText getAnswerQ3 = (EditText)view.findViewById(R.id.q3);
        String q3Input = getAnswerQ3.getText().toString();

        //Evaluates question one inputs and stores question one result
        questionOneCheck(checkBoxOne1, checkBoxOne2, checkBoxOne3);

        //Evaluates question two inputs and stores question two result
        questionTwoCheck();

        //Evaluates question three inputs and stores question three result
        questionThreeCheck(q3Input);

        //Evaluates question four inputs and stores question four result
        questionFourCheck();

        //Summarizes results of the quiz
        String triviaResults = checkAnswers(Q1Results, Q2Results, Q3Results, Q4Results, participantName);

        displayMessage(triviaResults);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_TEXT, triviaResults);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Trivia results for " +participantName);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView SummaryTextView = (TextView) findViewById(R.id.summary);
        SummaryTextView.setText(message);
    }

    private String checkAnswers (int checkQ1, int checkQ2, int checkQ3, int checkQ4, String name) {

        score = checkQ1 +checkQ2 + checkQ3 + checkQ4;

        String summary = name;
        summary += ", You got " + score ;
        summary += " of 4 questions correct!";
        summary += "\nYour results will be sent to the email your provided.";

        return summary;
    }

    private int questionOneCheck (boolean checkBoxOne1, boolean checkBoxOne2, boolean checkBoxOne3) {

        int Q1Results = 0;

        if(checkBoxOne2 && checkBoxOne3){
            CharSequence text = "You've reached the center of the lollipop! Correct!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(MainActivity.this, text, duration);
            toast.show();

            Q1Results = 1;
        }

        if(checkBoxOne1){
            CharSequence text = "Nope more licks needed!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(MainActivity.this, text, duration);
            toast.show();

            Q1Results = 0;
        }

        else {
            CharSequence text = "Nope more licks needed!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(MainActivity.this, text, duration);
            toast.show();

            Q1Results = 0;
        }

        return Q1Results;
    }


    public void questionTwoCheck (View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.qtwo1:
                if (checked) {

                    Toast toast = Toast.makeText(MainActivity.this, "No juice! Keep peeling away!", Toast.LENGTH_SHORT);
                    toast.show();

                    break;
                }

            case R.id.qtwo2:
                if (checked) {

                    Toast toast = Toast.makeText(MainActivity.this, "Yes! You a deserve a cold one!", Toast.LENGTH_SHORT);
                    toast.show();

                    break;
                }

            case R.id.qtwo3:
                if (checked) {

                    Toast toast = Toast.makeText(MainActivity.this, "It's not coffee, but you can get it with and without caffeine.", Toast.LENGTH_SHORT);
                    toast.show();
                    
                    break;
                }
        }

    }

    private int questionThreeCheck (String q3Answer) {
        int Q3Results = 0;
        String String ="blank";

        if (q3Answer == "jupiter" || q3Answer == "Jupiter") {
            Toast toast = Toast.makeText(MainActivity.this, "The force is strong with this one, nice work!", Toast.LENGTH_LONG);
            toast.show();
            Q3Results = 1;

            }
            else {
                Toast toast = Toast.makeText(MainActivity.this, "Keep trying, the answer has a nice 'ring' to it.", Toast.LENGTH_SHORT);
                toast.show();
            Q3Results = 0;
        }

        return Q3Results;
    }

    private int questionFourCheck (View view) {
        int Q4Results = 0;
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.qfour1:
                if (checked) {

                    CharSequence text = "Sorry but that's incorrect, I'm not kitten with you.";

                    Toast toast = Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT);
                    toast.show();

                    Q4Results = 0;
                    break;
                }

            case R.id.qfour2:
                if (checked) {

                    CharSequence text = "I PINKy swear to you that this is the right answer! Nice!";

                    Toast toast = Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT);
                    toast.show();

                    Q4Results = 1;
                    break;
                }

            case R.id.qfour3:
                if (checked) {

                    CharSequence text = "I'm furly sure this isn't right... Keep trying.";

                    Toast toast = Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT);
                    toast.show();

                    Q4Results = 0;
                    break;
                }
        }
        return Q4Results;
    }

}
