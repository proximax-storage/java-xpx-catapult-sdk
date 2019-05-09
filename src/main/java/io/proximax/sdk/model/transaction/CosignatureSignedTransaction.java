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

package io.proximax.sdk.model.transaction;

/**
 * The co-signature signed transaction.
 *
 * @since 1.0
 */
public class CosignatureSignedTransaction {
    private final String parentHash;
    private final String signature;
    private final String signer;

    public CosignatureSignedTransaction(String parentHash, String signature, String signer) {
        this.parentHash = parentHash;
        this.signature = signature;
        this.signer = signer;
    }

    /**
     * Returns hash of parent aggregate transaction that has been signed by a cosignatory of the transaction.
     *
     * @return String
     */
    public String getParentHash() {
        return parentHash;
    }

    /**
     * Returns signatures generated by signing the parent aggregate transaction hash.
     *
     * @return String
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Returns signer of the cosignature transaction.
     *
     * @return String
     */
    public String getSigner() {
        return signer;
    }
}