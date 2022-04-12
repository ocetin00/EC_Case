package com.oguzhancetin.androidprojecttemplate.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_detail")
data class UserDetail(
    @SerializedName("login") var login : String?,
    @SerializedName("id") var id : Int?,
    @SerializedName("node_id") var nodeId : String?,
    @SerializedName("avatar_url") var avatarUrl : String?,
    @SerializedName("gravatar_id") var gravatarId : String?,
    @SerializedName("url") var url : String?,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("html_url") var htmlUrl : String,
    @SerializedName("followers_url") var followersUrl : String?,
    @SerializedName("following_url") var followingUrl : String?,
    @SerializedName("gists_url") var gistsUrl : String?,
    @SerializedName("starred_url") var starredUrl : String?,
    @SerializedName("subscriptions_url") var subscriptionsUrl : String?,
    @SerializedName("organizations_url") var organizationsUrl : String?,
    @SerializedName("repos_url") var reposUrl : String?,
    @SerializedName("events_url") var eventsUrl : String,
    @SerializedName("received_events_url") var receivedEventsUrl : String?,
    @SerializedName("type") var type : String?,
    @SerializedName("site_admin") var siteAdmin : Boolean?,
    @SerializedName("name") var name : String?,
    @SerializedName("company") var company : String?,
    @SerializedName("blog") var blog : String?,
    @SerializedName("location") var location : String?,
    @SerializedName("email") var email : String?,
    @SerializedName("hireable") var hireable : String?,
    @SerializedName("bio") var bio : String?

)