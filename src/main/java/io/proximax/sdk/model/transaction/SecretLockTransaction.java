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

package io.proximax.sdk.model.transaction;

import java.math.BigInteger;
import java.util.Optional;

import org.apache.commons.lang3.Validate;
import org.spongycastle.util.encoders.Hex;

import com.google.flatbuffers.FlatBufferBuilder;

import io.proximax.core.utils.Base32Encoder;
import io.proximax.sdk.gen.buffers.SecretLockTransactionBuffer;
import io.proximax.sdk.model.account.Address;
import io.proximax.sdk.model.account.PublicAccount;
import io.proximax.sdk.model.blockchain.NetworkType;
import io.proximax.sdk.model.mosaic.Mosaic;
import io.proximax.sdk.utils.dto.UInt64Utils;

public class SecretLockTransaction extends Transaction {
   private final Schema schema = new SecretLockTransactionSchema();

   private final Mosaic mosaic;
   private final BigInteger duration;
   private final HashType hashType;
   private final String secret;
   private final Address recipient;

   /**
    * @param networkType network type
    * @param version transaction version. Use {@link TransactionVersion#MODIFY_MULTISIG_ACCOUNT} for current version
    * @param deadline transaction deadline
    * @param maxFee transaction fee
    * @param signature optional signature
    * @param signer optional signer
    * @param transactionInfo optional transaction info
    * @param mosaic mosaic amount to lock
    * @param duration duration of the lock in block count
    * @param hashType type of the hashing function
    * @param secret secret
    * @param recipient recipient of the locked funds
    */
   public SecretLockTransaction(NetworkType networkType, Integer version, TransactionDeadline deadline,
         BigInteger maxFee, Optional<String> signature, Optional<PublicAccount> signer,
         Optional<TransactionInfo> transactionInfo, Mosaic mosaic, BigInteger duration, HashType hashType,
         String secret, Address recipient) {
      super(TransactionType.SECRET_LOCK, networkType, version, deadline, maxFee, signature, signer, transactionInfo);
      // validations
      Validate.notNull(mosaic, "Mosaic must not be null");
      Validate.notNull(duration, "Duration must not be null");
      Validate.notNull(secret, "Secret must not be null");
      Validate.notNull(recipient, "Recipient must not be null");
      if (!hashType.validate(secret)) {
         throw new IllegalArgumentException("HashType and Secret have incompatible length or not hexadecimal string");
      }
      // assignments
      this.mosaic = mosaic;
      this.duration = duration;
      this.hashType = hashType;
      this.secret = secret;
      this.recipient = recipient;
   }

   /**
    * Returns locked mosaic.
    *
    * @return locked mosaic.
    */
   public Mosaic getMosaic() {
      return mosaic;
   }

   /**
    * Returns duration for the funds to be released or returned.
    *
    * @return duration for the funds to be released or returned.
    */
   public BigInteger getDuration() {
      return duration;
   }

   /**
    * Returns the hash algorithm, secret is generated with.
    *
    * @return the hash algorithm, secret is generated with.
    */
   public HashType getHashType() {
      return hashType;
   }

   /**
    * Returns the proof hashed.
    *
    * @return the proof hashed.
    */
   public String getSecret() {
      return secret;
   }

   /**
    * Returns the recipient of the funds.
    *
    * @return the recipient of the funds.
    */
   public Address getRecipient() {
      return recipient;
   }

   @Override
   protected byte[] generateBytes() {
      FlatBufferBuilder builder = new FlatBufferBuilder();
      BigInteger deadlineBigInt = BigInteger.valueOf(getDeadline().getInstant());

      // Create Vectors
      int signatureVector = SecretLockTransactionBuffer.createSignatureVector(builder, new byte[64]);
      int signerVector = SecretLockTransactionBuffer.createSignerVector(builder, new byte[32]);
      int deadlineVector = SecretLockTransactionBuffer.createDeadlineVector(builder,
            UInt64Utils.fromBigInteger(deadlineBigInt));
      int feeVector = SecretLockTransactionBuffer.createMaxFeeVector(builder, UInt64Utils.fromBigInteger(getMaxFee()));
      int mosaicIdVector = SecretLockTransactionBuffer.createMosaicIdVector(builder,
            UInt64Utils.fromBigInteger(mosaic.getId().getId()));
      int mosaicAmountVector = SecretLockTransactionBuffer.createMosaicAmountVector(builder,
            UInt64Utils.fromBigInteger(mosaic.getAmount()));
      int durationVector = SecretLockTransactionBuffer.createDurationVector(builder,
            UInt64Utils.fromBigInteger(duration));
      int secretVector = SecretLockTransactionBuffer.createSecretVector(builder, Hex.decode(secret));

      byte[] address = Base32Encoder.getBytes(getRecipient().plain());
      int recipientVector = SecretLockTransactionBuffer.createRecipientVector(builder, address);

      int size = getSerializedSize();

      SecretLockTransactionBuffer.startSecretLockTransactionBuffer(builder);
      SecretLockTransactionBuffer.addSize(builder, size);
      SecretLockTransactionBuffer.addSignature(builder, signatureVector);
      SecretLockTransactionBuffer.addSigner(builder, signerVector);
      SecretLockTransactionBuffer.addVersion(builder, getTxVersionforSerialization());
      SecretLockTransactionBuffer.addType(builder, getType().getValue());
      SecretLockTransactionBuffer.addMaxFee(builder, feeVector);
      SecretLockTransactionBuffer.addDeadline(builder, deadlineVector);

      SecretLockTransactionBuffer.addMosaicId(builder, mosaicIdVector);
      SecretLockTransactionBuffer.addMosaicAmount(builder, mosaicAmountVector);
      SecretLockTransactionBuffer.addDuration(builder, durationVector);
      SecretLockTransactionBuffer.addHashAlgorithm(builder, hashType.getValue());
      SecretLockTransactionBuffer.addSecret(builder, secretVector);
      SecretLockTransactionBuffer.addRecipient(builder, recipientVector);

      int codedSecretLock = SecretLockTransactionBuffer.endSecretLockTransactionBuffer(builder);
      builder.finish(codedSecretLock);

      // validate size
      byte[] output = schema.serialize(builder.sizedByteArray());
      Validate.isTrue(output.length == size, "Serialized transaction has incorrect length: " + this.getClass());
      return output;
   }

   public static int calculatePayloadSize() {
      // mosaicID, amount, duration, hash algo, secret, recipient
      return 8 + 8 + 8 + 1 + 32 + 25;
   }

   @Override
   protected int getPayloadSerializedSize() {
      return calculatePayloadSize();
   }

   @Override
   protected Transaction copyForSigner(PublicAccount signer) {
      return new SecretLockTransaction(getNetworkType(), getVersion(), getDeadline(), getMaxFee(), getSignature(),
            Optional.of(signer), getTransactionInfo(), getMosaic(), getDuration(), getHashType(), getSecret(),
            getRecipient());
   }
}
