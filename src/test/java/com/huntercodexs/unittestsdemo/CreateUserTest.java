package com.huntercodexs.unittestsdemo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateUserTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Test Create User
     */

    @Test
    public void whenRequestToCreateUserButNoBody_RetrieveMissingBodyRequest_409() throws Exception {
        boolean skip = true;
        if (!skip) {
            conflictByHttpPost(
                    props.getProperty("test.post-create-user-uri"),
                    "",
                    props.getProperty("test.post-create-user-no-body"));
        }
    }

    @Test
    public void whenRequestToCreateUserButNoBodyData_RetrieveMissingBodyRequest_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("test.post-create-user-uri"),
                "",
                props.getProperty("test.post-create-user-no-body-data"));
    }

    @Test
    public void whenRequestToCreateUserButNoUserName_RetrieveMissingUserName_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("test.post-create-user-uri"),
                "",
                props.getProperty("test.post-create-user-no-username"));
    }

    @Test
    public void whenRequestToCreateUserButNoName_RetrieveMissingName_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("test.post-create-user-uri"),
                "",
                props.getProperty("test.post-create-user-no-name"));
    }

    @Test
    public void whenRequestToCreateUserButNoPhoneNumberAndEmailAddress_RetrieveMissingDevice_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("test.post-create-user-uri"),
                "",
                props.getProperty("test.post-create-user-no-device"));
    }

    @Test
    public void whenRequestToCreateUserButPhoneIsInvalid_RetrieveInvalidPhoneNumber_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("test.post-create-user-uri"),
                "",
                props.getProperty("test.post-create-user-invalid-phone"));
    }

    @Test
    public void whenRequestToCreateUserButPhoneAlreadyExists_RetrievePhoneAlreadyExists_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("test.post-create-user-uri"),
                "",
                props.getProperty("test.post-create-user-exists-phone"));
    }

    @Test
    public void whenRequestToCreateUserButEmailIsInvalid_RetrieveInvalidEmailAddress_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("test.post-create-user-uri"),
                "",
                props.getProperty("test.post-create-user-invalid-mail"));
    }

    @Test
    public void whenRequestToCreateUserButEmailAlreadyExists_RetrieveEmailAddressAlreadyExists_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("test.post-create-user-uri"),
                "",
                props.getProperty("test.post-create-user-exists-mail"));
    }

    @Test
    public void whenRequestToCreateUserButUserNameIsNotEmailOrCpf_RetrieveWrongUserName_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("test.post-create-user-uri"),
                "",
                props.getProperty("test.post-create-user-invalid-username"));
    }

    @Test
    public void whenCorrectRequestToCreateUserButUserExists_RetrieveUserConflict_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("test.post-create-user-uri"),
                "",
                props.getProperty("test.post-create-user-conflict"));
    }

    @Test
    public void whenRequestToCreateUserButServerError_RetrieveServerError_500() throws Exception {
        boolean skip = true;
        if (!skip) {
            serverErrorByHttpPost(
                    props.getProperty("test.post-create-user-uri"),
                    "",
                    props.getProperty("test.post-create-user-server-error"));
        }
    }

    @Test
    public void whenCorrectRequestToCreateUser_RetrieveUserCreated_201() throws Exception {
        boolean skip = true;
        if (!skip) {
            createdByHttpPost(
                    props.getProperty("test.post-create-user-uri"),
                    "",
                    props.getProperty("test.post-create-user-correctly"));
        }
    }

    @Test
    public void whenCorrectRequestToCreateUserUsingWebHook_RetrieveUserCreated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            acceptedByHttpPost(
                    props.getProperty("test.post-create-user-uri"),
                    "",
                    props.getProperty("test.post-create-user-correctly-webhook"));
        }
    }

    @Test
    public void whenCorrectRequestToCreateUserUsingJustMail_RetrieveUserCreated_201() throws Exception {
        boolean skip = true;
        if (!skip) {
            createdByHttpPost(
                    props.getProperty("test.post-create-user-uri"),
                    "",
                    props.getProperty("test.post-create-user-just-using-mail"));
        }
    }

    @Test
    public void whenCorrectRequestToCreateUserUsingJustMailUsingWebHook_RetrieveUserCreated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            createdByHttpPost(
                    props.getProperty("test.post-create-user-uri"),
                    "",
                    props.getProperty("test.post-create-user-just-using-mail-webhook"));
        }
    }
}
