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
import ru.d_shap.cli.command.CommandDefinitionException;
import ru.d_shap.cli.data.Context;
import ru.d_shap.cli.data.Lines;

/**
 * Tests for {@link AbstractInputCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractInputCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractInputCommandTest() {
        super();
    }

    /**
     * {@link AbstractInputCommand} class test.
     */
    @Test
    public void getContextKeyTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1,2,5");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputCommandImpl command1 = new AbstractInputCommandImpl(null, new Lines("line"), "default: <%s>", "wrong: <%>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("1,2,5");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputCommandImpl command2 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("1,2,5");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputCommandImpl command3 = new AbstractInputCommandImpl(null, new Lines("line"), "default: <%s>", "wrong: <%>");
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder();

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("1,2,5");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        AbstractInputCommandImpl command4 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%>");
        Context context4 = new Context();
        commandRunner4.execute(command4, context4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "");
        Assertions.assertThat(context4.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context4.getValue("key")).containsExactlyInOrder(1, 2, 5);
    }

    /**
     * {@link AbstractInputCommand} class test.
     */
    @Test
    public void getHeaderTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1,2,5");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputCommandImpl command1 = new AbstractInputCommandImpl("key", new Lines(), "default: <%s>", "wrong: <%>");
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context1.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("1,2,5");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputCommandImpl command2 = new AbstractInputCommandImpl("key", new Lines(""), "default: <%s>", "wrong: <%>");
        Context context2 = new Context();
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context2.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("1,2,5");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputCommandImpl command3 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%>");
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context3.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("1,2,5");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        AbstractInputCommandImpl command4 = new AbstractInputCommandImpl("key", new Lines("line 1", "line 2"), "default: <%s>", "wrong: <%>");
        Context context4 = new Context();
        commandRunner4.execute(command4, context4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line 1", "line 2", "");
        Assertions.assertThat(context4.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context4.getValue("key")).containsExactlyInOrder(1, 2, 5);

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("1,2,5");
            CommandRunner commandRunner = new CommandRunner(os, is);
            AbstractInputCommandImpl command = new AbstractInputCommandImpl("key", null, "default: <%s>", "wrong: <%>");
            Context context = new Context();
            commandRunner.execute(command, context);
            Assertions.fail("AbstractInputCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Header is not defined");
        }
    }

    /**
     * {@link AbstractInputCommand} class test.
     */
    @Test
    public void getDefaultMessageTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1,2,5");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputCommandImpl command1 = new AbstractInputCommandImpl("key", new Lines("line"), null, "wrong: <%>");
        Context context1 = new Context();
        context1.putValue("key", new int[]{7, 9, 3});
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context1.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("1,2,5");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputCommandImpl command2 = new AbstractInputCommandImpl("key", new Lines("line"), "", "wrong: <%>");
        Context context2 = new Context();
        context2.putValue("key", new int[]{7, 9, 3});
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context2.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("1,2,5");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputCommandImpl command3 = new AbstractInputCommandImpl("key", new Lines("line"), "default", "wrong: <%>");
        Context context3 = new Context();
        context3.putValue("key", new int[]{7, 9, 3});
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "default", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context3.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("1,2,5");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        AbstractInputCommandImpl command4 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%>");
        Context context4 = new Context();
        context4.putValue("key", new int[]{7, 9, 3});
        commandRunner4.execute(command4, context4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "default: <7,9,3>", "");
        Assertions.assertThat(context4.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context4.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("1,2,5");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        AbstractInputCommandImpl command5 = new AbstractInputCommandImpl(null, new Lines("line"), "default: <%s>", "wrong: <%>");
        Context context5 = new Context();
        context5.putValue("key", new int[]{7, 9, 3});
        commandRunner5.execute(command5, context5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "");
        Assertions.assertThat(context5.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context5.getValue("key")).containsExactlyInOrder(7, 9, 3);

        ByteArrayOutputStream os6 = createOutputStream();
        InputStream is6 = createInputStream("1,2,5");
        CommandRunner commandRunner6 = new CommandRunner(os6, is6);
        AbstractInputCommandImpl command6 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%>");
        Context context6 = new Context();
        commandRunner6.execute(command6, context6);
        Assertions.assertThat(getLines(os6)).containsExactlyInOrder("line", "");
        Assertions.assertThat(context6.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context6.getValue("key")).containsExactlyInOrder(1, 2, 5);
    }

    /**
     * {@link AbstractInputCommand} class test.
     */
    @Test
    public void getWrongInputMessageTest() {

    }

    /**
     * {@link AbstractInputCommand} class test.
     */
    @Test
    public void executeTest() {

    }

}