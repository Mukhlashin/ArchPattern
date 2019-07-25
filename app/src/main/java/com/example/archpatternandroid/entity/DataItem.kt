package com.example.archpatternandroid.entity

import com.google.gson.annotations.SerializedName

data class DataItem(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("gcm_registrasi")
	val gcmRegistrasi: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("level")
	val level: String? = null,

	@field:SerializedName("tgl")
	val tgl: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)