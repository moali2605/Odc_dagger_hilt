package com.example.androidd07

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidd07.Favourite.view.FavouriteActivity
import com.example.androidd07.databinding.ActivityMainBinding
import com.example.androidd07.product.view.ProductActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnGAM.setOnClickListener {
            val intent= Intent(this,ProductActivity::class.java)
            startActivity(intent)
        }
        binding.btnGFM.setOnClickListener {
            val intent= Intent(this,FavouriteActivity::class.java)
            startActivity(intent)
        }
        binding.btnExit.setOnClickListener {
            finish()
        }

    }
}