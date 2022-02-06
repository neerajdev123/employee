package com.example.employee.model


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Geo (

    @SerializedName("lat" ) var lat : String,
    @SerializedName("lng" ) var lng : String

) : Serializable