package com.huntercodexs.unittestsdemo.integration;

import com.huntercodexs.unittestsdemo.abstractor.IntegrationAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadUserByIdTest extends IntegrationAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Read User by id
     */
    @Test
    public void whenRequestToGetExternalUserWithInvalidCredentials_RetrieveUnauthorized_401() throws Exception {
        /*NOTE: Change the application.properties in external.basic-auth field or use external.basic-auth-invalid*/
        try {
            unauthorizedByHttpGet(
                    props.getProperty("integration.test.get-user-uri"),
                    props.getProperty("integration.test.get-user-by-id"));
        } catch (Exception e) {
            assertIntegration("401 Null", e.getMessage());
        }
    }

    @Test
    public void whenRequestToGetUserByIdThatNotExists_RetrieveUserNotFound_404() throws Exception {
        try {
            notFoundByHttpGet(
                    props.getProperty("integration.test.get-user-uri"),
                    props.getProperty("integration.test.get-user-by-id-not-found"));
        } catch (Exception e) {
            assertIntegration("404 Not Found", e.getMessage());
        }
    }

    @Test
    public void whenRequestToGetUserByIdNonInteger_RetrieveBadRequest_400() throws Exception {
        try {
            badRequestByHttpGet(
                    props.getProperty("integration.test.get-user-uri"),
                    props.getProperty("integration.test.get-user-by-id-non-integer"));
        } catch (Exception e) {
            assertIntegration("400 Bad Request", e.getMessage());
        }
    }

    @Test
    public void whenRequestToGetUserByIdButServerError_RetrieveServerError_500() throws Exception {
        System.out.println("whenRequestToGetUserByIdButServerError_RetrieveServerError_500 is Ignored !");
    }

    @Test
    public void whenCorrectRequestToGetUserById_RetrieveUserFound_200() throws Exception {
        try {
            okByHttpGet(
                    props.getProperty("integration.test.get-user-uri"),
                    props.getProperty("integration.test.get-user-by-id"));
        } catch (Exception e) {
            assertIntegration("200 OK", e.getMessage());
        }
    }

}
