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
    public void whenCorrectRequestToGetAllUsers_RetrieveUsersFound_200() throws Exception {
        okByHttpGet(props.getProperty("integration.test.get-users-uri"), "");
    }

    @Test
    public void whenRequestToGetExternalUserWithInvalidCredentials_RetrieveUnauthorized_401() throws Exception {
        /*NOTE: Change the application.properties in external.basic-auth field*/
        try {
            unauthorizedByHttpGet(props.getProperty("integration.test.get-user-uri"), "");
        } catch (Exception e) {
            assertionTest("Error on findUsers: 401 null", e.getMessage());
        }
    }

}
