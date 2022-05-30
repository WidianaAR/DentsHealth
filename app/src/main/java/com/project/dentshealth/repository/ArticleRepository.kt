package com.project.dentshealth.repository

import com.project.dentshealth.model.Article
import com.project.dentshealth.model.network.ArticleService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val articleService: ArticleService
) {

    fun getFacts(): Single<List<Article>> {
        return articleService.getFacts().subscribeOn(Schedulers.io())
    }

    fun getTips(): Single<List<Article>> {
        return articleService.getTips().subscribeOn(Schedulers.io())
    }
}