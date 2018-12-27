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

    }

    /**
     * {@link AbstractInputCommand} class test.
     */
    @Test
    public void getDefaultMessageTest() {

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
