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


package io.proximax.sdk.infrastructure.model;

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
 * PublicKeys
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-05-10T01:03:53.323+02:00")
public class PublicKeys {
  @SerializedName("publicKeys")
  private List<String> publicKeys = null;

  public PublicKeys publicKeys(List<String> publicKeys) {
    this.publicKeys = publicKeys;
    return this;
  }

  public PublicKeys addPublicKeysItem(String publicKeysItem) {
    if (this.publicKeys == null) {
      this.publicKeys = new ArrayList<String>();
    }
    this.publicKeys.add(publicKeysItem);
    return this;
  }

   /**
   * Get publicKeys
   * @return publicKeys
  **/
  @ApiModelProperty(example = "[\"8599BA6DB5B81BB69F96B88DD80A3B9EB7BBF8849CBD979100E89D69C30356E0\",\"3DCB6E5EFF4D63A38902EF948E895B01D6EA497EBF84B1460C14CA5BEDCAD9F3\"]", value = "")
  public List<String> getPublicKeys() {
    return publicKeys;
  }

  public void setPublicKeys(List<String> publicKeys) {
    this.publicKeys = publicKeys;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PublicKeys publicKeys = (PublicKeys) o;
    return Objects.equals(this.publicKeys, publicKeys.publicKeys);
  }

  @Override
  public int hashCode() {
    return Objects.hash(publicKeys);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PublicKeys {\n");
    
    sb.append("    publicKeys: ").append(toIndentedString(publicKeys)).append("\n");
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

