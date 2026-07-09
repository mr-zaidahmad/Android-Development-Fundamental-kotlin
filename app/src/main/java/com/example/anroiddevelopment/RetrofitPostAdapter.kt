package com.example.anroiddevelopment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.jsoup.Jsoup

// context -> needed to inflate layouts and load images
// posts -> the actual list of blog posts we're going to display
class RetrofitPostAdapter(val context: Context, val posts: List<Post>) :
    RecyclerView.Adapter<RetrofitPostAdapter.PostViewHolder>() {

    // Called when a brand NEW card needs to be built (Android reuses old ones when possible)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        // Build one blank card using our item design (blogger_item_layout.xml)
        val view: View = LayoutInflater.from(context).inflate(R.layout.blogger_item_layout, parent, false)

        return PostViewHolder(view)
    }

    // Called every time a card needs to be filled in with real data
    // "position" tells us WHICH post this specific card represents
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val post = posts[position]

        // Simple text, no HTML involved
        holder.postTitle.text = post.title

        // Search the post's HTML content for an image link
        val imageUrl = extractImageUrl(post.content)

        if (imageUrl != null) {
            holder.postImage.visibility = View.VISIBLE
            Glide.with(context).load(imageUrl).into(holder.postImage)
        } else {
            holder.postImage.visibility = View.GONE
        }

        // Use Jsoup to properly parse the HTML, strip out image tags,
        // then extract clean plain text with no leftover HTML
        val textOnlyContent = Jsoup.parse(post.content).apply {
            select("img").remove()
        }.text()

        holder.postContent.text = textOnlyContent
    }

    // Tells RecyclerView how many total cards should exist
    override fun getItemCount(): Int {
        return posts.size
    }

    // Helper function - digs through raw HTML to find an image link, if one exists
    private fun extractImageUrl(html: String): String? {
        val document = Jsoup.parse(html)          // properly parse the HTML structure
        val imgElement = document.select("img").first()  // find the first <img> tag
        return imgElement?.attr("src")            // return its link, or null if no image found
    }

    // Holds references to the views INSIDE one single card
    // Avoids calling findViewById() repeatedly for the same views
    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postTitle = itemView.findViewById<TextView>(R.id.PostTitle)
        var postImage = itemView.findViewById<ImageView>(R.id.PostImage)
        var postContent = itemView.findViewById<TextView>(R.id.PostContent)
    }
}