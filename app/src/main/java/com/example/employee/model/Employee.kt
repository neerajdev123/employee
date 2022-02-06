package com.example.employee.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Employee")
data class Employee  (
    @SerializedName("name"          ) var name         : String?,
    @SerializedName("username"      ) var username     : String?,
    @SerializedName("email"         ) var email        : String?,
    @SerializedName("profile_image" ) var profileImage : String?,
    @SerializedName("address"       ) @Embedded var address      : Address?,
    @SerializedName("phone"         ) var phone        : String?,
    @SerializedName("website"       ) var website      : String?,
    @SerializedName("company"       ) @Embedded (prefix = "company") var company      : Company?) : Serializable{

    @PrimaryKey(autoGenerate = false)
    var id: Int? = null

    companion object {
        @BindingAdapter("employeeImage")
        @JvmStatic
        fun loadImage(view: ImageView, imageUrl: String?) {
            Glide.with(view.getContext())
                .load(imageUrl).centerCrop()
                .into(view)
        }
    }
}
