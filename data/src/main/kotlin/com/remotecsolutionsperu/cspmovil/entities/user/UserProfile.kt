package com.remotecsolutionsperu.cspmovil.entities.user

data class UserProfile(
    val uid: String,
    val code: String,
    val email: String,
    val password: String,
    val fullName: String,
    val phone: String,
    val councilDepartamental: String,
    val category: String,
    val condition: String,
    val lastPaidPeriod: String,
    val emailConstitutional: String,
    val universityDegree: String,
    val major: String,
    val role: String,
    val accessFirstTime: String,
    val active: String,
    val createdAt: String,
    val modifiedAt: String,
    val urlImageProfile: String,
)