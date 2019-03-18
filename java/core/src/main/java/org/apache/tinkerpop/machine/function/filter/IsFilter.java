/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tinkerpop.machine.function.filter;

import org.apache.tinkerpop.machine.bytecode.Argument;
import org.apache.tinkerpop.machine.bytecode.P;
import org.apache.tinkerpop.machine.coefficient.Coefficient;
import org.apache.tinkerpop.machine.function.AbstractFunction;
import org.apache.tinkerpop.machine.function.FilterFunction;
import org.apache.tinkerpop.machine.traverser.Traverser;
import org.apache.tinkerpop.util.StringFactory;

import java.util.Set;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public final class IsFilter<C, S> extends AbstractFunction<C> implements FilterFunction<C, S> {

    private final P.Type predicate;
    private final Argument<S> argument;

    public IsFilter(final Coefficient<C> coefficient, final Set<String> labels, final P.Type predicate, final Argument<S> argument) {
        super(coefficient, labels);
        this.predicate = predicate;
        this.argument = argument;
    }

    @Override
    public boolean test(final Traverser<C, S> traverser) {
        return this.predicate.test(traverser.object(), this.argument.getArg(traverser));
    }

    @Override
    public String toString() {
        return StringFactory.makeFunctionString(this, this.predicate, this.argument);
    }
}
