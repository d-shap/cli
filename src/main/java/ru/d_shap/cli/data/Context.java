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
     * @param <T>  the generic type of the value.
     *
     * @return the value from the context.
     */
    @SuppressWarnings("unchecked")
    public <T> T getValue(final String name) {
        return (T) _values.get(name);
    }

    /**
     * Put the value to the context.
     *
     * @param name  the specified name.
     * @param value the value to put to the context.
     * @param <T>   the generic type of the value.
     */
    public <T> void putValue(final String name, final T value) {
        if (name != null) {
            _values.put(name, value);
        }
    }

    /**
     * Remove the value from the context.
     *
     * @param name the specified name.
     */
    public void removeValue(final String name) {
        _values.remove(name);
    }

    /**
     * Copy the value to the specified context.
     *
     * @param name    the specified name.
     * @param context the specified context.
     */
    public void copyValueTo(final String name, final Context context) {
        if (hasValue(name)) {
            Object value = getValue(name);
            context.putValue(name, value);
        } else {
            context.removeValue(name);
        }
    }

}
