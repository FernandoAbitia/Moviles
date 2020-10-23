package com.fgonzalezh.myfirstroomdb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.fgonzalezh.myfirstroomdb.R
import com.fgonzalezh.myfirstroomdb.models.entities.Content
import com.fgonzalezh.myfirstroomdb.models.entities.Favorite

class FavoritesAdapter(private val favorites: List<Content>) : RecyclerView.Adapter<FavoritesAdapter.FavoriteAdapterViewHolder>() {

    val favoriteSelected = mutableListOf<Content>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorites, parent, false)
        return FavoriteAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapterViewHolder, position: Int) {
        val favorite = favorites[position]
        holder.onBind(favorite)
    }

    override fun getItemCount(): Int = favorites.size

    inner class FavoriteAdapterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(favorite: Content) {
            val checkboxFavorite = view.findViewById<CheckBox>(R.id.checkBox_favorite)

            checkboxFavorite.text = "${favorite.name} ${favorite.lastName}"

            checkboxFavorite.setOnCheckedChangeListener{ _, checked ->
                if (checked){
                    favoriteSelected.add(favorite)
                }else{
                    favoriteSelected.remove(favorite)
                }
            }
        }
    }
}