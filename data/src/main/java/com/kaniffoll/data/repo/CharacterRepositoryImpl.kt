package com.kaniffoll.data.repo

import com.kaniffoll.data.local.CharacterDao
import com.kaniffoll.data.remote.RemoteRes.BASE_URL
import com.kaniffoll.data.remote.RemoteRes.PEOPLE_PATH
import com.kaniffoll.data.remote.api.character.CharacterApi
import com.kaniffoll.data.utils.NetworkConnectivityObserver
import com.kaniffoll.data.utils.toCharacter
import com.kaniffoll.data.utils.toEntity
import com.kaniffoll.domain.model.Character
import com.kaniffoll.domain.model.Page
import com.kaniffoll.domain.repo.CharacterRepository
import jakarta.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharacterApi,
    private val dao: CharacterDao,
    private val connectivityObserver: NetworkConnectivityObserver
) : CharacterRepository {
    override suspend fun getCharacters(url: String?): Result<Page<Character>> {
        try {
            val page = if (connectivityObserver.isNetworkAvailable())
                getCharsApiRequest(url)
            else
                getCharsDaoRequest()

            return Result.success(page)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun getCharacterById(id: Int): Result<Character> {
        return try {
            Result.success(api.getCharacterById(id).toCharacter())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun getCharsApiRequest(url: String?): Page<Character> {
        val req = url ?: "$BASE_URL$PEOPLE_PATH/"
        val response = api.getCharacters(req)

        dao.saveCharacters(response.results.map { it.toEntity() } )
        return Page(
            items = response.results.map { it.toCharacter() }, nextKey = response.next
        )
    }

    private suspend fun getCharsDaoRequest(): Page<Character> {
        val response = dao.getCharacters().map { it.toCharacter() }
        return Page(items = response, nextKey = null)
    }
}