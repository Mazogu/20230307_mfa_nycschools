package com.example.a20230307_mfa_nycschools.retrofit

import com.example.a20230307_mfa_nycschools.model.NYCSATScores
import com.example.a20230307_mfa_nycschools.model.NYCSchool
import retrofit2.Response
import javax.inject.Inject

/**
 * Class to handle retrofit calls.
 * @property api An instance of [NYCSchoolAPI] using Retrofit.
 */
class NYCSchoolRepo @Inject constructor(private val api: NYCSchoolAPI) {

    /**
     * Retrofit call to retrieve a list of NYC schools.
     * @return A [Response] containing a List of [NYCSchool]
     */
    suspend fun getNYCSchools():Response<List<NYCSchool>> = api.getNYCSchools()

    /**
     * Retrofit call to retrieve the SAT scores of a given dbn of a school.
     * @param id The dbn of a given school.
     * @return A [Response] containing a queried list of [NYCSATScores]
     */
    suspend fun getSATScores(id: String): Response<List<NYCSATScores>> = api.getSATScores(id)

    companion object {
        const val URL = "https://data.cityofnewyork.us/resource/"
    }
}