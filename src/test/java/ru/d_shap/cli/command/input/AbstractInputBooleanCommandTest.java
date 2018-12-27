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

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("t");
            CommandRunner commandRunner = new CommandRunner(os, is);
            AbstractInputBooleanCommandImpl command = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("t"), true);
            Context context = new Context();
            commandRunner.execute(command, context);
            Assertions.fail("AbstractInputBooleanCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("True values and False values contain the same value");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("t");
            CommandRunner commandRunner = new CommandRunner(os, is);
            AbstractInputBooleanCommandImpl command = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("f", "false", "t"), true);
            Context context = new Context();
            commandRunner.execute(command, context);
            Assertions.fail("AbstractInputBooleanCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("True values and False values contain the same value");
        }
    }

    /**
     * {@link AbstractInputBooleanCommand} class test.
     */
    @Test
    public void getFalseValuesTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("f");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputBooleanCommandImpl command1 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t"), createSet("f"), false);
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "true", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context1.getValue("key")).isFalse();

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("F");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputBooleanCommandImpl command2 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t"), createSet("f"), false);
        Context context2 = new Context();
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "true", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context2.getValue("key")).isFalse();

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("false");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputBooleanCommandImpl command3 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("f", "false"), false);
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "true", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context3.getValue("key")).isFalse();

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("f");
            CommandRunner commandRunner = new CommandRunner(os, is);
            AbstractInputBooleanCommandImpl command = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t"), null, false);
            Context context = new Context();
            commandRunner.execute(command, context);
            Assertions.fail("AbstractInputBooleanCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("False values are not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("f");
            CommandRunner commandRunner = new CommandRunner(os, is);
            AbstractInputBooleanCommandImpl command = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t"), createSet(), false);
            Context context = new Context();
            commandRunner.execute(command, context);
            Assertions.fail("AbstractInputBooleanCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("False values are not defined");
        }
    }

    /**
     * {@link AbstractInputBooleanCommand} class test.
     */
    @Test
    public void isValidTypeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("t");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputBooleanCommandImpl command1 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("f", "false"), true);
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "false", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context1.getValue("key")).isTrue();

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("true");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputBooleanCommandImpl command2 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("f", "false"), true);
        Context context2 = new Context();
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "false", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context2.getValue("key")).isTrue();

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("f");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputBooleanCommandImpl command3 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("f", "false"), false);
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "true", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context3.getValue("key")).isFalse();

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("false");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        AbstractInputBooleanCommandImpl command4 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("f", "false"), false);
        Context context4 = new Context();
        commandRunner4.execute(command4, context4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "true", "");
        Assertions.assertThat(context4.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context4.getValue("key")).isFalse();

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("", "t");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        AbstractInputBooleanCommandImpl command5 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("f", "false"), true);
        Context context5 = new Context();
        commandRunner5.execute(command5, context5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "wrong: <>", "", "line", "false", "");
        Assertions.assertThat(context5.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context5.getValue("key")).isTrue();

        ByteArrayOutputStream os6 = createOutputStream();
        InputStream is6 = createInputStream("", "f");
        CommandRunner commandRunner6 = new CommandRunner(os6, is6);
        AbstractInputBooleanCommandImpl command6 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("f", "false"), false);
        Context context6 = new Context();
        commandRunner6.execute(command6, context6);
        Assertions.assertThat(getLines(os6)).containsExactlyInOrder("line", "wrong: <>", "", "line", "true", "");
        Assertions.assertThat(context6.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context6.getValue("key")).isFalse();

        ByteArrayOutputStream os7 = createOutputStream();
        InputStream is7 = createInputStream("x", "t");
        CommandRunner commandRunner7 = new CommandRunner(os7, is7);
        AbstractInputBooleanCommandImpl command7 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("f", "false"), true);
        Context context7 = new Context();
        commandRunner7.execute(command7, context7);
        Assertions.assertThat(getLines(os7)).containsExactlyInOrder("line", "wrong: <x>", "", "line", "false", "");
        Assertions.assertThat(context7.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context7.getValue("key")).isTrue();

        ByteArrayOutputStream os8 = createOutputStream();
        InputStream is8 = createInputStream("x", "f");
        CommandRunner commandRunner8 = new CommandRunner(os8, is8);
        AbstractInputBooleanCommandImpl command8 = new AbstractInputBooleanCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", createSet("t", "true"), createSet("f", "false"), false);
        Context context8 = new Context();
        commandRunner8.execute(command8, context8);
        Assertions.assertThat(getLines(os8)).containsExactlyInOrder("line", "wrong: <x>", "", "line", "true", "");
        Assertions.assertThat(context8.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((boolean) context8.getValue("key")).isFalse();
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
