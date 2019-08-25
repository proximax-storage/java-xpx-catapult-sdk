/*
 * Copyright 2019 ProximaX Limited. All rights reserved.
 * Use of this source code is governed by the Apache 2.0
 * license that can be found in the LICENSE file.
 */
package io.proximax.sdk.model.transaction.builder;

import java.math.BigInteger;

import io.proximax.sdk.FeeCalculationStrategy;
import io.proximax.sdk.model.blockchain.NetworkType;

/**
 * Central factory to instantiate transaction builders for all transaction types
 */
public class TransactionBuilderFactory {
   private NetworkType networkType;
   private BigInteger deadlineMillis;
   private FeeCalculationStrategy feeCalculationStrategy;

   /**
    * @return the networkType
    */
   public NetworkType getNetworkType() {
      return networkType;
   }

   /**
    * @param networkType the networkType to set
    */
   public void setNetworkType(NetworkType networkType) {
      this.networkType = networkType;
   }

   /**
    * @return the deadline in milliseconds
    */
   public BigInteger getDeadlineMillis() {
      return deadlineMillis;
   }

   /**
    * set default builder transaction deadline specified as milliseconds elapsed from the time when
    * {@link TransactionBuilder#build()} is invoked
    * 
    * @param deadlineMillis the deadlineMillis to set
    */
   public void setDeadlineMillis(BigInteger deadlineMillis) {
      this.deadlineMillis = deadlineMillis;
   }

   /**
    * @return the default fee calculation strategy
    */
   public FeeCalculationStrategy getFeeCalculationStrategy() {
      return feeCalculationStrategy;
   }

   /**
    * @param feeCalculationStrategy the feeCalculationStrategy to set
    */
   public void setFeeCalculationStrategy(FeeCalculationStrategy feeCalculationStrategy) {
      this.feeCalculationStrategy = feeCalculationStrategy;
   }

   /**
    * initialize default values for the builder
    * 
    * @param builder
    */
   protected void initDefaults(TransactionBuilder<?, ?> builder) {
      builder.networkType(networkType);
      if (feeCalculationStrategy != null) {
         builder.feeCalculationStrategy(feeCalculationStrategy);
      }
      if (deadlineMillis != null) {
         builder.deadlineDuration(deadlineMillis);
      }
   }

   // ------------------------------ retrieve specific transaction builders ------------------------------- //
   /**
    * get builder for transfer transaction
    * 
    * @return the transfer transaction builder
    */
   public TransferTransactionBuilder transfer() {
      TransferTransactionBuilder builder = new TransferTransactionBuilder();
      initDefaults(builder);
      return builder;
   }
   
   /**
    * get builder for account link transaction
    * 
    * @return the account link transaction builder
    */
   public AccountLinkTransactionBuilder accountLink() {
      AccountLinkTransactionBuilder builder = new AccountLinkTransactionBuilder();
      initDefaults(builder);
      return builder;
   }
}