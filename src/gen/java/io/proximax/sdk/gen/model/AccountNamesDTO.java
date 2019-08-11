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

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * AccountNamesDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-08-11T15:04:35.591+02:00[Europe/Prague]")
public class AccountNamesDTO {
  public static final String SERIALIZED_NAME_ADDRESS = "address";
  @SerializedName(SERIALIZED_NAME_ADDRESS)
  private String address;

  public static final String SERIALIZED_NAME_NAMES = "names";
  @SerializedName(SERIALIZED_NAME_NAMES)
  private List<String> names = new ArrayList<>();

  public AccountNamesDTO address(String address) {
    this.address = address;
    return this;
  }

   /**
   * The address of the account in hexadecimal.
   * @return address
  **/
  @ApiModelProperty(example = "9081FCCB41F8C8409A9B99E485E0E28D23BD6304EF7215E01A", required = true, value = "The address of the account in hexadecimal.")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public AccountNamesDTO names(List<String> names) {
    this.names = names;
    return this;
  }

  public AccountNamesDTO addNamesItem(String namesItem) {
    this.names.add(namesItem);
    return this;
  }

   /**
   * The mosaic linked namespace names.
   * @return names
  **/
  @ApiModelProperty(example = "[\"alias1\",\"alias2\"]", required = true, value = "The mosaic linked namespace names.")
  public List<String> getNames() {
    return names;
  }

  public void setNames(List<String> names) {
    this.names = names;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountNamesDTO accountNamesDTO = (AccountNamesDTO) o;
    return Objects.equals(this.address, accountNamesDTO.address) &&
        Objects.equals(this.names, accountNamesDTO.names);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, names);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountNamesDTO {\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    names: ").append(toIndentedString(names)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

