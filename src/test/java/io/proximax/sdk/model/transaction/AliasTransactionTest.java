/*
 * Copyright 2019 ProximaX Limited. All rights reserved.
 * Use of this source code is governed by the Apache 2.0
 * license that can be found in the LICENSE file.
 */
package io.proximax.sdk.model.transaction;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import io.proximax.sdk.ResourceBasedTest;
import io.proximax.sdk.model.account.Address;
import io.proximax.sdk.model.account.PublicAccount;
import io.proximax.sdk.model.alias.AliasAction;
import io.proximax.sdk.model.blockchain.NetworkType;
import io.proximax.sdk.model.mosaic.MosaicId;
import io.proximax.sdk.model.namespace.NamespaceId;

/**
 * {@link AliasTransaction} tests
 */
class AliasTransactionTest extends ResourceBasedTest {

   @Test
   void genericConstructor() {
      Deadline deadLine = new FakeDeadline();
      AliasTransaction trans = new AliasTransaction(TransactionType.ADDRESS_ALIAS, NetworkType.MIJIN, 63, deadLine,
            BigInteger.valueOf(765), Optional.empty(), Optional.of(new Address("MADDR",
            NetworkType.MIJIN)), new NamespaceId("something"), AliasAction.LINK,
            "sign", new PublicAccount("", NetworkType.MIJIN),
            TransactionInfo.create(BigInteger.ONE, "infohash", "merklehash"));

      // run assertions
      assertFalse(trans.getMosaicId().isPresent());
      assertEquals(new Address("MADDR", NetworkType.MIJIN), trans.getAddress().orElseThrow(AssertionError::new));
      assertEquals(new NamespaceId("something"), trans.getNamespaceId());
      assertEquals(AliasAction.LINK, trans.getAliasAction());
   }

   @Test
   void genericConstructorThrows() {
      assertThrows(IllegalArgumentException.class,
            () -> new AliasTransaction(TransactionType.ADDRESS_ALIAS, NetworkType.MIJIN, 63, new FakeDeadline(),
                  BigInteger.valueOf(765), Optional.empty(), Optional.empty(), new NamespaceId("something"),
                  AliasAction.LINK, "sign", new PublicAccount("", NetworkType.MIJIN),
                  TransactionInfo.create(BigInteger.ONE, "infohash", "merklehash")));
      assertThrows(IllegalArgumentException.class,
            () -> new AliasTransaction(TransactionType.ADDRESS_ALIAS, NetworkType.MIJIN, 63, new FakeDeadline(),
                  BigInteger.valueOf(765), Optional.of(new MosaicId(BigInteger.ONE)),
                  Optional.of(new Address("MADDR", NetworkType.MIJIN)), new NamespaceId("something"), AliasAction.LINK,
                  "sign", new PublicAccount("", NetworkType.MIJIN),
                  TransactionInfo.create(BigInteger.ONE, "infohash", "merklehash")));
   }

   @Test
   void addressConstructor() {
      AliasTransaction trans = AliasTransaction.create(new Address("MADDR",
            NetworkType.MIJIN), new NamespaceId("testest"), AliasAction.LINK, new FakeDeadline(), NetworkType.MIJIN);
      // run assertions
      assertFalse(trans.getMosaicId().isPresent());
      assertEquals(new Address("MADDR", NetworkType.MIJIN), trans.getAddress().orElseThrow(AssertionError::new));
      assertEquals(new NamespaceId("testest"), trans.getNamespaceId());
      assertEquals(AliasAction.LINK, trans.getAliasAction());
   }

   @Test
   void mosaicConstructor() {
      AliasTransaction trans = AliasTransaction.create(new MosaicId(
            BigInteger.ONE), new NamespaceId("testest"), AliasAction.UNLINK, new FakeDeadline(), NetworkType.MIJIN);
      assertEquals(new MosaicId(BigInteger.ONE), trans.getMosaicId().orElseThrow(AssertionError::new));
      // run assertions
      assertFalse(trans.getAddress().isPresent());
      assertEquals(new NamespaceId("testest"), trans.getNamespaceId());
      assertEquals(AliasAction.UNLINK, trans.getAliasAction());
   }

   @Test
   void addressSerialization() throws IOException {
      AliasTransaction trans = AliasTransaction.create(new Address("MADDR",
            NetworkType.MIJIN), new NamespaceId("testest"), AliasAction.LINK, new FakeDeadline(), NetworkType.MIJIN);
      // generated by saveBytes
      byte[] actual = trans.generateBytes();
//      saveBytes("link_address", actual);
      assertArrayEquals(loadBytes("link_address"), actual);
   }

   @Test
   void mosaicSerialization() throws IOException {
      AliasTransaction trans = AliasTransaction.create(new MosaicId(
            BigInteger.ONE), new NamespaceId("testest"), AliasAction.LINK, new FakeDeadline(), NetworkType.MIJIN);
      // generated by saveBytes
      byte[] actual = trans.generateBytes();
//      saveBytes("link_mosaic", actual);
      assertArrayEquals(loadBytes("link_mosaic"), actual);
   }

}
