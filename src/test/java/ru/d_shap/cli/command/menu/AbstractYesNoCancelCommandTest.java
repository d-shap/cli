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
 * Tests for {@link AbstractYesNoCancelCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractYesNoCancelCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractYesNoCancelCommandTest() {
        super();
    }

    /**
     * {@link AbstractYesNoCancelCommand} class test.
     */
    @Test
    public void getYesMenuItemSymbolTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("y");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand11 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand12 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command1 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand11, "n", new Lines("No"), childCommand12, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "", "yes selected");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("yes");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand21 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand22 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command2 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "yes", new Lines("Yes"), childCommand21, "no", new Lines("No"), childCommand22, "cancel", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "    yes: Yes", "     no: No", " cancel: Cancel", "", "yes selected");

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("y");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCancelCommandImpl command = new AbstractYesNoCancelCommandImpl(new Lines("line"), null, new Lines("Yes"), childCommand1, "n", new Lines("No"), childCommand2, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCancelCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("y");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCancelCommandImpl command = new AbstractYesNoCancelCommandImpl(new Lines("line"), "", new Lines("Yes"), childCommand1, "n", new Lines("No"), childCommand2, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCancelCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("y");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCancelCommandImpl command = new AbstractYesNoCancelCommandImpl(new Lines("line"), " ", new Lines("Yes"), childCommand1, "n", new Lines("No"), childCommand2, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCancelCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is wrong:  ");
        }
    }

    /**
     * {@link AbstractYesNoCancelCommand} class test.
     */
    @Test
    public void getYesMenuItemLabelTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("y");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand11 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand12 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command1 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", null, childCommand11, "n", new Lines("No"), childCommand12, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: ", "      n: No", "      c: Cancel", "", "yes selected");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("y");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand21 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand22 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command2 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines(), childCommand21, "n", new Lines("No"), childCommand22, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      y: ", "      n: No", "      c: Cancel", "", "yes selected");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("y");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command childCommand31 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand32 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command3 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines(""), childCommand31, "n", new Lines("No"), childCommand32, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      y: ", "      n: No", "      c: Cancel", "", "yes selected");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("y");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Command childCommand41 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand42 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command4 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("line"), childCommand41, "n", new Lines("No"), childCommand42, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "      y: line", "      n: No", "      c: Cancel", "", "yes selected");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("y");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Command childCommand51 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand52 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command5 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("line 1", "line 2"), childCommand51, "n", new Lines("No"), childCommand52, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "      y: line 1", "         line 2", "      n: No", "      c: Cancel", "", "yes selected");
    }

    /**
     * {@link AbstractYesNoCancelCommand} class test.
     */
    @Test
    public void getYesMenuItemCommandTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("y");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractYesNoCancelCommandImpl command1 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), null, "n", new Lines("No"), null, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("y");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand21 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand22 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command2 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand21, "n", new Lines("No"), childCommand22, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "", "yes selected");
    }

    /**
     * {@link AbstractYesNoCancelCommand} class test.
     */
    @Test
    public void getNoMenuItemSymbolTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("n");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand11 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand12 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command1 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand11, "n", new Lines("No"), childCommand12, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "", "no selected");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("no");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand21 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand22 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command2 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "yes", new Lines("Yes"), childCommand21, "no", new Lines("No"), childCommand22, "cancel", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "    yes: Yes", "     no: No", " cancel: Cancel", "", "no selected");

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("n");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCancelCommandImpl command = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, null, new Lines("No"), childCommand2, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.assertThat(getLines(os)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "", "no selected");
            Assertions.fail("AbstractYesNoCancelCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("n");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCancelCommandImpl command = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, "", new Lines("No"), childCommand2, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.assertThat(getLines(os)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "", "no selected");
            Assertions.fail("AbstractYesNoCancelCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("n");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCancelCommandImpl command = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, " ", new Lines("No"), childCommand2, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.assertThat(getLines(os)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "", "no selected");
            Assertions.fail("AbstractYesNoCancelCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is wrong:  ");
        }
    }

    /**
     * {@link AbstractYesNoCancelCommand} class test.
     */
    @Test
    public void getNoMenuItemLabelTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("n");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand11 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand12 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command1 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand11, "n", null, childCommand12, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "      n: ", "      c: Cancel", "", "no selected");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("n");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand21 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand22 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command2 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand21, "n", new Lines(), childCommand22, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      y: Yes", "      n: ", "      c: Cancel", "", "no selected");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("n");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command childCommand31 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand32 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command3 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand31, "n", new Lines(""), childCommand32, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      y: Yes", "      n: ", "      c: Cancel", "", "no selected");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("n");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Command childCommand41 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand42 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command4 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand41, "n", new Lines("line"), childCommand42, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "      y: Yes", "      n: line", "      c: Cancel", "", "no selected");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("n");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Command childCommand51 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand52 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command5 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand51, "n", new Lines("line 1", "line 2"), childCommand52, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "      y: Yes", "      n: line 1", "         line 2", "      c: Cancel", "", "no selected");
    }

    /**
     * {@link AbstractYesNoCancelCommand} class test.
     */
    @Test
    public void getNoMenuItemCommandTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("n");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractYesNoCancelCommandImpl command1 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), null, "n", new Lines("No"), null, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("n");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand21 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand22 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command2 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand21, "n", new Lines("No"), childCommand22, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "", "no selected");
    }

    /**
     * {@link AbstractYesNoCancelCommand} class test.
     */
    @Test
    public void getCancelMenuItemSymbolTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("c");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand11 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand12 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command1 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand11, "n", new Lines("No"), childCommand12, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("cancel");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand21 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand22 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command2 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "yes", new Lines("Yes"), childCommand21, "no", new Lines("No"), childCommand22, "cancel", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "    yes: Yes", "     no: No", " cancel: Cancel", "");

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("c");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCancelCommandImpl command = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, "n", new Lines("No"), childCommand2, null, new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCancelCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("c");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCancelCommandImpl command = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, "n", new Lines("No"), childCommand2, "", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCancelCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("c");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCancelCommandImpl command = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, "n", new Lines("No"), childCommand2, " ", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCancelCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is wrong:  ");
        }
    }

    /**
     * {@link AbstractYesNoCancelCommand} class test.
     */
    @Test
    public void getCancelMenuItemLabelTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("c");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand11 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand12 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command1 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand11, "n", new Lines("No"), childCommand12, "c", null, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: ", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("c");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand21 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand22 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command2 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand21, "n", new Lines("No"), childCommand22, "c", new Lines(), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: ", "");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("c");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command childCommand31 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand32 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command3 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand31, "n", new Lines("No"), childCommand32, "c", new Lines(""), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: ", "");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("c");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Command childCommand41 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand42 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command4 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand41, "n", new Lines("No"), childCommand42, "c", new Lines("line"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: line", "");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("c");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Command childCommand51 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand52 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCancelCommandImpl command5 = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand51, "n", new Lines("No"), childCommand52, "c", new Lines("line 1", "line 2"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: line 1", "         line 2", "");
    }

    /**
     * {@link AbstractYesNoCancelCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("y");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command parentCommand1 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand11 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand12 = new AbstractExecutionCommandImpl("child command 2");
        AbstractYesNoCancelCommandImpl command1 = new AbstractYesNoCancelCommandImpl(parentCommand1, new Lines("line"), "y", new Lines("Yes"), childCommand11, "n", new Lines("No"), childCommand12, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "", "child command 1");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("n");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command parentCommand2 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand21 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand22 = new AbstractExecutionCommandImpl("child command 2");
        AbstractYesNoCancelCommandImpl command2 = new AbstractYesNoCancelCommandImpl(parentCommand2, new Lines("line"), "y", new Lines("Yes"), childCommand21, "n", new Lines("No"), childCommand22, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "", "child command 2");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("c");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command parentCommand3 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand31 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand32 = new AbstractExecutionCommandImpl("child command 2");
        AbstractYesNoCancelCommandImpl command3 = new AbstractYesNoCancelCommandImpl(parentCommand3, new Lines("line"), "y", new Lines("Yes"), childCommand31, "n", new Lines("No"), childCommand32, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "", "parent command");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("", "c");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Command parentCommand4 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand41 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand42 = new AbstractExecutionCommandImpl("child command 2");
        AbstractYesNoCancelCommandImpl command4 = new AbstractYesNoCancelCommandImpl(parentCommand4, new Lines("line"), "y", new Lines("Yes"), childCommand41, "n", new Lines("No"), childCommand42, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "wrong: <>", "", "line", "      y: Yes", "      n: No", "      c: Cancel", "", "parent command");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Command parentCommand5 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand51 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand52 = new AbstractExecutionCommandImpl("child command 2");
        AbstractYesNoCancelCommandImpl command5 = new AbstractYesNoCancelCommandImpl(parentCommand5, new Lines("line"), "y", new Lines("Yes"), childCommand51, "n", new Lines("No"), childCommand52, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, 2, "wrong: <%s>");
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "     *c: Cancel", "", "parent command");

        ByteArrayOutputStream os6 = createOutputStream();
        InputStream is6 = createInputStream("x", "c");
        CommandRunner commandRunner6 = new CommandRunner(os6, is6);
        Command parentCommand6 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand61 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand62 = new AbstractExecutionCommandImpl("child command 2");
        AbstractYesNoCancelCommandImpl command6 = new AbstractYesNoCancelCommandImpl(parentCommand6, new Lines("line"), "y", new Lines("Yes"), childCommand61, "n", new Lines("No"), childCommand62, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner6.execute(command6);
        Assertions.assertThat(getLines(os6)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "wrong: <x>", "", "line", "      y: Yes", "      n: No", "      c: Cancel", "", "parent command");

        ByteArrayOutputStream os7 = createOutputStream();
        InputStream is7 = createInputStream("y");
        CommandRunner commandRunner7 = new CommandRunner(os7, is7);
        Command parentCommand7 = new AbstractExecutionCommandImpl("parent command");
        AbstractYesNoCancelCommandImpl command7 = new AbstractYesNoCancelCommandImpl(parentCommand7, new Lines("line"), "y", new Lines("Yes"), null, "n", new Lines("No"), null, "c", new Lines("Cancel"), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner7.execute(command7);
        Assertions.assertThat(getLines(os7)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "      c: Cancel", "", "parent command");
    }

    /**
     * {@link AbstractYesNoCancelCommand} class test.
     */
    @Test
    public void resetTest() {
        Command childCommand1 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand2 = new AbstractExecutionCommandImpl("child command 2");
        AbstractYesNoCancelCommandImpl command = new AbstractYesNoCancelCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, "n", new Lines("No"), childCommand2, "c", new Lines("Cancel"), 3, 1, "wrong: <%s>");

        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("x", "y");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Context context1 = new Context();
        commandRunner1.execute(command, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "  y: Yes", " *n: No", "  c: Cancel", "wrong: <x>", "", "line", "  y: Yes", " *n: No", "  c: Cancel", "", "child command 1");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("x", "n");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Context context2 = new Context();
        commandRunner2.execute(command, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "  y: Yes", " *n: No", "  c: Cancel", "wrong: <x>", "", "line", "  y: Yes", " *n: No", "  c: Cancel", "", "child command 2");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("x", "y");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Context context3 = new Context();
        context3.putValue(AbstractYesNoCancelCommandImpl.CONTEXT_RESET, new Object());
        commandRunner3.execute(command, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "  y: Yes", " *n: No", "  c: Cancel", "wrong: <x>", "", "line", "  y: Yes", " *n: No", "  c: Cancel", "", "child command 1");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("x", "n");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Context context4 = new Context();
        context4.putValue(AbstractYesNoCancelCommandImpl.CONTEXT_RESET, new Object());
        commandRunner4.execute(command, context4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "  y: Yes", " *n: No", "  c: Cancel", "wrong: <x>", "", "line", "  y: Yes", " *n: No", "  c: Cancel", "", "child command 2");

        command.reset();

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("x", "r!y");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Context context5 = new Context();
        context5.putValue(AbstractYesNoCancelCommandImpl.CONTEXT_RESET, new Object());
        commandRunner5.execute(command, context5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "r!", " r!y: Yes", "      r!", " r!n: No", "      r!", " r!c: Cancel", "      r!", "r!wrong: <x>", "", "line", "r!", " r!y: Yes", "      r!", " r!n: No", "      r!", " r!c: Cancel", "      r!", "");

        ByteArrayOutputStream os6 = createOutputStream();
        InputStream is6 = createInputStream("x", "r!n");
        CommandRunner commandRunner6 = new CommandRunner(os6, is6);
        Context context6 = new Context();
        context6.putValue(AbstractYesNoCancelCommandImpl.CONTEXT_RESET, new Object());
        commandRunner6.execute(command, context6);
        Assertions.assertThat(getLines(os6)).containsExactlyInOrder("line", "r!", " r!y: Yes", "      r!", " r!n: No", "      r!", " r!c: Cancel", "      r!", "r!wrong: <x>", "", "line", "r!", " r!y: Yes", "      r!", " r!n: No", "      r!", " r!c: Cancel", "      r!", "");
    }

}
