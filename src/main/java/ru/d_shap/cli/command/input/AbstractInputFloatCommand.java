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
 * Base class for all Float input commands.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractInputFloatCommand extends AbstractInputCommand<Float> {

    /**
     * Create new object.
     */
    protected AbstractInputFloatCommand() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     */
    protected AbstractInputFloatCommand(final Command parentCommand) {
        super(parentCommand);
    }

    @Override
    protected final boolean isValidType(final String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    @Override
    protected final Float getValue(final String input) {
        return Float.parseFloat(input);
    }

    @Override
    protected final String getValueAsString(final Float value) {
        return String.valueOf(value);
    }

}
