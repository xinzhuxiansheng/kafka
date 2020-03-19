/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// THIS CODE IS AUTOMATICALLY GENERATED.  DO NOT EDIT.

package org.apache.kafka.common.message;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import org.apache.kafka.common.errors.UnsupportedVersionException;
import org.apache.kafka.common.protocol.ApiMessage;
import org.apache.kafka.common.protocol.Message;
import org.apache.kafka.common.protocol.MessageUtil;
import org.apache.kafka.common.protocol.ObjectSerializationCache;
import org.apache.kafka.common.protocol.Readable;
import org.apache.kafka.common.protocol.Writable;
import org.apache.kafka.common.protocol.types.ArrayOf;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.protocol.types.RawTaggedField;
import org.apache.kafka.common.protocol.types.RawTaggedFieldWriter;
import org.apache.kafka.common.protocol.types.Schema;
import org.apache.kafka.common.protocol.types.Struct;
import org.apache.kafka.common.protocol.types.Type;
import org.apache.kafka.common.utils.ByteUtils;
import org.apache.kafka.common.utils.ImplicitLinkedHashMultiCollection;


public class AlterConfigsRequestData implements ApiMessage {
    private AlterConfigsResourceCollection resources;
    private boolean validateOnly;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
            new Field("resources", new ArrayOf(AlterConfigsResource.SCHEMA_0), "The updates for each resource."),
            new Field("validate_only", Type.BOOLEAN, "True if we should validate the request, but not change the configurations.")
        );
    
    public static final Schema SCHEMA_1 = SCHEMA_0;
    
    public static final Schema[] SCHEMAS = new Schema[] {
        SCHEMA_0,
        SCHEMA_1
    };
    
    public AlterConfigsRequestData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public AlterConfigsRequestData(Struct struct, short _version) {
        fromStruct(struct, _version);
    }
    
    public AlterConfigsRequestData() {
        this.resources = new AlterConfigsResourceCollection(0);
        this.validateOnly = false;
    }
    
    @Override
    public short apiKey() {
        return 33;
    }
    
    @Override
    public short lowestSupportedVersion() {
        return 0;
    }
    
    @Override
    public short highestSupportedVersion() {
        return 1;
    }
    
    @Override
    public void read(Readable _readable, short _version) {
        {
            int arrayLength;
            arrayLength = _readable.readInt();
            if (arrayLength < 0) {
                throw new RuntimeException("non-nullable field resources was serialized as null");
            } else {
                AlterConfigsResourceCollection newCollection = new AlterConfigsResourceCollection(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    newCollection.add(new AlterConfigsResource(_readable, _version));
                }
                this.resources = newCollection;
            }
        }
        this.validateOnly = _readable.readByte() != 0;
        this._unknownTaggedFields = null;
    }
    
    @Override
    public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
        int _numTaggedFields = 0;
        _writable.writeInt(resources.size());
        for (AlterConfigsResource resourcesElement : resources) {
            resourcesElement.write(_writable, _cache, _version);
        }
        _writable.writeByte(validateOnly ? (byte) 1 : (byte) 0);
        RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
        _numTaggedFields += _rawWriter.numFields();
        if (_numTaggedFields > 0) {
            throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void fromStruct(Struct struct, short _version) {
        this._unknownTaggedFields = null;
        {
            Object[] _nestedObjects = struct.getArray("resources");
            this.resources = new AlterConfigsResourceCollection(_nestedObjects.length);
            for (Object nestedObject : _nestedObjects) {
                this.resources.add(new AlterConfigsResource((Struct) nestedObject, _version));
            }
        }
        this.validateOnly = struct.getBoolean("validate_only");
    }
    
    @Override
    public Struct toStruct(short _version) {
        TreeMap<Integer, Object> _taggedFields = null;
        Struct struct = new Struct(SCHEMAS[_version]);
        {
            Struct[] _nestedObjects = new Struct[resources.size()];
            int i = 0;
            for (AlterConfigsResource element : this.resources) {
                _nestedObjects[i++] = element.toStruct(_version);
            }
            struct.set("resources", (Object[]) _nestedObjects);
        }
        struct.set("validate_only", this.validateOnly);
        return struct;
    }
    
    @Override
    public int size(ObjectSerializationCache _cache, short _version) {
        int _size = 0, _numTaggedFields = 0;
        {
            int _arraySize = 0;
            _arraySize += 4;
            for (AlterConfigsResource resourcesElement : resources) {
                _arraySize += resourcesElement.size(_cache, _version);
            }
            _size += _arraySize;
        }
        _size += 1;
        if (_unknownTaggedFields != null) {
            _numTaggedFields += _unknownTaggedFields.size();
            for (RawTaggedField _field : _unknownTaggedFields) {
                _size += ByteUtils.sizeOfUnsignedVarint(_field.tag());
                _size += ByteUtils.sizeOfUnsignedVarint(_field.size());
                _size += _field.size();
            }
        }
        if (_numTaggedFields > 0) {
            throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
        }
        return _size;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AlterConfigsRequestData)) return false;
        AlterConfigsRequestData other = (AlterConfigsRequestData) obj;
        if (this.resources == null) {
            if (other.resources != null) return false;
        } else {
            if (!this.resources.equals(other.resources)) return false;
        }
        if (validateOnly != other.validateOnly) return false;
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + (resources == null ? 0 : resources.hashCode());
        hashCode = 31 * hashCode + (validateOnly ? 1231 : 1237);
        return hashCode;
    }
    
    @Override
    public String toString() {
        return "AlterConfigsRequestData("
            + "resources=" + MessageUtil.deepToString(resources.iterator())
            + ", validateOnly=" + (validateOnly ? "true" : "false")
            + ")";
    }
    
    public AlterConfigsResourceCollection resources() {
        return this.resources;
    }
    
    public boolean validateOnly() {
        return this.validateOnly;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public AlterConfigsRequestData setResources(AlterConfigsResourceCollection v) {
        this.resources = v;
        return this;
    }
    
    public AlterConfigsRequestData setValidateOnly(boolean v) {
        this.validateOnly = v;
        return this;
    }
    
    static public class AlterConfigsResource implements Message, ImplicitLinkedHashMultiCollection.Element {
        private byte resourceType;
        private String resourceName;
        private AlterableConfigCollection configs;
        private List<RawTaggedField> _unknownTaggedFields;
        private int next;
        private int prev;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("resource_type", Type.INT8, "The resource type."),
                new Field("resource_name", Type.STRING, "The resource name."),
                new Field("configs", new ArrayOf(AlterableConfig.SCHEMA_0), "The configurations.")
            );
        
        public static final Schema SCHEMA_1 = SCHEMA_0;
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0,
            SCHEMA_1
        };
        
        public AlterConfigsResource(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public AlterConfigsResource(Struct struct, short _version) {
            fromStruct(struct, _version);
        }
        
        public AlterConfigsResource() {
            this.resourceType = (byte) 0;
            this.resourceName = "";
            this.configs = new AlterableConfigCollection(0);
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 0;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 1;
        }
        
        @Override
        public void read(Readable _readable, short _version) {
            if (_version > 1) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of AlterConfigsResource");
            }
            this.resourceType = _readable.readByte();
            {
                int length;
                length = _readable.readShort();
                if (length < 0) {
                    throw new RuntimeException("non-nullable field resourceName was serialized as null");
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field resourceName had invalid length " + length);
                } else {
                    this.resourceName = _readable.readString(length);
                }
            }
            {
                int arrayLength;
                arrayLength = _readable.readInt();
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field configs was serialized as null");
                } else {
                    AlterableConfigCollection newCollection = new AlterableConfigCollection(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new AlterableConfig(_readable, _version));
                    }
                    this.configs = newCollection;
                }
            }
            this._unknownTaggedFields = null;
        }
        
        @Override
        public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
            if (_version > 1) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of AlterConfigsResource");
            }
            int _numTaggedFields = 0;
            _writable.writeByte(resourceType);
            {
                byte[] _stringBytes = _cache.getSerializedValue(resourceName);
                _writable.writeShort((short) _stringBytes.length);
                _writable.writeByteArray(_stringBytes);
            }
            _writable.writeInt(configs.size());
            for (AlterableConfig configsElement : configs) {
                configsElement.write(_writable, _cache, _version);
            }
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            if (_numTaggedFields > 0) {
                throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
            }
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public void fromStruct(Struct struct, short _version) {
            if (_version > 1) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of AlterConfigsResource");
            }
            this._unknownTaggedFields = null;
            this.resourceType = struct.getByte("resource_type");
            this.resourceName = struct.getString("resource_name");
            {
                Object[] _nestedObjects = struct.getArray("configs");
                this.configs = new AlterableConfigCollection(_nestedObjects.length);
                for (Object nestedObject : _nestedObjects) {
                    this.configs.add(new AlterableConfig((Struct) nestedObject, _version));
                }
            }
        }
        
        @Override
        public Struct toStruct(short _version) {
            if (_version > 1) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of AlterConfigsResource");
            }
            TreeMap<Integer, Object> _taggedFields = null;
            Struct struct = new Struct(SCHEMAS[_version]);
            struct.set("resource_type", this.resourceType);
            struct.set("resource_name", this.resourceName);
            {
                Struct[] _nestedObjects = new Struct[configs.size()];
                int i = 0;
                for (AlterableConfig element : this.configs) {
                    _nestedObjects[i++] = element.toStruct(_version);
                }
                struct.set("configs", (Object[]) _nestedObjects);
            }
            return struct;
        }
        
        @Override
        public int size(ObjectSerializationCache _cache, short _version) {
            int _size = 0, _numTaggedFields = 0;
            if (_version > 1) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of AlterConfigsResource");
            }
            _size += 1;
            {
                byte[] _stringBytes = resourceName.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'resourceName' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(resourceName, _stringBytes);
                _size += _stringBytes.length + 2;
            }
            {
                int _arraySize = 0;
                _arraySize += 4;
                for (AlterableConfig configsElement : configs) {
                    _arraySize += configsElement.size(_cache, _version);
                }
                _size += _arraySize;
            }
            if (_unknownTaggedFields != null) {
                _numTaggedFields += _unknownTaggedFields.size();
                for (RawTaggedField _field : _unknownTaggedFields) {
                    _size += ByteUtils.sizeOfUnsignedVarint(_field.tag());
                    _size += ByteUtils.sizeOfUnsignedVarint(_field.size());
                    _size += _field.size();
                }
            }
            if (_numTaggedFields > 0) {
                throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
            }
            return _size;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof AlterConfigsResource)) return false;
            AlterConfigsResource other = (AlterConfigsResource) obj;
            if (resourceType != other.resourceType) return false;
            if (this.resourceName == null) {
                if (other.resourceName != null) return false;
            } else {
                if (!this.resourceName.equals(other.resourceName)) return false;
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + resourceType;
            hashCode = 31 * hashCode + (resourceName == null ? 0 : resourceName.hashCode());
            return hashCode;
        }
        
        @Override
        public String toString() {
            return "AlterConfigsResource("
                + "resourceType=" + resourceType
                + ", resourceName=" + ((resourceName == null) ? "null" : "'" + resourceName.toString() + "'")
                + ", configs=" + MessageUtil.deepToString(configs.iterator())
                + ")";
        }
        
        public byte resourceType() {
            return this.resourceType;
        }
        
        public String resourceName() {
            return this.resourceName;
        }
        
        public AlterableConfigCollection configs() {
            return this.configs;
        }
        
        @Override
        public int next() {
            return this.next;
        }
        
        @Override
        public int prev() {
            return this.prev;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public AlterConfigsResource setResourceType(byte v) {
            this.resourceType = v;
            return this;
        }
        
        public AlterConfigsResource setResourceName(String v) {
            this.resourceName = v;
            return this;
        }
        
        public AlterConfigsResource setConfigs(AlterableConfigCollection v) {
            this.configs = v;
            return this;
        }
        
        @Override
        public void setNext(int v) {
            this.next = v;
        }
        
        @Override
        public void setPrev(int v) {
            this.prev = v;
        }
    }
    
    static public class AlterableConfig implements Message, ImplicitLinkedHashMultiCollection.Element {
        private String name;
        private String value;
        private List<RawTaggedField> _unknownTaggedFields;
        private int next;
        private int prev;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("name", Type.STRING, "The configuration key name."),
                new Field("value", Type.NULLABLE_STRING, "The value to set for the configuration key.")
            );
        
        public static final Schema SCHEMA_1 = SCHEMA_0;
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0,
            SCHEMA_1
        };
        
        public AlterableConfig(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public AlterableConfig(Struct struct, short _version) {
            fromStruct(struct, _version);
        }
        
        public AlterableConfig() {
            this.name = "";
            this.value = "";
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 0;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 1;
        }
        
        @Override
        public void read(Readable _readable, short _version) {
            if (_version > 1) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of AlterableConfig");
            }
            {
                int length;
                length = _readable.readShort();
                if (length < 0) {
                    throw new RuntimeException("non-nullable field name was serialized as null");
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field name had invalid length " + length);
                } else {
                    this.name = _readable.readString(length);
                }
            }
            {
                int length;
                length = _readable.readShort();
                if (length < 0) {
                    this.value = null;
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field value had invalid length " + length);
                } else {
                    this.value = _readable.readString(length);
                }
            }
            this._unknownTaggedFields = null;
        }
        
        @Override
        public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
            if (_version > 1) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of AlterableConfig");
            }
            int _numTaggedFields = 0;
            {
                byte[] _stringBytes = _cache.getSerializedValue(name);
                _writable.writeShort((short) _stringBytes.length);
                _writable.writeByteArray(_stringBytes);
            }
            if (value == null) {
                _writable.writeShort((short) -1);
            } else {
                byte[] _stringBytes = _cache.getSerializedValue(value);
                _writable.writeShort((short) _stringBytes.length);
                _writable.writeByteArray(_stringBytes);
            }
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            if (_numTaggedFields > 0) {
                throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
            }
        }
        
        @SuppressWarnings("unchecked")
        @Override
        public void fromStruct(Struct struct, short _version) {
            if (_version > 1) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of AlterableConfig");
            }
            this._unknownTaggedFields = null;
            this.name = struct.getString("name");
            this.value = struct.getString("value");
        }
        
        @Override
        public Struct toStruct(short _version) {
            if (_version > 1) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of AlterableConfig");
            }
            TreeMap<Integer, Object> _taggedFields = null;
            Struct struct = new Struct(SCHEMAS[_version]);
            struct.set("name", this.name);
            struct.set("value", this.value);
            return struct;
        }
        
        @Override
        public int size(ObjectSerializationCache _cache, short _version) {
            int _size = 0, _numTaggedFields = 0;
            if (_version > 1) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of AlterableConfig");
            }
            {
                byte[] _stringBytes = name.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'name' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(name, _stringBytes);
                _size += _stringBytes.length + 2;
            }
            if (value == null) {
                _size += 2;
            } else {
                byte[] _stringBytes = value.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'value' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(value, _stringBytes);
                _size += _stringBytes.length + 2;
            }
            if (_unknownTaggedFields != null) {
                _numTaggedFields += _unknownTaggedFields.size();
                for (RawTaggedField _field : _unknownTaggedFields) {
                    _size += ByteUtils.sizeOfUnsignedVarint(_field.tag());
                    _size += ByteUtils.sizeOfUnsignedVarint(_field.size());
                    _size += _field.size();
                }
            }
            if (_numTaggedFields > 0) {
                throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
            }
            return _size;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof AlterableConfig)) return false;
            AlterableConfig other = (AlterableConfig) obj;
            if (this.name == null) {
                if (other.name != null) return false;
            } else {
                if (!this.name.equals(other.name)) return false;
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + (name == null ? 0 : name.hashCode());
            return hashCode;
        }
        
        @Override
        public String toString() {
            return "AlterableConfig("
                + "name=" + ((name == null) ? "null" : "'" + name.toString() + "'")
                + ", value=" + ((value == null) ? "null" : "'" + value.toString() + "'")
                + ")";
        }
        
        public String name() {
            return this.name;
        }
        
        public String value() {
            return this.value;
        }
        
        @Override
        public int next() {
            return this.next;
        }
        
        @Override
        public int prev() {
            return this.prev;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public AlterableConfig setName(String v) {
            this.name = v;
            return this;
        }
        
        public AlterableConfig setValue(String v) {
            this.value = v;
            return this;
        }
        
        @Override
        public void setNext(int v) {
            this.next = v;
        }
        
        @Override
        public void setPrev(int v) {
            this.prev = v;
        }
    }
    
    public static class AlterableConfigCollection extends ImplicitLinkedHashMultiCollection<AlterableConfig> {
        public AlterableConfigCollection() {
            super();
        }
        
        public AlterableConfigCollection(int expectedNumElements) {
            super(expectedNumElements);
        }
        
        public AlterableConfigCollection(Iterator<AlterableConfig> iterator) {
            super(iterator);
        }
        
        public AlterableConfig find(String name) {
            AlterableConfig key = new AlterableConfig();
            key.setName(name);
            return find(key);
        }
        
        public List<AlterableConfig> findAll(String name) {
            AlterableConfig key = new AlterableConfig();
            key.setName(name);
            return findAll(key);
        }
        
    }
    
    public static class AlterConfigsResourceCollection extends ImplicitLinkedHashMultiCollection<AlterConfigsResource> {
        public AlterConfigsResourceCollection() {
            super();
        }
        
        public AlterConfigsResourceCollection(int expectedNumElements) {
            super(expectedNumElements);
        }
        
        public AlterConfigsResourceCollection(Iterator<AlterConfigsResource> iterator) {
            super(iterator);
        }
        
        public AlterConfigsResource find(byte resourceType, String resourceName) {
            AlterConfigsResource key = new AlterConfigsResource();
            key.setResourceType(resourceType);
            key.setResourceName(resourceName);
            return find(key);
        }
        
        public List<AlterConfigsResource> findAll(byte resourceType, String resourceName) {
            AlterConfigsResource key = new AlterConfigsResource();
            key.setResourceType(resourceType);
            key.setResourceName(resourceName);
            return findAll(key);
        }
        
    }
}
