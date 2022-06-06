package com.huntercodexs.unittestsdemo.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ExternalApplicationSimulatorSecurity {

    @Value("Basic MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY6ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U=")
    String basicAuth;

    public Boolean authorization(HttpServletRequest headers) {
        String authHeader = headers.getHeader("Authorization");
        return authHeader.equals(basicAuth);
    }
}
