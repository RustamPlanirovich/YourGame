package com.example.yourgame


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.firebase.ui.firestore.FirestoreRecyclerOptions.Builder
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import de.hdodenhof.circleimageview.CircleImageView


class MainActivity : AppCompatActivity() {

    private val LAYOUT_ONE = 0
    private val LAYOUT_TWO = 1

    lateinit var progressBar: ProgressBar
    lateinit var friendList: RecyclerView

    lateinit var db: FirebaseFirestore
    lateinit var adapter: FirestoreRecyclerAdapter<FriendsResponse, FriendsHolder>
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        friendList = findViewById(R.id.friend_list)

        progressBar = findViewById(R.id.progress_bar)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        init()
        getFriendList()

    }

    private fun init() {
        linearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        friendList.layoutManager = linearLayoutManager
        db = FirebaseFirestore.getInstance()

    }

    private fun getFriendList() {
        val query: Query = db.collection("users")
        val response: FirestoreRecyclerOptions<FriendsResponse> = Builder<FriendsResponse>()
            .setQuery(query, FriendsResponse::class.java)
            .build()
        adapter = object : FirestoreRecyclerAdapter<FriendsResponse, FriendsHolder>(response) {
            override fun onBindViewHolder(
                holder: FriendsHolder,
                position: Int,
                model: FriendsResponse
            ) {

                progressBar.visibility = View.GONE

                holder.textName!!.text = model.name
                holder.textTitle!!.text = model.description
                holder.textCompany!!.text = model.price
                Glide.with(applicationContext)
                    .load(model.image)
                    .into(holder.imageView!!)
                holder.itemView.setOnClickListener {
                    friendList.let {

                        // Sending image id to FullScreenActivity
                        val i = Intent(
                            applicationContext,
                            Detail::class.java
                        )

                        // passing array index
                        i.putExtra("name", model.name)
                        i.putExtra("description", model.description)
                        i.putExtra("price", model.price)
                        i.putExtra("image", model.image)
                        startActivity(i)
                    }
                }

            }

            override fun getItemViewType(position: Int): Int {
                return if (position == 0) LAYOUT_ONE else LAYOUT_TWO
            }

            override fun onCreateViewHolder(group: ViewGroup, i: Int): FriendsHolder {
                val view: View = LayoutInflater.from(group.context)
                    .inflate(R.layout.game_item, group, false)
                return FriendsHolder(view)
            }

            override fun onError(e: FirebaseFirestoreException) {
                Log.e("error", e.message!!)
            }
        }
        adapter.notifyDataSetChanged()
        friendList.adapter = adapter
    }


    class FriendsHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {

        var textName: TextView? = itemView?.findViewById(R.id.name)

        var imageView: CircleImageView? = itemView?.findViewById(R.id.image)

        var textTitle: TextView? = itemView?.findViewById(R.id.title)

        var textCompany: TextView? = itemView?.findViewById(R.id.company)

        var stockText: TextView? = itemView?.findViewById(R.id.stocksText)

    }


    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}