package com.hoholms.online.wallet

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.DynamicColors

class MainActivity : AppCompatActivity() {

    private val expenses = mutableListOf<Expense>(Expense("Template Expense", 420.69))
    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DynamicColors.applyToActivityIfAvailable(this)
        setContentView(R.layout.activity_main)

        val editTextAmount: EditText = findViewById(R.id.editTextAmount)
        val editTextDescription: EditText = findViewById(R.id.editTextDescription)
        val btnAddExpense: Button = findViewById(R.id.btnAddExpense)
        val recyclerViewExpenses: RecyclerView = findViewById(R.id.recyclerViewExpenses)

        expenseAdapter = ExpenseAdapter(expenses)
        recyclerViewExpenses.layoutManager = LinearLayoutManager(this)
        recyclerViewExpenses.adapter = expenseAdapter

        btnAddExpense.setOnClickListener {
            val amount = editTextAmount.text.toString().toDoubleOrNull()
            val description = editTextDescription.text.toString()

            if (amount != null && description.isNotEmpty()) {
                val expense = Expense(description, amount)
                expenses.add(expense)
                expenseAdapter.notifyDataSetChanged()

                editTextAmount.text.clear()
                editTextDescription.text.clear()
            }
        }
    }
}
