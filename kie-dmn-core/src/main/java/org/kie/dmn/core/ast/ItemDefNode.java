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

package org.kie.dmn.core.ast;

import org.kie.dmn.core.api.DMNType;
import org.kie.dmn.feel.model.v1_1.ItemDefinition;

public class ItemDefNode
        extends DMNBaseNode
        implements DMNNode {

    private ItemDefinition itemDef;
    private DMNType        type;

    public ItemDefNode(ItemDefinition itemDef) {
        this( itemDef, null );
    }

    public ItemDefNode(ItemDefinition itemDef, DMNType type) {
        super( itemDef );
        this.itemDef = itemDef;
        this.type = type;
    }

    public ItemDefinition getItemDef() {
        return itemDef;
    }

    public void setItemDef(ItemDefinition itemDef) {
        this.itemDef = itemDef;
    }

    public String getId() {
        return itemDef.getId();
    }

    public String getName() {
        return itemDef.getName();
    }

    public boolean isCollection() {
        return itemDef.isIsCollection();
    }

    public DMNType getType() {
        return type;
    }

    public void setType(DMNType type) {
        this.type = type;
    }
}
