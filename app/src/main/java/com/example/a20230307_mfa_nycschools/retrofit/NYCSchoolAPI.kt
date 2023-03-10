package com.example.a20230307_mfa_nycschools.retrofit

import com.example.a20230307_mfa_nycschools.model.NYCSATScores
import com.example.a20230307_mfa_nycschools.model.NYCSchool
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface for NYC directory calls.
 */
interface NYCSchoolAPI {

    /**
     * Call to NYC directory API for the school directory.
     * @return A [Response] with a list of [NYCSchool].
     */
    @GET("s3k6-pzi2.json")
    suspend fun getNYCSchools(): Response<List<NYCSchool>>

    /**
     * Call to SAT API to receive school SAT test scores.
     * @param schoolId The dbn of the school to query the search results
     * @return A[Response] with a filtered list of [NYCSATScores] scores by school.
     */
    @GET("f9bf-2cp4.json")
    suspend fun getSATScores(@Query("dbn") schoolId: String): Response<List<NYCSATScores>>
}