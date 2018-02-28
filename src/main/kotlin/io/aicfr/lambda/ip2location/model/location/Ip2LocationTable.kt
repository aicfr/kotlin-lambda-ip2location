package io.aicfr.lambda.ip2location.model.location

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

// DAO
object Ip2LocationTable : Table("ip2location") {
	val ipFrom: Column<Long> = long("ip_from")
	val ipTo: Column<Long> = long("ip_to")
	val countryCode: Column<String> = varchar("country_code", 2)
	val countryName: Column<String> = varchar("country_name", 64)
}