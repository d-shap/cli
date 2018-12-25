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
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command container1 = new AbstractContainerCommandImpl(null);
        Context context1 = new Context();
        commandRunner1.execute(container1, context1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder();
        Assertions.assertThat(context1.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context1.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(100);

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command command2 = new AbstractCommandImpl("Prompt 1");
        Command container2 = new AbstractContainerCommandImpl(command2);
        Context context2 = new Context();
        commandRunner2.execute(container2, context2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("Prompt 1", "Строка 1");
        Assertions.assertThat(context2.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context2.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(101);

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command command33 = new AbstractCommandImpl("Prompt 3");
        Command command32 = new AbstractCommandImpl("Prompt 2", command33);
        Command command31 = new AbstractCommandImpl("Prompt 1", command32);
        Command container3 = new AbstractContainerCommandImpl(command31);
        Context context3 = new Context();
        commandRunner3.execute(container3, context3);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");
        Assertions.assertThat(context3.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();
        Assertions.assertThat(context3.getValue(AbstractContainerCommandImpl.CONTAINER_KEY)).isEqualTo(103);
    }

}
