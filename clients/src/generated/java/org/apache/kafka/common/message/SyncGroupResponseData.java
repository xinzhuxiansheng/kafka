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
import java.util.Arrays;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import org.apache.kafka.common.errors.UnsupportedVersionException;
import org.apache.kafka.common.protocol.ApiMessage;
import org.apache.kafka.common.protocol.ObjectSerializationCache;
import org.apache.kafka.common.protocol.Readable;
import org.apache.kafka.common.protocol.Writable;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.protocol.types.RawTaggedField;
import org.apache.kafka.common.protocol.types.RawTaggedFieldWriter;
import org.apache.kafka.common.protocol.types.Schema;
import org.apache.kafka.common.protocol.types.Struct;
import org.apache.kafka.common.protocol.types.Type;
import org.apache.kafka.common.utils.ByteUtils;
import org.apache.kafka.common.utils.Bytes;

import static java.util.Map.Entry;
import static org.apache.kafka.common.protocol.types.Field.TaggedFieldsSection;


public class SyncGroupResponseData implements ApiMessage {
    private int throttleTimeMs;
    private short errorCode;
    private byte[] assignment;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
            new Field("error_code", Type.INT16, "The error code, or 0 if there was no error."),
            new Field("assignment", Type.BYTES, "The member assignment.")
        );
    
    public static final Schema SCHEMA_1 =
        new Schema(
            new Field("throttle_time_ms", Type.INT32, "The duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota."),
            new Field("error_code", Type.INT16, "The error code, or 0 if there was no error."),
            new Field("assignment", Type.BYTES, "The member assignment.")
        );
    
    public static final Schema SCHEMA_2 = SCHEMA_1;
    
    public static final Schema SCHEMA_3 = SCHEMA_2;
    
    public static final Schema SCHEMA_4 =
        new Schema(
            new Field("throttle_time_ms", Type.INT32, "The duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota."),
            new Field("error_code", Type.INT16, "The error code, or 0 if there was no error."),
            new Field("assignment", Type.COMPACT_BYTES, "The member assignment."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema[] SCHEMAS = new Schema[] {
        SCHEMA_0,
        SCHEMA_1,
        SCHEMA_2,
        SCHEMA_3,
        SCHEMA_4
    };
    
    public SyncGroupResponseData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public SyncGroupResponseData(Struct struct, short _version) {
        fromStruct(struct, _version);
    }
    
    public SyncGroupResponseData() {
        this.throttleTimeMs = 0;
        this.errorCode = (short) 0;
        this.assignment = Bytes.EMPTY;
    }
    
    @Override
    public short apiKey() {
        return 14;
    }
    
    @Override
    public short lowestSupportedVersion() {
        return 0;
    }
    
    @Override
    public short highestSupportedVersion() {
        return 4;
    }
    
    @Override
    public void read(Readable _readable, short _version) {
        if (_version >= 1) {
            this.throttleTimeMs = _readable.readInt();
        } else {
            this.throttleTimeMs = 0;
        }
        this.errorCode = _readable.readShort();
        {
            int length;
            if (_version >= 4) {
                length = _readable.readUnsignedVarint() - 1;
            } else {
                length = _readable.readInt();
            }
            if (length < 0) {
                throw new RuntimeException("non-nullable field assignment was serialized as null");
            } else {
                byte[] newBytes = new byte[length];
                _readable.readArray(newBytes);
                this.assignment = newBytes;
            }
        }
        this._unknownTaggedFields = null;
        if (_version >= 4) {
            int _numTaggedFields = _readable.readUnsignedVarint();
            for (int _i = 0; _i < _numTaggedFields; _i++) {
                int _tag = _readable.readUnsignedVarint();
                int _size = _readable.readUnsignedVarint();
                switch (_tag) {
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
            _writable.writeInt(throttleTimeMs);
        }
        _writable.writeShort(errorCode);
        if (_version >= 4) {
            _writable.writeUnsignedVarint(assignment.length + 1);
        } else {
            _writable.writeInt(assignment.length);
        }
        _writable.writeByteArray(assignment);
        RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
        _numTaggedFields += _rawWriter.numFields();
        if (_version >= 4) {
            _writable.writeUnsignedVarint(_numTaggedFields);
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
        if (_version >= 4) {
            _taggedFields = (NavigableMap<Integer, Object>) struct.get("_tagged_fields");
        }
        if (_version >= 1) {
            this.throttleTimeMs = struct.getInt("throttle_time_ms");
        } else {
            this.throttleTimeMs = 0;
        }
        this.errorCode = struct.getShort("error_code");
        this.assignment = struct.getByteArray("assignment");
        if (_version >= 4) {
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
        if (_version >= 4) {
            _taggedFields = new TreeMap<>();
        }
        Struct struct = new Struct(SCHEMAS[_version]);
        if (_version >= 1) {
            struct.set("throttle_time_ms", this.throttleTimeMs);
        }
        struct.set("error_code", this.errorCode);
        struct.setByteArray("assignment", this.assignment);
        if (_version >= 4) {
            struct.set("_tagged_fields", _taggedFields);
        }
        return struct;
    }
    
    @Override
    public int size(ObjectSerializationCache _cache, short _version) {
        int _size = 0, _numTaggedFields = 0;
        if (_version >= 1) {
            _size += 4;
        }
        _size += 2;
        {
            int _bytesSize = assignment.length;
            if (_version >= 4) {
                _bytesSize += ByteUtils.sizeOfUnsignedVarint(assignment.length + 1);
            } else {
                _bytesSize += 4;
            }
            _size += _bytesSize;
        }
        if (_unknownTaggedFields != null) {
            _numTaggedFields += _unknownTaggedFields.size();
            for (RawTaggedField _field : _unknownTaggedFields) {
                _size += ByteUtils.sizeOfUnsignedVarint(_field.tag());
                _size += ByteUtils.sizeOfUnsignedVarint(_field.size());
                _size += _field.size();
            }
        }
        if (_version >= 4) {
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
        if (!(obj instanceof SyncGroupResponseData)) return false;
        SyncGroupResponseData other = (SyncGroupResponseData) obj;
        if (throttleTimeMs != other.throttleTimeMs) return false;
        if (errorCode != other.errorCode) return false;
        if (!Arrays.equals(this.assignment, other.assignment)) return false;
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + throttleTimeMs;
        hashCode = 31 * hashCode + errorCode;
        hashCode = 31 * hashCode + Arrays.hashCode(assignment);
        return hashCode;
    }
    
    @Override
    public String toString() {
        return "SyncGroupResponseData("
            + "throttleTimeMs=" + throttleTimeMs
            + ", errorCode=" + errorCode
            + ", assignment=" + Arrays.toString(assignment)
            + ")";
    }
    
    public int throttleTimeMs() {
        return this.throttleTimeMs;
    }
    
    public short errorCode() {
        return this.errorCode;
    }
    
    public byte[] assignment() {
        return this.assignment;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public SyncGroupResponseData setThrottleTimeMs(int v) {
        this.throttleTimeMs = v;
        return this;
    }
    
    public SyncGroupResponseData setErrorCode(short v) {
        this.errorCode = v;
        return this;
    }
    
    public SyncGroupResponseData setAssignment(byte[] v) {
        this.assignment = v;
        return this;
    }
}
