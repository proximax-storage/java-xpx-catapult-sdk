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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;

import io.proximax.core.test.ExceptionAssert;
import io.proximax.core.test.IsRoundedEqual;

public class ColumnVectorTest {

    //region constructor / getAt / setAt

    private static void assertOutOfBounds(final int size, final int index) {
        ExceptionAssert.assertThrows(v -> {
            // Arrange:
            final ColumnVector vector = new ColumnVector(size);

            // Act:
            vector.getAt(index);
        }, IndexOutOfBoundsException.class);
    }

    @Test
    public void vectorIsInitializedToZero() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(3);

        // Assert:
        MatcherAssert.assertThat(vector.size(), IsEqual.equalTo(3));
        MatcherAssert.assertThat(vector.getAt(0), IsEqual.equalTo(0.0));
        MatcherAssert.assertThat(vector.getAt(1), IsEqual.equalTo(0.0));
        MatcherAssert.assertThat(vector.getAt(2), IsEqual.equalTo(0.0));
    }

    @Test
    public void vectorCanBeInitializedAroundRawVector() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(9.0, 3.2, 5.4);

        // Assert:
        MatcherAssert.assertThat(vector.size(), IsEqual.equalTo(3));
        MatcherAssert.assertThat(vector.getAt(0), IsEqual.equalTo(9.0));
        MatcherAssert.assertThat(vector.getAt(1), IsEqual.equalTo(3.2));
        MatcherAssert.assertThat(vector.getAt(2), IsEqual.equalTo(5.4));
    }

    @Test
    public void vectorMustHaveNonZeroSize() {
        // Assert:
        ExceptionAssert.assertThrows(v -> new ColumnVector(0), IllegalArgumentException.class);
        ExceptionAssert.assertThrows(v -> new ColumnVector(null), IllegalArgumentException.class);
        ExceptionAssert.assertThrows(v -> new ColumnVector(), IllegalArgumentException.class);
    }

    @Test
    public void vectorValuesCanBeSet() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(3);

        // Act:
        vector.setAt(0, 7);
        vector.setAt(1, 3);
        vector.setAt(2, 5);

        // Assert:
        MatcherAssert.assertThat(vector, IsEqual.equalTo(new ColumnVector(7, 3, 5)));
    }

    @Test
    public void vectorValuesCanBeIncremented() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(3);

        // Act:
        vector.setAt(0, 7);
        vector.setAt(1, 3);
        vector.setAt(2, 5);
        vector.incrementAt(0, 6);
        vector.incrementAt(1, 4);
        vector.incrementAt(2, 1);

        // Assert:
        MatcherAssert.assertThat(vector, IsEqual.equalTo(new ColumnVector(13, 7, 6)));
    }

    @Test
    public void vectorCannotBeIndexedOutOfBounds() {
        // Assert:
        assertOutOfBounds(3, -1);
        assertOutOfBounds(3, 3);
    }

    //endregion

    //region getRaw

    @Test
    public void rawVectorIsAccessible() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(9.0, 3.2, 5.4);

        // Act:
        final boolean areEqual = Arrays.equals(vector.getRaw(), new double[]{9.0, 3.2, 5.4});

        // Assert:
        MatcherAssert.assertThat(areEqual, IsEqual.equalTo(true));
    }

    @Test
    public void rawVectorIsMutable() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(9.0, 3.2, 5.4);

        // Act:
        vector.setAt(1, 7.1);
        final boolean areEqual = Arrays.equals(vector.getRaw(), new double[]{9.0, 7.1, 5.4});

        // Assert:
        MatcherAssert.assertThat(areEqual, IsEqual.equalTo(true));
    }

    //endregion

    //region setAll

    @Test
    public void setAllSetsAllVectorElementValues() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(3);

        // Act:
        vector.setAll(4);

        // Assert:
        MatcherAssert.assertThat(vector, IsEqual.equalTo(new ColumnVector(4, 4, 4)));
    }

    //endregion

    //region sum / absSum

    @Test
    public void vectorSumCanBeCalculated() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(7, -3, 5);

        // Assert:
        MatcherAssert.assertThat(vector.sum(), IsEqual.equalTo(9.0));
    }

    @Test
    public void vectorAbsSumCanBeCalculated() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(7, -3, 5);

        // Assert:
        MatcherAssert.assertThat(vector.absSum(), IsEqual.equalTo(15.0));
    }

    //endregion

    //region max / median

    @Test
    public void vectorMaxCanBeCalculated() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(7, 11, 5);

        // Assert:
        MatcherAssert.assertThat(vector.max(), IsEqual.equalTo(11.0));
    }

    @Test
    public void vectorMedianCanBeCalculated() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(7, 11, 5);

        // Assert:
        MatcherAssert.assertThat(vector.median(), IsEqual.equalTo(7.0));
    }

    //endregion

    //region align

    @Test
    public void cannotAlignVectorWithNonZeroInFirstPosition() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(0, -6, 14);

        // Act:
        final boolean result = vector.align();

        // Assert:
        MatcherAssert.assertThat(result, IsEqual.equalTo(false));
        MatcherAssert.assertThat(vector, IsEqual.equalTo(new ColumnVector(0, -6, 14)));
    }

    @Test
    public void canAlignVectorWithNonZeroValueInFirstPosition() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(-4, -6, 14);

        // Act:
        final boolean result = vector.align();

        // Assert:
        MatcherAssert.assertThat(result, IsEqual.equalTo(true));
        MatcherAssert.assertThat(vector, IsEqual.equalTo(new ColumnVector(1, 1.5, -3.5)));
    }

    //endregion

    //region scale

    @Test
    public void vectorCanBeScaledByArbitraryFactor() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(2, -4, 1);

        // Act:
        vector.scale(8);

        // Assert:
        MatcherAssert.assertThat(vector, IsEqual.equalTo(new ColumnVector(0.25, -0.50, 0.125)));
    }

    //endregion

    //region normalize

    @Test
    public void vectorWithNonZeroSumCanBeNormalized() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(3, 5, 2);

        // Act:
        vector.normalize();

        // Assert:
        MatcherAssert.assertThat(vector, IsEqual.equalTo(new ColumnVector(0.3, 0.5, 0.2)));
    }

    @Test
    public void vectorWithNegativeValuesCanBeNormalized() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(3, -5, 2);

        // Act:
        vector.normalize();

        // Assert:
        MatcherAssert.assertThat(vector, IsEqual.equalTo(new ColumnVector(0.3, -0.5, 0.2)));
    }

    @Test
    public void zeroVectorCanBeNormalized() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(3);

        // Act:
        vector.normalize();

        // Assert:
        MatcherAssert.assertThat(vector, IsEqual.equalTo(new ColumnVector(0, 0, 0)));
    }

    //endregion

    //region add

    @Test
    public void scalarCanBeAddedToVector() {
        // Arrange:
        final ColumnVector a = new ColumnVector(2, -4, 1);

        // Act:
        final ColumnVector result = a.add(8);

        // Assert:
        MatcherAssert.assertThat(result, IsNot.not(IsEqual.equalTo(a)));
        MatcherAssert.assertThat(result, IsEqual.equalTo(new ColumnVector(10, 4, 9)));
    }

    //endregion

    //region addElementWise

    @Test
    public void twoVectorsOfSameSizeCanBeAddedTogetherElementWise() {
        // Arrange:
        final ColumnVector a = new ColumnVector(7, 5, 11);
        final ColumnVector b = new ColumnVector(2, -4, 1);

        // Act:
        final ColumnVector result = a.addElementWise(b);

        // Assert:
        MatcherAssert.assertThat(result, IsNot.not(IsEqual.equalTo(a)));
        MatcherAssert.assertThat(result, IsNot.not(IsEqual.equalTo(b)));
        MatcherAssert.assertThat(result, IsEqual.equalTo(new ColumnVector(9, 1, 12)));
    }

    @Test
    public void differentSizedVectorsCannotBeAddedTogether() {
        // Arrange:
        final ColumnVector largerVector = new ColumnVector(8);
        final ColumnVector smallerVector = new ColumnVector(7);

        // Act:
        ExceptionAssert.assertThrows(v -> largerVector.addElementWise(smallerVector), IllegalArgumentException.class);
        ExceptionAssert.assertThrows(v -> smallerVector.addElementWise(largerVector), IllegalArgumentException.class);
    }

    //endregion

    //region distance / getMagnitude

    @Test
    public void l1DistanceCanBeCalculatedBetweenTwoVectorsOfSameSize() {
        // Arrange:
        final ColumnVector a = new ColumnVector(7, 5, 11);
        final ColumnVector b = new ColumnVector(2, -4, 1);

        // Act:
        final double distance = a.l1Distance(b);

        // Assert:
        assertEquals(24.0, distance, 0.0000001);
    }

    @Test
    public void l1DistanceCannotBeCalculatedForDifferentSizedVectors() {
        // Arrange:
        final ColumnVector largerVector = new ColumnVector(8);
        final ColumnVector smallerVector = new ColumnVector(7);

        // Act:
        ExceptionAssert.assertThrows(v -> largerVector.l1Distance(smallerVector), IllegalArgumentException.class);
        ExceptionAssert.assertThrows(v -> smallerVector.l1Distance(largerVector), IllegalArgumentException.class);
    }

    @Test
    public void l2DistanceCanBeCalculatedBetweenTwoVectorsOfSameSize() {
        // Arrange:
        final ColumnVector a = new ColumnVector(7, 5, 11);
        final ColumnVector b = new ColumnVector(2, -4, 1);

        // Act:
        final double distance = a.l2Distance(b);

        // Assert:
        assertEquals(14.3527, distance, 0.0000001);
    }

    @Test
    public void l2DistanceCannotBeCalculatedForDifferentSizedVectors() {
        // Arrange:
        final ColumnVector largerVector = new ColumnVector(8);
        final ColumnVector smallerVector = new ColumnVector(7);

        // Act:
        ExceptionAssert.assertThrows(v -> largerVector.l2Distance(smallerVector), IllegalArgumentException.class);
        ExceptionAssert.assertThrows(v -> smallerVector.l2Distance(largerVector), IllegalArgumentException.class);
    }

    @Test
    public void magnitudeCanBeCalculatedForVector() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(7, 5, 11);

        // Act:
        final double magnitude = vector.getMagnitude();

        // Assert:
        assertEquals(13.96424, magnitude, 0.0000001);
    }

    //endregion

    //region correlation

    @Test
    public void correlationCannotBeCalculatedForDifferentSizedVectors() {
        // Arrange:
        final ColumnVector largerVector = new ColumnVector(8);
        final ColumnVector smallerVector = new ColumnVector(7);

        // Act:
        ExceptionAssert.assertThrows(v -> largerVector.correlation(smallerVector), IllegalArgumentException.class);
        ExceptionAssert.assertThrows(v -> smallerVector.correlation(largerVector), IllegalArgumentException.class);
    }

    @Test
    public void correlationCanBeCalculatedForPerfectlyCorrelatedVectors() {
        // Arrange:
        final ColumnVector lhs = new ColumnVector(1, 2, 3, 4, 5);
        final ColumnVector rhs = new ColumnVector(5, 10, 15, 20, 25);

        // Assert:
        MatcherAssert.assertThat(lhs.correlation(lhs), IsEqual.equalTo(1.0));
        MatcherAssert.assertThat(lhs.correlation(rhs), IsEqual.equalTo(1.0));
        MatcherAssert.assertThat(rhs.correlation(lhs), IsEqual.equalTo(1.0));
        MatcherAssert.assertThat(rhs.correlation(rhs), IsEqual.equalTo(1.0));
    }

    @Test
    public void correlationCanBeCalculatedForPerfectlyAntiCorrelatedVectors() {
        // Arrange:
        final ColumnVector lhs = new ColumnVector(5, 4, 3, 2, 1);
        final ColumnVector rhs = new ColumnVector(5, 10, 15, 20, 25);

        // Assert:
        MatcherAssert.assertThat(lhs.correlation(rhs), IsEqual.equalTo(-1.0));
        MatcherAssert.assertThat(rhs.correlation(lhs), IsEqual.equalTo(-1.0));
    }

    @Test
    public void correlationCanBeCalculatedForPartiallyCorrelatedVectors() {
        // Arrange:
        final ColumnVector lhs = new ColumnVector(10.00, 200.0, 7.000, 150.0, 2.000);
        final ColumnVector rhs = new ColumnVector(0.001, 0.450, 0.007, 0.200, 0.300);

        // Assert:
        MatcherAssert.assertThat(lhs.correlation(rhs), IsRoundedEqual.equalTo(0.6877, 4));
        MatcherAssert.assertThat(rhs.correlation(lhs), IsRoundedEqual.equalTo(0.6877, 4));
    }

    //endregion

    //region multiply

    @Test
    public void vectorCanBeMultipliedByScalar() {
        // Arrange:
        final ColumnVector a = new ColumnVector(2, -4, 1);

        // Act:
        final ColumnVector result = a.multiply(8);

        // Assert:
        MatcherAssert.assertThat(result, IsNot.not(IsEqual.equalTo(a)));
        MatcherAssert.assertThat(result, IsEqual.equalTo(new ColumnVector(16, -32, 8)));
    }

    //endregion

    //region multiplyElementWise

    @Test
    public void vectorCanBeMultipliedByVectorElementWise() {
        // Arrange:
        final ColumnVector v1 = new ColumnVector(3, 7, 2);
        final ColumnVector v2 = new ColumnVector(1, 5, 3);

        // Act:
        final ColumnVector result = v1.multiplyElementWise(v2);

        // Assert:
        MatcherAssert.assertThat(result, IsNot.not(IsEqual.equalTo(v1)));
        MatcherAssert.assertThat(result, IsNot.not(IsEqual.equalTo(v2)));
        MatcherAssert.assertThat(result, IsEqual.equalTo(new ColumnVector(3, 35, 6)));
    }

    @Test
    public void vectorCannotBeMultipliedByVectorWithFewerColumns() {
        // Arrange:
        final ColumnVector v1 = new ColumnVector(2);
        final ColumnVector v2 = new ColumnVector(3);

        // Act:
        assertThrows(IllegalArgumentException.class, () -> v1.multiplyElementWise(v2));
    }

    @Test
    public void vectorCannotBeMultipliedByVectorWithMoreColumns() {
        // Arrange:
        final ColumnVector v1 = new ColumnVector(3);
        final ColumnVector v2 = new ColumnVector(2);

        // Act:
        assertThrows(IllegalArgumentException.class, () -> v1.multiplyElementWise(v2));
    }

    //endregion

    //region roundTo / abs / sqrt

    @Test
    public void vectorCanBeRounded() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(0.00024452, -0.123, 0.577);

        // Act:
        final ColumnVector result = vector.roundTo(2);

        // Assert:
        MatcherAssert.assertThat(result, IsNot.not(IsEqual.equalTo(vector)));
        MatcherAssert.assertThat(result, IsEqual.equalTo(new ColumnVector(0.00, -0.12, 0.58)));
    }

    @Test
    public void vectorAbsoluteValueCanBeTaken() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(12.4, -2.1, 7);

        // Act:
        final ColumnVector result = vector.abs();

        // Assert:
        MatcherAssert.assertThat(result, IsNot.not(IsEqual.equalTo(vector)));
        MatcherAssert.assertThat(result, IsEqual.equalTo(new ColumnVector(12.4, 2.1, 7)));
    }

    @Test
    public void vectorCanBeSquareRooted() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(625, 36, 121);

        // Act:
        final ColumnVector result = vector.sqrt();

        // Assert:
        MatcherAssert.assertThat(result, IsNot.not(IsEqual.equalTo(vector)));
        MatcherAssert.assertThat(result, IsEqual.equalTo(new ColumnVector(25.0, 6.0, 11.0)));
    }

    //endregion

    //region isZeroVector

    @Test
    public void isZeroVectorReturnsTrueIfAndOnlyIfAllElementsAreZero() {
        // Assert:
        MatcherAssert.assertThat(new ColumnVector(-3, 2, -5, 7, -1, 8).isZeroVector(), IsEqual.equalTo(false));
        MatcherAssert.assertThat(new ColumnVector(-3, 2, -5, 0, -1, 8).isZeroVector(), IsEqual.equalTo(false));
        MatcherAssert.assertThat(new ColumnVector(0, 0, -1, 1, 0, 0).isZeroVector(), IsEqual.equalTo(false));
        MatcherAssert.assertThat(new ColumnVector(0, 0, -1, 0, 0, 0).isZeroVector(), IsEqual.equalTo(false));
        MatcherAssert.assertThat(new ColumnVector(0, 0, 0, 0, 0, 0).isZeroVector(), IsEqual.equalTo(true));
    }

    //endregion

    //region toString

    @Test
    public void vectorStringRepresentationIsCorrect() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(2.1234, 3.2345, 5012.0126, 11.1234, 1, 8);

        // Assert:
        final String expectedResult = "2.123 3.235 5012.013 11.123 1.000 8.000";

        // Assert:
        MatcherAssert.assertThat(vector.toString(), IsEqual.equalTo(expectedResult));
    }

    //endregion

    //region removeNegatives

    @Test
    public void removeNegativesSetsNegativeValuesToZero() {
        // Arrange:
        final Map<ColumnVector, ColumnVector> testCases = new HashMap<ColumnVector, ColumnVector>() {
            {
                this.put(new ColumnVector(2, -4, 1), new ColumnVector(2, 0, 1));
                this.put(new ColumnVector(-1, 454, 1), new ColumnVector(0, 454, 1));
                this.put(new ColumnVector(2, 343, -131), new ColumnVector(2, 343, 0));
                this.put(new ColumnVector(-2, -343, -131), new ColumnVector(0, 0, 0));
                this.put(new ColumnVector(2, 343, 131), new ColumnVector(2, 343, 131));
            }
        };

        // Act:
        for (final Map.Entry<ColumnVector, ColumnVector> entry : testCases.entrySet()) {
            entry.getKey().removeNegatives();

            // Assert:
            MatcherAssert.assertThat(entry.getKey(), IsEqual.equalTo(entry.getValue()));
        }
    }

    //endregion

    //region equals / hashCode

    @Test
    public void equalsOnlyReturnsTrueForEquivalentObjects() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(2, -4, 1);

        // Assert:
        MatcherAssert.assertThat(new ColumnVector(2, -4, 1), IsEqual.equalTo(vector));
        MatcherAssert.assertThat(new ColumnVector(1, -4, 1), IsNot.not(IsEqual.equalTo(vector)));
        MatcherAssert.assertThat(new ColumnVector(2, 8, 1), IsNot.not(IsEqual.equalTo(vector)));
        MatcherAssert.assertThat(new ColumnVector(2, -4, 2), IsNot.not(IsEqual.equalTo(vector)));
        MatcherAssert.assertThat(null, IsNot.not(IsEqual.equalTo(vector)));
        MatcherAssert.assertThat(new double[]{2, -4, 1}, IsNot.not(IsEqual.equalTo((Object) vector)));
    }

    @Test
    public void hashCodesAreEqualForEquivalentObjects() {
        // Arrange:
        final ColumnVector vector = new ColumnVector(2, -4, 1);
        final int hashCode = vector.hashCode();

        // Assert:
        MatcherAssert.assertThat(new ColumnVector(2, -4, 1).hashCode(), IsEqual.equalTo(hashCode));
        MatcherAssert.assertThat(new ColumnVector(1, -4, 1).hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
        MatcherAssert.assertThat(new ColumnVector(2, 8, 1).hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
        MatcherAssert.assertThat(new ColumnVector(2, -4, 2).hashCode(), IsNot.not(IsEqual.equalTo(hashCode)));
    }

    //endregion
}