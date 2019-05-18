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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proximax.core.crypto.KeyPair;
import io.proximax.sdk.model.account.Account;
import io.proximax.sdk.model.account.Address;
import io.proximax.sdk.model.mosaic.Mosaic;
import io.proximax.sdk.model.mosaic.NetworkCurrencyMosaic;
import io.proximax.sdk.model.transaction.AggregateTransaction;
import io.proximax.sdk.model.transaction.Message;
import io.proximax.sdk.model.transaction.PlainMessage;
import io.proximax.sdk.model.transaction.SecureMessage;
import io.proximax.sdk.model.transaction.SignedTransaction;
import io.proximax.sdk.model.transaction.TransferTransaction;

/**
 * E2E tests that demonstrate transfers
 * 
 * @author tonowie
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class E2ETransferTest extends E2EBaseTest {
   /** logger */
   private static final Logger logger = LoggerFactory.getLogger(E2ETransferTest.class);

   private final Account simpleAccount = new Account(new KeyPair(), getNetworkType());

   @BeforeAll
   void addListener() {
      logger.info("Sending transactions to {}", simpleAccount);
      disposables.add(listener.status(simpleAccount.getAddress())
            .subscribe(err -> logger.error("Operation failed: {}", err), t -> logger.error("exception thrown", t)));
   }

   @AfterAll
   void closeDown() {
      // return the XPX
      SignedTransaction signedTransaction = signTransfer(simpleAccount,
            seedAccount.getAddress(),
            NetworkCurrencyMosaic.createAbsolute(BigInteger.valueOf(4)),
            new PlainMessage("money back guarantee"));
      logger.info("Returning funds. {}", transactionHttp.announce(signedTransaction).blockingFirst());
      logger.info("Returned funds. {}",
            listener.confirmed(simpleAccount.getAddress()).timeout(getTimeoutSeconds(), TimeUnit.SECONDS).blockingFirst());
      // check that target account has expected number of incoming transactions
      int transactions = accountHttp.incomingTransactions(simpleAccount.getPublicAccount()).blockingFirst().size();
      // TODO why 2? we did 4 transfers but 2 were aggregate?
      assertEquals(2, transactions);
   }

   @Test
   void sendPlaintextMessage() {
      // send transaction with plain message
      transfer(seedAccount,
            simpleAccount.getAddress(),
            NetworkCurrencyMosaic.createAbsolute(BigInteger.valueOf(1)),
            new PlainMessage("java SDK plain message test"));
   }

   @Test
   void sendSecureMessage() {
      // send transaction with encrypted message
      SecureMessage secureMessage = SecureMessage.create(seedAccount.getKeyPair().getPrivateKey(),
            simpleAccount.getKeyPair().getPublicKey(),
            "java SDK secure message");
      transfer(seedAccount, simpleAccount.getAddress(), NetworkCurrencyMosaic.createAbsolute(BigInteger.valueOf(1)), secureMessage);
   }

   /**
    * return transactions as specified by arguments signed by the signer account
    * 
    * @param signerAccount account used to sign the transaction
    * @param target target address for the transfer
    * @param amount mosaic to transfer
    * @param message message for the transfer
    * @return instance of signed transaction which can be then announced to the network
    */
   private SignedTransaction signTransfer(Account signerAccount, Address target, Mosaic amount, Message message) {
      TransferTransaction transaction = TransferTransaction
            .create(getDeadline(), target, Collections.singletonList(amount), message, getNetworkType());
      return signerAccount.sign(transaction);
   }

   /**
    * return transactions as specified by arguments signed by the signer account
    * 
    * @param signerAccount account used to sign the transaction
    * @param target target address for the transfer
    * @param amount mosaic to transfer
    * @param message message for the transfer
    * @return instance of signed transaction which can be then announced to the network
    */
   private SignedTransaction signAggregateTransfer(Account signerAccount, Address target, Mosaic amount,
         Message message) {
      TransferTransaction transfer = TransferTransaction
            .create(getDeadline(), target, Collections.singletonList(amount), message, getNetworkType());
      // add the modification to the aggregate transaction. has to be bonded because we are going to test the lock
      AggregateTransaction aggregateTransaction = AggregateTransaction.createComplete(getDeadline(),
            Arrays.asList(transfer.toAggregate(signerAccount.getPublicAccount())),
            getNetworkType());
      return signerAccount.sign(aggregateTransaction);
   }

   /**
    * create transaction and announce it
    * 
    * @param from account used to sign the transaction
    * @param to target address for the transfer
    * @param mosaic mosaic to transfer
    * @param message message for the transfer
    * @return transaction announce response message
    * @throws InterruptedException
    * @throws ExecutionException
    */
   private void transfer(Account from, Address to, Mosaic mosaic, Message message) {
      SignedTransaction signedTransaction = signTransfer(from, to, mosaic, message);
      logger.info("Transfer announced. {}", transactionHttp.announce(signedTransaction).blockingFirst());
      logger.info("Transfer done. {}",
            listener.confirmed(from.getAddress()).timeout(getTimeoutSeconds(), TimeUnit.SECONDS).blockingFirst());
      SignedTransaction signedAggregateTransaction = signAggregateTransfer(from, to, mosaic, message);
      logger.info("Transfer announced. {}", transactionHttp.announce(signedAggregateTransaction).blockingFirst());
      logger.info("Transfer done. {}",
            listener.confirmed(from.getAddress()).timeout(getTimeoutSeconds(), TimeUnit.SECONDS).blockingFirst());

   }

}