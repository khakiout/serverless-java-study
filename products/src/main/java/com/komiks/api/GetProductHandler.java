package com.komiks.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.komiks.api.domain.Product;
import java.util.Collections;
import java.util.Map;
import org.apache.log4j.Logger;

public class GetProductHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

        try {
            // get the 'pathParameters' from input
            @SuppressWarnings("unchecked")
            Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
            String productId = pathParameters.get("id");

            // get the Product by id
            Product product = null;
            if (productId.equalsIgnoreCase("1")) {
                product = new Product();
                product.setId(productId);
                product.setName("Product 1");
                product.setPrice(10000F);
            }

            // send the response back
            if (product != null) {
                return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(product)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .build();
            } else {
                return ApiGatewayResponse.builder()
                    .setStatusCode(404)
                    .setObjectBody("Product with id: '" + productId + "' not found.")
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .build();
            }
        } catch (Exception ex) {
            logger.error("Error in retrieving product: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in retrieving product: ", input);
            return ApiGatewayResponse.builder()
                .setStatusCode(500)
                .setObjectBody(responseBody)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
        }
    }
}
