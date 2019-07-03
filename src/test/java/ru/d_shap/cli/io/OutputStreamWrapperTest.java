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
import java.io.OutputStream;

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
     * @throws Exception exception in test.
     */
    @Test
    public void writeByteTest() throws Exception {
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        OutputStream osw1 = new OutputStreamWrapper(baos1);
        osw1.write(5);
        Assertions.assertThat(baos1.toByteArray()).containsExactlyInOrder(5);
        osw1.write(7);
        Assertions.assertThat(baos1.toByteArray()).containsExactlyInOrder(5, 7);
        osw1.write(10);
        Assertions.assertThat(baos1.toByteArray()).containsExactlyInOrder(5, 7, 10);

        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        OutputStream osw2 = new OutputStreamWrapper(baos2, null);
        osw2.write(5);
        Assertions.assertThat(baos2.toByteArray()).containsExactlyInOrder(5);
        osw2.write(7);
        Assertions.assertThat(baos2.toByteArray()).containsExactlyInOrder(5, 7);
        osw2.write(10);
        Assertions.assertThat(baos2.toByteArray()).containsExactlyInOrder(5, 7, 10);

        ByteArrayOutputStream baos31 = new ByteArrayOutputStream();
        ByteArrayOutputStream baos32 = new ByteArrayOutputStream();
        OutputStream osw3 = new OutputStreamWrapper(baos31, baos32);
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
     * @throws Exception exception in test.
     */
    @Test
    public void writeByteArrayTest() throws Exception {
        byte[] data1 = new byte[]{5, 7, 10};
        byte[] data2 = new byte[]{1, 6, 3};

        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        OutputStream osw1 = new OutputStreamWrapper(baos1);
        osw1.write(data1);
        Assertions.assertThat(baos1.toByteArray()).containsExactlyInOrder(5, 7, 10);
        osw1.write(data2);
        Assertions.assertThat(baos1.toByteArray()).containsExactlyInOrder(5, 7, 10, 1, 6, 3);

        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        OutputStream osw2 = new OutputStreamWrapper(baos2, null);
        osw2.write(data1);
        Assertions.assertThat(baos2.toByteArray()).containsExactlyInOrder(5, 7, 10);
        osw2.write(data2);
        Assertions.assertThat(baos2.toByteArray()).containsExactlyInOrder(5, 7, 10, 1, 6, 3);

        ByteArrayOutputStream baos31 = new ByteArrayOutputStream();
        ByteArrayOutputStream baos32 = new ByteArrayOutputStream();
        OutputStream osw3 = new OutputStreamWrapper(baos31, baos32);
        osw3.write(data1);
        Assertions.assertThat(baos31.toByteArray()).containsExactlyInOrder(5, 7, 10);
        Assertions.assertThat(baos32.toByteArray()).containsExactlyInOrder(5, 7, 10);
        osw3.write(data2);
        Assertions.assertThat(baos31.toByteArray()).containsExactlyInOrder(5, 7, 10, 1, 6, 3);
        Assertions.assertThat(baos32.toByteArray()).containsExactlyInOrder(5, 7, 10, 1, 6, 3);
    }

    /**
     * {@link OutputStreamWrapper} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void writeByteArrayWithOffsetTest() throws Exception {
        byte[] data = new byte[]{5, 7, 10, 1, 6, 3, 9, 2, 4, 8};

        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        OutputStream osw1 = new OutputStreamWrapper(baos1);
        osw1.write(data, 1, 4);
        Assertions.assertThat(baos1.toByteArray()).containsExactlyInOrder(7, 10, 1, 6);
        osw1.write(data, 0, 2);
        Assertions.assertThat(baos1.toByteArray()).containsExactlyInOrder(7, 10, 1, 6, 5, 7);

        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        OutputStream osw2 = new OutputStreamWrapper(baos2, null);
        osw2.write(data, 1, 4);
        Assertions.assertThat(baos2.toByteArray()).containsExactlyInOrder(7, 10, 1, 6);
        osw2.write(data, 0, 2);
        Assertions.assertThat(baos2.toByteArray()).containsExactlyInOrder(7, 10, 1, 6, 5, 7);

        ByteArrayOutputStream baos31 = new ByteArrayOutputStream();
        ByteArrayOutputStream baos32 = new ByteArrayOutputStream();
        OutputStream osw3 = new OutputStreamWrapper(baos31, baos32);
        osw3.write(data, 1, 4);
        Assertions.assertThat(baos31.toByteArray()).containsExactlyInOrder(7, 10, 1, 6);
        Assertions.assertThat(baos32.toByteArray()).containsExactlyInOrder(7, 10, 1, 6);
        osw3.write(data, 0, 2);
        Assertions.assertThat(baos31.toByteArray()).containsExactlyInOrder(7, 10, 1, 6, 5, 7);
        Assertions.assertThat(baos32.toByteArray()).containsExactlyInOrder(7, 10, 1, 6, 5, 7);
    }

    /**
     * {@link OutputStreamWrapper} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void flushTest() throws Exception {
        FlushedOutputStream fos1 = new FlushedOutputStream();
        OutputStream osw1 = new OutputStreamWrapper(fos1);
        Assertions.assertThat(fos1.isFlushed()).isFalse();
        osw1.flush();
        Assertions.assertThat(fos1.isFlushed()).isTrue();

        FlushedOutputStream fos2 = new FlushedOutputStream();
        OutputStream osw2 = new OutputStreamWrapper(fos2, null);
        Assertions.assertThat(fos2.isFlushed()).isFalse();
        osw2.flush();
        Assertions.assertThat(fos2.isFlushed()).isTrue();

        FlushedOutputStream fos31 = new FlushedOutputStream();
        FlushedOutputStream fos32 = new FlushedOutputStream();
        OutputStream osw3 = new OutputStreamWrapper(fos31, fos32);
        Assertions.assertThat(fos31.isFlushed()).isFalse();
        Assertions.assertThat(fos32.isFlushed()).isFalse();
        osw3.flush();
        Assertions.assertThat(fos31.isFlushed()).isTrue();
        Assertions.assertThat(fos32.isFlushed()).isTrue();
    }

    /**
     * {@link OutputStreamWrapper} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void closeTest() throws Exception {
        ClosedOutputStream cos1 = new ClosedOutputStream();
        OutputStream osw1 = new OutputStreamWrapper(cos1);
        Assertions.assertThat(cos1.isClosed()).isFalse();
        osw1.close();
        Assertions.assertThat(cos1.isClosed()).isTrue();

        ClosedOutputStream cos2 = new ClosedOutputStream();
        OutputStream osw2 = new OutputStreamWrapper(cos2, null);
        Assertions.assertThat(cos2.isClosed()).isFalse();
        osw2.close();
        Assertions.assertThat(cos2.isClosed()).isTrue();

        ClosedOutputStream cos31 = new ClosedOutputStream();
        ClosedOutputStream cos32 = new ClosedOutputStream();
        OutputStream osw3 = new OutputStreamWrapper(cos31, cos32);
        Assertions.assertThat(cos31.isClosed()).isFalse();
        Assertions.assertThat(cos32.isClosed()).isFalse();
        osw3.close();
        Assertions.assertThat(cos31.isClosed()).isTrue();
        Assertions.assertThat(cos32.isClosed()).isTrue();

        OutputStream focos4 = new FailOnCloseOutputStream("main");
        OutputStream osw4 = new OutputStreamWrapper(focos4);
        try {
            osw4.close();
            Assertions.fail("OutputStreamWrapper test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("main");
        }

        OutputStream focos5 = new FailOnCloseOutputStream("main");
        OutputStream osw5 = new OutputStreamWrapper(focos5, null);
        try {
            osw5.close();
            Assertions.fail("OutputStreamWrapper test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("main");
        }

        OutputStream focos6 = new FailOnCloseOutputStream("main");
        OutputStream baos6 = new ByteArrayOutputStream();
        OutputStream osw6 = new OutputStreamWrapper(focos6, baos6);
        try {
            osw6.close();
            Assertions.fail("OutputStreamWrapper test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("main");
        }

        OutputStream baos7 = new ByteArrayOutputStream();
        OutputStream focos7 = new FailOnCloseOutputStream("log");
        OutputStream osw7 = new OutputStreamWrapper(baos7, focos7);
        try {
            osw7.close();
            Assertions.fail("OutputStreamWrapper test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("log");
        }

        OutputStream focos81 = new FailOnCloseOutputStream("main");
        OutputStream focos82 = new FailOnCloseOutputStream("log");
        OutputStream osw8 = new OutputStreamWrapper(focos81, focos82);
        try {
            osw8.close();
            Assertions.fail("OutputStreamWrapper test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("main");
        }
    }

}
