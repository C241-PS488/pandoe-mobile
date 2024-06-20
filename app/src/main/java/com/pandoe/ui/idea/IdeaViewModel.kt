package com.pandoe.ui.idea

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandoe.data.model.Idea
import com.pandoe.data.repository.IdeaRepository

class IdeaViewModel(private val repository: IdeaRepository) : ViewModel() {

    fun generateIdeas(): LiveData<Idea> {
        val dummyIdeasLiveData = MutableLiveData<Idea>()
        dummyIdeasLiveData.value = getDummyIdeas()
        return dummyIdeasLiveData
    }

    private fun getDummyIdeas(): Idea {
        return Idea(
            data = listOf(
                "Katering Rumahan",
                "Jasa Memasak",
                "Kanal Youtube Memasak"
            )
        )
    }
}