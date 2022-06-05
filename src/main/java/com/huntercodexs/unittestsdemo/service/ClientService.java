package com.huntercodexs.unittestsdemo.service;

import com.huntercodexs.unittestsdemo.dto.request.UserRequestDto;
import com.huntercodexs.unittestsdemo.dto.response.UserResponseDto;
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

    @Value("${application.base-url-remote}")
    String baseUrlRemote;

    @Value("${application.base-uri-remote-get-user-id}")
    String uriGetUserId;

    @Value("${application.base-uri-remote-get-users}")
    String uriGetUsers;

    @Value("${application.base-uri-remote-create-user}")
    String uriCreateUser;

    @Value("${application.base-uri-remote-delete-user}")
    String uriDeleteUser;

    @Value("${application.base-uri-remote-update-user}")
    String uriUpdateUser;

    @Value("${application.base-uri-remote-patch-user}")
    String uriPatchUser;

    @Value("${remote.basic-auth}")
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

    public ResponseEntity<UserResponseDto> findUserId(String id) {

        log.info("findUserId by ClientService is calling");

        String urlFindUserId = this.baseUrlRemote+this.uriGetUserId.replaceFirst("@id", id);
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(httpRequestHeaders());

        try {
            return restTemplate.exchange(urlFindUserId, HttpMethod.GET, httpEntity, UserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on findUserId");
        }

    }

    public ResponseEntity<UserResponseDto> findUsers() {

        log.info("findUsers by ClientService is calling");

        String urlFindUsers = this.baseUrlRemote+this.uriGetUsers;
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(httpRequestHeaders());

        try {
            return restTemplate.exchange(urlFindUsers, HttpMethod.GET, httpEntity, UserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on findUsers");
        }

    }

    public ResponseEntity<UserResponseDto> createUser(UserRequestDto userRequestDto) {

        log.info("createUser by ClientService is calling");

        String urlCreateUser = this.baseUrlRemote+this.uriCreateUser;
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(userRequestDto, httpRequestHeaders());

        try {
            return restTemplate.postForEntity(urlCreateUser, httpEntity, UserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on createUser");
        }

    }

    public ResponseEntity<UserResponseDto> deleteUser(String id) {

        log.info("deleteUser by ClientService is calling");

        String urlDeleteUser = this.baseUrlRemote+this.uriDeleteUser.replaceFirst("@id", id);
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(httpRequestHeaders());

        try {
            return restTemplate.exchange(urlDeleteUser, HttpMethod.DELETE, httpEntity, UserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on deleteUser");
        }

    }

    public ResponseEntity<UserResponseDto> updateUser(String id, UserRequestDto userRequestDto) {

        log.info("updateUser by ClientService is calling");

        String urlUpdateUser = this.baseUrlRemote + this.uriUpdateUser.replaceFirst("@id", id);
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(userRequestDto, httpRequestHeaders());

        try {
            return restTemplate.exchange(urlUpdateUser, HttpMethod.PUT, httpEntity, UserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on update user");
        }
    }

    public ResponseEntity<UserResponseDto> patchUser(String id, UserRequestDto userRequestDto) {

        log.info("patchUser by ClientService is calling");

        String urlPatchUser = this.baseUrlRemote + this.uriPatchUser.replaceFirst("@id", id);
        HttpEntity<UserRequestDto> httpEntity = new HttpEntity<>(userRequestDto, httpRequestHeaders());

        try {
            restTemplate.setRequestFactory(httpClientFactory());
            return restTemplate.exchange(urlPatchUser, HttpMethod.PATCH, httpEntity, UserResponseDto.class);
        } catch (RuntimeException re) {
            throw new RuntimeException("Error on patch user");
        }
    }

}
