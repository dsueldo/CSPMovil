package com.remotecsolutionsperu.cspmovil.domain.entities.benefits

data class BenefitsItem(
    val image: String,
    val title: String,
    val content: String,
    val order: Int,
) {
    companion object {
        fun empty() = BenefitsItem("", "", "", 0)
    }
}