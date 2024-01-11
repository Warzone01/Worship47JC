package com.kirdevelopment.worship47compose.data.remote.dto

data class SongDto(
    val count: Int,
    val next: Int,
    val previous: Any,
    val results: List<SongData>
)

data class SongData(
    val author: String,
    val category: List<Category>,
    val chordKey1: String,
    val chordKey2: String,
    val chords: String,
    val chordsFile1: String,
    val chordsFile2: Any,
    val created: String,
    val difficult: String,
    val id: Int,
    val is_translated: Boolean,
    val main_key: String,
    val modified: String,
    val presentation: String,
    val text: String,
    val text_eng: String,
    val text_file: String,
    val title: String,
    val title_eng: String,
    val translator: List<Translator>,
    val ytb_id1: String,
    val ytb_id2: String,
    val ytb_id3: String
)

data class Category(
    val id: Int,
    val image: String,
    val priority: Int,
    val slug: String,
    val title: String
)

data class Translator(
    val count: Int,
    val id: Int,
    val name: String,
    val protected: Boolean,
    val slug: String
)