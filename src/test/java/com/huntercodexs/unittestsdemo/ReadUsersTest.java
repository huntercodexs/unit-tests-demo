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
    public void whenCorrectRequestToGetAllUsers_RetrieveUsersFound_200() throws Exception {
        okByHttpGet(props.getProperty("test.find-users-uri"), "");
    }

    @Test
    public void whenRequestToGetAllUsersButUsersNotFound_RetrieveUsersNotFound_409() throws Exception {
        System.out.println("whenRequestToGetAllUsersButUsersNotFound_RetrieveUsersNotFound_409 is Ignored !");
    }

    @Test
    public void whenRequestToGetAllUsersButServerError_RetrieveServerError_500() throws Exception {
        System.out.println("whenRequestToGetAllUsersButServerError_RetrieveServerError_500 is Ignored !");
    }

}
