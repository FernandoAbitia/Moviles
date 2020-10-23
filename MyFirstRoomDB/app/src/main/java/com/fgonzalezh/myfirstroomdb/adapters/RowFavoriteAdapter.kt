package com.fgonzalezh.myfirstroomdb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fgonzalez.myfirstroomdb.interfaces.BottomDialogListener
import com.fgonzalezh.myfirstroomdb.R
import com.fgonzalezh.myfirstroomdb.models.entities.Content
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.row_favorite.view.*

class RowFavoriteAdapter(val favorites: List<Content>,
    val bottomDialogListener: BottomDialogListener): RecyclerView.Adapter<RowFavoriteAdapter.RowFavoriteAdapterViewHolder>(){

    inner class RowFavoriteAdapterViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun onBind(content: Content){
            val textViewFavoriteName = view.findViewById<TextView>(R.id.textview_favorite_name)
            val buttonDelete = view.findViewById<MaterialButton>(R.id.button_delete_favorite)

            textViewFavoriteName.text=content.fullName
            buttonDelete.setOnClickListener{
                bottomDialogListener.onClick(content.id!!)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RowFavoriteAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_favorite,parent,false)

        return RowFavoriteAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: RowFavoriteAdapterViewHolder, position: Int) {
        val favorite = favorites[position]
        holder.onBind(favorite)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }
}