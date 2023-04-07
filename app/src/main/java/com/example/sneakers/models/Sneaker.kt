package com.example.sneakers.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonParser


@Entity(tableName = "sneakers")
data class Sneaker(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val brand: String?,
    val colorway: String?,
    val gender: String?,
    val media: Media?,
    val releaseDate: String?,
    val retailPrice: Int?,
    val styleId: String?,
    val shoe: String?,
    val name: String?,
    val title: String?,
    val year: Int?,
    val cartAdded: Int?,
    val detailAdded: Int?
)

data class Media(

    val imageUrl: String?,
    val smallImageUrl: String?,
    val thumbUrl: String?
)

class Converters {
    @TypeConverter
    fun fromMedia(media: Media?): String? {
        return Gson().toJson(media)
    }

    @TypeConverter
    fun toMedia(mediaJson: String?): Media? {
        val jsonElement = JsonParser.parseString(mediaJson)
        return if (jsonElement.isJsonObject)
            Gson().fromJson(mediaJson, Media::class.java)
        else
            Media("", "", "")
    }
}

class SneakersList : ArrayList<Sneaker>()
