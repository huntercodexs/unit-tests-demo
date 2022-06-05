package com.huntercodexs.unittestsdemo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReadUserByIdTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Read User by id
     */

    @Test
    public void whenRequestToGetUserByIdButUserNotFound_RetrieveUserNotFound_409() throws Exception {
        conflictByHttpGet(
                props.getProperty("test.find-user-uri"),
                props.getProperty("test.find-user-by-id-not-found"));
    }

    @Test
    public void whenCorrectRequestToGetUserById_RetrieveUserFound_200() throws Exception {
        okByHttpGet(
                props.getProperty("test.find-user-uri"),
                props.getProperty("test.find-user-by-id"));
    }

    @Test
    public void whenRequestToGetUserByIdButServerError_RetrieveServerError_500() throws Exception {
        System.out.println("whenRequestToGetUserByIdButServerError_RetrieveServerError_500 is Ignored !");
    }

}
