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
package ru.d_shap.cli.data;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;

/**
 * Tests for {@link ValueHolder}.
 *
 * @author Dmitry Shapovalov
 */
public final class ValueHolderTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public ValueHolderTest() {
        super();
    }

    /**
     * {@link ValueHolder} class test.
     */
    @Test
    public void getValueTest() {
        ValueLoaderImpl<String> valueLoader1 = new ValueLoaderImpl<>(null, true);
        ValueHolder<String> valueHolder1 = new ValueHolder<>(valueLoader1);
        Assertions.assertThat(valueHolder1.getValue()).isNull();
        Assertions.assertThat(valueHolder1.getValue()).isNull();

        ValueLoaderImpl<String> valueLoader2 = new ValueLoaderImpl<>("", true);
        ValueHolder<String> valueHolder2 = new ValueHolder<>(valueLoader2);
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("");
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("");

        ValueLoaderImpl<String> valueLoader3 = new ValueLoaderImpl<>("value", true);
        ValueHolder<String> valueHolder3 = new ValueHolder<>(valueLoader3);
        Assertions.assertThat(valueHolder3.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder3.getValue()).isEqualTo("value");

        ValueLoaderImpl<Integer> valueLoader4 = new ValueLoaderImpl<>(10, true);
        ValueHolder<Integer> valueHolder4 = new ValueHolder<>(valueLoader4);
        Assertions.assertThat(valueHolder4.getValue()).isEqualTo(10);
        Assertions.assertThat(valueHolder4.getValue()).isEqualTo(10);
    }

    /**
     * {@link ValueHolder} class test.
     */
    @Test(expected = NullPointerException.class)
    public void getValueNullValueLoaderFailTest() {
        ValueHolder<String> valueHolder = new ValueHolder<>(null);
        valueHolder.getValue();
    }

    /**
     * {@link ValueHolder} class test.
     */
    @Test
    public void resetTest() {
        ValueLoaderImpl<String> valueLoader1 = new ValueLoaderImpl<>("value", true);
        ValueHolder<String> valueHolder1 = new ValueHolder<>(valueLoader1);
        Assertions.assertThat(valueHolder1.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder1.getValue()).isEqualTo("value");
        valueHolder1.reset();
        try {
            valueHolder1.getValue();
            Assertions.fail("ValueHolder test fail");
        } catch (ValueLoadException ex) {
            Assertions.assertThat(ex).hasMessage("Not first call!");
        }

        ValueLoaderImpl<String> valueLoader2 = new ValueLoaderImpl<>("value", false);
        ValueHolder<String> valueHolder2 = new ValueHolder<>(valueLoader2);
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
        valueHolder2.reset();
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
    }

}
