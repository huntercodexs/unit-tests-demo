package com.huntercodexs.unittestsdemo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadUsersTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Read Users
     */

    @Test
    public void whenCorrectRequestToGetAllUsersLocal_RetrieveUsersFound_200() throws Exception {
        okByHttpGet(props.getProperty("test.local.read-users-uri"), "");
    }

    @Test
    public void whenRequestToGetExternalUserWithInvalidCredentials_RetrieveUnauthorized_401() throws Exception {
        /*NOTE: Change the application.properties in external.basic-auth field*/
        try {
            unauthorizedByHttpGet(props.getProperty("test.external.read-user-uri"), "");
        } catch (Exception e) {
            tryAssertResponse("Error on findUsers: 401 null", e.getMessage());
        }
    }

}
