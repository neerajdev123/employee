package com.example.employee.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Address (

    @SerializedName("street"  ) var street  : String,
    @SerializedName("suite"   ) var suite   : String,
    @SerializedName("city"    ) var city    : String,
    @SerializedName("zipcode" ) var zipcode : String,
    @SerializedName("geo"     ) @Embedded var  geo     : Geo?

) : Serializable

