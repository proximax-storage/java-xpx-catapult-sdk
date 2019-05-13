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

package io.proximax.core.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;

import io.proximax.core.test.ExceptionAssert;

/**
 * Test for SparseBitmap, our wrapper for EWAHCompressedBitmap.
 */
public class SparseBitmapTest {

    //region createEmpty

    private static final Map<String, SparseBitmap> DESC_TO_SB_MAP = new HashMap<String, SparseBitmap>() {
        {
            this.put("default", SparseBitmap.createFromSortedData(4, 8, 16));
            this.put("diff-short", SparseBitmap.createFromSortedData(4, 8));
            this.put("diff-long", SparseBitmap.createFromSortedData(4, 8, 16, 32));
            this.put("diff-values", SparseBitmap.createFromSortedData(4, 9, 16));
        }
    };

    //endregion

    //region createFromUnsortedData

    @Test
    public void createEmptyCanCreateEmptyBitmap() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createEmpty();

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(0));
        MatcherAssert.assertThat(sb.get(0), IsEqual.equalTo(false));
    }

    @Test
    public void createFromUnsortedDataCanCreateBitmapAroundNoBits() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromUnsortedData();

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(0));
        MatcherAssert.assertThat(sb.get(0), IsEqual.equalTo(false));
    }

    @Test
    public void createFromUnsortedDataCanCreateBitmapAroundBitsInSortedOrder() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromUnsortedData(0, 1, 3);

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(3));
        MatcherAssert.assertThat(sb.get(0), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(1), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(2), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(3), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(4), IsEqual.equalTo(false));
    }

    @Test
    public void createFromUnsortedDataCanCreateBitmapAroundBitsInSortedOrderWithDuplicates() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromUnsortedData(0, 1, 1, 3);

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(3));
        MatcherAssert.assertThat(sb.get(0), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(1), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(2), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(3), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(4), IsEqual.equalTo(false));
    }

    //endregion

    //region createFromSortedData

    @Test
    public void createFromUnsortedDataCanCreateBitmapAroundBitsInUnsortedOrder() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromUnsortedData(1, 0, 3);

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(3));
        MatcherAssert.assertThat(sb.get(0), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(1), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(2), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(3), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(4), IsEqual.equalTo(false));
    }

    @Test
    public void createFromSortedDataCanCreateBitmapAroundNoBits() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromSortedData();

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(0));
        MatcherAssert.assertThat(sb.get(0), IsEqual.equalTo(false));
    }

    @Test
    public void createFromSortedDataCanCreateBitmapAroundBitsInSortedOrder() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromUnsortedData(0, 1, 3);

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(3));
        MatcherAssert.assertThat(sb.get(0), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(1), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(2), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(3), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(4), IsEqual.equalTo(false));
    }

    //endregion

    //region get / set

    @Test
    public void createFromSortedDataCanCreateBitmapAroundBitsInSortedOrderWithDuplicates() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromSortedData(0, 1, 1, 3);

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(3));
        MatcherAssert.assertThat(sb.get(0), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(1), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(2), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(3), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(4), IsEqual.equalTo(false));
    }

    @Test
    public void getReturnsFalseOutOfBounds() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromUnsortedData(0, 1, 3);

        // Assert:
        MatcherAssert.assertThat(sb.get(-1000), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(-1), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(4), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(1000), IsEqual.equalTo(false));
    }

    @Test
    public void bitsCanBeSetInOrder() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromSortedData();
        sb.set(0);
        sb.set(100);
        sb.set(100);
        sb.set(2000000);

        // Assert:
        MatcherAssert.assertThat(sb.get(0), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(100), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(2000000), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(3), IsEqual.equalTo(false));
    }

    @Test
    public void bitsCannotBeSetOutOfOrder() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromSortedData();
        sb.set(2000000);

        // Assert:
        ExceptionAssert.assertThrows(v -> sb.set(100), IllegalArgumentException.class);
        ExceptionAssert.assertThrows(v -> sb.set(0), IllegalArgumentException.class);
    }

    @Test
    public void bitsCanBeSetInOrderWithoutAscendingCheck() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromSortedData();
        sb.setWithoutAscendingCheck(0);
        sb.setWithoutAscendingCheck(100);
        sb.setWithoutAscendingCheck(100);
        sb.setWithoutAscendingCheck(2000000);

        // Assert:
        MatcherAssert.assertThat(sb.get(0), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(100), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(2000000), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(3), IsEqual.equalTo(false));
    }

    //endregion

    //region clear

    @Test
    public void bitsCanBeSetOutOfOrderWithoutAscendingCheck() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromSortedData();
        sb.setWithoutAscendingCheck(2000000);
        sb.setWithoutAscendingCheck(100);
        sb.setWithoutAscendingCheck(100);
        sb.setWithoutAscendingCheck(0);

        // Assert: the correct bits are set
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(3));
        MatcherAssert.assertThat(sb.get(0), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(100), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(2000000), IsEqual.equalTo(true));
    }

    @Test
    public void clearZerosOutAllBits() {
        // Arrange:
        final SparseBitmap sb = SparseBitmap.createFromSortedData(4, 5, 6);

        // Act:
        sb.clear();

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(0));
        MatcherAssert.assertThat(sb.get(4), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(5), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(6), IsEqual.equalTo(false));
    }

    //endregion

    //region getHighestBit

    @Test
    public void bitsCanBeSetAfterClearing() {
        // Arrange:
        final SparseBitmap sb = SparseBitmap.createFromSortedData(4, 5, 6);

        // Act:
        sb.clear();
        sb.set(3);

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(1));
        MatcherAssert.assertThat(sb.get(4), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(5), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(6), IsEqual.equalTo(false));
        MatcherAssert.assertThat(sb.get(3), IsEqual.equalTo(true));
    }

    @Test
    public void getHighestBitReturnsCorrectResultWhenNoBitsAreSet() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromSortedData();

        // Assert:
        MatcherAssert.assertThat(sb.getHighestBit(), IsEqual.equalTo(0));
    }

    //endregion

    //region batchOr

    @Test
    public void getHighestBitReturnsCorrectResultWhenBitsAreSet() {
        // Act:
        final SparseBitmap sb = SparseBitmap.createFromUnsortedData(0, 1, 7);

        // Assert:
        MatcherAssert.assertThat(sb.getHighestBit(), IsEqual.equalTo(7));
    }

    @Test
    public void batchOrCanCreateBitmapFromZeroBitmaps() {
        // Act:
        final SparseBitmap sb = SparseBitmap.batchOr();

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(0));
    }

    @Test
    public void batchOrCanCreateBitmapFromOneBitmap() {
        // Act:
        final SparseBitmap sb = SparseBitmap.batchOr(SparseBitmap.createFromSortedData(1, 3));

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(2));
        MatcherAssert.assertThat(sb.get(1), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(3), IsEqual.equalTo(true));
    }

    //endregion

    //region toList / iterator

    @Test
    public void batchOrCanCreateBitmapFromMultipleBitmaps() {
        // Act:
        final SparseBitmap sb = SparseBitmap.batchOr(
                SparseBitmap.createFromSortedData(1, 3),
                SparseBitmap.createFromSortedData(5, 6),
                SparseBitmap.createFromSortedData(2, 7));

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(6));
        MatcherAssert.assertThat(sb.get(1), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(3), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(5), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(6), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(2), IsEqual.equalTo(true));
        MatcherAssert.assertThat(sb.get(7), IsEqual.equalTo(true));
    }

    @Test
    public void toListReturnsSetBits() {
        // Arrange:
        final SparseBitmap sb = SparseBitmap.createFromSortedData(100, 200, 300);

        // Assert:
        MatcherAssert.assertThat(sb.toList(), IsEqual.equalTo(Arrays.asList(100, 200, 300)));
    }

    //endregion

    //region or / and / andNot

    @Test
    public void iteratorReturnsSetBits() {
        // Arrange:
        final SparseBitmap sb = SparseBitmap.createFromSortedData(100, 200, 300);

        // Assert:
        MatcherAssert.assertThat(
                StreamSupport.stream(sb.spliterator(), false).collect(Collectors.toList()),
                IsEqual.equalTo(Arrays.asList(100, 200, 300)));
    }

    @Test
    public void orCreatesCorrectBitmap() {
        // Arrange:
        final SparseBitmap sb1 = SparseBitmap.createFromSortedData(2, 4, 8);
        final SparseBitmap sb2 = SparseBitmap.createFromSortedData(4, 16, 256);

        // Act:
        final SparseBitmap result = sb1.or(sb2);

        // Assert:

        MatcherAssert.assertThat(result.cardinality(), IsEqual.equalTo(5));
        MatcherAssert.assertThat(result.get(2), IsEqual.equalTo(true));
        MatcherAssert.assertThat(result.get(4), IsEqual.equalTo(true));
        MatcherAssert.assertThat(result.get(8), IsEqual.equalTo(true));
        MatcherAssert.assertThat(result.get(16), IsEqual.equalTo(true));
        MatcherAssert.assertThat(result.get(256), IsEqual.equalTo(true));
    }

    @Test
    public void andCreatesCorrectBitmap() {
        // Arrange:
        final SparseBitmap sb1 = SparseBitmap.createFromSortedData(2, 4, 8);
        final SparseBitmap sb2 = SparseBitmap.createFromSortedData(4, 16, 256);

        // Act:
        final SparseBitmap result = sb1.and(sb2);

        // Assert:

        MatcherAssert.assertThat(result.cardinality(), IsEqual.equalTo(1));
        MatcherAssert.assertThat(result.get(4), IsEqual.equalTo(true));
    }

    //endregion

    //region cardinality / andCardinality

    @Test
    public void andNotCreatesCorrectBitmap() {
        // Arrange:
        final SparseBitmap sb1 = SparseBitmap.createFromSortedData(2, 4, 8);
        final SparseBitmap sb2 = SparseBitmap.createFromSortedData(4, 16, 256);

        // Act:
        final SparseBitmap result = sb1.andNot(sb2);

        // Assert:

        MatcherAssert.assertThat(result.cardinality(), IsEqual.equalTo(2));
        MatcherAssert.assertThat(result.get(2), IsEqual.equalTo(true));
        MatcherAssert.assertThat(result.get(8), IsEqual.equalTo(true));
    }

    @Test
    public void cardinalityReturnsTheNumberOfSetBits() {
        // Arrange:
        final SparseBitmap sb = SparseBitmap.createFromSortedData(100, 200, 300, 400, 1337);

        // Assert:
        MatcherAssert.assertThat(sb.cardinality(), IsEqual.equalTo(5));
    }

    //endregion

    //region toString

    @Test
    public void andCardinalityReturnsTheNumberOfIntersectingBits() {
        // Arrange:
        final SparseBitmap sb1 = SparseBitmap.createFromSortedData(2, 4, 8, 16);
        final SparseBitmap sb2 = SparseBitmap.createFromSortedData(4, 16, 256);

        // Act:
        final int result = sb1.andCardinality(sb2);

        // Assert:
        MatcherAssert.assertThat(result, IsEqual.equalTo(2));
    }

    @Test
    public void toStringReturnsCorrectRepresentationForEmptyBitmap() {
        // Arrange:
        final SparseBitmap sb = SparseBitmap.createFromSortedData();

        // Assert:
        MatcherAssert.assertThat(sb.toString(), IsEqual.equalTo("{}"));
    }

    //endregion

    //region hashCode/equals

    @Test
    public void toStringReturnsCorrectRepresentationForNonEmptyBitmap() {
        // Arrange:
        final SparseBitmap sb = SparseBitmap.createFromSortedData(100, 200, 300, 400, 1337);

        // Assert:
        MatcherAssert.assertThat(sb.toString(), IsEqual.equalTo("{100,200,300,400,1337}"));
    }

    @Test
    public void equalsOnlyReturnsTrueForEquivalentObjects() {
        // Arrange:
        final SparseBitmap sb = SparseBitmap.createFromSortedData(4, 8, 16);

        // Assert:
        MatcherAssert.assertThat(DESC_TO_SB_MAP.get("default"), IsEqual.equalTo(sb));
        MatcherAssert.assertThat(DESC_TO_SB_MAP.get("diff-short"), IsNot.not(IsEqual.equalTo(sb)));
        MatcherAssert.assertThat(DESC_TO_SB_MAP.get("diff-long"), IsNot.not(IsEqual.equalTo(sb)));
        MatcherAssert.assertThat(DESC_TO_SB_MAP.get("diff-values"), IsNot.not(IsEqual.equalTo(sb)));
        MatcherAssert.assertThat(null, IsNot.not(IsEqual.equalTo(sb)));
        MatcherAssert.assertThat(8, IsNot.not(IsEqual.equalTo((Object) sb)));
    }

    @Test
    public void hashCodesAreEqualForEquivalentObjects() {
        // Arrange:
        final SparseBitmap sb = SparseBitmap.createFromSortedData(4, 8, 16);
        final int hashCode = sb.hashCode();

        // Assert:
        MatcherAssert.assertThat(DESC_TO_SB_MAP.get("default").hashCode(), IsEqual.equalTo(hashCode));
        MatcherAssert.assertThat(DESC_TO_SB_MAP.get("diff-short").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
        MatcherAssert.assertThat(DESC_TO_SB_MAP.get("diff-long").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
        MatcherAssert.assertThat(DESC_TO_SB_MAP.get("diff-values").hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
    }

    // endregion
}
