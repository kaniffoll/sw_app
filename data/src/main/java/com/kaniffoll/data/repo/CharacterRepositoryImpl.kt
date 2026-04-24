package com.kaniffoll.data.repo

import com.kaniffoll.data.remote.RemoteRes.BASE_URL
import com.kaniffoll.data.remote.RemoteRes.PEOPLE_PATH
import com.kaniffoll.data.remote.api.character.CharacterApi
import com.kaniffoll.data.remote.model.utils.toCharacter
import com.kaniffoll.domain.model.Character
import com.kaniffoll.domain.model.Page
import com.kaniffoll.domain.repo.CharacterRepository

class CharacterRepositoryImpl(
    private val api: CharacterApi
) : CharacterRepository {
    override suspend fun getCharacters(url: String?): Result<Page<Character>> {
        try {
            val req = url ?: "$BASE_URL$PEOPLE_PATH/"

            val response = api.getCharacters(req)
            return Result.success(
                Page(
                    items = response.results.map { it.toCharacter() }, nextKey = response.next
                )
            )
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
}