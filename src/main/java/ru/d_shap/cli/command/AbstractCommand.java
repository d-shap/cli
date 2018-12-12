///////////////////////////////////////////////////////////////////////////////////////////////////
// CLI tools provide facilities for the command line interface development.
// Copyright (C) 2019 Dmitry Shapovalov.
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

import ru.d_shap.cli.Command;
import ru.d_shap.cli.CommandRunner;
import ru.d_shap.cli.data.Context;

/**
 * Base class for all commands.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractCommand implements Command {

    private final Command _parentCommand;

    private Context _context;

    private CommandRunner _commandRunner;

    /**
     * Create new object.
     *
     * @param parentCommand the parrent command.
     */
    protected AbstractCommand(final Command parentCommand) {
        super();
        _parentCommand = parentCommand;
        _context = null;
        _commandRunner = null;
    }

    @Override
    public final Command getParentCommand() {
        return _parentCommand;
    }

    @Override
    public final Context getContext() {
        return _context;
    }

    @Override
    public final void setContext(final Context context) {
        _context = context;
    }

    @Override
    public final CommandRunner getCommandRunner() {
        return _commandRunner;
    }

    @Override
    public final void setCommandRunner(final CommandRunner commandRunner) {
        _commandRunner = commandRunner;
    }

}
