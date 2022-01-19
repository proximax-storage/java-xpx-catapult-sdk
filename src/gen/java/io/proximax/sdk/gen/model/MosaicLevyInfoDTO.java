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
 * MosaicInfoDTO
 */
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-09-22T22:57:50.932+02:00[Europe/Prague]")
public class MosaicLevyInfoDTO {
    public static final String SERIALIZED_NAME_TYPE = "type";
    @SerializedName(SERIALIZED_NAME_TYPE)
    private MosaicLevyTypeEnum type = null;

    public static final String SERIALIZED_NAME_RECIPIENT = "recipient";
    @SerializedName(SERIALIZED_NAME_RECIPIENT)
    private String recipient = null;

    public static final String SERIALIZED_NAME_MOSAICID = "mosaicId";
    @SerializedName(SERIALIZED_NAME_MOSAICID)
    private UInt64DTO mosaicId = null;

    public static final String SERIALIZED_NAME_FEE = "fee";
    @SerializedName(SERIALIZED_NAME_FEE)
    private UInt64DTO fee = null;
   
    public MosaicLevyInfoDTO type(MosaicLevyTypeEnum type) {
        this.type = type;
        return this;
    }
    /**
     * Get type
     * 
     * @return Mosaic levy type
     **/
    @ApiModelProperty(required = true, value = "")
     public MosaicLevyTypeEnum getMosaicLevyType() {
         return type;
     }

    public void setMosaicLevyType(MosaicLevyTypeEnum type) {
        this.type = type;
    }
    
    public MosaicLevyInfoDTO recipient(String recipient) {
        this.recipient = recipient;
        return this;
    }
    /**
     * Get recipient
     * 
     * @return recipient
     **/
    @ApiModelProperty(required = true, value = "")
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

   
     public MosaicLevyInfoDTO mosaicId(UInt64DTO mosaicId) {
        this.mosaicId = mosaicId;
        return this;
    }
    /**
     * Get mosaicId
     * 
     * @return mosaicId
     **/
    @ApiModelProperty(required = true, value = "")
    public UInt64DTO getMosaicId() {
        return mosaicId;
        
    }

    public void setMosaicId(UInt64DTO mosaicId) {
        this.mosaicId = mosaicId;
        
    }

   
  public MosaicLevyInfoDTO fee(UInt64DTO fee) {
        this.fee = fee;
        return this;
    }
    /**
     * Get fee
     * 
     * @return fee
     **/
    @ApiModelProperty(required = true, value = "")
    public UInt64DTO getFee() {
        return fee;
    }
    public void setFee(UInt64DTO fee) {
        this.fee = fee;
    }

  

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
        return true;
        }
        if (o == null || getClass() != o.getClass()) {
        return false;
        }
        MosaicLevyInfoDTO mosaicLevyInfoDTO = (MosaicLevyInfoDTO) o;
        return Objects.equals(this.type, mosaicLevyInfoDTO.type) &&
            Objects.equals(this.recipient, mosaicLevyInfoDTO.recipient) &&
            Objects.equals(this.mosaicId, mosaicLevyInfoDTO.mosaicId) &&
            Objects.equals(this.fee, mosaicLevyInfoDTO.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, recipient, mosaicId, fee);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MosaicLevyInfoDTO {\n");
        sb.append("type: ").append(toIndentedString(type)).append("\n");
        sb.append("recipient: ").append(toIndentedString(recipient)).append("\n");
        sb.append("mosaicId: ").append(toIndentedString(mosaicId)).append("\n");
        sb.append("fee: ").append(toIndentedString(fee)).append("\n");
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