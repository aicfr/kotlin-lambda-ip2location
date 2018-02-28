package io.aicfr.lambda.ip2location.model.event

/**
 * @see "https://docs.aws.amazon.com/fr_fr/apigateway/latest/developerguide/set-up-lambda-proxy-integrations.html#api-gateway-simple-proxy-for-lambda-input-format"
 */
data class APIGatewayProxyRequestEvent(
	val queryStringParameters: Map<String, String> = hashMapOf()
)