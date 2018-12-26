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
import ru.d_shap.cli.CommandRunner;

/**
 * Tests for {@link MenuSeparator}.
 *
 * @author Dmitry Shapovalov
 */
public final class MenuSeparatorTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public MenuSeparatorTest() {
        super();
    }

    /**
     * {@link MenuSeparator} class test.
     */
    @Test
    public void printTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream();
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        MenuSeparator menuSeparator1 = new MenuSeparator();
        menuSeparator1.print(commandRunner1.getWriter(), -1, false);
        commandRunner1.getWriter().flush();
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream();
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        MenuSeparator menuSeparator2 = new MenuSeparator();
        menuSeparator2.print(commandRunner2.getWriter(), -1, true);
        commandRunner2.getWriter().flush();
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream();
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        MenuSeparator menuSeparator3 = new MenuSeparator();
        menuSeparator3.print(commandRunner3.getWriter(), 0, false);
        commandRunner3.getWriter().flush();
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream();
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        MenuSeparator menuSeparator4 = new MenuSeparator();
        menuSeparator4.print(commandRunner4.getWriter(), 0, true);
        commandRunner4.getWriter().flush();
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream();
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        MenuSeparator menuSeparator5 = new MenuSeparator();
        menuSeparator5.print(commandRunner5.getWriter(), 5, false);
        commandRunner5.getWriter().flush();
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("");

        ByteArrayOutputStream os6 = createOutputStream();
        InputStream is6 = createInputStream();
        CommandRunner commandRunner6 = new CommandRunner(os6, is6);
        MenuSeparator menuSeparator6 = new MenuSeparator();
        menuSeparator6.print(commandRunner6.getWriter(), 5, true);
        commandRunner6.getWriter().flush();
        Assertions.assertThat(getLines(os6)).containsExactlyInOrder("");
    }

}
