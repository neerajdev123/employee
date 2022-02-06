package com.example.employee.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Company (

    @SerializedName("name"        ) var name        : String,
    @SerializedName("catchPhrase" ) var catchPhrase : String,
    @SerializedName("bs"          ) var bs          : String

) : Serializable