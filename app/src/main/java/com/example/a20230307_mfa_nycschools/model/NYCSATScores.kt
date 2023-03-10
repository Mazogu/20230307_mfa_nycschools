package com.example.a20230307_mfa_nycschools.model

import com.google.gson.annotations.SerializedName

/**
 * Data class for containing SAT response Gson.
 * @property id dbn for the school
 * @property schoolName The name of the school
 * @property numApplicants The number of students that took the SAT
 * @property criticalReadingScore The average critical reading score
 * @property mathScore The average math score
 * @property writingScore The average writing score
 */
data class NYCSATScores(
    @SerializedName("dbn")
    val id:String,
    @SerializedName("school_name")
    val schoolName:String,
    @SerializedName("num_of_sat_test_takers")
    val numApplicants:String,
    @SerializedName("sat_critical_reading_avg_score")
    val criticalReadingScore:String,
    @SerializedName("sat_math_avg_score")
    val mathScore:String,
    @SerializedName("sat_writing_avg_score")
    val writingScore:String
)