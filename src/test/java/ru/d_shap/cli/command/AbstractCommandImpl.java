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

/**
 * The test implementation of the {@link AbstractCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractCommandImpl extends AbstractCommand {

    public static final String INCREMENT_KEY = "__INCREMENT_KEY__";

    public static final String COUNTER_KEY = "__COUNTER_KEY__";

    private final String _message;

    private final Command _nextCommand;

    /**
     * Create new object.
     *
     * @param message     the message to write to the output.
     * @param nextCommand the next command to execute.
     */
    public AbstractCommandImpl(final String message, final Command nextCommand) {
        super();
        _message = message;
        _nextCommand = nextCommand;
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     * @param message       the message to write to the output.
     * @param nextCommand   the next command to execute.
     */
    public AbstractCommandImpl(final Command parentCommand, final String message, final Command nextCommand) {
        super(parentCommand);
        _message = message;
        _nextCommand = nextCommand;
    }

    @Override
    public Command execute(final PrintWriter writer, final BufferedReader reader) {
        try {
            writer.println(_message);
            String line = reader.readLine();
            if (line != null) {
                writer.println(line);
            }
            writer.flush();

            Integer increment = getContext().getValue(INCREMENT_KEY);
            if (increment == null) {
                increment = 1;
            }

            Integer counter = getContext().getValue(COUNTER_KEY);
            if (counter == null) {
                getContext().putValue(COUNTER_KEY, increment);
            } else {
                getContext().putValue(COUNTER_KEY, counter + increment);
            }

            return _nextCommand;
        } catch (IOException ex) {
            throw new CliIOException(ex);
        }
    }

}
