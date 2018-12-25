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
package ru.d_shap.cli.command;

import java.io.PrintWriter;

import ru.d_shap.cli.Command;

/**
 * The test implementation of the {@link AbstractUserActionCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractUserActionCommandImpl extends AbstractUserActionCommand {

    private final String _message;

    /**
     * Create new object.
     *
     * @param message the message to write to the output.
     */
    public AbstractUserActionCommandImpl(final String message) {
        super();
        _message = message;
    }

    /**
     * Create new object.
     *
     * @param message       the message to write to the output.
     * @param parentCommand the parent command.
     */
    public AbstractUserActionCommandImpl(final String message, final Command parentCommand) {
        super(parentCommand);
        _message = message;
    }

    @Override
    protected void printMessage(final PrintWriter writer) {

    }

    @Override
    protected Command processDefaultInput(final String input, final PrintWriter writer) {
        return null;
    }

    @Override
    protected Command processInput(final String input, final PrintWriter writer) {
        return null;
    }

}
