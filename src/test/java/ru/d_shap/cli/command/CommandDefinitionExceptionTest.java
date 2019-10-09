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
package ru.d_shap.cli.command;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;

/**
 * Tests for {@link CommandDefinitionException}.
 *
 * @author Dmitry Shapovalov
 */
public final class CommandDefinitionExceptionTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public CommandDefinitionExceptionTest() {
        super();
    }

    /**
     * {@link CommandDefinitionException} class test.
     */
    @Test
    public void errorMessageTest() {
        Assertions.assertThat(new CommandDefinitionException(null)).toMessage().isNull();
        Assertions.assertThat(new CommandDefinitionException("")).hasMessage("");
        Assertions.assertThat(new CommandDefinitionException(" ")).hasMessage(" ");
        Assertions.assertThat(new CommandDefinitionException("error")).hasMessage("error");
    }

    /**
     * {@link CommandDefinitionException} class test.
     */
    @Test
    public void errorCauseTest() {
        Assertions.assertThat(new CommandDefinitionException("")).toCause().isNull();
        Assertions.assertThat(new CommandDefinitionException(" ")).toCause().isNull();
        Assertions.assertThat(new CommandDefinitionException("error")).toCause().isNull();
    }

}
