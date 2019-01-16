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
 * Tests for {@link AbstractInputDoubleCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractInputDoubleCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractInputDoubleCommandTest() {
        super();
    }

    /**
     * {@link AbstractInputDoubleCommand} class test.
     */
    @Test
    public void isValidTypeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1.1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputDoubleCommandImpl command1 = new AbstractInputDoubleCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "2.1", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((double) context1.getValue("key")).isEqualTo(1.1, 0.01);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("", "1.1");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputDoubleCommandImpl command2 = new AbstractInputDoubleCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context2 = new Context();
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "wrong: <>", "", "line", "2.1", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((double) context2.getValue("key")).isEqualTo(1.1, 0.01);

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("x", "1.1");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputDoubleCommandImpl command3 = new AbstractInputDoubleCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "wrong: <x>", "", "line", "2.1", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((double) context3.getValue("key")).isEqualTo(1.1, 0.01);
    }

    /**
     * {@link AbstractInputDoubleCommand} class test.
     */
    @Test
    public void getValueTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1.1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputDoubleCommandImpl command1 = new AbstractInputDoubleCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        context1.putValue("key", 5.1);
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "default: <5.1>", "2.1", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((double) context1.getValue("key")).isEqualTo(1.1, 0.01);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputDoubleCommandImpl command2 = new AbstractInputDoubleCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context2 = new Context();
        context2.putValue("key", 5.1);
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "default: <5.1>", "6.1", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((double) context2.getValue("key")).isEqualTo(5.1, 0.01);
    }

    /**
     * {@link AbstractInputDoubleCommand} class test.
     */
    @Test
    public void getValueAsStringTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("", "5.1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputDoubleCommandImpl command1 = new AbstractInputDoubleCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        context1.putValue("key", 15.1);
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "wrong: <>", "", "line", "6.1", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((double) context1.getValue("key")).isEqualTo(5.1, 0.01);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("", "5.1");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputDoubleCommandImpl command2 = new AbstractInputDoubleCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context2 = new Context();
        context2.putValue("key", 6.1);
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "default: <6.1>", "7.1", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((double) context2.getValue("key")).isEqualTo(6.1, 0.01);
    }

    /**
     * {@link AbstractInputDoubleCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1.1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command parentCommand1 = new AbstractExecutionCommandImpl("parent command");
        AbstractInputDoubleCommandImpl command1 = new AbstractInputDoubleCommandImpl(parentCommand1, "key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "2.1", "", "parent command");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((double) context1.getValue("key")).isEqualTo(1.1, 0.01);
    }

    /**
     * {@link AbstractInputDoubleCommand} class test.
     */
    @Test
    public void resetTest() {
        AbstractInputDoubleCommandImpl command = new AbstractInputDoubleCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");

        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("11.1", "1.1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Context context1 = new Context();
        context1.putValue("key", 7.1);
        commandRunner1.execute(command, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "default: <7.1>", "wrong: <11.1>", "", "line", "default: <7.1>", "2.1", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("11.1", "1.1");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Context context2 = new Context();
        context2.putValue("key", 7.1);
        context2.putValue(AbstractInputDoubleCommandImpl.CONTEXT_RESET, new Object());
        commandRunner2.execute(command, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "default: <7.1>", "wrong: <11.1>", "", "line", "default: <7.1>", "2.1", "");

        command.reset();

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("11.1", "1.1");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Context context3 = new Context();
        context3.putValue("r!key", 7.1);
        context3.putValue(AbstractInputDoubleCommandImpl.CONTEXT_RESET, new Object());
        commandRunner3.execute(command, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "r!", "r!default: <7.1>", "r!wrong: <11.1>", "", "line", "r!", "r!default: <7.1>", "2.1", "");
    }

}
