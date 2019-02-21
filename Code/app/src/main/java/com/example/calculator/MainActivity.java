package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView operatorT;
    private EditText finalResult;
    private EditText anotherNumber;

    private Double operandNum1 = null;
    private String operator = "=";

    private static final String LAYOUT_CHANGE_STATE = "previousOperation";
    private static final String LAYOUT_CHANGE_OPERANDNUM1 = "OperandNum1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button num0 = (Button) findViewById(R.id.num0);
        Button num1 = (Button) findViewById(R.id.num1);
        Button num2 = (Button) findViewById(R.id.num2);
        Button num3 = (Button) findViewById(R.id.num3);
        Button num4 = (Button) findViewById(R.id.num4);
        Button num5 = (Button) findViewById(R.id.num5);
        Button num6 = (Button) findViewById(R.id.num6);
        Button num7 = (Button) findViewById(R.id.num7);
        Button num8 = (Button) findViewById(R.id.num8);
        Button num9 = (Button) findViewById(R.id.num9);
        Button dot = (Button) findViewById(R.id.dot);
        Button multiply = (Button) findViewById(R.id.multiply);
        Button divide = (Button) findViewById(R.id.divide);
        Button add = (Button) findViewById(R.id.add);
        Button minus = (Button) findViewById(R.id.minus);
        Button equals = (Button) findViewById(R.id.equal);

        operatorT = (TextView) findViewById(R.id.operator);
        finalResult = (EditText) findViewById(R.id.result);
        anotherNumber = (EditText) findViewById(R.id.newnum);

        Button negativeButton = (Button) findViewById(R.id.negative);

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numValueP = anotherNumber.getText().toString();
                if(numValueP.length()==0){
                    anotherNumber.setText("-");
                }
                else {
                    try{
                        Double val_Double_Check = Double.valueOf(numValueP);
                        val_Double_Check = val_Double_Check*-1;
                        anotherNumber.setText(val_Double_Check.toString());
                    }catch (NumberFormatException e){
                        anotherNumber.setText("");
                    }
                }

            }
        });

        Button clear = (Button) findViewById(R.id.clearButton);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalResult.setText("");
                operandNum1 = 0.0;
                anotherNumber.setText("");
                operatorT.setText("");
            }
        });

        // OnClickListener for number buttons
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button getNumber = (Button) v;
                anotherNumber.append(getNumber.getText().toString());
            }
        };

        // OnClick listener for operator
        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button operatorCheck = (Button) v;
                String operatorS = operatorCheck.getText().toString();
                String numValue = anotherNumber.getText().toString();

                try {

                    Double val_Double = Double.valueOf(numValue);
                    doCalculation(val_Double, operatorS);

                } catch (NumberFormatException e) {
                    anotherNumber.setText("");
                }
                operator = operatorS;
                operatorT.setText(operator);

            }
        };

        num0.setOnClickListener(numberListener);
        num1.setOnClickListener(numberListener);
        num2.setOnClickListener(numberListener);
        num3.setOnClickListener(numberListener);
        num4.setOnClickListener(numberListener);
        num5.setOnClickListener(numberListener);
        num6.setOnClickListener(numberListener);
        num7.setOnClickListener(numberListener);
        num8.setOnClickListener(numberListener);
        num9.setOnClickListener(numberListener);
        dot.setOnClickListener(numberListener);


        equals.setOnClickListener(operationListener);
        divide.setOnClickListener(operationListener);
        multiply.setOnClickListener(operationListener);
        minus.setOnClickListener(operationListener);
        add.setOnClickListener(operationListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(LAYOUT_CHANGE_STATE,operator);
        if (operandNum1!=null){
            outState.putDouble(LAYOUT_CHANGE_OPERANDNUM1,operandNum1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operator = savedInstanceState.getString(LAYOUT_CHANGE_STATE);
        operandNum1 = savedInstanceState.getDouble(LAYOUT_CHANGE_OPERANDNUM1);
        operatorT.setText(operator);
    }

    private void doCalculation(Double numValueP, String operatorP) {

        if (operandNum1 == null) {
            operandNum1 = numValueP;
        } else {

            if (operator == "=") {
                operator = operatorP;
            }
            switch (operator) {
                case "=":
                    operandNum1 = numValueP;
                    break;
                case "/":
                    if (numValueP == 0) {
                        operandNum1 = 0.0;
                    } else {
                        operandNum1 = operandNum1 / numValueP;
                    }
                    break;
                case "*":
                    operandNum1 = operandNum1 * numValueP;
                    break;
                case "-":
                    operandNum1 = operandNum1 - numValueP;
                    break;
                case "+":
                    operandNum1 = operandNum1 + numValueP;
                    break;
            }
        }

        finalResult.setText(operandNum1.toString());
        anotherNumber.setText("");
    }
}
