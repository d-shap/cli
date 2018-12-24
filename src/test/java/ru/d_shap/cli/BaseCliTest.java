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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
