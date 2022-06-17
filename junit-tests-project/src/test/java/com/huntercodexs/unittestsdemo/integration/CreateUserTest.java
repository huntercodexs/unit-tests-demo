package com.huntercodexs.unittestsdemo.integration;

import com.huntercodexs.unittestsdemo.abstractor.IntegrationAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateUserTest extends IntegrationAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Create User
     */
    @Test
    public void whenRequestToCreateUserUnauthorized_RetrieveUnauthorized_401() throws Exception {
        /*NOTE: Change the application.properties in external.basic-auth field or use external.basic-auth-invalid*/
        try {
            unauthorizedByHttpPost(
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-body-correct"));
        } catch (Exception e) {
            assertIntegration("401 Null", e.getMessage());
        }
    }

    @Test
    public void whenRequestToCreateUserButNoBody_RetrieveMissingBodyRequest_400() throws Exception {
        try {
            badRequestByHttpPost(
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-no-body"));
        } catch (Exception e) {
            assertIntegration("400 Bad Request", e.getMessage());
        }
    }

    @Test
    public void whenRequestToCreateUserButNoBodyData_RetrieveMissingBodyRequest_400() throws Exception {
        try {
            badRequestByHttpPost(
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-no-body-data"));
        } catch (Exception e) {
            assertIntegration("400 Bad Request", e.getMessage());
        }
    }

    @Test
    public void whenCorrectRequestToCreateUserButUserExists_RetrieveUserConflict_409() throws Exception {
        try {
            conflictByHttpPost(
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-body-conflict"));
        } catch (Exception e) {
            assertIntegration("409 Conflict", e.getMessage());
        }
    }

    @Test
    public void whenRequestToCreateUserButServerError_RetrieveBadRequest_400() throws Exception {
        try {
            badRequestByHttpPost(
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-body-error"));
        } catch (Exception e) {
            assertIntegration("400 Bad Request", e.getMessage());
        }
    }

    @Test
    public void whenCorrectRequestToCreateUser_RetrieveUserCreated_201() throws Exception {
        try {
            createdByHttpPost(
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-body-correct"));
        } catch (Exception e) {
            assertIntegration("201 Created", e.getMessage());
        }
    }
}
