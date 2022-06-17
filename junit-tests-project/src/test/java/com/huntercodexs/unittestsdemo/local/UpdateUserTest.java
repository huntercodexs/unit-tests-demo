package com.huntercodexs.unittestsdemo.local;

import com.huntercodexs.unittestsdemo.abstractor.LocalAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateUserTest extends LocalAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Update User by id
     */

    @Test
    public void whenRequestToUpdateUserButWithoutId_RetrieveMissingId_400() throws Exception {
        badRequestByHttpPut(
                props.getProperty("local.test.put-user-uri"),
                " ", /*id*/
                props.getProperty("local.test.put-user-body-correct"));
    }

    @Test
    public void whenRequestToUpdateUserButIdIsNotNumber_RetrieveMissingId_400() throws Exception {
        badRequestByHttpPut(
                props.getProperty("local.test.put-user-uri"),
                props.getProperty("local.test.put-user-by-id-non-integer"),
                props.getProperty("local.test.put-user-body-correct"));
    }

    @Test
    public void whenRequestToUpdateUserButIdNotFound_RetrieveMissingId_404() throws Exception {
        notFoundByHttpPut(
                props.getProperty("local.test.put-user-uri"),
                props.getProperty("local.test.put-user-by-id-not-found"),
                props.getProperty("local.test.put-user-body-correct"));
    }

    @Test
    public void whenRequestToUpdateUserUsingInvalidBody_RetrieveConflict_409() throws Exception {
        conflictByHttpPut(
                props.getProperty("local.test.put-user-uri"),
                props.getProperty("local.test.put-user-by-id"),
                props.getProperty("local.test.put-user-body-invalid"));
    }

    @Test
    public void whenRequestToUpdateUserUsingErrorBody_RetrieveError_400() throws Exception {
        badRequestByHttpPut(
                props.getProperty("local.test.put-user-uri"),
                props.getProperty("local.test.put-user-by-id"),
                props.getProperty("local.test.put-user-body-error"));
    }

    @Test
    public void whenRequestToUpdateUserCorrectly_RetrieveUserUpdated_200() throws Exception {
        okByHttpPut(
                props.getProperty("local.test.put-user-uri"),
                props.getProperty("local.test.put-user-by-id"),
                props.getProperty("local.test.put-user-body-correct"));
    }

}
