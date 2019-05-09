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

import io.proximax.core.math.DenseMatrix;
import io.proximax.core.math.Matrix;
import io.proximax.core.test.ExceptionAssert;
import io.proximax.core.test.IsEquivalent;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsSame;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DenseMatrixTest extends MatrixTest<DenseMatrix> {

    //region constructor

    @Test
    public void matrixCanBeInitializedWithDefaultValues() {
        // Arrange:
        final double[] values = new double[]{1, 4, 5, 7, 2, 3};
        final DenseMatrix matrix = new DenseMatrix(2, 3, values);

        // Assert:
        Assert.assertThat(matrix.getRowCount(), IsEqual.equalTo(2));
        Assert.assertThat(matrix.getColumnCount(), IsEqual.equalTo(3));
        Assert.assertThat(matrix.getElementCount(), IsEqual.equalTo(6));
        Assert.assertThat(matrix.getRaw(), IsSame.sameInstance(values));
        Assert.assertThat(matrix.getAt(0, 0), IsEqual.equalTo(1.0));
        Assert.assertThat(matrix.getAt(0, 1), IsEqual.equalTo(4.0));
        Assert.assertThat(matrix.getAt(0, 2), IsEqual.equalTo(5.0));
        Assert.assertThat(matrix.getAt(1, 0), IsEqual.equalTo(7.0));
        Assert.assertThat(matrix.getAt(1, 1), IsEqual.equalTo(2.0));
        Assert.assertThat(matrix.getAt(1, 2), IsEqual.equalTo(3.0));
    }

    @Test
    public void matrixCannotBeInitializedWithIncompleteDefaultValues() {
        // Assert:
        ExceptionAssert.assertThrows(
                v -> new DenseMatrix(2, 3, new double[]{1, 4, 5, 7, 2}),
                IllegalArgumentException.class);
    }

    //endregion

    //region forEach

    @Test
    public void forEachReturnsAllElements() {
        // Arrange:
        final Matrix matrix = this.createMatrix(3, 2, new double[]{2, 0, 0, 1, -5, 8});

        // Act:
        final List<Double> values = new ArrayList<>();
        matrix.forEach((row, col, value) -> values.add(value));

        // Assert:
        Assert.assertThat(values, IsEquivalent.equivalentTo(2.0, 0.0, 0.0, 1.0, -5.0, 8.0));
    }

    //endregion

    //region getRaw

    @Test
    public void rawVectorIsAccessible() {
        // Arrange:
        final DenseMatrix matrix = this.createMatrix(3, 2, new double[]{9.0, 3.2, 5.4, 1.2, 4.3, 7.6});

        // Act:
        final boolean areEqual = Arrays.equals(matrix.getRaw(), new double[]{9.0, 3.2, 5.4, 1.2, 4.3, 7.6});

        // Assert:
        Assert.assertThat(areEqual, IsEqual.equalTo(true));
    }

    @Test
    public void rawVectorIsMutable() {
        // Arrange:
        final DenseMatrix matrix = this.createMatrix(3, 2, new double[]{9.0, 3.2, 5.4, 1.2, 4.3, 7.6});

        // Act:
        matrix.setAt(0, 1, 12.1);
        final boolean areEqual = Arrays.equals(matrix.getRaw(), new double[]{9.0, 12.1, 5.4, 1.2, 4.3, 7.6});

        // Assert:
        Assert.assertThat(areEqual, IsEqual.equalTo(true));
    }

    //endregion

    //region toString

    @Test
    public void denseMatrixStringRepresentationIsCorrect() {
        // Arrange:
        final Matrix matrix = this.createMatrix(3, 2, new double[]{
                2.1234, 11.1234, 3.2345, 1, 5012.0126, 8
        });

        // Assert:
        final String expectedResult =
                "2.123 11.123" + System.lineSeparator() +
                        "3.235 1.000" + System.lineSeparator() +
                        "5012.013 8.000";
        Assert.assertThat(matrix.toString(), IsEqual.equalTo(expectedResult));
    }

    //endregion

    @Override
    protected DenseMatrix createMatrix(final int rows, final int cols) {
        return new DenseMatrix(rows, cols);
    }
}