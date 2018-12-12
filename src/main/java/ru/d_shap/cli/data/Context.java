///////////////////////////////////////////////////////////////////////////////////////////////////
// Command line interface tools provide facilities for application interface development.
// Copyright (C) 2019 Dmitry Shapovalov.
//
// This file is part of command line interface.
//
// Command line interface is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Command line interface is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.cli.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Command execution context.
 *
 * @author Dmitry Shapovalov
 */
public final class Context {

    private final Map<String, Object> _values;

    /**
     * Create new object.
     */
    public Context() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param context the parent context.
     */
    public Context(final Context context) {
        super();
        if (context == null) {
            _values = new LinkedHashMap<>();
        } else {
            _values = new LinkedHashMap<>(context._values);
        }
    }

    /**
     * Get all context names.
     *
     * @return all context names.
     */
    public List<String> getNames() {
        return new ArrayList<>(_values.keySet());
    }

    /**
     * Check if the context has the value.
     *
     * @param name the specified name.
     *
     * @return true if the context has the value.
     */
    public boolean hasValue(final String name) {
        return _values.containsKey(name);
    }

    /**
     * Get the value from the context.
     *
     * @param name the specified name.
     *
     * @return the value from the context.
     */
    public Object getValue(final String name) {
        return _values.get(name);
    }

    /**
     * Put the value to the context.
     *
     * @param name  the specified name.
     * @param value the value to put to the context.
     */
    public void putValue(final String name, final Object value) {
        _values.put(name, value);
    }

    /**
     * Remove the value from the context.
     *
     * @param name the specified name.
     */
    public void removeValue(final String name) {
        _values.remove(name);
    }

}
