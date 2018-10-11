package com.beviswang.retrofitlib.model

import com.google.gson.annotations.SerializedName

/** 段子 */
class SatinModel {
    @SerializedName("bimageuri")
    var imageUri: String = ""                      // 封面
    @SerializedName("bookmark")
    var bookMark: Int = 0
    @SerializedName("cache_version")
    var cacheVersion: Int = 0
    var cai: Int = 0
    @SerializedName("cdn_img")
    var cdnImg: String = ""
    var comment: Int = 0
    @SerializedName("created_at")
    var createdAt: String = ""
    var ding: Int = 0
    var favourite: Int = 0
    var hate: Int = 0
    var love: Int = 0
    var name: String = ""
    @SerializedName("screen_name")
    var screenName: String = ""
    var type: Int = 0
    var text: String = ""

    @SerializedName("is_gif")
    var isGif: Boolean = false
    @SerializedName("videotime")
    var videoTime: Long = 0
    @SerializedName("videouri")
    var videoUri: String = ""
    var height: Int = 0
    var width: Int = 0
    @SerializedName("playcount")
    var playCount: Int = 0
    @SerializedName("playfcount")
    var playFCount: Int = 0

    @SerializedName("user_id")
    var userId: String = ""
    @SerializedName("profile_image")
    var profileImage: String = ""
    var repost: Int = 0
    @SerializedName("original_pid")
    var originalPId: String = ""

    @SerializedName("theme_id")
    var themeId: String = ""
    @SerializedName("theme_name")
    var themeName: String = ""
    @SerializedName("theme_type")
    var themeType: String = ""
}