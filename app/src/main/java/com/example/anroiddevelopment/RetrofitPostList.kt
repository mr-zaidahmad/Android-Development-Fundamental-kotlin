package com.example.anroiddevelopment

// This represents the WHOLE response Blogger sends back
// Blogger wraps all posts inside a field called "items"
data class PostList(
    val items: List<Post>
)

// This represents ONE SINGLE blog post inside that list
// Each field name here must match exactly what Blogger's JSON uses
data class Post(
    val id: String,
    val title: String,
    val content: String,   // this comes as raw HTML text from Blogger
    val url: String
)