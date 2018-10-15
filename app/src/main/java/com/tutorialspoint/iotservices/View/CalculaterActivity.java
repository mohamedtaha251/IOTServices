package com.tutorialspoint.iotservices.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tutorialspoint.iotservices.R;

import javax.xml.transform.Result;

public class CalculaterActivity extends AppCompatActivity {

    String input = "";
    String input2 = "";
    int ResultValue = 0;

    TextView txResult;
    TextView txInput;
    Button BtnOne;
    Button BtnTwo;
    Button BtnThree;
    Button BtnFour;
    Button BtnFive;
    Button BtnSex;
    Button BtnSeven;
    Button BtnEight;
    Button BtnNine;
    Button BtnZero;
    Button BtnPlus;
    Button BtnMinus;
    Button BtnEqual;
    Button BtnMultiply;
    Button BtnDivide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculater);
        txResult = (TextView) findViewById(R.id.tv_screen_result);
        txInput = (TextView) findViewById(R.id.tv_screen_input);

        BtnOne = (Button) findViewById(R.id.btn_one);
        BtnTwo = (Button) findViewById(R.id.btn_two);
        BtnThree = (Button) findViewById(R.id.btn_three);
        BtnFour = (Button) findViewById(R.id.btn_four);
        BtnFive = (Button) findViewById(R.id.btn_five);
        BtnSex = (Button) findViewById(R.id.btn_six);
        BtnSeven = (Button) findViewById(R.id.btn_seven);
        BtnEight = (Button) findViewById(R.id.btn_eight);
        BtnNine = (Button) findViewById(R.id.btn_nine);
        BtnZero = (Button) findViewById(R.id.btn_zero);
        BtnPlus = (Button) findViewById(R.id.btn_plus);
        BtnMinus = (Button) findViewById(R.id.btn_minus);
        BtnEqual = (Button) findViewById(R.id.btn_equal);
        BtnMultiply = (Button) findViewById(R.id.btn_multiply);
        BtnDivide = (Button) findViewById(R.id.btn_divide);

        BtnOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("1");
                input += "1";
                input2 += "1";

            }
        });
        BtnTwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("2");
                input += "2";
                input2 += "2";

            }
        });
        BtnThree.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("3");
                input += "3";
                input2 += "3";

            }
        });
        BtnFour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("4");
                input += "4";
                input2 += "4";

            }
        });
        BtnFive.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("5");
                input += "5";
                input2 += "5";

            }
        });
        BtnSex.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("6");
                input += "6";
                input2 += "6";

            }
        });
        BtnSeven.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("7");
                input += "7";
                input2 += "7";

            }
        });
        BtnEight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("8");
                input += "8";
                input2 += "8";

            }
        });
        BtnNine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("9");
                input += "9";
                input2 += "9";
            }
        });
        BtnZero.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("0");
                input += "0";
                input2 += "0";
            }
        });
        BtnPlus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("+");
                input += "+";
                input2 += ",+";
            }
        });
        BtnMinus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("-");
                input += "-";
                input2 += ",-";

            }
        });

        BtnMultiply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("*");
                input += "*";
                input2 += ";*";
            }
        });
        BtnDivide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                txInput.append("/");
                input += "/";
                input2 += ";/";

            }
        });
        BtnEqual.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                parseResult2();
                txResult.setText(String.valueOf(ResultValue));


                //clear variables
                txInput.setText("");
                ResultValue = 0;
                input = "";
                input2 = "";

            }
        });


    }

    private void parseResult() {


        String[] splitedPlus = input.split("\\+");

        for (int i = 0; i < splitedPlus.length; i++) {

            if (!splitedPlus[i].contains("-")) {
                ResultValue = ResultValue + Integer.parseInt(splitedPlus[i]);
                continue;
            }


            String SpiltedMinus[] = splitedPlus[i].split("-");

            int tempResult = Integer.parseInt(SpiltedMinus[0]);
            for (int j = 1; j < SpiltedMinus.length; j++) {
                tempResult = tempResult - Integer.parseInt(SpiltedMinus[j]);
            }


            ResultValue = ResultValue + tempResult;
        }
    }
    private void parseResultUbdated() {



        //sperate string input to parts lika that 5,+8,-12,6
        String[] splitedStrings = input2.split(",");



            ResultValue = Integer.parseInt((splitedStrings[0]));

        //loop on other parts and apply operation based on operation
        for (int i = 1; i < splitedStrings.length; i++) {

            //apply mutiply and divide if found
            String splitedPart = splitedStrings[i];
            if (splitedPart.contains("*") || splitedPart.contains("/"))
                splitedPart = parseMuliplyAndDivide(splitedPart);

            //apply add and subtract
            if (splitedPart.charAt(0) == '+')
                ResultValue += Integer.parseInt(splitedPart.substring(1));
            else
                ResultValue -= Integer.parseInt(splitedPart.substring(1));
        }

    }

    private void parseResult2() {


        //sperate string input to parts lika that 5,+8,-12,6
        String[] splitedStrings = input2.split(",");


        //apply mutiply and divide if found
        String firstPart = splitedStrings[0];
        if (firstPart.contains("*") || firstPart.contains("/"))
            ResultValue = Integer.parseInt(parseMuliplyAndDivide(firstPart));
        else
            ResultValue = Integer.parseInt((splitedStrings[0]));

        //loop on other parts and apply operation based on operation
        for (int i = 1; i < splitedStrings.length; i++) {

            //apply mutiply and divide if found
            String splitedPart = splitedStrings[i];
            if (splitedPart.contains("*") || splitedPart.contains("/"))
                splitedPart = parseMuliplyAndDivide(splitedPart);

            //apply add and subtract
            if (splitedPart.charAt(0) == '+')
                ResultValue += Integer.parseInt(splitedPart.substring(1));
            else
                ResultValue -= Integer.parseInt(splitedPart.substring(1));
        }

    }

    private String parseMuliplyAndDivide(String part) {
        int tempResultValue;
        //sperate string input to parts lika that 5;*8;/12;*6
        String[] splitedStrings2 = input2.split(";");

        //put first number in result
        tempResultValue = Integer.parseInt((splitedStrings2[0]));

        //loop on other parts and apply operation based on operation
        for (int i = 1; i < splitedStrings2.length; i++) {
            if (splitedStrings2[i].charAt(0) == '*')
                tempResultValue *= Integer.parseInt(splitedStrings2[i].substring(1));
            else
                tempResultValue /= Integer.parseInt(splitedStrings2[i].substring(1));
        }
        return String.valueOf(tempResultValue);
    }
}
