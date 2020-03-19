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

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.UUID;
import org.apache.kafka.common.errors.UnsupportedVersionException;
import org.apache.kafka.common.protocol.ApiMessage;
import org.apache.kafka.common.protocol.MessageUtil;
import org.apache.kafka.common.protocol.ObjectSerializationCache;
import org.apache.kafka.common.protocol.Readable;
import org.apache.kafka.common.protocol.Writable;
import org.apache.kafka.common.protocol.types.CompactArrayOf;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.protocol.types.RawTaggedField;
import org.apache.kafka.common.protocol.types.RawTaggedFieldWriter;
import org.apache.kafka.common.protocol.types.Schema;
import org.apache.kafka.common.protocol.types.Struct;
import org.apache.kafka.common.protocol.types.Type;
import org.apache.kafka.common.utils.ByteUtils;

import static java.util.Map.Entry;
import static org.apache.kafka.common.protocol.types.Field.TaggedFieldsSection;


public class SimpleExampleMessageData implements ApiMessage {
    private UUID processId;
    private List<Integer> myTaggedIntArray;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
        );
    
    public static final Schema SCHEMA_1 =
        new Schema(
            new Field("process_id", Type.UUID, ""),
            TaggedFieldsSection.of(
                0, new Field("my_tagged_int_array", new CompactArrayOf(Type.INT32), "")
            )
        );
    
    public static final Schema[] SCHEMAS = new Schema[] {
        SCHEMA_0,
        SCHEMA_1
    };
    
    public SimpleExampleMessageData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public SimpleExampleMessageData(Struct struct, short _version) {
        fromStruct(struct, _version);
    }
    
    public SimpleExampleMessageData() {
        this.processId = MessageUtil.ZERO_UUID;
        this.myTaggedIntArray = new ArrayList<Integer>();
    }
    
    @Override
    public short apiKey() {
        return -1;
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
        if (_version >= 1) {
            this.processId = _readable.readUUID();
        } else {
            this.processId = MessageUtil.ZERO_UUID;
        }
        {
            this.myTaggedIntArray = new ArrayList<Integer>();
        }
        this._unknownTaggedFields = null;
        if (_version >= 1) {
            int _numTaggedFields = _readable.readUnsignedVarint();
            for (int _i = 0; _i < _numTaggedFields; _i++) {
                int _tag = _readable.readUnsignedVarint();
                int _size = _readable.readUnsignedVarint();
                switch (_tag) {
                    case 0: {
                        int arrayLength;
                        arrayLength = _readable.readUnsignedVarint() - 1;
                        if (arrayLength < 0) {
                            throw new RuntimeException("non-nullable field myTaggedIntArray was serialized as null");
                        } else {
                            ArrayList<Integer> newCollection = new ArrayList<Integer>(arrayLength);
                            for (int i = 0; i < arrayLength; i++) {
                                newCollection.add(_readable.readInt());
                            }
                            this.myTaggedIntArray = newCollection;
                        }
                        break;
                    }
                    default:
                        this._unknownTaggedFields = _readable.readUnknownTaggedField(this._unknownTaggedFields, _tag, _size);
                        break;
                }
            }
        }
    }
    
    @Override
    public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
        int _numTaggedFields = 0;
        if (_version >= 1) {
            _writable.writeUUID(processId);
        } else {
            if (processId != MessageUtil.ZERO_UUID) {
                throw new UnsupportedVersionException("Attempted to write a non-default processId at version " + _version);
            }
        }
        if (_version >= 1) {
            if (!myTaggedIntArray.isEmpty()) {
                _numTaggedFields++;
            }
        } else {
            if (!myTaggedIntArray.isEmpty()) {
                throw new UnsupportedVersionException("Attempted to write a non-default myTaggedIntArray at version " + _version);
            }
        }
        RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
        _numTaggedFields += _rawWriter.numFields();
        if (_version >= 1) {
            _writable.writeUnsignedVarint(_numTaggedFields);
            if (!myTaggedIntArray.isEmpty()) {
                _writable.writeUnsignedVarint(0);
                _writable.writeUnsignedVarint(_cache.getArraySizeInBytes(this.myTaggedIntArray) + 1);
                _writable.writeUnsignedVarint(myTaggedIntArray.size() + 1);
                for (Integer myTaggedIntArrayElement : myTaggedIntArray) {
                    _writable.writeInt(myTaggedIntArrayElement);
                }
            }
            _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
        } else {
            if (_numTaggedFields > 0) {
                throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void fromStruct(Struct struct, short _version) {
        NavigableMap<Integer, Object> _taggedFields = null;
        this._unknownTaggedFields = null;
        if (_version >= 1) {
            _taggedFields = (NavigableMap<Integer, Object>) struct.get("_tagged_fields");
        }
        if (_version >= 1) {
            this.processId = struct.getUUID("process_id");
        } else {
            this.processId = MessageUtil.ZERO_UUID;
        }
        if (_version >= 1) {
            if (_taggedFields.containsKey(0)) {
                Object[] _nestedObjects = (Object[]) _taggedFields.remove(0);
                this.myTaggedIntArray = new ArrayList<Integer>(_nestedObjects.length);
                for (Object nestedObject : _nestedObjects) {
                    this.myTaggedIntArray.add((Integer) nestedObject);
                }
            } else {
                this.myTaggedIntArray = new ArrayList<Integer>();
            }
        } else {
            this.myTaggedIntArray = new ArrayList<Integer>();
        }
        if (_version >= 1) {
            if (!_taggedFields.isEmpty()) {
                this._unknownTaggedFields = new ArrayList<>(_taggedFields.size());
                for (Entry<Integer, Object> entry : _taggedFields.entrySet()) {
                    this._unknownTaggedFields.add((RawTaggedField) entry.getValue());
                }
            }
        }
    }
    
    @Override
    public Struct toStruct(short _version) {
        TreeMap<Integer, Object> _taggedFields = null;
        if (_version >= 1) {
            _taggedFields = new TreeMap<>();
        }
        Struct struct = new Struct(SCHEMAS[_version]);
        if (_version >= 1) {
            struct.set("process_id", this.processId);
        }
        if (_version >= 1) {
            if (!myTaggedIntArray.isEmpty()) {
                Integer[] _nestedObjects = new Integer[myTaggedIntArray.size()];
                int i = 0;
                for (Integer element : this.myTaggedIntArray) {
                    _nestedObjects[i++] = element;
                }
                _taggedFields.put(0, _nestedObjects);
            }
        }
        if (_version >= 1) {
            struct.set("_tagged_fields", _taggedFields);
        }
        return struct;
    }
    
    @Override
    public int size(ObjectSerializationCache _cache, short _version) {
        int _size = 0, _numTaggedFields = 0;
        if (_version >= 1) {
            _size += 16;
        }
        if (_version >= 1) {
            if (!myTaggedIntArray.isEmpty()) {
                _numTaggedFields++;
                _size += 1;
                {
                    int _arraySize = 0;
                    _arraySize += ByteUtils.sizeOfUnsignedVarint(myTaggedIntArray.size() + 1);
                    _arraySize += myTaggedIntArray.size() * 4;
                    _cache.setArraySizeInBytes(myTaggedIntArray, _arraySize);
                    _size += _arraySize + ByteUtils.sizeOfUnsignedVarint(_arraySize);
                }
            }
        }
        if (_unknownTaggedFields != null) {
            _numTaggedFields += _unknownTaggedFields.size();
            for (RawTaggedField _field : _unknownTaggedFields) {
                _size += ByteUtils.sizeOfUnsignedVarint(_field.tag());
                _size += ByteUtils.sizeOfUnsignedVarint(_field.size());
                _size += _field.size();
            }
        }
        if (_version >= 1) {
            _size += ByteUtils.sizeOfUnsignedVarint(_numTaggedFields);
        } else {
            if (_numTaggedFields > 0) {
                throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
            }
        }
        return _size;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SimpleExampleMessageData)) return false;
        SimpleExampleMessageData other = (SimpleExampleMessageData) obj;
        if (!this.processId.equals(other.processId)) return false;
        if (this.myTaggedIntArray == null) {
            if (other.myTaggedIntArray != null) return false;
        } else {
            if (!this.myTaggedIntArray.equals(other.myTaggedIntArray)) return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + processId.hashCode();
        hashCode = 31 * hashCode + (myTaggedIntArray == null ? 0 : myTaggedIntArray.hashCode());
        return hashCode;
    }
    
    @Override
    public String toString() {
        return "SimpleExampleMessageData("
            + "processId=" + processId.toString()
            + ", myTaggedIntArray=" + MessageUtil.deepToString(myTaggedIntArray.iterator())
            + ")";
    }
    
    public UUID processId() {
        return this.processId;
    }
    
    public List<Integer> myTaggedIntArray() {
        return this.myTaggedIntArray;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public SimpleExampleMessageData setProcessId(UUID v) {
        this.processId = v;
        return this;
    }
    
    public SimpleExampleMessageData setMyTaggedIntArray(List<Integer> v) {
        this.myTaggedIntArray = v;
        return this;
    }
}
