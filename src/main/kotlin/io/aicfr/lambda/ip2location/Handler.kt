package io.aicfr.lambda.ip2location

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import com.beust.klaxon.Klaxon
import io.aicfr.lambda.ip2location.model.event.APIGatewayProxyRequestEvent
import io.aicfr.lambda.ip2location.model.event.APIGatewayProxyResponseEvent
import io.aicfr.lambda.ip2location.model.location.Ip2Location
import io.aicfr.lambda.ip2location.repository.Ip2LocationRepository
import java.io.InputStream
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.nio.charset.StandardCharsets.UTF_8

class Handler : RequestStreamHandler {
	override fun handleRequest(input: InputStream?, output: OutputStream?, context: Context?) {
		val logger = context?.logger
		val writer = OutputStreamWriter(output, UTF_8)
		val response: APIGatewayProxyResponseEvent

		val payload = Klaxon().parse<APIGatewayProxyRequestEvent>(input!!)
		val ip = payload?.queryStringParameters?.get("ip")
		logger?.log("Locate IP address " + ip)

		response = try {
			APIGatewayProxyResponseEvent(body = Klaxon().toJsonString(handle(ip)).replace("\"", "\\\""))
		} catch (nfe: NotFoundException) {
			APIGatewayProxyResponseEvent(statusCode = HTTP_NOT_FOUND)
		} catch (iae: IllegalArgumentException) {
			APIGatewayProxyResponseEvent(statusCode = HTTP_BAD_REQUEST)
		}

		writer.write(Klaxon().toJsonString(response))
		writer.close()
	}

	fun handle(ip: String?): Ip2Location = try {
		val ipLong = toLong(ip)
		val location = Ip2LocationRepository().findLocationByIp(ipLong)
		location?.let {
			return location
		} ?: throw NotFoundException()
	} catch (e: Exception) {
		throw IllegalArgumentException(e)
	}

	class NotFoundException : Throwable()
}