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

import ru.d_shap.cli.Command;
import ru.d_shap.cli.data.Lines;

/**
 * The test implementation of the {@link AbstractYesNoCancelCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractYesNoCancelCommandImpl extends AbstractYesNoCancelCommand {

    private final Lines _header;

    private final String _yesMenuItemSymbol;

    private final Lines _yesMenuItemLabel;

    private final Command _yesMenuItemCommand;

    private final String _noMenuItemSymbol;

    private final Lines _noMenuItemLabel;

    private final Command _noMenuItemCommand;

    private final String _cancelMenuItemSymbol;

    private final Lines _cancelMenuItemLabel;

    private final int _symbolLength;

    private final int _defaultOptionIndex;

    private final String _wrongInputMessage;

    /**
     * Create new object.
     *
     * @param header               the menu header.
     * @param yesMenuItemSymbol    the symbol to select the "yes" menu item.
     * @param yesMenuItemLabel     the label of the "yes" menu item.
     * @param yesMenuItemCommand   the command to execute if the "yes" menu item is selected.
     * @param noMenuItemSymbol     the symbol to select the "no" menu item.
     * @param noMenuItemLabel      the label of the "no" menu item.
     * @param noMenuItemCommand    the command to execute if the "no" menu item is selected.
     * @param cancelMenuItemSymbol the symbol to select the "cancel" menu item.
     * @param cancelMenuItemLabel  the label of the "cancel" menu item.
     * @param symbolLength         the number of characters to display the symbol.
     * @param defaultOptionIndex   the index of the default option.
     * @param wrongInputMessage    the message for the wrong input.
     */
    public AbstractYesNoCancelCommandImpl(final Lines header, final String yesMenuItemSymbol, final Lines yesMenuItemLabel, final Command yesMenuItemCommand, final String noMenuItemSymbol, final Lines noMenuItemLabel, final Command noMenuItemCommand, final String cancelMenuItemSymbol, final Lines cancelMenuItemLabel, final int symbolLength, final int defaultOptionIndex, final String wrongInputMessage) {
        super();
        _header = header;
        _yesMenuItemSymbol = yesMenuItemSymbol;
        _yesMenuItemLabel = yesMenuItemLabel;
        _yesMenuItemCommand = yesMenuItemCommand;
        _noMenuItemSymbol = noMenuItemSymbol;
        _noMenuItemLabel = noMenuItemLabel;
        _noMenuItemCommand = noMenuItemCommand;
        _cancelMenuItemSymbol = cancelMenuItemSymbol;
        _cancelMenuItemLabel = cancelMenuItemLabel;
        _symbolLength = symbolLength;
        _defaultOptionIndex = defaultOptionIndex;
        _wrongInputMessage = wrongInputMessage;
    }

    /**
     * Create new object.
     *
     * @param parentCommand        the parent command.
     * @param header               the menu header.
     * @param yesMenuItemSymbol    the symbol to select the "yes" menu item.
     * @param yesMenuItemLabel     the label of the "yes" menu item.
     * @param yesMenuItemCommand   the command to execute if the "yes" menu item is selected.
     * @param noMenuItemSymbol     the symbol to select the "no" menu item.
     * @param noMenuItemLabel      the label of the "no" menu item.
     * @param noMenuItemCommand    the command to execute if the "no" menu item is selected.
     * @param cancelMenuItemSymbol the symbol to select the "cancel" menu item.
     * @param cancelMenuItemLabel  the label of the "cancel" menu item.
     * @param symbolLength         the number of characters to display the symbol.
     * @param defaultOptionIndex   the index of the default option.
     * @param wrongInputMessage    the message for the wrong input.
     */
    public AbstractYesNoCancelCommandImpl(final Command parentCommand, final Lines header, final String yesMenuItemSymbol, final Lines yesMenuItemLabel, final Command yesMenuItemCommand, final String noMenuItemSymbol, final Lines noMenuItemLabel, final Command noMenuItemCommand, final String cancelMenuItemSymbol, final Lines cancelMenuItemLabel, final int symbolLength, final int defaultOptionIndex, final String wrongInputMessage) {
        super(parentCommand);
        _header = header;
        _yesMenuItemSymbol = yesMenuItemSymbol;
        _yesMenuItemLabel = yesMenuItemLabel;
        _yesMenuItemCommand = yesMenuItemCommand;
        _noMenuItemSymbol = noMenuItemSymbol;
        _noMenuItemLabel = noMenuItemLabel;
        _noMenuItemCommand = noMenuItemCommand;
        _cancelMenuItemSymbol = cancelMenuItemSymbol;
        _cancelMenuItemLabel = cancelMenuItemLabel;
        _symbolLength = symbolLength;
        _defaultOptionIndex = defaultOptionIndex;
        _wrongInputMessage = wrongInputMessage;
    }

    @Override
    protected Lines getHeader() {
        return _header;
    }

    @Override
    protected String getYesMenuItemSymbol() {
        return _yesMenuItemSymbol;
    }

    @Override
    protected Lines getYesMenuItemLabel() {
        return _yesMenuItemLabel;
    }

    @Override
    protected Command getYesMenuItemCommand() {
        return _yesMenuItemCommand;
    }

    @Override
    protected String getNoMenuItemSymbol() {
        return _noMenuItemSymbol;
    }

    @Override
    protected Lines getNoMenuItemLabel() {
        return _noMenuItemLabel;
    }

    @Override
    protected Command getNoMenuItemCommand() {
        return _noMenuItemCommand;
    }

    @Override
    protected String getCancelMenuItemSymbol() {
        return _cancelMenuItemSymbol;
    }

    @Override
    protected Lines getCancelMenuItemLabel() {
        return _cancelMenuItemLabel;
    }

    @Override
    protected int getSymbolLength() {
        return _symbolLength;
    }

    @Override
    protected int getDefaultOptionIndex() {
        return _defaultOptionIndex;
    }

    @Override
    protected String getWrongInputMessage() {
        return _wrongInputMessage;
    }

}