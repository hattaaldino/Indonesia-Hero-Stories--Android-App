package com.antel.recyclerviewcodelab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardHeroAdapter(val listhero: ArrayList<Hero>): Adapter<CardHeroAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.cardview_item_hero, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return listhero.size
    }

    inner class CardViewHolder(itemView: View): ViewHolder(itemView) {
        val listImg: ImageView = itemView.findViewById(R.id.img_item_photo)
        val listName: TextView = itemView.findViewById(R.id.tv_item_name)
        val listDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        val favBtn: Button = itemView.findViewById(R.id.btn_set_favorite)
        val shareBtn: Button = itemView.findViewById(R.id.btn_set_share)

        fun bind(){
            val hero: Hero = listhero[adapterPosition]

            with(itemView){
                Glide.with(this.context)
                    .load(hero.photo)
                    .apply(RequestOptions().override(350, 550))
                    .into(listImg)

                listName.text = hero.name
                listDetail.text = hero.detail

                favBtn.setOnClickListener {
                    Toast.makeText(this.context, "Favorite ${hero.name}", Toast.LENGTH_SHORT).show()
                }

                shareBtn.setOnClickListener {
                    Toast.makeText(this.context, "Share ${hero.name}", Toast.LENGTH_SHORT).show()
                }

                this.setOnClickListener {
                    Toast.makeText(this.context, "You Choose ${hero.name}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}