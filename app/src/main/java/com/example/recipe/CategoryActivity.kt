package com.example.recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipe.databinding.ActivityCategoryBinding
import com.example.recipe.databinding.ActivityMainBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var rvAdapter: CategoryAdapter
    private lateinit var datalist: ArrayList<Recipe>
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpRecyclerView()
        title = intent.getStringExtra("TITTLE")
        binding.goBackHome.setOnClickListener{
            finish()
        }
    }

    private fun setUpRecyclerView() {
        datalist = ArrayList()
        binding.rvCategory.layoutManager =
            LinearLayoutManager(this,)
        var db = Room.databaseBuilder(this@CategoryActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        var recipes = daoObject.getAll()
        for (i in recipes!!.indices) {
            if (recipes[i]!!.category.contains(intent.getStringExtra("CATEGORY")!!)) {
                datalist.add(recipes[i]!!)
            }
            rvAdapter = CategoryAdapter(datalist, this)
            binding.rvCategory.adapter = rvAdapter
        }
    }

}