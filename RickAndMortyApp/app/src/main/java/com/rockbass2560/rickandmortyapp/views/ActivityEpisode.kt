package com.rockbass2560.rickandmortyapp.views

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rockbass2560.rickandmortyapp.BASE_URL
import com.rockbass2560.rickandmortyapp.R
import com.rockbass2560.rickandmortyapp.adapters.CharacterAdapter
import com.rockbass2560.rickandmortyapp.adapters.EpisodeAdapter
import com.rockbass2560.rickandmortyapp.databinding.LayoutEpisodeBinding
import com.rockbass2560.rickandmortyapp.models.CharacterView
import com.rockbass2560.rickandmortyapp.models.Episode
import com.rockbass2560.rickandmortyapp.models.Results
import com.rockbass2560.rickandmortyapp.viewmodels.ActivityEpisodeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_episode.*

class ActivityEpisode(): AppCompatActivity() {

    val viewModelActivityEpisode: ActivityEpisodeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databind: LayoutEpisodeBinding = DataBindingUtil.setContentView(this,R.layout.layout_episode)

        val episodeURL : String? = intent.extras?.getString("episodeURL")

        val recyclerChar = databind.recyclerViewChar
        recyclerChar.layoutManager = LinearLayoutManager(this)

        viewModelActivityEpisode.rickAndMortyListLiveDataChar.observe(this,
            Observer<List<Results>>{
                val episodeAdapter = EpisodeAdapter(it)
                recyclerChar.adapter=episodeAdapter
                episodeAdapter.notifyDataSetChanged()
            })

        viewModelActivityEpisode.rickAndMortyListLiveData.observe(this,
            Observer<Episode>{
                databind.episode = it
                viewModelActivityEpisode.getCharacter(it.characters)
                databind.executePendingBindings()
            })

        viewModelActivityEpisode.getEpisode(episodeURL!!)

    }
}