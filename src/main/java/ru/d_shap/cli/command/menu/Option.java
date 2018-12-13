package ru.d_shap.cli.command.menu;

import java.io.PrintWriter;

import ru.d_shap.cli.Command;

/**
 * Option interface.
 *
 * @author Dmitry Shapovalov
 */
public interface Option {

    void print(PrintWriter writer, boolean isDefault);

    boolean isSelected(char symbol);

    Command getCommand();

}
