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

import java.util.HashSet;
import java.util.Set;

import ru.d_shap.cli.Command;
import ru.d_shap.cli.command.CommandDefinitionException;
import ru.d_shap.cli.data.ValueHolder;
import ru.d_shap.cli.data.ValueLoader;

/**
 * Base class for all Boolean input commands.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractInputBooleanCommand extends AbstractInputCommand<Boolean> {

    private final ValueHolder<Set<String>> _trueValues;

    private final ValueHolder<Set<String>> _falseValues;

    /**
     * Create new object.
     */
    protected AbstractInputBooleanCommand() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     */
    protected AbstractInputBooleanCommand(final Command parentCommand) {
        super(parentCommand);
        _trueValues = new ValueHolder<>(new TrueValuesLoader());
        _falseValues = new ValueHolder<>(new FalseValuesLoader());
    }

    /**
     * Get the valid true values.
     *
     * @return the valid true values.
     */
    protected abstract Set<String> getTrueValues();

    /**
     * Get the valid false values.
     *
     * @return the valid false values.
     */
    protected abstract Set<String> getFalseValues();

    @Override
    protected final boolean isValidType(final String input) {
        Set<String> trueValues = _trueValues.getValue();
        for (String trueValue : trueValues) {
            if (trueValue.equalsIgnoreCase(input)) {
                return true;
            }
        }
        Set<String> falseValues = _falseValues.getValue();
        for (String falseValue : falseValues) {
            if (falseValue.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected final Boolean getValue(final String input) {
        Set<String> trueValues = _trueValues.getValue();
        for (String trueValue : trueValues) {
            if (trueValue.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected final String getValueAsString(final Boolean value) {
        if (value) {
            Set<String> trueValues = _trueValues.getValue();
            if (trueValues.isEmpty()) {
                return String.valueOf(value);
            } else {
                return trueValues.iterator().next();
            }
        } else {
            Set<String> falseValues = _falseValues.getValue();
            if (falseValues.isEmpty()) {
                return String.valueOf(value);
            } else {
                return falseValues.iterator().next();
            }
        }
    }

    /**
     * Value loader for the true values.
     *
     * @author Dmitry Shapovalov
     */
    private final class TrueValuesLoader implements ValueLoader<Set<String>> {

        TrueValuesLoader() {
            super();
        }

        @Override
        public Set<String> loadValue() {
            Set<String> trueValues = getTrueValues();
            checkTrueValuesDefined(trueValues);
            return trueValues;
        }

        private void checkTrueValuesDefined(final Set<String> trueValues) {
            if (trueValues == null || trueValues.isEmpty()) {
                throw new CommandDefinitionException("True values are not defined");
            }
        }

        private void checkSameValues(final Set<String> trueValues) {
            Set<String> falseValues = _falseValues.getValue();
            Set<String> set = new HashSet<>(trueValues);
            set.retainAll(falseValues);
            if (!set.isEmpty()) {
                throw new CommandDefinitionException("True values and False values contain the same value");
            }
        }

    }

    /**
     * Value loader for the false values.
     *
     * @author Dmitry Shapovalov
     */
    private final class FalseValuesLoader implements ValueLoader<Set<String>> {

        FalseValuesLoader() {
            super();
        }

        @Override
        public Set<String> loadValue() {
            Set<String> falseValues = getFalseValues();
            checkFalseValuesDefined(falseValues);
            return falseValues;
        }

        private void checkFalseValuesDefined(final Set<String> falseValues) {
            if (falseValues == null || falseValues.isEmpty()) {
                throw new CommandDefinitionException("False values are not defined");
            }
        }

    }

}
