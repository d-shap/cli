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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;
import ru.d_shap.cli.CommandRunner;
import ru.d_shap.cli.command.CommandDefinitionException;
import ru.d_shap.cli.data.Context;
import ru.d_shap.cli.data.Lines;

/**
 * Tests for {@link AbstractInputBooleanCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractInputBooleanCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractInputBooleanCommandTest() {
        super();
    }

    /**
     * {@link AbstractInputBooleanCommand} class test.
     */
    @Test
    public void getTrueValuesTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("t");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputBooleanCommandImpl command1 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t"), createSet("f"), true);
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "false", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context1.getValue("key")).isTrue();

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("T");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputBooleanCommandImpl command2 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t"), createSet("f"), true);
        Context context2 = new Context();
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "false", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context2.getValue("key")).isTrue();

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("true");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputBooleanCommandImpl command3 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("f", "false"), true);
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "false", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context3.getValue("key")).isTrue();

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("t");
            CommandRunner commandRunner = new CommandRunner(os, is);
            AbstractInputBooleanCommandImpl command = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", null, createSet("f"), true);
            Context context = new Context();
            commandRunner.execute(command, context);
            Assertions.fail("AbstractInputBooleanCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("True values are not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("t");
            CommandRunner commandRunner = new CommandRunner(os, is);
            AbstractInputBooleanCommandImpl command = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet(), createSet("f"), true);
            Context context = new Context();
            commandRunner.execute(command, context);
            Assertions.fail("AbstractInputBooleanCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("True values are not defined");
        }
    }

    /**
     * {@link AbstractInputBooleanCommand} class test.
     */
    @Test
    public void getFalseValuesTest() {

    }

    /**
     * {@link AbstractInputBooleanCommand} class test.
     */
    @Test
    public void isValidTypeTest() {

    }

    /**
     * {@link AbstractInputBooleanCommand} class test.
     */
    @Test
    public void getValueTest() {

    }

    /**
     * {@link AbstractInputBooleanCommand} class test.
     */
    @Test
    public void getValueAsStringTest() {

    }

    private Set<String> createSet(final String... values) {
        return new HashSet<>(Arrays.asList(values));
    }

}
