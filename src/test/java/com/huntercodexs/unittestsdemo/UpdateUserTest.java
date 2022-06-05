package com.huntercodexs.unittestsdemo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateUserTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Update User by id
     */

    @Test
    public void whenRequestToUpdateUserMailAddressMailButNoUserName_RetrieveMissingUserName_409() throws Exception {
        conflictByHttpPut(
                props.getProperty("test.put-update-user-uri"),
                " ", /*id = username*/
                props.getProperty("test.put-update-user-mail"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddressButEmailIsInvalid_RetrieveInvalidEmail_409() throws Exception {
        conflictByHttpPut(
                props.getProperty("test.put-update-user-uri"),
                props.getProperty("test.put-update-user-id"),
                props.getProperty("test.put-update-user-mail-invalid"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddressButEmailAlreadyExists_RetrieveEmailAlreadyExists_409() throws Exception {
        conflictByHttpPut(
                props.getProperty("test.put-update-user-uri"),
                props.getProperty("test.put-update-user-id"),
                props.getProperty("test.put-update-user-mail-conflict"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddress_RetrieveUserUpdated_200() throws Exception {
        boolean skip = true;
        if (!skip) {
            okByHttpPut(
                    props.getProperty("test.put-update-user-uri"),
                    props.getProperty("test.put-update-user-id"),
                    props.getProperty("test.put-update-user-mail"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressUsingWebHook_RetrieveUserUpdated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            acceptedByHttpPut(
                    props.getProperty("test.put-update-user-uri"),
                    props.getProperty("test.put-update-user-id"),
                    props.getProperty("test.put-update-user-mail-webhook"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressExists_RetrieveUserUpdated_200() throws Exception {
        boolean skip = true;
        if (!skip) {
            Thread.sleep(1500);
            okByHttpPut(
                    props.getProperty("test.put-update-user-uri"),
                    props.getProperty("test.put-update-user-id"),
                    props.getProperty("test.put-update-user-mail-exists"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressExistsUsingWebHook_RetrieveUserUpdated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            Thread.sleep(1500);
            acceptedByHttpPut(
                    props.getProperty("test.put-update-user-uri"),
                    props.getProperty("test.put-update-user-id"),
                    props.getProperty("test.put-update-user-mail-exists-webhook"));
        }
    }

}
