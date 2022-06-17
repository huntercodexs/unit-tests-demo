package com.huntercodexs.unittestsdemo.integration;

import com.huntercodexs.unittestsdemo.abstractor.IntegrationAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteUserByIdTest extends IntegrationAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Delete User By Id
     */
    @Test
    public void whenRequestToDeleteUserByIdUnauthorized_RetrieveUnauthorized_401() throws Exception {
        /*NOTE: Change the application.properties in external.basic-auth field or use external.basic-auth-invalid*/
        try {
            unauthorizedByHttpDelete(
                    props.getProperty("integration.test.delete-user-uri"),
                    props.getProperty("integration.test.delete-user-by-id"));
        } catch (Exception e) {
            assertIntegration("401 Null", e.getMessage());
        }
    }

    @Test
    public void whenRequestToDeleteUserByIdButUserNotFound_RetrieveUserNotFound_404() throws Exception {
        try {
            notFoundByHttpDelete(
                    props.getProperty("integration.test.delete-user-uri"),
                    props.getProperty("integration.test.delete-user-by-id-not-found"));
        } catch (Exception e) {
            assertIntegration("404 Not Found", e.getMessage());
        }
    }

    @Test
    public void whenRequestToDeleteUserByIdNonInteger_RetrieveBadRequest_400() throws Exception {
        try {
            badRequestByHttpDelete(
                    props.getProperty("integration.test.delete-user-uri"),
                    props.getProperty("integration.test.delete-user-by-id-non-integer"));
        } catch (Exception e) {
            assertIntegration("400 Bad Request", e.getMessage());
        }
    }

    @Test
    public void whenCorrectRequestToDeleteUserById_RetrieveUsersDeleted_200() throws Exception {
        try {
            okByHttpDelete(
                    props.getProperty("integration.test.delete-user-uri"),
                    props.getProperty("integration.test.delete-user-by-id"));
        } catch (Exception e) {
            assertIntegration("200 OK", e.getMessage());
        }
    }

    @Test
    public void whenRequestToDeleteUserByIdButServerError_RetrieveServerError_500() throws Exception {
        System.out.println("whenRequestToDeleteUserByIdButServerError_RetrieveServerError_500 is Ignored !");
    }

}
