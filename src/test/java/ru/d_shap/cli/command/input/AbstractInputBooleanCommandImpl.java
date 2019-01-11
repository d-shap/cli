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
import java.util.LinkedHashSet;
import java.util.Set;

import ru.d_shap.cli.Command;
import ru.d_shap.cli.data.Lines;

/**
 * The test implementation of the {@link AbstractInputBooleanCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractInputBooleanCommandImpl extends AbstractInputBooleanCommand {

    public static final String CONTEXT_RESET = "CONTEXT_RESET";

    private final String _contextKey;

    private final Lines _header;

    private final String _defaultMessage;

    private final String _wrongInputMessage;

    private final Set<String> _trueValues;

    private final Set<String> _falseValues;

    private final boolean _validValue;

    /**
     * Create new object.
     *
     * @param contextKey        the context key to store the user input.
     * @param header            the input header.
     * @param defaultMessage    the message for the default value.
     * @param wrongInputMessage the message for the wrong input.
     * @param trueValues        the valid true values.
     * @param falseValues       the valid false values.
     * @param validValue        the valid value;
     */
    public AbstractInputBooleanCommandImpl(final String contextKey, final Lines header, final String defaultMessage, final String wrongInputMessage, final Set<String> trueValues, final Set<String> falseValues, final boolean validValue) {
        super();
        _contextKey = contextKey;
        _header = header;
        _defaultMessage = defaultMessage;
        _wrongInputMessage = wrongInputMessage;
        _trueValues = trueValues;
        _falseValues = falseValues;
        _validValue = validValue;
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
     * @param validValue        the valid value;
     */
    public AbstractInputBooleanCommandImpl(final Command parentCommand, final String contextKey, final Lines header, final String defaultMessage, final String wrongInputMessage, final Set<String> trueValues, final Set<String> falseValues, final boolean validValue) {
        super(parentCommand);
        _contextKey = contextKey;
        _header = header;
        _defaultMessage = defaultMessage;
        _wrongInputMessage = wrongInputMessage;
        _trueValues = trueValues;
        _falseValues = falseValues;
        _validValue = validValue;
    }

    @Override
    protected String getContextKey() {
        if (isContextReset()) {
            return "r!" + _contextKey;
        } else {
            return _contextKey;
        }
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
    protected String getDefaultMessage() {
        if (isContextReset()) {
            return "r!" + _defaultMessage;
        } else {
            return _defaultMessage;
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

    @Override
    protected Set<String> getTrueValues() {
        if (isContextReset()) {
            Set<String> trueValues = new LinkedHashSet<>(_trueValues);
            trueValues.add("r!t");
            return trueValues;
        } else {
            return _trueValues;
        }
    }

    @Override
    protected Set<String> getFalseValues() {
        if (isContextReset()) {
            Set<String> falseValues = new LinkedHashSet<>(_falseValues);
            falseValues.add("r!f");
            return falseValues;
        } else {
            return _falseValues;
        }
    }

    @Override
    protected boolean isValidValue(final Boolean value) {
        return value == _validValue;
    }

    @Override
    protected Command processValue(final Boolean value, final PrintWriter writer) {
        writer.println(!value);
        return getParentCommand();
    }

    private boolean isContextReset() {
        return getContext().getValue(CONTEXT_RESET) != null;
    }

}
