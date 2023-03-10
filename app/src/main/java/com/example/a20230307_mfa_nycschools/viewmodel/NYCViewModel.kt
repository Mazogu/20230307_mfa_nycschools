package com.example.a20230307_mfa_nycschools.viewmodel

import androidx.lifecycle.*
import com.example.a20230307_mfa_nycschools.model.NYCSATScores
import com.example.a20230307_mfa_nycschools.model.NYCSchool
import com.example.a20230307_mfa_nycschools.retrofit.NYCSchoolRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * View model for handling NYC school requests.
 * Written in Kotlin because I like coroutines and suspended functions.
 * @param repo [NYCSchoolRepo] Repository for necessary network calls.
 */
@Suppress("UNCHECKED_CAST")
class NYCViewModel(private val repo:NYCSchoolRepo):ViewModel() {
    private val _schoolList = MutableLiveData<List<NYCSchool>>()
    val schoolList get() = _schoolList as LiveData<List<NYCSchool>>
    private val _currentSchool = MutableLiveData<NYCSATScores>()
    val currentSchool get() = _currentSchool as LiveData<NYCSATScores>


    /**
     * Coroutine call for getting NYC school list
     */
    fun getNYCSchools(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                Timber.d("Coroutine Launched")
                val response = repo.getNYCSchools()
                if (response.isSuccessful) {
                    Timber.d("Response Successful")
                    _schoolList.postValue(response.body())
                } else {
                    _schoolList.postValue(listOf())
                    Timber.e(response.errorBody()?.string())
                }
            }
            catch (e:Exception){
                _schoolList.postValue(listOf())
                Timber.e(e)
            }
        }
    }

    /**
     * Coroutine call for getting the SAT scores of a chosen school
     * @param id The [String] dbn of the school.
     */
    fun getSATScores(id:String){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val response = repo.getSATScores(id)
                if (response.isSuccessful && response.body()?.isNotEmpty() == true) {
                    val school = response.body()!![0]
                    Timber.d("Name: ${school.schoolName} Number:${school.numApplicants}")
                    _currentSchool.postValue(response.body()!![0])
                } else {
                    //I'd rather have the strings be from @string
                    val fakeStudent = NYCSATScores("N/A", "School Not Found", "N/A", "N/A", "N/A", "N/A")
                    _currentSchool.postValue(fakeStudent)
                    Timber.e(response.errorBody()?.string())
                }
            }
            catch (e:Exception){
                val fakeStudent = NYCSATScores("N/A", "Network Error", "N/A", "N/A", "N/A", "N/A")
                _currentSchool.postValue(fakeStudent)
                Timber.e(e)
            }
        }
    }

    companion object{
        /**
         * Custom [NYCViewModel] provider.
         */
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return NYCViewModel(NYCSchoolRepo()) as T
            }
        }
    }

}