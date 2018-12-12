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
package ru.d_shap.cli;

/**
 * Base class for all exceptions.
 *
 * @author Dmitry Shapovalov
 */
public class CliException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Create new object.
     *
     * @param message message exception message.
     */
    protected CliException(final String message) {
        super(message);
    }

    /**
     * Create new object.
     *
     * @param message message exception message.
     * @param cause   cause exception.
     */
    protected CliException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Create new object.
     *
     * @param cause cause exception.
     */
    protected CliException(final Throwable cause) {
        super(cause);
    }

}
