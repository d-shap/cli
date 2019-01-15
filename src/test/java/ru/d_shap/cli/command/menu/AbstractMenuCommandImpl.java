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

import java.util.ArrayList;
import java.util.List;

import ru.d_shap.cli.Command;
import ru.d_shap.cli.data.Lines;

/**
 * The test implementation of the {@link AbstractMenuCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractMenuCommandImpl extends AbstractMenuCommand {

    public static final String CONTEXT_RESET = "CONTEXT_RESET";

    private final Lines _header;

    private final List<Option> _options;

    private final int _symbolLength;

    private final int _defaultOptionIndex;

    private final String _wrongInputMessage;

    /**
     * Create new object.
     *
     * @param header             the menu header.
     * @param options            the menu options.
     * @param symbolLength       the number of characters to display the symbol.
     * @param defaultOptionIndex the index of the default option.
     * @param wrongInputMessage  the message for the wrong input.
     */
    public AbstractMenuCommandImpl(final Lines header, final List<Option> options, final int symbolLength, final int defaultOptionIndex, final String wrongInputMessage) {
        super();
        _header = header;
        _options = options;
        _symbolLength = symbolLength;
        _defaultOptionIndex = defaultOptionIndex;
        _wrongInputMessage = wrongInputMessage;
    }

    /**
     * Create new object.
     *
     * @param parentCommand      the parent command.
     * @param header             the menu header.
     * @param options            the menu options.
     * @param symbolLength       the number of characters to display the symbol.
     * @param defaultOptionIndex the index of the default option.
     * @param wrongInputMessage  the message for the wrong input.
     */
    public AbstractMenuCommandImpl(final Command parentCommand, final Lines header, final List<Option> options, final int symbolLength, final int defaultOptionIndex, final String wrongInputMessage) {
        super(parentCommand);
        _header = header;
        _options = options;
        _symbolLength = symbolLength;
        _defaultOptionIndex = defaultOptionIndex;
        _wrongInputMessage = wrongInputMessage;
    }

    @Override
    protected Lines getHeader() {
        if (isContextReset()) {
            return new Lines(_header, "r!");
        } else {
            return _header;
        }
    }

    @Override
    protected List<Option> getOptions() {
        if (isContextReset()) {
            List<Option> options = new ArrayList<>(_options);
            Option option = new MenuItem("r!", "r!");
            options.add(option);
            return options;
        } else {
            return _options;
        }
    }

    @Override
    protected int getSymbolLength() {
        if (isContextReset()) {
            return _symbolLength + 1;
        } else {
            return _symbolLength;
        }
    }

    @Override
    protected int getDefaultOptionIndex() {
        if (isContextReset()) {
            return NO_DEFAULT_OPTION_INDEX;
        } else {
            return _defaultOptionIndex;
        }
    }

    @Override
    protected String getWrongInputMessage() {
        if (isContextReset()) {
            return "r!" + _wrongInputMessage;
        } else {
            return _wrongInputMessage;
        }
    }

    private boolean isContextReset() {
        return getContext().getValue(CONTEXT_RESET) != null;
    }

}
