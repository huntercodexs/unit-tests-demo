package com.huntercodexs.unittestsdemo.external;

import com.huntercodexs.unittestsdemo.abstractor.ExternalAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatchUserTest extends ExternalAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Patch User by id
     */

    @Test
    public void whenRequestToPatchUserUnauthorized_RetrieveUnauthorized_401() throws Exception {
        assertRestByPatch(
                props.getProperty("external.test.patch-user-uri"),
                props.getProperty("external.test.patch-user-by-id"),
                props.getProperty("external.test.patch-user-body-correct"),
                401);
    }

    @Test
    public void whenRequestToPatchUserButWithoutId_RetrieveMissingId_400() throws Exception {
        assertRestByPatch(
                props.getProperty("external.test.patch-user-uri"),
                " ", /*id*/
                props.getProperty("external.test.patch-user-body-correct"),
                400);
    }

    @Test
    public void whenRequestToPatchUserButIdIsNotNumber_RetrieveMissingId_400() throws Exception {
        assertRestByPatch(
                props.getProperty("external.test.patch-user-uri"),
                props.getProperty("external.test.patch-user-by-id-non-integer"),
                props.getProperty("external.test.patch-user-body-correct"),
                400);
    }

    @Test
    public void whenRequestToPatchUserButIdNotFound_RetrieveMissingId_404() throws Exception {
        assertRestByPatch(
                props.getProperty("external.test.patch-user-uri"),
                props.getProperty("external.test.patch-user-by-id-not-found"),
                props.getProperty("external.test.patch-user-body-correct"),
                404);
    }

    @Test
    public void whenRequestToPatchUserUsingInvalidBody_RetrieveConflict_409() throws Exception {
        assertRestByPatch(
                props.getProperty("external.test.patch-user-uri"),
                props.getProperty("external.test.patch-user-by-id"),
                props.getProperty("external.test.patch-user-body-invalid"),
                409);
    }

    @Test
    public void whenRequestToPatchUserUsingErrorBody_RetrieveError_400() throws Exception {
        assertRestByPatch(
                props.getProperty("external.test.patch-user-uri"),
                props.getProperty("external.test.patch-user-by-id"),
                props.getProperty("external.test.patch-user-body-error"),
                400);
    }

    @Test
    public void whenRequestToPatchUserCorrectly_RetrieveUserPatched_200() throws Exception {
        assertRestByPatch(
                props.getProperty("external.test.patch-user-uri"),
                props.getProperty("external.test.patch-user-by-id"),
                props.getProperty("external.test.patch-user-body-correct"),
                200);
    }

}
