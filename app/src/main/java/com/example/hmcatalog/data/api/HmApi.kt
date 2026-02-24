package com.example.hmcatalog.data.api

import com.example.hmcatalog.data.dto.ProductResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HmApi {
    @GET("search-services/v1/sv_se/search/resultpage")
    suspend fun searchProducts(
        @Query("touchPoint") touchPoint: String = "android",
        @Query("query") query: String = "jeans",
        @Query("page") page: Int,
    ): ProductResponseDto
}