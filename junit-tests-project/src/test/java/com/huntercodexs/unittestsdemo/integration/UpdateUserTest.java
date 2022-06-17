package com.huntercodexs.unittestsdemo.integration;

import com.huntercodexs.unittestsdemo.abstractor.IntegrationAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateUserTest extends IntegrationAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Update User by id
     */
    @Test
    public void whenRequestToUpdateUserByIdUnauthorized_RetrieveUnauthorized_401() throws Exception {
        /*NOTE: Change the application.properties in external.basic-auth field or use external.basic-auth-invalid*/
        try {
            unauthorizedByHttpPut(
                    props.getProperty("integration.test.put-user-uri"),
                    props.getProperty("integration.test.put-user-id"),
                    props.getProperty("integration.test.put-user-body-correct"));
        } catch (Exception e) {
            assertIntegration("401 Null", e.getMessage());
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressMailButNoUserName_RetrieveMissingUserName_404() throws Exception {
        try {
            notFoundByHttpPut(
                    props.getProperty("integration.test.put-user-uri"),
                    props.getProperty("integration.test.put-user-by-id-not-found"),
                    props.getProperty("integration.test.put-user-body-correct"));
        } catch (Exception e) {
            assertIntegration("404 Not Found", e.getMessage());
        }
    }

    @Test
    public void whenRequestToUpdateUserByIdNonInteger_RetrieveBadRequest_400() throws Exception {
        try {
            badRequestByHttpPut(
                    props.getProperty("integration.test.put-user-uri"),
                    props.getProperty("integration.test.put-user-by-id-non-integer"),
                    props.getProperty("integration.test.put-user-body-correct"));
        } catch (Exception e) {
            assertIntegration("400 Bad Request", e.getMessage());
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddress_RetrieveUserUpdated_202() throws Exception {
        try {
            acceptedByHttpPut(
                    props.getProperty("integration.test.put-user-uri"),
                    props.getProperty("integration.test.put-user-by-id"),
                    props.getProperty("integration.test.put-user-body-correct"));
        } catch (Exception e) {
            assertIntegration("202 Accepted", e.getMessage());
        }
    }

}
