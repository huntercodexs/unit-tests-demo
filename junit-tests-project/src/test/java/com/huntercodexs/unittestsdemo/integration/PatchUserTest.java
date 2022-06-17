package com.huntercodexs.unittestsdemo.integration;

import com.huntercodexs.unittestsdemo.abstractor.IntegrationAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatchUserTest extends IntegrationAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Patch User by id
     */
    public void whenRequestToPatchUserByIdUnauthorized_RetrieveUnauthorized_401() throws Exception {
        /*NOTE: Change the application.properties in external.basic-auth field or use external.basic-auth-invalid*/
        try {
            unauthorizedByHttpPatch(
                    props.getProperty("integration.test.patch-user-uri"),
                    props.getProperty("integration.test.patch-user-id"),
                    props.getProperty("integration.test.patch-user-body-correct"));
        } catch (Exception e) {
            assertIntegration("401 Null", e.getMessage());
        }
    }

    @Test
    public void whenRequestToPatchUserMailAddressMailButNoUserName_RetrieveMissingUserName_404() throws Exception {
        try {
            notFoundByHttpPatch(
                    props.getProperty("integration.test.patch-user-uri"),
                    props.getProperty("integration.test.patch-user-by-id-not-found"),
                    props.getProperty("integration.test.patch-user-body-correct"));
        } catch (Exception e) {
            assertIntegration("404 Not Found", e.getMessage());
        }
    }

    @Test
    public void whenRequestToPatchUserByIdNonInteger_RetrieveBadRequest_400() throws Exception {
        try {
            badRequestByHttpPatch(
                    props.getProperty("integration.test.patch-user-uri"),
                    props.getProperty("integration.test.patch-user-by-id-non-integer"),
                    props.getProperty("integration.test.patch-user-body-correct"));
        } catch (Exception e) {
            assertIntegration("400 Bad Request", e.getMessage());
        }
    }

    @Test
    public void whenRequestToPatchUserMailAddress_RetrieveUserPatched_202() throws Exception {
        try {
            acceptedByHttpPatch(
                    props.getProperty("integration.test.patch-user-uri"),
                    props.getProperty("integration.test.patch-user-by-id"),
                    props.getProperty("integration.test.patch-user-body-correct"));
        } catch (Exception e) {
            assertIntegration("202 Accepted", e.getMessage());
        }
    }

}
