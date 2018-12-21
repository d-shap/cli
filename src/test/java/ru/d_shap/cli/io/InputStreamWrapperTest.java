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
        byte[] buffer1 = new byte[]{};
        ByteArrayInputStream bais11 = new ByteArrayInputStream(buffer1);
        InputStreamWrapper isw11 = new InputStreamWrapper(bais11);
        Assertions.assertThat(isw11.read()).isLessThan(0);
        ByteArrayInputStream bais12 = new ByteArrayInputStream(buffer1);
        InputStreamWrapper isw12 = new InputStreamWrapper(bais12, null);
        Assertions.assertThat(isw12.read()).isLessThan(0);
        ByteArrayInputStream bais13 = new ByteArrayInputStream(buffer1);
        ByteArrayOutputStream baos13 = new ByteArrayOutputStream();
        InputStreamWrapper isw13 = new InputStreamWrapper(bais13, baos13);
        Assertions.assertThat(isw13.read()).isLessThan(0);
        Assertions.assertThat(baos13.toByteArray()).containsExactlyInOrder();

        byte[] buffer2 = new byte[]{5};
        ByteArrayInputStream bais21 = new ByteArrayInputStream(buffer2);
        InputStreamWrapper isw21 = new InputStreamWrapper(bais21);
        Assertions.assertThat(isw21.read()).isEqualTo(5);
        Assertions.assertThat(isw21.read()).isLessThan(0);
        ByteArrayInputStream bais22 = new ByteArrayInputStream(buffer2);
        InputStreamWrapper isw22 = new InputStreamWrapper(bais22, null);
        Assertions.assertThat(isw22.read()).isEqualTo(5);
        Assertions.assertThat(isw22.read()).isLessThan(0);
        ByteArrayInputStream bais23 = new ByteArrayInputStream(buffer2);
        ByteArrayOutputStream baos23 = new ByteArrayOutputStream();
        InputStreamWrapper isw23 = new InputStreamWrapper(bais23, baos23);
        Assertions.assertThat(isw23.read()).isEqualTo(5);
        Assertions.assertThat(baos23.toByteArray()).containsExactlyInOrder(5);
        Assertions.assertThat(isw23.read()).isLessThan(0);
        Assertions.assertThat(baos23.toByteArray()).containsExactlyInOrder(5);

        byte[] buffer3 = new byte[]{5, 7, 10};
        ByteArrayInputStream bais31 = new ByteArrayInputStream(buffer3);
        InputStreamWrapper isw31 = new InputStreamWrapper(bais31);
        Assertions.assertThat(isw31.read()).isEqualTo(5);
        Assertions.assertThat(isw31.read()).isEqualTo(7);
        Assertions.assertThat(isw31.read()).isEqualTo(10);
        Assertions.assertThat(isw31.read()).isLessThan(0);
        ByteArrayInputStream bais32 = new ByteArrayInputStream(buffer3);
        InputStreamWrapper isw32 = new InputStreamWrapper(bais32, null);
        Assertions.assertThat(isw32.read()).isEqualTo(5);
        Assertions.assertThat(isw32.read()).isEqualTo(7);
        Assertions.assertThat(isw32.read()).isEqualTo(10);
        Assertions.assertThat(isw32.read()).isLessThan(0);
        ByteArrayInputStream bais33 = new ByteArrayInputStream(buffer3);
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
     */
    @Test
    public void readByteArrayTest() {

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
