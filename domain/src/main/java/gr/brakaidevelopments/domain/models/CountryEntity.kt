/*
 * Copyright (c) 2019.
 * Developer: Klainti brakai
 */

package gr.brakaidevelopments.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class CountryEntity(
    @SerializedName("country_id")
    val countryId: UUID = UUID.randomUUID(),
    @SerializedName("country_code")
    var countryCode: String,
    @SerializedName("country_name")
    var countryName: String
) : Parcelable
