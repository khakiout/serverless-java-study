package com.komiks.api.model;

import com.komiks.api.model.AuthPolicy.HttpMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains the metadata object for the AuthPolicy.
 */
public class PolicyMetadata {

    private Logger logger = LogManager.getLogger(this.getClass());

    String region;
    String awsAccountId;
    String restApiId;
    String stage;
    HttpMethod httpMethod;
    String resourcePath;

    /**
     * Construct the metadata based on the ARN obtained from the context.
     * @param methodArn the valid AWS ARN.
     */
    public PolicyMetadata(String methodArn) {
        logger.info("ARN is {}", methodArn);
        String[] arnPartials = methodArn.split(":");
        this.region = arnPartials[3];
        this.awsAccountId = arnPartials[4];

        String[] apiGatewayArnPartials = arnPartials[5].split("/");
        this.restApiId = apiGatewayArnPartials[0];
        this.stage = apiGatewayArnPartials[1];
        this.httpMethod = this.determineHttpMethod(apiGatewayArnPartials[2]);
        this.resourcePath = ""; // root resource
        if (apiGatewayArnPartials.length >= 4) {
            String[] resources = new String[apiGatewayArnPartials.length - 3];
            for (int i = 3,j = 0; i < apiGatewayArnPartials.length; i++,j++) {
                resources[j] = apiGatewayArnPartials[i];
            }
            this.resourcePath = String.join("/", resources);
        }
        logger.info("Resource {}", resourcePath);
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    private HttpMethod determineHttpMethod(String httpMethodString) {
        return HttpMethod.valueOf(httpMethodString);
    }
}
