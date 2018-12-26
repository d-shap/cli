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
package ru.d_shap.cli.command.menu;

import java.io.PrintWriter;
import java.util.List;

import ru.d_shap.cli.Command;
import ru.d_shap.cli.command.AbstractUserActionCommand;
import ru.d_shap.cli.command.CommandDefinitionException;
import ru.d_shap.cli.data.Lines;
import ru.d_shap.cli.data.ValueHolder;
import ru.d_shap.cli.data.ValueLoader;

/**
 * Base class for all menu selection commands.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractMenuCommand extends AbstractUserActionCommand {

    public static final int DEFAULT_SYMBOL_LENGTH = 7;

    public static final int NO_DEFAULT_OPTION_INDEX = -1;

    private final ValueHolder<Lines> _header;

    private final ValueHolder<List<Option>> _options;

    private final ValueHolder<Integer> _symbolLength;

    private final ValueHolder<Integer> _defaultOptionIndex;

    private final ValueHolder<String> _wrongInputMessage;

    /**
     * Create new object.
     */
    protected AbstractMenuCommand() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     */
    protected AbstractMenuCommand(final Command parentCommand) {
        super(parentCommand);
        _header = new ValueHolder<>(new HeaderLoader());
        _options = new ValueHolder<>(new OptionsLoader());
        _symbolLength = new ValueHolder<>(new SymbolLengthLoader());
        _defaultOptionIndex = new ValueHolder<>(new DefaultOptionIndexLoader());
        _wrongInputMessage = new ValueHolder<>(new WrongInputMessageLoader());
    }

    /**
     * Get the menu header.
     *
     * @return the menu header.
     */
    protected abstract Lines getHeader();

    /**
     * Get the menu options.
     *
     * @return the menu options.
     */
    protected abstract List<Option> getOptions();

    /**
     * Get the number of characters to display the symbol.
     *
     * @return the number of characters to display the symbol.
     */
    protected abstract int getSymbolLength();

    /**
     * Get the index of the default option.
     *
     * @return the index of the default option.
     */
    protected abstract int getDefaultOptionIndex();

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

        List<Option> options = _options.getValue();
        int symbolLength = _symbolLength.getValue();
        int defaultOptionIndex = _defaultOptionIndex.getValue();
        for (int i = 0; i < options.size(); i++) {
            Option option = options.get(i);
            option.print(writer, symbolLength, i == defaultOptionIndex);
        }
    }

    @Override
    protected final Command processDefaultInput(final String input, final PrintWriter writer) {
        List<Option> options = _options.getValue();
        int defaultOptionIndex = _defaultOptionIndex.getValue();
        if (defaultOptionIndex >= 0 && defaultOptionIndex < options.size()) {
            Option option = options.get(defaultOptionIndex);
            return ((SelectableOption) option).getCommand();
        }

        return processWrongInput(_wrongInputMessage, input, writer);
    }

    @Override
    protected final Command processInput(final String input, final PrintWriter writer) {
        List<Option> options = _options.getValue();
        for (Option option : options) {
            if (option instanceof SelectableOption && ((SelectableOption) option).isSelected(input)) {
                return ((SelectableOption) option).getCommand();
            }
        }

        return processWrongInput(_wrongInputMessage, input, writer);
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
            Lines header = getHeader();
            checkHeaderDefined(header);
            return header;
        }

        private void checkHeaderDefined(final Lines header) {
            if (header == null) {
                throw new CommandDefinitionException("Header is not defined");
            }
        }

    }

    /**
     * Value loader for the options.
     *
     * @author Dmitry Shapovalov
     */
    private final class OptionsLoader implements ValueLoader<List<Option>> {

        OptionsLoader() {
            super();
        }

        @Override
        public List<Option> loadValue() {
            List<Option> options = getOptions();
            checkOptionsDefined(options);
            checkOptionSymbolDefined(options);
            checkOptionSymbolUnique(options);
            checkOptionSymbolLength(options);
            checkOptionLabelDefined(options);
            return options;
        }

        private void checkOptionsDefined(final List<Option> options) {
            if (options == null) {
                throw new CommandDefinitionException("Options are not defined");
            }

            int count = 0;
            for (Option option : options) {
                if (!(option instanceof SelectableOption)) {
                    continue;
                }
                count++;
            }
            if (count == 0) {
                throw new CommandDefinitionException("Options are not defined");
            }
        }

        private void checkOptionSymbolDefined(final List<Option> options) {
            for (Option option : options) {
                if (!(option instanceof SelectableOption)) {
                    continue;
                }
                String symbol = ((SelectableOption) option).getSymbol();
                if (symbol == null || isDefaultInput(symbol)) {
                    throw new CommandDefinitionException("Option symbol is not defined");
                }
                String trimmedSymbol = symbol.trim();
                if (!trimmedSymbol.equals(symbol)) {
                    throw new CommandDefinitionException("Option symbol is wrong: " + symbol);
                }
            }
        }

        private void checkOptionSymbolUnique(final List<Option> options) {
            for (Option checkOption : options) {
                if (!(checkOption instanceof SelectableOption)) {
                    continue;
                }
                for (Option option : options) {
                    if (option == checkOption) {
                        continue;
                    }
                    if (!(option instanceof SelectableOption)) {
                        continue;
                    }
                    String symbol = ((SelectableOption) checkOption).getSymbol();
                    if (((SelectableOption) option).isSelected(symbol)) {
                        throw new CommandDefinitionException("Option symbol is not unique: " + symbol);
                    }
                }
            }
        }

        private void checkOptionSymbolLength(final List<Option> options) {
            int symbolLength = _symbolLength.getValue();
            for (Option option : options) {
                if (!(option instanceof SelectableOption)) {
                    continue;
                }
                String symbol = ((SelectableOption) option).getSymbol();
                if (symbol.length() > symbolLength) {
                    throw new CommandDefinitionException("Option symbol length is too large: " + symbol);
                }
            }
        }

        private void checkOptionLabelDefined(final List<Option> options) {
            for (Option option : options) {
                if (!(option instanceof SelectableOption)) {
                    continue;
                }
                Lines label = ((SelectableOption) option).getLabel();
                if (label == null) {
                    throw new CommandDefinitionException("Option label is not defined");
                }
            }
        }

    }

    /**
     * Value loader for the symbol length.
     *
     * @author Dmitry Shapovalov
     */
    private final class SymbolLengthLoader implements ValueLoader<Integer> {

        SymbolLengthLoader() {
            super();
        }

        @Override
        public Integer loadValue() {
            Integer symbolLength = getSymbolLength();
            checkSymbolLengthDefined(symbolLength);
            checkSymbolLengthPositive(symbolLength);
            return symbolLength;
        }

        private void checkSymbolLengthDefined(final Integer symbolLength) {
            if (symbolLength == null) {
                throw new CommandDefinitionException("Symbol length is not defined");
            }
        }

        private void checkSymbolLengthPositive(final Integer symbolLength) {
            if (symbolLength <= 0) {
                throw new CommandDefinitionException("Symbol length is not positive: " + symbolLength);
            }
        }

    }

    /**
     * Value loader for the default option index.
     *
     * @author Dmitry Shapovalov
     */
    private final class DefaultOptionIndexLoader implements ValueLoader<Integer> {

        DefaultOptionIndexLoader() {
            super();
        }

        @Override
        public Integer loadValue() {
            Integer defaultOptionIndex = getDefaultOptionIndex();
            checkSelectableOption(defaultOptionIndex);
            return defaultOptionIndex;
        }

        private void checkSelectableOption(final Integer defaultOptionIndex) {
            if (defaultOptionIndex == null) {
                return;
            }
            List<Option> options = _options.getValue();
            if (defaultOptionIndex < 0 || defaultOptionIndex >= options.size()) {
                return;
            }
            Option option = options.get(defaultOptionIndex);
            if (!(option instanceof SelectableOption)) {
                throw new CommandDefinitionException("Default option is not selectable: " + defaultOptionIndex);
            }
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
