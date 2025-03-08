package com.projfirebase.projectefirebase.Model

import kotlinx.serialization.Serializable

@Serializable
    data class Items(
        val id: Int,
        val name: String,
        val craftable: Boolean,
        val stackable: Boolean,
        val stack_limit: Int
    )
