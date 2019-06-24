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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import io.proximax.core.crypto.KeyPair;
import io.proximax.sdk.ResourceBasedTest;
import io.proximax.sdk.model.account.Address;
import io.proximax.sdk.model.account.PublicAccount;
import io.proximax.sdk.model.blockchain.NetworkType;
import io.proximax.sdk.model.mosaic.NetworkCurrencyMosaic;

public class SecretLockTransactionTest extends ResourceBasedTest {

   @Test
   void constructor() {
      String secret = "3fc8ba10229ab5778d05d9c4b7f56676a88bf9295c185acfc0f961db5408cafe";
      SecretLockTransaction tx = new SecretLockTransaction(NetworkType.MIJIN, 23, new FakeDeadline(), BigInteger.ONE, NetworkCurrencyMosaic.createRelative(BigInteger.valueOf(10)),
            BigInteger.valueOf(100),
            HashType.SHA3_256,
            secret,
            Address.createFromRawAddress("SDUP5PLHDXKBX3UU5Q52LAY4WYEKGEWC6IB3VBFM"),"signaturestring", new PublicAccount(new KeyPair().getPublicKey().getHexString(), NetworkType.MIJIN),
            TransactionInfo.create(BigInteger.ONE, "infohash", "merklehash"));
      // make assertions
      assertEquals(NetworkCurrencyMosaic.createRelative(BigInteger.valueOf(10)), tx.getMosaic());
      assertEquals(BigInteger.valueOf(100), tx.getDuration());
      assertEquals(HashType.SHA3_256, tx.getHashType());
      assertEquals(secret, tx.getSecret());
      assertEquals(Address.createFromRawAddress("SDUP5PLHDXKBX3UU5Q52LAY4WYEKGEWC6IB3VBFM"), tx.getRecipient());
   }

   @Test
   void serialization() throws IOException {
      String secret = "3fc8ba10229ab5778d05d9c4b7f56676a88bf9295c185acfc0f961db5408cafe";
      SecretLockTransaction secretLocktx = SecretLockTransaction.create(new FakeDeadline(),
            NetworkCurrencyMosaic.createRelative(BigInteger.valueOf(10)),
            BigInteger.valueOf(100),
            HashType.SHA3_256,
            secret,
            Address.createFromRawAddress("SDUP5PLHDXKBX3UU5Q52LAY4WYEKGEWC6IB3VBFM"),
            NetworkType.MIJIN_TEST);
      byte[] actual = secretLocktx.generateBytes();
      assertArrayEquals(loadBytes("secret_lock"), actual);
   }

   @Test
   void shouldThrowErrorWhenSecretIsNotValid() {
      assertThrows(IllegalArgumentException.class, () -> {
         SecretLockTransaction secretLocktx = SecretLockTransaction.create(new FakeDeadline(),
               NetworkCurrencyMosaic.createRelative(BigInteger.valueOf(10)),
               BigInteger.valueOf(100),
               HashType.SHA3_256,
               "non valid hash",
               Address.createFromRawAddress("SDUP5PLHDXKBX3UU5Q52LAY4WYEKGEWC6IB3VBFM"),
               NetworkType.MIJIN_TEST);
      }, "not a valid secret");
   }
}
