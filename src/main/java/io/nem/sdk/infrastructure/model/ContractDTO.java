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
import io.nem.sdk.infrastructure.model.UInt64DTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ContractDTO
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-04-24T01:47:49.709+02:00")
public class ContractDTO {
  @SerializedName("multisig")
  private String multisig = null;

  @SerializedName("multisigAddress")
  private String multisigAddress = null;

  @SerializedName("start")
  private UInt64DTO start = null;

  @SerializedName("duration")
  private UInt64DTO duration = null;

  @SerializedName("hash")
  private String hash = null;

  @SerializedName("customers")
  private List<String> customers = new ArrayList<String>();

  @SerializedName("executors")
  private List<String> executors = new ArrayList<String>();

  @SerializedName("verifiers")
  private List<String> verifiers = new ArrayList<String>();

  public ContractDTO multisig(String multisig) {
    this.multisig = multisig;
    return this;
  }

   /**
   * Get multisig
   * @return multisig
  **/
  @ApiModelProperty(example = "EB8923957301F796C884977234D20B0388A3AD6F865F1ACC7D3A94AFF597D59D", required = true, value = "")
  public String getMultisig() {
    return multisig;
  }

  public void setMultisig(String multisig) {
    this.multisig = multisig;
  }

  public ContractDTO multisigAddress(String multisigAddress) {
    this.multisigAddress = multisigAddress;
    return this;
  }

   /**
   * Get multisigAddress
   * @return multisigAddress
  **/
  @ApiModelProperty(example = "905BD08D85AF3224A62C2EDAB004CFF4432271E662B333BA34", required = true, value = "")
  public String getMultisigAddress() {
    return multisigAddress;
  }

  public void setMultisigAddress(String multisigAddress) {
    this.multisigAddress = multisigAddress;
  }

  public ContractDTO start(UInt64DTO start) {
    this.start = start;
    return this;
  }

   /**
   * Get start
   * @return start
  **/
  @ApiModelProperty(required = true, value = "")
  public UInt64DTO getStart() {
    return start;
  }

  public void setStart(UInt64DTO start) {
    this.start = start;
  }

  public ContractDTO duration(UInt64DTO duration) {
    this.duration = duration;
    return this;
  }

   /**
   * Get duration
   * @return duration
  **/
  @ApiModelProperty(required = true, value = "")
  public UInt64DTO getDuration() {
    return duration;
  }

  public void setDuration(UInt64DTO duration) {
    this.duration = duration;
  }

  public ContractDTO hash(String hash) {
    this.hash = hash;
    return this;
  }

   /**
   * Get hash
   * @return hash
  **/
  @ApiModelProperty(example = "D8E06B597BEE34263E9C970A50B5341783EFF67EF00637644C114447BE1905DA", required = true, value = "")
  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public ContractDTO customers(List<String> customers) {
    this.customers = customers;
    return this;
  }

  public ContractDTO addCustomersItem(String customersItem) {
    this.customers.add(customersItem);
    return this;
  }

   /**
   * Get customers
   * @return customers
  **/
  @ApiModelProperty(required = true, value = "")
  public List<String> getCustomers() {
    return customers;
  }

  public void setCustomers(List<String> customers) {
    this.customers = customers;
  }

  public ContractDTO executors(List<String> executors) {
    this.executors = executors;
    return this;
  }

  public ContractDTO addExecutorsItem(String executorsItem) {
    this.executors.add(executorsItem);
    return this;
  }

   /**
   * Get executors
   * @return executors
  **/
  @ApiModelProperty(required = true, value = "")
  public List<String> getExecutors() {
    return executors;
  }

  public void setExecutors(List<String> executors) {
    this.executors = executors;
  }

  public ContractDTO verifiers(List<String> verifiers) {
    this.verifiers = verifiers;
    return this;
  }

  public ContractDTO addVerifiersItem(String verifiersItem) {
    this.verifiers.add(verifiersItem);
    return this;
  }

   /**
   * Get verifiers
   * @return verifiers
  **/
  @ApiModelProperty(required = true, value = "")
  public List<String> getVerifiers() {
    return verifiers;
  }

  public void setVerifiers(List<String> verifiers) {
    this.verifiers = verifiers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContractDTO contractDTO = (ContractDTO) o;
    return Objects.equals(this.multisig, contractDTO.multisig) &&
        Objects.equals(this.multisigAddress, contractDTO.multisigAddress) &&
        Objects.equals(this.start, contractDTO.start) &&
        Objects.equals(this.duration, contractDTO.duration) &&
        Objects.equals(this.hash, contractDTO.hash) &&
        Objects.equals(this.customers, contractDTO.customers) &&
        Objects.equals(this.executors, contractDTO.executors) &&
        Objects.equals(this.verifiers, contractDTO.verifiers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(multisig, multisigAddress, start, duration, hash, customers, executors, verifiers);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContractDTO {\n");
    
    sb.append("    multisig: ").append(toIndentedString(multisig)).append("\n");
    sb.append("    multisigAddress: ").append(toIndentedString(multisigAddress)).append("\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
    sb.append("    customers: ").append(toIndentedString(customers)).append("\n");
    sb.append("    executors: ").append(toIndentedString(executors)).append("\n");
    sb.append("    verifiers: ").append(toIndentedString(verifiers)).append("\n");
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

