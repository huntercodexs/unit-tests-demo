package com.huntercodexs.unittestsdemo.external;

import com.huntercodexs.unittestsdemo.abstractor.ExternalAbstractTest;
import org.junit.Before;
import org.junit.Test;

public class CreateUserTest extends ExternalAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Post Create User
     */

    @Test
    public void whenRequestToCreateUserUnauthorized_RetrieveUnauthorized_401() throws Exception {
        assertRestByPost(
                props.getProperty("external.test.post-user-uri"),
                "",
                props.getProperty("external.test.post-user-body-correct"),
                401);
    }

    @Test
    public void whenRequestToCreateUserButNoBody_RetrieveMissingBodyRequest_400() throws Exception {
        assertRestByPost(
                props.getProperty("external.test.post-user-uri"),
                "",
                props.getProperty("external.test.post-user-no-body"),
                400);
    }

    @Test
    public void whenRequestToCreateUserButNoBodyData_RetrieveMissingBodyRequest_400() throws Exception {
        assertRestByPost(
                props.getProperty("external.test.post-user-uri"),
                "",
                props.getProperty("external.test.post-user-no-body-data"),
                400);
    }

    @Test
    public void whenRequestToCreateUserAlreadyExists_RetrieveConflict_409() throws Exception {
        assertRestByPost(
                props.getProperty("external.test.post-user-uri"),
                "",
                props.getProperty("external.test.post-user-body-conflict"),
                409);
    }

    @Test
    public void whenRequestToCreateUserCorrectly_RetrieveOK_200() throws Exception {
        assertRestByPost(
                props.getProperty("external.test.post-user-uri"),
                "",
                props.getProperty("external.test.post-user-body-correct"),
                200);
    }
}
