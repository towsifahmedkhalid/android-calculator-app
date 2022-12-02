package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_textView, solution_textView;
    MaterialButton buttonC, buttonAC, buttonAdd, buttonSub, buttonDiv, buttonMultiply, buttonEqual, buttonDot, buttonBracketOpen, buttonBracketClose, button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_textView = findViewById(R.id.result_textView);
        solution_textView = findViewById(R.id.solution_textView);

        assignId(buttonC, R.id.button_c);
        assignId(buttonAC, R.id.button_ac);
        assignId(buttonAdd, R.id.button_add);
        assignId(buttonSub, R.id.button_sub);
        assignId(buttonDiv, R.id.button_div);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonEqual, R.id.button_equal);
        assignId(buttonDot, R.id.button_dot);
        assignId(buttonBracketOpen, R.id.button_bracOpen);
        assignId(buttonBracketClose, R.id.button_bracClose);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){

        MaterialButton button= (MaterialButton) view;
        String buttonText= button.getText().toString();
        String dataToCalculate = solution_textView.getText().toString();

        if(buttonText.equals("AC")){
            solution_textView.setText("");
            result_textView.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution_textView.setText(result_textView.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate = dataToCalculate + buttonText;
        }
        solution_textView.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            result_textView.setText(finalResult);
        }
    }

    String getResult(String data) {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript", 1, null).toString();
            return finalResult;
        }
        catch (Exception e){
            return "Err";
        }
    }
}