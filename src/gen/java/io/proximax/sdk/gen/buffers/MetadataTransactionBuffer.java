// automatically generated by the FlatBuffers compiler, do not modify

package io.proximax.sdk.gen.buffers;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class MetadataTransactionBuffer extends Table {
  public static MetadataTransactionBuffer getRootAsMetadataTransactionBuffer(ByteBuffer _bb) { return getRootAsMetadataTransactionBuffer(_bb, new MetadataTransactionBuffer()); }
  public static MetadataTransactionBuffer getRootAsMetadataTransactionBuffer(ByteBuffer _bb, MetadataTransactionBuffer obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public MetadataTransactionBuffer __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public long size() { int o = __offset(4); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0L; }
  public int signature(int j) { int o = __offset(6); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int signatureLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer signatureAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer signatureInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }
  public int signer(int j) { int o = __offset(8); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int signerLength() { int o = __offset(8); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer signerAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }
  public ByteBuffer signerInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 8, 1); }
  public long version() { int o = __offset(10); return o != 0 ? (long)bb.getInt(o + bb_pos) & 0xFFFFFFFFL : 0L; }
  public int type() { int o = __offset(12); return o != 0 ? bb.getShort(o + bb_pos) & 0xFFFF : 0; }
  public long maxFee(int j) { int o = __offset(14); return o != 0 ? (long)bb.getInt(__vector(o) + j * 4) & 0xFFFFFFFFL : 0; }
  public int maxFeeLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer maxFeeAsByteBuffer() { return __vector_as_bytebuffer(14, 4); }
  public ByteBuffer maxFeeInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 14, 4); }
  public long deadline(int j) { int o = __offset(16); return o != 0 ? (long)bb.getInt(__vector(o) + j * 4) & 0xFFFFFFFFL : 0; }
  public int deadlineLength() { int o = __offset(16); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer deadlineAsByteBuffer() { return __vector_as_bytebuffer(16, 4); }
  public ByteBuffer deadlineInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 16, 4); }
  public int targetKey(int j) { int o = __offset(18); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int targetKeyLength() { int o = __offset(18); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer targetKeyAsByteBuffer() { return __vector_as_bytebuffer(18, 1); }
  public ByteBuffer targetKeyInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 18, 1); }
  public long scopedMetadataKey(int j) { int o = __offset(20); return o != 0 ? (long)bb.getInt(__vector(o) + j * 4) & 0xFFFFFFFFL : 0; }
  public int scopedMetadataKeyLength() { int o = __offset(20); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer scopedMetadataKeyAsByteBuffer() { return __vector_as_bytebuffer(20, 4); }
  public ByteBuffer scopedMetadataKeyInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 20, 4); }
  /**
   * In case of address it is empty array. In case of mosaic or namespace id it is 8 byte array(or 2 uint32 array)
   */
  public long targetId(int j) { int o = __offset(22); return o != 0 ? (long)bb.getInt(__vector(o) + j * 4) & 0xFFFFFFFFL : 0; }
  public int targetIdLength() { int o = __offset(22); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer targetIdAsByteBuffer() { return __vector_as_bytebuffer(22, 4); }
  public ByteBuffer targetIdInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 22, 4); }
  public short valueSizeDelta() { int o = __offset(24); return o != 0 ? bb.getShort(o + bb_pos) : 0; }
  public int valueSize() { int o = __offset(26); return o != 0 ? bb.getShort(o + bb_pos) & 0xFFFF : 0; }
  public int value(int j) { int o = __offset(28); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int valueLength() { int o = __offset(28); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer valueAsByteBuffer() { return __vector_as_bytebuffer(28, 1); }
  public ByteBuffer valueInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 28, 1); }

  public static int createMetadataTransactionBuffer(FlatBufferBuilder builder,
      long size,
      int signatureOffset,
      int signerOffset,
      long version,
      int type,
      int maxFeeOffset,
      int deadlineOffset,
      int targetKeyOffset,
      int scopedMetadataKeyOffset,
      int targetIdOffset,
      short valueSizeDelta,
      int valueSize,
      int valueOffset) {
    builder.startObject(13);
    MetadataTransactionBuffer.addValue(builder, valueOffset);
    MetadataTransactionBuffer.addTargetId(builder, targetIdOffset);
    MetadataTransactionBuffer.addScopedMetadataKey(builder, scopedMetadataKeyOffset);
    MetadataTransactionBuffer.addTargetKey(builder, targetKeyOffset);
    MetadataTransactionBuffer.addDeadline(builder, deadlineOffset);
    MetadataTransactionBuffer.addMaxFee(builder, maxFeeOffset);
    MetadataTransactionBuffer.addVersion(builder, version);
    MetadataTransactionBuffer.addSigner(builder, signerOffset);
    MetadataTransactionBuffer.addSignature(builder, signatureOffset);
    MetadataTransactionBuffer.addSize(builder, size);
    MetadataTransactionBuffer.addValueSize(builder, valueSize);
    MetadataTransactionBuffer.addValueSizeDelta(builder, valueSizeDelta);
    MetadataTransactionBuffer.addType(builder, type);
    return MetadataTransactionBuffer.endMetadataTransactionBuffer(builder);
  }

  public static void startMetadataTransactionBuffer(FlatBufferBuilder builder) { builder.startObject(13); }
  public static void addSize(FlatBufferBuilder builder, long size) { builder.addInt(0, (int)size, (int)0L); }
  public static void addSignature(FlatBufferBuilder builder, int signatureOffset) { builder.addOffset(1, signatureOffset, 0); }
  public static int createSignatureVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startSignatureVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addSigner(FlatBufferBuilder builder, int signerOffset) { builder.addOffset(2, signerOffset, 0); }
  public static int createSignerVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startSignerVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addVersion(FlatBufferBuilder builder, long version) { builder.addInt(3, (int)version, (int)0L); }
  public static void addType(FlatBufferBuilder builder, int type) { builder.addShort(4, (short)type, (short)0); }
  public static void addMaxFee(FlatBufferBuilder builder, int maxFeeOffset) { builder.addOffset(5, maxFeeOffset, 0); }
  public static int createMaxFeeVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startMaxFeeVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addDeadline(FlatBufferBuilder builder, int deadlineOffset) { builder.addOffset(6, deadlineOffset, 0); }
  public static int createDeadlineVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startDeadlineVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addTargetKey(FlatBufferBuilder builder, int targetKeyOffset) { builder.addOffset(7, targetKeyOffset, 0); }
  public static int createTargetKeyVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startTargetKeyVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addScopedMetadataKey(FlatBufferBuilder builder, int scopedMetadataKeyOffset) { builder.addOffset(8, scopedMetadataKeyOffset, 0); }
  public static int createScopedMetadataKeyVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startScopedMetadataKeyVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addTargetId(FlatBufferBuilder builder, int targetIdOffset) { builder.addOffset(9, targetIdOffset, 0); }
  public static int createTargetIdVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startTargetIdVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addValueSizeDelta(FlatBufferBuilder builder, short valueSizeDelta) { builder.addShort(10, valueSizeDelta, 0); }
  public static void addValueSize(FlatBufferBuilder builder, int valueSize) { builder.addShort(11, (short)valueSize, (short)0); }
  public static void addValue(FlatBufferBuilder builder, int valueOffset) { builder.addOffset(12, valueOffset, 0); }
  public static int createValueVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startValueVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static int endMetadataTransactionBuffer(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishMetadataTransactionBufferBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
  public static void finishSizePrefixedMetadataTransactionBufferBuffer(FlatBufferBuilder builder, int offset) { builder.finishSizePrefixed(offset); }
}
