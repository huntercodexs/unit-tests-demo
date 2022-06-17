package com.huntercodexs.unittestsdemo.external;

import com.huntercodexs.unittestsdemo.abstractor.ExternalAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateUserTest extends ExternalAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Update User by id
     */

    @Test
    public void whenRequestToUpdateUserUnauthorized_RetrieveUnauthorized_401() throws Exception {
        assertRestByPut(
                props.getProperty("external.test.put-user-uri"),
                props.getProperty("external.test.put-user-by-id"),
                props.getProperty("external.test.put-user-body-correct"),
                401);
    }

    @Test
    public void whenRequestToUpdateUserButWithoutId_RetrieveMissingId_400() throws Exception {
        assertRestByPut(
                props.getProperty("external.test.put-user-uri"),
                " ", /*id*/
                props.getProperty("external.test.put-user-body-correct"),
                400);
    }

    @Test
    public void whenRequestToUpdateUserButIdIsNotNumber_RetrieveMissingId_400() throws Exception {
        assertRestByPut(
                props.getProperty("external.test.put-user-uri"),
                props.getProperty("external.test.put-user-by-id-non-integer"),
                props.getProperty("external.test.put-user-body-correct"),
                400);
    }

    @Test
    public void whenRequestToUpdateUserButIdNotFound_RetrieveMissingId_404() throws Exception {
        assertRestByPut(
                props.getProperty("external.test.put-user-uri"),
                props.getProperty("external.test.put-user-by-id-not-found"),
                props.getProperty("external.test.put-user-body-correct"),
                404);
    }

    @Test
    public void whenRequestToUpdateUserUsingInvalidBody_RetrieveConflict_400() throws Exception {
        assertRestByPut(
                props.getProperty("external.test.put-user-uri"),
                props.getProperty("external.test.put-user-by-id"),
                props.getProperty("external.test.put-user-body-invalid"),
                400);
    }

    @Test
    public void whenRequestToUpdateUserUsingErrorBody_RetrieveError_400() throws Exception {
        assertRestByPut(
                props.getProperty("external.test.put-user-uri"),
                props.getProperty("external.test.put-user-by-id"),
                props.getProperty("external.test.put-user-body-error"),
                400);
    }

    @Test
    public void whenRequestToUpdateUserCorrectly_RetrieveUserUpdated_200() throws Exception {
        assertRestByPut(
                props.getProperty("external.test.put-user-uri"),
                props.getProperty("external.test.put-user-by-id"),
                props.getProperty("external.test.put-user-body-correct"),
                200);
    }

}
