package com.rogok.glideapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rogok.glideapp.api.JsonPlaceholderApi
import com.rogok.glideapp.models.User
import retrofit2.HttpException
import java.io.IOException

const val DEFAULT_PAGE_INDEX = 1

class PlaceholderPagingSource(
    private val api: JsonPlaceholderApi
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = api.getUsers(page, params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }
}