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
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * AccountPropertiesDTO
 */
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-09-22T22:57:50.932+02:00[Europe/Prague]")
public class AccountPropertiesDTO {
  public static final String SERIALIZED_NAME_ADDRESS = "address";
  @SerializedName(SERIALIZED_NAME_ADDRESS)
  private String address;

  public static final String SERIALIZED_NAME_PROPERTIES = "properties";
  @SerializedName(SERIALIZED_NAME_PROPERTIES)
  private List<AccountPropertyDTO> properties = new ArrayList<>();

  public AccountPropertiesDTO address(String address) {
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

  public AccountPropertiesDTO properties(List<AccountPropertyDTO> properties) {
    this.properties = properties;
    return this;
  }

  public AccountPropertiesDTO addPropertiesItem(AccountPropertyDTO propertiesItem) {
    this.properties.add(propertiesItem);
    return this;
  }

   /**
   * Get properties
   * @return properties
  **/
  @ApiModelProperty(required = true, value = "")
  public List<AccountPropertyDTO> getProperties() {
    return properties;
  }

  public void setProperties(List<AccountPropertyDTO> properties) {
    this.properties = properties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountPropertiesDTO accountPropertiesDTO = (AccountPropertiesDTO) o;
    return Objects.equals(this.address, accountPropertiesDTO.address) &&
        Objects.equals(this.properties, accountPropertiesDTO.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, properties);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountPropertiesDTO {\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
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

