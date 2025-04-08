package com.remotecsolutionsperu.cspmovil.domain.repositories

import com.remotecsolutionsperu.cspmovil.domain.entities.benefits.Benefits

interface BenefitsRepository {
    suspend fun getAllBenefits(): List<Benefits>
    suspend fun getBenefitsDetail(benefitsId: String): Benefits
}