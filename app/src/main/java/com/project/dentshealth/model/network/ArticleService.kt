package com.project.dentshealth.model.network

import com.project.dentshealth.model.Article
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ArticleService {

    @GET("/api/facts")
    fun getFacts(): Single<List<Article>>

    @GET("/api/tips")
    fun getTips(): Single<List<Article>>
}