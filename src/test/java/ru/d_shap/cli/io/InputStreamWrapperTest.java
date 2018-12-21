///////////////////////////////////////////////////////////////////////////////////////////////////
// CLI tools provide facilities for the command line interface development.
// Copyright (C) 2018 Dmitry Shapovalov.
//
// This file is part of CLI tools.
//
// CLI tools is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// CLI tools is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.cli.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;

/**
 * Tests for {@link InputStreamWrapper}.
 *
 * @author Dmitry Shapovalov
 */
public final class InputStreamWrapperTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public InputStreamWrapperTest() {
        super();
    }

    /**
     * {@link InputStreamWrapper} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void readByteTest() throws IOException {
        byte[] data1 = new byte[]{};
        ByteArrayInputStream bais11 = new ByteArrayInputStream(data1);
        InputStreamWrapper isw11 = new InputStreamWrapper(bais11);
        Assertions.assertThat(isw11.read()).isLessThan(0);
        ByteArrayInputStream bais12 = new ByteArrayInputStream(data1);
        InputStreamWrapper isw12 = new InputStreamWrapper(bais12, null);
        Assertions.assertThat(isw12.read()).isLessThan(0);
        ByteArrayInputStream bais13 = new ByteArrayInputStream(data1);
        ByteArrayOutputStream baos13 = new ByteArrayOutputStream();
        InputStreamWrapper isw13 = new InputStreamWrapper(bais13, baos13);
        Assertions.assertThat(isw13.read()).isLessThan(0);
        Assertions.assertThat(baos13.toByteArray()).containsExactlyInOrder();

        byte[] data2 = new byte[]{5};
        ByteArrayInputStream bais21 = new ByteArrayInputStream(data2);
        InputStreamWrapper isw21 = new InputStreamWrapper(bais21);
        Assertions.assertThat(isw21.read()).isEqualTo(5);
        Assertions.assertThat(isw21.read()).isLessThan(0);
        ByteArrayInputStream bais22 = new ByteArrayInputStream(data2);
        InputStreamWrapper isw22 = new InputStreamWrapper(bais22, null);
        Assertions.assertThat(isw22.read()).isEqualTo(5);
        Assertions.assertThat(isw22.read()).isLessThan(0);
        ByteArrayInputStream bais23 = new ByteArrayInputStream(data2);
        ByteArrayOutputStream baos23 = new ByteArrayOutputStream();
        InputStreamWrapper isw23 = new InputStreamWrapper(bais23, baos23);
        Assertions.assertThat(isw23.read()).isEqualTo(5);
        Assertions.assertThat(baos23.toByteArray()).containsExactlyInOrder(5);
        Assertions.assertThat(isw23.read()).isLessThan(0);
        Assertions.assertThat(baos23.toByteArray()).containsExactlyInOrder(5);

        byte[] data3 = new byte[]{5, 7, 10};
        ByteArrayInputStream bais31 = new ByteArrayInputStream(data3);
        InputStreamWrapper isw31 = new InputStreamWrapper(bais31);
        Assertions.assertThat(isw31.read()).isEqualTo(5);
        Assertions.assertThat(isw31.read()).isEqualTo(7);
        Assertions.assertThat(isw31.read()).isEqualTo(10);
        Assertions.assertThat(isw31.read()).isLessThan(0);
        ByteArrayInputStream bais32 = new ByteArrayInputStream(data3);
        InputStreamWrapper isw32 = new InputStreamWrapper(bais32, null);
        Assertions.assertThat(isw32.read()).isEqualTo(5);
        Assertions.assertThat(isw32.read()).isEqualTo(7);
        Assertions.assertThat(isw32.read()).isEqualTo(10);
        Assertions.assertThat(isw32.read()).isLessThan(0);
        ByteArrayInputStream bais33 = new ByteArrayInputStream(data3);
        ByteArrayOutputStream baos33 = new ByteArrayOutputStream();
        InputStreamWrapper isw33 = new InputStreamWrapper(bais33, baos33);
        Assertions.assertThat(isw33.read()).isEqualTo(5);
        Assertions.assertThat(baos33.toByteArray()).containsExactlyInOrder(5);
        Assertions.assertThat(isw33.read()).isEqualTo(7);
        Assertions.assertThat(baos33.toByteArray()).containsExactlyInOrder(5, 7);
        Assertions.assertThat(isw33.read()).isEqualTo(10);
        Assertions.assertThat(baos33.toByteArray()).containsExactlyInOrder(5, 7, 10);
        Assertions.assertThat(isw33.read()).isLessThan(0);
        Assertions.assertThat(baos33.toByteArray()).containsExactlyInOrder(5, 7, 10);
    }

    /**
     * {@link InputStreamWrapper} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void readByteArrayTest() throws IOException {
        byte[] data1 = new byte[]{};
        ByteArrayInputStream bais11 = new ByteArrayInputStream(data1);
        InputStreamWrapper isw11 = new InputStreamWrapper(bais11);
        byte[] buffer11 = new byte[5];
        Assertions.assertThat(isw11.read(buffer11)).isLessThan(0);
        Assertions.assertThat(buffer11).containsExactlyInOrder(0, 0, 0, 0, 0);
        ByteArrayInputStream bais12 = new ByteArrayInputStream(data1);
        InputStreamWrapper isw12 = new InputStreamWrapper(bais12, null);
        byte[] buffer12 = new byte[5];
        Assertions.assertThat(isw12.read(buffer12)).isLessThan(0);
        Assertions.assertThat(buffer12).containsExactlyInOrder(0, 0, 0, 0, 0);
        ByteArrayInputStream bais13 = new ByteArrayInputStream(data1);
        ByteArrayOutputStream baos13 = new ByteArrayOutputStream();
        InputStreamWrapper isw13 = new InputStreamWrapper(bais13, baos13);
        byte[] buffer13 = new byte[5];
        Assertions.assertThat(isw13.read(buffer13)).isLessThan(0);
        Assertions.assertThat(buffer13).containsExactlyInOrder(0, 0, 0, 0, 0);
        Assertions.assertThat(baos13.toByteArray()).containsExactlyInOrder();

        byte[] data2 = new byte[]{5};
        ByteArrayInputStream bais21 = new ByteArrayInputStream(data2);
        InputStreamWrapper isw21 = new InputStreamWrapper(bais21);
        byte[] buffer21 = new byte[5];
        Assertions.assertThat(isw21.read(buffer21)).isEqualTo(1);
        Assertions.assertThat(buffer21).containsExactlyInOrder(5, 0, 0, 0, 0);
        ByteArrayInputStream bais22 = new ByteArrayInputStream(data2);
        InputStreamWrapper isw22 = new InputStreamWrapper(bais22, null);
        byte[] buffer22 = new byte[5];
        Assertions.assertThat(isw22.read(buffer22)).isEqualTo(1);
        Assertions.assertThat(buffer22).containsExactlyInOrder(5, 0, 0, 0, 0);
        ByteArrayInputStream bais23 = new ByteArrayInputStream(data2);
        ByteArrayOutputStream baos23 = new ByteArrayOutputStream();
        InputStreamWrapper isw23 = new InputStreamWrapper(bais23, baos23);
        byte[] buffer23 = new byte[5];
        Assertions.assertThat(isw23.read(buffer23)).isEqualTo(1);
        Assertions.assertThat(buffer23).containsExactlyInOrder(5, 0, 0, 0, 0);
        Assertions.assertThat(baos23.toByteArray()).containsExactlyInOrder(5);

        byte[] data3 = new byte[]{5, 7, 10};
        ByteArrayInputStream bais31 = new ByteArrayInputStream(data3);
        InputStreamWrapper isw31 = new InputStreamWrapper(bais31);
        byte[] buffer31 = new byte[5];
        Assertions.assertThat(isw31.read(buffer31)).isEqualTo(3);
        Assertions.assertThat(buffer31).containsExactlyInOrder(5, 7, 10, 0, 0);
        ByteArrayInputStream bais32 = new ByteArrayInputStream(data3);
        InputStreamWrapper isw32 = new InputStreamWrapper(bais32, null);
        byte[] buffer32 = new byte[5];
        Assertions.assertThat(isw32.read(buffer32)).isEqualTo(3);
        Assertions.assertThat(buffer32).containsExactlyInOrder(5, 7, 10, 0, 0);
        ByteArrayInputStream bais33 = new ByteArrayInputStream(data3);
        ByteArrayOutputStream baos33 = new ByteArrayOutputStream();
        InputStreamWrapper isw33 = new InputStreamWrapper(bais33, baos33);
        byte[] buffer33 = new byte[5];
        Assertions.assertThat(isw33.read(buffer33)).isEqualTo(3);
        Assertions.assertThat(buffer33).containsExactlyInOrder(5, 7, 10, 0, 0);
        Assertions.assertThat(baos33.toByteArray()).containsExactlyInOrder(5, 7, 10);
    }

    /**
     * {@link InputStreamWrapper} class test.
     */
    @Test
    public void readByteArrayWithOffsetTest() {

    }

    /**
     * {@link InputStreamWrapper} class test.
     */
    @Test
    public void skipTest() {

    }

    /**
     * {@link InputStreamWrapper} class test.
     */
    @Test
    public void availableTest() {

    }

    /**
     * {@link InputStreamWrapper} class test.
     */
    @Test
    public void closeTest() {

    }

    /**
     * {@link InputStreamWrapper} class test.
     */
    @Test
    public void markSupportedTest() {

    }

}
