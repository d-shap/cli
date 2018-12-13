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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import ru.d_shap.cli.CliIOException;
import ru.d_shap.cli.Command;

/**
 * Base class for all user action commands.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractUserActionCommand extends AbstractCommand {

    /**
     * Create new object.
     */
    protected AbstractUserActionCommand() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parrent command.
     */
    protected AbstractUserActionCommand(final Command parentCommand) {
        super(parentCommand);
    }

    @Override
    public final Command execute(final PrintWriter writer, final BufferedReader reader) {
        try {
            printMessage(writer);
            writer.println();
            writer.flush();

            String input = reader.readLine();
            if (input == null) {
                input = "";
            } else {
                input = input.trim();
            }
            Command command = processInput(input, writer);
            writer.println();
            writer.flush();

            return command;
        } catch (IOException ex) {
            throw new CliIOException(ex);
        }
    }

    /**
     * Print the message.
     *
     * @param writer the stream to write the command output.
     */
    protected abstract void printMessage(PrintWriter writer);

    /**
     * Process the user input.
     *
     * @param input  the user input.
     * @param writer the stream to write the command output.
     *
     * @return the next command to execute.
     */
    protected abstract Command processInput(String input, PrintWriter writer);

}