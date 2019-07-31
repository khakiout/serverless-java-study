package com.komiks.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.komiks.api.domain.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class ListProductsHandler implements
    RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            Product product1 = new Product();
            product1.setId("1");
            product1.setName("Product 1");
            product1.setPrice(10000F);

            Product product2 = new Product();
            product2.setId("2");
            product2.setName("Product 2");
            product2.setPrice(20000F);

            List<Product> products = new ArrayList<>();
            products.add(product1);
            products.add(product2);

            // send the response back
            return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(products)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();

        } catch (Exception ex) {
            logger.error("Error in listing products: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in listing products: ", input);
            return ApiGatewayResponse.builder()
                .setStatusCode(500)
                .setObjectBody(responseBody)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
        }
    }
}
