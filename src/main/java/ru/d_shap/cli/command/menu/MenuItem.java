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
package ru.d_shap.cli.command.menu;

import java.io.PrintWriter;
import java.util.List;

import ru.d_shap.cli.Command;
import ru.d_shap.cli.data.Lines;

/**
 * Menu item.
 *
 * @author Dmitry Shapovalov
 */
public final class MenuItem implements SelectableOption {

    private final String _symbol;

    private final Lines _label;

    private final Command _command;

    /**
     * Create new object.
     *
     * @param symbol the symbol to select this menu item.
     * @param label  the label.
     */
    public MenuItem(final String symbol, final String label) {
        this(symbol, new Lines(label), null);
    }

    /**
     * Create new object.
     *
     * @param symbol the symbol to select this menu item.
     * @param label  the label.
     */
    public MenuItem(final String symbol, final Lines label) {
        this(symbol, label, null);
    }

    /**
     * Create new object.
     *
     * @param symbol  the symbol to select this menu item.
     * @param label   the label.
     * @param command the command to execute if this menu item is selected.
     */
    public MenuItem(final String symbol, final String label, final Command command) {
        this(symbol, new Lines(label), command);
    }

    /**
     * Create new object.
     *
     * @param symbol  the symbol to select this menu item.
     * @param label   the label.
     * @param command the command to execute if this menu item is selected.
     */
    public MenuItem(final String symbol, final Lines label, final Command command) {
        super();
        _symbol = symbol;
        if (label == null) {
            _label = new Lines();
        } else {
            _label = label;
        }
        _command = command;
    }

    @Override
    public String getSymbol() {
        return _symbol;
    }

    @Override
    public Lines getLabel() {
        return _label;
    }

    @Override
    public void print(final PrintWriter writer, final int length, final boolean isDefault) {
        String firstLinePattern = "%" + length + "s: %s";
        String nextLinesPattern = "%" + length + "s  %s";
        String symbol;
        if (_symbol == null) {
            symbol = "";
        } else {
            symbol = _symbol;
        }
        if (isDefault) {
            symbol = "*" + symbol;
        }
        List<String> lines = _label.getLines();
        boolean firstLine = true;
        String str;
        for (String line : lines) {
            if (firstLine) {
                str = String.format(firstLinePattern, symbol, line);
                firstLine = false;
            } else {
                str = String.format(nextLinesPattern, "", line);
            }
            writer.println(str);
        }
    }

    @Override
    public boolean isSelected(final String symbol) {
        return _symbol != null && _symbol.equalsIgnoreCase(symbol);
    }

    @Override
    public Command getCommand() {
        return _command;
    }

}
