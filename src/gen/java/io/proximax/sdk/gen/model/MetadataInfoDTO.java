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
 * MetadataInfoDTO
 */
@javax.annotation.processing.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2019-09-22T22:57:50.932+02:00[Europe/Prague]")
public class MetadataInfoDTO {
    public static final String SERIALIZED_NAME_METADATA_ENTRY = "metadataEntry";
    @SerializedName(SERIALIZED_NAME_METADATA_ENTRY)
    private MetadataEntryDTO metadataEntry = null;

    public static final String SERIALIZED_NAME_ID = "id";
    @SerializedName(SERIALIZED_NAME_ID)
    private String id = null;
    
    public MetadataInfoDTO metadataEntry(MetadataEntryDTO metadataEntry) {
        this.metadataEntry = metadataEntry;
        return this;
    }

    /**
     * Get metadataEntry
     * 
     * @return metadataEntry
     **/
    @ApiModelProperty(required = true, value = "")
    public MetadataEntryDTO getMetadataEntry() {
        return metadataEntry;
    }

    public void setMetadataEntry(MetadataEntryDTO metadataEntry) {
        this.metadataEntry = metadataEntry;
    }

    public MetadataInfoDTO mosmetaaic(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     * 
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
        MetadataInfoDTO MetadataInfoDTO = (MetadataInfoDTO) o;
        return Objects.equals(this.metadataEntry, 
                MetadataInfoDTO.metadataEntry) &&
                Objects.equals(this.id, MetadataInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadataEntry, id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MetadataInfoDTO {\n");
        sb.append("    metadataEntry: ").append(toIndentedString(metadataEntry)).append("\n");
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
