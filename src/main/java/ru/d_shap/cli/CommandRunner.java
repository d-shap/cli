///////////////////////////////////////////////////////////////////////////////////////////////////
// Command line interface provides facilities for rapid application interface prototyping.
// Copyright (C) 2019 Dmitry Shapovalov.
//
// This file is part of command line interface.
//
// Command line interface is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Command line interface is distributed in the hope that it will be useful,
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

import ru.d_shap.cli.io.InputStreamWrapper;
import ru.d_shap.cli.io.OutputStreamWrapper;
import ru.d_shap.cli.value.Context;

/**
 * Command runner.
 *
 * @author Dmitry Shapovalov
 */
public final class CommandRunner {

    private final PrintWriter _writer;

    private final BufferedReader _reader;

    public CommandRunner(final OutputStream outputStream, final InputStream inputStream) {
        this(outputStream, inputStream, (OutputStream) null);
    }

    public CommandRunner(final OutputStream outputStream, final InputStream inputStream, final OutputStream logOutputStream) {
        super();
        OutputStreamWrapper outputStreamWrapper = new OutputStreamWrapper(outputStream, logOutputStream);
        Writer writer = new OutputStreamWriter(outputStreamWrapper);
        _writer = new PrintWriter(writer);
        InputStreamWrapper inputStreamWrapper = new InputStreamWrapper(inputStream, logOutputStream);
        Reader reader = new InputStreamReader(inputStreamWrapper);
        _reader = new BufferedReader(reader);
    }

    public CommandRunner(final OutputStream outputStream, final InputStream inputStream, final String charsetName) {
        this(outputStream, inputStream, null, charsetName);
    }

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
            throw new CliException(ex);
        }
    }

    public CommandRunner(final OutputStream outputStream, final InputStream inputStream, final Charset charset) {
        this(outputStream, inputStream, null, charset);
    }

    public CommandRunner(final OutputStream outputStream, final InputStream inputStream, final OutputStream logOutputStream, final Charset charset) {
        super();
        OutputStreamWrapper outputStreamWrapper = new OutputStreamWrapper(outputStream, logOutputStream);
        Writer writer = new OutputStreamWriter(outputStreamWrapper, charset);
        _writer = new PrintWriter(writer);
        InputStreamWrapper inputStreamWrapper = new InputStreamWrapper(inputStream, logOutputStream);
        Reader reader = new InputStreamReader(inputStreamWrapper, charset);
        _reader = new BufferedReader(reader);
    }

    public void execute(final Command command) {
        execute(command, null);
    }

    public void execute(final Command command, final Context context) {
        Command currentCommand = command;
        Context currentContext;
        if (context == null) {
            currentContext = new Context();
        } else {
            currentContext = context;
        }
        while (currentCommand != null) {
            currentCommand.setContext(currentContext);
            currentCommand.setCommandRunner(this);
            currentCommand = currentCommand.execute(_writer, _reader);
        }
    }

}
