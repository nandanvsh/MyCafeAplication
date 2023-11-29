package com.example.bangkitapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.view.View
import com.example.bangkitapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.actionShare.setOnClickListener(this)
        val actionBar = supportActionBar

        val dataFood = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Food>(MainActivity.MAIN_FOOD, Food::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Food>(MainActivity.MAIN_FOOD)
        }

        actionBar!!.title = "Description"
        actionBar.setDefaultDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        if (dataFood != null){
            binding.imageView.setImageResource(dataFood.photo)
            binding.textView.text = dataFood.name
            binding.tvItemDescription.text = dataFood.description
            binding.negaraAsal.text = dataFood.country
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.action_share -> {
                val Url = "mailto:admin@example.com?cc=dev@foods.id&subject=HalloFoodies&body=Artikelyangmembantu"
                val theIntent = Intent(Intent.ACTION_SENDTO, Uri.parse(Url))
                startActivity(theIntent)
            }
        }
    }

}