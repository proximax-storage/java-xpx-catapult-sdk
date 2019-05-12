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

package io.proximax.core.crypto.ed25519;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;

import io.proximax.core.crypto.BlockCipher;
import io.proximax.core.crypto.BlockCipherTest;
import io.proximax.core.crypto.CryptoEngine;
import io.proximax.core.crypto.CryptoEngines;
import io.proximax.core.crypto.KeyPair;

public class Ed25519BlockCipherTest extends BlockCipherTest {

    @Test
    public void decryptReturnsNullIfInputIsTooSmallInLength() {
        // Arrange:
        final CryptoEngine engine = this.getCryptoEngine();
        final KeyPair kp = KeyPair.random(engine);
        final BlockCipher blockCipher = this.getBlockCipher(kp, kp);

        // Act:
        final byte[] decryptedBytes = blockCipher.decrypt(new byte[63]);

        // Assert:
        MatcherAssert.assertThat(decryptedBytes, IsNull.nullValue());
    }

    @Override
    protected BlockCipher getBlockCipher(final KeyPair senderKeyPair, final KeyPair recipientKeyPair) {
        return new Ed25519BlockCipher(senderKeyPair, recipientKeyPair);
    }

    @Override
    protected CryptoEngine getCryptoEngine() {
        return CryptoEngines.ed25519Engine();
    }
}
