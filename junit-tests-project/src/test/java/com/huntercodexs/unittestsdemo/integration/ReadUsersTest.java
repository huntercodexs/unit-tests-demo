package com.huntercodexs.unittestsdemo.integration;

import com.huntercodexs.unittestsdemo.abstractor.IntegrationAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadUsersTest extends IntegrationAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Read Users
     */
    @Test
    public void whenRequestToGetExternalUserWithInvalidCredentials_RetrieveUnauthorized_401() throws Exception {
        /*NOTE: Change the application.properties in external.basic-auth field or use external.basic-auth-invalid*/
        try {
            unauthorizedByHttpGet(props.getProperty("integration.test.get-user-uri"), "");
        } catch (Exception e) {
            assertIntegration("401 Null", e.getMessage());
        }
    }

    @Test
    public void whenCorrectRequestToGetAllUsers_RetrieveUsersFound_200() throws Exception {
        try {
            okByHttpGet(props.getProperty("integration.test.get-users-uri"), "");
        } catch (Exception e) {
            assertIntegration("200 OK", e.getMessage());
        }
    }

}
