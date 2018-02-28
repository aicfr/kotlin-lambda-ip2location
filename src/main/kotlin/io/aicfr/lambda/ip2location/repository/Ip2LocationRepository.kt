package io.aicfr.lambda.ip2location.repository

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.overriding
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import io.aicfr.lambda.ip2location.model.config.db
import io.aicfr.lambda.ip2location.model.location.Ip2Location
import io.aicfr.lambda.ip2location.model.location.Ip2LocationTable
import java.io.File

class Ip2LocationRepository {
	fun findLocationByIp(ip: Long?): Ip2Location? {
		val defaultProperties = File(javaClass.classLoader.getResource("defaults.properties")!!.file)

		val config = EnvironmentVariables() overriding
			ConfigurationProperties.fromFile(defaultProperties)

		Database.connect(config[db.url], config[db.driver], config[db.user], config[db.password])

		var ip2Location: Ip2Location? = null

		// Find
		transaction {
			Ip2LocationTable.select {
				Ip2LocationTable.ipFrom.lessEq(ip) and Ip2LocationTable.ipTo.greaterEq(ip)
			}.forEach {
					ip2Location = Ip2Location(
						it[Ip2LocationTable.ipFrom].toLong(),
						it[Ip2LocationTable.ipTo].toLong(),
						it[Ip2LocationTable.countryCode],
						it[Ip2LocationTable.countryName])
				}
		}

		return ip2Location
	}
}