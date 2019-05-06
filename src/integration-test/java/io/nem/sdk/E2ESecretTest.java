/*
 * Copyright 2018 NEM
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
package io.nem.sdk;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.nem.core.crypto.Hashes;
import io.nem.core.crypto.KeyPair;
import io.nem.sdk.model.account.Account;
import io.nem.sdk.model.account.Address;
import io.nem.sdk.model.mosaic.XPX;
import io.nem.sdk.model.transaction.AggregateTransaction;
import io.nem.sdk.model.transaction.HashType;
import io.nem.sdk.model.transaction.SecretLockTransaction;
import io.nem.sdk.model.transaction.SecretProofTransaction;
import io.nem.sdk.model.transaction.SignedTransaction;

/**
 * Tests for secure lock and secure proof that can be used for cross-chain swaps
 * 
 * @author tonowie
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class E2ESecretTest extends E2EBaseTest {
   /** logger */
   private static final Logger logger = LoggerFactory.getLogger(E2ESecretTest.class);

   private final Account simpleAccount = new Account(new KeyPair(), NETWORK_TYPE);

   @BeforeAll
   void addListener() {
      logger.info("Will be sending transactions to {}", simpleAccount);
      listener.status(simpleAccount.getAddress()).doOnNext(err -> logger.info("Error on recipient: {}", err))
            .doOnError(exception -> logger.error("Failure on recipient: {}", exception))
            .doOnComplete(() -> logger.info("done with recipient {}", simpleAccount));
   }

   @Test
   void standaloneSecretLockAndProofTransaction_SHA3_256() throws ExecutionException, InterruptedException {
      standaloneSecretLockAndProofTransaction(seedAccount, simpleAccount.getAddress(), HashType.SHA3_256);
   }

   @Test
   void standaloneSecretLockAndProofTransaction_KECCAK_256() throws ExecutionException, InterruptedException {
      standaloneSecretLockAndProofTransaction(seedAccount, simpleAccount.getAddress(), HashType.KECCAK_256);
   }

   @Test
   void standaloneSecretLockAndProofTransaction_HASH_160() throws ExecutionException, InterruptedException {
      standaloneSecretLockAndProofTransaction(seedAccount, simpleAccount.getAddress(), HashType.HASH_160);
   }

   @Test
   void standaloneSecretLockAndProofTransaction_HASH_256() throws ExecutionException, InterruptedException {
      standaloneSecretLockAndProofTransaction(seedAccount, simpleAccount.getAddress(), HashType.HASH_256);
   }

   void standaloneSecretLockAndProofTransaction(Account from, Address to, HashType hashType) {
      logger.info("Creating standalone lock and proof for {}", hashType);
      byte[] secretBytes = new byte[20];
      new Random().nextBytes(secretBytes);
      byte[] result = hashType.hashValue(secretBytes);
      String secret = Hex.encodeHexString(result);
      String proof = Hex.encodeHexString(secretBytes);
      // make a secret lock moving mosaic to the target account
      SecretLockTransaction secretLocktx = SecretLockTransaction.create(getDeadline(),
            XPX.createRelative(BigInteger.valueOf(1)),
            BigInteger.valueOf(10),
            hashType,
            secret,
            to,
            NETWORK_TYPE);
      SignedTransaction secretLockTransactionSigned = from.sign(secretLocktx);
      transactionHttp.announce(secretLockTransactionSigned).blockingFirst();
      logger.info("Lock confirmed: {}",
            listener.confirmed(from.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());

      SecretProofTransaction secretProoftx = SecretProofTransaction
            .create(getDeadline(), hashType, secret, proof, NETWORK_TYPE);
      SignedTransaction secretProoftxSigned = from.sign(secretProoftx);
      transactionHttp.announce(secretProoftxSigned).blockingFirst();
      logger.info("Proof confirmed: {}",
            listener.confirmed(from.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
   }

   @Test
   void aggregateSecretLockAndProofTransaction_SHA3_256() {
      aggregateSecretLockAndProofTransaction(seedAccount, simpleAccount.getAddress(), HashType.SHA3_256);
   }
   
   @Test
   void aggregateSecretLockandProofTransaction_KECCAK_256() {
      aggregateSecretLockAndProofTransaction(seedAccount, simpleAccount.getAddress(), HashType.KECCAK_256);
   }
   
   @Test
   void aggregateSecretLockAndProofTransaction_HASH_160() {
      aggregateSecretLockAndProofTransaction(seedAccount, simpleAccount.getAddress(), HashType.HASH_160);
   }
   
   @Test
   void aggregateSecretLockAndProofTransaction_HASH_256() {
      aggregateSecretLockAndProofTransaction(seedAccount, simpleAccount.getAddress(), HashType.HASH_256);
   }
   
   void aggregateSecretLockAndProofTransaction(Account from, Address to, HashType hashType) {
      logger.info("Creating aggregate lock and proof for {}", hashType);
      byte[] secretBytes = new byte[20];
      new Random().nextBytes(secretBytes);
      byte[] result = Hashes.sha3_256(secretBytes);
      String secret = Hex.encodeHexString(result);
      String proof = Hex.encodeHexString(secretBytes);
      // make a secret lock moving mosaic to the target account
      SecretLockTransaction secretLocktx = SecretLockTransaction.create(getDeadline(),
            XPX.createRelative(BigInteger.valueOf(1)),
            BigInteger.valueOf(10),
            hashType,
            secret,
            to,
            NETWORK_TYPE);
      SignedTransaction lockFundsTransactionSigned = from.sign(secretLocktx);
      transactionHttp.announce(lockFundsTransactionSigned).blockingFirst();
      logger.info("Lock confirmed: {}",
            listener.confirmed(from.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());

      // create aggregate proof
      SecretProofTransaction secretProoftx = SecretProofTransaction
            .create(getDeadline(), hashType, secret, proof, NETWORK_TYPE);
      AggregateTransaction secretProofAggregatetx = AggregateTransaction.createComplete(getDeadline(),
            Collections.singletonList(secretProoftx.toAggregate(from.getPublicAccount())),
            NETWORK_TYPE);
      SignedTransaction secretProofTransactionSigned = from.sign(secretProofAggregatetx);
      transactionHttp.announce(secretProofTransactionSigned).blockingFirst();

      logger.info("Proof confirmed: {}",
            listener.confirmed(from.getAddress()).timeout(30, TimeUnit.SECONDS).blockingFirst());
   }
}