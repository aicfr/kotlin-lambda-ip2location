package io.aicfr.lambda.ip2location.model.event

import java.net.HttpURLConnection.HTTP_OK

/**
 * @see "https://docs.aws.amazon.com/fr_fr/apigateway/latest/developerguide/set-up-lambda-proxy-integrations.html#api-gateway-simple-proxy-for-lambda-output-format"
 */
data class APIGatewayProxyResponseEvent(
	val statusCode: Int = HTTP_OK,
	val headers: Map<String, String> = hashMapOf(),
	val body: String = "",
	val isBase64Encoded: Boolean = false
)