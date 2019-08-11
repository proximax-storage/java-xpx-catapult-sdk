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
import io.proximax.sdk.gen.model.MultisigModificationTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * CosignatoryModificationDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-08-11T15:04:35.591+02:00[Europe/Prague]")
public class CosignatoryModificationDTO {
  public static final String SERIALIZED_NAME_MODIFICATION_TYPE = "modificationType";
  @SerializedName(SERIALIZED_NAME_MODIFICATION_TYPE)
  private MultisigModificationTypeEnum modificationType;

  public static final String SERIALIZED_NAME_COSIGNATORY_PUBLIC_KEY = "cosignatoryPublicKey";
  @SerializedName(SERIALIZED_NAME_COSIGNATORY_PUBLIC_KEY)
  private String cosignatoryPublicKey;

  public CosignatoryModificationDTO modificationType(MultisigModificationTypeEnum modificationType) {
    this.modificationType = modificationType;
    return this;
  }

   /**
   * Get modificationType
   * @return modificationType
  **/
  @ApiModelProperty(required = true, value = "")
  public MultisigModificationTypeEnum getModificationType() {
    return modificationType;
  }

  public void setModificationType(MultisigModificationTypeEnum modificationType) {
    this.modificationType = modificationType;
  }

  public CosignatoryModificationDTO cosignatoryPublicKey(String cosignatoryPublicKey) {
    this.cosignatoryPublicKey = cosignatoryPublicKey;
    return this;
  }

   /**
   * The public key of the cosignatory account.
   * @return cosignatoryPublicKey
  **/
  @ApiModelProperty(example = "5D9513282B65A12A1B68DCB67DB64245721F7AE7822BE441FE813173803C512C", required = true, value = "The public key of the cosignatory account.")
  public String getCosignatoryPublicKey() {
    return cosignatoryPublicKey;
  }

  public void setCosignatoryPublicKey(String cosignatoryPublicKey) {
    this.cosignatoryPublicKey = cosignatoryPublicKey;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosignatoryModificationDTO cosignatoryModificationDTO = (CosignatoryModificationDTO) o;
    return Objects.equals(this.modificationType, cosignatoryModificationDTO.modificationType) &&
        Objects.equals(this.cosignatoryPublicKey, cosignatoryModificationDTO.cosignatoryPublicKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(modificationType, cosignatoryPublicKey);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosignatoryModificationDTO {\n");
    sb.append("    modificationType: ").append(toIndentedString(modificationType)).append("\n");
    sb.append("    cosignatoryPublicKey: ").append(toIndentedString(cosignatoryPublicKey)).append("\n");
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

