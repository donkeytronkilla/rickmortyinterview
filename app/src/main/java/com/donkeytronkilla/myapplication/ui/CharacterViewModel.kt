package com.donkeytronkilla.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.donkeytronkilla.myapplication.api.RickMortyApi
import com.donkeytronkilla.myapplication.data.CharacterPagingSource
import com.donkeytronkilla.myapplication.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor (private val api: RickMortyApi): ViewModel() {
    val charactersPagingData: Flow<PagingData<Character>>

    init {

        charactersPagingData = Pager(
                config = PagingConfig(pageSize = RickMortyApi.PAGE_SIZE)
            ) {
           CharacterPagingSource(api)
        }.flow
    }

}