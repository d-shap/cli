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

import java.io.BufferedReader;
import java.io.PrintWriter;

import ru.d_shap.cli.Command;
import ru.d_shap.cli.CommandRunner;
import ru.d_shap.cli.data.Context;

/**
 * Command delegate to define the command at the some later time.
 *
 * @author Dmitry Shapovalov
 */
public final class CommandDelegate implements Command {

    private Command _command;

    /**
     * Create new object.
     */
    public CommandDelegate() {
        super();
    }

    /**
     * Create new object.
     *
     * @param command the command to delegate calls.
     */
    public CommandDelegate(final Command command) {
        super();
        _command = command;
    }

    /**
     * Get the command to delegate calls.
     *
     * @return the command to delegate calls.
     */
    public Command getCommand() {
        return _command;
    }

    /**
     * Set the command to delegate calls.
     *
     * @param command the command to delegate calls.
     */
    public void setCommand(final Command command) {
        _command = command;
    }

    @Override
    public boolean hasParentCommand() {
        if (_command == null) {
            return false;
        } else {
            return _command.hasParentCommand();
        }
    }

    @Override
    public <T extends Command> boolean hasParentCommand(final Class<T> clazz) {
        if (_command == null) {
            return false;
        } else {
            return _command.hasParentCommand(clazz);
        }
    }

    @Override
    public Command getParentCommand() {
        if (_command == null) {
            return null;
        } else {
            return _command.getParentCommand();
        }
    }

    @Override
    public <T extends Command> Command getParentCommand(final Class<T> clazz) {
        if (_command == null) {
            return null;
        } else {
            return _command.getParentCommand(clazz);
        }
    }

    @Override
    public Context getContext() {
        if (_command == null) {
            return null;
        } else {
            return _command.getContext();
        }
    }

    @Override
    public void setContext(final Context context) {
        if (_command != null) {
            _command.setContext(context);
        }
    }

    @Override
    public CommandRunner getCommandRunner() {
        if (_command == null) {
            return null;
        } else {
            return _command.getCommandRunner();
        }
    }

    @Override
    public void setCommandRunner(final CommandRunner commandRunner) {
        if (_command != null) {
            _command.setCommandRunner(commandRunner);
        }
    }

    @Override
    public Command execute(final PrintWriter writer, final BufferedReader reader) {
        if (_command == null) {
            return null;
        } else {
            return _command.execute(writer, reader);
        }
    }

    @Override
    public void reset() {
        if (_command != null) {
            _command.reset();
        }
    }

}
