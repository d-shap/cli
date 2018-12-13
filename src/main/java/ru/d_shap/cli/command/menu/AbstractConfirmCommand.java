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

import java.util.ArrayList;
import java.util.List;

import ru.d_shap.cli.Command;
import ru.d_shap.cli.data.ValueHolder;
import ru.d_shap.cli.data.ValueLoader;

/**
 * Base class for all commands to confirm user action.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractConfirmCommand extends AbstractMenuCommand {

    private final ValueHolder<String> _yesMenuItemSymbol;

    private final ValueHolder<String> _yesMenuItemLabel;

    private final ValueHolder<Command> _yesMenuItemCommand;

    private final ValueHolder<String> _noMenuItemSymbol;

    private final ValueHolder<String> _noMenuItemLabel;

    /**
     * Create new object.
     */
    protected AbstractConfirmCommand() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     */
    protected AbstractConfirmCommand(final Command parentCommand) {
        super(parentCommand);
        _yesMenuItemSymbol = new ValueHolder<>(new YesMenuItemSymbolLoader());
        _yesMenuItemLabel = new ValueHolder<>(new YesMenuItemLabelLoader());
        _yesMenuItemCommand = new ValueHolder<>(new YesMenuItemCommandLoader());
        _noMenuItemSymbol = new ValueHolder<>(new NoMenuItemSymbolLoader());
        _noMenuItemLabel = new ValueHolder<>(new NoMenuItemLabelLoader());
    }

    /**
     * Get the symbol to select the "yes" menu item.
     *
     * @return the symbol to select the "yes" menu item.
     */
    protected abstract String getYesMenuItemSymbol();

    /**
     * Get the label of the "yes" menu item.
     *
     * @return the label of the "yes" menu item.
     */
    protected abstract String getYesMenuItemLabel();

    /**
     * Get the command to execute if the "yes" menu item is selected.
     *
     * @return the command to execute if the "yes" menu item is selected.
     */
    protected abstract Command getYesMenuItemCommand();

    /**
     * Get the symbol to select the "no" menu item.
     *
     * @return the symbol to select the "no" menu item.
     */
    protected abstract String getNoMenuItemSymbol();

    /**
     * Get the label of the "no" menu item.
     *
     * @return the label of the "no" menu item.
     */
    protected abstract String getNoMenuItemLabel();

    @Override
    protected final List<Option> getOptions() {
        List<Option> options = new ArrayList<>();

        String yesMenuItemSymbol = _yesMenuItemSymbol.getValue();
        String yesMenuItemLabel = _yesMenuItemLabel.getValue();
        Command yesMenuItemCommand = _yesMenuItemCommand.getValue();
        Option yesMenuItem = new MenuItem(yesMenuItemSymbol, yesMenuItemLabel, yesMenuItemCommand);
        options.add(yesMenuItem);

        String noMenuItemSymbol = _noMenuItemSymbol.getValue();
        String noMenuItemLabel = _noMenuItemLabel.getValue();
        Option noMenuItem = new MenuItem(noMenuItemSymbol, noMenuItemLabel);
        options.add(noMenuItem);

        return options;
    }

    @Override
    protected final int getDefaultOptionIndex() {
        return 1;
    }

    /**
     * Value loader for the "yes" menu item symbol.
     *
     * @author Dmitry Shapovalov
     */
    private final class YesMenuItemSymbolLoader implements ValueLoader<String> {

        YesMenuItemSymbolLoader() {
            super();
        }

        @Override
        public String loadValue() {
            return getYesMenuItemSymbol();
        }

    }

    /**
     * Value loader for the "yes" menu item label.
     *
     * @author Dmitry Shapovalov
     */
    private final class YesMenuItemLabelLoader implements ValueLoader<String> {

        YesMenuItemLabelLoader() {
            super();
        }

        @Override
        public String loadValue() {
            return getYesMenuItemLabel();
        }

    }

    /**
     * Value loader for the "yes" menu item command.
     *
     * @author Dmitry Shapovalov
     */
    private final class YesMenuItemCommandLoader implements ValueLoader<Command> {

        YesMenuItemCommandLoader() {
            super();
        }

        @Override
        public Command loadValue() {
            return getYesMenuItemCommand();
        }

    }

    /**
     * Value loader for the "no" menu item symbol.
     *
     * @author Dmitry Shapovalov
     */
    private final class NoMenuItemSymbolLoader implements ValueLoader<String> {

        NoMenuItemSymbolLoader() {
            super();
        }

        @Override
        public String loadValue() {
            return getNoMenuItemSymbol();
        }

    }

    /**
     * Value loader for the "no" menu item label.
     *
     * @author Dmitry Shapovalov
     */
    private final class NoMenuItemLabelLoader implements ValueLoader<String> {

        NoMenuItemLabelLoader() {
            super();
        }

        @Override
        public String loadValue() {
            return getNoMenuItemLabel();
        }

    }

}
