package com.komiks.api.model;

import static org.junit.Assert.assertEquals;

import com.komiks.api.model.AuthPolicy.HttpMethod;
import org.junit.Test;

public class PolicyMetadataTest {

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testConstructorWithInvalidArnMustThrowError() {
        PolicyMetadata metadata = new PolicyMetadata("");
    }

    @Test
    public void testConstructorWithValidSingleResource() {
        String arn = "arn:aws:execute-api:southeast:1234:oqs9wx2516/dev/GET/users";
        PolicyMetadata metadata = new PolicyMetadata(arn);

        assertEquals("southeast", metadata.region);
        assertEquals("1234", metadata.awsAccountId);
        assertEquals("oqs9wx2516", metadata.restApiId);
        assertEquals("dev", metadata.stage);
        assertEquals(HttpMethod.GET, metadata.httpMethod);
        assertEquals("users", metadata.resourcePath);
    }

    @Test
    public void testConstructorWithSingleNestedResource() {
        String arn = "arn:aws:execute-api:southeast:12345:oqs9wx25216/dev/PUT/users/123";
        PolicyMetadata metadata = new PolicyMetadata(arn);

        assertEquals("southeast", metadata.region);
        assertEquals("12345", metadata.awsAccountId);
        assertEquals("oqs9wx25216", metadata.restApiId);
        assertEquals("dev", metadata.stage);
        assertEquals(HttpMethod.PUT, metadata.httpMethod);
        assertEquals("users/123", metadata.resourcePath);
    }

    @Test
    public void testConstructorWithDeeplyNestedResource() {
        String arn = "arn:aws:execute-api:southeast:12345:oqs9wx25216/dev/PUT/users/123/12311";
        PolicyMetadata metadata = new PolicyMetadata(arn);

        assertEquals("southeast", metadata.region);
        assertEquals("12345", metadata.awsAccountId);
        assertEquals("oqs9wx25216", metadata.restApiId);
        assertEquals("dev", metadata.stage);
        assertEquals(HttpMethod.PUT, metadata.httpMethod);
        assertEquals("users/123/12311", metadata.resourcePath);
    }

    @Test
    public void testConstructorWithTrailingPaths() {
        String arn = "arn:aws:execute-api:southeast:12345:oqs9wx25216/dev/POST/users/123/12311/";
        PolicyMetadata metadata = new PolicyMetadata(arn);

        assertEquals("southeast", metadata.region);
        assertEquals("12345", metadata.awsAccountId);
        assertEquals("oqs9wx25216", metadata.restApiId);
        assertEquals("dev", metadata.stage);
        assertEquals(HttpMethod.POST, metadata.httpMethod);
        assertEquals("users/123/12311", metadata.resourcePath);
    }

    @Test
    public void testConstructorWithTrailingPathsAtIndex() {
        String arn = "arn:aws:execute-api:southeast:12345:oqs9wx25216/prod/GET/users/";
        PolicyMetadata metadata = new PolicyMetadata(arn);

        assertEquals("southeast", metadata.region);
        assertEquals("12345", metadata.awsAccountId);
        assertEquals("oqs9wx25216", metadata.restApiId);
        assertEquals("prod", metadata.stage);
        assertEquals(HttpMethod.GET, metadata.httpMethod);
        assertEquals("users", metadata.resourcePath);
    }

    @Test
    public void testConstructorWithNoResource() {
        String arn = "arn:aws:execute-api:southeast:12345:oqs9wx25216/prod/GET/";
        PolicyMetadata metadata = new PolicyMetadata(arn);

        assertEquals("southeast", metadata.region);
        assertEquals("12345", metadata.awsAccountId);
        assertEquals("oqs9wx25216", metadata.restApiId);
        assertEquals("prod", metadata.stage);
        assertEquals(HttpMethod.GET, metadata.httpMethod);
        assertEquals("", metadata.resourcePath);
    }

}
