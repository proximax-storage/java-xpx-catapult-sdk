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

// automatically generated by the FlatBuffers compiler, do not modify

package io.proximax.sdk.model.transaction;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

@SuppressWarnings("unused")
final class MultisigAggregateModificationTransactionBuffer extends Table {
    public static MultisigAggregateModificationTransactionBuffer getRootAsMultisigAggregateModificationTransactionBuffer(ByteBuffer _bb) {
        return getRootAsMultisigAggregateModificationTransactionBuffer(_bb, new MultisigAggregateModificationTransactionBuffer());
    }

    public static MultisigAggregateModificationTransactionBuffer getRootAsMultisigAggregateModificationTransactionBuffer(ByteBuffer _bb, MultisigAggregateModificationTransactionBuffer obj) {
        _bb.order(ByteOrder.LITTLE_ENDIAN);
        return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb));
    }

    public static int createMultisigAggregateModificationTransactionBuffer(FlatBufferBuilder builder,
                                                                           long size,
                                                                           int signatureOffset,
                                                                           int signerOffset,
                                                                           int version,
                                                                           int type,
                                                                           int feeOffset,
                                                                           int deadlineOffset,
                                                                           int minRemovalDelta,
                                                                           int minApprovalDelta,
                                                                           int numModifications,
                                                                           int modificationsOffset) {
        builder.startObject(11);
        MultisigAggregateModificationTransactionBuffer.addModifications(builder, modificationsOffset);
        MultisigAggregateModificationTransactionBuffer.addDeadline(builder, deadlineOffset);
        MultisigAggregateModificationTransactionBuffer.addFee(builder, feeOffset);
        MultisigAggregateModificationTransactionBuffer.addSigner(builder, signerOffset);
        MultisigAggregateModificationTransactionBuffer.addSignature(builder, signatureOffset);
        MultisigAggregateModificationTransactionBuffer.addSize(builder, size);
        MultisigAggregateModificationTransactionBuffer.addType(builder, type);
        MultisigAggregateModificationTransactionBuffer.addVersion(builder, version);
        MultisigAggregateModificationTransactionBuffer.addNumModifications(builder, numModifications);
        MultisigAggregateModificationTransactionBuffer.addMinApprovalDelta(builder, minApprovalDelta);
        MultisigAggregateModificationTransactionBuffer.addMinRemovalDelta(builder, minRemovalDelta);
        return MultisigAggregateModificationTransactionBuffer.endMultisigAggregateModificationTransactionBuffer(builder);
    }

    public static void startMultisigAggregateModificationTransactionBuffer(FlatBufferBuilder builder) {
        builder.startObject(11);
    }

    public static void addSize(FlatBufferBuilder builder, long size) {
        builder.addInt(0, (int) size, (int) 0L);
    }

    public static void addSignature(FlatBufferBuilder builder, int signatureOffset) {
        builder.addOffset(1, signatureOffset, 0);
    }

    public static int createSignatureVector(FlatBufferBuilder builder, byte[] data) {
        builder.startVector(1, data.length, 1);
        for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]);
        return builder.endVector();
    }

    public static void startSignatureVector(FlatBufferBuilder builder, int numElems) {
        builder.startVector(1, numElems, 1);
    }

    public static void addSigner(FlatBufferBuilder builder, int signerOffset) {
        builder.addOffset(2, signerOffset, 0);
    }

    public static int createSignerVector(FlatBufferBuilder builder, byte[] data) {
        builder.startVector(1, data.length, 1);
        for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]);
        return builder.endVector();
    }

    public static void startSignerVector(FlatBufferBuilder builder, int numElems) {
        builder.startVector(1, numElems, 1);
    }

    public static void addVersion(FlatBufferBuilder builder, int version) {
        builder.addShort(3, (short) version, (short) 0);
    }

    public static void addType(FlatBufferBuilder builder, int type) {
        builder.addShort(4, (short) type, (short) 0);
    }

    public static void addFee(FlatBufferBuilder builder, int feeOffset) {
        builder.addOffset(5, feeOffset, 0);
    }

    public static int createFeeVector(FlatBufferBuilder builder, int[] data) {
        builder.startVector(4, data.length, 4);
        for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]);
        return builder.endVector();
    }

    public static void startFeeVector(FlatBufferBuilder builder, int numElems) {
        builder.startVector(4, numElems, 4);
    }

    public static void addDeadline(FlatBufferBuilder builder, int deadlineOffset) {
        builder.addOffset(6, deadlineOffset, 0);
    }

    public static int createDeadlineVector(FlatBufferBuilder builder, int[] data) {
        builder.startVector(4, data.length, 4);
        for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]);
        return builder.endVector();
    }

    public static void startDeadlineVector(FlatBufferBuilder builder, int numElems) {
        builder.startVector(4, numElems, 4);
    }

    public static void addMinRemovalDelta(FlatBufferBuilder builder, int minRemovalDelta) {
        builder.addByte(7, (byte) minRemovalDelta, (byte) 0);
    }

    public static void addMinApprovalDelta(FlatBufferBuilder builder, int minApprovalDelta) {
        builder.addByte(8, (byte) minApprovalDelta, (byte) 0);
    }

    public static void addNumModifications(FlatBufferBuilder builder, int numModifications) {
        builder.addByte(9, (byte) numModifications, (byte) 0);
    }

    public static void addModifications(FlatBufferBuilder builder, int modificationsOffset) {
        builder.addOffset(10, modificationsOffset, 0);
    }

    public static int createModificationsVector(FlatBufferBuilder builder, int[] data) {
        builder.startVector(4, data.length, 4);
        for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]);
        return builder.endVector();
    }

    public static void startModificationsVector(FlatBufferBuilder builder, int numElems) {
        builder.startVector(4, numElems, 4);
    }

    public static int endMultisigAggregateModificationTransactionBuffer(FlatBufferBuilder builder) {
        int o = builder.endObject();
        return o;
    }

    public static void finishMultisigAggregateModificationTransactionBufferBuffer(FlatBufferBuilder builder, int offset) {
        builder.finish(offset);
    }

    public void __init(int _i, ByteBuffer _bb) {
        bb_pos = _i;
        bb = _bb;
    }

    public MultisigAggregateModificationTransactionBuffer __assign(int _i, ByteBuffer _bb) {
        __init(_i, _bb);
        return this;
    }

    public long size() {
        int o = __offset(4);
        return o != 0 ? (long) bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0L;
    }

    public int signature(int j) {
        int o = __offset(6);
        return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0;
    }

    public int signatureLength() {
        int o = __offset(6);
        return o != 0 ? __vector_len(o) : 0;
    }

    public ByteBuffer signatureAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public int signer(int j) {
        int o = __offset(8);
        return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0;
    }

    public int signerLength() {
        int o = __offset(8);
        return o != 0 ? __vector_len(o) : 0;
    }

    public ByteBuffer signerAsByteBuffer() {
        return __vector_as_bytebuffer(8, 1);
    }

    public int version() {
        int o = __offset(10);
        return o != 0 ? bb.getShort(o + bb_pos) & 0xFFFF : 0;
    }

    public int type() {
        int o = __offset(12);
        return o != 0 ? bb.getShort(o + bb_pos) & 0xFFFF : 0;
    }

    public long fee(int j) {
        int o = __offset(14);
        return o != 0 ? (long) bb.getInt(__vector(o) + j * 4) & 0xFFFFFFFFL : 0;
    }

    public int feeLength() {
        int o = __offset(14);
        return o != 0 ? __vector_len(o) : 0;
    }

    public ByteBuffer feeAsByteBuffer() {
        return __vector_as_bytebuffer(14, 4);
    }

    public long deadline(int j) {
        int o = __offset(16);
        return o != 0 ? (long) bb.getInt(__vector(o) + j * 4) & 0xFFFFFFFFL : 0;
    }

    public int deadlineLength() {
        int o = __offset(16);
        return o != 0 ? __vector_len(o) : 0;
    }

    public ByteBuffer deadlineAsByteBuffer() {
        return __vector_as_bytebuffer(16, 4);
    }

    public int minRemovalDelta() {
        int o = __offset(18);
        return o != 0 ? bb.get(o + bb_pos) & 0xFF : 0;
    }

    public int minApprovalDelta() {
        int o = __offset(20);
        return o != 0 ? bb.get(o + bb_pos) & 0xFF : 0;
    }

    public int numModifications() {
        int o = __offset(22);
        return o != 0 ? bb.get(o + bb_pos) & 0xFF : 0;
    }

    public CosignatoryModificationBuffer modifications(int j) {
        return modifications(new CosignatoryModificationBuffer(), j);
    }

    public CosignatoryModificationBuffer modifications(CosignatoryModificationBuffer obj, int j) {
        int o = __offset(24);
        return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null;
    }

    public int modificationsLength() {
        int o = __offset(24);
        return o != 0 ? __vector_len(o) : 0;
    }
}

