package com.rockbass2560.rickandmortyapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rockbass2560.rickandmortyapp.R
import com.rockbass2560.rickandmortyapp.databinding.CardCharacterBinding
import com.rockbass2560.rickandmortyapp.databinding.CardEpisodeBinding
import com.rockbass2560.rickandmortyapp.listeners.OnBottomReachedListener
import com.rockbass2560.rickandmortyapp.models.CharacterView
import com.rockbass2560.rickandmortyapp.models.Results
import com.rockbass2560.rickandmortyapp.views.ActivityEpisode
import kotlinx.android.synthetic.main.card_character.view.*

class EpisodeAdapter(private val charactersInEpisode : List<Results>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cardEpisodeBinding = CardEpisodeBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(cardEpisodeBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterViewHolder){
            val characterView = charactersInEpisode[position]
            holder.onBind(characterView)
        }

    }
    override fun getItemCount(): Int {
        return charactersInEpisode.size
    }
    inner class CharacterViewHolder(private val cardEpisodeBinding: CardEpisodeBinding) : RecyclerView.ViewHolder(cardEpisodeBinding.root) {

        fun onBind(characterView: Results) {
            cardEpisodeBinding.character = characterView
        }

    }
}