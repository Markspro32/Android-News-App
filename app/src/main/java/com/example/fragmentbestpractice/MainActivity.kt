package com.example.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    data class News(
        val title: String,
        val content: String,
        val imageResId: Int = R.drawable.news_img_1,
        val publisher: String = "Unknown",
        val publisherLogoResId: Int = R.drawable.publisher_logo_1
    )

    class NewsContentFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.news_content_frag, container, false)
        }

        fun refresh(
            title: String,
            content: String,
            imageResId: Int,
            publisher: String,
            publisherLogoResId: Int
        ) {
            val contentLayout = view?.findViewById<LinearLayout>(R.id.contentLayout)
            val newsTitleView = view?.findViewById<TextView>(R.id.newsTitle)
            val newsContentView = view?.findViewById<TextView>(R.id.newsContent)
            val newsImageView = view?.findViewById<ImageView>(R.id.newsImage)
            val publisherLogoView = view?.findViewById<ImageView>(R.id.publisherLogo)
            val publisherNameView = view?.findViewById<TextView>(R.id.publisherName)
            val likeButton = view?.findViewById<Button>(R.id.likeButton)
            val shareButton = view?.findViewById<Button>(R.id.shareButton)

            contentLayout?.visibility = View.VISIBLE
            newsTitleView?.text = title
            newsContentView?.text = content
            newsImageView?.setImageResource(imageResId)
            publisherLogoView?.setImageResource(publisherLogoResId)
            publisherNameView?.text = publisher

            // Extension functions wire up interactive behaviour
            likeButton?.setupAsLikeButton()
            shareButton?.setupAsShareButton(title)
        }
    }
}
