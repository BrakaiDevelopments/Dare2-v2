package gr.brakaidevelopments.data.model

import com.google.gson.annotations.SerializedName

data class LocationEntity(
    @SerializedName("country")
    var country: String?,
    @SerializedName("county")
    var county: String?,
    @SerializedName("city")
    var city: String?,
    @SerializedName("address")
    var address: String?
)
