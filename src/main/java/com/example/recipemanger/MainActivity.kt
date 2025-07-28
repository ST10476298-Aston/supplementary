package com.example.recipemanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        var recipeNames = mutableListOf<String>()
        var recipeCategories = mutableListOf<String>()
        var recipeRatings = mutableListOf<Int>()
        var recipeIngredients = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddRecipe = findViewById<Button>(R.id.btnAddRecipe)
        val btnViewRecipes = findViewById<Button>(R.id.btnViewRecipes)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnAddRecipe.setOnClickListener { showAddRecipeDialog() }
        btnViewRecipes.setOnClickListener {
            val intent = Intent(this, DetailedViewActivity::class.java)
            startActivity(intent)
        }
        btnExit.setOnClickListener { finish() }
    }

    private fun showAddRecipeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_recipe, null)
        val edtName = dialogView.findViewById<EditText>(R.id.edtName)
        val edtCategory = dialogView.findViewById<EditText>(R.id.edtCategory)
        val edtRating = dialogView.findViewById<EditText>(R.id.edtRating)
        val edtIngredients = dialogView.findViewById<EditText>(R.id.edtIngredients)

        AlertDialog.Builder(this)
            .setTitle("Add Recipe")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = edtName.text.toString().trim()
                val category = edtCategory.text.toString().trim()
                val rating = edtRating.text.toString().toIntOrNull()
                val ingredients = edtIngredients.text.toString().trim()

                if (name.isNotEmpty() && category.isNotEmpty() && rating != null && rating in 1..5 && ingredients.isNotEmpty()) {
                    recipeNames.add(name)
                    recipeCategories.add(category)
                    recipeRatings.add(rating)
                    recipeIngredients.add(ingredients)
                    Toast.makeText(this, "Recipe added!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Invalid input. Rating must be 1-5.", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
