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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;
import ru.d_shap.cli.Command;
import ru.d_shap.cli.CommandRunner;
import ru.d_shap.cli.data.Context;

/**
 * Tests for {@link SimpleContainerCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class SimpleContainerCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public SimpleContainerCommandTest() {
        super();
    }

    /**
     * {@link SimpleContainerCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os01 = createOutputStream();
        InputStream is01 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner01 = new CommandRunner(os01, is01);
        Command container01 = new SimpleContainerCommand(null);
        Context context01 = new Context();
        commandRunner01.execute(container01, context01);
        Assertions.assertThat(getLines(os01)).containsExactlyInOrder();
        Assertions.assertThat(context01.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        ByteArrayOutputStream os02 = createOutputStream();
        InputStream is02 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner02 = new CommandRunner(os02, is02);
        Command container02 = new SimpleContainerCommand(null, null);
        Context context02 = new Context();
        commandRunner02.execute(container02, context02);
        Assertions.assertThat(getLines(os02)).containsExactlyInOrder();
        Assertions.assertThat(context02.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        ByteArrayOutputStream os03 = createOutputStream();
        InputStream is03 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner03 = new CommandRunner(os03, is03);
        Command command03 = new AbstractCommandImpl("Prompt 1", null);
        Command container03 = new SimpleContainerCommand(command03);
        Context context03 = new Context();
        commandRunner03.execute(container03, context03);
        Assertions.assertThat(getLines(os03)).containsExactlyInOrder("Prompt 1", "Строка 1");
        Assertions.assertThat(context03.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        ByteArrayOutputStream os04 = createOutputStream();
        InputStream is04 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner04 = new CommandRunner(os04, is04);
        Command command04 = new AbstractCommandImpl("Prompt 1", null);
        Command container04 = new SimpleContainerCommand(null, command04);
        Context context04 = new Context();
        commandRunner04.execute(container04, context04);
        Assertions.assertThat(getLines(os04)).containsExactlyInOrder("Prompt 1", "Строка 1");
        Assertions.assertThat(context04.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        ByteArrayOutputStream os05 = createOutputStream();
        InputStream is05 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner05 = new CommandRunner(os05, is05);
        Command parentCommand05 = new AbstractCommandImpl("message", null);
        Command command05 = new AbstractCommandImpl("Prompt 1", null);
        Command container05 = new SimpleContainerCommand(parentCommand05, command05);
        Context context05 = new Context();
        commandRunner05.execute(container05, context05);
        Assertions.assertThat(getLines(os05)).containsExactlyInOrder("Prompt 1", "Строка 1", "message", "Строка 2");
        Assertions.assertThat(context05.getValue(AbstractCommandImpl.COUNTER_KEY)).isEqualTo(1);

        ByteArrayOutputStream os06 = createOutputStream();
        InputStream is06 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner06 = new CommandRunner(os06, is06);
        Command command063 = new AbstractCommandImpl("Prompt 3", null);
        Command command062 = new AbstractCommandImpl("Prompt 2", command063);
        Command command061 = new AbstractCommandImpl("Prompt 1", command062);
        Command container06 = new SimpleContainerCommand(command061);
        Context context06 = new Context();
        commandRunner06.execute(container06, context06);
        Assertions.assertThat(getLines(os06)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");
        Assertions.assertThat(context06.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        ByteArrayOutputStream os07 = createOutputStream();
        InputStream is07 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner07 = new CommandRunner(os07, is07);
        Command command073 = new AbstractCommandImpl("Prompt 3", null);
        Command command072 = new AbstractCommandImpl("Prompt 2", command073);
        Command command071 = new AbstractCommandImpl("Prompt 1", command072);
        Command container07 = new SimpleContainerCommand(null, command071);
        Context context07 = new Context();
        commandRunner07.execute(container07, context07);
        Assertions.assertThat(getLines(os07)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");
        Assertions.assertThat(context07.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        ByteArrayOutputStream os08 = createOutputStream();
        InputStream is08 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner08 = new CommandRunner(os08, is08);
        Command parentCommand08 = new AbstractCommandImpl("message", null);
        Command command083 = new AbstractCommandImpl("Prompt 3", null);
        Command command082 = new AbstractCommandImpl("Prompt 2", command083);
        Command command081 = new AbstractCommandImpl("Prompt 1", command082);
        Command container08 = new SimpleContainerCommand(parentCommand08, command081);
        Context context08 = new Context();
        commandRunner08.execute(container08, context08);
        Assertions.assertThat(getLines(os08)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3", "message");
        Assertions.assertThat(context08.getValue(AbstractCommandImpl.COUNTER_KEY)).isEqualTo(1);

        ByteArrayOutputStream os09 = createOutputStream();
        InputStream is09 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner09 = new CommandRunner(os09, is09);
        Command parentCommand09 = new AbstractCommandImpl("message", null);
        Command command09 = new AbstractCommandImpl(parentCommand09, "Prompt 1", null);
        Command container09 = new SimpleContainerCommand(parentCommand09, command09);
        Context context09 = new Context();
        commandRunner09.execute(container09, context09);
        Assertions.assertThat(getLines(os09)).containsExactlyInOrder("Prompt 1", "Строка 1", "message", "Строка 2", "message");
        Assertions.assertThat(context09.getValue(AbstractCommandImpl.COUNTER_KEY)).isEqualTo(1);

        ByteArrayOutputStream os10 = createOutputStream();
        InputStream is10 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner10 = new CommandRunner(os10, is10);
        Command parentCommand10 = new AbstractCommandImpl("message", null);
        Command command103 = new AbstractCommandImpl(parentCommand10, "Prompt 3", null);
        Command command102 = new AbstractCommandImpl(parentCommand10, "Prompt 2", command103);
        Command command101 = new AbstractCommandImpl(parentCommand10, "Prompt 1", command102);
        Command container10 = new SimpleContainerCommand(parentCommand10, command101);
        Context context10 = new Context();
        commandRunner10.execute(container10, context10);
        Assertions.assertThat(getLines(os10)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3", "message", "message");
        Assertions.assertThat(context10.getValue(AbstractCommandImpl.COUNTER_KEY)).isEqualTo(1);

        ByteArrayOutputStream os11 = createOutputStream();
        InputStream is11 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner11 = new CommandRunner(os11, is11);
        Command command113 = new AbstractCommandImpl("Prompt 3", null);
        Command command112 = new AbstractCommandImpl("Prompt 2", command113);
        Command command111 = new AbstractCommandImpl("Prompt 1", command112);
        Command container11 = new SimpleContainerCommand(command111);
        Context context11 = new Context();
        context11.putValue(AbstractCommandImpl.COUNTER_KEY, 1);
        context11.putValue(AbstractCommandImpl.INCREMENT_KEY, 2);
        commandRunner11.execute(container11, context11);
        Assertions.assertThat(getLines(os11)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");
        Assertions.assertThat(context11.getValue(AbstractCommandImpl.COUNTER_KEY)).isEqualTo(1);

        ByteArrayOutputStream os12 = createOutputStream();
        InputStream is12 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner12 = new CommandRunner(os12, is12);
        Command parentCommand12 = new AbstractCommandImpl("message", null);
        Command command123 = new AbstractCommandImpl("Prompt 3", null);
        Command command122 = new AbstractCommandImpl("Prompt 2", command123);
        Command command121 = new AbstractCommandImpl("Prompt 1", command122);
        Command container12 = new SimpleContainerCommand(parentCommand12, command121);
        Context context12 = new Context();
        context12.putValue(AbstractCommandImpl.COUNTER_KEY, 1);
        context12.putValue(AbstractCommandImpl.INCREMENT_KEY, 2);
        commandRunner12.execute(container12, context12);
        Assertions.assertThat(getLines(os12)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3", "message");
        Assertions.assertThat(context12.getValue(AbstractCommandImpl.COUNTER_KEY)).isEqualTo(3);
    }

    /**
     * {@link SimpleContainerCommand} class test.
     */
    @Test
    public void resetTest() {
        Command command3 = new AbstractCommandImpl("Prompt 3", null);
        Command command2 = new AbstractCommandImpl("Prompt 2", command3);
        Command command1 = new AbstractCommandImpl("Prompt 1", command2);
        Command container = new SimpleContainerCommand(null, command1);

        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Context context1 = new Context();
        commandRunner1.execute(container, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");
        Assertions.assertThat(context1.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Context context2 = new Context();
        commandRunner2.execute(container, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");
        Assertions.assertThat(context2.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        container.reset();

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Context context3 = new Context();
        commandRunner3.execute(container, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");
        Assertions.assertThat(context3.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
    }

}
