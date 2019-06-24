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

import java.math.BigInteger;
import java.util.Objects;

import io.proximax.sdk.model.transaction.UInt64Id;

/**
 * A mosaic describes an instance of a mosaic definition. Mosaics can be transferred by means of a transfer transaction.
 *
 * @since 1.0
 */
public class Mosaic {
   private final UInt64Id id;
   private final BigInteger amount;

   public Mosaic(UInt64Id id, BigInteger amount) {
      this.id = id;
      this.amount = amount;
   }

   /**
    * Returns the mosaic identifier
    *
    * @return mosaic identifier
    */
   public UInt64Id getId() {
      return id;
   }

   /**
    * Return mosaic amount. The quantity is always given in smallest units for the mosaic i.e. if it has a divisibility
    * of 3 the quantity is given in millis.
    *
    * @return amount of mosaic
    */
   public BigInteger getAmount() {
      return amount;
   }

   /**
    * Returns mosaic id as a hexadecimal string
    *
    * @return id hex string
    */
   public String getIdAsHex() {
      return id.getIdAsHex();
   }

   
   @Override
   public String toString() {
      return "Mosaic [id=" + id + ", amount=" + amount + "]";
   }

   @Override
   public int hashCode() {
      return Objects.hash(amount, id);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Mosaic other = (Mosaic) obj;
      return Objects.equals(amount, other.amount) && Objects.equals(id, other.id);
   }
}
