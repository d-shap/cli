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
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;
import ru.d_shap.cli.CommandRunner;
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
        AbstractMenuCommandImpl command1 = new AbstractMenuCommandImpl(null, new Lines(), getOptions(new MenuItem("s", "label")), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("", "      s: label", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("s");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        AbstractMenuCommandImpl command2 = new AbstractMenuCommandImpl(null, new Lines(""), getOptions(new MenuItem("s", "label")), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("", "      s: label", "");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("s");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        AbstractMenuCommandImpl command3 = new AbstractMenuCommandImpl(null, new Lines("line"), getOptions(new MenuItem("s", "label")), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner3.execute(command3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("line", "      s: label", "");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("s");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        AbstractMenuCommandImpl command4 = new AbstractMenuCommandImpl(null, new Lines("line1", "line2"), getOptions(new MenuItem("s", "label")), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
        commandRunner4.execute(command4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("line1", "line2", "      s: label", "");

        try {
            ByteArrayOutputStream os5 = createOutputStream();
            InputStream is5 = createInputStream("s");
            CommandRunner commandRunner5 = new CommandRunner(os5, is5);
            AbstractMenuCommandImpl command5 = new AbstractMenuCommandImpl(null, null, getOptions(new MenuItem("s", "label")), AbstractMenuCommand.DEFAULT_SYMBOL_LENGTH, AbstractMenuCommand.NO_DEFAULT_OPTION_INDEX, null);
            commandRunner5.execute(command5);
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

    }

    /**
     * {@link AbstractMenuCommand} class test.
     */
    @Test
    public void getSymbolLengthTest() {

    }

    /**
     * {@link AbstractMenuCommand} class test.
     */
    @Test
    public void getDefaultOptionIndexTest() {

    }

    /**
     * {@link AbstractMenuCommand} class test.
     */
    @Test
    public void getWrongInputMessageTest() {

    }

    private List<Option> getOptions(final Option... options) {
        return Arrays.asList(options);
    }

}
