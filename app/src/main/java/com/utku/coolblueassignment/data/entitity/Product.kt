package com.utku.coolblueassignment.data.entitity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val productId: Int = 0,
    val productName: String = "",
    val reviewInformation: ReviewInformation = ReviewInformation(),
    @SerialName("USPs") val usps: List<String> = listOf(),
    val availabilityState: Int = 0,
    val salesPriceIncVat: Double = 0.0,
    val productImage: String = "",
    val coolbluesChoiceInformationTitle: String = "",
    val promoIcon: PromoIcon = PromoIcon(),
    val nextDayDelivery: Boolean = false
)

