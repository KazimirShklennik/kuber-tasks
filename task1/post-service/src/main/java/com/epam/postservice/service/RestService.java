/*
 * (c) 2022 EPAM systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.postservice.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface RestService {

    <T> ResponseEntity<T> executeGet(String url, Map<String, Object> param, Class<T> entityType, Map<String, String> headers);

    ResponseEntity<String> executePost(String url, HttpEntity<?> request, Map<String, Object> param);
}