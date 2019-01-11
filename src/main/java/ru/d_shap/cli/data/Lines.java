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
import java.util.Collection;
import java.util.List;

/**
 * Class to hold the output lines.
 *
 * @author Dmitry Shapovalov
 */
public final class Lines {

    private final List<String> _lines;

    /**
     * Create new object.
     */
    public Lines() {
        this((Lines) null);
    }

    /**
     * Create new object.
     *
     * @param line the output line.
     */
    public Lines(final String line) {
        this(null, line);
    }

    /**
     * Create new object.
     *
     * @param lines the output lines.
     */
    public Lines(final String... lines) {
        this(null, lines);
    }

    /**
     * Create new object.
     *
     * @param lines the output lines.
     */
    public Lines(final Collection<String> lines) {
        this(null, lines);
    }

    /**
     * Create new object.
     *
     * @param base the base lines.
     */
    public Lines(final Lines base) {
        super();
        _lines = new ArrayList<>();
        if (base != null) {
            _lines.addAll(base._lines);
        }
    }

    /**
     * Create new object.
     *
     * @param base the base lines.
     * @param line the output line.
     */
    public Lines(final Lines base, final String line) {
        this(base);
        addLine(line);
    }

    /**
     * Create new object.
     *
     * @param base  the base lines.
     * @param lines the output lines.
     */
    public Lines(final Lines base, final String... lines) {
        this(base);
        addLines(lines);
    }

    /**
     * Create new object.
     *
     * @param base  the base lines.
     * @param lines the output lines.
     */
    public Lines(final Lines base, final Collection<String> lines) {
        this(base);
        addLines(lines);
    }

    /**
     * Add the output line.
     *
     * @param line the output line.
     */
    public void addLine(final String line) {
        if (line != null) {
            _lines.add(line);
        }
    }

    /**
     * Add the output lines.
     *
     * @param lines the output lines.
     */
    public void addLines(final String... lines) {
        if (lines != null) {
            for (String line : lines) {
                addLine(line);
            }
        }
    }

    /**
     * Add the output lines.
     *
     * @param lines the output lines.
     */
    public void addLines(final Collection<String> lines) {
        if (lines != null) {
            for (String line : lines) {
                addLine(line);
            }
        }
    }

    /**
     * Get the output lines.
     *
     * @return the output lines.
     */
    public List<String> getLines() {
        List<String> lines = new ArrayList<>(_lines);
        if (lines.isEmpty()) {
            lines.add("");
        }
        return lines;
    }

}
