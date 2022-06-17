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
    public void whenRequestToGetUserByIdUnauthorized_RetrieveUnauthorized_401() throws Exception {
        unauthorizedByHttpGet(
                props.getProperty("integration.test.get-user-uri"),
                props.getProperty("integration.test.get-user-by-id"));
    }

    @Test
    public void whenRequestToGetUserByIdThatNotExists_RetrieveUserNotFound_404() throws Exception {
        notFoundByHttpGet(
                props.getProperty("integration.test.get-user-uri"),
                props.getProperty("integration.test.get-user-by-id-not-found"));
    }

    @Test
    public void whenRequestToGetUserByIdNonInteger_RetrieveBadRequest_400() throws Exception {
        badRequestByHttpGet(
                props.getProperty("integration.test.get-user-uri"),
                props.getProperty("integration.test.get-user-by-id-non-integer"));
    }

    @Test
    public void whenRequestToGetUserByIdButServerError_RetrieveServerError_500() throws Exception {
        System.out.println("whenRequestToGetUserByIdButServerError_RetrieveServerError_500 is Ignored !");
    }

    @Test
    public void whenCorrectRequestToGetUserById_RetrieveUserFound_200() throws Exception {
        okByHttpGet(
                props.getProperty("integration.test.get-user-uri"),
                props.getProperty("integration.test.get-user-by-id"));
    }

    @Test
    public void whenRequestToGetExternalUserByIdThatNotExists_RetrieveUserNotFound_404() throws Exception {
        /*NOTE: Change the application.properties in external.basic-auth field*/
        try {
            notFoundByHttpGet(
                    props.getProperty("integration.test.get-user-uri"),
                    props.getProperty("integration.test.get-user-by-id-not-found"));
        } catch (Exception e) {
            assertionTest("Error on findUserId: 404 null", e.getMessage());
        }
    }

    @Test
    public void whenRequestToGetExternalUserWithInvalidCredentials_RetrieveUnauthorized_401() throws Exception {
        /*NOTE: Change the application.properties in external.basic-auth field*/
        try {
            unauthorizedByHttpGet(
                    props.getProperty("integration.test.get-user-uri"),
                    props.getProperty("integration.test.get-user-by-id"));
        } catch (Exception e) {
            assertionTest("Error on findUserId: 401 null", e.getMessage());
        }
    }

}
