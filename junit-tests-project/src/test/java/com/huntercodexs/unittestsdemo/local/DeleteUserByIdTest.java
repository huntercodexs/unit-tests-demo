package com.huntercodexs.unittestsdemo.local;

import com.huntercodexs.unittestsdemo.abstractor.LocalAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteUserByIdTest extends LocalAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Delete User By Id
     */

    @Test
    public void whenRequestToDeleteUserByIdButUserNotFound_RetrieveUserNotFound_404() throws Exception {
        notFoundByHttpDelete(
                props.getProperty("local.test.delete-user-uri"),
                props.getProperty("local.test.delete-user-by-id-not-found"));
    }

    @Test
    public void whenRequestToDeleteUserByIdNonInteger_RetrieveBadRequest_400() throws Exception {
        badRequestByHttpGet(
                props.getProperty("local.test.delete-user-uri"),
                props.getProperty("local.test.delete-user-by-id-non-integer"));
    }

    @Test
    public void whenCorrectRequestToDeleteUserById_RetrieveUsersDeleted_200() throws Exception {
        okByHttpDelete(
                props.getProperty("local.test.delete-user-uri"),
                props.getProperty("local.test.delete-user-by-id"));
    }

    @Test
    public void whenRequestToDeleteUserByIdButServerError_RetrieveServerError_500() throws Exception {
        System.out.println("whenRequestToDeleteUserByIdButServerError_RetrieveServerError_500 is Ignored !");
    }

}
