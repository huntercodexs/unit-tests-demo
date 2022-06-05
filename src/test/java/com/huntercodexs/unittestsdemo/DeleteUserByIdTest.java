package com.huntercodexs.unittestsdemo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteUserByIdTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Delete User By Id
     */

    @Test
    public void whenRequestToDeleteUserByIdButUserNotFound_RetrieveUserNotFound_409() throws Exception {
        boolean skip = true;
        if (!skip) {
            conflictByHttpDelete(
                    props.getProperty("test.delete-user-uri"),
                    props.getProperty("test.delete-user-by-id-not-found"));
        }
    }

    @Test
    public void whenCorrectRequestToDeleteUserById_RetrieveUsersDeleted_200() throws Exception {
        boolean skip = true;
        if (!skip) {
            okByHttpDelete(
                    props.getProperty("test.delete-user-uri"),
                    props.getProperty("test.delete-user-by-id"));
        }
    }

    @Test
    public void whenCorrectRequestToDeleteUserByIdUsingWebHook_RetrieveUsersDeleted_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            acceptedByHttpDelete(
                    props.getProperty("test.delete-user-uri"),
                    props.getProperty("test.delete-user-by-id-webhook"));
        }
    }

    @Test
    public void whenRequestToDeleteUserByIdButServerError_RetrieveServerError_500() throws Exception {
        System.out.println("whenRequestToDeleteUserByIdButServerError_RetrieveServerError_500 is Ignored !");
    }

}
