package gr.brakaidevelopments.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationEntity(
    @SerializedName("country")
    var country: String?,
    @SerializedName("county")
    var county: String?,
    @SerializedName("city")
    var city: String?,
    @SerializedName("address")
    var address: String?
) : Parcelable
