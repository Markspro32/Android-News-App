package com.example.fragmentbestpractice

import android.content.Intent
import android.widget.Button
import android.widget.Toast

/**
 * Turns a Button into a stateful like toggle.
 * Tapping it flips between liked/unliked, updates its label,
 * shows a Toast, and fires [onLikeChanged] with the new state.
 */
fun Button.setupAsLikeButton(
    likeText: String = "♥  Like",
    likedText: String = "♥  Liked",
    onLikeChanged: ((isLiked: Boolean) -> Unit)? = null
) {
    var isLiked = false
    text = likeText
    setOnClickListener {
        isLiked = !isLiked
        text = if (isLiked) likedText else likeText
        val message = if (isLiked) "You liked this article!" else "Like removed"
        showToast(message)
        onLikeChanged?.invoke(isLiked)
    }
}

/**
 * Turns a Button into a share action that opens the system share sheet
 * with [newsTitle] as the subject and a short teaser as the body.
 */
fun Button.setupAsShareButton(newsTitle: String) {
    setOnClickListener {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, newsTitle)
            putExtra(Intent.EXTRA_TEXT, "Check out this article: $newsTitle")
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}

/**
 * Shows a short Toast anchored to this view's context.
 */
fun android.view.View.showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
