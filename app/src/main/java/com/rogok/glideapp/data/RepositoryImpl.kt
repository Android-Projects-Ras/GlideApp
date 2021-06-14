package com.rogok.glideapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rogok.glideapp.api.JsonPlaceholderApi
import com.rogok.glideapp.models.User
import kotlinx.coroutines.flow.Flow
import java.util.ArrayList

interface UserRepository {
    //suspend fun getUsers(): ArrayList<User>
    fun getUsersFlow(/*pagingConfig: PagingConfig*/): Flow<PagingData<User>>
}

const val DEFAULT_PAGE_SIZE = 10

class UserRepositoryImpl(
    private val api: JsonPlaceholderApi
): UserRepository {

    /*override suspend fun getUsers(): ArrayList<User> =
        api.getUsers(0, 100)*/

    override fun getUsersFlow(/*pagingConfig: PagingConfig*/): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PlaceholderPagingSource(api) }
        ).flow
    }
}