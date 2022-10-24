package com.example.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddIngredientActivity extends AppCompatActivity {
    LocalDate currentDate;

    // description
    EditText descriptionEditText;
    // location
    Spinner locationSpinner;
    ArrayAdapter locationSpinnerAdapter;
    // category
    Spinner categorySpinner;
    ArrayAdapter categorySpinnerAdapter;
    // unit
    Spinner unitSpinner;
    ArrayAdapter unitSpinnerAdapter;
    // expiry date
    TextView expiryDateTextView;
    Button selectExpiryDateButton;
    // amount
    EditText amountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        descriptionEditText = findViewById(R.id.add_ingredient_description_editText);
        locationSpinner = findViewById(R.id.add_ingredient_location_spinner);
        categorySpinner = findViewById(R.id.add_ingredient_category_spinner);
        unitSpinner = findViewById(R.id.add_ingredient_unit_spinner);
        expiryDateTextView = findViewById(R.id.add_ingredient_expiryDate_textView);
        selectExpiryDateButton = findViewById(R.id.add_ingredient_select_expiryDate_button);
        amountEditText = findViewById(R.id.add_ingredient_amount_editText);

        initLocationSpinner();
        initCategorySpinner();
        initUnitSpinner();

    }//onCreate

    public void ConfirmAdd(View view){

        String description = descriptionEditText.getText().toString();// String = description input
        String location = locationSpinner.getSelectedItem().toString();
        String category = categorySpinner.getSelectedItem().toString();
        String amount = amountEditText.getText().toString();

        if(description.isEmpty() || location.isEmpty() || category.isEmpty()  || amount.isEmpty()) {//if user input is empty..
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_LONG).show();
            return;


        }else if( Double.parseDouble(amount) <=0 ){//checks if user input count is zero
            Toast.makeText(this, "Inputs cannot be less than zero", Toast.LENGTH_LONG).show();
            //Log.d("Checking","2");
            return;
        }//if

        // return ingredient to IngredientsActivity
        Ingredient ingredient = new Ingredient(description, LocalDate.now(), location, Integer.parseInt(amount), "kg", category);
        Intent intent = new Intent(this, IngredientsActivity.class);
        intent.putExtra("ingredient", ingredient);
        setResult(RESULT_OK, intent);
        finish();

    }//ConfirmAdd

    public void setDate(View view){
        currentDate = LocalDate.now();

        Button SelectDateButton;
        SelectDateButton = findViewById(R.id.add_ingredient_select_expiryDate_button);
        SelectDateButton.setText(currentDate.toString());

    }//setDate

    private void initLocationSpinner() {
        // TODO: add ability for user to add locations to list/adapter
        ArrayList<String> locations = new ArrayList<String>() {{
            add("Fridge");
            add("Freezer");
            add("Pantry");
            add("");
        }};
        locationSpinnerAdapter = new CustomSpinnerAdapter(this, R.layout.spinner_item, locations);
        locationSpinner.setAdapter(locationSpinnerAdapter);
        locationSpinner.setSelection(locationSpinnerAdapter.getCount());
    }

    private void initCategorySpinner() {
        // TODO: add ability for user to add locations to list/adapter
        ArrayList<String> categories = new ArrayList<String>() {{
            add("Vegetable");
            add("Fruit");
            add("Pastry");
            add("");
        }};
        categorySpinnerAdapter = new CustomSpinnerAdapter(this, R.layout.spinner_item, categories);
        categorySpinner.setAdapter(categorySpinnerAdapter);
        categorySpinner.setSelection(categorySpinnerAdapter.getCount());
    }

    private void initUnitSpinner() {
        // TODO: add ability for user to add locations to list/adapter
        ArrayList<String> units = new ArrayList<String>() {{
            add("kg");
            add("lb");
            add("oz");
            add("");
        }};
        unitSpinnerAdapter = new CustomSpinnerAdapter(this, R.layout.spinner_item, units);
        unitSpinner.setAdapter(unitSpinnerAdapter);
        unitSpinner.setSelection(unitSpinnerAdapter.getCount());
    }
}