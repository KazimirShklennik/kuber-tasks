/*
 * (c) 2022 EPAM systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.postservice.service.impl;

import com.epam.postservice.service.RestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestServiceImpl implements RestService {

    private final RestTemplate restTemplate;

    @Override
    public <T> ResponseEntity<T> executeGet(String url, Map<String, Object> param, Class<T> entityType, Map<String, String> headers) {
        ResponseEntity<T> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity<>(headers),
                    entityType, param);

        } catch (HttpClientErrorException e) {
            log.warn("The request failed: Url: {}, Reason: {}", url, e.getStatusCode());
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<String> executePost(String url, HttpEntity<?> request, Map<String, Object> param) {
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.postForEntity(url, request, String.class, param);
        } catch (HttpClientErrorException e) {
            log.warn("The request failed: Url: {}, Reason: {}", url, e.getStatusCode());
        }
        return responseEntity;
    }
}