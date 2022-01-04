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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

/**
 * NamespaceIds
 */
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-09-22T22:57:50.932+02:00[Europe/Prague]")
public class NamespaceIds {
  public static final String SERIALIZED_NAME_NAMESPACE_IDS = "namespaceIds";
  @SerializedName(SERIALIZED_NAME_NAMESPACE_IDS)
  private List<String> namespaceIds = new ArrayList<>();

  public NamespaceIds namespaceIds(List<String> namespaceIds) {
    this.namespaceIds = namespaceIds;
    return this;
  }

  public NamespaceIds addNamespaceIdsItem(String namespaceIdsItem) {
    if (this.namespaceIds == null) {
      this.namespaceIds = new ArrayList<>();
    }
    this.namespaceIds.add(namespaceIdsItem);
    return this;
  }

   /**
   * The array of namespace identifiers.
   * @return namespaceIds
  **/
  @ApiModelProperty(example = "[\"84b3552d375ffa4b\"]", value = "The array of namespace identifiers.")
  public List<String> getNamespaceIds() {
    return namespaceIds;
  }

  public void setNamespaceIds(List<String> namespaceIds) {
    this.namespaceIds = namespaceIds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NamespaceIds namespaceIds = (NamespaceIds) o;
    return Objects.equals(this.namespaceIds, namespaceIds.namespaceIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(namespaceIds);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NamespaceIds {\n");
    sb.append("    namespaceIds: ").append(toIndentedString(namespaceIds)).append("\n");
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

