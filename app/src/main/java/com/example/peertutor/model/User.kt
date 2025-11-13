package com.example.peertutor.model

data class User(
    val uid: String? = null,
    val name: String? = null,
    val email: String? = null,
    val role: String? = null,
    val course: String? = null,
    val units: List<String>? = null,
    val lessons: List<String>? = null
)
