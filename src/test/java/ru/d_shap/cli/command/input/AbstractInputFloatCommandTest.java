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
import ru.d_shap.cli.CommandRunner;
import ru.d_shap.cli.data.Context;
import ru.d_shap.cli.data.Lines;

/**
 * Tests for {@link AbstractInputFloatCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractInputFloatCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractInputFloatCommandTest() {
        super();
    }

    /**
     * {@link AbstractInputFloatCommand} class test.
     */
    @Test
    public void isValidTypeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1.1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputFloatCommandImpl command1 = new AbstractInputFloatCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "2.1", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((float) context1.getValue("key")).isEqualTo(1.1f, 0.01f);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("", "1.1");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputFloatCommandImpl command2 = new AbstractInputFloatCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context2 = new Context();
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "wrong: <>", "", "line", "2.1", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((float) context2.getValue("key")).isEqualTo(1.1f, 0.01f);

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("x", "1.1");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputFloatCommandImpl command3 = new AbstractInputFloatCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "wrong: <x>", "", "line", "2.1", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((float) context3.getValue("key")).isEqualTo(1.1f, 0.01f);
    }

    /**
     * {@link AbstractInputFloatCommand} class test.
     */
    @Test
    public void getValueTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1.1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputFloatCommandImpl command1 = new AbstractInputFloatCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        context1.putValue("key", 5.1f);
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "default: <5.1>", "2.1", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((float) context1.getValue("key")).isEqualTo(1.1f, 0.01f);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputFloatCommandImpl command2 = new AbstractInputFloatCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context2 = new Context();
        context2.putValue("key", 5.1f);
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "default: <5.1>", "6.1", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((float) context2.getValue("key")).isEqualTo(5.1f, 0.01f);
    }

    /**
     * {@link AbstractInputFloatCommand} class test.
     */
    @Test
    public void getValueAsStringTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("", "5.1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputFloatCommandImpl command1 = new AbstractInputFloatCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>");
        Context context1 = new Context();
        context1.putValue("key", 15.1f);
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "default: <15.1>", "wrong: <15.1>", "", "line", "default: <15.1>", "6.1", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((float) context1.getValue("key")).isEqualTo(5.1f, 0.01f);
    }

}
