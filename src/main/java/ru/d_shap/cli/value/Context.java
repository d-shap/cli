///////////////////////////////////////////////////////////////////////////////////////////////////
// Command line interface provides facilities for rapid application interface prototyping.
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
package ru.d_shap.cli.value;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Context of multiple commands.
 *
 * @author Dmitry Shapovalov
 */
public final class Context {

    private final Map<String, Object> _values;

    public Context() {
        this(null);
    }

    public Context(final Context context) {
        super();
        if (context == null) {
            _values = new LinkedHashMap<>();
        } else {
            _values = new LinkedHashMap<>(context._values);
        }
    }

    public List<String> getNames() {
        return new ArrayList<>(_values.keySet());
    }

    public boolean hasValue(final String name) {
        return _values.containsKey(name);
    }

    public Object getValue(final String name) {
        return _values.get(name);
    }

    public void putValue(final String name, final Object value) {
        _values.put(name, value);
    }

}
