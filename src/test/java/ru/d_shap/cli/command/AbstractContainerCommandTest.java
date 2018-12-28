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
 * Tests for {@link AbstractContainerCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractContainerCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractContainerCommandTest() {
        super();
    }

    /**
     * {@link AbstractContainerCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os01 = createOutputStream();
        InputStream is01 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner01 = new CommandRunner(os01, is01);
        Command container01 = new AbstractContainerCommandImpl(null);
        Context context01 = new Context();
        commandRunner01.execute(container01, context01);
        Assertions.assertThat(getLines(os01)).containsExactlyInOrder();
        Assertions.assertThat(context01.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context01.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(100);

        ByteArrayOutputStream os02 = createOutputStream();
        InputStream is02 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner02 = new CommandRunner(os02, is02);
        Command command02 = new AbstractCommandImpl("Prompt 1");
        Command container02 = new AbstractContainerCommandImpl(command02);
        Context context02 = new Context();
        commandRunner02.execute(container02, context02);
        Assertions.assertThat(getLines(os02)).containsExactlyInOrder("Prompt 1", "Строка 1");
        Assertions.assertThat(context02.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context02.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(101);

        ByteArrayOutputStream os03 = createOutputStream();
        InputStream is03 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner03 = new CommandRunner(os03, is03);
        Command command033 = new AbstractCommandImpl("Prompt 3");
        Command command032 = new AbstractCommandImpl("Prompt 2", command033);
        Command command031 = new AbstractCommandImpl("Prompt 1", command032);
        Command container03 = new AbstractContainerCommandImpl(command031);
        Context context03 = new Context();
        commandRunner03.execute(container03, context03);
        Assertions.assertThat(getLines(os03)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");
        Assertions.assertThat(context03.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context03.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(103);

        ByteArrayOutputStream os04 = createOutputStream();
        InputStream is04 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner04 = new CommandRunner(os04, is04);
        Command container04 = new AbstractContainerCommandImpl(null, null);
        Context context04 = new Context();
        commandRunner04.execute(container04, context04);
        Assertions.assertThat(getLines(os04)).containsExactlyInOrder();
        Assertions.assertThat(context04.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context04.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(100);

        ByteArrayOutputStream os05 = createOutputStream();
        InputStream is05 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner05 = new CommandRunner(os05, is05);
        Command command05 = new AbstractCommandImpl("Prompt 1");
        Command container05 = new AbstractContainerCommandImpl(null, command05);
        Context context05 = new Context();
        commandRunner05.execute(container05, context05);
        Assertions.assertThat(getLines(os05)).containsExactlyInOrder("Prompt 1", "Строка 1");
        Assertions.assertThat(context05.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context05.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(101);

        ByteArrayOutputStream os06 = createOutputStream();
        InputStream is06 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner06 = new CommandRunner(os06, is06);
        Command command063 = new AbstractCommandImpl("Prompt 3");
        Command command062 = new AbstractCommandImpl("Prompt 2", command063);
        Command command061 = new AbstractCommandImpl("Prompt 1", command062);
        Command container06 = new AbstractContainerCommandImpl(null, command061);
        Context context06 = new Context();
        commandRunner06.execute(container06, context06);
        Assertions.assertThat(getLines(os06)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");
        Assertions.assertThat(context06.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context06.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(103);

        ByteArrayOutputStream os07 = createOutputStream();
        InputStream is07 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner07 = new CommandRunner(os07, is07);
        Command parentCommand07 = new AbstractExecutionCommandImpl("message");
        Command container07 = new AbstractContainerCommandImpl(parentCommand07, null);
        Context context07 = new Context();
        commandRunner07.execute(container07, context07);
        Assertions.assertThat(getLines(os07)).containsExactlyInOrder("message");
        Assertions.assertThat(context07.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context07.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(100);

        ByteArrayOutputStream os08 = createOutputStream();
        InputStream is08 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner08 = new CommandRunner(os08, is08);
        Command parentCommand08 = new AbstractExecutionCommandImpl("message");
        Command command08 = new AbstractCommandImpl("Prompt 1");
        Command container08 = new AbstractContainerCommandImpl(parentCommand08, command08);
        Context context08 = new Context();
        commandRunner08.execute(container08, context08);
        Assertions.assertThat(getLines(os08)).containsExactlyInOrder("Prompt 1", "Строка 1", "message");
        Assertions.assertThat(context08.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context08.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(101);

        ByteArrayOutputStream os09 = createOutputStream();
        InputStream is09 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner09 = new CommandRunner(os09, is09);
        Command parentCommand09 = new AbstractExecutionCommandImpl("message");
        Command command093 = new AbstractCommandImpl("Prompt 3");
        Command command092 = new AbstractCommandImpl("Prompt 2", command093);
        Command command091 = new AbstractCommandImpl("Prompt 1", command092);
        Command container09 = new AbstractContainerCommandImpl(parentCommand09, command091);
        Context context09 = new Context();
        commandRunner09.execute(container09, context09);
        Assertions.assertThat(getLines(os09)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3", "message");
        Assertions.assertThat(context09.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context09.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(103);

        ByteArrayOutputStream os10 = createOutputStream();
        InputStream is10 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner10 = new CommandRunner(os10, is10);
        Command parentCommand10 = new AbstractExecutionCommandImpl("message");
        Command command103 = new AbstractCommandImpl("Prompt 3");
        Command command102 = new AbstractCommandImpl("Prompt 2", command103);
        Command command101 = new AbstractCommandImpl("Prompt 1", command102);
        Command container10 = new AbstractContainerCommandImpl(parentCommand10, command101);
        Context context10 = new Context();
        context10.putValue(AbstractContainerCommandImpl.CONTAINER_KEY, 50);
        commandRunner10.execute(container10, context10);
        Assertions.assertThat(getLines(os10)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3", "message");
        Assertions.assertThat(context10.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context10.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(53);

        ByteArrayOutputStream os11 = createOutputStream();
        InputStream is11 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner11 = new CommandRunner(os11, is11);
        Command parentCommand11 = new AbstractExecutionCommandImpl("message");
        Command command113 = new AbstractCommandImpl("Prompt 3");
        Command command112 = new AbstractCommandImpl("Prompt 2", command113);
        Command command111 = new AbstractCommandImpl("Prompt 1", command112);
        Command container11 = new AbstractContainerCommandImpl(parentCommand11, command111);
        Context context11 = new Context();
        context11.putValue(AbstractCommandImpl.INCREMENT_KEY, -1);
        context11.putValue(AbstractContainerCommandImpl.CONTAINER_KEY, 50);
        commandRunner11.execute(container11, context11);
        Assertions.assertThat(getLines(os11)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3", "message");
        Assertions.assertThat(context11.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context11.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(47);
    }

}
