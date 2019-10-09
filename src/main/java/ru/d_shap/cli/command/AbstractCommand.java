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

import java.util.ArrayList;
import java.util.List;

import ru.d_shap.cli.Command;
import ru.d_shap.cli.CommandRunner;
import ru.d_shap.cli.data.Context;
import ru.d_shap.cli.data.ValueHolder;
import ru.d_shap.cli.data.ValueLoader;

/**
 * Base class for all commands.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractCommand implements Command {

    private final Command _parentCommand;

    private Context _context;

    private CommandRunner _commandRunner;

    private final List<ValueHolder<?>> _valueHolders;

    /**
     * Create new object.
     */
    protected AbstractCommand() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     */
    protected AbstractCommand(final Command parentCommand) {
        super();
        _parentCommand = parentCommand;
        _context = null;
        _commandRunner = null;
        _valueHolders = new ArrayList<>();
    }

    @Override
    public final boolean hasParentCommand() {
        return getParentCommand() != null;
    }

    @Override
    public final <T extends Command> boolean hasParentCommand(final Class<T> clazz) {
        return getParentCommand(clazz) != null;
    }

    @Override
    public final Command getParentCommand() {
        return _parentCommand;
    }

    @Override
    public final <T extends Command> Command getParentCommand(final Class<T> clazz) {
        Command parentCommand = _parentCommand;
        while (parentCommand != null && !clazz.isInstance(parentCommand)) {
            parentCommand = parentCommand.getParentCommand();
        }
        return parentCommand;
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

    /**
     * Create the value holder.
     *
     * @param valueLoader the loader for the value.
     * @param <T>         the generic type of the value.
     *
     * @return the value holder.
     */
    protected final <T> ValueHolder<T> createValueHolder(final ValueLoader<T> valueLoader) {
        ValueHolder<T> valueHolder = new ValueHolder<>(valueLoader);
        _valueHolders.add(valueHolder);
        return valueHolder;
    }

    @Override
    public final void reset() {
        for (ValueHolder<?> valueHolder : _valueHolders) {
            valueHolder.reset();
        }
    }

}
