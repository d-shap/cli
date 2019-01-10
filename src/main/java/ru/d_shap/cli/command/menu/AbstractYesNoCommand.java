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
import ru.d_shap.cli.data.Lines;
import ru.d_shap.cli.data.ValueHolder;
import ru.d_shap.cli.data.ValueLoader;

/**
 * Base class for all commands to select one of two options.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractYesNoCommand extends AbstractMenuCommand {

    private final ValueHolder<String> _yesMenuItemSymbol;

    private final ValueHolder<Lines> _yesMenuItemLabel;

    private final ValueHolder<Command> _yesMenuItemCommand;

    private final ValueHolder<String> _noMenuItemSymbol;

    private final ValueHolder<Lines> _noMenuItemLabel;

    private final ValueHolder<Command> _noMenuItemCommand;

    /**
     * Create new object.
     */
    protected AbstractYesNoCommand() {
        this(null);
    }

    /**
     * Create new object.
     *
     * @param parentCommand the parent command.
     */
    protected AbstractYesNoCommand(final Command parentCommand) {
        super(parentCommand);
        _yesMenuItemSymbol = createValueHolder(new YesMenuItemSymbolLoader());
        _yesMenuItemLabel = createValueHolder(new YesMenuItemLabelLoader());
        _yesMenuItemCommand = createValueHolder(new YesMenuItemCommandLoader());
        _noMenuItemSymbol = createValueHolder(new NoMenuItemSymbolLoader());
        _noMenuItemLabel = createValueHolder(new NoMenuItemLabelLoader());
        _noMenuItemCommand = createValueHolder(new NoMenuItemCommandLoader());
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
    protected abstract Lines getYesMenuItemLabel();

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
    protected abstract Lines getNoMenuItemLabel();

    /**
     * Get the command to execute if the "no" menu item is selected.
     *
     * @return the command to execute if the "no" menu item is selected.
     */
    protected abstract Command getNoMenuItemCommand();

    @Override
    protected final List<Option> getOptions() {
        List<Option> options = new ArrayList<>();

        String yesMenuItemSymbol = _yesMenuItemSymbol.getValue();
        Lines yesMenuItemLabel = _yesMenuItemLabel.getValue();
        Command yesMenuItemCommand = _yesMenuItemCommand.getValue();
        Option yesMenuItem = new MenuItem(yesMenuItemSymbol, yesMenuItemLabel, yesMenuItemCommand);
        options.add(yesMenuItem);

        String noMenuItemSymbol = _noMenuItemSymbol.getValue();
        Lines noMenuItemLabel = _noMenuItemLabel.getValue();
        Command noMenuItemCommand = _noMenuItemCommand.getValue();
        Option noMenuItem = new MenuItem(noMenuItemSymbol, noMenuItemLabel, noMenuItemCommand);
        options.add(noMenuItem);

        return options;
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
    private final class YesMenuItemLabelLoader implements ValueLoader<Lines> {

        YesMenuItemLabelLoader() {
            super();
        }

        @Override
        public Lines loadValue() {
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
    private final class NoMenuItemLabelLoader implements ValueLoader<Lines> {

        NoMenuItemLabelLoader() {
            super();
        }

        @Override
        public Lines loadValue() {
            return getNoMenuItemLabel();
        }

    }

    /**
     * Value loader for the "no" menu item command.
     *
     * @author Dmitry Shapovalov
     */
    private final class NoMenuItemCommandLoader implements ValueLoader<Command> {

        NoMenuItemCommandLoader() {
            super();
        }

        @Override
        public Command loadValue() {
            return getNoMenuItemCommand();
        }

    }

}
