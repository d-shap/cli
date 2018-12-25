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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;

import ru.d_shap.cli.data.Context;
import ru.d_shap.cli.io.InputStreamWrapper;
import ru.d_shap.cli.io.OutputStreamWrapper;

/**
 * Command runner.
 *
 * @author Dmitry Shapovalov
 */
public final class CommandRunner {

    public static final String CHARSET_NAME = "UTF-8";

    private final PrintWriter _writer;

    private final BufferedReader _reader;

    /**
     * Create new object.
     *
     * @param outputStream the stream to write the command output.
     * @param inputStream  the stream to read the user input.
     */
    public CommandRunner(final OutputStream outputStream, final InputStream inputStream) {
        this(outputStream, inputStream, null, CHARSET_NAME);
    }

    /**
     * Create new object.
     *
     * @param outputStream    the stream to write the command output.
     * @param inputStream     the stream to read the user input.
     * @param logOutputStream the stream to log the user input and the command output.
     */
    public CommandRunner(final OutputStream outputStream, final InputStream inputStream, final OutputStream logOutputStream) {
        this(outputStream, inputStream, logOutputStream, CHARSET_NAME);
    }

    /**
     * Create new object.
     *
     * @param outputStream the stream to write the command output.
     * @param inputStream  the stream to read the user input.
     * @param charsetName  the name of the charset.
     */
    public CommandRunner(final OutputStream outputStream, final InputStream inputStream, final String charsetName) {
        this(outputStream, inputStream, null, charsetName);
    }

    /**
     * Create new object.
     *
     * @param outputStream    the stream to write the command output.
     * @param inputStream     the stream to read the user input.
     * @param logOutputStream the stream to log the user input and the command output.
     * @param charsetName     the name of the charset.
     */
    public CommandRunner(final OutputStream outputStream, final InputStream inputStream, final OutputStream logOutputStream, final String charsetName) {
        super();
        try {
            OutputStreamWrapper outputStreamWrapper = new OutputStreamWrapper(outputStream, logOutputStream);
            Writer writer = new OutputStreamWriter(outputStreamWrapper, charsetName);
            _writer = new PrintWriter(writer);
            InputStreamWrapper inputStreamWrapper = new InputStreamWrapper(inputStream, logOutputStream);
            Reader reader = new InputStreamReader(inputStreamWrapper, charsetName);
            _reader = new BufferedReader(reader);
        } catch (UnsupportedEncodingException ex) {
            throw new CliIOException(ex);
        }
    }

    /**
     * Create new object.
     *
     * @param outputStream the stream to write the command output.
     * @param inputStream  the stream to read the user input.
     * @param charset      the charset.
     */
    public CommandRunner(final OutputStream outputStream, final InputStream inputStream, final Charset charset) {
        this(outputStream, inputStream, null, charset.name());
    }

    /**
     * Create new object.
     *
     * @param outputStream    the stream to write the command output.
     * @param inputStream     the stream to read the user input.
     * @param logOutputStream the stream to log the user input and the command output.
     * @param charset         the charset.
     */
    public CommandRunner(final OutputStream outputStream, final InputStream inputStream, final OutputStream logOutputStream, final Charset charset) {
        this(outputStream, inputStream, logOutputStream, charset.name());
    }

    /**
     * Get the stream to write the command output.
     *
     * @return the stream to write the command output.
     */
    public PrintWriter getWriter() {
        return _writer;
    }

    /**
     * Get the stream to read the user input.
     *
     * @return the stream to read the user input.
     */
    public BufferedReader getReader() {
        return _reader;
    }

    /**
     * Execute the command sequence with the default context, started from the specified command.
     *
     * @param command the specified command to start with.
     */
    public void execute(final Command command) {
        execute(command, null);
    }

    /**
     * Execute the command sequence with the specified context, started from the specified command.
     *
     * @param command the specified command to start with.
     * @param context the specified context.
     */
    public void execute(final Command command, final Context context) {
        Command currentCommand = command;
        Context currentContext;
        if (context == null) {
            currentContext = new Context();
        } else {
            currentContext = context;
        }
        Command nextCommand;
        while (currentCommand != null) {
            currentCommand.setContext(currentContext);
            currentCommand.setCommandRunner(this);
            nextCommand = currentCommand.execute(_writer, _reader);
            if (nextCommand == null) {
                nextCommand = currentCommand.getParentCommand();
            }
            currentCommand = nextCommand;
        }
    }

}
