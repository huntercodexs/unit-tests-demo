package com.huntercodexs.unittestsdemo.service;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import com.huntercodexs.unittestsdemo.external.ExternalUserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ClientService {

    @Value("${external.base-url-remote}")
    String baseUrlRemote;

    @Value("${external.base-uri-remote-get-user-id}")
    String uriGetUserId;

    @Value("${external.base-uri-remote-get-users}")
    String uriGetUsers;

    @Value("${external.base-uri-remote-create-user}")
    String uriCreateUser;

    @Value("${external.base-uri-remote-delete-user}")
    String uriDeleteUser;

    @Value("${external.base-uri-remote-update-user}")
    String uriUpdateUser;

    @Value("${external.base-uri-remote-patch-user}")
    String uriPatchUser;

    @Value("${external.basic-auth}")
    String remoteBasicAuth;

    private final RestTemplate restTemplate = new RestTemplate();

    protected HttpHeaders httpRequestHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", remoteBasicAuth);
        return httpHeaders;
    }

    protected HttpComponentsClientHttpRequestFactory httpClientFactory() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    public ResponseEntity<ExternalUserResponseDto> findUserById(String id) {

        log.info("findUserId by ClientService is calling");

        String urlFindUserId = this.baseUrlRemote+this.uriGetUserId.replaceFirst("@id", id);
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(httpRequestHeaders());

        try {
           return restTemplate.exchange(urlFindUserId, HttpMethod.GET, httpEntity, ExternalUserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on findUserId: " + re.getMessage());
        }

    }

    public ResponseEntity<ExternalUserResponseDto> findUsers() {

        log.info("findUsers by ClientService is calling");

        String urlFindUsers = this.baseUrlRemote+this.uriGetUsers;
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(httpRequestHeaders());

        try {
            return restTemplate.exchange(urlFindUsers, HttpMethod.GET, httpEntity, ExternalUserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on findUsers: " + re.getMessage());
        }

    }

    public ResponseEntity<ExternalUserResponseDto> createUser(UserRequestDto userRequestDto) {

        log.info("createUser by ClientService is calling");

        String urlCreateUser = this.baseUrlRemote+this.uriCreateUser;
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(userRequestDto, httpRequestHeaders());

        try {
            return restTemplate.postForEntity(urlCreateUser, httpEntity, ExternalUserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on createUser: " + re.getMessage());
        }

    }

    public ResponseEntity<ExternalUserResponseDto> deleteUserById(String id) {

        log.info("deleteUser by ClientService is calling");

        String urlDeleteUser = this.baseUrlRemote+this.uriDeleteUser.replaceFirst("@id", id);
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(httpRequestHeaders());

        try {
            return restTemplate.exchange(urlDeleteUser, HttpMethod.DELETE, httpEntity, ExternalUserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on deleteUser: " + re.getMessage());
        }

    }

    public ResponseEntity<ExternalUserResponseDto> updateUserById(String id, UserRequestDto userRequestDto) {

        log.info("updateUser by ClientService is calling");

        String urlUpdateUser = this.baseUrlRemote + this.uriUpdateUser.replaceFirst("@id", id);
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(userRequestDto, httpRequestHeaders());

        try {
            return restTemplate.exchange(urlUpdateUser, HttpMethod.PUT, httpEntity, ExternalUserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on update user: " + re.getMessage());
        }
    }

    public ResponseEntity<ExternalUserResponseDto> patchUserById(String id, UserRequestDto userRequestDto) {

        log.info("patchUser by ClientService is calling");

        String urlPatchUser = this.baseUrlRemote + this.uriPatchUser.replaceFirst("@id", id);
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(userRequestDto, httpRequestHeaders());

        try {
            restTemplate.setRequestFactory(httpClientFactory());
            return restTemplate.exchange(urlPatchUser, HttpMethod.PATCH, httpEntity, ExternalUserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on patch user: " + re.getMessage());
        }
    }

}
