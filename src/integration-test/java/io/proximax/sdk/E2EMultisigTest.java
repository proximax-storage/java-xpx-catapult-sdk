/*
 * Copyright 2019 ProximaX
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
package io.proximax.sdk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proximax.core.crypto.KeyPair;
import io.proximax.sdk.model.account.Account;
import io.proximax.sdk.model.account.Address;
import io.proximax.sdk.model.account.MultisigAccountGraphInfo;
import io.proximax.sdk.model.account.MultisigAccountInfo;
import io.proximax.sdk.model.mosaic.NetworkCurrencyMosaic;
import io.proximax.sdk.model.transaction.*;
import io.reactivex.functions.Consumer;

/**
 * Multisig integration tests
 * 
 * @author tonowie
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class E2EMultisigTest extends E2EBaseTest {
   /** slf4j logger */
   private static final Logger logger = LoggerFactory.getLogger(E2EMultisigTest.class);

   private final Account multiMultisigAccount = new Account(new KeyPair(), NETWORK_TYPE);
   private final Account multisigAccount = new Account(new KeyPair(), NETWORK_TYPE);
   private final Account cosig1 = new Account(new KeyPair(), NETWORK_TYPE);
   private final Account cosig2 = new Account(new KeyPair(), NETWORK_TYPE);
   private final Account cosig3 = new Account(new KeyPair(), NETWORK_TYPE);

   @AfterAll
   void returnFunds() {
      // send back 10XPX to the seed account from cosig1
      sendSomeCash(cosig1, seedAccount.getAddress(), 10);
   }

   @BeforeAll
   void prepareForTest() {
      signup(multiMultisigAccount.getAddress());
      signup(multisigAccount.getAddress());
      signup(cosig1.getAddress());
      signup(cosig2.getAddress());
   }

   /**
    * subscribe to all channels for the address
    * 
    * @param addr
    */
   private void signup(Address addr) {
      // output nothing by default
      Consumer<? super Object> logme = (obj) -> logger.trace("listener fired: {}", obj);
      disposables.add(listener.status(addr).subscribe(logme, logme));
      disposables.add(listener.unconfirmedAdded(addr).subscribe(logme, logme));
      disposables.add(listener.unconfirmedRemoved(addr).subscribe(logme, logme));
      disposables.add(listener.aggregateBondedAdded(addr).subscribe(logme, logme));
      disposables.add(listener.aggregateBondedRemoved(addr).subscribe(logme, logme));
      disposables.add(listener.cosignatureAdded(addr).subscribe(logme, logme));
      disposables.add(listener.confirmed(addr).subscribe(logme, logme));
   }

   @Test
   void test00GetFunds() {
      logger.info("multisig: {}", multisigAccount);
      logger.info("cosig1: {}", cosig1);
      logger.info("cosig2: {}", cosig2);
      logger.info("cosig3: {}", cosig3);
      // send 1XPX to the to-be-multisig account so we can test transfers later on
      sendSomeCash(seedAccount, multisigAccount.getAddress(), 1);
      // give cosig1 10XPX so he can lock funds for aggregate transactions
      sendSomeCash(seedAccount, cosig1.getAddress(), 10);
   }

   @Test
   void test01CreateMultisig() throws InterruptedException, ExecutionException {
      // change the account to multisig with 1 of2 cosignatories
      ModifyMultisigAccountTransaction changeToMultisig = ModifyMultisigAccountTransaction.create(getDeadline(),
            1,
            1,
            Arrays.asList(
                  new MultisigCosignatoryModification(MultisigCosignatoryModificationType.ADD,
                        cosig1.getPublicAccount()),
                  new MultisigCosignatoryModification(MultisigCosignatoryModificationType.ADD,
                        cosig2.getPublicAccount())),
            NETWORK_TYPE);
      SignedTransaction signedChangeToMultisig = multisigAccount.sign(changeToMultisig);
      // announce the transaction
      logger.info("Sent request: {}", transactionHttp.announce(signedChangeToMultisig).toFuture().get());
      // verify that account is multisig
      logger.info("request o create multisig confirmed: {}",
            listener.confirmed(multisigAccount.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
      testMultisigAccount(multisigAccount, true, 1, 1, 2, 2);
   }

   @Test
   void test01TransferFromMultisig1Of2Aggregate() {
      // prepare transfer to the seed account
      TransferTransaction transfer = TransferTransaction.create(getDeadline(),
            seedAccount.getAddress(),
            Collections.singletonList(NetworkCurrencyMosaic.createAbsolute(BigInteger.valueOf(1))),
            PlainMessage.Empty,
            NETWORK_TYPE);
      // add the modification to the aggregate transaction
      AggregateTransaction aggregateTransaction = AggregateTransaction.createComplete(getDeadline(),
            Arrays.asList(transfer.toAggregate(multisigAccount.getPublicAccount())),
            NETWORK_TYPE);
      // sign the aggregate transaction
      SignedTransaction signedTransaction = cosig1.sign(aggregateTransaction);
      // announce the transfer
      logger.info("Announced the aggregate complete transfer from multisig: {}",
            transactionHttp.announce(signedTransaction).blockingFirst());
      logger.info("request to transfer confirmed: {}", listener.confirmed(cosig1.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
   }

   @Test
   void test01TransferFromMultisig1Of2AggregateBonded() {
      // prepare transfer to the seed account
      TransferTransaction transfer = TransferTransaction.create(getDeadline(),
            seedAccount.getAddress(),
            Collections.singletonList(NetworkCurrencyMosaic.createAbsolute(BigInteger.valueOf(1))),
            PlainMessage.Empty,
            NETWORK_TYPE);
      // add the modification to the aggregate transaction. has to be bonded because we are going to test the lock
      AggregateTransaction aggregateTransaction = AggregateTransaction.createBonded(getDeadline(),
            Arrays.asList(transfer.toAggregate(multisigAccount.getPublicAccount())),
            NETWORK_TYPE);
      // sign the aggregate bonded transaction
      SignedTransaction signedTransaction = cosig1.sign(aggregateTransaction);
      // lock 10 of XPX (required to prevent spamming)
      LockFundsTransaction lockFundsTransaction = LockFundsTransaction.create(getDeadline(),
            NetworkCurrencyMosaic.createRelative(BigInteger.valueOf(10)),
            BigInteger.valueOf(480),
            signedTransaction,
            NETWORK_TYPE);
      logger.info("locking account {}", lockFundsTransaction);
      // sign the fund lock
      SignedTransaction lockFundsTransactionSigned = cosig1.sign(lockFundsTransaction);
      // announce the lock transaction
      logger.info("Sent request to lock funds before transfer: {}",
            transactionHttp.announce(lockFundsTransactionSigned).blockingFirst());
      logger.info("request to transfer aggregate bonded confirmed: {}",
            listener.confirmed(cosig1.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
      // announce the multisig change as aggregate bounded
      logger.info("Announced the aggregate bonded transfer from multisig: {}",
            transactionHttp.announceAggregateBonded(signedTransaction).blockingFirst());
      logger.info("request to make transfer confirmed: {}", listener.confirmed(cosig1.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
   }

   @Test
   void test02IncreaseMinApproval() throws InterruptedException, ExecutionException {
      logger.info("Going to make the account 2 of 2");
      // modify account to be 2 of 2
      ModifyMultisigAccountTransaction changeTo2of2 = ModifyMultisigAccountTransaction.create(getDeadline(),
            1, // add one to the minApproval to change it to 2
            0,
            Arrays.asList(),
            NETWORK_TYPE);
      AggregateTransaction aggregateTransaction = AggregateTransaction.createComplete(getDeadline(),
            Arrays.asList(changeTo2of2.toAggregate(multisigAccount.getPublicAccount())),
            NETWORK_TYPE);
      SignedTransaction signedChangeTo2of2 = cosig1.sign(aggregateTransaction);
      logger.info("Sent request: {}", transactionHttp.announce(signedChangeTo2of2).toFuture().get());
      // verify that min approvals is set to 2
      logger.info("request to increase min approval confirmed: {}",
            listener.confirmed(cosig1.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
      testMultisigAccount(multisigAccount, true, 2, 1, 2, 2);
   }

   @Test
   void test02TransferFromMultisig2Of2Aggregate() {
      // prepare transfer to the seed account
      TransferTransaction transfer = TransferTransaction.create(getDeadline(),
            seedAccount.getAddress(),
            Collections.singletonList(NetworkCurrencyMosaic.createAbsolute(BigInteger.valueOf(1))),
            PlainMessage.Empty,
            NETWORK_TYPE);
      // add the modification to the aggregate transaction
      AggregateTransaction aggregateTransaction = AggregateTransaction.createComplete(getDeadline(),
            Arrays.asList(transfer.toAggregate(multisigAccount.getPublicAccount())),
            NETWORK_TYPE);
      // sign the aggregate transaction
      SignedTransaction signedTransaction = cosig1.signTransactionWithCosignatories(aggregateTransaction,
            Arrays.asList(cosig2));
      // announce the transfer
      logger.info("Announced the transfer from multisig: {}",
            transactionHttp.announce(signedTransaction).blockingFirst());
      logger.info("request confirmed: {}", listener.confirmed(cosig1.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
   }

   @Test
   void test02TransferFromMultisig2Of2AggregateBonded() {
      // prepare transfer to the seed account
      TransferTransaction transfer = TransferTransaction.create(getDeadline(),
            seedAccount.getAddress(),
            Collections.singletonList(NetworkCurrencyMosaic.createAbsolute(BigInteger.valueOf(1))),
            PlainMessage.Empty,
            NETWORK_TYPE);
      // add the modification to the aggregate transaction. has to be bonded because we are going to test the lock
      AggregateTransaction aggregateTransaction = AggregateTransaction.createBonded(getDeadline(),
            Arrays.asList(transfer.toAggregate(multisigAccount.getPublicAccount())),
            NETWORK_TYPE);
      // sign the aggregate bonded transaction
      SignedTransaction signedTransaction = cosig1.signTransactionWithCosignatories(aggregateTransaction,
            Arrays.asList(cosig2));
      // lock 10 of XPX (required to prevent spamming)
      LockFundsTransaction lockFundsTransaction = LockFundsTransaction.create(getDeadline(),
            NetworkCurrencyMosaic.createRelative(BigInteger.valueOf(10)),
            BigInteger.valueOf(480),
            signedTransaction,
            NETWORK_TYPE);
      logger.info("locking account {}", lockFundsTransaction);
      // sign the fund lock
      SignedTransaction lockFundsTransactionSigned = cosig1.sign(lockFundsTransaction);
      // announce the lock transaction
      logger.info("Sent request to lock funds: {}",
            transactionHttp.announce(lockFundsTransactionSigned).blockingFirst());
      logger.info("request confirmed: {}", listener.confirmed(cosig1.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
      // announce the multisig change as aggregate bounded
      logger.info("Announced the transfer from multisig: {}",
            transactionHttp.announceAggregateBonded(signedTransaction).blockingFirst());
      logger.info("request confirmed: {}", listener.confirmed(cosig1.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
   }

   @Test
   void test02TransferFromMultisig2Of2AggregateBondedSeparateConfirmation() {
      // prepare transfer to the seed account
      TransferTransaction transfer = TransferTransaction.create(getDeadline(),
            seedAccount.getAddress(),
            Collections.singletonList(NetworkCurrencyMosaic.createAbsolute(BigInteger.valueOf(1))),
            PlainMessage.Empty,
            NETWORK_TYPE);
      // add the modification to the aggregate transaction. has to be bonded because we are going to test the lock
      AggregateTransaction aggregateTransaction = AggregateTransaction.createBonded(getDeadline(),
            Arrays.asList(transfer.toAggregate(multisigAccount.getPublicAccount())),
            NETWORK_TYPE);
      // sign the aggregate bonded transaction
      SignedTransaction signedTransaction = cosig1.sign(aggregateTransaction);
      // lock 10 of XPX (required to prevent spamming)
      LockFundsTransaction lockFundsTransaction = LockFundsTransaction.create(getDeadline(),
            NetworkCurrencyMosaic.createRelative(BigInteger.valueOf(10)),
            BigInteger.valueOf(480),
            signedTransaction,
            NETWORK_TYPE);
      logger.info("locking account {}", lockFundsTransaction);
      // sign the fund lock
      SignedTransaction lockFundsTransactionSigned = cosig1.sign(lockFundsTransaction);
      // announce the lock transaction
      logger.info("Sent request to lock funds: {}",
            transactionHttp.announce(lockFundsTransactionSigned).blockingFirst());
      logger.info("request confirmed: {}", listener.confirmed(cosig1.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
      // announce the multisig change as aggregate bounded
      logger.info("Announced the transfer from multisig: {}",
            transactionHttp.announceAggregateBonded(signedTransaction).blockingFirst());
      logger.info("request confirmed: {}", listener.aggregateBondedAdded(cosig1.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
      // cosign the request
      cosignMultisigTransaction();
   }

   @Test
   void test03AddCosignatory() throws InterruptedException, ExecutionException {
      logger.info("Going to add third cosignatory");
      // create multisig modification
      ModifyMultisigAccountTransaction addCosig3 = ModifyMultisigAccountTransaction.create(getDeadline(),
            0,
            0,
            Arrays.asList(new MultisigCosignatoryModification(MultisigCosignatoryModificationType.ADD,
                  cosig3.getPublicAccount())),
            NETWORK_TYPE);
      // add the modification to the aggregate bonded transaction
      AggregateTransaction aggregateTransaction = AggregateTransaction.createBonded(getDeadline(),
            Arrays.asList(addCosig3.toAggregate(multisigAccount.getPublicAccount())),
            NETWORK_TYPE);
      // sign the aggregate bonded transaction
      SignedTransaction signedTransaction = cosig1.sign(aggregateTransaction);
      // lock 10 of XPX (required to prevent spamming)
      LockFundsTransaction lockFundsTransaction = LockFundsTransaction.create(getDeadline(),
            NetworkCurrencyMosaic.createRelative(BigInteger.valueOf(10)),
            BigInteger.valueOf(480),
            signedTransaction,
            NETWORK_TYPE);
      // sign the fund lock
      SignedTransaction lockFundsTransactionSigned = cosig1.sign(lockFundsTransaction);
      // announce the transaction
      logger.info("Sent request to lock funds: {}",
            transactionHttp.announce(lockFundsTransactionSigned).toFuture().get());
      logger.info("request confirmed: {}", listener.confirmed(cosig1.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
      // announce the multisig change as aggregate bounded
      logger.info("Announced the multisig change: {}",
            transactionHttp.announceAggregateBonded(signedTransaction).toFuture().get());
      logger.info("request confirmed: {}", listener.aggregateBondedAdded(cosig1.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
      // change should not be in effect yet
      testMultisigAccount(multisigAccount, true, 2, 1, 2, 2);
   }

   @Test
   void test04CosignNewCosignatory() {
      logger.info("Going to cosign the addition of cosignatory");
      cosignMultisigTransaction();
      testMultisigAccount(multisigAccount, true, 2, 1, 3, 2);
   }

   @Test
   void test05CreateMultilevelMultisig() throws InterruptedException, ExecutionException {
      // change the account to multisig with 1 of2 cosignatories
      ModifyMultisigAccountTransaction changeToMultisig = ModifyMultisigAccountTransaction.create(getDeadline(),
            1,
            1,
            Arrays.asList(
                  new MultisigCosignatoryModification(MultisigCosignatoryModificationType.ADD,
                        cosig1.getPublicAccount()),
                  new MultisigCosignatoryModification(MultisigCosignatoryModificationType.ADD,
                        multisigAccount.getPublicAccount())),
            NETWORK_TYPE);
      SignedTransaction signedChangeToMultisig = multiMultisigAccount.sign(changeToMultisig);
      // announce the transaction
      logger.info("Sent request: {}", transactionHttp.announce(signedChangeToMultisig).toFuture().get());
      // verify that account is multisig
      logger.info("request o create multilevel multisig confirmed: {}",
            listener.confirmed(multiMultisigAccount.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
      testMultisigAccount(multiMultisigAccount, true, 1, 1, 2, 3);
   }
   
   /**
    * cosign all aggregate bonded transactions pending on the multisig account
    */
   private void cosignMultisigTransaction() {
      long count = accountHttp.aggregateBondedTransactions(multisigAccount.getPublicAccount()).flatMapIterable(tx -> tx)
            .filter(tx -> !tx.signedByAccount(cosig2.getPublicAccount())).map(tx -> {
               logger.info("Going to co-sign {}", tx);
               final CosignatureTransaction cosignatureTransaction = CosignatureTransaction.create(tx);
               final CosignatureSignedTransaction cosignatureSignedTransaction = cosig2
                     .signCosignatureTransaction(cosignatureTransaction);
               return transactionHttp.announceAggregateBondedCosignature(cosignatureSignedTransaction).blockingFirst();
            }).count().blockingGet();
      // make sure that we co-signed exactly one transaction
      assertEquals(1, count);
      logger.info("cosingned transactions: {}", listener.confirmed(cosig2.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
   }

   /**
    * test whether account has required attributes
    * 
    * @param isMultisig account is multisignature
    * @param minApprovals desired minimum approvals
    * @param minRemoval desired minimum number of signatures for removal
    * @param cosignatories desired number of cosignatories
    * @throws InterruptedException
    * @throws ExecutionException
    */
   private void testMultisigAccount(Account multisig, boolean isMultisig, int minApprovals, int minRemoval, int cosignatories, int levels) {
      logger.info("Checking status of multisig account");
      MultisigAccountInfo mai = accountHttp.getMultisigAccountInfo(multisig.getAddress()).blockingFirst();
      assertEquals(isMultisig, mai.isMultisig());
      if (isMultisig) {
         assertEquals(minApprovals, mai.getMinApproval());
         assertEquals(minRemoval, mai.getMinRemoval());
         assertEquals(cosignatories, mai.getCosignatories().size());
         // inspect the graph
         MultisigAccountGraphInfo graphInfo = accountHttp.getMultisigAccountGraphInfo(multisig.getAddress())
               .blockingFirst();
         // number of cosig levels + one for the base account
         assertEquals(levels, graphInfo.getLevelsNumber().size(), "level number");
      }
   }
}