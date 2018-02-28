package io.aicfr.lambda.ip2location

import org.amshove.kluent.`should be equal to`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

class HandlerTestCase : Spek({
	describe("Test handle method") {
		it("should get location for 8.8.8.8") {
			val location = Handler().handle("8.8.8.8")
			location.countryCode `should be equal to` "US"
		}
	}
})