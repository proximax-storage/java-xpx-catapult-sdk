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

/**
 * EmbeddedTransactionMetaDTO
 */
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-09-22T22:57:50.932+02:00[Europe/Prague]")
public class EmbeddedTransactionMetaDTO {
  public static final String SERIALIZED_NAME_HEIGHT = "height";
  @SerializedName(SERIALIZED_NAME_HEIGHT)
  private UInt64DTO height = new UInt64DTO();

  public static final String SERIALIZED_NAME_HASH = "hash";
  @SerializedName(SERIALIZED_NAME_HASH)
  private String hash;

  public static final String SERIALIZED_NAME_MERKLE_COMPONENT_HASH = "merkleComponentHash";
  @SerializedName(SERIALIZED_NAME_MERKLE_COMPONENT_HASH)
  private String merkleComponentHash;

  public static final String SERIALIZED_NAME_INDEX = "index";
  @SerializedName(SERIALIZED_NAME_INDEX)
  private Integer index;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private String id;

  public EmbeddedTransactionMetaDTO height(UInt64DTO height) {
    this.height = height;
    return this;
  }

   /**
   * Get height
   * @return height
  **/
  @ApiModelProperty(required = true, value = "")
  public UInt64DTO getHeight() {
    return height;
  }

  public void setHeight(UInt64DTO height) {
    this.height = height;
  }

  public EmbeddedTransactionMetaDTO hash(String hash) {
    this.hash = hash;
    return this;
  }

   /**
   * Get hash
   * @return hash
  **/
  @ApiModelProperty(required = true, value = "")
  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public EmbeddedTransactionMetaDTO merkleComponentHash(String merkleComponentHash) {
    this.merkleComponentHash = merkleComponentHash;
    return this;
  }

   /**
   * Get merkleComponentHash
   * @return merkleComponentHash
  **/
  @ApiModelProperty(required = true, value = "")
  public String getMerkleComponentHash() {
    return merkleComponentHash;
  }

  public void setMerkleComponentHash(String merkleComponentHash) {
    this.merkleComponentHash = merkleComponentHash;
  }

  public EmbeddedTransactionMetaDTO index(Integer index) {
    this.index = index;
    return this;
  }

   /**
   * Get index
   * @return index
  **/
  @ApiModelProperty(required = true, value = "")
  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public EmbeddedTransactionMetaDTO id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmbeddedTransactionMetaDTO embeddedTransactionMetaDTO = (EmbeddedTransactionMetaDTO) o;
    return Objects.equals(this.height, embeddedTransactionMetaDTO.height) &&
        Objects.equals(this.hash, embeddedTransactionMetaDTO.hash) &&
        Objects.equals(this.merkleComponentHash, embeddedTransactionMetaDTO.merkleComponentHash) &&
        Objects.equals(this.index, embeddedTransactionMetaDTO.index) &&
        Objects.equals(this.id, embeddedTransactionMetaDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(height, hash, merkleComponentHash, index, id);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmbeddedTransactionMetaDTO {\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
    sb.append("    merkleComponentHash: ").append(toIndentedString(merkleComponentHash)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

