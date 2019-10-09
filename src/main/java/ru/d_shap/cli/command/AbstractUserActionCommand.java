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
import java.io.IOException;
import java.io.PrintWriter;

import ru.d_shap.cli.CliIOException;
import ru.d_shap.cli.Command;
import ru.d_shap.cli.data.ValueHolder;

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
     * @param parentCommand the parent command.
     */
    protected AbstractUserActionCommand(final Command parentCommand) {
        super(parentCommand);
    }

    @Override
    public final Command execute(final PrintWriter writer, final BufferedReader reader) {
        try {
            printMessage(writer);
            writer.flush();

            String input = reader.readLine();
            if (input == null) {
                input = "";
            } else {
                input = input.trim();
            }

            Command command;
            if (isDefaultInput(input)) {
                command = processDefaultInput(input, writer);
            } else {
                command = processInput(input, writer);
            }
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
     * Check if the user input is the default input.
     *
     * @param input the user input.
     *
     * @return true if the user input is the default input.
     */
    protected final boolean isDefaultInput(final String input) {
        return "".equals(input);
    }

    /**
     * Process the default user input.
     *
     * @param input  the user input.
     * @param writer the stream to write the command output.
     *
     * @return the next command to execute.
     */
    protected abstract Command processDefaultInput(String input, PrintWriter writer);

    /**
     * Process the user input.
     *
     * @param input  the user input.
     * @param writer the stream to write the command output.
     *
     * @return the next command to execute.
     */
    protected abstract Command processInput(String input, PrintWriter writer);

    /**
     * Process the wrong user input.
     *
     * @param wrongInputMessage the message for the wrong input.
     * @param input             the user input.
     * @param writer            the stream to write the command output.
     *
     * @return the next command to execute.
     */
    protected final Command processWrongInput(final ValueHolder<String> wrongInputMessage, final String input, final PrintWriter writer) {
        String str;
        if (wrongInputMessage == null) {
            str = null;
        } else {
            str = wrongInputMessage.getValue();
        }
        return processWrongInput(str, input, writer);
    }

    /**
     * Process the wrong user input.
     *
     * @param wrongInputMessage the message for the wrong input.
     * @param input             the user input.
     * @param writer            the stream to write the command output.
     *
     * @return the next command to execute.
     */
    protected final Command processWrongInput(final String wrongInputMessage, final String input, final PrintWriter writer) {
        if (wrongInputMessage != null) {
            String str = String.format(wrongInputMessage, input);
            writer.println(str);
        }
        return this;
    }

}
