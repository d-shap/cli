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
package ru.d_shap.cli.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Output stream wrapper to log the command output.
 *
 * @author Dmitry Shapovalov
 */
public final class OutputStreamWrapper extends OutputStream {

    private final OutputStream _outputStream;

    private final OutputStream _logOutputStream;

    /**
     * Create new object.
     *
     * @param outputStream stream to write the command output.
     */
    public OutputStreamWrapper(final OutputStream outputStream) {
        this(outputStream, null);
    }

    /**
     * Create new object.
     *
     * @param outputStream    stream to write the command output.
     * @param logOutputStream stream to log the command output.
     */
    public OutputStreamWrapper(final OutputStream outputStream, final OutputStream logOutputStream) {
        super();
        _outputStream = outputStream;
        _logOutputStream = logOutputStream;
    }

    @Override
    public void write(final int value) throws IOException {
        _outputStream.write(value);
        if (_logOutputStream != null) {
            _logOutputStream.write(value);
        }
    }

    @Override
    public void write(final byte[] value) throws IOException {
        _outputStream.write(value);
        if (_logOutputStream != null) {
            _logOutputStream.write(value);
        }
    }

    @Override
    public void write(final byte[] value, final int offset, final int length) throws IOException {
        _outputStream.write(value, offset, length);
        if (_logOutputStream != null) {
            _logOutputStream.write(value, offset, length);
        }
    }

    @Override
    public void flush() throws IOException {
        _outputStream.flush();
        if (_logOutputStream != null) {
            _logOutputStream.flush();
        }
    }

    @Override
    public void close() throws IOException {
        IOException exception = null;
        try {
            _outputStream.close();
        } catch (IOException ex) {
            exception = ex;
        }
        if (_logOutputStream != null) {
            try {
                _logOutputStream.close();
            } catch (IOException ex) {
                if (exception == null) {
                    exception = ex;
                }
            }
        }
        if (exception != null) {
            throw exception;
        }
    }

}
