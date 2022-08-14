package com.prashannar.mitosys.api

data class UserDetails(
    val address: String,
    val age: Int,
    val dob: String,
    val father: String,
    val gender: String,
    val group: String,
    val isAdmission: Boolean,
    val mother: String,
    val name: String,
    val phone: Long,
    val snacks: Int,
    val speechTherapy: Int,
    val studentId: String,
    val therapy: Int,
    val transportation: Int,
    val tuition: Int
)