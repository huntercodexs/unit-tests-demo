package com.huntercodexs.unittestsdemo.integration;

import com.huntercodexs.unittestsdemo.abstractor.IntegrationAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateUserTest extends IntegrationAbstractTest {

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
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-no-body"));
        }
    }

    @Test
    public void whenRequestToCreateUserButNoBodyData_RetrieveMissingBodyRequest_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("integration.test.post-user-uri"),
                "",
                props.getProperty("integration.test.post-user-no-body-data"));
    }

    @Test
    public void whenRequestToCreateUserButNoUserName_RetrieveMissingUserName_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("integration.test.post-user-uri"),
                "",
                props.getProperty("integration.test.post-user-no-username"));
    }

    @Test
    public void whenRequestToCreateUserButNoName_RetrieveMissingName_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("integration.test.post-user-uri"),
                "",
                props.getProperty("integration.test.post-user-no-name"));
    }

    @Test
    public void whenRequestToCreateUserButNoPhoneNumberAndEmailAddress_RetrieveMissingDevice_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("integration.test.post-user-uri"),
                "",
                props.getProperty("integration.test.post-user-no-device"));
    }

    @Test
    public void whenRequestToCreateUserButPhoneIsInvalid_RetrieveInvalidPhoneNumber_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("integration.test.post-user-uri"),
                "",
                props.getProperty("integration.test.post-user-invalid-phone"));
    }

    @Test
    public void whenRequestToCreateUserButPhoneAlreadyExists_RetrievePhoneAlreadyExists_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("integration.test.post-user-uri"),
                "",
                props.getProperty("integration.test.post-user-exists-phone"));
    }

    @Test
    public void whenRequestToCreateUserButEmailIsInvalid_RetrieveInvalidEmailAddress_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("integration.test.post-user-uri"),
                "",
                props.getProperty("integration.test.post-user-invalid-mail"));
    }

    @Test
    public void whenRequestToCreateUserButEmailAlreadyExists_RetrieveEmailAddressAlreadyExists_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("integration.test.post-user-uri"),
                "",
                props.getProperty("integration.test.post-user-exists-mail"));
    }

    @Test
    public void whenRequestToCreateUserButUserNameIsNotEmailOrCpf_RetrieveWrongUserName_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("integration.test.post-user-uri"),
                "",
                props.getProperty("integration.test.post-user-invalid-username"));
    }

    @Test
    public void whenCorrectRequestToCreateUserButUserExists_RetrieveUserConflict_409() throws Exception {
        conflictByHttpPost(
                props.getProperty("integration.test.post-user-uri"),
                "",
                props.getProperty("integration.test.post-user-conflict"));
    }

    @Test
    public void whenRequestToCreateUserButServerError_RetrieveServerError_500() throws Exception {
        boolean skip = true;
        if (!skip) {
            serverErrorByHttpPost(
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-server-error"));
        }
    }

    @Test
    public void whenCorrectRequestToCreateUser_RetrieveUserCreated_201() throws Exception {
        boolean skip = true;
        if (!skip) {
            createdByHttpPost(
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-correctly"));
        }
    }

    @Test
    public void whenCorrectRequestToCreateUserUsingWebHook_RetrieveUserCreated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            acceptedByHttpPost(
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-correctly-webhook"));
        }
    }

    @Test
    public void whenCorrectRequestToCreateUserUsingJustMail_RetrieveUserCreated_201() throws Exception {
        boolean skip = true;
        if (!skip) {
            createdByHttpPost(
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-just-using-mail"));
        }
    }

    @Test
    public void whenCorrectRequestToCreateUserUsingJustMailUsingWebHook_RetrieveUserCreated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            createdByHttpPost(
                    props.getProperty("integration.test.post-user-uri"),
                    "",
                    props.getProperty("integration.test.post-user-just-using-mail-webhook"));
        }
    }
}
