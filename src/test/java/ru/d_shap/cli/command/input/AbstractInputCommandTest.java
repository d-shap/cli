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
        AbstractInputCommandImpl command1 = new AbstractInputCommandImpl(null, new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("1,2,5");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputCommandImpl command2 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("1,2,5");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputCommandImpl command3 = new AbstractInputCommandImpl(null, new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder();

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("1,2,5");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        AbstractInputCommandImpl command4 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
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
        AbstractInputCommandImpl command1 = new AbstractInputCommandImpl("key", new Lines(), "default: <%s>", "wrong: <%s>", null);
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context1.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("1,2,5");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputCommandImpl command2 = new AbstractInputCommandImpl("key", new Lines(""), "default: <%s>", "wrong: <%s>", null);
        Context context2 = new Context();
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context2.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("1,2,5");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputCommandImpl command3 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context3.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("1,2,5");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        AbstractInputCommandImpl command4 = new AbstractInputCommandImpl("key", new Lines("line 1", "line 2"), "default: <%s>", "wrong: <%s>", null);
        Context context4 = new Context();
        commandRunner4.execute(command4, context4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line 1", "line 2", "");
        Assertions.assertThat(context4.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context4.getValue("key")).containsExactlyInOrder(1, 2, 5);

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("1,2,5");
            CommandRunner commandRunner = new CommandRunner(os, is);
            AbstractInputCommandImpl command = new AbstractInputCommandImpl("key", null, "default: <%s>", "wrong: <%s>", null);
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
        AbstractInputCommandImpl command1 = new AbstractInputCommandImpl("key", new Lines("line"), null, "wrong: <%s>", null);
        Context context1 = new Context();
        context1.putValue("key", new int[]{7, 9, 3});
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context1.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("1,2,5");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputCommandImpl command2 = new AbstractInputCommandImpl("key", new Lines("line"), "", "wrong: <%s>", null);
        Context context2 = new Context();
        context2.putValue("key", new int[]{7, 9, 3});
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context2.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("1,2,5");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputCommandImpl command3 = new AbstractInputCommandImpl("key", new Lines("line"), "default", "wrong: <%s>", null);
        Context context3 = new Context();
        context3.putValue("key", new int[]{7, 9, 3});
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "default", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context3.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("1,2,5");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        AbstractInputCommandImpl command4 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context4 = new Context();
        context4.putValue("key", new int[]{7, 9, 3});
        commandRunner4.execute(command4, context4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "default: <7,9,3>", "");
        Assertions.assertThat(context4.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context4.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("1,2,5");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        AbstractInputCommandImpl command5 = new AbstractInputCommandImpl(null, new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context5 = new Context();
        context5.putValue("key", new int[]{7, 9, 3});
        commandRunner5.execute(command5, context5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "");
        Assertions.assertThat(context5.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context5.getValue("key")).containsExactlyInOrder(7, 9, 3);

        ByteArrayOutputStream os6 = createOutputStream();
        InputStream is6 = createInputStream("1,2,5");
        CommandRunner commandRunner6 = new CommandRunner(os6, is6);
        AbstractInputCommandImpl command6 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context6 = new Context();
        commandRunner6.execute(command6, context6);
        Assertions.assertThat(getLines(os6)).containsExactlyInOrder("line", "");
        Assertions.assertThat(context6.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context6.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os7 = createOutputStream();
        InputStream is7 = createInputStream("1,2,5");
        CommandRunner commandRunner7 = new CommandRunner(os7, is7);
        AbstractInputCommandImpl command7 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context7 = new Context();
        context7.putValue("key", null);
        commandRunner7.execute(command7, context7);
        Assertions.assertThat(getLines(os7)).containsExactlyInOrder("line", "default: <>", "");
        Assertions.assertThat(context7.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context7.getValue("key")).containsExactlyInOrder(1, 2, 5);
    }

    /**
     * {@link AbstractInputCommand} class test.
     */
    @Test
    public void getWrongInputMessageTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("x", "1,2,5");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractInputCommandImpl command1 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", null, null);
        Context context1 = new Context();
        commandRunner1.execute(command1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "", "line", "");
        Assertions.assertThat(context1.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context1.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("x", "1,2,5");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractInputCommandImpl command2 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "", null);
        Context context2 = new Context();
        commandRunner2.execute(command2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "", "", "line", "");
        Assertions.assertThat(context2.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context2.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("x", "1,2,5");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractInputCommandImpl command3 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong", null);
        Context context3 = new Context();
        commandRunner3.execute(command3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "wrong", "", "line", "");
        Assertions.assertThat(context3.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context3.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("x", "1,2,5");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        AbstractInputCommandImpl command4 = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context4 = new Context();
        commandRunner4.execute(command4, context4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "wrong: <x>", "", "line", "");
        Assertions.assertThat(context4.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context4.getValue("key")).containsExactlyInOrder(1, 2, 5);
    }

    /**
     * {@link AbstractInputCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os01 = createOutputStream();
        InputStream is01 = createInputStream("");
        CommandRunner commandRunner01 = new CommandRunner(os01, is01);
        Command parentCommand01 = new AbstractExecutionCommandImpl("parent command", null);
        AbstractInputCommandImpl command01 = new AbstractInputCommandImpl(parentCommand01, "key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context01 = new Context();
        context01.putValue("key", new int[]{7, 9, 3});
        commandRunner01.execute(command01, context01);
        Assertions.assertThat(getLines(os01)).containsExactlyInOrder("line", "default: <7,9,3>", "", "parent command");
        Assertions.assertThat(context01.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context01.getValue("key")).containsExactlyInOrder(7, 9, 3);

        ByteArrayOutputStream os02 = createOutputStream();
        InputStream is02 = createInputStream("", "1,2,5");
        CommandRunner commandRunner02 = new CommandRunner(os02, is02);
        Command parentCommand02 = new AbstractExecutionCommandImpl("parent command", null);
        AbstractInputCommandImpl command02 = new AbstractInputCommandImpl(parentCommand02, "key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context02 = new Context();
        context02.putValue("key", new int[]{7});
        commandRunner02.execute(command02, context02);
        Assertions.assertThat(getLines(os02)).containsExactlyInOrder("line", "wrong: <>", "", "line", "", "parent command");
        Assertions.assertThat(context02.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context02.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os03 = createOutputStream();
        InputStream is03 = createInputStream("", "1,2,5");
        CommandRunner commandRunner03 = new CommandRunner(os03, is03);
        Command parentCommand03 = new AbstractExecutionCommandImpl("parent command", null);
        AbstractInputCommandImpl command03 = new AbstractInputCommandImpl(parentCommand03, "key", new Lines("line"), null, "wrong: <%s>", null);
        Context context03 = new Context();
        context03.putValue("key", new int[]{7});
        commandRunner03.execute(command03, context03);
        Assertions.assertThat(getLines(os03)).containsExactlyInOrder("line", "wrong: <>", "", "line", "", "parent command");
        Assertions.assertThat(context03.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context03.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os04 = createOutputStream();
        InputStream is04 = createInputStream("", "1,2,5");
        CommandRunner commandRunner04 = new CommandRunner(os04, is04);
        Command parentCommand04 = new AbstractExecutionCommandImpl("parent command", null);
        AbstractInputCommandImpl command04 = new AbstractInputCommandImpl(parentCommand04, "key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context04 = new Context();
        commandRunner04.execute(command04, context04);
        Assertions.assertThat(getLines(os04)).containsExactlyInOrder("line", "wrong: <>", "", "line", "", "parent command");
        Assertions.assertThat(context04.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context04.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os05 = createOutputStream();
        InputStream is05 = createInputStream("x", "1,2,5");
        CommandRunner commandRunner05 = new CommandRunner(os05, is05);
        Command parentCommand05 = new AbstractExecutionCommandImpl("parent command", null);
        AbstractInputCommandImpl command05 = new AbstractInputCommandImpl(parentCommand05, "key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context05 = new Context();
        commandRunner05.execute(command05, context05);
        Assertions.assertThat(getLines(os05)).containsExactlyInOrder("line", "wrong: <x>", "", "line", "", "parent command");
        Assertions.assertThat(context05.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context05.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os06 = createOutputStream();
        InputStream is06 = createInputStream("7", "1,2,5");
        CommandRunner commandRunner06 = new CommandRunner(os06, is06);
        Command parentCommand06 = new AbstractExecutionCommandImpl("parent command", null);
        AbstractInputCommandImpl command06 = new AbstractInputCommandImpl(parentCommand06, "key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);
        Context context06 = new Context();
        commandRunner06.execute(command06, context06);
        Assertions.assertThat(getLines(os06)).containsExactlyInOrder("line", "wrong: <7>", "", "line", "", "parent command");
        Assertions.assertThat(context06.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context06.getValue("key")).containsExactlyInOrder(1, 2, 5);

        ByteArrayOutputStream os07 = createOutputStream();
        InputStream is07 = createInputStream("");
        CommandRunner commandRunner07 = new CommandRunner(os07, is07);
        Command parentCommand07 = new AbstractExecutionCommandImpl("parent command", null);
        Command nextCommand07 = new AbstractExecutionCommandImpl("next command", null);
        AbstractInputCommandImpl command07 = new AbstractInputCommandImpl(parentCommand07, "key", new Lines("line"), "default: <%s>", "wrong: <%s>", nextCommand07);
        Context context07 = new Context();
        context07.putValue("key", new int[]{7, 9, 3});
        commandRunner07.execute(command07, context07);
        Assertions.assertThat(getLines(os07)).containsExactlyInOrder("line", "default: <7,9,3>", "", "next command");
        Assertions.assertThat(context07.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context07.getValue("key")).containsExactlyInOrder(7, 9, 3);

        ByteArrayOutputStream os08 = createOutputStream();
        InputStream is08 = createInputStream("x", "1,2,5");
        CommandRunner commandRunner08 = new CommandRunner(os08, is08);
        Command parentCommand08 = new AbstractExecutionCommandImpl("parent command", null);
        Command nextCommand08 = new AbstractExecutionCommandImpl("next command", null);
        AbstractInputCommandImpl command08 = new AbstractInputCommandImpl(parentCommand08, "key", new Lines("line"), "default: <%s>", "wrong: <%s>", nextCommand08);
        Context context08 = new Context();
        commandRunner08.execute(command08, context08);
        Assertions.assertThat(getLines(os08)).containsExactlyInOrder("line", "wrong: <x>", "", "line", "", "next command");
        Assertions.assertThat(context08.getNames()).containsExactlyInOrder("key");
        Assertions.assertThat((int[]) context08.getValue("key")).containsExactlyInOrder(1, 2, 5);
    }

    /**
     * {@link AbstractInputCommand} class test.
     */
    @Test
    public void resetTest() {
        AbstractInputCommandImpl command = new AbstractInputCommandImpl("key", new Lines("line"), "default: <%s>", "wrong: <%s>", null);

        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("7", "1,2,5");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Context context1 = new Context();
        context1.putValue("key", new int[]{7, 9, 3});
        commandRunner1.execute(command, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "default: <7,9,3>", "wrong: <7>", "", "line", "default: <7,9,3>", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("7", "1,2,5");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Context context2 = new Context();
        context2.putValue("key", new int[]{7, 9, 3});
        context2.putValue(AbstractInputCommandImpl.CONTEXT_RESET, new Object());
        commandRunner2.execute(command, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "default: <7,9,3>", "wrong: <7>", "", "line", "default: <7,9,3>", "");

        command.reset();

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("7", "1,2,5");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Context context3 = new Context();
        context3.putValue("r!key", new int[]{7, 9, 3});
        context3.putValue(AbstractInputCommandImpl.CONTEXT_RESET, new Object());
        commandRunner3.execute(command, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "r!", "r!default: <7,9,3>", "r!wrong: <7>", "", "line", "r!", "r!default: <7,9,3>", "");
    }

}
