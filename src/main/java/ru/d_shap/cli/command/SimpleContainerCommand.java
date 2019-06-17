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
import ru.d_shap.cli.data.Context;

/**
 * Container command that execute contained commands with the nested context.
 * There is no way to affect the parent context.
 *
 * @author Dmitry Shapovalov
 */
public final class SimpleContainerCommand extends AbstractContainerCommand {

    private final Command _startCommand;

    /**
     * Create new object.
     *
     * @param startCommand the first command to execute within container.
     */
    public SimpleContainerCommand(final Command startCommand) {
        this(null, startCommand);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     * @param startCommand  the first command to execute within container.
     */
    public SimpleContainerCommand(final Command parentCommand, final Command startCommand) {
        super(ParentCommandHelper.getParentCommand(parentCommand, startCommand));
        _startCommand = startCommand;
    }

    @Override
    protected Command getStartCommand() {
        return _startCommand;
    }

    @Override
    protected void initContext(final Context context, final PrintWriter writer) {
        // Ignore
    }

    @Override
    protected Command processContext(final Context context, final PrintWriter writer) {
        return null;
    }

}
