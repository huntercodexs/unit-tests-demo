package com.huntercodexs.unittestsdemo.abstractor;

import com.huntercodexs.unittestsdemo.UnitTestsDemoApplication;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UnitTestsDemoApplication.class)
@WebAppConfiguration
public abstract class LocalAbstractTest {

    protected MockMvc mockMvc;

    private static final String propFile = "classpath:local.test.properties";
    protected final Properties props = loadPropsTest();

    protected String localUrlBaseTest = props.getProperty("local.test.base-url");
    protected String localUriBaseTest = props.getProperty("local.test.base-uri");

    protected final String localBasicAuthorization = props.getProperty("local.test.basic-authorization");
    protected final String localInvalidBasicAuthorization = props.getProperty("local.test.basic-authorization-invalid");
    protected final String localAppNameAuthorization = props.getProperty("local.test.api-key.app-name");
    protected final String localTokenAuthorization = props.getProperty("local.test.api-key.token");
    protected final String localSecretAuthorization = props.getProperty("local.test.api-key.secret");
    protected final String localValueAuthorization = props.getProperty("local.test.api-key.value");
    protected final String localGenericAuthorization = props.getProperty("local.test.api-key.generic");
    protected final String localAdditionalAuthorization = props.getProperty("local.test.api-key.additional");

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public static Properties loadPropsTest() {
        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile(LocalAbstractTest.propFile);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    protected void createBeforeTest(String user_data) throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(localUriBaseTest)
                                .content(user_data)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                ).andReturn();
    }

    protected void rollbackTest(String md5Id) throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(localUriBaseTest+"/"+md5Id)
                                .header("Authorization", localBasicAuthorization)
                ).andReturn();
    }

    protected void assertionTest(String ref, String text) {
        if (text.contains(ref)) {
            Assert.assertEquals(1, 1);
        } else {
            Assert.assertEquals(1, 0);
        }
    }

    /**
     * Using Http GET
     */
    protected void unauthorizedByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(localUriBaseTest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", localInvalidBasicAuthorization)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(localUriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(localUriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(localUriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void createdByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(localUriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isCreated())
                .andReturn();
    }

    protected void acceptedByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(localUriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isAccepted())
                .andReturn();
    }

    protected void notFoundByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(localUriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void conflictByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(localUriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpGet(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(localUriBaseTest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http POST
     */
    protected void unauthorizedByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localInvalidBasicAuthorization)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void createdByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isCreated())
                .andReturn();
    }

    protected void acceptedByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isAccepted())
                .andReturn();
    }

    protected void notFoundByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void foundByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isFound())
                .andReturn();
    }

    protected void conflictByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpPost(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http PUT
     */
    protected void unauthorizedByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localInvalidBasicAuthorization)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void createdByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isCreated())
                .andReturn();
    }

    protected void acceptedByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isAccepted())
                .andReturn();
    }

    protected void notFoundByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void conflictByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpPut(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http DELETE
     */
    protected void unauthorizedByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(localUriBaseTest)
                        .header("Authorization", localInvalidBasicAuthorization)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(localUriBaseTest)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(localUriBaseTest)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void conflictByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(localUriBaseTest)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void okByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(localUriBaseTest)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void acceptedByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(localUriBaseTest)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isAccepted())
                .andReturn();
    }

    protected void notFoundByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(localUriBaseTest)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void serverErrorByHttpDelete(String uri, String id) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete(localUriBaseTest)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http PATCH
     */
    protected void unauthorizedByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localInvalidBasicAuthorization)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void acceptedByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isAccepted())
                .andReturn();
    }

    protected void notFoundByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void conflictByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpPatch(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http HEAD
     */
    protected void unauthorizedByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localInvalidBasicAuthorization)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void notFoundByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void conflictByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpHead(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .head(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

    /**
     * Using Http OPTIONS
     */
    protected void unauthorizedByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localInvalidBasicAuthorization)
                )
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

    protected void methodNotAllowedByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isMethodNotAllowed())
                .andReturn();
    }

    protected void badRequestByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    protected void okByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    protected void notFoundByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isNotFound())
                .andReturn();
    }

    protected void conflictByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isConflict())
                .andReturn();
    }

    protected void serverErrorByHttpOptions(String uri, String id, String dataRequest) throws Exception {

        if (!uri.equals("")) localUriBaseTest = uri;
        if (!id.equals("")) localUriBaseTest = localUriBaseTest+"/"+id;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .options(localUriBaseTest)
                                .content(dataRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .header("Authorization", localBasicAuthorization)
                )
                .andExpect(status().isInternalServerError())
                .andReturn();
    }

}