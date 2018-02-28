package io.aicfr.lambda.ip2location.model.config

import com.natpryce.konfig.PropertyGroup
import com.natpryce.konfig.getValue
import com.natpryce.konfig.stringType

// DTO config
object db : PropertyGroup() {
	val url by stringType
	val driver by stringType
	val user by stringType
	val password by stringType
}