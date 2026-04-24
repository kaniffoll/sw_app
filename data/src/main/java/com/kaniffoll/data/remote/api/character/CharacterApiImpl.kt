package com.kaniffoll.data.remote.api.character

import com.kaniffoll.data.remote.RemoteRes.BASE_URL
import com.kaniffoll.data.remote.RemoteRes.PEOPLE_PATH
import com.kaniffoll.data.remote.model.ApiResponse
import com.kaniffoll.data.remote.model.CharacterDto
import com.kaniffoll.data.session.CacheHolder
import com.kaniffoll.data.session.CacheState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CharacterApiImpl(
    private val client: HttpClient
) : CharacterApi {

    override suspend fun getCharacters(url: String): ApiResponse<CharacterDto> {
        val response = client.get(url)
        return response.body()
    }

    override suspend fun getCharacterById(id: Int): CharacterDto {
        val response = client.get("${BASE_URL}$PEOPLE_PATH/${id}/")
        return response.body()
    }
}