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
 * Tests for {@link Context}.
 *
 * @author Dmitry Shapovalov
 */
public final class ContextTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public ContextTest() {
        super();
    }

    /**
     * {@link Context} class test.
     */
    @Test
    public void getNamesTest() {
        Context context11 = new Context();
        Assertions.assertThat(context11.getNames()).containsExactlyInOrder();
        Context context12 = new Context(null);
        Assertions.assertThat(context12.getNames()).containsExactlyInOrder();
        Context context13 = new Context(context11);
        Assertions.assertThat(context13.getNames()).containsExactlyInOrder();

        Context context21 = new Context();
        context21.putValue("name1", "value1");
        Assertions.assertThat(context21.getNames()).containsExactlyInOrder("name1");
        Context context22 = new Context(null);
        context22.putValue("name1", "value1");
        Assertions.assertThat(context22.getNames()).containsExactlyInOrder("name1");
        Context context23 = new Context(context21);
        Assertions.assertThat(context23.getNames()).containsExactlyInOrder("name1");
        context23.putValue("name2", "value2");
        Assertions.assertThat(context23.getNames()).containsExactlyInOrder("name1", "name2");
        context23.removeValue("name1");
        Assertions.assertThat(context23.getNames()).containsExactlyInOrder("name2");

        Context context31 = new Context();
        context31.putValue("name2", "value2");
        context31.putValue("name1", "value1");
        context31.putValue("name3", "value3");
        Assertions.assertThat(context31.getNames()).containsExactlyInOrder("name2", "name1", "name3");
        Context context32 = new Context(null);
        context32.putValue("name2", "value2");
        context32.putValue("name1", "value1");
        context32.putValue("name3", "value3");
        Assertions.assertThat(context32.getNames()).containsExactlyInOrder("name2", "name1", "name3");
        Context context33 = new Context(context31);
        context33.putValue("name1", "value1");
        context33.putValue("name2", "value2");
        context33.putValue("name4", "value4");
        Assertions.assertThat(context33.getNames()).containsExactlyInOrder("name2", "name1", "name3", "name4");
        context33.removeValue("name3");
        context33.putValue("name3", "value3");
        Assertions.assertThat(context33.getNames()).containsExactlyInOrder("name2", "name1", "name4", "name3");
    }

    /**
     * {@link Context} class test.
     */
    @Test
    public void hasValueTest() {
        Context context1 = new Context();
        Assertions.assertThat(context1.hasValue(null)).isFalse();
        Assertions.assertThat(context1.hasValue("")).isFalse();
        Assertions.assertThat(context1.hasValue(" ")).isFalse();
        Assertions.assertThat(context1.hasValue("name1")).isFalse();
        Assertions.assertThat(context1.hasValue("name2")).isFalse();
        context1.putValue(null, "value");
        Assertions.assertThat(context1.hasValue(null)).isFalse();
        Assertions.assertThat(context1.hasValue("")).isFalse();
        Assertions.assertThat(context1.hasValue(" ")).isFalse();
        Assertions.assertThat(context1.hasValue("name1")).isFalse();
        Assertions.assertThat(context1.hasValue("name2")).isFalse();
        context1.putValue("", "value");
        Assertions.assertThat(context1.hasValue(null)).isFalse();
        Assertions.assertThat(context1.hasValue("")).isTrue();
        Assertions.assertThat(context1.hasValue(" ")).isFalse();
        Assertions.assertThat(context1.hasValue("name1")).isFalse();
        Assertions.assertThat(context1.hasValue("name2")).isFalse();
        context1.putValue("name1", "value1");
        Assertions.assertThat(context1.hasValue(null)).isFalse();
        Assertions.assertThat(context1.hasValue("")).isTrue();
        Assertions.assertThat(context1.hasValue(" ")).isFalse();
        Assertions.assertThat(context1.hasValue("name1")).isTrue();
        Assertions.assertThat(context1.hasValue("name2")).isFalse();
        context1.putValue("name2", "value2");
        Assertions.assertThat(context1.hasValue(null)).isFalse();
        Assertions.assertThat(context1.hasValue("")).isTrue();
        Assertions.assertThat(context1.hasValue(" ")).isFalse();
        Assertions.assertThat(context1.hasValue("name1")).isTrue();
        Assertions.assertThat(context1.hasValue("name2")).isTrue();
        context1.removeValue("name1");
        Assertions.assertThat(context1.hasValue(null)).isFalse();
        Assertions.assertThat(context1.hasValue("")).isTrue();
        Assertions.assertThat(context1.hasValue(" ")).isFalse();
        Assertions.assertThat(context1.hasValue("name1")).isFalse();
        Assertions.assertThat(context1.hasValue("name2")).isTrue();

        Context context2 = new Context(null);
        Assertions.assertThat(context2.hasValue(null)).isFalse();
        Assertions.assertThat(context2.hasValue("")).isFalse();
        Assertions.assertThat(context2.hasValue(" ")).isFalse();
        Assertions.assertThat(context2.hasValue("name1")).isFalse();
        Assertions.assertThat(context2.hasValue("name2")).isFalse();
        context2.putValue(null, "value");
        Assertions.assertThat(context2.hasValue(null)).isFalse();
        Assertions.assertThat(context2.hasValue("")).isFalse();
        Assertions.assertThat(context2.hasValue(" ")).isFalse();
        Assertions.assertThat(context2.hasValue("name1")).isFalse();
        Assertions.assertThat(context2.hasValue("name2")).isFalse();
        context2.putValue("", "value");
        Assertions.assertThat(context2.hasValue(null)).isFalse();
        Assertions.assertThat(context2.hasValue("")).isTrue();
        Assertions.assertThat(context2.hasValue(" ")).isFalse();
        Assertions.assertThat(context2.hasValue("name1")).isFalse();
        Assertions.assertThat(context2.hasValue("name2")).isFalse();
        context2.putValue("name1", "value1");
        Assertions.assertThat(context2.hasValue(null)).isFalse();
        Assertions.assertThat(context2.hasValue("")).isTrue();
        Assertions.assertThat(context2.hasValue(" ")).isFalse();
        Assertions.assertThat(context2.hasValue("name1")).isTrue();
        Assertions.assertThat(context2.hasValue("name2")).isFalse();
        context2.putValue("name2", "value2");
        Assertions.assertThat(context2.hasValue(null)).isFalse();
        Assertions.assertThat(context2.hasValue("")).isTrue();
        Assertions.assertThat(context2.hasValue(" ")).isFalse();
        Assertions.assertThat(context2.hasValue("name1")).isTrue();
        Assertions.assertThat(context2.hasValue("name2")).isTrue();
        context2.removeValue("name1");
        Assertions.assertThat(context2.hasValue(null)).isFalse();
        Assertions.assertThat(context2.hasValue("")).isTrue();
        Assertions.assertThat(context2.hasValue(" ")).isFalse();
        Assertions.assertThat(context2.hasValue("name1")).isFalse();
        Assertions.assertThat(context2.hasValue("name2")).isTrue();

        Context context3 = new Context(context1);
        Assertions.assertThat(context3.hasValue(null)).isFalse();
        Assertions.assertThat(context3.hasValue("")).isTrue();
        Assertions.assertThat(context3.hasValue(" ")).isFalse();
        Assertions.assertThat(context3.hasValue("name1")).isFalse();
        Assertions.assertThat(context3.hasValue("name2")).isTrue();
        context3.putValue(null, "value");
        Assertions.assertThat(context3.hasValue(null)).isFalse();
        Assertions.assertThat(context3.hasValue("")).isTrue();
        Assertions.assertThat(context3.hasValue(" ")).isFalse();
        Assertions.assertThat(context3.hasValue("name1")).isFalse();
        Assertions.assertThat(context3.hasValue("name2")).isTrue();
        context3.putValue("", "value");
        Assertions.assertThat(context3.hasValue(null)).isFalse();
        Assertions.assertThat(context3.hasValue("")).isTrue();
        Assertions.assertThat(context3.hasValue(" ")).isFalse();
        Assertions.assertThat(context3.hasValue("name1")).isFalse();
        Assertions.assertThat(context3.hasValue("name2")).isTrue();
        context3.putValue("name1", "value1");
        Assertions.assertThat(context3.hasValue(null)).isFalse();
        Assertions.assertThat(context3.hasValue("")).isTrue();
        Assertions.assertThat(context3.hasValue(" ")).isFalse();
        Assertions.assertThat(context3.hasValue("name1")).isTrue();
        Assertions.assertThat(context3.hasValue("name2")).isTrue();
        context3.putValue("name2", "value2");
        Assertions.assertThat(context3.hasValue(null)).isFalse();
        Assertions.assertThat(context3.hasValue("")).isTrue();
        Assertions.assertThat(context3.hasValue(" ")).isFalse();
        Assertions.assertThat(context3.hasValue("name1")).isTrue();
        Assertions.assertThat(context3.hasValue("name2")).isTrue();
        context3.removeValue("name1");
        Assertions.assertThat(context3.hasValue(null)).isFalse();
        Assertions.assertThat(context3.hasValue("")).isTrue();
        Assertions.assertThat(context3.hasValue(" ")).isFalse();
        Assertions.assertThat(context3.hasValue("name1")).isFalse();
        Assertions.assertThat(context3.hasValue("name2")).isTrue();
    }

    /**
     * {@link Context} class test.
     */
    @Test
    public void getValueTest() {
        Context context1 = new Context();
        Assertions.assertThat(context1.getValue(null)).isNull();
        Assertions.assertThat(context1.getValue("")).isNull();
        Assertions.assertThat(context1.getValue(" ")).isNull();
        Assertions.assertThat(context1.getValue("name1")).isNull();
        Assertions.assertThat(context1.getValue("name2")).isNull();
        context1.putValue(null, "value");
        Assertions.assertThat(context1.getValue(null)).isNull();
        Assertions.assertThat(context1.getValue("")).isNull();
        Assertions.assertThat(context1.getValue(" ")).isNull();
        Assertions.assertThat(context1.getValue("name1")).isNull();
        Assertions.assertThat(context1.getValue("name2")).isNull();
        context1.putValue("", "value");
        Assertions.assertThat(context1.getValue(null)).isNull();
        Assertions.assertThat(context1.getValue("")).isEqualTo("value");
        Assertions.assertThat(context1.getValue(" ")).isNull();
        Assertions.assertThat(context1.getValue("name1")).isNull();
        Assertions.assertThat(context1.getValue("name2")).isNull();
        context1.putValue("name1", "value1");
        Assertions.assertThat(context1.getValue(null)).isNull();
        Assertions.assertThat(context1.getValue("")).isEqualTo("value");
        Assertions.assertThat(context1.getValue(" ")).isNull();
        Assertions.assertThat(context1.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context1.getValue("name2")).isNull();
        context1.putValue("name2", "value2");
        Assertions.assertThat(context1.getValue(null)).isNull();
        Assertions.assertThat(context1.getValue("")).isEqualTo("value");
        Assertions.assertThat(context1.getValue(" ")).isNull();
        Assertions.assertThat(context1.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context1.getValue("name2")).isEqualTo("value2");
        context1.removeValue("name1");
        Assertions.assertThat(context1.getValue(null)).isNull();
        Assertions.assertThat(context1.getValue("")).isEqualTo("value");
        Assertions.assertThat(context1.getValue(" ")).isNull();
        Assertions.assertThat(context1.getValue("name1")).isNull();
        Assertions.assertThat(context1.getValue("name2")).isEqualTo("value2");

        Context context2 = new Context(null);
        Assertions.assertThat(context2.getValue(null)).isNull();
        Assertions.assertThat(context2.getValue("")).isNull();
        Assertions.assertThat(context2.getValue(" ")).isNull();
        Assertions.assertThat(context2.getValue("name1")).isNull();
        Assertions.assertThat(context2.getValue("name2")).isNull();
        context2.putValue(null, "value");
        Assertions.assertThat(context2.getValue(null)).isNull();
        Assertions.assertThat(context2.getValue("")).isNull();
        Assertions.assertThat(context2.getValue(" ")).isNull();
        Assertions.assertThat(context2.getValue("name1")).isNull();
        Assertions.assertThat(context2.getValue("name2")).isNull();
        context2.putValue("", "value");
        Assertions.assertThat(context2.getValue(null)).isNull();
        Assertions.assertThat(context2.getValue("")).isEqualTo("value");
        Assertions.assertThat(context2.getValue(" ")).isNull();
        Assertions.assertThat(context2.getValue("name1")).isNull();
        Assertions.assertThat(context2.getValue("name2")).isNull();
        context2.putValue("name1", "value1");
        Assertions.assertThat(context2.getValue(null)).isNull();
        Assertions.assertThat(context2.getValue("")).isEqualTo("value");
        Assertions.assertThat(context2.getValue(" ")).isNull();
        Assertions.assertThat(context2.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context2.getValue("name2")).isNull();
        context2.putValue("name2", "value2");
        Assertions.assertThat(context2.getValue(null)).isNull();
        Assertions.assertThat(context2.getValue("")).isEqualTo("value");
        Assertions.assertThat(context2.getValue(" ")).isNull();
        Assertions.assertThat(context2.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context2.getValue("name2")).isEqualTo("value2");
        context2.removeValue("name1");
        Assertions.assertThat(context2.getValue(null)).isNull();
        Assertions.assertThat(context2.getValue("")).isEqualTo("value");
        Assertions.assertThat(context2.getValue(" ")).isNull();
        Assertions.assertThat(context2.getValue("name1")).isNull();
        Assertions.assertThat(context2.getValue("name2")).isEqualTo("value2");

        Context context3 = new Context(context1);
        Assertions.assertThat(context3.getValue(null)).isNull();
        Assertions.assertThat(context3.getValue("")).isEqualTo("value");
        Assertions.assertThat(context3.getValue(" ")).isNull();
        Assertions.assertThat(context3.getValue("name1")).isNull();
        Assertions.assertThat(context3.getValue("name2")).isEqualTo("value2");
        context3.putValue(null, "value");
        Assertions.assertThat(context3.getValue(null)).isNull();
        Assertions.assertThat(context3.getValue("")).isEqualTo("value");
        Assertions.assertThat(context3.getValue(" ")).isNull();
        Assertions.assertThat(context3.getValue("name1")).isNull();
        Assertions.assertThat(context3.getValue("name2")).isEqualTo("value2");
        context3.putValue("", "value");
        Assertions.assertThat(context3.getValue(null)).isNull();
        Assertions.assertThat(context3.getValue("")).isEqualTo("value");
        Assertions.assertThat(context3.getValue(" ")).isNull();
        Assertions.assertThat(context3.getValue("name1")).isNull();
        Assertions.assertThat(context3.getValue("name2")).isEqualTo("value2");
        context3.putValue("name1", "value1");
        Assertions.assertThat(context3.getValue(null)).isNull();
        Assertions.assertThat(context3.getValue("")).isEqualTo("value");
        Assertions.assertThat(context3.getValue(" ")).isNull();
        Assertions.assertThat(context3.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context3.getValue("name2")).isEqualTo("value2");
        context3.putValue("name2", "value2");
        Assertions.assertThat(context3.getValue(null)).isNull();
        Assertions.assertThat(context3.getValue("")).isEqualTo("value");
        Assertions.assertThat(context3.getValue(" ")).isNull();
        Assertions.assertThat(context3.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context3.getValue("name2")).isEqualTo("value2");
        context3.removeValue("name1");
        Assertions.assertThat(context3.getValue(null)).isNull();
        Assertions.assertThat(context3.getValue("")).isEqualTo("value");
        Assertions.assertThat(context3.getValue(" ")).isNull();
        Assertions.assertThat(context3.getValue("name1")).isNull();
        Assertions.assertThat(context3.getValue("name2")).isEqualTo("value2");
    }

    /**
     * {@link Context} class test.
     */
    @Test
    public void putValueTest() {
        Context context1 = new Context();
        context1.putValue("name1", "value1");
        context1.putValue("name2", "value2");
        context1.putValue("name3", "value3");
        Context context2 = new Context(context1);
        Assertions.assertThat(context1.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context1.getValue("name2")).isEqualTo("value2");
        Assertions.assertThat(context1.getValue("name3")).isEqualTo("value3");
        Assertions.assertThat(context2.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context2.getValue("name2")).isEqualTo("value2");
        Assertions.assertThat(context2.getValue("name3")).isEqualTo("value3");

        context1.putValue("name2", "value");
        context1.putValue("name4", "value4");
        Assertions.assertThat(context1.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context1.getValue("name2")).isEqualTo("value");
        Assertions.assertThat(context1.getValue("name3")).isEqualTo("value3");
        Assertions.assertThat(context1.getValue("name4")).isEqualTo("value4");
        Assertions.assertThat(context2.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context2.getValue("name2")).isEqualTo("value2");
        Assertions.assertThat(context2.getValue("name3")).isEqualTo("value3");

        context2.putValue("name3", "value");
        context2.putValue("name5", "value5");
        Assertions.assertThat(context1.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context1.getValue("name2")).isEqualTo("value");
        Assertions.assertThat(context1.getValue("name3")).isEqualTo("value3");
        Assertions.assertThat(context1.getValue("name4")).isEqualTo("value4");
        Assertions.assertThat(context2.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context2.getValue("name2")).isEqualTo("value2");
        Assertions.assertThat(context2.getValue("name3")).isEqualTo("value");
        Assertions.assertThat(context2.getValue("name5")).isEqualTo("value5");
    }

    /**
     * {@link Context} class test.
     */
    @Test
    public void removeValueTest() {
        Context context1 = new Context();
        context1.putValue("name1", "value1");
        context1.putValue("name2", "value2");
        context1.putValue("name3", "value3");
        Context context2 = new Context(context1);
        Assertions.assertThat(context1.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context1.getValue("name2")).isEqualTo("value2");
        Assertions.assertThat(context1.getValue("name3")).isEqualTo("value3");
        Assertions.assertThat(context2.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context2.getValue("name2")).isEqualTo("value2");
        Assertions.assertThat(context2.getValue("name3")).isEqualTo("value3");

        context1.removeValue("name");
        context1.removeValue("name2");
        Assertions.assertThat(context1.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context1.getValue("name3")).isEqualTo("value3");
        Assertions.assertThat(context2.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context2.getValue("name2")).isEqualTo("value2");
        Assertions.assertThat(context2.getValue("name3")).isEqualTo("value3");

        context2.removeValue("name");
        context2.removeValue("name3");
        Assertions.assertThat(context1.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context1.getValue("name3")).isEqualTo("value3");
        Assertions.assertThat(context2.getValue("name1")).isEqualTo("value1");
        Assertions.assertThat(context2.getValue("name2")).isEqualTo("value2");
    }

}
