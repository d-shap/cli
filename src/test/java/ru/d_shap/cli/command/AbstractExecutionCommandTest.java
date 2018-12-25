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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;
import ru.d_shap.cli.CommandRunner;

/**
 * Tests for {@link AbstractExecutionCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractExecutionCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractExecutionCommandTest() {
        super();
    }

    /**
     * {@link AbstractExecutionCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream();
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractExecutionCommand executionCommand1 = new AbstractExecutionCommandImpl("Output 1");
        commandRunner1.execute(executionCommand1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("Output 1");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream();
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractExecutionCommand executionCommand2 = new AbstractExecutionCommandImpl("Output 1", null);
        commandRunner2.execute(executionCommand2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("Output 1");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream();
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractExecutionCommand executionCommand33 = new AbstractExecutionCommandImpl("Output 3");
        AbstractExecutionCommand executionCommand32 = new AbstractExecutionCommandImpl("Output 2", executionCommand33);
        AbstractExecutionCommand executionCommand31 = new AbstractExecutionCommandImpl("Output 1", executionCommand32);
        commandRunner3.execute(executionCommand31);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("Output 1", "Output 2", "Output 3");
    }

}
