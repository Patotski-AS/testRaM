package com.example.android.testram.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.android.testram.R
import com.example.android.testram.data.mappers.MapCharacterPojoToCharacterInfo
import com.example.android.testram.data.network.TestRaMApiService
import com.example.android.testram.data.paging.SearchPagingSource
import com.example.android.testram.domain.model.CharacterInfo
import com.example.android.testram.domain.repository.CharacterRepository
import com.example.android.testram.domain.utill.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_SIZE = 40

class CharacterRepositoryImpl(
    private val service: TestRaMApiService,
    private val mapCharacterPojoToCharacterInfo: MapCharacterPojoToCharacterInfo,
    private val context: Context
) : CharacterRepository {

    override fun getListCharacters(): Flow<PagingData<CharacterInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = STARTING_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    service = service
                )
            }
        ).flow.map { pagingData ->
            pagingData.map {
                mapCharacterPojoToCharacterInfo.map(it)
            }
        }
    }

    override fun getCharacter(id: String): Flow<Resource<CharacterInfo>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.getCharacter(id)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(Resource.Success(data = mapCharacterPojoToCharacterInfo.map(body)))
                }
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(context.getString(R.string.message_http_error))
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(context.getString(R.string.message_internet_connecton_error))
            )
        }
    }

//    override fun getCharacters(): Flow<PagingData<CharacterInfo>> {
//
//        @OptIn(ExperimentalPagingApi::class)
//        return Pager(
//            config = PagingConfig(
//                pageSize = STARTING_PAGE_SIZE,
//                enablePlaceholders = false
//            ),
//            remoteMediator = CharactersRemoteMediator(
//                database = database,
//                service = service,
//                mapCharacterPojoToCharacterEntity = mapCharacterPojoToCharacterEntity
//            ),
//            pagingSourceFactory = { database.characterDAO().getAllCharacters() }
//        ).flow.map { pagingData ->
//            pagingData.map  { mapCharacterEntityToCharacterInfo.map(it) }
//        }
//    }
}