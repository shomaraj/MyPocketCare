package com.shoma.mypocketcare.model;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shoma.mypocketcare.DataIncomeExpense.PocketDatabase;
import com.shoma.mypocketcare.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddIncomeFragment extends Fragment {
    EditText editTextDescription,editTextAmount,editTextDate;
    TextView textViewclickCategory;
    Spinner spinnerCategory;
    Button buttonAddIncome;
    ArrayAdapter<String> CategoryAdapter;
    PocketDatabase database;

    String[] income_Category={"Salary","Rent","Deposits","Shares","Others"};
    public AddIncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //initialise database
        database=new PocketDatabase(getActivity());

        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_add_income, container, false);

        editTextDescription=(EditText)v.findViewById(R.id.edittext_Income_Description);
        editTextDate=(EditText)v.findViewById(R.id.edittext_Income_Date);
        editTextAmount=(EditText)v.findViewById(R.id.edittext_Income_Amount);

        textViewclickCategory=(TextView)v.findViewById(R.id.textView_IncomeCategory);
        buttonAddIncome=(Button)v.findViewById(R.id.button_Add_Income);
        spinnerCategory=(Spinner)v.findViewById(R.id.spinner_Income_category);
        CategoryAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item,income_Category);
        spinnerCategory.setAdapter(CategoryAdapter);

    buttonAddIncome.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String description =editTextDescription.getText().toString();
        String category=income_Category[spinnerCategory.getSelectedItemPosition()];
        String date=editTextDate.getText().toString();
        int amount=Integer.parseInt(editTextAmount.getText().toString());
        long result =database.createIncomeDetails(category,description,date,amount);
        if(result > -1){
            Toast.makeText(getActivity(), "inserted", Toast.LENGTH_SHORT).show();
        }
    }
});

        return v;
    }

}
