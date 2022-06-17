package com.huntercodexs.unittestsdemo.integration;

import com.huntercodexs.unittestsdemo.abstractor.IntegrationAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatchUserTest extends IntegrationAbstractTest {

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
                props.getProperty("integration.test.patch-user-uri"),
                " ", /*id = username*/
                props.getProperty("integration.test.patch-user-mail"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddressButEmailIsInvalid_RetrieveInvalidEmail_409() throws Exception {
        conflictByHttpPatch(
                props.getProperty("integration.test.patch-user-uri"),
                props.getProperty("integration.test.patch-user-id"),
                props.getProperty("integration.test.patch-user-mail-invalid"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddressButEmailAlreadyExists_RetrieveEmailAlreadyExists_409() throws Exception {
        conflictByHttpPatch(
                props.getProperty("integration.test.patch-user-uri"),
                props.getProperty("integration.test.patch-user-id"),
                props.getProperty("integration.test.patch-user-mail-conflict"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddress_RetrieveUserUpdated_200() throws Exception {
        boolean skip = true;
        if (!skip) {
            okByHttpPatch(
                    props.getProperty("integration.test.patch-user-uri"),
                    props.getProperty("integration.test.patch-user-id"),
                    props.getProperty("integration.test.patch-user-mail"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressUsingWebHook_RetrieveUserUpdated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            acceptedByHttpPatch(
                    props.getProperty("integration.test.patch-user-uri"),
                    props.getProperty("integration.test.patch-user-id"),
                    props.getProperty("integration.test.patch-user-mail-webhook"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressExists_RetrieveUserUpdated_200() throws Exception {
        boolean skip = true;
        if (!skip) {
            Thread.sleep(1500);
            okByHttpPatch(
                    props.getProperty("integration.test.patch-user-uri"),
                    props.getProperty("integration.test.patch-user-id"),
                    props.getProperty("integration.test.patch-user-mail-exists"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressExistsUsingWebHook_RetrieveUserUpdated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            Thread.sleep(1500);
            acceptedByHttpPatch(
                    props.getProperty("integration.test.patch-user-uri"),
                    props.getProperty("integration.test.patch-user-id"),
                    props.getProperty("integration.test.patch-user-mail-exists-webhook"));
        }
    }

}
