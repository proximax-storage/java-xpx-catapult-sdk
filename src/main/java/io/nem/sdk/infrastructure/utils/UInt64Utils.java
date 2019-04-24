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

package io.nem.sdk.infrastructure.utils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.apache.commons.lang.ArrayUtils;

import io.nem.sdk.infrastructure.model.UInt64DTO;

/**
 * Utility class to handle conversions between BigInteger and int array
 * 
 * @author tonowie
 */
public class UInt64Utils {

    /**
	 * utility constructor to prevent instantiation
	 */
	private UInt64Utils() {
		// nothing to do, just making constructor private
	}

	/**
	 * create array of integers from BigInteger instance
	 * 
	 * @param input BigInteger that will be split to array of int values
	 * @return array of integers
	 */
	public static int[] fromBigInteger(BigInteger input) {
        byte[] bytes = input.toByteArray();
        ArrayUtils.reverse(bytes);
        int lower = 0;
        int higher = 0;
        byte[] lowerBound = new byte[4];
        int size = 4;
        if (bytes.length < 4) size = bytes.length;
        System.arraycopy(bytes, 0, lowerBound, 0, size);
        lower = ByteBuffer.wrap(lowerBound).order(ByteOrder.LITTLE_ENDIAN).getInt();
        if (bytes.length > 4) {
            byte[] higherBound = new byte[4];
            size = 4;
            if (bytes.length - 4 < 4) {
                size = bytes.length - 4;
            }
            System.arraycopy(bytes, 4, higherBound, 0, size);
            higher = ByteBuffer.wrap(higherBound).order(ByteOrder.LITTLE_ENDIAN).getInt();
        }
        return new int[]{lower, higher};
    }

	/**
	 * reconstruct BigInteger from int array
	 * 
	 * @param input array of int values
	 * @return BigInt instance representing the value
	 */
    public static BigInteger fromIntArray(int[] input) {
        if (input.length != 2) {
            throw new IllegalArgumentException("input must have length 2");
        }
        ArrayUtils.reverse(input);
        byte[] array = new byte[input.length * 4];
        ByteBuffer bbuf = ByteBuffer.wrap(array);
        IntBuffer ibuf = bbuf.asIntBuffer();
        ibuf.put(input);
        return new BigInteger(array);
    }

    /**
     * convert DTO representing array of integers to BigInteger instance
     * 
     * @param dto UInt64DTO instance representing array of int32 values
     * @return BigInteger reconstructed from the array
     */
    public static BigInteger toBigInt(UInt64DTO dto) {
    	return fromIntArray(dto.stream().mapToInt(Integer::intValue).toArray());
    }
    
    /**
     * convert BigInteger to series of hex characters
     * 
     * @param input BigInteger
     * @return hex string representing the value
     */
    public static String bigIntegerToHex(BigInteger input) {
        int[] uint64Parts = UInt64Utils.fromBigInteger(input);
        return Integer.toHexString(uint64Parts[1]) + Integer.toHexString(uint64Parts[0]);
    }
}