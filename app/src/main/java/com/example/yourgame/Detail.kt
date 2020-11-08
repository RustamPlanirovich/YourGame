package com.example.yourgame


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.yourgame.MainActivity.*
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import kotlinx.android.synthetic.main.detail_view.*


class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_view)

        val intent = intent



        // Selected image id
        val name = intent.extras!!.getString("name")
        val description = intent.extras!!.getString("description")
        val price = intent.extras!!.getString("price")
        val image = intent.extras!!.getString("image")

        Glide.with(applicationContext)
            .load(image)
            .centerCrop()
            .into(gameItemImage)
        gameItemName.text = name
        textView2.text = description
        textView5.text = price



    }
}