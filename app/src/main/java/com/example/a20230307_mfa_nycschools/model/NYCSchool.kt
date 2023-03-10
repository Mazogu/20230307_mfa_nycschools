package com.example.a20230307_mfa_nycschools.model

import com.google.gson.annotations.SerializedName

/**
 * Simple data class for containing a gson with the name and dbn of the schools from the directory.
 * @property id School dbn
 * @property name School name
 */
data class NYCSchool(
    @SerializedName("dbn")
    val id:String,
    @SerializedName("school_name")
    val name:String
)