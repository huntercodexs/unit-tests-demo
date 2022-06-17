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
        unauthorizedByHttpPost(
                props.getProperty("external.test.post-user-uri"),
                "",
                props.getProperty("external.test.post-user-body-correct"));
    }

    @Test
    public void whenRequestToCreateUserButNoBody_RetrieveMissingBodyRequest_400() throws Exception {
        badRequestByHttpPost(
                props.getProperty("external.test.post-user-uri"),
                "",
                props.getProperty("external.test.post-user-no-body"));
    }

    @Test
    public void whenRequestToCreateUserButNoBodyData_RetrieveMissingBodyRequest_400() throws Exception {
        badRequestByHttpPost(
                props.getProperty("external.test.post-user-uri"),
                "",
                props.getProperty("external.test.post-user-no-body-data"));
    }

    @Test
    public void whenRequestToCreateUserAlreadyExists_RetrieveConflict_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("external.test.post-user-uri"),
                "",
                props.getProperty("external.test.post-user-body-conflict"));
    }

    @Test
    public void whenRequestToCreateUserCorrectly_RetrieveOK_200() throws Exception {
        okByHttpPost(
                props.getProperty("external.test.post-user-uri"),
                "",
                props.getProperty("external.test.post-user-body-correct"));
    }
}
