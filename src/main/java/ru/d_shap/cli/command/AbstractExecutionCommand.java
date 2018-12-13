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

/**
 * Base class for all execution commands.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractExecutionCommand extends AbstractCommand {

    /**
     * Create new object.
     */
    protected AbstractExecutionCommand() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     */
    protected AbstractExecutionCommand(final Command parentCommand) {
        super(parentCommand);
    }

    @Override
    public final Command execute(final PrintWriter writer, final BufferedReader reader) {
        return execute(writer);
    }

    /**
     * Execute the command.
     *
     * @param writer the stream to write the command output.
     *
     * @return the next command to execute.
     */
    protected abstract Command execute(PrintWriter writer);

}
