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

/**
 * NodeInfoDTO
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-05-10T01:03:53.323+02:00")
public class NodeInfoDTO {
  @SerializedName("publicKey")
  private String publicKey = null;

  @SerializedName("port")
  private Integer port = null;

  @SerializedName("networkIdentifier")
  private Integer networkIdentifier = null;

  @SerializedName("version")
  private Integer version = null;

  @SerializedName("roles")
  private Integer roles = null;

  @SerializedName("host")
  private String host = null;

  @SerializedName("friendlyName")
  private String friendlyName = null;

  public NodeInfoDTO publicKey(String publicKey) {
    this.publicKey = publicKey;
    return this;
  }

   /**
   * Get publicKey
   * @return publicKey
  **/
  @ApiModelProperty(example = "EB6839C7E6BD0031FDD5F8CB5137E9BC950D7EE7756C46B767E68F3F58C24390", required = true, value = "")
  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }

  public NodeInfoDTO port(Integer port) {
    this.port = port;
    return this;
  }

   /**
   * Get port
   * @return port
  **/
  @ApiModelProperty(example = "7900", required = true, value = "")
  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public NodeInfoDTO networkIdentifier(Integer networkIdentifier) {
    this.networkIdentifier = networkIdentifier;
    return this;
  }

   /**
   * Get networkIdentifier
   * @return networkIdentifier
  **/
  @ApiModelProperty(example = "144", required = true, value = "")
  public Integer getNetworkIdentifier() {
    return networkIdentifier;
  }

  public void setNetworkIdentifier(Integer networkIdentifier) {
    this.networkIdentifier = networkIdentifier;
  }

  public NodeInfoDTO version(Integer version) {
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @ApiModelProperty(example = "0", required = true, value = "")
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public NodeInfoDTO roles(Integer roles) {
    this.roles = roles;
    return this;
  }

   /**
   * Get roles
   * @return roles
  **/
  @ApiModelProperty(example = "2", required = true, value = "")
  public Integer getRoles() {
    return roles;
  }

  public void setRoles(Integer roles) {
    this.roles = roles;
  }

  public NodeInfoDTO host(String host) {
    this.host = host;
    return this;
  }

   /**
   * Get host
   * @return host
  **/
  @ApiModelProperty(example = "api-node-0", required = true, value = "")
  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public NodeInfoDTO friendlyName(String friendlyName) {
    this.friendlyName = friendlyName;
    return this;
  }

   /**
   * Get friendlyName
   * @return friendlyName
  **/
  @ApiModelProperty(example = "api-node-0", required = true, value = "")
  public String getFriendlyName() {
    return friendlyName;
  }

  public void setFriendlyName(String friendlyName) {
    this.friendlyName = friendlyName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NodeInfoDTO nodeInfoDTO = (NodeInfoDTO) o;
    return Objects.equals(this.publicKey, nodeInfoDTO.publicKey) &&
        Objects.equals(this.port, nodeInfoDTO.port) &&
        Objects.equals(this.networkIdentifier, nodeInfoDTO.networkIdentifier) &&
        Objects.equals(this.version, nodeInfoDTO.version) &&
        Objects.equals(this.roles, nodeInfoDTO.roles) &&
        Objects.equals(this.host, nodeInfoDTO.host) &&
        Objects.equals(this.friendlyName, nodeInfoDTO.friendlyName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(publicKey, port, networkIdentifier, version, roles, host, friendlyName);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NodeInfoDTO {\n");
    
    sb.append("    publicKey: ").append(toIndentedString(publicKey)).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
    sb.append("    networkIdentifier: ").append(toIndentedString(networkIdentifier)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
    sb.append("    host: ").append(toIndentedString(host)).append("\n");
    sb.append("    friendlyName: ").append(toIndentedString(friendlyName)).append("\n");
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

