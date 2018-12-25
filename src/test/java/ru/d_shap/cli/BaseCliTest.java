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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Base CLI test class.
 *
 * @author Dmitry Shapovalov
 */
public class BaseCliTest {

    /**
     * Create new object.
     */
    protected BaseCliTest() {
        super();
    }

    /**
     * Create the output stream.
     *
     * @return the output stream.
     */
    public final ByteArrayOutputStream createOutputStream() {
        return new ByteArrayOutputStream();
    }

    /**
     * Create the input stream for the specified lines.
     *
     * @param lines the specified lines.
     *
     * @return the input stream.
     */
    public final InputStream createInputStream(final String... lines) {
        return createInputStreamWithEncoding(CommandRunner.ENCODING, lines);
    }

    /**
     * Create the input stream for the specified lines.
     *
     * @param encoding the encoding for the lines.
     * @param lines    the specified lines.
     *
     * @return the input stream.
     */
    public final InputStream createInputStreamWithEncoding(final String encoding, final String... lines) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, encoding);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter);
            for (String line : lines) {
                printWriter.println(line);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return new ByteArrayInputStream(bytes);
        } catch (IOException ex) {
            throw new CliIOException(ex);
        }
    }

    /**
     * Get the lines from the specified stream.
     *
     * @param baos the specified stream.
     *
     * @return the lines.
     */
    public final List<String> getLines(final ByteArrayOutputStream baos) {
        return getLines(CommandRunner.ENCODING, baos);
    }

    /**
     * Get the lines from the specified stream.
     *
     * @param encoding the encoding for the lines.
     * @param baos     the specified stream.
     *
     * @return the lines.
     */
    public final List<String> getLines(final String encoding, final ByteArrayOutputStream baos) {
        try {
            byte[] bytes = baos.toByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            Reader reader = new InputStreamReader(byteArrayInputStream, encoding);
            BufferedReader bufferedReader = new BufferedReader(reader);
            return getLines(bufferedReader);
        } catch (IOException ex) {
            throw new CliIOException(ex);
        }
    }

    /**
     * Get the lines from the specified reader.
     *
     * @param bufferedReader the specified reader.
     *
     * @return the lines.
     */
    public final List<String> getLines(final BufferedReader bufferedReader) {
        try {
            List<String> lines = new ArrayList<>();
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                lines.add(line);
            }
            return lines;
        } catch (IOException ex) {
            throw new CliIOException(ex);
        }
    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    public static final class ClosedInputStream extends InputStream {

        private boolean _closed;

        /**
         * Create new object.
         */
        public ClosedInputStream() {
            super();
            _closed = false;
        }

        /**
         * Check if this stream is closed.
         *
         * @return true if this stream is closed.
         */
        public boolean isClosed() {
            return _closed;
        }

        @Override
        public int read() throws IOException {
            return -1;
        }

        @Override
        public void close() throws IOException {
            super.close();
            _closed = true;
        }

    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    public static final class ClosedOutputStream extends OutputStream {

        private boolean _closed;

        /**
         * Create new object.
         */
        public ClosedOutputStream() {
            super();
            _closed = false;
        }

        /**
         * Check if this stream is closed.
         *
         * @return true if this stream is closed.
         */
        public boolean isClosed() {
            return _closed;
        }

        @Override
        public void write(final int b) throws IOException {
            // Ignore
        }

        @Override
        public void close() throws IOException {
            super.close();
            _closed = true;
        }

    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    public static final class FlushedOutputStream extends OutputStream {

        private boolean _flushed;

        /**
         * Create new object.
         */
        public FlushedOutputStream() {
            super();
            _flushed = false;
        }

        /**
         * Check if this stream is flushed.
         *
         * @return true if this stream is flushed.
         */
        public boolean isFlushed() {
            return _flushed;
        }

        @Override
        public void write(final int b) throws IOException {
            // Ignore
        }

        @Override
        public void flush() throws IOException {
            super.flush();
            _flushed = true;
        }

    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    public static final class FailOnCloseInputStream extends InputStream {

        private final String _message;

        /**
         * Create new object.
         *
         * @param message fail message.
         */
        public FailOnCloseInputStream(final String message) {
            super();
            _message = message;
        }

        @Override
        public int read() throws IOException {
            return -1;
        }

        @Override
        public void close() throws IOException {
            throw new IOException(_message);
        }

    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    public static final class FailOnCloseOutputStream extends OutputStream {

        private final String _message;

        /**
         * Create new object.
         *
         * @param message fail message.
         */
        public FailOnCloseOutputStream(final String message) {
            super();
            _message = message;
        }

        @Override
        public void write(final int b) throws IOException {
            // Ignore
        }

        @Override
        public void close() throws IOException {
            throw new IOException(_message);
        }

    }

}
