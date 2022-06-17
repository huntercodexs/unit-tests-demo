package com.huntercodexs.unittestsdemo.external;

import com.huntercodexs.unittestsdemo.abstractor.ExternalAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadUserByIdTest extends ExternalAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Read User by id
     */

    @Test
    public void whenRequestToGetExternalUserByIdUnauthorized_RetrieveUnauthorized_401() throws Exception {
        assertRestByGet(
                props.getProperty("external.test.get-user-uri"),
                props.getProperty("external.test.get-user-by-id"),
                null,
                401);
    }

    @Test
    public void whenRequestToGetExternalUserByIdThatNotExists_RetrieveUserNotFound_404() throws Exception {
        assertRestByGet(
                props.getProperty("external.test.get-user-uri"),
                props.getProperty("external.test.get-user-by-id-not-found"),
                null,
                404);
    }

    @Test
    public void whenRequestToGetExternalUserByIdNonInteger_RetrieveBadRequest_400() throws Exception {
        assertRestByGet(
                props.getProperty("external.test.get-user-uri"),
                props.getProperty("external.test.get-user-by-id-non-integer"),
                null,
                400);
    }

    @Test
    public void whenRequestToGetExternalUserByIdButServerError_RetrieveServerError_500() throws Exception {
        System.out.println("whenRequestToGetExternalUserByIdButServerError_RetrieveServerError_500 is Ignored !");
    }

    @Test
    public void whenCorrectRequestToGetExternalUserById_RetrieveUserFound_200() throws Exception {
        assertRestByGet(
                props.getProperty("external.test.get-user-uri"),
                props.getProperty("external.test.get-user-by-id"),
                null,
                200);
    }

}
