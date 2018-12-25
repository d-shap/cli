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
 * Menu option.
 *
 * @author Dmitry Shapovalov
 */
public interface Option {

    /**
     * Get the symbol to select this option.
     *
     * @return the symbol to select this option.
     */
    String getSymbol();

    /**
     * Print the option.
     *
     * @param writer    the stream to write the command output.
     * @param length    the number of characters to display the symbol.
     * @param isDefault true if this option is the default option.
     */
    void print(PrintWriter writer, int length, boolean isDefault);

    /**
     * Check if this option is selected.
     *
     * @param symbol the user input.
     *
     * @return true if this option is selected.
     */
    boolean isSelected(String symbol);

    /**
     * Get the command to execute.
     *
     * @return the command to execute.
     */
    Command getCommand();

}
