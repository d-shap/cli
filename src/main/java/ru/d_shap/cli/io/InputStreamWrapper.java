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
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Input stream wrapper to log the user input.
 *
 * @author Dmitry Shapovalov
 */
public final class InputStreamWrapper extends InputStream {

    private final InputStream _inputStream;

    private final OutputStream _logOutputStream;

    private boolean _otherMethodProcessing;

    /**
     * Create new object.
     *
     * @param inputStream stream to read the user input.
     */
    public InputStreamWrapper(final InputStream inputStream) {
        this(inputStream, null);
    }

    /**
     * Create new object.
     *
     * @param inputStream     stream to read the user input.
     * @param logOutputStream stream to log the user input.
     */
    public InputStreamWrapper(final InputStream inputStream, final OutputStream logOutputStream) {
        super();
        _inputStream = inputStream;
        _logOutputStream = logOutputStream;
        _otherMethodProcessing = false;
    }

    @Override
    public int read() throws IOException {
        try {
            boolean otherMethodProcessing = _otherMethodProcessing;
            _otherMethodProcessing = true;
            int value = _inputStream.read();
            if (!otherMethodProcessing && value >= 0 && _logOutputStream != null) {
                _logOutputStream.write(value);
            }
            return value;
        } finally {
            _otherMethodProcessing = false;
        }
    }

    @Override
    public int read(final byte[] buffer) throws IOException {
        try {
            boolean otherMethodProcessing = _otherMethodProcessing;
            _otherMethodProcessing = true;
            int result = _inputStream.read(buffer);
            if (!otherMethodProcessing && result > 0 && _logOutputStream != null) {
                _logOutputStream.write(buffer, 0, result);
            }
            return result;
        } finally {
            _otherMethodProcessing = false;
        }
    }

    @Override
    public int read(final byte[] buffer, final int offset, final int length) throws IOException {
        try {
            boolean otherMethodProcessing = _otherMethodProcessing;
            _otherMethodProcessing = true;
            int result = _inputStream.read(buffer, offset, length);
            if (!otherMethodProcessing && result > 0 && _logOutputStream != null) {
                _logOutputStream.write(buffer, offset, result);
            }
            return result;
        } finally {
            _otherMethodProcessing = false;
        }
    }

    @Override
    public long skip(final long count) throws IOException {
        return _inputStream.skip(count);
    }

    @Override
    public int available() throws IOException {
        return _inputStream.available();
    }

    @Override
    public void close() throws IOException {
        IOException exception = null;
        try {
            _inputStream.close();
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

    @Override
    public boolean markSupported() {
        return false;
    }

}
