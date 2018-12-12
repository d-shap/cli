package ru.d_shap.cli.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import ru.d_shap.cli.CliIOException;
import ru.d_shap.cli.Command;

/**
 * Base class for all user action commands.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractUserActionCommand extends AbstractCommand {

    /**
     * Create new object.
     *
     * @param parentCommand the parrent command.
     */
    protected AbstractUserActionCommand(final Command parentCommand) {
        super(parentCommand);
    }

    @Override
    public final Command execute(final PrintWriter writer, final BufferedReader reader) {
        try {
            printMessage(writer);
            writer.println();
            writer.flush();

            String input = reader.readLine().trim();
            Command command = processInput(input, writer);
            writer.println();
            writer.flush();

            return command;
        } catch (IOException ex) {
            throw new CliIOException(ex);
        }
    }

    protected abstract void printMessage(PrintWriter writer);

    protected abstract Command processInput(String input, PrintWriter writer);

}
