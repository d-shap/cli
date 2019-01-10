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
package ru.d_shap.cli.data;

/**
 * Value holder.
 *
 * @param <T> the generic type of the value.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueHolder<T> {

    private final ValueLoader<T> _valueLoader;

    private boolean _valueLoaded;

    private T _value;

    /**
     * Create new object.
     *
     * @param valueLoader loader for the value.
     */
    public ValueHolder(final ValueLoader<T> valueLoader) {
        super();
        _valueLoader = valueLoader;
        _valueLoaded = false;
        _value = null;
    }

    /**
     * Get the value.
     *
     * @return the value.
     */
    public T getValue() {
        if (_valueLoaded) {
            return _value;
        } else {
            _value = _valueLoader.loadValue();
            _valueLoaded = true;
            return _value;
        }
    }

    /**
     * Reset the value state.
     */
    public void reset() {
        _valueLoaded = false;
        _value = null;
    }

}
