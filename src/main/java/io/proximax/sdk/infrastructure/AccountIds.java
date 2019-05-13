/*
 * Copyright 2018 NEM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * NIS2 API
 * This document defines all the nis2 api routes and behaviour
 *
 * OpenAPI spec version: 1.0.0
 * Contact: guillemchain@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.proximax.sdk.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

/**
 * AccountIds
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-12-19T19:07:40.115Z")
class AccountIds {
    @SerializedName("accountIds")
    private List<String> accountIds = null;

    public AccountIds accountIds(List<String> accountIds) {
        this.accountIds = accountIds;
        return this;
    }

    public AccountIds addAccountIdsItem(String accountIdsItem) {
        if (this.accountIds == null) {
            this.accountIds = new ArrayList<String>();
        }
        this.accountIds.add(accountIdsItem);
        return this;
    }

    /**
     * Get accountIds
     *
     * @return accountIds
     **/
    @ApiModelProperty(example = "[\"B4F12E7C9F6946091E2CB8B6D3A12B50D17CCBBF646386EA27CE2946A7423DCF\",\"EB6839C7E6BD0031FDD5F8CB5137E9BC950D7EE7756C46B767E68F3F58C24390\"]", value = "")
    public List<String> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(List<String> accountIds) {
        this.accountIds = accountIds;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountIds accountIds = (AccountIds) o;
        return Objects.equals(this.accountIds, accountIds.accountIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountIds);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AccountIds {\n");

        sb.append("    accountIds: ").append(toIndentedString(accountIds)).append("\n");
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

