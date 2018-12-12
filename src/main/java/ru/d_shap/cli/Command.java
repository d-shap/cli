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
package ru.d_shap.cli;

import java.io.BufferedReader;
import java.io.PrintWriter;

import ru.d_shap.cli.data.Context;

/**
 * Command interface.
 *
 * @author Dmitry Shapovalov
 */
public interface Command {

    /**
     * Get the parrent command.
     *
     * @return the parrent command.
     */
    Command getParentCommand();

    /**
     * Get the command context.
     *
     * @return the command context.
     */
    Context getContext();

    /**
     * Set the command context.
     *
     * @param context the command context.
     */
    void setContext(Context context);

    /**
     * Get the command runner.
     *
     * @return the command runner.
     */
    CommandRunner getCommandRunner();

    /**
     * Set the command runner.
     *
     * @param commandRunner the command runner.
     */
    void setCommandRunner(CommandRunner commandRunner);

    /**
     * Execute the command.
     *
     * @param writer stream to write the command output.
     * @param reader stream to read the user input.
     *
     * @return the next command to execute.
     */
    Command execute(PrintWriter writer, BufferedReader reader);

}
