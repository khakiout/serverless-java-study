package com.komiks.api.commons;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility methods for JSON parsing.
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();


    /**
     * Convert the object into JSON string.
     *
     * @param object the Java object to be converted to JSON string.
     * @return the JSON string.
     * @throws IOException if a parsing error occurs.
     */
    public static String toJson(Object object) throws IOException {
        String json = null;
        if (object != null) {
            objectMapper.setSerializationInclusion(Include.NON_NULL);
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            json = objectMapper.writeValueAsString(object);
        }
        return json;
    }

    /**
     * Convert the object into formatted JSON string using the default pretty printer.
     *
     * @param object the Java object to be converted to formatted JSON string.
     * @return the JSON string.
     * @throws IOException if a parsing error occurs.
     */
    public static String toFormattedJson(Object object) throws IOException {
        String json = null;
        if (object != null) {
            objectMapper.setSerializationInclusion(Include.NON_NULL);
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }
        return json;
    }

    /**
     * Parse a JSON string into a Java class.
     *
     * @param json the JSON string.
     * @param type class of type T
     * @param <T>  any class that can be converted from JSON.
     * @return a Java instance from the desired class.
     * @throws IOException if a parsing error occurs.
     */
    public static <T extends Object> T parseJson(String json, Class<T> type) throws IOException {
        T object = null;
        if (!isNullOrEmpty(json)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            object = mapper.readValue(json, type);
        }
        return object;
    }

    /**
     * Check if String value is null or empty.
     *
     * @param string the value
     */
    public static boolean isNullOrEmpty(String string) {
        if (string == null) {
            return true;
        }

        return string.trim().isEmpty();
    }

    /**
     * Convert Map to Entity.
     *
     * @param params the params
     * @param type   the type
     * @return the entity
     * @throws IOException the IO exception
     */
    public static <T extends Object> T convertToEntity(Map<String, List<String>> params,
        Class<T> type)
        throws IOException {
        Map<String, String> data = new HashMap<>();
        for (Map.Entry<String, List<String>> keySet : params.entrySet()) {
            String key = keySet.getKey();
            List<String> content = keySet.getValue();
            if (content != null && content.size() > 0) {
                data.put(key, content.get(0));
            }
        }

        String json = toJson(data);
        return parseJson(json, type);
    }

    /**
     * Convert Object to Map.
     *
     * @param obj obj to be converted
     * @return Map converted from obj
     */
    @SuppressWarnings("unchecked")
    public static Map convertToMap(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.convertValue(obj, Map.class);
        return map;
    }

    /**
     * Convert an object to JSON and then to desired class.
     *
     * @param fromValue the object to be converted.
     * @param type      the target class type
     * @param <T>       the Class type
     * @return the converted object.
     */
    public static <T extends Object> T convertValue(Object fromValue, Class<T> type) {
        return objectMapper.convertValue(fromValue, type);
    }

}
