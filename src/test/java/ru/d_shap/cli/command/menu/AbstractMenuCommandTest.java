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
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;
import ru.d_shap.cli.Command;
import ru.d_shap.cli.CommandRunner;
import ru.d_shap.cli.command.AbstractExecutionCommandImpl;
import ru.d_shap.cli.command.CommandDefinitionException;
import ru.d_shap.cli.data.Lines;

/**
 * Tests for {@link AbstractMenuCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractMenuCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractMenuCommandTest() {
        super();
    }

    /**
     * {@link AbstractMenuCommand} class test.
     */
    @Test
    public void getHeaderTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("s");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        AbstractMenuCommandImpl command1 = new AbstractMenuCommandImpl(new Lines(), getOptions(new MenuItem("s", "label")), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("", "      s: label", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("s");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractMenuCommandImpl command2 = new AbstractMenuCommandImpl(new Lines(""), getOptions(new MenuItem("s", "label")), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("", "      s: label", "");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("s");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractMenuCommandImpl command3 = new AbstractMenuCommandImpl(new Lines("line"), getOptions(new MenuItem("s", "label")), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      s: label", "");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("s");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        AbstractMenuCommandImpl command4 = new AbstractMenuCommandImpl(new Lines("line 1", "line 2"), getOptions(new MenuItem("s", "label")), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line 1", "line 2", "      s: label", "");

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream();
            CommandRunner commandRunner = new CommandRunner(os, is);
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(null, getOptions(new MenuItem("s", "label")), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Header is not defined");
        }
    }

    /**
     * {@link AbstractMenuCommand} class test.
     */
    @Test
    public void getOptionsTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        List<Option> options1 = getOptions(new MenuItem("1", "Option 1"));
        AbstractMenuCommandImpl command1 = new AbstractMenuCommandImpl(new Lines("line"), options1, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      1: Option 1", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("1");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        List<Option> options2 = getOptions(new MenuItem("1", "Option 1"), new MenuItem("2", "Option 2"), new MenuSeparator(), new MenuItem("3", "Option 3"));
        AbstractMenuCommandImpl command2 = new AbstractMenuCommandImpl(new Lines("line"), options2, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "      3: Option 3", "");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("1");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        List<Option> options3 = getOptions(new MenuItem("1", new Lines("Option 1 line 1", "Option 1 line 2")), new MenuItem("2", new Lines("Option 2 line 1", "Option 2 line 2")));
        AbstractMenuCommandImpl command3 = new AbstractMenuCommandImpl(new Lines("line"), options3, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      1: Option 1 line 1", "         Option 1 line 2", "      2: Option 2 line 1", "         Option 2 line 2", "");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("1");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        List<Option> options4 = getOptions(new MenuItem("1", (String) null));
        AbstractMenuCommandImpl command4 = new AbstractMenuCommandImpl(new Lines("line"), options4, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "      1: ", "");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("1");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        List<Option> options5 = getOptions(new MenuItem("1", (Lines) null));
        AbstractMenuCommandImpl command5 = new AbstractMenuCommandImpl(new Lines("line"), options5, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "      1: ", "");

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream();
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = null;
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Options are not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream();
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions();
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Options are not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream();
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuSeparator(), new MenuSeparator());
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Options are not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream();
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem(null, "Option 2"));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream();
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem("", "Option 2"));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not defined");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream();
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem(" ", "Option 2"));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is wrong:  ");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream();
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem(" 2 ", "Option 2"));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is wrong:  2 ");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream();
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem("2", "Option 2"), new MenuItem("4", "Option 3"), new MenuItem("4", "Option 4"));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol is not unique: 4");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream();
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("12345", "Option 1"), new MenuSeparator(), new MenuItem("234567", "Option 2"));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, 5, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option symbol length is too large: 234567");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream();
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem("2", "Option 2"), new SelectableOptionImpl());
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Option label is not defined");
        }
    }

    /**
     * {@link AbstractMenuCommand} class test.
     */
    @Test
    public void getSymbolLengthTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        List<Option> options1 = getOptions(new MenuItem("1", "Option 1"), new MenuItem("2", new Lines("Option 2 line 1", "Option 2 line 2")));
        AbstractMenuCommandImpl command1 = new AbstractMenuCommandImpl(new Lines("line"), options1, 5, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "    1: Option 1", "    2: Option 2 line 1", "       Option 2 line 2", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("1");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        List<Option> options2 = getOptions(new MenuItem("1", "Option 1"), new MenuItem("2", new Lines("Option 2 line 1", "Option 2 line 2")));
        AbstractMenuCommandImpl command2 = new AbstractMenuCommandImpl(new Lines("line"), options2, 3, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "  1: Option 1", "  2: Option 2 line 1", "     Option 2 line 2", "");

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("1");
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("1", "Option 1"), new MenuItem("2", new Lines("Option 2 line 1", "Option 2 line 2")));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, 0, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Symbol length is not positive: 0");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("1");
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("1", "Option 1"), new MenuItem("2", new Lines("Option 2 line 1", "Option 2 line 2")));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, -1, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Symbol length is not positive: -1");
        }
    }

    /**
     * {@link AbstractMenuCommand} class test.
     */
    @Test
    public void getDefaultOptionIndexTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        List<Option> options1 = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem("2", "Option 2"));
        AbstractMenuCommandImpl command1 = new AbstractMenuCommandImpl(new Lines("line"), options1, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, -1, null);
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      1: Option 1", "", "      2: Option 2", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        List<Option> options2 = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem("2", "Option 2"));
        AbstractMenuCommandImpl command2 = new AbstractMenuCommandImpl(new Lines("line"), options2, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, 0, null);
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "     *1: Option 1", "", "      2: Option 2", "");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        List<Option> options3 = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem("2", "Option 2"));
        AbstractMenuCommandImpl command3 = new AbstractMenuCommandImpl(new Lines("line"), options3, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, 2, null);
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      1: Option 1", "", "     *2: Option 2", "");

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("");
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem("2", "Option 2"));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, 3, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Default option is not selectable: 3");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("");
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem("2", "Option 2"));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, 4, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Default option is not selectable: 4");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("");
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuSeparator(), new MenuItem("1", "Option 1"), new MenuItem("2", "Option 2"));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, 0, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Default option is not selectable: 0");
        }

        try {
            ByteArrayOutputStream os = createOutputStream();
            InputStream is = createInputStream("");
            CommandRunner commandRunner = new CommandRunner(os, is);
            List<Option> options = getOptions(new MenuItem("1", "Option 1"), new MenuSeparator(), new MenuItem("2", "Option 2"));
            AbstractMenuCommandImpl command = new AbstractMenuCommandImpl(new Lines("line"), options, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, 1, null);
            commandRunner.execute(command);
            Assertions.fail("AbstractMenuCommand test fail");
        } catch (CommandDefinitionException ex) {
            Assertions.assertThat(ex).hasMessage("Default option is not selectable: 1");
        }
    }

    /**
     * {@link AbstractMenuCommand} class test.
     */
    @Test
    public void getWrongInputMessageTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("x", "1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        List<Option> options1 = getOptions(new MenuItem("1", "Option 1"), new MenuItem("2", "Option 2"));
        AbstractMenuCommandImpl command1 = new AbstractMenuCommandImpl(new Lines("line"), options1, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "line", "      1: Option 1", "      2: Option 2", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("x", "1");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        List<Option> options2 = getOptions(new MenuItem("1", "Option 1"), new MenuItem("2", "Option 2"));
        AbstractMenuCommandImpl command2 = new AbstractMenuCommandImpl(new Lines("line"), options2, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "", "line", "      1: Option 1", "      2: Option 2", "");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("x", "1");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        List<Option> options3 = getOptions(new MenuItem("1", "Option 1"), new MenuItem("2", "Option 2"));
        AbstractMenuCommandImpl command3 = new AbstractMenuCommandImpl(new Lines("line"), options3, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, " ");
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", " ", "", "line", "      1: Option 1", "      2: Option 2", "");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("x", "1");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        List<Option> options4 = getOptions(new MenuItem("1", "Option 1"), new MenuItem("2", "Option 2"));
        AbstractMenuCommandImpl command4 = new AbstractMenuCommandImpl(new Lines("line"), options4, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong");
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "wrong", "", "line", "      1: Option 1", "      2: Option 2", "");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("x", "1");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        List<Option> options5 = getOptions(new MenuItem("1", "Option 1"), new MenuItem("2", "Option 2"));
        AbstractMenuCommandImpl command5 = new AbstractMenuCommandImpl(new Lines("line"), options5, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "wrong: <x>", "", "line", "      1: Option 1", "      2: Option 2", "");
    }

    /**
     * {@link AbstractMenuCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("1");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command parentCommand1 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand11 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand12 = new AbstractExecutionCommandImpl("child command 2");
        List<Option> options1 = getOptions(new MenuItem("1", "Option 1", childCommand11), new MenuItem("2", "Option 2", childCommand12), new MenuSeparator(), new MenuItem("3", "Option 3"));
        AbstractMenuCommandImpl command1 = new AbstractMenuCommandImpl(parentCommand1, new Lines("line"), options1, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "      3: Option 3", "", "child command 1");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("2");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command parentCommand2 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand21 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand22 = new AbstractExecutionCommandImpl("child command 2");
        List<Option> options2 = getOptions(new MenuItem("1", "Option 1", childCommand21), new MenuItem("2", "Option 2", childCommand22), new MenuSeparator(), new MenuItem("3", "Option 3"));
        AbstractMenuCommandImpl command2 = new AbstractMenuCommandImpl(parentCommand2, new Lines("line"), options2, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "      3: Option 3", "", "child command 2");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("3");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command parentCommand3 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand31 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand32 = new AbstractExecutionCommandImpl("child command 2");
        List<Option> options3 = getOptions(new MenuItem("1", "Option 1", childCommand31), new MenuItem("2", "Option 2", childCommand32), new MenuSeparator(), new MenuItem("3", "Option 3"));
        AbstractMenuCommandImpl command3 = new AbstractMenuCommandImpl(parentCommand3, new Lines("line"), options3, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "      3: Option 3", "", "parent command");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("", "3");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Command parentCommand4 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand41 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand42 = new AbstractExecutionCommandImpl("child command 2");
        List<Option> options4 = getOptions(new MenuItem("1", "Option 1", childCommand41), new MenuItem("2", "Option 2", childCommand42), new MenuSeparator(), new MenuItem("3", "Option 3"));
        AbstractMenuCommandImpl command4 = new AbstractMenuCommandImpl(parentCommand4, new Lines("line"), options4, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "      3: Option 3", "wrong: <>", "", "line", "      1: Option 1", "      2: Option 2", "", "      3: Option 3", "", "parent command");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("x", "3");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Command parentCommand5 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand51 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand52 = new AbstractExecutionCommandImpl("child command 2");
        List<Option> options5 = getOptions(new MenuItem("1", "Option 1", childCommand51), new MenuItem("2", "Option 2", childCommand52), new MenuSeparator(), new MenuItem("3", "Option 3"));
        AbstractMenuCommandImpl command5 = new AbstractMenuCommandImpl(parentCommand5, new Lines("line"), options5, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, "wrong: <%s>");
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "      3: Option 3", "wrong: <x>", "", "line", "      1: Option 1", "      2: Option 2", "", "      3: Option 3", "", "parent command");

        ByteArrayOutputStream os6 = createOutputStream();
        InputStream is6 = createInputStream("2");
        CommandRunner commandRunner6 = new CommandRunner(os6, is6);
        Command parentCommand6 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand61 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand62 = new AbstractExecutionCommandImpl("child command 2");
        List<Option> options6 = getOptions(new MenuItem("1", "Option 1", childCommand61), new MenuItem("2", "Option 2", childCommand62), new MenuSeparator(), new MenuItem("3", "Option 3"));
        AbstractMenuCommandImpl command6 = new AbstractMenuCommandImpl(parentCommand6, new Lines("line"), options6, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, 3, "wrong: <%s>");
        commandRunner6.execute(command6);
        Assertions.assertThat(getLines(os6)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "     *3: Option 3", "", "child command 2");

        ByteArrayOutputStream os7 = createOutputStream();
        InputStream is7 = createInputStream("", "3");
        CommandRunner commandRunner7 = new CommandRunner(os7, is7);
        Command parentCommand7 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand71 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand72 = new AbstractExecutionCommandImpl("child command 2");
        List<Option> options7 = getOptions(new MenuItem("1", "Option 1", childCommand71), new MenuItem("2", "Option 2", childCommand72), new MenuSeparator(), new MenuItem("3", "Option 3"));
        AbstractMenuCommandImpl command7 = new AbstractMenuCommandImpl(parentCommand7, new Lines("line"), options7, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, 3, "wrong: <%s>");
        commandRunner7.execute(command7);
        Assertions.assertThat(getLines(os7)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "     *3: Option 3", "", "parent command");

        ByteArrayOutputStream os8 = createOutputStream();
        InputStream is8 = createInputStream("x", "3");
        CommandRunner commandRunner8 = new CommandRunner(os8, is8);
        Command parentCommand8 = new AbstractExecutionCommandImpl("parent command");
        Command childCommand81 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand82 = new AbstractExecutionCommandImpl("child command 2");
        List<Option> options8 = getOptions(new MenuItem("1", "Option 1", childCommand81), new MenuItem("2", "Option 2", childCommand82), new MenuSeparator(), new MenuItem("3", "Option 3"));
        AbstractMenuCommandImpl command8 = new AbstractMenuCommandImpl(parentCommand8, new Lines("line"), options8, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, 3, "wrong: <%s>");
        commandRunner8.execute(command8);
        Assertions.assertThat(getLines(os8)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "     *3: Option 3", "wrong: <x>", "", "line", "      1: Option 1", "      2: Option 2", "", "     *3: Option 3", "", "parent command");

        ByteArrayOutputStream os9 = createOutputStream();
        InputStream is9 = createInputStream("", "3");
        CommandRunner commandRunner9 = new CommandRunner(os9, is9);
        Command childCommand91 = new AbstractExecutionCommandImpl("child command 1");
        Command childCommand92 = new AbstractExecutionCommandImpl("child command 2");
        List<Option> options9 = getOptions(new MenuItem("1", "Option 1", childCommand91), new MenuItem("2", "Option 2", childCommand92), new MenuSeparator(), new MenuItem("3", "Option 3"));
        AbstractMenuCommandImpl command9 = new AbstractMenuCommandImpl(new Lines("line"), options9, AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, 3, "wrong: <%s>");
        commandRunner9.execute(command9);
        Assertions.assertThat(getLines(os9)).containsExactlyInOrder("line", "      1: Option 1", "      2: Option 2", "", "     *3: Option 3", "");
    }

    /**
     * {@link AbstractMenuCommand} class test.
     */
    @Test
    public void resetTest() {
        // TODO
    }

    private List<Option> getOptions(final Option... options) {
        return Arrays.asList(options);
    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    public static final class SelectableOptionImpl implements SelectableOption {

        /**
         * Create new object.
         */
        public SelectableOptionImpl() {
            super();
        }

        @Override
        public String getSymbol() {
            return "s";
        }

        @Override
        public Lines getLabel() {
            return null;
        }

        @Override
        public void print(final PrintWriter writer, final int length, final boolean isDefault) {
            writer.println(getSymbol());
        }

        @Override
        public boolean isSelected(final String symbol) {
            return false;
        }

        @Override
        public Command getCommand() {
            return null;
        }

    }

}
