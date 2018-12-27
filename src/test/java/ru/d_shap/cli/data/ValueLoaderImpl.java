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
 * The test implementation of the {@link ValueLoader}.
 *
 * @param <T> the generic type of the value.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueLoaderImpl<T> implements ValueLoader<T> {

    private boolean _firstCall;

    private final T _value;

    /**
     * Create new object.
     *
     * @param value the value.
     */
    public ValueLoaderImpl(final T value) {
        super();
        _firstCall = true;
        _value = value;
    }

    @Override
    public T loadValue() {
        if (_firstCall) {
            _firstCall = false;
            return _value;
        } else {
            throw new ValueLoadException("Not first call!");
        }
    }

}
