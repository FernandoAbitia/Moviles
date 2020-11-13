package com.rockbass2560.rickandmortyapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rockbass2560.rickandmortyapp.models.CharacterView
import com.rockbass2560.rickandmortyapp.models.Episode
import com.rockbass2560.rickandmortyapp.models.Results
import com.rockbass2560.rickandmortyapp.repositories.RickAndMortyRepository
import kotlinx.coroutines.launch

class ActivityEpisodeViewModel(application: Application) : AndroidViewModel(application) {

    val rickAndMortyListLiveData = MutableLiveData<Episode>()
    val rickAndMortyListLiveDataChar = MutableLiveData<List<Results>>()

    val rickAndMortyRepository = RickAndMortyRepository()

    fun getEpisode(url: String) {
        viewModelScope.launch {
            rickAndMortyListLiveData.postValue(rickAndMortyRepository.getEpisodeByUrl(url))
        }
    }

    fun getCharacter(url: List<String>){
        viewModelScope.launch {
            rickAndMortyListLiveDataChar.postValue(url.map { url -> rickAndMortyRepository.getCharacterByUrl(url) })
        }
    }
}