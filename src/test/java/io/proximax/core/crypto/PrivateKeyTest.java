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

package io.proximax.core.crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;

public class PrivateKeyTest {

    //region constructors / factories

    @Test
    public void canCreateFromBigInteger() {
        // Arrange:
        final PrivateKey key = new PrivateKey(new BigInteger("2275"));

        // Assert:
        MatcherAssert.assertThat(key.getRaw(), IsEqual.equalTo(new BigInteger("2275")));
    }

    @Test
    public void canCreateFromDecimalString() {
        // Arrange:
        final PrivateKey key = PrivateKey.fromDecimalString("2279");

        // Assert:
        MatcherAssert.assertThat(key.getRaw(), IsEqual.equalTo(new BigInteger("2279")));
    }

    @Test
    public void canCreateFromNegativeDecimalString() {
        // Arrange:
        final PrivateKey key = PrivateKey.fromDecimalString("-2279");

        // Assert:
        MatcherAssert.assertThat(key.getRaw(), IsEqual.equalTo(new BigInteger("-2279")));
    }

    @Test
    public void canCreateFromHexString() {
        // Arrange:
        final PrivateKey key = PrivateKey.fromHexString("227F");

        // Assert:
        MatcherAssert.assertThat(key.getRaw(), IsEqual.equalTo(new BigInteger("227F", 16)));
    }

    @Test
    public void canCreateFromOddLengthHexString() {
        // Arrange:
        final PrivateKey key = PrivateKey.fromHexString("ABC");

        // Assert:
        MatcherAssert.assertThat(key.getRaw(), IsEqual.equalTo(new BigInteger(new byte[]{(byte) 0x0A, (byte) 0xBC})));
    }

    @Test
    public void canCreateFromNegativeHexString() {
        // Arrange:
        final PrivateKey key = PrivateKey.fromHexString("8000");

        // Assert:
        MatcherAssert.assertThat(key.getRaw(), IsEqual.equalTo(new BigInteger(-1, new byte[]{(byte) 0x80, 0x00})));
    }

    @Test
    public void cannotCreateAroundMalformedDecimalString() {
        // Act:
        assertThrows(CryptoException.class, () -> PrivateKey.fromDecimalString("22A75"));
    }

    @Test
    public void cannotCreateAroundMalformedHexString() {
        // Act:
       assertThrows(CryptoException.class, () -> PrivateKey.fromHexString("22G75"));
    }

    //endregion

    //region serializer

    //endregion

    //region equals / hashCode

    @Test
    public void equalsOnlyReturnsTrueForEquivalentObjects() {
        // Arrange:
        final PrivateKey key = new PrivateKey(new BigInteger("2275"));

        // Assert:
        MatcherAssert.assertThat(PrivateKey.fromDecimalString("2275"), IsEqual.equalTo(key));
        MatcherAssert.assertThat(PrivateKey.fromDecimalString("2276"), IsNot.not(IsEqual.equalTo(key)));
        MatcherAssert.assertThat(PrivateKey.fromHexString("2276"), IsNot.not(IsEqual.equalTo(key)));
        MatcherAssert.assertThat(null, IsNot.not(IsEqual.equalTo(key)));
        MatcherAssert.assertThat(new BigInteger("1235"), IsNot.not(IsEqual.equalTo((Object) key)));
    }

    @Test
    public void hashCodesAreEqualForEquivalentObjects() {
        // Arrange:
        final PrivateKey key = new PrivateKey(new BigInteger("2275"));
        final int hashCode = key.hashCode();

        // Assert:
        MatcherAssert.assertThat(PrivateKey.fromDecimalString("2275").hashCode(), IsEqual.equalTo(hashCode));
        MatcherAssert.assertThat(PrivateKey.fromDecimalString("2276").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
        MatcherAssert.assertThat(PrivateKey.fromHexString("2275").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
    }

    //endregion

    //region toString

    @Test
    public void toStringReturnsHexRepresentation() {
       // private key is 32 bytes => 64 hexadecimal characters
       assertEquals("0000000000000000000000000000000000000000000000000000000000002275", PrivateKey.fromHexString("2275").toString());
       assertEquals("00000000000000000000000000000000000000000000000000000000000008e3", PrivateKey.fromDecimalString("2275").toString());
    }

    //endregion
}
