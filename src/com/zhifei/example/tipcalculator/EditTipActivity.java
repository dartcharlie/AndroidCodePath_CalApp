package com.zhifei.example.tipcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EditTipActivity extends Activity {
	private EditText etNewTipAmount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_tip);
		String oldTipText = getIntent().getStringExtra("oldTipAmount");
		etNewTipAmount = (EditText) findViewById(R.id.etNewTipAmount);
		etNewTipAmount.setText(oldTipText);
		Selection.setSelection(etNewTipAmount.getText(), oldTipText.length());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_tip, menu);
		return true;
	}
	
	public void onSave(View v){
		Intent returnData = new Intent();
		returnData.putExtra("newTipAmount", etNewTipAmount.getText().toString());
		setResult(RESULT_OK,returnData);
		this.finish();
	}

}
