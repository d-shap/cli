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
package ru.d_shap.cli.command.menu;

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
 * Tests for {@link AbstractConfirmCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractConfirmCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractConfirmCommandTest() {
        super();
    }

    /**
     * {@link AbstractConfirmCommand} class test.
     */
    @Test
    public void getYesMenuItemSymbolTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("y");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand1 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command1 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "     *n: No", "", "yes selected");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("yes");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand2 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command2 = new AbstractConfirmCommandImpl(new Lines("line"), "yes", new Lines("Yes"), childCommand2, "no", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "    yes: Yes", "    *no: No", "", "yes selected");

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("y");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand = new AbstractExecutionCommandImpl("yes selected", null);
            AbstractConfirmCommandImpl command = new AbstractConfirmCommandImpl(new Lines("line"), null, new Lines("Yes"), childCommand, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractConfirmCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("y");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand = new AbstractExecutionCommandImpl("yes selected", null);
            AbstractConfirmCommandImpl command = new AbstractConfirmCommandImpl(new Lines("line"), "", new Lines("Yes"), childCommand, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractConfirmCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("y");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand = new AbstractExecutionCommandImpl("yes selected", null);
            AbstractConfirmCommandImpl command = new AbstractConfirmCommandImpl(new Lines("line"), " ", new Lines("Yes"), childCommand, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractConfirmCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is wrong:  ");
        }
    }

    /**
     * {@link AbstractConfirmCommand} class test.
     */
    @Test
    public void getYesMenuItemLabelTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("y");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand1 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command1 = new AbstractConfirmCommandImpl(new Lines("line"), "y", null, childCommand1, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: ", "     *n: No", "", "yes selected");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("y");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand2 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command2 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines(), childCommand2, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      y: ", "     *n: No", "", "yes selected");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("y");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command childCommand3 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command3 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines(""), childCommand3, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      y: ", "     *n: No", "", "yes selected");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("y");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Command childCommand4 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command4 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("line"), childCommand4, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "      y: line", "     *n: No", "", "yes selected");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("y");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Command childCommand5 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command5 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("line 1", "line 2"), childCommand5, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "      y: line 1", "         line 2", "     *n: No", "", "yes selected");
    }

    /**
     * {@link AbstractConfirmCommand} class test.
     */
    @Test
    public void getYesMenuItemCommandTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("y");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractConfirmCommandImpl command1 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), null, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "     *n: No", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("y");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand2 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command2 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand2, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      y: Yes", "     *n: No", "", "yes selected");
    }

    /**
     * {@link AbstractConfirmCommand} class test.
     */
    @Test
    public void getNoMenuItemSymbolTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("n");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand1 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command1 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "     *n: No", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("no");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand2 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command2 = new AbstractConfirmCommandImpl(new Lines("line"), "yes", new Lines("Yes"), childCommand2, "no", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "    yes: Yes", "    *no: No", "");

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("n");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand = new AbstractExecutionCommandImpl("yes selected", null);
            AbstractConfirmCommandImpl command = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand, null, new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractConfirmCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("n");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand = new AbstractExecutionCommandImpl("yes selected", null);
            AbstractConfirmCommandImpl command = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand, "", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractConfirmCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("n");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand = new AbstractExecutionCommandImpl("yes selected", null);
            AbstractConfirmCommandImpl command = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand, " ", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractConfirmCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is wrong:  ");
        }
    }

    /**
     * {@link AbstractConfirmCommand} class test.
     */
    @Test
    public void getNoMenuItemLabelTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("n");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand1 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command1 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, "n", null, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "     *n: ", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("n");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand2 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command2 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand2, "n", new Lines(), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      y: Yes", "     *n: ", "");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("n");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command childCommand3 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command3 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand3, "n", new Lines(""), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      y: Yes", "     *n: ", "");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("n");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Command childCommand4 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command4 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand4, "n", new Lines("line"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "      y: Yes", "     *n: line", "");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("n");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Command childCommand5 = new AbstractExecutionCommandImpl("yes selected", null);
        AbstractConfirmCommandImpl command5 = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand5, "n", new Lines("line 1", "line 2"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "      y: Yes", "     *n: line 1", "         line 2", "");
    }

    /**
     * {@link AbstractConfirmCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("y");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command parentCommand1 = new AbstractExecutionCommandImpl("parent command", null);
        Command childCommand1 = new AbstractExecutionCommandImpl("child command", null);
        AbstractConfirmCommandImpl command1 = new AbstractConfirmCommandImpl(parentCommand1, new Lines("line"), "y", new Lines("Yes"), childCommand1, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "     *n: No", "", "child command");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("n");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command parentCommand2 = new AbstractExecutionCommandImpl("parent command", null);
        Command childCommand2 = new AbstractExecutionCommandImpl("child command", null);
        AbstractConfirmCommandImpl command2 = new AbstractConfirmCommandImpl(parentCommand2, new Lines("line"), "y", new Lines("Yes"), childCommand2, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      y: Yes", "     *n: No", "", "parent command");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command parentCommand3 = new AbstractExecutionCommandImpl("parent command", null);
        Command childCommand3 = new AbstractExecutionCommandImpl("child command", null);
        AbstractConfirmCommandImpl command3 = new AbstractConfirmCommandImpl(parentCommand3, new Lines("line"), "y", new Lines("Yes"), childCommand3, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      y: Yes", "     *n: No", "", "parent command");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("x", "");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Command parentCommand4 = new AbstractExecutionCommandImpl("parent command", null);
        Command childCommand4 = new AbstractExecutionCommandImpl("child command", null);
        AbstractConfirmCommandImpl command4 = new AbstractConfirmCommandImpl(parentCommand4, new Lines("line"), "y", new Lines("Yes"), childCommand4, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "      y: Yes", "     *n: No", "wrong: <x>", "", "line", "      y: Yes", "     *n: No", "", "parent command");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("y");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Command parentCommand5 = new AbstractExecutionCommandImpl("parent command", null);
        AbstractConfirmCommandImpl command5 = new AbstractConfirmCommandImpl(parentCommand5, new Lines("line"), "y", new Lines("Yes"), null, "n", new Lines("No"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, "wrong: <%s>");
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "      y: Yes", "     *n: No", "", "parent command");
    }

    /**
     * {@link AbstractConfirmCommand} class test.
     */
    @Test
    public void resetTest() {
        Command childCommand = new AbstractExecutionCommandImpl("child command", null);
        AbstractConfirmCommandImpl command = new AbstractConfirmCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand, "n", new Lines("No"), 3, "wrong: <%s>");

        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("x", "y");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Context context1 = new Context();
        commandRunner1.execute(command, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "  y: Yes", " *n: No", "wrong: <x>", "", "line", "  y: Yes", " *n: No", "", "child command");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("x", "y");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Context context2 = new Context();
        context2.putValue(AbstractConfirmCommandImpl.CONTEXT_RESET, new Object());
        commandRunner2.execute(command, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "  y: Yes", " *n: No", "wrong: <x>", "", "line", "  y: Yes", " *n: No", "", "child command");

        command.reset();

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("x", "r!y");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Context context3 = new Context();
        context3.putValue(AbstractConfirmCommandImpl.CONTEXT_RESET, new Object());
        commandRunner3.execute(command, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "r!", " r!y: Yes", "      r!", "*r!n: No", "      r!", "r!wrong: <x>", "", "line", "r!", " r!y: Yes", "      r!", "*r!n: No", "      r!", "");
    }

}
