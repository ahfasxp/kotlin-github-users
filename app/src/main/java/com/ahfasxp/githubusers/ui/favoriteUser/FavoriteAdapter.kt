package com.ahfasxp.githubusers.ui.favoriteUser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahfasxp.githubusers.R
import com.ahfasxp.githubusers.data.Favorite
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_user.view.*

class FavoriteAdapter(favoriteActivity: FavoriteActivity) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    var listFavorite = ArrayList<Favorite>()
        set(listNotes) {
            if (listNotes.size > 0) {
                this.listFavorite.clear()
            }
            this.listFavorite.addAll(listNotes)

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    override fun getItemCount(): Int = this.listFavorite.size

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(favorite: Favorite) {
            with(itemView){
                Glide.with(itemView.context)
                    .load(favorite.avatar)
                    .apply(RequestOptions().override(55, 55))
                    .into(img_avatar)
                txt_name.text = favorite.username
                txt_username.text = favorite.username
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(favorite) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Favorite)
    }
}