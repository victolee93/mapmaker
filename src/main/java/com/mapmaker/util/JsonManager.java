package com.mapmaker.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/*
 * ObjectMapper 클래스를 사용하여, DTO <---> Json 간의 변환을 담당하는 util
 */
public class JsonManager {
    private static ObjectMapper objectMapper = new ObjectMapper();

    // DTO 클래스를 Json 문자열로 반환
    public static String convertDtoToJson(Object dtoObject) {
        String jsonString = "";

        try {
            jsonString = objectMapper.writeValueAsString(dtoObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    // Json 문자열을 DTO 클래스로 반환
    public static <T> List<T> covnertJsonToDto( Class<T> contentClass, String json) {
        List<T> dtoList = null;
        JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, contentClass);

        try {
            dtoList = objectMapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dtoList;
    }
}
