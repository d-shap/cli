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
/**
 * <p>
 * CLI tools provide facilities for the command line interface development.
 * </p>
 * <p>
 * Command line interface can be defined as a command flow. The commands in this flow are executed
 * step by step.
 * </p>
 * <p>
 * The command implementation class should extend the {@link ru.d_shap.cli.command.AbstractCommand} class
 * or it's subclasses. The {@link ru.d_shap.cli.command.AbstractCommand#execute(java.io.PrintWriter, java.io.BufferedReader)}
 * method defines the next command to execute. So each command performs some actions and then defines
 * the next command based on this actions. This is the command flow.
 * </p>
 * <p>
 * The {@link ru.d_shap.cli.CommandRunner} class is used to run the command flow. This class obtains
 * the first command in the flow and executes all the commands in this flow.
 * </p>
 * <p>
 * Some commands require user interaction, some does not. For example, the application first collects
 * the required data from the user and then performs some actions based on this data.
 * </p>
 * <p>
 * Commands, that require the user interaction, should extend the {@link ru.d_shap.cli.command.AbstractUserActionCommand}
 * class or it's subclasses. Some useful subclasses are {@link ru.d_shap.cli.command.menu.AbstractMenuCommand}
 * to define the menu, {@link ru.d_shap.cli.command.input.AbstractInputCommand} to obtain the data
 * from the user like strings, numbers etc.
 * </p>
 * <p>
 * Commands, that does not require the user interaction, should extend the {@link ru.d_shap.cli.command.AbstractExecutionCommand}
 * class or it's subclasses.
 * </p>
 * <p>
 * All the commands in the flow share the same context. One command in the flow can put the value to
 * the context, another command can use this value to perform some actions.
 * </p>
 * <p>
 * The command flow can contain nested flows. Nested flow has the nested context. All the commands of
 * the nested flow share the same nested context, but nested flow does not affect the parent context.
 * </p>
 * <p>
 * The nested flow is defined by the command class, that extends the {@link ru.d_shap.cli.command.AbstractContainerCommand}.
 * </p>
 */
package ru.d_shap.cli;
