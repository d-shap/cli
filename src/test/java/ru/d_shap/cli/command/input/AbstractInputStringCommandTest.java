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
package ru.d_shap.cli.command.input;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;
import ru.d_shap.cli.Command;
import ru.d_shap.cli.CommandRunner;
import ru.d_shap.cli.command.AbstractExecutionCommandImpl;
import ru.d_shap.cli.data.Context;
import ru.d_shap.cli.data.Lines;

/**
 * Tests for {@link AbstractInputStringCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractInputStringCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractInputStringCommandTest() {
        super();
    }

    /**
     * {@link AbstractInputStringCommand} class test.
     */
    @Test
    public void isValidTypeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("value");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputStringCommandImpl command1 = new AbstractInputStringCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "value1", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((String) context1.getValue("key")).isEqualTo("value");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("", "value");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputStringCommandImpl command2 = new AbstractInputStringCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context2 = new Context();
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "wrong: <>", "", "line", "value1", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((String) context2.getValue("key")).isEqualTo("value");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("xxxxxxxxxxx", "value");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputStringCommandImpl command3 = new AbstractInputStringCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "wrong: <xxxxxxxxxxx>", "", "line", "value1", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((String) context3.getValue("key")).isEqualTo("value");
    }

    /**
     * {@link AbstractInputStringCommand} class test.
     */
    @Test
    public void getValueTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("value");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputStringCommandImpl command1 = new AbstractInputStringCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        context1.putValue("key", "def");
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "default: <def>", "value1", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((String) context1.getValue("key")).isEqualTo("value");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputStringCommandImpl command2 = new AbstractInputStringCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context2 = new Context();
        context2.putValue("key", "def");
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "default: <def>", "def1", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((String) context2.getValue("key")).isEqualTo("def");
    }

    /**
     * {@link AbstractInputStringCommand} class test.
     */
    @Test
    public void getValueAsStringTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("", "value");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputStringCommandImpl command1 = new AbstractInputStringCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        context1.putValue("key", "xxxxxxxxxxx");
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "wrong: <>", "", "line", "value1", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((String) context1.getValue("key")).isEqualTo("value");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("", "value");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputStringCommandImpl command2 = new AbstractInputStringCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context2 = new Context();
        context2.putValue("key", "def");
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "default: <def>", "def1", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((String) context2.getValue("key")).isEqualTo("def");
    }

    /**
     * {@link AbstractInputStringCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("value");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command parentCommand1 = new AbstractExecutionCommandImpl("parent command", null);
        AbstractInputStringCommandImpl command1 = new AbstractInputStringCommandImpl(parentCommand1, "key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "value1", "", "parent command");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((String) context1.getValue("key")).isEqualTo("value");
    }

    /**
     * {@link AbstractInputStringCommand} class test.
     */
    @Test
    public void resetTest() {
        AbstractInputStringCommandImpl command = new AbstractInputStringCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");

        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("xxxxxxxxxxx", "value");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Context context1 = new Context();
        context1.putValue("key", "def");
        commandRunner1.execute(command, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "default: <def>", "wrong: <xxxxxxxxxxx>", "", "line", "default: <def>", "value1", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("xxxxxxxxxxx", "value");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Context context2 = new Context();
        context2.putValue("key", "def");
        context2.putValue(AbstractInputStringCommandImpl.CONTEXT_RESET, new Object());
        commandRunner2.execute(command, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "default: <def>", "wrong: <xxxxxxxxxxx>", "", "line", "default: <def>", "value1", "");

        command.reset();

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("xxxxxxxxxxx", "value");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Context context3 = new Context();
        context3.putValue("r!key", "def");
        context3.putValue(AbstractInputStringCommandImpl.CONTEXT_RESET, new Object());
        commandRunner3.execute(command, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "r!", "r!default: <def>", "r!wrong: <xxxxxxxxxxx>", "", "line", "r!", "r!default: <def>", "value1", "");
    }

}
