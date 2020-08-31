package com.antel.recyclerviewcodelab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GridHeroAdapter(val listhero: ArrayList<Hero>): Adapter<GridHeroAdapter.GridViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.grid_item_hero, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return listhero.size
    }

    inner class GridViewHolder(itemView: View): ViewHolder(itemView){
        val itemImg: ImageView = itemView.findViewById(R.id.img_item_photo)

        fun bind(){
            val hero = listhero[adapterPosition]

            with(itemView){
                Glide.with(this.context)
                    .load(hero.photo)
                    .apply(RequestOptions().override(350))
                    .into(itemImg)

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