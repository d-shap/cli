package ru.d_shap.cli.command.input;

import java.io.PrintWriter;

import ru.d_shap.cli.Command;
import ru.d_shap.cli.command.AbstractUserActionCommand;
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

    private final ValueHolder<String> _header;

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
    protected abstract String getHeader();

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
        String header = _header.getValue();
        writer.println(header);

        String contextKey = _contextKey.getValue();
        boolean hasContextValue = getContext().hasValue(contextKey);
        String defaultMessage = _defaultMessage.getValue();
        if (hasContextValue && defaultMessage != null) {
            T contextValue = getContext().getValue(contextKey);
            String str = String.format(defaultMessage, contextValue);
            writer.println(str);
        }
    }

    @Override
    protected final Command processInput(final String input, final PrintWriter writer) {
        String contextKey = _contextKey.getValue();
        boolean hasContextValue = getContext().hasValue(contextKey);
        if (hasContextValue && isDefaultInput(input)) {
            T contextValue = getContext().getValue(contextKey);
            return processValue(contextValue, writer);
        }

        if (isValidType(input)) {
            T value = getValue(input);
            if (isValidValue(value)) {
                getContext().putValue(contextKey, value);
                return processValue(value, writer);
            }
        }

        String wrongInputMessage = _wrongInputMessage.getValue();
        if (wrongInputMessage != null) {
            String str = String.format(wrongInputMessage, input);
            writer.println(str);
        }
        return this;
    }

    protected abstract boolean isValidType(String input);

    protected abstract T getValue(String input);

    protected abstract boolean isValidValue(T value);

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
    private final class HeaderLoader implements ValueLoader<String> {

        HeaderLoader() {
            super();
        }

        @Override
        public String loadValue() {
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
