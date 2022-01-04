/*
 * Copyright 2019 ProximaX Limited. All rights reserved.
 * Use of this source code is governed by the Apache 2.0
 * license that can be found in the LICENSE file.
 */
package io.proximax.sdk.model.transaction.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.proximax.sdk.FeeCalculationStrategy;
import io.proximax.sdk.model.account.props.AccountPropertyModification;
import io.proximax.sdk.model.account.props.AccountPropertyType;
import io.proximax.sdk.model.network.NetworkType;
import io.proximax.sdk.model.transaction.EntityType;
import io.proximax.sdk.model.transaction.ModifyAccountPropertyTransaction;

/**
 * {@link ModifyAccountPropertyEntityTransactionBuilder} tests
 */
class ModifyAccountPropertyEntityTransactionBuilderTest {

   private static final NetworkType NETWORK_TYPE = NetworkType.TEST_NET;

   private ModifyAccountPropertyEntityTransactionBuilder builder;

   @BeforeEach
   void setUp() {
      builder = new ModifyAccountPropertyEntityTransactionBuilder();
      builder.networkType(NETWORK_TYPE);
      builder.deadlineDuration(BigInteger.valueOf(60_000));
      builder.feeCalculationStrategy(FeeCalculationStrategy.MEDIUM);
   }
   
   @Test
   void checkBuilder() {
      assertEquals(EntityType.ACCOUNT_PROPERTIES_ENTITY_TYPE, builder.getType());
   }
   
   @Test
   void test() {
      AccountPropertyModification<EntityType> mod = AccountPropertyModification.add(EntityType.MODIFY_CONTRACT);
      ModifyAccountPropertyTransaction<EntityType> trans = builder.propertyType(AccountPropertyType.ALLOW_TRANSACTION).modifications(Arrays.asList(mod)).build();
      
      assertEquals(AccountPropertyType.ALLOW_TRANSACTION, trans.getPropertyType());
      assertEquals(1, trans.getPropertyModifications().size());
      assertEquals(mod, trans.getPropertyModifications().get(0));
   }

   @Test
   void testAllowed() {
      AccountPropertyModification<EntityType> mod = AccountPropertyModification.add(EntityType.MODIFY_CONTRACT);
      ModifyAccountPropertyTransaction<EntityType> trans = builder.allowed(Arrays.asList(mod)).build();
      
      assertEquals(AccountPropertyType.ALLOW_TRANSACTION, trans.getPropertyType());
      assertEquals(1, trans.getPropertyModifications().size());
      assertEquals(mod, trans.getPropertyModifications().get(0));
   }

   @Test
   void testBlocked() {
      AccountPropertyModification<EntityType> mod = AccountPropertyModification.add(EntityType.MODIFY_CONTRACT);
      ModifyAccountPropertyTransaction<EntityType> trans = builder.blocked(Arrays.asList(mod)).build();
      
      assertEquals(AccountPropertyType.BLOCK_TRANSACTION, trans.getPropertyType());
      assertEquals(1, trans.getPropertyModifications().size());
      assertEquals(mod, trans.getPropertyModifications().get(0));
   }

}
