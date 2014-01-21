package com.zhifei.example.tipcalculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText etTotalAmount;
	private TextView tvTipAmount;
	private double totalAmount;
	private String tipAmountText;
	private final int REQUEST_CODE = 86;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTotalAmount = (EditText) findViewById(R.id.etTotalAmount);
        tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);
        setupTipAmountListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private double getTotalAmount(){
    	String fieldValue = etTotalAmount.getText().toString();
    	double ret = 0.00;
    	try{
    		ret = Double.parseDouble(fieldValue);
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
    
}
