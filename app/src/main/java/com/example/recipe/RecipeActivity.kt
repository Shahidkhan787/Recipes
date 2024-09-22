package com.example.recipe

import android.graphics.Color.BLACK
import android.graphics.Color.green
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.recipe.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRecipeBinding
    var imgCrop=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
        binding.title.text=intent.getStringExtra("tittle")

        binding.steps.text=intent.getStringExtra("des")
        var ing = intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }
            ?.toTypedArray()
        binding.time.text=ing?.get(0)
        binding.itemImage.setOnClickListener{
            for(i in 1 until (ing!!.size))
                binding.ingData.text=
                    """"${binding.ingData.text}  &{ing[i]}
                    """.trimIndent()
        }
        binding.steps.background=null

        binding.steps.setOnClickListener{
            binding.steps.setTextColor(getColor(R.color.white))
            binding.steps.setTextColor(getColor(R.color.white))

            }


        if(imgCrop){
            binding.itemImage.scaleType= ImageView.ScaleType.FIT_CENTER
            Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
        //    binding.itemImage.setColorFilter(color.BLACK,PorterDuff.Mode.SRC_ATOP)
            binding.steps.visibility=View.GONE
            imgCrop=!imgCrop
        }else{
            binding.itemImage.scaleType=ImageView.ScaleType.CENTER_CROP
            Glide.with(this).load(intent.getStringExtra("ing")).into(binding.itemImage)
            binding.itemImage.setColorFilter(null)
            binding.steps.visibility= View.GONE
            imgCrop=!imgCrop
        }
    }
}