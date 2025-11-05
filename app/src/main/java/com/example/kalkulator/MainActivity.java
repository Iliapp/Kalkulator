package com.example.kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView workingTv;
    TextView res;
    String workings = "";

    Function log10 = new Function("log10", 1) {
        @Override
        public double apply(double... args) {
            return Math.log10(args[0]);
        }
    };

    Function factorial = new Function("fact", 1) {
        @Override
        public double apply(double... args) {
            double n = args[0];
            double result = 1;
            for (int i = 1; i <= n; i++) result *= i;
            return result;
        }
    };

    Function x2 = new Function("x2",1) {
        @Override
        public double apply(double... args) {
            return Math.pow(args[0],2);
        }
    };

    Function x3 = new Function("x3",1) {
        @Override
        public double apply(double... args) {
            return Math.pow(args[0],3);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initTextViews();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);



            return insets;


        });



//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initTextViews()
    }

        @Override
        protected void  onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);
            String resultText = ((TextView) findViewById(R.id.textView)).getText().toString();
            outState.putString("result", resultText);

        }

        @Override
        protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
            super.onRestoreInstanceState(savedInstanceState);
            workings = savedInstanceState.getString("result");
            TextView resText = findViewById(R.id.textView);
            resText.setText(workings);


    }





    private void initTextViews() {
        workingTv =  findViewById(R.id.textView);
        res =  findViewById(R.id.textView2);
    }

    public void setWorkings(String givenValue){
        workings = workings +  givenValue;
        workingTv.setText(workings);
    }

    private String closeOpenParentheses(String input) {
        int openCount = input.length() - input.replace("(", "").length();
        int closeCount = input.length() - input.replace(")", "").length();

        while (openCount > closeCount) {
            input += ")";
            closeCount++;
        }

        return input;
    }


    public void equalOnClick(View view) {
        try {
            String expressionString = closeOpenParentheses(workings);

            Expression expression = new ExpressionBuilder(expressionString)
                    .functions(log10,factorial,x2,x3)
                    .build();

            double result = expression.evaluate();
            res.setText(String.valueOf(result));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void clearOnClick(View view) {
        workingTv.setText("");
        workings = "";
        res.setText("");
    }

    public void clearOneOnCLick(View view) {
        if (!workings.isEmpty()) {
            workings = workings.substring(0, workings.length() - 1);
            workingTv.setText(workings);
        }
    }



    public void sevennOnClick(View view) {
        setWorkings("7");
    }


    public void eightOnClick(View view) {

        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }


    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void minusOnClick(View view) {
        setWorkings("-");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void plusOnClick(View view) {
        setWorkings("+");
    }

    public void decimalOnClick(View view) {
        setWorkings(".");
    }

    public void zeroOnClick(View view) {
        setWorkings("0");
    }

    public void divisionOnClick(View view) {
        setWorkings("/");
    }

    public void substanceOnClick(View view) {
        setWorkings("*");
    }

    public void procOnClick(View view) {
        setWorkings("%");
    }

    public void logOnClick(View view) {
        setWorkings("log10(");
    }

    public void xOnClick(View view) {
        setWorkings("fact(");
    }

    public void sqrtOnClick(View view) {
        setWorkings("sqrt(");
    }


    public void setX3(View view) {
        setWorkings("x3(");
    }

    public void setX2(View view) {
        setWorkings("x2(");
    }










//    public void pressbutton(View view) {
//
//
//    }

}