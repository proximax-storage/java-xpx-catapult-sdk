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
 * The type of the metadata modification: * 0 - Add metadata. * 1 - Remove metadata. 
 */
@JsonAdapter(MetadataModificationTypeEnum.Adapter.class)
public enum MetadataModificationTypeEnum {
  
  NUMBER_0(0),
  
  NUMBER_1(1);

  private Integer value;

  MetadataModificationTypeEnum(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static MetadataModificationTypeEnum fromValue(Integer value) {
    for (MetadataModificationTypeEnum b : MetadataModificationTypeEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<MetadataModificationTypeEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final MetadataModificationTypeEnum enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public MetadataModificationTypeEnum read(final JsonReader jsonReader) throws IOException {
      Integer value = jsonReader.nextInt();
      return MetadataModificationTypeEnum.fromValue(value);
    }
  }
}

