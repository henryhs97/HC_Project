package com.example.sibdoo.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Calculator extends AppCompatActivity {

    private static final String TOTAL_BILL = "TOTAL_BILL";
    private static final String CURRENT_TIP = "CURRENT_TIP";
    private static final String BILL_WITHOUT_TIP = "BILL_WITHOUT_TIP";

    private double billBeforeTip;
    private double tipAmount;
    private double finalBill;

    EditText billBeforeTipET;
    EditText tipAmountET;
    EditText finalBillET;

    private int[] checklistValues = new int[12];

    CheckBox friendlyCheckBox;
    CheckBox specialsCheckBox;
    CheckBox opinionCheckBox;

    RadioGroup availableRadioGroup;
    RadioButton availableBadRadio;
    RadioButton availableOKRadio;
    RadioButton availableGoodRadio;

    Spinner problemsSpinner;

    Button startChronometerButton;
    Button pauseChronometerButton;
    Button resetChronometerButton;

    Chronometer timeWaitingChronometer;

    long secondsYouWaited = 0;

    TextView timeWaitingTextView;

    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        if( savedInstanceState == null ){
            billBeforeTip = 0.0;
            tipAmount = .15;
            finalBill = 0.0;
        } else {
            billBeforeTip = savedInstanceState.getDouble(BILL_WITHOUT_TIP);
            tipAmount = savedInstanceState.getDouble(CURRENT_TIP);
            finalBill = savedInstanceState.getDouble(TOTAL_BILL);
        }

        billBeforeTipET = (EditText) findViewById(R.id.billEditText);
        tipAmountET = (EditText) findViewById(R.id.tipEditText);
        finalBillET = (EditText) findViewById(R.id.finalBillEditText);

        seekBar = (SeekBar) findViewById(R.id.SeekBar);

        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        billBeforeTipET.addTextChangedListener(billBeforeTipListener);

        friendlyCheckBox = (CheckBox) findViewById(R.id.FriendlyCheckBox);
        specialsCheckBox = (CheckBox) findViewById(R.id.SpecialCheckBox);
        opinionCheckBox = (CheckBox) findViewById(R.id.OpinionCheckBox);

        setUpIntroCheckBoxes();


        availableRadioGroup = (RadioGroup) findViewById(R.id.availableRadio);
        availableBadRadio = (RadioButton) findViewById(R.id.availableBadRadio);
        availableOKRadio = (RadioButton) findViewById(R.id.availableOKRadio);
        availableGoodRadio = (RadioButton) findViewById(R.id.availableGoodRadio);

        addChangeListenertoRadios();

        problemsSpinner = (Spinner) findViewById(R.id.problemsSpinner);

        addItemSelectedListenerToSpinner();

        startChronometerButton = (Button) findViewById(R.id.startChronometerButton);
        pauseChronometerButton = (Button) findViewById(R.id.pauseChronometerButton);
        resetChronometerButton = (Button) findViewById(R.id.resetChronometerButton);

        setButtonOnClickListener();

        timeWaitingChronometer = (Chronometer) findViewById(R.id.timeWaitingChronometer);

        timeWaitingTextView = (TextView) findViewById(R.id.timeWaitingTextView);

    }

    private TextWatcher billBeforeTipListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try{
                billBeforeTip = Double.parseDouble(charSequence.toString());
            }catch (NumberFormatException e){
                billBeforeTip = 0.0;
            }

            updateTipAndFinalBill();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putDouble(TOTAL_BILL, finalBill);
        outState.putDouble(CURRENT_TIP, tipAmount);
        outState.putDouble(BILL_WITHOUT_TIP,billBeforeTip);
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            tipAmount = (seekBar.getProgress()) * .01;
            tipAmountET.setText(String.format("%.02f", tipAmount));

            updateTipAndFinalBill();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private void updateTipAndFinalBill(){

        double tipAmount = Double.parseDouble(tipAmountET.getText().toString().replace(",", "."));


        double finalBill = billBeforeTip + (tipAmount*billBeforeTip);

        finalBillET.setText(String.format("%.02f", finalBill));
    }

    private void setUpIntroCheckBoxes() {
        friendlyCheckBox.setOnCheckedChangeListener(new CheckBox().OnCheckedChangeListener());
    //3:54 THIS IS WHERE WE LEFT OFF
    }



}
