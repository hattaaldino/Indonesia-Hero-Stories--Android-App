package com.antel.recyclerviewcodelab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_item_hero.view.*

class ListHeroAdapter(val listHero: ArrayList<Hero>): Adapter<ListHeroAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.row_item_hero, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind()
}

    override fun getItemCount(): Int {
        return listHero.size
    }

    inner class ListViewHolder(itemView: View): ViewHolder(itemView){
        val itemImg: ImageView = itemView.findViewById(R.id.img_item_photo)
        val itemName: TextView = itemView.findViewById(R.id.tv_item_name)
        val itemDetail: TextView = itemView.findViewById(R.id.tv_item_detail)

        fun bind(){
            with(itemView) {
                val hero: Hero = listHero[adapterPosition]

                Glide.with(this.context)
                    .load(hero.photo)
                    .apply(RequestOptions().override(55, 55))
                    .into(itemImg)

                itemName.text = hero.name
                itemDetail.text = hero.detail

                this.setOnClickListener {
                    onItemClickCallback.onItemClicked(hero)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(item: Hero)
    }
}