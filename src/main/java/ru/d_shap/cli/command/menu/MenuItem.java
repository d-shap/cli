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

import ru.d_shap.cli.Command;

/**
 * Menu item.
 *
 * @author Dmitry Shapovalov
 */
public final class MenuItem implements Option {

    private final String _symbol;

    private final String _label;

    private final Command _command;

    /**
     * Create new object.
     *
     * @param symbol the symbol to select this menu item.
     * @param label  the label.
     */
    public MenuItem(final String symbol, final String label) {
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
        super();
        _symbol = symbol;
        _label = label;
        _command = command;
    }

    /**
     * Get the symbol to select this menu item.
     *
     * @return the symbol to select this menu item.
     */
    public String getSymbol() {
        return _symbol;
    }

    /**
     * Get the label.
     *
     * @return the label.
     */
    public String getLabel() {
        return _label;
    }

    @Override
    public void print(final PrintWriter writer, final boolean isDefault) {
        String str;
        if (isDefault) {
            str = String.format("%7s: %s", "*" + _symbol, _label);
        } else {
            str = String.format("%7s: %s", _symbol, _label);
        }
        writer.println(str);
    }

    @Override
    public boolean isSelected(final String symbol) {
        return _symbol == null && symbol == null || _symbol != null && _symbol.equalsIgnoreCase(symbol);
    }

    @Override
    public Command getCommand() {
        return _command;
    }

}
