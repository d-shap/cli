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
package ru.d_shap.cli.command;

import ru.d_shap.cli.Command;

/**
 * Helper class to obtain the parent command.
 *
 * @author Dmitry Shapovalov
 */
public final class ParentCommandHelper {

    private ParentCommandHelper() {
        super();
    }

    /**
     * Get the parent command, defined explicitly or obtained from the specified command.
     *
     * @param parentCommand explicitly defined parent command.
     * @param command       the specified command.
     *
     * @return the parent command.
     */
    public static Command getParentCommand(final Command parentCommand, final Command command) {
        if (parentCommand != null) {
            return parentCommand;
        }
        if (command == null) {
            return null;
        } else {
            return command.getParentCommand();
        }
    }

}
