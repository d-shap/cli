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
package ru.d_shap.cli;

import java.io.IOException;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link CliIOException}.
 *
 * @author Dmitry Shapovalov
 */
public final class CliIOExceptionTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public CliIOExceptionTest() {
        super();
    }

    /**
     * {@link CliIOException} class test.
     */
    @Test
    public void errorMessageTest() {
        Assertions.assertThat(new CliIOException(null)).messageIsNull();
        Assertions.assertThat(new CliIOException(new IOException())).messageIsNull();
        Assertions.assertThat(new CliIOException(new IOException(""))).hasMessage("");
        Assertions.assertThat(new CliIOException(new IOException(" "))).hasMessage(" ");
        Assertions.assertThat(new CliIOException(new IOException("io error"))).hasMessage("io error");
    }

    /**
     * {@link CliIOException} class test.
     */
    @Test
    public void errorCauseTest() {
        Assertions.assertThat(new CliIOException(null)).causeIsNull();
        Assertions.assertThat(new CliIOException(new IOException())).hasCause(IOException.class);
        Assertions.assertThat(new CliIOException(new IOException())).causeMessageIsNull();
        Assertions.assertThat(new CliIOException(new IOException("io error"))).hasCause(IOException.class);
        Assertions.assertThat(new CliIOException(new IOException("io error"))).hasCauseMessage("io error");
    }

}
