package com.huntercodexs.unittestsdemo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatchUserTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    /**
     * Fix User by id
     */

    @Test
    public void whenRequestToUpdateUserMailAddressMailButNoUserName_RetrieveMissingUserName_409() throws Exception {
        conflictByHttpPatch(
                props.getProperty("test.patch-update-user-uri"),
                " ", /*id = username*/
                props.getProperty("test.patch-update-user-mail"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddressButEmailIsInvalid_RetrieveInvalidEmail_409() throws Exception {
        conflictByHttpPatch(
                props.getProperty("test.patch-update-user-uri"),
                props.getProperty("test.patch-update-user-id"),
                props.getProperty("test.patch-update-user-mail-invalid"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddressButEmailAlreadyExists_RetrieveEmailAlreadyExists_409() throws Exception {
        conflictByHttpPatch(
                props.getProperty("test.patch-update-user-uri"),
                props.getProperty("test.patch-update-user-id"),
                props.getProperty("test.patch-update-user-mail-conflict"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddress_RetrieveUserUpdated_200() throws Exception {
        boolean skip = true;
        if (!skip) {
            okByHttpPatch(
                    props.getProperty("test.patch-update-user-uri"),
                    props.getProperty("test.patch-update-user-id"),
                    props.getProperty("test.patch-update-user-mail"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressUsingWebHook_RetrieveUserUpdated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            acceptedByHttpPatch(
                    props.getProperty("test.patch-update-user-uri"),
                    props.getProperty("test.patch-update-user-id"),
                    props.getProperty("test.patch-update-user-mail-webhook"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressExists_RetrieveUserUpdated_200() throws Exception {
        boolean skip = true;
        if (!skip) {
            Thread.sleep(1500);
            okByHttpPatch(
                    props.getProperty("test.patch-update-user-uri"),
                    props.getProperty("test.patch-update-user-id"),
                    props.getProperty("test.patch-update-user-mail-exists"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressExistsUsingWebHook_RetrieveUserUpdated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            Thread.sleep(1500);
            acceptedByHttpPatch(
                    props.getProperty("test.patch-update-user-uri"),
                    props.getProperty("test.patch-update-user-id"),
                    props.getProperty("test.patch-update-user-mail-exists-webhook"));
        }
    }

}
