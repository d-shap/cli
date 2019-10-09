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
import ru.d_shap.cli.data.Context;
import ru.d_shap.cli.data.ValueHolder;
import ru.d_shap.cli.data.ValueLoader;

/**
 * Base class for all commands that execute contained commands with the nested context.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractContainerCommand extends AbstractCommand {

    private final ValueHolder<Command> _startCommand;

    /**
     * Create new object.
     */
    protected AbstractContainerCommand() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     */
    protected AbstractContainerCommand(final Command parentCommand) {
        super(parentCommand);
        _startCommand = createValueHolder(new StartCommandLoader());
    }

    /**
     * Get the first command to execute within container.
     *
     * @return the first command to execute within container.
     */
    protected abstract Command getStartCommand();

    @Override
    public final Command execute(final PrintWriter writer, final BufferedReader reader) {
        Context context = new Context(getContext());
        initContext(context, writer);
        Command startCommand = _startCommand.getValue();
        getCommandRunner().execute(startCommand, context);
        return processContext(context, writer);
    }

    /**
     * Initialize the nested context.
     *
     * @param context the nested context.
     * @param writer  the stream to write the command output.
     */
    protected abstract void initContext(Context context, PrintWriter writer);

    /**
     * Process the nested context after the contained commands are executed.
     *
     * @param context the nested context.
     * @param writer  the stream to write the command output.
     *
     * @return the next command to execute.
     */
    protected abstract Command processContext(Context context, PrintWriter writer);

    /**
     * Value loader for the start command.
     *
     * @author Dmitry Shapovalov
     */
    private final class StartCommandLoader implements ValueLoader<Command> {

        StartCommandLoader() {
            super();
        }

        @Override
        public Command loadValue() {
            return getStartCommand();
        }

    }

}
