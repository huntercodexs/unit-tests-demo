package com.huntercodexs.unittestsdemo.local;

import com.huntercodexs.unittestsdemo.abstractor.LocalAbstractTest;
import org.junit.Before;
import org.junit.Test;

public class CreateUserTest extends LocalAbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Post Create User
     */

    @Test
    public void whenRequestToCreateUserButNoBody_RetrieveMissingBodyRequest_400() throws Exception {
        badRequestByHttpPost(
                props.getProperty("local.test.post-user-uri"),
                "",
                props.getProperty("local.test.post-user-no-body"));
    }

    @Test
    public void whenRequestToCreateUserButNoBodyData_RetrieveMissingBodyRequest_400() throws Exception {
        badRequestByHttpPost(
                props.getProperty("local.test.post-user-uri"),
                "",
                props.getProperty("local.test.post-user-no-body-data"));
    }

    @Test
    public void whenRequestToCreateUserAlreadyExists_RetrieveConflict_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("local.test.post-user-uri"),
                "",
                props.getProperty("local.test.post-user-body-conflict"));
    }

    @Test
    public void whenRequestToCreateUserCorrectly_RetrieveOK_200() throws Exception {
        okByHttpPost(
                props.getProperty("local.test.post-user-uri"),
                "",
                props.getProperty("local.test.post-user-body-correct"));
    }
}
