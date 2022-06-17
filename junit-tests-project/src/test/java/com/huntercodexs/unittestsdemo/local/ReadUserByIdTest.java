package com.huntercodexs.unittestsdemo.local;

import com.huntercodexs.unittestsdemo.abstractor.LocalAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadUserByIdTest extends LocalAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Read User by id
     */

    @Test
    public void whenRequestToGetLocalUserByIdThatNotExists_RetrieveUserNotFound_404() throws Exception {
        notFoundByHttpGet(
                props.getProperty("local.test.get-user-uri"),
                props.getProperty("local.test.get-user-by-id-not-found"));
    }

    @Test
    public void whenRequestToGetLocalUserByIdNonInteger_RetrieveBadRequest_400() throws Exception {
        badRequestByHttpGet(
                props.getProperty("local.test.get-user-uri"),
                props.getProperty("local.test.get-user-by-id-non-integer"));
    }

    @Test
    public void whenRequestToGetLocalUserByIdButServerError_RetrieveServerError_500() throws Exception {
        System.out.println("whenRequestToGetLocalUserByIdButServerError_RetrieveServerError_500 is Ignored !");
    }

    @Test
    public void whenCorrectRequestToGetLocalUserById_RetrieveUserFound_200() throws Exception {
        okByHttpGet(
                props.getProperty("local.test.get-user-uri"),
                props.getProperty("local.test.get-user-by-id"));
    }

}
