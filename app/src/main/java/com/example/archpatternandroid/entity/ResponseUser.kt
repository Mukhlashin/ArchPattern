package com.example.archpatternandroid.entity

import com.google.gson.annotations.SerializedName

data class ResponseUser(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)