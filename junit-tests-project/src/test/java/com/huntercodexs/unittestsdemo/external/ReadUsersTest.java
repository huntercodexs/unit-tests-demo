package com.huntercodexs.unittestsdemo.external;

import com.huntercodexs.unittestsdemo.abstractor.ExternalAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadUsersTest extends ExternalAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Read Users
     */

    @Test
    public void whenRequestToGetExternalUsersUnauthorized_RetrieveUnauthorized_401() throws Exception {
        assertRestByGet(
                props.getProperty("external.test.get-users-uri"),
                "",
                null,
                401);
    }

    @Test
    public void whenCorrectRequestToGetAllUsers_RetrieveUsersFound_200() throws Exception {
        assertRestByGet(
                props.getProperty("external.test.get-users-uri"),
                "",
                null,
                200);
    }

}
