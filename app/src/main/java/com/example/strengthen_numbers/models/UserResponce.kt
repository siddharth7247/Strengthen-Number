package com.example.example

import com.google.gson.annotations.SerializedName
data class UserResponce (
    @SerializedName("data" ) var data : UserData? = UserData(),
    @SerializedName("meta" ) var meta : UserMeta? = UserMeta()
)

data class UserData (
    @SerializedName("id"               ) var id              : Int?    = null,
    @SerializedName("name"             ) var name            : String? = null,
    @SerializedName("username"         ) var username        : String? = null,
    @SerializedName("email"            ) var email           : String? = null,
    @SerializedName("contact_number"   ) var contactNumber   : String? = null,
    @SerializedName("dob"              ) var dob             : String? = null,
    @SerializedName("bio"              ) var bio             : String? = null,
    @SerializedName("gender"           ) var gender          : String? = null,
    @SerializedName("latitude"         ) var latitude        : Int?    = null,
    @SerializedName("longitude"        ) var longitude       : Int?    = null,
    @SerializedName("address"          ) var address         : String? = null,
    @SerializedName("fitness_level"    ) var fitnessLevel    : String? = null,
    @SerializedName("interests"        ) var interests       : String? = null,
    @SerializedName("profile_photo"    ) var profilePhoto    : String? = null,
    @SerializedName("registered_at"    ) var registeredAt    : String? = null,
    @SerializedName("total_followers"  ) var totalFollowers  : Int?    = null,
    @SerializedName("total_followings" ) var totalFollowings : Int?    = null,
    @SerializedName("is_following"     ) var isFollowing     : Int?    = null,
    @SerializedName("is_blocked"       ) var isBlocked       : Int?    = null
)

data class UserMeta (
    @SerializedName("message" ) var message : String? = null
)