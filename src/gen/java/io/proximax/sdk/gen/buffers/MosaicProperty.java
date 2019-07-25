// automatically generated by the FlatBuffers compiler, do not modify

package io.proximax.sdk.gen.buffers;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class MosaicProperty extends Table {
  public static MosaicProperty getRootAsMosaicProperty(ByteBuffer _bb) { return getRootAsMosaicProperty(_bb, new MosaicProperty()); }
  public static MosaicProperty getRootAsMosaicProperty(ByteBuffer _bb, MosaicProperty obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public MosaicProperty __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public int mosaicPropertyId() { int o = __offset(4); return o != 0 ? bb.get(o + bb_pos) & 0xFF : 0; }
  public long value(int j) { int o = __offset(6); return o != 0 ? (long)bb.getInt(__vector(o) + j * 4) & 0xFFFFFFFFL : 0; }
  public int valueLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer valueAsByteBuffer() { return __vector_as_bytebuffer(6, 4); }
  public ByteBuffer valueInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 4); }

  public static int createMosaicProperty(FlatBufferBuilder builder,
      int mosaicPropertyId,
      int valueOffset) {
    builder.startObject(2);
    MosaicProperty.addValue(builder, valueOffset);
    MosaicProperty.addMosaicPropertyId(builder, mosaicPropertyId);
    return MosaicProperty.endMosaicProperty(builder);
  }

  public static void startMosaicProperty(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addMosaicPropertyId(FlatBufferBuilder builder, int mosaicPropertyId) { builder.addByte(0, (byte)mosaicPropertyId, (byte)0); }
  public static void addValue(FlatBufferBuilder builder, int valueOffset) { builder.addOffset(1, valueOffset, 0); }
  public static int createValueVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startValueVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endMosaicProperty(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

