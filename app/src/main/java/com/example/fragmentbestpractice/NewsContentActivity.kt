package com.example.fragmentbestpractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_content)

        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")
        val imageResId = intent.getIntExtra("news_image_res_id", R.drawable.news_img_1)
        val publisher = intent.getStringExtra("news_publisher") ?: "Unknown"
        val publisherLogoResId = intent.getIntExtra("news_publisher_logo_res_id", R.drawable.publisher_logo_1)

        if (title != null && content != null) {
            val fragment =
                supportFragmentManager.findFragmentById(R.id.newsContentFrag) as? MainActivity.NewsContentFragment
            fragment?.refresh(title, content, imageResId, publisher, publisherLogoResId)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        fun actionStart(
            context: Context,
            title: String,
            content: String,
            imageResId: Int,
            publisher: String,
            publisherLogoResId: Int
        ) {
            val intent = Intent(context, NewsContentActivity::class.java).apply {
                putExtra("news_title", title)
                putExtra("news_content", content)
                putExtra("news_image_res_id", imageResId)
                putExtra("news_publisher", publisher)
                putExtra("news_publisher_logo_res_id", publisherLogoResId)
            }
            context.startActivity(intent)
        }
    }

    class NewsTitleFragment : Fragment() {

        private var isTwoPane = false

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.news_title_frag, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            isTwoPane = requireActivity().findViewById<View>(R.id.newsContentLayout) != null

            val layoutManager = LinearLayoutManager(activity)
            val newsTitleRecyclerView = view.findViewById<RecyclerView>(R.id.newsTitleRecyclerView)
            newsTitleRecyclerView.layoutManager = layoutManager
            val adapter = NewsAdapter(getNews())
            newsTitleRecyclerView.adapter = adapter
        }

        private fun getNews(): List<MainActivity.News> {
            // Three publishers cycling through different images
            val publishers = listOf(
                Triple("CNN",  R.drawable.publisher_logo_1, listOf(R.drawable.news_img_1, R.drawable.news_img_2)),
                Triple("BBC",  R.drawable.publisher_logo_2, listOf(R.drawable.news_img_3, R.drawable.news_img_4)),
                Triple("NYT",  R.drawable.publisher_logo_3, listOf(R.drawable.news_img_5, R.drawable.news_img_1))
            )
            val newsList = ArrayList<MainActivity.News>()
            for (i in 1..50) {
                val (name, logoRes, images) = publishers[i % publishers.size]
                val imgRes = images[i % images.size]
                newsList.add(
                    MainActivity.News(
                        title = "This is news title $i",
                        content = getRandomLengthString("This is news content $i."),
                        imageResId = imgRes,
                        publisher = name,
                        publisherLogoResId = logoRes
                    )
                )
            }
            return newsList
        }

        fun getRandomLengthString(str: String) = str * (1..20).random()

        inner class NewsAdapter(val newsList: List<MainActivity.News>) :
            RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

            inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
                val newsTitle: TextView = view.findViewById(R.id.newsTitle)
                val publisherLogo: ImageView = view.findViewById(R.id.itemPublisherLogo)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
                val holder = ViewHolder(view)

                holder.itemView.setOnClickListener {
                    val position = holder.adapterPosition
                    if (position == RecyclerView.NO_POSITION) return@setOnClickListener
                    val news = newsList[position]

                    val activity = activity ?: return@setOnClickListener
                    val newsContentLayout = activity.findViewById<View>(R.id.newsContentLayout)

                    if (newsContentLayout != null) {
                        val fragment =
                            (activity as? AppCompatActivity)?.supportFragmentManager
                                ?.findFragmentById(R.id.newsContentFrag) as? MainActivity.NewsContentFragment
                        fragment?.refresh(
                            news.title, news.content,
                            news.imageResId, news.publisher, news.publisherLogoResId
                        )
                    } else {
                        NewsContentActivity.actionStart(
                            parent.context,
                            news.title, news.content,
                            news.imageResId, news.publisher, news.publisherLogoResId
                        )
                    }
                }
                return holder
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val news = newsList[position]
                holder.newsTitle.text = news.title
                holder.publisherLogo.setImageResource(news.publisherLogoResId)
            }

            override fun getItemCount() = newsList.size
        }
    }
}
