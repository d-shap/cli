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
import java.io.InputStream;
import java.io.OutputStream;

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
     * @throws Exception exception in test.
     */
    @Test
    public void readByteTest() throws Exception {
        byte[] data1 = new byte[]{};
        InputStream bais11 = new ByteArrayInputStream(data1);
        InputStream isw11 = new InputStreamWrapper(bais11);
        Assertions.assertThat(isw11).isCompleted();
        InputStream bais12 = new ByteArrayInputStream(data1);
        InputStream isw12 = new InputStreamWrapper(bais12, null);
        Assertions.assertThat(isw12).isCompleted();
        InputStream bais13 = new ByteArrayInputStream(data1);
        ByteArrayOutputStream baos13 = new ByteArrayOutputStream();
        InputStream isw13 = new InputStreamWrapper(bais13, baos13);
        Assertions.assertThat(isw13).isCompleted();
        Assertions.assertThat(baos13.toByteArray()).containsExactlyInOrder();

        byte[] data2 = new byte[]{5};
        InputStream bais21 = new ByteArrayInputStream(data2);
        InputStream isw21 = new InputStreamWrapper(bais21);
        Assertions.assertThat(isw21.read()).isEqualTo(5);
        Assertions.assertThat(isw21).isCompleted();
        InputStream bais22 = new ByteArrayInputStream(data2);
        InputStream isw22 = new InputStreamWrapper(bais22, null);
        Assertions.assertThat(isw22.read()).isEqualTo(5);
        Assertions.assertThat(isw22).isCompleted();
        InputStream bais23 = new ByteArrayInputStream(data2);
        ByteArrayOutputStream baos23 = new ByteArrayOutputStream();
        InputStream isw23 = new InputStreamWrapper(bais23, baos23);
        Assertions.assertThat(isw23.read()).isEqualTo(5);
        Assertions.assertThat(baos23.toByteArray()).containsExactlyInOrder(5);
        Assertions.assertThat(isw23).isCompleted();
        Assertions.assertThat(baos23.toByteArray()).containsExactlyInOrder(5);

        byte[] data3 = new byte[]{5, 7, 10};
        InputStream bais31 = new ByteArrayInputStream(data3);
        InputStream isw31 = new InputStreamWrapper(bais31);
        Assertions.assertThat(isw31.read()).isEqualTo(5);
        Assertions.assertThat(isw31.read()).isEqualTo(7);
        Assertions.assertThat(isw31.read()).isEqualTo(10);
        Assertions.assertThat(isw31).isCompleted();
        InputStream bais32 = new ByteArrayInputStream(data3);
        InputStream isw32 = new InputStreamWrapper(bais32, null);
        Assertions.assertThat(isw32.read()).isEqualTo(5);
        Assertions.assertThat(isw32.read()).isEqualTo(7);
        Assertions.assertThat(isw32.read()).isEqualTo(10);
        Assertions.assertThat(isw32).isCompleted();
        InputStream bais33 = new ByteArrayInputStream(data3);
        ByteArrayOutputStream baos33 = new ByteArrayOutputStream();
        InputStream isw33 = new InputStreamWrapper(bais33, baos33);
        Assertions.assertThat(isw33.read()).isEqualTo(5);
        Assertions.assertThat(baos33.toByteArray()).containsExactlyInOrder(5);
        Assertions.assertThat(isw33.read()).isEqualTo(7);
        Assertions.assertThat(baos33.toByteArray()).containsExactlyInOrder(5, 7);
        Assertions.assertThat(isw33.read()).isEqualTo(10);
        Assertions.assertThat(baos33.toByteArray()).containsExactlyInOrder(5, 7, 10);
        Assertions.assertThat(isw33).isCompleted();
        Assertions.assertThat(baos33.toByteArray()).containsExactlyInOrder(5, 7, 10);

        byte[] data4 = new byte[]{0, 0, 0};
        InputStream bais41 = new ByteArrayInputStream(data4);
        InputStream isw41 = new InputStreamWrapper(bais41);
        Assertions.assertThat(isw41.read()).isEqualTo(0);
        Assertions.assertThat(isw41.read()).isEqualTo(0);
        Assertions.assertThat(isw41.read()).isEqualTo(0);
        Assertions.assertThat(isw41).isCompleted();
        InputStream bais42 = new ByteArrayInputStream(data4);
        InputStream isw42 = new InputStreamWrapper(bais42, null);
        Assertions.assertThat(isw42.read()).isEqualTo(0);
        Assertions.assertThat(isw42.read()).isEqualTo(0);
        Assertions.assertThat(isw42.read()).isEqualTo(0);
        Assertions.assertThat(isw42).isCompleted();
        InputStream bais43 = new ByteArrayInputStream(data4);
        ByteArrayOutputStream baos43 = new ByteArrayOutputStream();
        InputStream isw43 = new InputStreamWrapper(bais43, baos43);
        Assertions.assertThat(isw43.read()).isEqualTo(0);
        Assertions.assertThat(baos43.toByteArray()).containsExactlyInOrder(0);
        Assertions.assertThat(isw43.read()).isEqualTo(0);
        Assertions.assertThat(baos43.toByteArray()).containsExactlyInOrder(0, 0);
        Assertions.assertThat(isw43.read()).isEqualTo(0);
        Assertions.assertThat(baos43.toByteArray()).containsExactlyInOrder(0, 0, 0);
        Assertions.assertThat(isw43).isCompleted();
        Assertions.assertThat(baos43.toByteArray()).containsExactlyInOrder(0, 0, 0);
    }

    /**
     * {@link InputStreamWrapper} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void readByteArrayTest() throws Exception {
        byte[] data1 = new byte[]{};
        InputStream bais11 = new ByteArrayInputStream(data1);
        InputStream isw11 = new InputStreamWrapper(bais11);
        byte[] buffer11 = new byte[5];
        Assertions.assertThat(isw11.read(buffer11)).isLessThan(0);
        Assertions.assertThat(buffer11).containsExactlyInOrder(0, 0, 0, 0, 0);
        InputStream bais12 = new ByteArrayInputStream(data1);
        InputStream isw12 = new InputStreamWrapper(bais12, null);
        byte[] buffer12 = new byte[5];
        Assertions.assertThat(isw12.read(buffer12)).isLessThan(0);
        Assertions.assertThat(buffer12).containsExactlyInOrder(0, 0, 0, 0, 0);
        InputStream bais13 = new ByteArrayInputStream(data1);
        ByteArrayOutputStream baos13 = new ByteArrayOutputStream();
        InputStream isw13 = new InputStreamWrapper(bais13, baos13);
        byte[] buffer13 = new byte[5];
        Assertions.assertThat(isw13.read(buffer13)).isLessThan(0);
        Assertions.assertThat(buffer13).containsExactlyInOrder(0, 0, 0, 0, 0);
        Assertions.assertThat(baos13.toByteArray()).containsExactlyInOrder();

        byte[] data2 = new byte[]{5};
        InputStream bais21 = new ByteArrayInputStream(data2);
        InputStream isw21 = new InputStreamWrapper(bais21);
        byte[] buffer21 = new byte[5];
        Assertions.assertThat(isw21.read(buffer21)).isEqualTo(1);
        Assertions.assertThat(buffer21).containsExactlyInOrder(5, 0, 0, 0, 0);
        InputStream bais22 = new ByteArrayInputStream(data2);
        InputStream isw22 = new InputStreamWrapper(bais22, null);
        byte[] buffer22 = new byte[5];
        Assertions.assertThat(isw22.read(buffer22)).isEqualTo(1);
        Assertions.assertThat(buffer22).containsExactlyInOrder(5, 0, 0, 0, 0);
        InputStream bais23 = new ByteArrayInputStream(data2);
        ByteArrayOutputStream baos23 = new ByteArrayOutputStream();
        InputStream isw23 = new InputStreamWrapper(bais23, baos23);
        byte[] buffer23 = new byte[5];
        Assertions.assertThat(isw23.read(buffer23)).isEqualTo(1);
        Assertions.assertThat(buffer23).containsExactlyInOrder(5, 0, 0, 0, 0);
        Assertions.assertThat(baos23.toByteArray()).containsExactlyInOrder(5);

        byte[] data3 = new byte[]{5, 7, 10};
        InputStream bais31 = new ByteArrayInputStream(data3);
        InputStream isw31 = new InputStreamWrapper(bais31);
        byte[] buffer31 = new byte[5];
        Assertions.assertThat(isw31.read(buffer31)).isEqualTo(3);
        Assertions.assertThat(buffer31).containsExactlyInOrder(5, 7, 10, 0, 0);
        InputStream bais32 = new ByteArrayInputStream(data3);
        InputStream isw32 = new InputStreamWrapper(bais32, null);
        byte[] buffer32 = new byte[5];
        Assertions.assertThat(isw32.read(buffer32)).isEqualTo(3);
        Assertions.assertThat(buffer32).containsExactlyInOrder(5, 7, 10, 0, 0);
        InputStream bais33 = new ByteArrayInputStream(data3);
        ByteArrayOutputStream baos33 = new ByteArrayOutputStream();
        InputStream isw33 = new InputStreamWrapper(bais33, baos33);
        byte[] buffer33 = new byte[5];
        Assertions.assertThat(isw33.read(buffer33)).isEqualTo(3);
        Assertions.assertThat(buffer33).containsExactlyInOrder(5, 7, 10, 0, 0);
        Assertions.assertThat(baos33.toByteArray()).containsExactlyInOrder(5, 7, 10);
    }

    /**
     * {@link InputStreamWrapper} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void readByteArrayWithOffsetTest() throws Exception {
        byte[] data1 = new byte[]{};
        InputStream bais11 = new ByteArrayInputStream(data1);
        InputStream isw11 = new InputStreamWrapper(bais11);
        byte[] buffer11 = new byte[5];
        Assertions.assertThat(isw11.read(buffer11, 1, 4)).isLessThan(0);
        Assertions.assertThat(buffer11).containsExactlyInOrder(0, 0, 0, 0, 0);
        InputStream bais12 = new ByteArrayInputStream(data1);
        InputStream isw12 = new InputStreamWrapper(bais12, null);
        byte[] buffer12 = new byte[5];
        Assertions.assertThat(isw12.read(buffer12, 1, 4)).isLessThan(0);
        Assertions.assertThat(buffer12).containsExactlyInOrder(0, 0, 0, 0, 0);
        InputStream bais13 = new ByteArrayInputStream(data1);
        ByteArrayOutputStream baos13 = new ByteArrayOutputStream();
        InputStream isw13 = new InputStreamWrapper(bais13, baos13);
        byte[] buffer13 = new byte[5];
        Assertions.assertThat(isw13.read(buffer13, 1, 4)).isLessThan(0);
        Assertions.assertThat(buffer13).containsExactlyInOrder(0, 0, 0, 0, 0);
        Assertions.assertThat(baos13.toByteArray()).containsExactlyInOrder();

        byte[] data2 = new byte[]{5};
        InputStream bais21 = new ByteArrayInputStream(data2);
        InputStream isw21 = new InputStreamWrapper(bais21);
        byte[] buffer21 = new byte[5];
        Assertions.assertThat(isw21.read(buffer21, 1, 4)).isEqualTo(1);
        Assertions.assertThat(buffer21).containsExactlyInOrder(0, 5, 0, 0, 0);
        InputStream bais22 = new ByteArrayInputStream(data2);
        InputStream isw22 = new InputStreamWrapper(bais22, null);
        byte[] buffer22 = new byte[5];
        Assertions.assertThat(isw22.read(buffer22, 1, 4)).isEqualTo(1);
        Assertions.assertThat(buffer22).containsExactlyInOrder(0, 5, 0, 0, 0);
        InputStream bais23 = new ByteArrayInputStream(data2);
        ByteArrayOutputStream baos23 = new ByteArrayOutputStream();
        InputStream isw23 = new InputStreamWrapper(bais23, baos23);
        byte[] buffer23 = new byte[5];
        Assertions.assertThat(isw23.read(buffer23, 1, 4)).isEqualTo(1);
        Assertions.assertThat(buffer23).containsExactlyInOrder(0, 5, 0, 0, 0);
        Assertions.assertThat(baos23.toByteArray()).containsExactlyInOrder(5);

        byte[] data3 = new byte[]{5, 7, 10};
        InputStream bais31 = new ByteArrayInputStream(data3);
        InputStream isw31 = new InputStreamWrapper(bais31);
        byte[] buffer31 = new byte[5];
        Assertions.assertThat(isw31.read(buffer31, 1, 4)).isEqualTo(3);
        Assertions.assertThat(buffer31).containsExactlyInOrder(0, 5, 7, 10, 0);
        InputStream bais32 = new ByteArrayInputStream(data3);
        InputStream isw32 = new InputStreamWrapper(bais32, null);
        byte[] buffer32 = new byte[5];
        Assertions.assertThat(isw32.read(buffer32, 1, 4)).isEqualTo(3);
        Assertions.assertThat(buffer32).containsExactlyInOrder(0, 5, 7, 10, 0);
        InputStream bais33 = new ByteArrayInputStream(data3);
        ByteArrayOutputStream baos33 = new ByteArrayOutputStream();
        InputStream isw33 = new InputStreamWrapper(bais33, baos33);
        byte[] buffer33 = new byte[5];
        Assertions.assertThat(isw33.read(buffer33, 1, 4)).isEqualTo(3);
        Assertions.assertThat(buffer33).containsExactlyInOrder(0, 5, 7, 10, 0);
        Assertions.assertThat(baos33.toByteArray()).containsExactlyInOrder(5, 7, 10);
    }

    /**
     * {@link InputStreamWrapper} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void skipTest() throws Exception {
        byte[] data = new byte[]{5, 7, 10, 1, 6, 3, 9, 2, 4, 8};

        InputStream bais1 = new ByteArrayInputStream(data);
        InputStream isw1 = new InputStreamWrapper(bais1);
        Assertions.assertThat(isw1.read()).isEqualTo(5);
        Assertions.assertThat(isw1.read()).isEqualTo(7);
        Assertions.assertThat(isw1.read()).isEqualTo(10);
        Assertions.assertThat(isw1.skip(3)).isEqualTo(3);
        Assertions.assertThat(isw1.read()).isEqualTo(9);
        Assertions.assertThat(isw1.read()).isEqualTo(2);
        Assertions.assertThat(isw1.skip(3)).isEqualTo(2);
        Assertions.assertThat(isw1).isCompleted();

        InputStream bais2 = new ByteArrayInputStream(data);
        InputStream isw2 = new InputStreamWrapper(bais2, null);
        Assertions.assertThat(isw2.read()).isEqualTo(5);
        Assertions.assertThat(isw2.read()).isEqualTo(7);
        Assertions.assertThat(isw2.read()).isEqualTo(10);
        Assertions.assertThat(isw2.skip(3)).isEqualTo(3);
        Assertions.assertThat(isw2.read()).isEqualTo(9);
        Assertions.assertThat(isw2.read()).isEqualTo(2);
        Assertions.assertThat(isw2.skip(3)).isEqualTo(2);
        Assertions.assertThat(isw2).isCompleted();

        InputStream bais3 = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        InputStream isw3 = new InputStreamWrapper(bais3, baos3);
        Assertions.assertThat(isw3.read()).isEqualTo(5);
        Assertions.assertThat(baos3.toByteArray()).containsExactlyInOrder(5);
        Assertions.assertThat(isw3.read()).isEqualTo(7);
        Assertions.assertThat(baos3.toByteArray()).containsExactlyInOrder(5, 7);
        Assertions.assertThat(isw3.read()).isEqualTo(10);
        Assertions.assertThat(baos3.toByteArray()).containsExactlyInOrder(5, 7, 10);
        Assertions.assertThat(isw3.skip(3)).isEqualTo(3);
        Assertions.assertThat(baos3.toByteArray()).containsExactlyInOrder(5, 7, 10);
        Assertions.assertThat(isw3.read()).isEqualTo(9);
        Assertions.assertThat(baos3.toByteArray()).containsExactlyInOrder(5, 7, 10, 9);
        Assertions.assertThat(isw3.read()).isEqualTo(2);
        Assertions.assertThat(baos3.toByteArray()).containsExactlyInOrder(5, 7, 10, 9, 2);
        Assertions.assertThat(isw3.skip(3)).isEqualTo(2);
        Assertions.assertThat(baos3.toByteArray()).containsExactlyInOrder(5, 7, 10, 9, 2);
        Assertions.assertThat(isw3).isCompleted();
        Assertions.assertThat(baos3.toByteArray()).containsExactlyInOrder(5, 7, 10, 9, 2);
    }

    /**
     * {@link InputStreamWrapper} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void availableTest() throws Exception {
        byte[] data = new byte[]{5, 7, 10, 1, 6, 3, 9, 2, 4, 8};

        InputStream bais1 = new ByteArrayInputStream(data);
        InputStream isw1 = new InputStreamWrapper(bais1);
        Assertions.assertThat(isw1).hasAvailable(10);
        Assertions.assertThat(isw1).isNextBytesEqualTo(5, 7, 10);
        Assertions.assertThat(isw1).hasAvailable(7);
        Assertions.assertThat(isw1.skip(3)).isEqualTo(3);
        Assertions.assertThat(isw1).hasAvailable(4);
        Assertions.assertThat(isw1).isNextBytesEqualTo(9, 2);
        Assertions.assertThat(isw1).hasAvailable(2);
        Assertions.assertThat(isw1).isNextBytesEqualTo(4, 8);
        Assertions.assertThat(isw1).hasAvailable(0);

        InputStream bais2 = new ByteArrayInputStream(data);
        InputStream isw2 = new InputStreamWrapper(bais2, null);
        Assertions.assertThat(isw2).hasAvailable(10);
        Assertions.assertThat(isw2).isNextBytesEqualTo(5, 7, 10);
        Assertions.assertThat(isw2).hasAvailable(7);
        Assertions.assertThat(isw2.skip(3)).isEqualTo(3);
        Assertions.assertThat(isw2).hasAvailable(4);
        Assertions.assertThat(isw2).isNextBytesEqualTo(9, 2);
        Assertions.assertThat(isw2).hasAvailable(2);
        Assertions.assertThat(isw2).isNextBytesEqualTo(4, 8);
        Assertions.assertThat(isw2).hasAvailable(0);

        InputStream bais3 = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        InputStream isw3 = new InputStreamWrapper(bais3, baos3);
        Assertions.assertThat(isw3).hasAvailable(10);
        Assertions.assertThat(isw3).isNextBytesEqualTo(5, 7, 10);
        Assertions.assertThat(isw3).hasAvailable(7);
        Assertions.assertThat(isw3.skip(3)).isEqualTo(3);
        Assertions.assertThat(isw3).hasAvailable(4);
        Assertions.assertThat(isw3).isNextBytesEqualTo(9, 2);
        Assertions.assertThat(isw3).hasAvailable(2);
        Assertions.assertThat(isw3).isNextBytesEqualTo(4, 8);
        Assertions.assertThat(isw3).hasAvailable(0);
    }

    /**
     * {@link InputStreamWrapper} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void closeTest() throws Exception {
        ClosedInputStream cis1 = new ClosedInputStream();
        InputStream isw1 = new InputStreamWrapper(cis1);
        Assertions.assertThat(cis1.isClosed()).isFalse();
        isw1.close();
        Assertions.assertThat(cis1.isClosed()).isTrue();

        ClosedInputStream cis2 = new ClosedInputStream();
        InputStream isw2 = new InputStreamWrapper(cis2, null);
        Assertions.assertThat(cis2.isClosed()).isFalse();
        isw2.close();
        Assertions.assertThat(cis2.isClosed()).isTrue();

        ClosedInputStream cis3 = new ClosedInputStream();
        ClosedOutputStream cos3 = new ClosedOutputStream();
        InputStream isw3 = new InputStreamWrapper(cis3, cos3);
        Assertions.assertThat(cis3.isClosed()).isFalse();
        Assertions.assertThat(cos3.isClosed()).isFalse();
        isw3.close();
        Assertions.assertThat(cis3.isClosed()).isTrue();
        Assertions.assertThat(cos3.isClosed()).isTrue();

        InputStream focis4 = new FailOnCloseInputStream("main");
        InputStream isw4 = new InputStreamWrapper(focis4);
        try {
            isw4.close();
            Assertions.fail("InputStreamWrapper test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("main");
        }

        InputStream focis5 = new FailOnCloseInputStream("main");
        InputStream isw5 = new InputStreamWrapper(focis5, null);
        try {
            isw5.close();
            Assertions.fail("InputStreamWrapper test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("main");
        }

        InputStream focis6 = new FailOnCloseInputStream("main");
        OutputStream baos6 = new ByteArrayOutputStream();
        InputStream isw6 = new InputStreamWrapper(focis6, baos6);
        try {
            isw6.close();
            Assertions.fail("InputStreamWrapper test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("main");
        }

        InputStream bais7 = new ByteArrayInputStream(new byte[]{});
        OutputStream focos7 = new FailOnCloseOutputStream("log");
        InputStream isw7 = new InputStreamWrapper(bais7, focos7);
        try {
            isw7.close();
            Assertions.fail("InputStreamWrapper test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("log");
        }

        InputStream focis8 = new FailOnCloseInputStream("main");
        OutputStream focos8 = new FailOnCloseOutputStream("log");
        InputStream isw8 = new InputStreamWrapper(focis8, focos8);
        try {
            isw8.close();
            Assertions.fail("InputStreamWrapper test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("main");
        }
    }

    /**
     * {@link InputStreamWrapper} class test.
     */
    @Test
    public void markSupportedTest() {
        byte[] data = new byte[]{};
        InputStream bais = new ByteArrayInputStream(data);
        InputStream isw = new InputStreamWrapper(bais);
        Assertions.assertThat(isw.markSupported()).isFalse();
    }

}
