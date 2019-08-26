/*
 * Copyright 2019 ProximaX Limited. All rights reserved.
 * Use of this source code is governed by the Apache 2.0
 * license that can be found in the LICENSE file.
 */
package io.proximax.sdk.model.transaction.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.proximax.sdk.model.transaction.AggregateTransaction;
import io.proximax.sdk.model.transaction.AggregateTransactionCosignature;
import io.proximax.sdk.model.transaction.Transaction;
import io.proximax.sdk.model.transaction.TransactionType;
import io.proximax.sdk.model.transaction.TransactionVersion;

/**
 * builder for {@link AggregateTransaction}
 */
public class AggregateTransactionBuilder extends TransactionBuilder<AggregateTransactionBuilder, AggregateTransaction> {

   private List<Transaction> innerTransactions;
   private List<AggregateTransactionCosignature> cosignatures;

   private AggregateTransactionBuilder(TransactionType type, Integer version) {
      super(type, version);
      // provide empty lists as defaults
      this.innerTransactions = new ArrayList<>();
      this.cosignatures = new ArrayList<>();
   }

   public static AggregateTransactionBuilder createBonded() {
      return new AggregateTransactionBuilder(TransactionType.AGGREGATE_BONDED,
            TransactionVersion.AGGREGATE_BONDED.getValue());
   }

   public static AggregateTransactionBuilder createComplete() {
      return new AggregateTransactionBuilder(TransactionType.AGGREGATE_COMPLETE,
            TransactionVersion.AGGREGATE_COMPLETE.getValue());
   }

   @Override
   protected AggregateTransactionBuilder self() {
      return this;
   }

   @Override
   public AggregateTransaction build() {
      return new AggregateTransaction(getType(), getNetworkType(), getVersion(), getDeadline(), getMaxFee(),
            getSignature(), getSigner(), getTransactionInfo(), getFeeCalculationStrategy(), innerTransactions,
            cosignatures);
   }

   // ------------------------------------- setters ---------------------------------------------//

   /**
    * set list of inner transactions
    * 
    * @param innerTransactions inner transactions
    * @return the builder
    */
   public AggregateTransactionBuilder innerTransactions(List<Transaction> innerTransactions) {
      this.innerTransactions = innerTransactions;
      return self();
   }

   /**
    * set list of available cosignatures
    * 
    * @param cosignatures list of cosignatures
    * @return the builder
    */
   public AggregateTransactionBuilder cosignatures(List<AggregateTransactionCosignature> cosignatures) {
      this.cosignatures = cosignatures;
      return self();
   }

   // -------------------------------------- getters --------------------------------------------//
   
   /**
    * @return the innerTransactions
    */
   public List<Transaction> getInnerTransactions() {
      return innerTransactions;
   }

   /**
    * @return the cosignatures
    */
   public List<AggregateTransactionCosignature> getCosignatures() {
      return cosignatures;
   }
   
   // -------------------------------------- convenience --------------------------------------------//
   
   /**
    * set list of inner transactions
    * 
    * @param innerTransactions inner transactions
    * @return the builder
    */
   public AggregateTransactionBuilder innerTransactions(Transaction ... innerTransactions) {
      this.innerTransactions = new ArrayList<>();
      this.innerTransactions.addAll(Arrays.asList(innerTransactions));
      return self();
   }

   /**
    * add specified transactions as new inner transactions
    * 
    * @param innerTransactions inner transactions to add
    * @return the builder
    */
   public AggregateTransactionBuilder addInnerTransactions(Transaction ... innerTransactions) {
      this.innerTransactions.addAll(Arrays.asList(innerTransactions));
      return self();
   }

   
}
