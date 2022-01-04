/*
 * Catapult REST API Reference
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.7.15
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.proximax.sdk.gen.model;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * The namespace type: * 0 -  Root namespace. * 1 -  Subnamespace. 
 */
@JsonAdapter(NamespaceTypeEnum.Adapter.class)
public enum NamespaceTypeEnum {
  
  NUMBER_0(0),
  
  NUMBER_1(1);

  private Integer value;

  NamespaceTypeEnum(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static NamespaceTypeEnum fromValue(Integer value) {
    for (NamespaceTypeEnum b : NamespaceTypeEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<NamespaceTypeEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final NamespaceTypeEnum enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public NamespaceTypeEnum read(final JsonReader jsonReader) throws IOException {
      Integer value = jsonReader.nextInt();
      return NamespaceTypeEnum.fromValue(value);
    }
  }
}

