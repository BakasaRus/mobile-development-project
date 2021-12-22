package ru.itmo.fitp.mobile

import java.util.*

data class Game(
    val id: String,
    val title: String,
    val description: String?,
    val price: Int?,
    val image: String,
    val cover: String,
    val rating: Int,
    val prettyRating: String,
    val url: String,
    val hasDemo: Boolean,
    val hasDigitalVersion: Boolean,
    val hasPhysicalVersion: Boolean,
    val publisher: String?,
    val dateReleased: Date,
    val prettyDateReleased: String,
    val dateUpdated: Date?
)