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
 * Tests for {@link AbstractInputIntegerCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractInputIntegerCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractInputIntegerCommandTest() {
        super();
    }

    /**
     * {@link AbstractInputIntegerCommand} class test.
     */
    @Test
    public void isValidTypeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputIntegerCommandImpl command1 = new AbstractInputIntegerCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "2", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int) context1.getValue("key")).isEqualTo(1);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("", "1");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputIntegerCommandImpl command2 = new AbstractInputIntegerCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context2 = new Context();
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "wrong: <>", "", "line", "2", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int) context2.getValue("key")).isEqualTo(1);

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("x", "1");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputIntegerCommandImpl command3 = new AbstractInputIntegerCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "wrong: <x>", "", "line", "2", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int) context3.getValue("key")).isEqualTo(1);
    }

    /**
     * {@link AbstractInputIntegerCommand} class test.
     */
    @Test
    public void getValueTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputIntegerCommandImpl command1 = new AbstractInputIntegerCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        context1.putValue("key", 5);
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "default: <5>", "2", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int) context1.getValue("key")).isEqualTo(1);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputIntegerCommandImpl command2 = new AbstractInputIntegerCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context2 = new Context();
        context2.putValue("key", 5);
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "default: <5>", "6", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int) context2.getValue("key")).isEqualTo(5);
    }

    /**
     * {@link AbstractInputIntegerCommand} class test.
     */
    @Test
    public void getValueAsStringTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("", "5");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputIntegerCommandImpl command1 = new AbstractInputIntegerCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        context1.putValue("key", 15);
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "wrong: <>", "", "line", "6", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int) context1.getValue("key")).isEqualTo(5);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("", "5");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputIntegerCommandImpl command2 = new AbstractInputIntegerCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context2 = new Context();
        context2.putValue("key", 6);
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "default: <6>", "7", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int) context2.getValue("key")).isEqualTo(6);
    }

    /**
     * {@link AbstractInputIntegerCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command parentCommand1 = new AbstractExecutionCommandImpl("parent command", null);
        AbstractInputIntegerCommandImpl command1 = new AbstractInputIntegerCommandImpl(parentCommand1, "key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "2", "", "parent command");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int) context1.getValue("key")).isEqualTo(1);
    }

    /**
     * {@link AbstractInputIntegerCommand} class test.
     */
    @Test
    public void resetTest() {
        AbstractInputIntegerCommandImpl command = new AbstractInputIntegerCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");

        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("11", "1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Context context1 = new Context();
        context1.putValue("key", 7);
        commandRunner1.execute(command, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "default: <7>", "wrong: <11>", "", "line", "default: <7>", "2", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("11", "1");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Context context2 = new Context();
        context2.putValue("key", 7);
        context2.putValue(AbstractInputIntegerCommandImpl.CONTEXT_RESET, new Object());
        commandRunner2.execute(command, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "default: <7>", "wrong: <11>", "", "line", "default: <7>", "2", "");

        command.reset();

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("11", "1");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Context context3 = new Context();
        context3.putValue("r!key", 7);
        context3.putValue(AbstractInputIntegerCommandImpl.CONTEXT_RESET, new Object());
        commandRunner3.execute(command, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "r!", "r!default: <7>", "r!wrong: <11>", "", "line", "r!", "r!default: <7>", "2", "");
    }

}
