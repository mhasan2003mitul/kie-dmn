/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.dmn.feel.lang.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.kie.dmn.feel.lang.EvaluationContext;
import org.kie.dmn.feel.runtime.Range;
import org.kie.dmn.feel.runtime.UnaryTest;

public class InNode
        extends BaseNode {

    private BaseNode value;
    private BaseNode exprs;

    public InNode(ParserRuleContext ctx, BaseNode value, BaseNode exprs) {
        super( ctx );
        this.value = value;
        this.exprs = exprs;
    }

    public BaseNode getValue() {
        return value;
    }

    public void setValue(BaseNode value) {
        this.value = value;
    }

    public BaseNode getExprs() {
        return exprs;
    }

    public void setExprs(BaseNode exprs) {
        this.exprs = exprs;
    }

    @Override
    public Boolean evaluate(EvaluationContext ctx) {
        Object value = this.value.evaluate( ctx );
        Object expr = this.exprs.evaluate( ctx );
        if ( expr != null ) {
            if ( expr instanceof Iterable ) {
                // evaluate in the collection
                for ( Object e : ((Iterable) expr) ) {
                    // have to compare to Boolean.TRUE because in() might return null
                    if ( in( value, e ) == Boolean.TRUE ) {
                        return true;
                    }
                }
                return false;
            } else {
                // evaluate single entity
                return in( value, expr );
            }
        }
        return null;
    }

    private Boolean in(Object value, Object expr) {
        // need to improve this to work with unary tests
        if ( expr == null ) {
            return value == expr;
        } else if ( expr instanceof UnaryTest ) {
            return ((UnaryTest) expr).apply( value );
        } else if ( expr instanceof Range ) {
            if( !( value instanceof Comparable ) ) {
                return null;
            }
            return ((Range) expr).includes( (Comparable) value );
        } else if ( value != null ) {
            return value.equals( expr );
        } else {
            // value == null, expr != null and not Unary test
            return Boolean.FALSE;
        }
    }
}
