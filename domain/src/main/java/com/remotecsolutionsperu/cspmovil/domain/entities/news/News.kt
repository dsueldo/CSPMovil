package com.remotecsolutionsperu.cspmovil.domain.entities.news

import com.google.firebase.firestore.DocumentId

private const val TITLE_MAX_SIZE = 30

data class News (
    @DocumentId val id: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val date: String = ""
)

fun News.getTitle(): String {
    val isLongText = this.description.length > TITLE_MAX_SIZE
    val endRange = if (isLongText) TITLE_MAX_SIZE else this.description.length - 1
    return this.description.substring(IntRange(0, endRange))
}