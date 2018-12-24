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
package ru.d_shap.cli;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link CommandRunner}.
 *
 * @author Dmitry Shapovalov
 */
public final class CommandRunnerTest extends BaseCliTest {

    private static final String CHARSET_NAME = "Cp1251";

    /**
     * Test class constructor.
     */
    public CommandRunnerTest() {
        super();
    }

    /**
     * {@link CommandRunner} class test.
     */
    @Test
    public void getWriterTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream();
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        PrintWriter printWriter1 = commandRunner1.getWriter();
        printWriter1.println("Строка 1");
        printWriter1.println("Строка 2");
        printWriter1.flush();
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("Строка 1", "Строка 2");

        ByteArrayOutputStream os21 = createOutputStream();
        ByteArrayOutputStream os22 = createOutputStream();
        InputStream is2 = createInputStream();
        CommandRunner commandRunner2 = new CommandRunner(os21, is2, os22);
        PrintWriter printWriter2 = commandRunner2.getWriter();
        printWriter2.println("Строка 1");
        printWriter2.println("Строка 2");
        printWriter2.flush();
        Assertions.assertThat(getLines(os21)).containsExactlyInOrder("Строка 1", "Строка 2");
        Assertions.assertThat(getLines(os22)).containsExactlyInOrder("Строка 1", "Строка 2");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream();
        CommandRunner commandRunner3 = new CommandRunner(os3, is3, CHARSET_NAME);
        PrintWriter printWriter3 = commandRunner3.getWriter();
        printWriter3.println("Строка 1");
        printWriter3.println("Строка 2");
        printWriter3.flush();
        Assertions.assertThat(getLines(CHARSET_NAME, os3)).containsExactlyInOrder("Строка 1", "Строка 2");

        ByteArrayOutputStream os41 = createOutputStream();
        ByteArrayOutputStream os42 = createOutputStream();
        InputStream is4 = createInputStream();
        CommandRunner commandRunner4 = new CommandRunner(os41, is4, os42, CHARSET_NAME);
        PrintWriter printWriter4 = commandRunner4.getWriter();
        printWriter4.println("Строка 1");
        printWriter4.println("Строка 2");
        printWriter4.flush();
        Assertions.assertThat(getLines(CHARSET_NAME, os41)).containsExactlyInOrder("Строка 1", "Строка 2");
        Assertions.assertThat(getLines(CHARSET_NAME, os42)).containsExactlyInOrder("Строка 1", "Строка 2");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream();
        CommandRunner commandRunner5 = new CommandRunner(os5, is5, Charset.forName(CHARSET_NAME));
        PrintWriter printWriter5 = commandRunner5.getWriter();
        printWriter5.println("Строка 1");
        printWriter5.println("Строка 2");
        printWriter5.flush();
        Assertions.assertThat(getLines(CHARSET_NAME, os5)).containsExactlyInOrder("Строка 1", "Строка 2");

        ByteArrayOutputStream os61 = createOutputStream();
        ByteArrayOutputStream os62 = createOutputStream();
        InputStream is6 = createInputStream();
        CommandRunner commandRunner6 = new CommandRunner(os61, is6, os62, Charset.forName(CHARSET_NAME));
        PrintWriter printWriter6 = commandRunner6.getWriter();
        printWriter6.println("Строка 1");
        printWriter6.println("Строка 2");
        printWriter6.flush();
        Assertions.assertThat(getLines(CHARSET_NAME, os61)).containsExactlyInOrder("Строка 1", "Строка 2");
        Assertions.assertThat(getLines(CHARSET_NAME, os62)).containsExactlyInOrder("Строка 1", "Строка 2");
    }

    /**
     * {@link CommandRunner} class test.
     */
    @Test
    public void getReaderTest() {

    }

    /**
     * {@link CommandRunner} class test.
     */
    @Test
    public void executeTest() {

    }

}
