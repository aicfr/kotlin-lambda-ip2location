package io.aicfr.lambda.ip2location

import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldThrow
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class HandlerHelperTestCase : Spek({
	describe("IPv4 to long") {
		it("should return the long value of IP") {
			val ipLong = toLong("8.8.8.8")
			ipLong shouldEqualTo 134744072
		}

		it("should return an IllegalArgumentException with bad IP") {
			val illegalArgumentExceptionFunction = { toLong("8888") }
			illegalArgumentExceptionFunction shouldThrow IllegalArgumentException::class
		}

		it("should return an IllegalArgumentException with IP equal null") {
			val illegalArgumentExceptionFunction = { toLong(null) }
			illegalArgumentExceptionFunction shouldThrow IllegalArgumentException::class
		}
	}
})