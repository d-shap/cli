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
import java.util.Set;

import ru.d_shap.cli.Command;
import ru.d_shap.cli.data.Lines;

/**
 * The test implementation of the {@link AbstractInputBooleanCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractInputBooleanCommandImpl extends AbstractInputBooleanCommand {

    private final String _contextKey;

    private final Lines _header;

    private final String _defaultMessage;

    private final String _wrongInputMessage;

    private final Set<String> _trueValues;

    private final Set<String> _falseValues;

    /**
     * Create new object.
     *
     * @param contextKey        the context key to store the user input.
     * @param header            the input header.
     * @param defaultMessage    the message for the default value.
     * @param wrongInputMessage the message for the wrong input.
     * @param trueValues        the valid true values.
     * @param falseValues       the valid false values.
     */
    public AbstractInputBooleanCommandImpl(final String contextKey, final Lines header, final String defaultMessage, final String wrongInputMessage, final Set<String> trueValues, final Set<String> falseValues) {
        super();
        _contextKey = contextKey;
        _header = header;
        _defaultMessage = defaultMessage;
        _wrongInputMessage = wrongInputMessage;
        _trueValues = trueValues;
        _falseValues = falseValues;
    }

    /**
     * Create new object.
     *
     * @param parentCommand     the parent command.
     * @param contextKey        the context key to store the user input.
     * @param header            the input header.
     * @param defaultMessage    the message for the default value.
     * @param wrongInputMessage the message for the wrong input.
     * @param trueValues        the valid true values.
     * @param falseValues       the valid false values.
     */
    public AbstractInputBooleanCommandImpl(final Command parentCommand, final String contextKey, final Lines header, final String defaultMessage, final String wrongInputMessage, final Set<String> trueValues, final Set<String> falseValues) {
        super(parentCommand);
        _contextKey = contextKey;
        _header = header;
        _defaultMessage = defaultMessage;
        _wrongInputMessage = wrongInputMessage;
        _trueValues = trueValues;
        _falseValues = falseValues;
    }

    @Override
    protected String getContextKey() {
        return _contextKey;
    }

    @Override
    protected Lines getHeader() {
        return _header;
    }

    @Override
    protected String getDefaultMessage() {
        return _defaultMessage;
    }

    @Override
    protected String getWrongInputMessage() {
        return _wrongInputMessage;
    }

    @Override
    protected Set<String> getTrueValues() {
        return _trueValues;
    }

    @Override
    protected Set<String> getFalseValues() {
        return _falseValues;
    }

    @Override
    protected boolean isValidValue(final Boolean value) {
        return !value;
    }

    @Override
    protected Command processValue(final Boolean value, final PrintWriter writer) {
        writer.println(!value);
        return getParentCommand();
    }

}
