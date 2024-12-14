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

class MasterAdapter(private val onClick: (Master) -> Unit):
    ListAdapter<Master, MasterAdapter.MasterViewHolder>(MasterDiffCallback) {

    class MasterViewHolder(itemView: View, val onClick: (Master) -> Unit):
        RecyclerView.ViewHolder(itemView) {

        public val masterImage: ImageView = itemView.findViewById(R.id.master_image)
        public val masterFullName: TextView = itemView.findViewById(R.id.master_FullName)
        public val masterDescription: TextView = itemView.findViewById(R.id.master_Description)

        init {

        }

        fun bind(master: Master){
            masterFullName.text = master.fullName
            masterDescription.text = master.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.master_item, parent, false)
        return MasterViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MasterViewHolder, position: Int) {
        val master = getItem(position)
        holder.bind(master)

        Glide.with(holder.itemView.context).load(master.imageUrl).circleCrop()
            .error(R.drawable.fav)
            .placeholder(R.drawable.fav).into(holder.masterImage)

    }

    object MasterDiffCallback: DiffUtil.ItemCallback<Master>(){
        override fun areItemsTheSame(oldItem: Master, newItem: Master): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Master, newItem: Master): Boolean {
            return oldItem.id == newItem.id
        }

    }

}