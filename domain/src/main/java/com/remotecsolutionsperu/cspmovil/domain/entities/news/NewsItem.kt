package com.remotecsolutionsperu.cspmovil.domain.entities.news

import com.google.firebase.firestore.DocumentId

private const val TITLE_MAX_SIZE = 30

data class NewsItem (
//    @DocumentId val id: String = "",
    val image: String = "",
    val title: String = "",
    val content: String = "",
//    val date: String = ""
)

fun NewsItem.getTitle(): String {
    val isLongText = this.title.length > TITLE_MAX_SIZE
    val endRange = if (isLongText) TITLE_MAX_SIZE else this.title.length - 1
    return this.title.substring(IntRange(0, endRange))
}