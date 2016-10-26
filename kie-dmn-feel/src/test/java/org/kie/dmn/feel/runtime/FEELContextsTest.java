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

package org.kie.dmn.feel.runtime;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import org.junit.runners.Parameterized;

public class FEELContextsTest extends BaseFEELTest {

    @Parameterized.Parameters(name = "{index}: {0} ({1}) = {2}")
    public static Collection<Object[]> data() {
        final Object[][] cases = new Object[][] {
                { "{ first name : \"Bob\", birthday : date(\"1978-09-12\"), salutation : \"Hello \"+first name }",
                        new HashMap<String,Object>() {{
                            put( "first name", "Bob" );
                            put( "birthday", LocalDate.of(1978, 9, 12) );
                            put( "salutation", "Hello Bob" );
                        }} },
                // nested contexts + qualified name
                { "{ full name : { first name: \"Bob\", last name : \"Doe\" }, birthday : date(\"1978-09-12\"), salutation : \"Hello \"+full name.first name }",
                        new HashMap<String,Object>() {{
                            put( "full name", new HashMap<String,Object>() {{
                                put( "first name", "Bob" );
                                put( "last name", "Doe" );
                            }} );
                            put( "birthday", LocalDate.of(1978, 9, 12) );
                            put( "salutation", "Hello Bob" );
                        }} },
                // Example from spec. chapter "10.3.2.7 Ranges"
                { "{ startdate: date(\"1978-09-12\"), enddate: date(\"1978-10-12\"), rangedates: [startdate..enddate] }",
                        new HashMap<String,Object>() {{
                            put( "startdate", LocalDate.of(1978, 9, 12) );
                            put( "enddate", LocalDate.of(1978, 10, 13) );
                            put( "rangedates", "[\"1978-09-12\"..\"1978-10-12\"]" );
                }} }
        };
        return Arrays.asList( cases );
    }
}