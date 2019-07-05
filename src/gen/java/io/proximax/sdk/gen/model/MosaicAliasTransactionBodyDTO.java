/*
 * ProximaX Chain REST API Reference
 * ProximaX Chain REST API Reference
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
import io.proximax.sdk.gen.model.AliasActionEnum;
import io.proximax.sdk.gen.model.UInt64DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * MosaicAliasTransactionBodyDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-07-01T21:35:58.261+02:00[Europe/Prague]")
public class MosaicAliasTransactionBodyDTO {
  public static final String SERIALIZED_NAME_ALIAS_ACTION = "aliasAction";
  @SerializedName(SERIALIZED_NAME_ALIAS_ACTION)
  private AliasActionEnum aliasAction;

  public static final String SERIALIZED_NAME_NAMESPACE_ID = "namespaceId";
  @SerializedName(SERIALIZED_NAME_NAMESPACE_ID)
  private UInt64DTO namespaceId = new UInt64DTO();

  public static final String SERIALIZED_NAME_MOSAIC_ID = "mosaicId";
  @SerializedName(SERIALIZED_NAME_MOSAIC_ID)
  private UInt64DTO mosaicId = new UInt64DTO();

  public MosaicAliasTransactionBodyDTO aliasAction(AliasActionEnum aliasAction) {
    this.aliasAction = aliasAction;
    return this;
  }

   /**
   * Get aliasAction
   * @return aliasAction
  **/
  @ApiModelProperty(required = true, value = "")
  public AliasActionEnum getAliasAction() {
    return aliasAction;
  }

  public void setAliasAction(AliasActionEnum aliasAction) {
    this.aliasAction = aliasAction;
  }

  public MosaicAliasTransactionBodyDTO namespaceId(UInt64DTO namespaceId) {
    this.namespaceId = namespaceId;
    return this;
  }

   /**
   * Get namespaceId
   * @return namespaceId
  **/
  @ApiModelProperty(required = true, value = "")
  public UInt64DTO getNamespaceId() {
    return namespaceId;
  }

  public void setNamespaceId(UInt64DTO namespaceId) {
    this.namespaceId = namespaceId;
  }

  public MosaicAliasTransactionBodyDTO mosaicId(UInt64DTO mosaicId) {
    this.mosaicId = mosaicId;
    return this;
  }

   /**
   * Get mosaicId
   * @return mosaicId
  **/
  @ApiModelProperty(required = true, value = "")
  public UInt64DTO getMosaicId() {
    return mosaicId;
  }

  public void setMosaicId(UInt64DTO mosaicId) {
    this.mosaicId = mosaicId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MosaicAliasTransactionBodyDTO mosaicAliasTransactionBodyDTO = (MosaicAliasTransactionBodyDTO) o;
    return Objects.equals(this.aliasAction, mosaicAliasTransactionBodyDTO.aliasAction) &&
        Objects.equals(this.namespaceId, mosaicAliasTransactionBodyDTO.namespaceId) &&
        Objects.equals(this.mosaicId, mosaicAliasTransactionBodyDTO.mosaicId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aliasAction, namespaceId, mosaicId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MosaicAliasTransactionBodyDTO {\n");
    sb.append("    aliasAction: ").append(toIndentedString(aliasAction)).append("\n");
    sb.append("    namespaceId: ").append(toIndentedString(namespaceId)).append("\n");
    sb.append("    mosaicId: ").append(toIndentedString(mosaicId)).append("\n");
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
