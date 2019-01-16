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

import ru.d_shap.cli.Command;
import ru.d_shap.cli.data.Lines;

/**
 * The test implementation of the {@link AbstractInputCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractInputCommandImpl extends AbstractInputCommand<int[]> {

    public static final String CONTEXT_RESET = "CONTEXT_RESET";

    private final String _contextKey;

    private final Lines _header;

    private final String _defaultMessage;

    private final String _wrongInputMessage;

    /**
     * Create new object.
     *
     * @param contextKey        the context key to store the user input.
     * @param header            the input header.
     * @param defaultMessage    the message for the default value.
     * @param wrongInputMessage the message for the wrong input.
     */
    public AbstractInputCommandImpl(final String contextKey, final Lines header, final String defaultMessage, final String wrongInputMessage) {
        super();
        _contextKey = contextKey;
        _header = header;
        _defaultMessage = defaultMessage;
        _wrongInputMessage = wrongInputMessage;
    }

    /**
     * Create new object.
     *
     * @param parentCommand     the parent command.
     * @param contextKey        the context key to store the user input.
     * @param header            the input header.
     * @param defaultMessage    the message for the default value.
     * @param wrongInputMessage the message for the wrong input.
     */
    public AbstractInputCommandImpl(final Command parentCommand, final String contextKey, final Lines header, final String defaultMessage, final String wrongInputMessage) {
        super(parentCommand);
        _contextKey = contextKey;
        _header = header;
        _defaultMessage = defaultMessage;
        _wrongInputMessage = wrongInputMessage;
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
    protected boolean isValidType(final String input) {
        String[] parts = input.split("[,]");
        for (String part : parts) {
            try {
                Integer.parseInt(part.trim());
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected int[] getValue(final String input) {
        String[] parts = input.split("[,]");
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            result[i] = Integer.parseInt(part.trim());
        }
        return result;
    }

    @Override
    protected String getValueAsString(final int[] value) {
        StringBuilder result = new StringBuilder();
        result.append(value[0]);
        for (int i = 1; i < value.length; i++) {
            result.append(",").append(value[i]);
        }
        return result.toString();
    }

    @Override
    protected boolean isValidValue(final int[] value) {
        return value != null && value.length > 1;
    }

    @Override
    protected Command processValue(final int[] value, final PrintWriter writer) {
        return getParentCommand();
    }

    private boolean isContextReset() {
        return getContext().getValue(CONTEXT_RESET) != null;
    }

}
