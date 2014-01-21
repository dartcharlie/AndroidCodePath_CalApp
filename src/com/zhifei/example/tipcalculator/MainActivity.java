package com.zhifei.example.tipcalculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText etTotalAmount;
	private TextView tvTipAmount;
	private double totalAmount;
	private String tipAmountText;
	private final int REQUEST_CODE = 86;
	private Spinner spinPercentage;
	private ArrayAdapter<CharSequence> sPercentageAdapter;
	
	private final String TIP_AMOUNT_DEFAULT = "0.00";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);
        etTotalAmount = (EditText) findViewById(R.id.etTotalAmount);
        etTotalAmount.addTextChangedListener(new totalAmountWatcher());
        tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);
        spinPercentage = (Spinner) findViewById(R.id.spinPercentage);
        sPercentageAdapter = ArrayAdapter.createFromResource(this, R.array.percentage_array, android.R.layout.simple_spinner_dropdown_item);
       
        spinPercentage.setAdapter(sPercentageAdapter);
        
        setupSpinPercentageListener();
        setupTipAmountListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private float getTotalAmount(){
    	String fieldValue = etTotalAmount.getText().toString();
    	float ret = 0f;
    	try{
    		ret = Float.parseFloat(fieldValue);
    	} catch(NumberFormatException e){
    		e.printStackTrace();
    	}
    	return ret;
    }
    
    @SuppressLint("DefaultLocale")
	private String getTipAmountText(){
    	return tvTipAmount.getText().toString();
    }
    
    public void onClickBtn10p(View v){
    	totalAmount = getTotalAmount();
    	tipAmountText = String.format("%.2f", totalAmount * 0.1) ;
    	tvTipAmount.setText(tipAmountText);
    }
    
	public void onClickBtn15p(View v){
		totalAmount = getTotalAmount();
		tipAmountText = String.format("%.2f", totalAmount * 0.15) ;
    	tvTipAmount.setText(tipAmountText);	
	}
	
	public void onClickBtn20p(View v){
		totalAmount = getTotalAmount();
		tipAmountText = String.format("%.2f", totalAmount * 0.2) ;
    	tvTipAmount.setText(tipAmountText);
	}
	
	private void setupSpinPercentageListener(){
		spinPercentage.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> spinAdapter, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				totalAmount = getTotalAmount();
				float tipPercentage = getTipPercentageFromSpiner(spinPercentage);
				tipAmountText = String.format("%.2f", totalAmount * tipPercentage) ;
		    	tvTipAmount.setText(tipAmountText);
			}

			@Override
			public void onNothingSelected(AdapterView<?> spinAdapter) {
				// TODO Auto-generated method stub
				tvTipAmount.setText(TIP_AMOUNT_DEFAULT);
			}
		});
	}
	
	private float getTipPercentageFromSpiner(AdapterView<?> spinAdapter){
		
		String selectedPercentage = spinAdapter.getSelectedItem().toString();
		float tipPercentage;
		if(selectedPercentage.equals("10%")){
			tipPercentage = 0.1f;
		}else if(selectedPercentage.equals("15%")){
			tipPercentage = 0.15f;
		}else if(selectedPercentage.equals("20%")){
			tipPercentage = 0.2f;
		}else{
			tipPercentage = 0f;
		}
		return tipPercentage;
	}
	
	private void setupTipAmountListener(){
		tvTipAmount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent editIntent = new Intent(MainActivity.this,EditTipActivity.class);
				editIntent.putExtra("oldTipAmount", getTipAmountText());
				startActivityForResult(editIntent,REQUEST_CODE);
				
			}
		});
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
    		tipAmountText = data.getExtras().getString("newTipAmount");
    		tvTipAmount.setText(tipAmountText);
    	}
    }
	
	private class totalAmountWatcher implements TextWatcher{

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			totalAmount = getTotalAmount();
			float tipPercentage = getTipPercentageFromSpiner(spinPercentage);
			tipAmountText = String.format("%.2f", totalAmount * tipPercentage) ;
	    	tvTipAmount.setText(tipAmountText);
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			
		}
			
	}
    
}
