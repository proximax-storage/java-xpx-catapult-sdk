/*
 * Catapult REST API Reference
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.13
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.nem.sdk.infrastructure.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.nem.sdk.infrastructure.model.AccountPropertiesDTO;
import io.nem.sdk.infrastructure.model.AccountPropertiesMetaDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * AccountPropertiesInfoDTO
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-04-24T01:47:49.709+02:00")
public class AccountPropertiesInfoDTO {
  @SerializedName("meta")
  private AccountPropertiesMetaDTO meta = null;

  @SerializedName("accountProperties")
  private AccountPropertiesDTO accountProperties = null;

  public AccountPropertiesInfoDTO meta(AccountPropertiesMetaDTO meta) {
    this.meta = meta;
    return this;
  }

   /**
   * Get meta
   * @return meta
  **/
  @ApiModelProperty(required = true, value = "")
  public AccountPropertiesMetaDTO getMeta() {
    return meta;
  }

  public void setMeta(AccountPropertiesMetaDTO meta) {
    this.meta = meta;
  }

  public AccountPropertiesInfoDTO accountProperties(AccountPropertiesDTO accountProperties) {
    this.accountProperties = accountProperties;
    return this;
  }

   /**
   * Get accountProperties
   * @return accountProperties
  **/
  @ApiModelProperty(required = true, value = "")
  public AccountPropertiesDTO getAccountProperties() {
    return accountProperties;
  }

  public void setAccountProperties(AccountPropertiesDTO accountProperties) {
    this.accountProperties = accountProperties;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountPropertiesInfoDTO accountPropertiesInfoDTO = (AccountPropertiesInfoDTO) o;
    return Objects.equals(this.meta, accountPropertiesInfoDTO.meta) &&
        Objects.equals(this.accountProperties, accountPropertiesInfoDTO.accountProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(meta, accountProperties);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountPropertiesInfoDTO {\n");
    
    sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
    sb.append("    accountProperties: ").append(toIndentedString(accountProperties)).append("\n");
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
