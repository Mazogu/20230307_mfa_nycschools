package com.example.a20230307_mfa_nycschools.hilt

import com.example.a20230307_mfa_nycschools.retrofit.NYCSchoolAPI
import com.example.a20230307_mfa_nycschools.retrofit.NYCSchoolRepo
import com.example.a20230307_mfa_nycschools.retrofit.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule{
    @Provides
    fun provideRetrofit():NYCSchoolAPI {
        val retrofit = RetrofitHelper.getRetrofit(NYCSchoolRepo.URL)
        return retrofit.create(NYCSchoolAPI::class.java)
    }
}