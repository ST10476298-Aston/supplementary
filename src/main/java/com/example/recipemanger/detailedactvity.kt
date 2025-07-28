package com.example.recipemanager

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailedViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)

        val btnShowRecipes = findViewById<Button>(R.id.btnShowRecipes)
        val btnAverageRating = findViewById<Button>(R.id.btnAverageRating)
        val btnReturn = findViewById<Button>(R.id.btnReturn)
        val txtOutput = findViewById<TextView>(R.id.txtOutput)

        btnShowRecipes.setOnClickListener {
            if (MainActivity.recipeNames.isEmpty()) {
                txtOutput.text = "No recipes added yet."
            } else {
                val builder = StringBuilder()
                for (i in MainActivity.recipeNames.indices) {
                    builder.append("Name: ${MainActivity.recipeNames[i]}\n")
                    builder.append("Category: ${MainActivity.recipeCategories[i]}\n")
                    builder.append("Rating: ${MainActivity.recipeRatings[i]}\n")
                    builder.append("Ingredients: ${MainActivity.recipeIngredients[i]}\n\n")
                }
                txtOutput.text = builder.toString()
            }
        }

        btnAverageRating.setOnClickListener {
            if (MainActivity.recipeRatings.isNotEmpty()) {
                val average = MainActivity.recipeRatings.sum().toDouble() / MainActivity.recipeRatings.size
                txtOutput.text = "Average Rating: %.2f".format(average)
            } else {
                txtOutput.text = "No recipes to calculate."
            }
        }

        btnReturn.setOnClickListener { finish() }
    }
}
