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

import ru.d_shap.cli.Command;

/**
 * Base class for all Integer input commands.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractInputIntegerCommand extends AbstractInputCommand<Integer> {

    /**
     * Create new object.
     */
    protected AbstractInputIntegerCommand() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     */
    protected AbstractInputIntegerCommand(final Command parentCommand) {
        super(parentCommand);
    }

    @Override
    protected final boolean isValidType(final String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    @Override
    protected final Integer getValue(final String input) {
        return Integer.parseInt(input);
    }

    @Override
    protected final String getValueAsString(final Integer value) {
        return String.valueOf(value);
    }

}
