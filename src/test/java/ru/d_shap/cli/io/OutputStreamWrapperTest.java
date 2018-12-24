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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;

/**
 * Tests for {@link OutputStreamWrapper}.
 *
 * @author Dmitry Shapovalov
 */
public final class OutputStreamWrapperTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public OutputStreamWrapperTest() {
        super();
    }

    /**
     * {@link OutputStreamWrapper} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void writeByteTest() throws IOException {
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        OutputStreamWrapper osw1 = new OutputStreamWrapper(baos1);
        osw1.write(5);
        Assertions.assertThat(baos1.toByteArray()).containsExactlyInOrder(5);
        osw1.write(7);
        Assertions.assertThat(baos1.toByteArray()).containsExactlyInOrder(5, 7);
        osw1.write(10);
        Assertions.assertThat(baos1.toByteArray()).containsExactlyInOrder(5, 7, 10);

        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        OutputStreamWrapper osw2 = new OutputStreamWrapper(baos2, null);
        osw2.write(5);
        Assertions.assertThat(baos2.toByteArray()).containsExactlyInOrder(5);
        osw2.write(7);
        Assertions.assertThat(baos2.toByteArray()).containsExactlyInOrder(5, 7);
        osw2.write(10);
        Assertions.assertThat(baos2.toByteArray()).containsExactlyInOrder(5, 7, 10);

        ByteArrayOutputStream baos31 = new ByteArrayOutputStream();
        ByteArrayOutputStream baos32 = new ByteArrayOutputStream();
        OutputStreamWrapper osw3 = new OutputStreamWrapper(baos31, baos32);
        osw3.write(5);
        Assertions.assertThat(baos31.toByteArray()).containsExactlyInOrder(5);
        Assertions.assertThat(baos32.toByteArray()).containsExactlyInOrder(5);
        osw3.write(7);
        Assertions.assertThat(baos31.toByteArray()).containsExactlyInOrder(5, 7);
        Assertions.assertThat(baos32.toByteArray()).containsExactlyInOrder(5, 7);
        osw3.write(10);
        Assertions.assertThat(baos31.toByteArray()).containsExactlyInOrder(5, 7, 10);
        Assertions.assertThat(baos32.toByteArray()).containsExactlyInOrder(5, 7, 10);
    }

    /**
     * {@link OutputStreamWrapper} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void writeByteArrayTest() throws IOException {
        byte[] data = new byte[]{5, 7, 10};

        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        OutputStreamWrapper osw1 = new OutputStreamWrapper(baos1);
        osw1.write(data);
        Assertions.assertThat(baos1.toByteArray()).containsExactlyInOrder(5, 7, 10);

        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        OutputStreamWrapper osw2 = new OutputStreamWrapper(baos2, null);
        osw2.write(data);
        Assertions.assertThat(baos2.toByteArray()).containsExactlyInOrder(5, 7, 10);

        ByteArrayOutputStream baos31 = new ByteArrayOutputStream();
        ByteArrayOutputStream baos32 = new ByteArrayOutputStream();
        OutputStreamWrapper osw3 = new OutputStreamWrapper(baos31, baos32);
        osw3.write(data);
        Assertions.assertThat(baos31.toByteArray()).containsExactlyInOrder(5, 7, 10);
        Assertions.assertThat(baos32.toByteArray()).containsExactlyInOrder(5, 7, 10);
    }

    /**
     * {@link OutputStreamWrapper} class test.
     */
    @Test
    public void writeByteArrayWithOffsetTest() {

    }

    /**
     * {@link OutputStreamWrapper} class test.
     */
    @Test
    public void flushTest() {

    }

    /**
     * {@link OutputStreamWrapper} class test.
     */
    @Test
    public void closeTest() {

    }

}
