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
 * Tests for {@link CliException}.
 *
 * @author Dmitry Shapovalov
 */
public final class CliExceptionTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public CliExceptionTest() {
        super();
    }

    /**
     * {@link CliException} class test.
     */
    @Test
    public void errorMessageTest() {
        Assertions.assertThat(new CliException((String) null)).messageIsNull();
        Assertions.assertThat(new CliException("")).hasMessage("");
        Assertions.assertThat(new CliException(" ")).hasMessage(" ");
        Assertions.assertThat(new CliException("error")).hasMessage("error");

        Assertions.assertThat(new CliException(null, null)).messageIsNull();
        Assertions.assertThat(new CliException("", null)).hasMessage("");
        Assertions.assertThat(new CliException(" ", null)).hasMessage(" ");
        Assertions.assertThat(new CliException("error", null)).hasMessage("error");
        Assertions.assertThat(new CliException("error", new IOException("io error"))).hasMessage("error");

        Assertions.assertThat(new CliException(null, new IOException())).messageIsNull();
        Assertions.assertThat(new CliException("", new IOException())).hasMessage("");
        Assertions.assertThat(new CliException(" ", new IOException())).hasMessage(" ");
        Assertions.assertThat(new CliException("error", new IOException())).hasMessage("error");
        Assertions.assertThat(new CliException("error", new IOException())).hasMessage("error");

        Assertions.assertThat(new CliException(null, new IOException("io error"))).messageIsNull();
        Assertions.assertThat(new CliException("", new IOException("io error"))).hasMessage("");
        Assertions.assertThat(new CliException(" ", new IOException("io error"))).hasMessage(" ");
        Assertions.assertThat(new CliException("error", new IOException("io error"))).hasMessage("error");
        Assertions.assertThat(new CliException("error", new IOException("io error"))).hasMessage("error");

        Assertions.assertThat(new CliException((Throwable) null)).messageIsNull();
        Assertions.assertThat(new CliException(new IOException())).messageIsNull();
        Assertions.assertThat(new CliException(new IOException(""))).hasMessage("");
        Assertions.assertThat(new CliException(new IOException(" "))).hasMessage(" ");
        Assertions.assertThat(new CliException(new IOException("io error"))).hasMessage("io error");
    }

    /**
     * {@link CliException} class test.
     */
    @Test
    public void errorCauseTest() {
        Assertions.assertThat(new CliException("")).causeIsNull();
        Assertions.assertThat(new CliException(" ")).causeIsNull();
        Assertions.assertThat(new CliException("error")).causeIsNull();

        Assertions.assertThat(new CliException("", null)).causeIsNull();
        Assertions.assertThat(new CliException(" ", null)).causeIsNull();
        Assertions.assertThat(new CliException("error", null)).causeIsNull();

        Assertions.assertThat(new CliException("", new IOException())).hasCause(IOException.class);
        Assertions.assertThat(new CliException("", new IOException())).causeMessageIsNull();
        Assertions.assertThat(new CliException(" ", new IOException())).hasCause(IOException.class);
        Assertions.assertThat(new CliException(" ", new IOException())).causeMessageIsNull();
        Assertions.assertThat(new CliException("error", new IOException())).hasCause(IOException.class);
        Assertions.assertThat(new CliException("error", new IOException())).causeMessageIsNull();

        Assertions.assertThat(new CliException("", new IOException("io error"))).hasCause(IOException.class);
        Assertions.assertThat(new CliException("", new IOException("io error"))).hasCauseMessage("io error");
        Assertions.assertThat(new CliException(" ", new IOException("io error"))).hasCause(IOException.class);
        Assertions.assertThat(new CliException(" ", new IOException("io error"))).hasCauseMessage("io error");
        Assertions.assertThat(new CliException("error", new IOException("io error"))).hasCause(IOException.class);
        Assertions.assertThat(new CliException("error", new IOException("io error"))).hasCauseMessage("io error");

        Assertions.assertThat(new CliException((Throwable) null)).causeIsNull();
        Assertions.assertThat(new CliException(new IOException())).hasCause(IOException.class);
        Assertions.assertThat(new CliException(new IOException())).causeMessageIsNull();
        Assertions.assertThat(new CliException(new IOException("io error"))).hasCause(IOException.class);
        Assertions.assertThat(new CliException(new IOException("io error"))).hasCauseMessage("io error");
    }

}
