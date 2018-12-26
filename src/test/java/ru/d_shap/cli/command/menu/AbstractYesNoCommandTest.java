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
import ru.d_shap.cli.data.Lines;

/**
 * Tests for {@link AbstractYesNoCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractYesNoCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractYesNoCommandTest() {
        super();
    }

    /**
     * {@link AbstractYesNoCommand} class test.
     */
    @Test
    public void getYesMenuItemSymbolTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("y");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand11 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand12 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCommandImpl command1 = new AbstractYesNoCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand11, "n", new Lines("No"), childCommand12, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "", "yes selected");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("yes");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand21 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand22 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCommandImpl command2 = new AbstractYesNoCommandImpl(new Lines("line"), "yes", new Lines("Yes"), childCommand21, "no", new Lines("No"), childCommand22, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "    yes: Yes", "     no: No", "", "yes selected");

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("y");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCommandImpl command = new AbstractYesNoCommandImpl(new Lines("line"), null, new Lines("Yes"), childCommand1, "n", new Lines("No"), childCommand2, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("y");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCommandImpl command = new AbstractYesNoCommandImpl(new Lines("line"), "", new Lines("Yes"), childCommand1, "n", new Lines("No"), childCommand2, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("y");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCommandImpl command = new AbstractYesNoCommandImpl(new Lines("line"), " ", new Lines("Yes"), childCommand1, "n", new Lines("No"), childCommand2, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is wrong:  ");
        }
    }

    /**
     * {@link AbstractYesNoCommand} class test.
     */
    @Test
    public void getYesMenuItemLabelTest() {

    }

    /**
     * {@link AbstractYesNoCommand} class test.
     */
    @Test
    public void getYesMenuItemCommandTest() {

    }

    /**
     * {@link AbstractYesNoCommand} class test.
     */
    @Test
    public void getNoMenuItemSymbolTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("n");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command childCommand11 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand12 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCommandImpl command1 = new AbstractYesNoCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand11, "n", new Lines("No"), childCommand12, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      y: Yes", "      n: No", "", "no selected");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("no");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command childCommand21 = new AbstractExecutionCommandImpl("yes selected");
        Command childCommand22 = new AbstractExecutionCommandImpl("no selected");
        AbstractYesNoCommandImpl command2 = new AbstractYesNoCommandImpl(new Lines("line"), "yes", new Lines("Yes"), childCommand21, "no", new Lines("No"), childCommand22, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "    yes: Yes", "     no: No", "", "no selected");

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("n");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCommandImpl command = new AbstractYesNoCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, null, new Lines("No"), childCommand2, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("n");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCommandImpl command = new AbstractYesNoCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, "", new Lines("No"), childCommand2, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("n");
            CommandRunner commandRunner = new CommandRunner(os, is);
            Command childCommand1 = new AbstractExecutionCommandImpl("yes selected");
            Command childCommand2 = new AbstractExecutionCommandImpl("no selected");
            AbstractYesNoCommandImpl command = new AbstractYesNoCommandImpl(new Lines("line"), "y", new Lines("Yes"), childCommand1, " ", new Lines("No"), childCommand2, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
            commandRunner.execute(command);
            Assertions.fail("AbstractYesNoCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is wrong:  ");
        }
    }

    /**
     * {@link AbstractYesNoCommand} class test.
     */
    @Test
    public void getNoMenuItemLabelTest() {

    }

    /**
     * {@link AbstractYesNoCommand} class test.
     */
    @Test
    public void getNoMenuItemCommandTest() {

    }

    /**
     * {@link AbstractYesNoCommand} class test.
     */
    @Test
    public void executeTest() {

    }

}
