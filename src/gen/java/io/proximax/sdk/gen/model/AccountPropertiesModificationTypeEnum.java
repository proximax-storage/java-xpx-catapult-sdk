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
 * The account properties modification type: * 0 - Add property. * 1 - Remove property. 
 */
@JsonAdapter(AccountPropertiesModificationTypeEnum.Adapter.class)
public enum AccountPropertiesModificationTypeEnum {
  
  NUMBER_0(0),
  
  NUMBER_1(1);

  private Integer value;

  AccountPropertiesModificationTypeEnum(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static AccountPropertiesModificationTypeEnum fromValue(Integer value) {
    for (AccountPropertiesModificationTypeEnum b : AccountPropertiesModificationTypeEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<AccountPropertiesModificationTypeEnum> {
    @Override
    public void write(final JsonWriter jsonWriter, final AccountPropertiesModificationTypeEnum enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public AccountPropertiesModificationTypeEnum read(final JsonReader jsonReader) throws IOException {
      Integer value = jsonReader.nextInt();
      return AccountPropertiesModificationTypeEnum.fromValue(value);
    }
  }
}

