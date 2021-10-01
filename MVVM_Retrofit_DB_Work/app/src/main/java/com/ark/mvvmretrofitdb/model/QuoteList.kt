package com.ark.mvvmretrofitdb.model
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "quote")
data class QuoteList(
//    @PrimaryKey(autoGenerate = true)
//    val quoteId:Int,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("lastItemIndex")
    val lastItemIndex: Int?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Results>?,
    @SerializedName("totalCount")
    val totalCount: Int?,
    @SerializedName("totalPages")
    val totalPages: Int?
)