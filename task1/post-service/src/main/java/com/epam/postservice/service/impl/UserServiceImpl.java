package com.epam.postservice.service.impl;

import com.epam.postservice.entity.User;
import com.epam.postservice.service.RestService;
import com.epam.postservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.epam.postservice.utils.GsonUtils.getGson;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String AUTHOR_ID = "authorId";
    @Value(value = "${services.host.user}")
    private String songServiceHost;

    private final static String PATH_GET_BY_AUTHOR_ID = "/api/v1/user-service/users/{authorId}";
    private final static String PATH_POST_META_DATA = "/api/v1/user-service/users";
    private final static String URL_TEMPLATE = "%s/%s";

    private final RestService restService;

    @Override
    public User getUser(Integer authorId) {
        String url = String.format(URL_TEMPLATE, songServiceHost, PATH_GET_BY_AUTHOR_ID);
        Map<String, Object> param = new HashMap<>();
        param.put(AUTHOR_ID, authorId);

        ResponseEntity<User> responseEntity =
                restService.executeGet(url, param, User.class, new HashMap<>());

        if (responseEntity == null) {
            log.warn("There is no User. Author ID: {}", authorId);
            return null;
        }

        return responseEntity.getBody();
    }

    @Override
    public User updateUserData(User user) {
        String url = String.format(URL_TEMPLATE, songServiceHost, PATH_POST_META_DATA);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(createBody(user), headers);

        ResponseEntity<String> stringResponseEntity = restService.executePost(url, request, new HashMap<>());

        if (stringResponseEntity == null) {
            log.warn("Can't send song metadata. Resource key: {}", user.getUserId());
            return null;
        }

        return getGson().fromJson(stringResponseEntity.getBody(), User.class);
    }

    @SneakyThrows
    private static <T> String createBody(T entity) {
        ObjectWriter ow = new ObjectMapper()
                .writer()
                .forType(entity.getClass())
                .withDefaultPrettyPrinter();
        return ow.writeValueAsString(entity);
    }
}