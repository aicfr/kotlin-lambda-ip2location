package io.aicfr.lambda.ip2location

import java.util.*

var IPV4_PATTERN = Regex("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")

fun toLong(ip: String?): Long {
	if (Objects.isNull(ip) || !IPV4_PATTERN.matches(ip!!)) {
		throw IllegalArgumentException()
	}

	val ipAddressInArray = ip.split(".")

	var result: Long = 0
	for (i in ipAddressInArray.indices) {
		val power = 3 - i
		result += (Integer.parseInt(ipAddressInArray[i]) * Math.pow(256.0, power.toDouble())).toLong()
	}

	return result
}