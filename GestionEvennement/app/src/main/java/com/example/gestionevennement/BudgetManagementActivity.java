package com.example.gestionevennement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BudgetManagementActivity extends AppCompatActivity {

    private EditText editTextTotalBudget;
    private ListView listViewExpenses;
    private Button buttonAddExpense;
    private Button buttonShowTotalExpenses;

    private ArrayList<String> expenseList;
    private ArrayAdapter<String> expenseListAdapter;
    private double totalBudget = 0;
    private double totalExpenses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_management);

        editTextTotalBudget = findViewById(R.id.editTextTotalBudget);
        listViewExpenses = findViewById(R.id.listViewExpenses);
        buttonAddExpense = findViewById(R.id.buttonAddExpense);
        buttonShowTotalExpenses = findViewById(R.id.buttonShowTotalExpenses);

        expenseList = new ArrayList<>();
        expenseListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseList);
        listViewExpenses.setAdapter(expenseListAdapter);

        buttonAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
            }
        });

        buttonShowTotalExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTotalExpenses();
            }
        });
    }

    private void addExpense() {
        String expenseAmountString = editTextTotalBudget.getText().toString().trim();
        if (!expenseAmountString.isEmpty()) {
            double expenseAmount = Double.parseDouble(expenseAmountString);
            totalExpenses += expenseAmount;
            expenseList.add("Dépense: " + expenseAmountString);
            expenseListAdapter.notifyDataSetChanged();
            editTextTotalBudget.getText().clear();
            updateTotalExpenses();
        } else {
            Toast.makeText(this, "Veuillez saisir le montant de la dépense", Toast.LENGTH_SHORT).show();
        }
    }

    private void showTotalExpenses() {
        Toast.makeText(this, "Total des Dépenses: " + totalExpenses, Toast.LENGTH_SHORT).show();
    }

    private void updateTotalExpenses() {

    }

}
