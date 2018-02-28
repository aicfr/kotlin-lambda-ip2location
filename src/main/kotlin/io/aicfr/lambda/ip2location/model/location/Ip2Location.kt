package io.aicfr.lambda.ip2location.model.location

// DTO
data class Ip2Location(
	val ipFrom: Long,
	val ipTo: Long,
	val countryCode: String,
	val countryName: String
)