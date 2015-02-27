package com.mandiecohoon.bmicalculator;

import java.text.NumberFormat;

import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	// currency and percent formatters 
	   private static final NumberFormat numberFormat = NumberFormat.getInstance();

	   private double bmi = 0.0;
	   private double weight = 0.0;
	   private double height = 0.0;
	   boolean metric = false;
	   private TextView bmiText;
	   private TextView weightDisplayTextView;
	   private TextView heightDisplayTextView;
	   private CheckBox metricCheckBox;
	
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
		   
	      super.onCreate(savedInstanceState); // call superclass's version
	      setContentView(R.layout.activity_main); // inflate the GUI

	      // get references to the TextViews 
	      // that MainActivity interacts with programmatically
	      bmiText = (TextView) findViewById(R.id.bmiText);
	      weightDisplayTextView = (TextView) findViewById(R.id.weightDisplay);
	      heightDisplayTextView = (TextView) findViewById(R.id.heightDisplay);
	      metricCheckBox = (CheckBox) findViewById(R.id.metricCheckBox);
	            
	      weightDisplayTextView.setText(numberFormat.format(weight));
	      heightDisplayTextView.setText(numberFormat.format(height));
	      bmiText.setText(numberFormat.format(bmi));

	      // set weight TextWatcher
	      EditText weightEditText = (EditText) findViewById(R.id.weightTextEdit);
	      weightEditText.addTextChangedListener(weightTextEditWatcher);
	      
	      // set height TextWatcher
	      EditText heightTextEdit = (EditText) findViewById(R.id.heightTextEdit);
	      heightTextEdit.addTextChangedListener(heightTextEditWatcher);
	      
	      metricCheckBox.setOnClickListener(new OnClickListener() {

	          @Override
	          public void onClick(View v) {
	                    //is chkIos checked?
	            if (((CheckBox) v).isChecked()) {
	            	metric = true;
	            } else {
	            	metric = false;
	            }
	            updateBMI();
	          }
	        });
	      
	      updateBMI();
	      
	   } // end method onCreate
	   
	   private void updateBMI() {  // calculate BMI
		   if (metric) {
			   bmi = ((weight)/(height * height));
		   } else {
			   bmi = ((weight*703)/(height * height));
		   }
		   
		   bmiText.setText(numberFormat.format(bmi));
	   }

	   private TextWatcher weightTextEditWatcher = new TextWatcher()  {
	      @Override
	      public void onTextChanged(CharSequence s, int start, int before, int count) {  
	         try {
	            weight = Double.parseDouble(s.toString()) / 100.0;
	         } catch (NumberFormatException e) {
	            weight = 0.0;
	         }

	         weightDisplayTextView.setText(numberFormat.format(weight));
		     updateBMI();
	      }

	      @Override
	      public void afterTextChanged(Editable s) {
	    	  
	      }

	      @Override
	      public void beforeTextChanged(CharSequence s, int start, int count,  int after) {
	    	  
	      }
	      
	   };
	   
	   
	   private TextWatcher heightTextEditWatcher = new TextWatcher()  {
		      @Override
		      public void onTextChanged(CharSequence s, int start, int before, int count) {  
		         try {
		            height = Double.parseDouble(s.toString()) / 100.0;
		         } catch (NumberFormatException e) {
		            height = 0.0;
		         }

			     heightDisplayTextView.setText(numberFormat.format(height));
			     updateBMI();
		      }

		      @Override
		      public void afterTextChanged(Editable s) {
		    	  
		      }

		      @Override
		      public void beforeTextChanged(CharSequence s, int start, int count,  int after) {
		    	  
		      }
		      
		   };
}
