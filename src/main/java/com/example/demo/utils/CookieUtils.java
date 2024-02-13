package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class CookieUtils {

    private final Environment environment;

    public CookieUtils(Environment environment) {
        this.environment = environment;
    }

    public void setCookie(HttpServletResponse response, String key, String value, String domain, String path, int maxAge, boolean isHttpOnly) {
        Cookie cookie = new Cookie(key, value);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(isHttpOnly);
        cookie.setSecure(!environment.acceptsProfiles(Profiles.of("dev")));
        response.addCookie(cookie);

        log.debug("Cookie {} successfully set", key);
    }
}
