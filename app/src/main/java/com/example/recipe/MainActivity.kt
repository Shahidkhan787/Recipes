package com.example.recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipe.databinding.ActivityMainBinding
import com.example.recipe.databinding.PopularRvItemBinding
import com.google.android.material.navigation.NavigationBarMenu

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var datalist:ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
    binding.search.setOnClickListener{
        startActivity(Intent(this,SearchActivity::class.java))

    }
    binding.salad.setOnClickListener{
        var myIntent=Intent(this,CategoryActivity::class.java)
        myIntent.putExtra("TITLE","Salad")
        myIntent.putExtra("CATEGORY","Salad")
        startActivity(myIntent)
    }
    binding.nonveg.setOnClickListener{
        var myIntent=Intent(this,CategoryActivity::class.java)
        myIntent.putExtra("TITLE","Main Dish")
        myIntent.putExtra("CATEGORY","Dish")
        startActivity(myIntent)
    }
    binding.MainDish.setOnClickListener{
        var myIntent=Intent(this,CategoryActivity::class.java)
        myIntent.putExtra("TITLE","Drinks")
        myIntent.putExtra("CATEGORY","Drinks")
        startActivity(myIntent)
    }
    binding.categoryDrinks.setOnClickListener{
        var myIntent=Intent(this,CategoryActivity::class.java)
        myIntent.putExtra("TITLE","Desserts")
        myIntent.putExtra("CATEGORY","Desserts")
        startActivity(myIntent)
    }
    }

    private fun setUpRecyclerView() {
        datalist = ArrayList()
        binding.recycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        var db= Room.databaseBuilder(this@MainActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=db.getDao()
        var recipes=daoObject.getAll()
        for(i in recipes!!.indices)
            if(recipes[i]!!.category.contains("Popular")){
                datalist.add(recipes[i]!!)
            }
        rvAdapter=PopularAdapter(datalist,this)
        binding.recycler.adapter=rvAdapter
    }
}
