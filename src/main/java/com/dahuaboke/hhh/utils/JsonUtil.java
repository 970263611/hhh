package com.dahuaboke.hhh.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * author: dahua
 * date: 2023/11/20 17:43
 */
public class JsonUtil {

    private static class singleJson {
        private static final ObjectMapper INSTANCE = new ObjectMapper();
    }

    public static String toString(Object obj) {
        try {
            return singleJson.INSTANCE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Object decode(Response response, Type type) throws IOException {
//        if (response.body() == null)
//            return null;
//        Reader reader = response.body().asReader(Util.UTF_8);
//        if (!reader.markSupported()) {
//            reader = new BufferedReader(reader, 1);
//        }
//        try {
//            // Read the first byte to see if we have any data
//            reader.mark(1);
//            if (reader.read() == -1) {
//                return null; // Eagerly returning null avoids "No content to map due to end-of-input"
//            }
//            reader.reset();
//            return mapper.readValue(reader, mapper.constructType(type));
//        } catch (RuntimeJsonMappingException e) {
//            if (e.getCause() != null && e.getCause() instanceof IOException) {
//                throw IOException.class.cast(e.getCause());
//            }
//            throw e;
//        }
//    }
}
