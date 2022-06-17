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
        unauthorizedByHttpGet(
                props.getProperty("external.test.get-user-uri"),
                props.getProperty("external.test.get-user-by-id"));
    }

    @Test
    public void whenRequestToGetExternalUserByIdThatNotExists_RetrieveUserNotFound_404() throws Exception {
        notFoundByHttpGet(
                props.getProperty("external.test.get-user-uri"),
                props.getProperty("external.test.get-user-by-id-not-found"));
    }

    @Test
    public void whenRequestToGetExternalUserByIdNonInteger_RetrieveBadRequest_400() throws Exception {
        badRequestByHttpGet(
                props.getProperty("external.test.get-user-uri"),
                props.getProperty("external.test.get-user-by-id-non-integer"));
    }

    @Test
    public void whenRequestToGetExternalUserByIdButServerError_RetrieveServerError_500() throws Exception {
        System.out.println("whenRequestToGetExternalUserByIdButServerError_RetrieveServerError_500 is Ignored !");
    }

    @Test
    public void whenCorrectRequestToGetExternalUserById_RetrieveUserFound_200() throws Exception {
        okByHttpGet(
                props.getProperty("external.test.get-user-uri"),
                props.getProperty("external.test.get-user-by-id"));
    }

}
