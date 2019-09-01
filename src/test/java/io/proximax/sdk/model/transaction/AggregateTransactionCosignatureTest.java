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

import io.proximax.sdk.model.account.PublicAccount;
import io.proximax.sdk.model.blockchain.NetworkType;

public class AggregateTransactionCosignatureTest {

   @Test
   void createAnAggregateCosignatureViaConstructor() {
      AggregateTransactionCosignature aggregateTransactionCosignature = new AggregateTransactionCosignature("signature",
            new PublicAccount("9A49366406ACA952B88BADF5F1E9BE6CE4968141035A60BE503273EA65456B24",
                  NetworkType.MIJIN_TEST));

      assertEquals("signature", aggregateTransactionCosignature.getSignature());
      assertEquals(
            new PublicAccount("9A49366406ACA952B88BADF5F1E9BE6CE4968141035A60BE503273EA65456B24",
                  NetworkType.MIJIN_TEST),
            aggregateTransactionCosignature.getSigner());
   }
}
