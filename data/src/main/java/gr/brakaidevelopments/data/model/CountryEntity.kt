/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class CountryEntity(
    @SerializedName("country_id")
    val countryId: UUID = UUID.randomUUID(),
    @SerializedName("country_code")
    var countryCode: String,
    @SerializedName("country_name")
    var countryName: String
)
