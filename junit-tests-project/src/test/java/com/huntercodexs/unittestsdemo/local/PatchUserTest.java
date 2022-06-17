package com.huntercodexs.unittestsdemo.local;

import com.huntercodexs.unittestsdemo.abstractor.LocalAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatchUserTest extends LocalAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Patch User by id
     */

    @Test
    public void whenRequestToPatchUserButWithoutId_RetrieveMissingId_400() throws Exception {
        badRequestByHttpPatch(
                props.getProperty("local.test.patch-user-uri"),
                " ", /*id*/
                props.getProperty("local.test.patch-user-body-correct"));
    }

    @Test
    public void whenRequestToPatchUserButIdIsNotNumber_RetrieveMissingId_400() throws Exception {
        badRequestByHttpPatch(
                props.getProperty("local.test.patch-user-uri"),
                props.getProperty("local.test.patch-user-by-id-non-integer"),
                props.getProperty("local.test.patch-user-body-correct"));
    }

    @Test
    public void whenRequestToPatchUserButIdNotFound_RetrieveMissingId_404() throws Exception {
        notFoundByHttpPatch(
                props.getProperty("local.test.patch-user-uri"),
                props.getProperty("local.test.patch-user-by-id-not-found"),
                props.getProperty("local.test.patch-user-body-correct"));
    }

    @Test
    public void whenRequestToPatchUserUsingInvalidBody_RetrieveConflict_409() throws Exception {
        conflictByHttpPatch(
                props.getProperty("local.test.patch-user-uri"),
                props.getProperty("local.test.patch-user-by-id"),
                props.getProperty("local.test.patch-user-body-invalid"));
    }

    @Test
    public void whenRequestToPatchUserUsingErrorBody_RetrieveError_400() throws Exception {
        badRequestByHttpPatch(
                props.getProperty("local.test.patch-user-uri"),
                props.getProperty("local.test.patch-user-by-id"),
                props.getProperty("local.test.patch-user-body-error"));
    }

    @Test
    public void whenRequestToPatchUserCorrectly_RetrieveUserPatched_200() throws Exception {
        okByHttpPatch(
                props.getProperty("local.test.patch-user-uri"),
                props.getProperty("local.test.patch-user-by-id"),
                props.getProperty("local.test.patch-user-body-correct"));
    }

}
