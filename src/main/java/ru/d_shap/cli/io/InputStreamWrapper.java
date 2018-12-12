///////////////////////////////////////////////////////////////////////////////////////////////////
// Command line interface tools provide facilities for application interface development.
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
package ru.d_shap.cli.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Input stream wrapper.
 *
 * @author Dmitry Shapovalov
 */
public final class InputStreamWrapper extends InputStream {

    private final InputStream _inputStream;

    private final OutputStream _logOutputStream;

    public InputStreamWrapper(final InputStream inputStream) {
        this(inputStream, null);
    }

    public InputStreamWrapper(final InputStream inputStream, final OutputStream logOutputStream) {
        super();
        _inputStream = inputStream;
        _logOutputStream = logOutputStream;
    }

    @Override
    public int read() throws IOException {
        int value = _inputStream.read();
        if (value >= 0 && _logOutputStream != null) {
            _logOutputStream.write(value);
        }
        return value;
    }

    @Override
    public int read(final byte[] buffer) throws IOException {
        int result = _inputStream.read(buffer);
        if (result > 0 && _logOutputStream != null) {
            _logOutputStream.write(buffer, 0, result);
        }
        return result;
    }

    @Override
    public int read(final byte[] buffer, final int offset, final int length) throws IOException {
        int result = _inputStream.read(buffer, offset, length);
        if (result > 0 && _logOutputStream != null) {
            _logOutputStream.write(buffer, offset, result);
        }
        return result;
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
