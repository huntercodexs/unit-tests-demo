package com.huntercodexs.unittestsdemo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadUserByIdTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Read User by id
     */

    @Test
    public void whenRequestToGetLocalUserByIdThatNotExists_RetrieveUserNotFound_404() throws Exception {
        notFoundByHttpGet(
                props.getProperty("test.local.read-user-uri"),
                props.getProperty("test.local.read-user-by-id-not-found"));
    }

    @Test
    public void whenRequestToGetLocalUserByIdNonInteger_RetrieveBadRequest_400() throws Exception {
        badRequestByHttpGet(
                props.getProperty("test.local.read-user-uri"),
                props.getProperty("test.local.read-user-by-id-non-integer"));
    }

    @Test
    public void whenRequestToGetLocalUserByIdButServerError_RetrieveServerError_500() throws Exception {
        System.out.println("whenRequestToGetLocalUserByIdButServerError_RetrieveServerError_500 is Ignored !");
    }

    @Test
    public void whenCorrectRequestToGetLocalUserById_RetrieveUserFound_200() throws Exception {
        okByHttpGet(
                props.getProperty("test.local.read-user-uri"),
                props.getProperty("test.local.read-user-by-id"));
    }

    @Test
    public void whenRequestToGetExternalUserByIdThatNotExists_RetrieveUserNotFound_404() throws Exception {
        try {
            notFoundByHttpGet(
                    props.getProperty("test.external.read-user-uri"),
                    props.getProperty("test.external.read-user-by-id-not-found"));
        } catch (Exception e) {
            tryAssertResponse("Error on findUserId: 404 null", e.getMessage());
        }
    }

    @Test
    public void whenRequestToGetExternalUserWithInvalidCredentials_RetrieveUnauthorized_401() throws Exception {
        /*NOTE: Change the application.properties in external.basic-auth field*/
        try {
            unauthorizedByHttpGet(
                    props.getProperty("test.external.read-user-uri"),
                    props.getProperty("test.external.read-user-by-id"));
        } catch (Exception e) {
            tryAssertResponse("Error on findUserId: 401 null", e.getMessage());
        }
    }

}
