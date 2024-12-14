package ru.tvorigora.mvp.data

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.tvorigora.mvp.R

class NewsAdapter(private val onClick: (News) -> Unit):
        ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallback) {

        class NewsViewHolder(itemView: View, val onClick: (News) -> Unit):
            RecyclerView.ViewHolder(itemView) {

        public val newsImage: ImageView = itemView.findViewById(R.id.news_image)
        public val newsCaption: TextView = itemView.findViewById(R.id.news_caption)

        init {

        }

        fun bind(news: News){
            newsCaption.text = news.caption

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)

        Glide.with(holder.itemView.context).load(news.imageUrl).circleCrop()
            .error(R.drawable.fav)
            .placeholder(R.drawable.fav).into(holder.newsImage)

    }

    object NewsDiffCallback: DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

    }

}