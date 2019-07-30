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

package io.proximax.sdk.model.mosaic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

/**
 * {@link Mosaic} tests
 */
class MosaicTest {

    @Test
    void createANewMosaicViaConstructor() {
        MosaicId mosaicId = new MosaicId(new BigInteger("-3087871471161192663"));
        Mosaic mosaic = new Mosaic(mosaicId, BigInteger.valueOf(24));
        assertEquals(mosaicId, mosaic.getId());
        assertEquals(BigInteger.valueOf(24), mosaic.getAmount());
        assertEquals("d525ad41d95fcf29",  mosaic.getIdAsHex());
    }
    
    @Test
    void checkHashCode() {
       MosaicId mosaicId = new MosaicId(new BigInteger("-3087871471161192663"));
       Mosaic a1 = new Mosaic(mosaicId, BigInteger.valueOf(24));
       Mosaic a2 = new Mosaic(mosaicId, BigInteger.valueOf(24));
       Mosaic b = new Mosaic(mosaicId, BigInteger.valueOf(25));
       assertEquals(a1.hashCode(), a1.hashCode());
       assertEquals(a1.hashCode(), a2.hashCode());
       assertNotEquals(a1.hashCode(), b.hashCode());
    }
    
    @Test
    void checkEquals() {
       MosaicId mosaicId = new MosaicId(new BigInteger("-3087871471161192663"));
       Mosaic a1 = new Mosaic(mosaicId, BigInteger.valueOf(24));
       Mosaic a2 = new Mosaic(mosaicId, BigInteger.valueOf(24));
       Mosaic b = new Mosaic(mosaicId, BigInteger.valueOf(25));
       assertEquals(a1, a1);
       assertEquals(a1, a2);
       assertNotEquals(a1, b);
       assertNotEquals(a1, null);
       assertNotEquals(a1, "othertype");
    }
    
    @Test
    void checkToString() {
       MosaicId mosaicId = new MosaicId(new BigInteger("-3087871471161192663"));
       Mosaic a1 = new Mosaic(mosaicId, BigInteger.valueOf(24));

       assertTrue(a1.toString().startsWith("Mosaic "));
    }
}