package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView result;
    String operationButton;
    Boolean isOperation = false;
    int num1 = 0;
    boolean resetResultOnNextInput = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        result = findViewById((R.id.textViewResult));
        result.setText("");
    }




    public void ActsFunc(View view) {
        String currentText = result.getText().toString();

        if (currentText.isEmpty()) {
            return;
        }

        if (isOperation) {
            EqualFunc(view);
        }

        try {
            Button oper = (Button) view;
            operationButton = oper.getText().toString();
            num1 = Integer.parseInt(currentText);
            result.setText("");
            resetResultOnNextInput = false;
            isOperation = true;
        } catch (NumberFormatException e) {
            result.setText(R.string.error);
            Toast.makeText(this, "illegal number", Toast.LENGTH_SHORT).show();
        }
    }

    public void funcButtonClick(View view) {
        if (resetResultOnNextInput) {
            result.setText("");
            resetResultOnNextInput = false;
        }
        Button button = (Button) view;
        result.append(button.getText().toString());
    }


    public void EqualFunc(View view) {
        String currentText = result.getText().toString();

        if (currentText.isEmpty()) {
            return;
        }

        try {
            int num2 = Integer.parseInt(currentText);
            isOperation = false;

            switch (operationButton) {
                case "+":
                    result.setText(String.valueOf(num1 + num2));
                    resetResultOnNextInput = true;
                    break;
                case "-":
                    result.setText(String.valueOf(num1 - num2));
                    resetResultOnNextInput = true;
                    break;
                case "X":
                    result.setText(String.valueOf(num1 * num2));
                    resetResultOnNextInput = true;
                    break;
                case "/":
                    if (num2 == 0) {
                        result.setText("Division by 0");
                        Toast.makeText(this, "U cannot divide by 0", Toast.LENGTH_SHORT).show();
                    } else {
                        result.setText(String.valueOf(num1 / num2));
                        resetResultOnNextInput = true;
                    }
                    break;
                default:
                    result.setText(R.string.error);
                    resetResultOnNextInput = true;
                    Toast.makeText(this, "unrecognize operation", Toast.LENGTH_SHORT).show();
                    break;
            }

        } catch (NumberFormatException e) {
            result.setText(R.string.error);
            Toast.makeText(this, "illegal number", Toast.LENGTH_SHORT).show();
        }
    }

    public void PlusMinusFunc(View view) {
        String currentText = result.getText().toString();

        if (currentText.isEmpty()) {
            return;
        }

        try {
            int num = Integer.parseInt(currentText);
            result.setText(String.valueOf(num * -1));
        } catch (NumberFormatException e) {
            result.setText(R.string.error);
            Toast.makeText(this, "illegal number", Toast.LENGTH_SHORT).show();
        }
    }

    public void ClearFunc(View view) {
        result.setText("");
        isOperation = false;
    }

    public void ClearOneCharFunc(View view) {
        String currentText = result.getText().toString();

        if (currentText.isEmpty()) {
            return;
        }

        result.setText(currentText.substring(0, currentText.length() - 1));
    }
}
