package com.onedevoneops.springrestapp.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.core.io.ClassPathResource;

/**
 * @author erdoganf
 */
public class FileLoader {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  public static <T> List<T> loadJson(String fileName, Class<T> clazz) throws IOException {
    InputStreamReader isr = new InputStreamReader(FileLoader.class.getClassLoader().getResourceAsStream(fileName));
    CollectionType clazzCollection = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
    return OBJECT_MAPPER.readValue(isr, clazzCollection);
  }
}
