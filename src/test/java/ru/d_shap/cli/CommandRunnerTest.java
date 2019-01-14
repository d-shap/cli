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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.command.AbstractCommandImpl;
import ru.d_shap.cli.data.Context;

/**
 * Tests for {@link CommandRunner}.
 *
 * @author Dmitry Shapovalov
 */
public final class CommandRunnerTest extends BaseCliTest {

    private static final String CHARSET_NAME = "Cp1251";

    private static final String WRONG_CHARSET_NAME = "Wrong!!!";

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

        try {
            ByteArrayOutputStream os7 = createOutputStream();
            InputStream is7 = createInputStream();
            new CommandRunner(os7, is7, WRONG_CHARSET_NAME);
            Assertions.fail("CommandRunner test fail");
        } catch (CliIOException ex) {
            Assertions.assertThat(ex).hasCause(UnsupportedEncodingException.class);
        }

        try {
            ByteArrayOutputStream os81 = createOutputStream();
            ByteArrayOutputStream os82 = createOutputStream();
            InputStream is8 = createInputStream();
            new CommandRunner(os81, is8, os82, WRONG_CHARSET_NAME);
            Assertions.fail("CommandRunner test fail");
        } catch (CliIOException ex) {
            Assertions.assertThat(ex).hasCause(UnsupportedEncodingException.class);
        }
    }

    /**
     * {@link CommandRunner} class test.
     */
    @Test
    public void getReaderTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        BufferedReader reader1 = commandRunner1.getReader();
        Assertions.assertThat(getLines(reader1)).containsExactlyInOrder("Строка 1", "Строка 2");

        ByteArrayOutputStream os21 = createOutputStream();
        ByteArrayOutputStream os22 = createOutputStream();
        InputStream is2 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner2 = new CommandRunner(os21, is2, os22);
        BufferedReader reader2 = commandRunner2.getReader();
        Assertions.assertThat(getLines(reader2)).containsExactlyInOrder("Строка 1", "Строка 2");
        commandRunner2.getWriter().flush();
        Assertions.assertThat(getLines(os22)).containsExactlyInOrder("Строка 1", "Строка 2");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStreamWithCharset(CHARSET_NAME, "Строка 1", "Строка 2");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3, CHARSET_NAME);
        BufferedReader reader3 = commandRunner3.getReader();
        Assertions.assertThat(getLines(reader3)).containsExactlyInOrder("Строка 1", "Строка 2");

        ByteArrayOutputStream os41 = createOutputStream();
        ByteArrayOutputStream os42 = createOutputStream();
        InputStream is4 = createInputStreamWithCharset(CHARSET_NAME, "Строка 1", "Строка 2");
        CommandRunner commandRunner4 = new CommandRunner(os41, is4, os42, CHARSET_NAME);
        BufferedReader reader4 = commandRunner4.getReader();
        Assertions.assertThat(getLines(reader4)).containsExactlyInOrder("Строка 1", "Строка 2");
        commandRunner4.getWriter().flush();
        Assertions.assertThat(getLines(CHARSET_NAME, os42)).containsExactlyInOrder("Строка 1", "Строка 2");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStreamWithCharset(CHARSET_NAME, "Строка 1", "Строка 2");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5, Charset.forName(CHARSET_NAME));
        BufferedReader reader5 = commandRunner5.getReader();
        Assertions.assertThat(getLines(reader5)).containsExactlyInOrder("Строка 1", "Строка 2");

        ByteArrayOutputStream os61 = createOutputStream();
        ByteArrayOutputStream os62 = createOutputStream();
        InputStream is6 = createInputStreamWithCharset(CHARSET_NAME, "Строка 1", "Строка 2");
        CommandRunner commandRunner6 = new CommandRunner(os61, is6, os62, Charset.forName(CHARSET_NAME));
        BufferedReader reader6 = commandRunner6.getReader();
        Assertions.assertThat(getLines(reader6)).containsExactlyInOrder("Строка 1", "Строка 2");
        commandRunner6.getWriter().flush();
        Assertions.assertThat(getLines(CHARSET_NAME, os62)).containsExactlyInOrder("Строка 1", "Строка 2");

        try {
            ByteArrayOutputStream os7 = createOutputStream();
            InputStream is7 = createInputStream("Строка 1", "Строка 2");
            new CommandRunner(os7, is7, WRONG_CHARSET_NAME);
            Assertions.fail("CommandRunner test fail");
        } catch (CliIOException ex) {
            Assertions.assertThat(ex).hasCause(UnsupportedEncodingException.class);
        }

        try {
            ByteArrayOutputStream os81 = createOutputStream();
            ByteArrayOutputStream os82 = createOutputStream();
            InputStream is8 = createInputStream("Строка 1", "Строка 2");
            new CommandRunner(os81, is8, os82, WRONG_CHARSET_NAME);
            Assertions.fail("CommandRunner test fail");
        } catch (CliIOException ex) {
            Assertions.assertThat(ex).hasCause(UnsupportedEncodingException.class);
        }
    }

    /**
     * {@link CommandRunner} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        commandRunner1.execute(null);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder();

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command command2 = new AbstractCommandImpl("Prompt 1");
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("Prompt 1", "Строка 1");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command command33 = new AbstractCommandImpl("Prompt 3");
        Command command32 = new AbstractCommandImpl("Prompt 2", command33);
        Command command31 = new AbstractCommandImpl("Prompt 1", command32);
        commandRunner3.execute(command31);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");
    }

    /**
     * {@link CommandRunner} class test.
     */
    @Test
    public void executeWithContextTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        commandRunner1.execute(null, null);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder();

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command command2 = new AbstractCommandImpl("Prompt 1");
        commandRunner2.execute(command2, null);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("Prompt 1", "Строка 1");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command command33 = new AbstractCommandImpl("Prompt 3");
        Command command32 = new AbstractCommandImpl("Prompt 2", command33);
        Command command31 = new AbstractCommandImpl("Prompt 1", command32);
        commandRunner3.execute(command31, null);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Context context4 = new Context();
        commandRunner4.execute(null, context4);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder();
        Assertions.assertThat(context4.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Command command5 = new AbstractCommandImpl("Prompt 1");
        Context context5 = new Context();
        commandRunner5.execute(command5, context5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("Prompt 1", "Строка 1");
        Assertions.assertThat(context5.getValue(AbstractCommandImpl.COUNTER_KEY)).isEqualTo(1);

        ByteArrayOutputStream os6 = createOutputStream();
        InputStream is6 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner6 = new CommandRunner(os6, is6);
        Command command63 = new AbstractCommandImpl("Prompt 3");
        Command command62 = new AbstractCommandImpl("Prompt 2", command63);
        Command command61 = new AbstractCommandImpl("Prompt 1", command62);
        Context context6 = new Context();
        commandRunner6.execute(command61, context6);
        Assertions.assertThat(getLines(os6)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");
        Assertions.assertThat(context6.getValue(AbstractCommandImpl.COUNTER_KEY)).isEqualTo(3);
    }

}
