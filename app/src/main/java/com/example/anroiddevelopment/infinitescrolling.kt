package com.example.anroiddevelopment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class infinitescrolling : AppCompatActivity() {

    private var Isscrolling: Boolean = false

    // Moved to class-level (outside onCreate) so fetchData() can also access it
    private val list = mutableListOf(
        getString(R.string.zaid),
        getString(R.string.hamza),
        getString(R.string.shahid),
        getString(R.string.abbas),
        getString(R.string.abdullah),
        getString(R.string.ibrar),
        getString(R.string.asad),
        getString(R.string.yasin),
        getString(R.string.nehal),
        getString(R.string.zaid),
        getString(R.string.hamza),
        getString(R.string.shahid),
        getString(R.string.abbas),
        getString(R.string.abdullah),
    )

    // Also moved to class-level, since fetchData() needs to reach them too
    private lateinit var adapter: Aadapter
    private lateinit var manager: LinearLayoutManager
    private lateinit var pregressbarr : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_infinitescrolling)
        pregressbarr=findViewById<ProgressBar>(R.id.progressbar)

        val calling = findViewById<RecyclerView>(R.id.recyclerview)


        manager = LinearLayoutManager(this)
        calling.layoutManager = manager

        adapter = Aadapter(list)
        calling.adapter = adapter

        //now we start making the infinite scrolling in our recyclerview
        calling.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // A flag/boolean variable to keep track of whether the user is currently scrolling or not
                // starts as "false" because at the very beginning, nothing is being scrolled yet

                // Check: did the scroll state just change to "the user is actively dragging/touching the screen to scroll"?
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {

                    // If yes, update our flag to true - meaning "the user IS currently scrolling"
                    Isscrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var currentItem = manager.childCount
                var totalItems = manager.itemCount
                var scrollitems = manager.findFirstVisibleItemPosition()
                if (Isscrolling && (currentItem + scrollitems == totalItems)) {
                    // user has reached the bottom of the list
                    Isscrolling = false
                    fetchData()
                }
            }
        })
    }

    // Moved out of the scroll listener - now a normal class function, so it can see "list" and "adapter"
    private fun fetchData() {
        pregressbarr.visibility= View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            for (i in 0 until 5) {
                list.add((Math.floor(Math.random() * 100)).toString())
                adapter.notifyDataSetChanged()
                pregressbarr.visibility= View.GONE
            }
        }, 1000)
    }
}