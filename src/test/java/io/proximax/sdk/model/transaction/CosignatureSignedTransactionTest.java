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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CosignatureSignedTransactionTest {

    @Test
    void createACosignatureSignedTransactionViaConstructor() {
        CosignatureSignedTransaction cosignatureSignedTransaction = new CosignatureSignedTransaction("parentHash", "signature", "signer");

        assertEquals("parentHash", cosignatureSignedTransaction.getParentHash());
        assertEquals("signature", cosignatureSignedTransaction.getSignature());
        assertEquals("signer", cosignatureSignedTransaction.getSigner());
    }
}
