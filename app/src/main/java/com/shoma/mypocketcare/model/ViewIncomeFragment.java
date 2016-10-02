package com.shoma.mypocketcare.model;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.shoma.mypocketcare.DataIncomeExpense.PocketDatabase;
import com.shoma.mypocketcare.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewIncomeFragment extends Fragment {
//Declaration initialization
    PocketDatabase database;
    ListView listView_Incomeview;
    TextView textViewId,textViewDate,textViewCategory,textViewDescription,textViewAmount,textViewtotalIncome;
    EditText editTextTotalIncome;
    private ArrayList<ViewIncome> IncomeArrayList=new ArrayList<>();
    private  IncomeArrayAdapter incomeArrayAdapter;

    public ViewIncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v=inflater.inflate(R.layout.fragment_view_income, container, false);

        database = new PocketDatabase(getActivity());

        listView_Incomeview = (ListView) v.findViewById(R.id.listview_IncomeView);

        textViewId=(TextView)v.findViewById(R.id.textview_idViewIncome);
        textViewCategory=(TextView)v.findViewById(R.id.textView_IncomeCategory);
        textViewAmount=(TextView)v.findViewById(R.id.textview_AmountViewIncome);
        textViewDescription=(TextView)v.findViewById(R.id.textview_DescriptionViewIncome);
        textViewDate=(TextView)v.findViewById(R.id.textview_DateViewIncome);
        textViewtotalIncome=(TextView)v.findViewById(R.id.textview_TotalIncome);

        editTextTotalIncome=(EditText)v.findViewById(R.id.editText_TotalIncome);
        incomeArrayAdapter = new IncomeArrayAdapter();



        listView_Incomeview.setAdapter(incomeArrayAdapter);


        //readFromDatabaseAndUpdateListView();
        return v;
    }


    private void readFromDatabaseAndUpdateListView(){
       // helper=new Po(this);
        IncomeArrayList.clear();
        IncomeArrayList.addAll(database.getAllIncomeDetails());
        incomeArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        readFromDatabaseAndUpdateListView();
    }

    class IncomeArrayAdapter extends BaseAdapter{


         @Override
         public int getCount() {
             return IncomeArrayList.size();
         }

         @Override
         public Object getItem(int position) {
             return IncomeArrayList.get(position);
         }

         @Override
         public long getItemId(int position) {
             return position;
         }

         @Override
         public View getView(int position, View view, ViewGroup viewGroup) {

             ViewIncome viewIncome =IncomeArrayList.get(position);

             //View v=getLayoutInflater().inflate(R.layout.income_row,null);

             View v=getActivity().getLayoutInflater().inflate(R.layout.income_row,null);

             TextView textViewCategory=(TextView)v.findViewById(R.id.textview_Category_RowIncome);
             TextView textViewId=(TextView)v.findViewById(R.id.textview_id_RowIncome);
             TextView textViewDate=(TextView)v.findViewById(R.id.textview_Date_RowIncome);
             TextView textViewDescription=(TextView)v.findViewById(R.id.textview_Description_RowIncome);

             TextView textViewAmount=(TextView)v.findViewById(R.id.textview_Amount_RowIncome);

             textViewId.setText(""+viewIncome.getId());
             textViewDate.setText(""+viewIncome.getDate());
             textViewCategory.setText(""+viewIncome.getCategory());
             textViewDescription.setText(""+viewIncome.getDescription());
             textViewAmount.setText(""+viewIncome.getAmount());

             return v;
         }
     }

}
