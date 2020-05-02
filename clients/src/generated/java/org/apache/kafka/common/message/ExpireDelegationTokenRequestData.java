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


public class ExpireDelegationTokenRequestData implements ApiMessage {
    private byte[] hmac;
    private long expiryTimePeriodMs;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
            new Field("hmac", Type.BYTES, "The HMAC of the delegation token to be expired."),
            new Field("expiry_time_period_ms", Type.INT64, "The expiry time period in milliseconds.")
        );
    
    public static final Schema SCHEMA_1 = SCHEMA_0;
    
    public static final Schema[] SCHEMAS = new Schema[] {
        SCHEMA_0,
        SCHEMA_1
    };
    
    public ExpireDelegationTokenRequestData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public ExpireDelegationTokenRequestData(Struct struct, short _version) {
        fromStruct(struct, _version);
    }
    
    public ExpireDelegationTokenRequestData() {
        this.hmac = Bytes.EMPTY;
        this.expiryTimePeriodMs = 0L;
    }
    
    @Override
    public short apiKey() {
        return 40;
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
            int length;
            length = _readable.readInt();
            if (length < 0) {
                throw new RuntimeException("non-nullable field hmac was serialized as null");
            } else {
                byte[] newBytes = new byte[length];
                _readable.readArray(newBytes);
                this.hmac = newBytes;
            }
        }
        this.expiryTimePeriodMs = _readable.readLong();
        this._unknownTaggedFields = null;
    }
    
    @Override
    public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
        int _numTaggedFields = 0;
        _writable.writeInt(hmac.length);
        _writable.writeByteArray(hmac);
        _writable.writeLong(expiryTimePeriodMs);
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
        this.hmac = struct.getByteArray("hmac");
        this.expiryTimePeriodMs = struct.getLong("expiry_time_period_ms");
    }
    
    @Override
    public Struct toStruct(short _version) {
        TreeMap<Integer, Object> _taggedFields = null;
        Struct struct = new Struct(SCHEMAS[_version]);
        struct.setByteArray("hmac", this.hmac);
        struct.set("expiry_time_period_ms", this.expiryTimePeriodMs);
        return struct;
    }
    
    @Override
    public int size(ObjectSerializationCache _cache, short _version) {
        int _size = 0, _numTaggedFields = 0;
        {
            int _bytesSize = hmac.length;
            _bytesSize += 4;
            _size += _bytesSize;
        }
        _size += 8;
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
        if (!(obj instanceof ExpireDelegationTokenRequestData)) return false;
        ExpireDelegationTokenRequestData other = (ExpireDelegationTokenRequestData) obj;
        if (!Arrays.equals(this.hmac, other.hmac)) return false;
        if (expiryTimePeriodMs != other.expiryTimePeriodMs) return false;
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + Arrays.hashCode(hmac);
        hashCode = 31 * hashCode + ((int) (expiryTimePeriodMs >> 32) ^ (int) expiryTimePeriodMs);
        return hashCode;
    }
    
    @Override
    public String toString() {
        return "ExpireDelegationTokenRequestData("
            + "hmac=" + Arrays.toString(hmac)
            + ", expiryTimePeriodMs=" + expiryTimePeriodMs
            + ")";
    }
    
    public byte[] hmac() {
        return this.hmac;
    }
    
    public long expiryTimePeriodMs() {
        return this.expiryTimePeriodMs;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public ExpireDelegationTokenRequestData setHmac(byte[] v) {
        this.hmac = v;
        return this;
    }
    
    public ExpireDelegationTokenRequestData setExpiryTimePeriodMs(long v) {
        this.expiryTimePeriodMs = v;
        return this;
    }
}
