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

package io.proximax.sdk.model.namespace;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

import io.proximax.sdk.model.transaction.IdGenerator;
import io.proximax.sdk.model.transaction.UInt64Id;
import io.proximax.sdk.utils.dto.UInt64Utils;

/**
 * The namespace id structure describes namespace id
 *
 * @since 1.0
 */
public class NamespaceId implements UInt64Id {
   private final BigInteger id;
   private final Optional<String> fullName;

   /**
    * Create NamespaceId from namespace string name (ex: nem or domain.subdom.subdome)
    *
    * @param namespaceName string representing domain levels
    */
   public NamespaceId(String namespaceName) {
      this.id = IdGenerator.generateNamespaceId(namespaceName);
      this.fullName = Optional.of(namespaceName);
   }
  
   /**
    * Create NamespaceId from biginteger id
    *
    * @param id numeric id
    */
   public NamespaceId(BigInteger id) {
      this.id = id;
      this.fullName = Optional.empty();
   }

   /**
    * Returns namespace biginteger id
    *
    * @return namespace biginteger id
    */
   public BigInteger getId() {
      return id;
   }

   /**
    * Returns optional namespace full name, with subnamespaces if it's the case.
    *
    * @return namespace full name
    */
   public Optional<String> getFullName() {
      return fullName;
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      NamespaceId other = (NamespaceId) obj;
      return Objects.equals(id, other.id);
   }

   @Override
   public String toString() {
      return "NamespaceId [id=" + id + ", fullName=" + fullName + ", hexaId=" + UInt64Utils.bigIntegerToHex(id) + "]";
   }

   @Override
   public long getIdAsLong() {
      return id.longValue();
   }

   @Override
   public String getIdAsHex() {
      return UInt64Utils.bigIntegerToHex(id);
   }
}
