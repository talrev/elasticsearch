/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.painless.node;

import org.elasticsearch.painless.Location;
import org.elasticsearch.painless.ir.ClassNode;
import org.elasticsearch.painless.ir.FieldNode;

import java.util.Set;

/**
 * Represents a member field for its parent class (internal only).
 */
public class SField extends ANode {

    private final int modifiers;
    private final String name;
    private final Class<?> type;
    private final Object instance;

    /**
     * Standard constructor.
     * @param location original location in the source
     * @param modifiers java modifiers for the field
     * @param name name of the field
     * @param type type of the field
     * @param instance initial value for the field
     */
    public SField(Location location, int modifiers, String name, Class<?> type, Object instance) {
        super(location);

        this.modifiers = modifiers;
        this.name = name;
        this.type = type;
        this.instance = instance;
    }

    public String getName() {
        return name;
    }

    public Object getInstance() {
        return instance;
    }

    @Override
    void extractVariables(Set<String> variables) {
        throw createError(new UnsupportedOperationException("unexpected node"));
    }

    @Override
    FieldNode write(ClassNode classNode) {
        FieldNode fieldNode = new FieldNode();

        fieldNode.setLocation(location);
        fieldNode.setModifiers(modifiers);
        fieldNode.setName(name);
        fieldNode.setFieldType(type);
        fieldNode.setInstance(instance);

        return fieldNode;
    }

    @Override
    public String toString() {
        return singleLineToString(name, type);
    }
}
