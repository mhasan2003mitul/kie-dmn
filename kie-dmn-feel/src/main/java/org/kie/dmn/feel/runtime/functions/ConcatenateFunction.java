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

package org.kie.dmn.feel.runtime.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConcatenateFunction
        extends BaseFEELFunction {

    public ConcatenateFunction() {
        super( "concatenate" );
    }

    public List apply(@ParameterName("list") Object[] lists) {
        if ( lists == null ) {
            return null;
        }
        // spec requires us to return a new list
        List result = new ArrayList();
        for ( Object list : lists ) {
            if ( list instanceof Collection ) {
                result.addAll( (Collection) list );
            } else if ( list != null ) {
                result.add( list );
            } else {
                return null;
            }
        }
        return result;
    }
}
