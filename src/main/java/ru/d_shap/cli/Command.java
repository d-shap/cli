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
package ru.d_shap.cli;

import java.io.BufferedReader;
import java.io.PrintWriter;

import ru.d_shap.cli.value.Context;

/**
 * Command.
 *
 * @author Dmitry Shapovalov
 */
public interface Command {

    Command getParentCommand();

    Context getContext();

    void setContext(Context context);

    CommandRunner getCommandRunner();

    void setCommandRunner(CommandRunner commandRunner);

    Command execute(PrintWriter writer, BufferedReader reader);

}
