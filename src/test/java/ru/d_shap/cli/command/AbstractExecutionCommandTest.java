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
import ru.d_shap.cli.Command;
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
        Command command1 = new AbstractExecutionCommandImpl("Output 1", null);
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("Output 1");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream();
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command command2 = new AbstractExecutionCommandImpl(null, "Output 1", null);
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("Output 1");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream();
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command command33 = new AbstractExecutionCommandImpl("Output 3", null);
        Command command32 = new AbstractExecutionCommandImpl("Output 2", command33);
        Command command31 = new AbstractExecutionCommandImpl("Output 1", command32);
        commandRunner3.execute(command31);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("Output 1", "Output 2", "Output 3");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream();
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Command command43 = new AbstractExecutionCommandImpl(null, "Output 3", null);
        Command command42 = new AbstractExecutionCommandImpl(null, "Output 2", command43);
        Command command41 = new AbstractExecutionCommandImpl(null, "Output 1", command42);
        commandRunner4.execute(command41);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("Output 1", "Output 2", "Output 3");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream();
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Command parentCommand5 = new AbstractExecutionCommandImpl(null, "parent", null);
        Command command5 = new AbstractExecutionCommandImpl(parentCommand5, "Output 1", null);
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("Output 1", "parent");

        ByteArrayOutputStream os6 = createOutputStream();
        InputStream is6 = createInputStream();
        CommandRunner commandRunner6 = new CommandRunner(os6, is6);
        Command parentCommand6 = new AbstractExecutionCommandImpl(null, "parent", null);
        Command command63 = new AbstractExecutionCommandImpl(parentCommand6, "Output 3", null);
        Command command62 = new AbstractExecutionCommandImpl(parentCommand6, "Output 2", command63);
        Command command61 = new AbstractExecutionCommandImpl(parentCommand6, "Output 1", command62);
        commandRunner6.execute(command61);
        Assertions.assertThat(getLines(os6)).containsExactlyInOrder("Output 1", "Output 2", "Output 3", "parent");
    }

}
