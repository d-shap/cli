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
package ru.d_shap.cli.command.input;

import java.io.PrintWriter;
import java.util.List;

import ru.d_shap.cli.Command;
import ru.d_shap.cli.command.AbstractUserActionCommand;
import ru.d_shap.cli.data.Lines;
import ru.d_shap.cli.data.ValueHolder;
import ru.d_shap.cli.data.ValueLoader;

/**
 * Base class for all input commands.
 *
 * @param <T> the generic type of the value.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractInputCommand<T> extends AbstractUserActionCommand {

    private final ValueHolder<String> _contextKey;

    private final ValueHolder<Lines> _header;

    private final ValueHolder<String> _defaultMessage;

    private final ValueHolder<String> _wrongInputMessage;

    /**
     * Create new object.
     */
    protected AbstractInputCommand() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     */
    protected AbstractInputCommand(final Command parentCommand) {
        super(parentCommand);
        _contextKey = new ValueHolder<>(new ContextKeyLoader());
        _header = new ValueHolder<>(new HeaderLoader());
        _defaultMessage = new ValueHolder<>(new DefaultMessageLoader());
        _wrongInputMessage = new ValueHolder<>(new WrongInputMessageLoader());
    }

    /**
     * Get the context key to store the user input.
     *
     * @return the context key to store the user input.
     */
    protected abstract String getContextKey();

    /**
     * Get the input header.
     *
     * @return the input header.
     */
    protected abstract Lines getHeader();

    /**
     * Get the message for the default value.
     *
     * @return the message for the default value.
     */
    protected abstract String getDefaultMessage();

    /**
     * Get the message for the wrong input.
     *
     * @return the message for the wrong input.
     */
    protected abstract String getWrongInputMessage();

    @Override
    protected final void printMessage(final PrintWriter writer) {
        Lines header = _header.getValue();
        List<String> lines = header.getLines();
        for (String line : lines) {
            writer.println(line);
        }

        String contextKey = _contextKey.getValue();
        boolean hasContextValue = getContext().hasValue(contextKey);
        String defaultMessage = _defaultMessage.getValue();
        if (hasContextValue && defaultMessage != null) {
            T contextValue = getContext().getValue(contextKey);
            String stringValue = asString(contextValue);
            String str = String.format(defaultMessage, stringValue);
            writer.println(str);
        }
    }

    @Override
    protected final Command processDefaultInput(final String input, final PrintWriter writer) {
        String contextKey = _contextKey.getValue();
        boolean hasContextValue = getContext().hasValue(contextKey);
        if (hasContextValue) {
            T contextValue = getContext().getValue(contextKey);
            if (isValidValue(contextValue)) {
                return processValue(contextValue, writer);
            } else {
                String stringValue = asString(contextValue);
                return processWrongInput(_wrongInputMessage, stringValue, writer);
            }
        }

        return processWrongInput(_wrongInputMessage, input, writer);
    }

    @Override
    protected final Command processInput(final String input, final PrintWriter writer) {
        if (isValidType(input)) {
            T value = getValue(input);
            if (isValidValue(value)) {
                String contextKey = _contextKey.getValue();
                getContext().putValue(contextKey, value);
                return processValue(value, writer);
            }
        }

        return processWrongInput(_wrongInputMessage, input, writer);
    }

    /**
     * Check if the user input can be converted to the target type.
     *
     * @param input the user input
     *
     * @return true if the user input can be converted to the target type.
     */
    protected abstract boolean isValidType(String input);

    /**
     * Convert the user input to the target type.
     *
     * @param input the user input
     *
     * @return the user input, converted to the target type.
     */
    protected abstract T getValue(String input);

    /**
     * Convert the value to the string.
     *
     * @param value the value.
     *
     * @return the value, converted to the string.
     */
    protected abstract String getValueAsString(T value);

    private String asString(final T value) {
        if (value == null) {
            return null;
        } else {
            return getValueAsString(value);
        }
    }

    /**
     * Check if the value is valid.
     *
     * @param value the value.
     *
     * @return true if the value is valid.
     */
    protected abstract boolean isValidValue(T value);

    /**
     * Process the value.
     *
     * @param value  the value.
     * @param writer the stream to write the command output.
     *
     * @return the next command to execute.
     */
    protected abstract Command processValue(T value, PrintWriter writer);

    /**
     * Value loader for the context key.
     *
     * @author Dmitry Shapovalov
     */
    private final class ContextKeyLoader implements ValueLoader<String> {

        ContextKeyLoader() {
            super();
        }

        @Override
        public String loadValue() {
            return getContextKey();
        }

    }

    /**
     * Value loader for the header.
     *
     * @author Dmitry Shapovalov
     */
    private final class HeaderLoader implements ValueLoader<Lines> {

        HeaderLoader() {
            super();
        }

        @Override
        public Lines loadValue() {
            return getHeader();
        }

    }

    /**
     * Value loader for the default message.
     *
     * @author Dmitry Shapovalov
     */
    private final class DefaultMessageLoader implements ValueLoader<String> {

        DefaultMessageLoader() {
            super();
        }

        @Override
        public String loadValue() {
            return getDefaultMessage();
        }

    }

    /**
     * Value loader for the wrong input message.
     *
     * @author Dmitry Shapovalov
     */
    private final class WrongInputMessageLoader implements ValueLoader<String> {

        WrongInputMessageLoader() {
            super();
        }

        @Override
        public String loadValue() {
            return getWrongInputMessage();
        }

    }

}
