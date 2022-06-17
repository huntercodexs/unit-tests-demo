package com.huntercodexs.unittestsdemo.integration;

import com.huntercodexs.unittestsdemo.abstractor.IntegrationAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateUserTest extends IntegrationAbstractTest {

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
                props.getProperty("integration.test.put-user-uri"),
                " ", /*id = username*/
                props.getProperty("integration.test.put-user-mail"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddressButEmailIsInvalid_RetrieveInvalidEmail_409() throws Exception {
        conflictByHttpPut(
                props.getProperty("integration.test.put-user-uri"),
                props.getProperty("integration.test.put-user-id"),
                props.getProperty("integration.test.put-user-mail-invalid"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddressButEmailAlreadyExists_RetrieveEmailAlreadyExists_409() throws Exception {
        conflictByHttpPut(
                props.getProperty("integration.test.put-user-uri"),
                props.getProperty("integration.test.put-user-id"),
                props.getProperty("integration.test.put-user-mail-conflict"));
    }

    @Test
    public void whenRequestToUpdateUserMailAddress_RetrieveUserUpdated_200() throws Exception {
        boolean skip = true;
        if (!skip) {
            okByHttpPut(
                    props.getProperty("integration.test.put-user-uri"),
                    props.getProperty("integration.test.put-user-id"),
                    props.getProperty("integration.test.put-user-mail"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressUsingWebHook_RetrieveUserUpdated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            acceptedByHttpPut(
                    props.getProperty("integration.test.put-user-uri"),
                    props.getProperty("integration.test.put-user-id"),
                    props.getProperty("integration.test.put-user-mail-webhook"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressExists_RetrieveUserUpdated_200() throws Exception {
        boolean skip = true;
        if (!skip) {
            Thread.sleep(1500);
            okByHttpPut(
                    props.getProperty("integration.test.put-user-uri"),
                    props.getProperty("integration.test.put-user-id"),
                    props.getProperty("integration.test.put-user-mail-exists"));
        }
    }

    @Test
    public void whenRequestToUpdateUserMailAddressExistsUsingWebHook_RetrieveUserUpdated_202() throws Exception {
        boolean skip = true;
        if (!skip) {
            Thread.sleep(1500);
            acceptedByHttpPut(
                    props.getProperty("integration.test.put-user-uri"),
                    props.getProperty("integration.test.put-user-id"),
                    props.getProperty("integration.test.put-user-mail-exists-webhook"));
        }
    }

}
