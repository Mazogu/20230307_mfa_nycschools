package com.example.a20230307_mfa_nycschools

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a20230307_mfa_nycschools.model.NYCSATScores
import com.example.a20230307_mfa_nycschools.model.NYCSchool
import com.example.a20230307_mfa_nycschools.retrofit.NYCSchoolRepo
import com.example.a20230307_mfa_nycschools.viewmodel.NYCViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import retrofit2.Response
import retrofit2.Retrofit

//Written in Kotlin because it calls suspended functions.
class NYCViewModelTest {
    private lateinit var viewModel: NYCViewModel

    private val repo: NYCSchoolRepo = mockk(relaxed = false)
    @Before
    fun preTest() {
        viewModel = NYCViewModel(repo)
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun noCalls(){
        Assert.assertNull(viewModel.schoolList.value)
        Assert.assertNull(viewModel.currentSchool.value)
    }

    @Test
    fun twoSchools() {
        val school1 = NYCSchool("2345","Taco University")
        val school2 = NYCSchool("5363","Hamburg Tech")
        val list = listOf(school1, school2)
        val response:Response<List<NYCSchool>> = Response.success(list)
        coEvery { repo.getNYCSchools() } answers { response }
        viewModel.getNYCSchools()
        Thread.sleep(500)
        Assert.assertEquals(viewModel.schoolList.value?.get(0)?.id, list[0].id)
        Assert.assertEquals(viewModel.schoolList.value?.get(0)?.name, list[0].name)
        Assert.assertEquals(viewModel.schoolList.value?.get(1)?.id, list[1].id)
        Assert.assertEquals(viewModel.schoolList.value?.get(1)?.name, list[1].name)
    }

    @Test
    fun networkException() {
        coEvery { repo.getNYCSchools() } answers { throw NetworkErrorException() }
        viewModel.getNYCSchools()
        Thread.sleep(500)
        Assert.assertTrue(viewModel.schoolList.value?.isEmpty() == true)
    }

    @Test
    fun responseFailure(){
        val response:Response<List<NYCSchool>> = Response.error(401, ResponseBody.create(null, "It failed."))
        coEvery { repo.getNYCSchools() } answers { response }
        viewModel.getNYCSchools()
        Thread.sleep(500)
        Assert.assertTrue(viewModel.schoolList.value?.isEmpty() == true)
    }

    @Test
    fun twoScores() {
        val school1 = NYCSATScores("2345","Taco University","103","343","490","410")
        val list = listOf(school1)
        val response:Response<List<NYCSATScores>> = Response.success(list)
        coEvery { repo.getSATScores("2345") } answers { response }
        viewModel.getSATScores("2345")
        Thread.sleep(500)
        Assert.assertEquals(viewModel.currentSchool.value?.id, school1.id)
        Assert.assertEquals(viewModel.currentSchool.value?.schoolName, school1.schoolName)
        Assert.assertEquals(viewModel.currentSchool.value?.numApplicants, school1.numApplicants)
        Assert.assertEquals(viewModel.currentSchool.value?.criticalReadingScore, school1.criticalReadingScore)
        Assert.assertEquals(viewModel.currentSchool.value?.mathScore, school1.mathScore)
        Assert.assertEquals(viewModel.currentSchool.value?.writingScore, school1.writingScore)
    }

    @Test
    fun networkExceptionSAT() {
        coEvery { repo.getSATScores("") } answers { throw NetworkErrorException() }
        viewModel.getSATScores("")
        Thread.sleep(500)
        Assert.assertTrue(viewModel.currentSchool.value?.schoolName == "Network Error")
    }

    @Test
    fun responseFailureSAT(){
        val response:Response<List<NYCSATScores>> = Response.error(401, ResponseBody.create(null, "It failed."))
        coEvery { repo.getSATScores("") } answers { response }
        viewModel.getSATScores("")
        Thread.sleep(500)
        Assert.assertTrue(viewModel.currentSchool.value?.schoolName == "School Not Found")
    }

    @Test
    fun noResult(){
        val response:Response<List<NYCSATScores>> = Response.success(listOf())
        coEvery { repo.getSATScores("") } answers { response }
        viewModel.getSATScores("")
        Thread.sleep(500)
        Assert.assertTrue(viewModel.currentSchool.value?.schoolName == "School Not Found")
    }
}