/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package crdhn.queue.data;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TAgentChangelogFileInfo implements org.apache.thrift.TBase<TAgentChangelogFileInfo, TAgentChangelogFileInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TAgentChangelogFileInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TAgentChangelogFileInfo");

  private static final org.apache.thrift.protocol.TField DB_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("dbId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField LAST_VERSION_SYNC_FIELD_DESC = new org.apache.thrift.protocol.TField("lastVersionSync", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField LOG_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("logTime", org.apache.thrift.protocol.TType.I64, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TAgentChangelogFileInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TAgentChangelogFileInfoTupleSchemeFactory());
  }

  public int dbId; // required
  public long lastVersionSync; // required
  public long logTime; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DB_ID((short)1, "dbId"),
    LAST_VERSION_SYNC((short)2, "lastVersionSync"),
    LOG_TIME((short)3, "logTime");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // DB_ID
          return DB_ID;
        case 2: // LAST_VERSION_SYNC
          return LAST_VERSION_SYNC;
        case 3: // LOG_TIME
          return LOG_TIME;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __DBID_ISSET_ID = 0;
  private static final int __LASTVERSIONSYNC_ISSET_ID = 1;
  private static final int __LOGTIME_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DB_ID, new org.apache.thrift.meta_data.FieldMetaData("dbId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.LAST_VERSION_SYNC, new org.apache.thrift.meta_data.FieldMetaData("lastVersionSync", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LOG_TIME, new org.apache.thrift.meta_data.FieldMetaData("logTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TAgentChangelogFileInfo.class, metaDataMap);
  }

  public TAgentChangelogFileInfo() {
    this.dbId = -1;

    this.lastVersionSync = -1L;

    this.logTime = -1L;

  }

  public TAgentChangelogFileInfo(
    int dbId,
    long lastVersionSync,
    long logTime)
  {
    this();
    this.dbId = dbId;
    setDbIdIsSet(true);
    this.lastVersionSync = lastVersionSync;
    setLastVersionSyncIsSet(true);
    this.logTime = logTime;
    setLogTimeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TAgentChangelogFileInfo(TAgentChangelogFileInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.dbId = other.dbId;
    this.lastVersionSync = other.lastVersionSync;
    this.logTime = other.logTime;
  }

  public TAgentChangelogFileInfo deepCopy() {
    return new TAgentChangelogFileInfo(this);
  }

  @Override
  public void clear() {
    this.dbId = -1;

    this.lastVersionSync = -1L;

    this.logTime = -1L;

  }

  public int getDbId() {
    return this.dbId;
  }

  public TAgentChangelogFileInfo setDbId(int dbId) {
    this.dbId = dbId;
    setDbIdIsSet(true);
    return this;
  }

  public void unsetDbId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __DBID_ISSET_ID);
  }

  /** Returns true if field dbId is set (has been assigned a value) and false otherwise */
  public boolean isSetDbId() {
    return EncodingUtils.testBit(__isset_bitfield, __DBID_ISSET_ID);
  }

  public void setDbIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __DBID_ISSET_ID, value);
  }

  public long getLastVersionSync() {
    return this.lastVersionSync;
  }

  public TAgentChangelogFileInfo setLastVersionSync(long lastVersionSync) {
    this.lastVersionSync = lastVersionSync;
    setLastVersionSyncIsSet(true);
    return this;
  }

  public void unsetLastVersionSync() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LASTVERSIONSYNC_ISSET_ID);
  }

  /** Returns true if field lastVersionSync is set (has been assigned a value) and false otherwise */
  public boolean isSetLastVersionSync() {
    return EncodingUtils.testBit(__isset_bitfield, __LASTVERSIONSYNC_ISSET_ID);
  }

  public void setLastVersionSyncIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LASTVERSIONSYNC_ISSET_ID, value);
  }

  public long getLogTime() {
    return this.logTime;
  }

  public TAgentChangelogFileInfo setLogTime(long logTime) {
    this.logTime = logTime;
    setLogTimeIsSet(true);
    return this;
  }

  public void unsetLogTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LOGTIME_ISSET_ID);
  }

  /** Returns true if field logTime is set (has been assigned a value) and false otherwise */
  public boolean isSetLogTime() {
    return EncodingUtils.testBit(__isset_bitfield, __LOGTIME_ISSET_ID);
  }

  public void setLogTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LOGTIME_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DB_ID:
      if (value == null) {
        unsetDbId();
      } else {
        setDbId((Integer)value);
      }
      break;

    case LAST_VERSION_SYNC:
      if (value == null) {
        unsetLastVersionSync();
      } else {
        setLastVersionSync((Long)value);
      }
      break;

    case LOG_TIME:
      if (value == null) {
        unsetLogTime();
      } else {
        setLogTime((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DB_ID:
      return Integer.valueOf(getDbId());

    case LAST_VERSION_SYNC:
      return Long.valueOf(getLastVersionSync());

    case LOG_TIME:
      return Long.valueOf(getLogTime());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DB_ID:
      return isSetDbId();
    case LAST_VERSION_SYNC:
      return isSetLastVersionSync();
    case LOG_TIME:
      return isSetLogTime();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TAgentChangelogFileInfo)
      return this.equals((TAgentChangelogFileInfo)that);
    return false;
  }

  public boolean equals(TAgentChangelogFileInfo that) {
    if (that == null)
      return false;

    boolean this_present_dbId = true;
    boolean that_present_dbId = true;
    if (this_present_dbId || that_present_dbId) {
      if (!(this_present_dbId && that_present_dbId))
        return false;
      if (this.dbId != that.dbId)
        return false;
    }

    boolean this_present_lastVersionSync = true;
    boolean that_present_lastVersionSync = true;
    if (this_present_lastVersionSync || that_present_lastVersionSync) {
      if (!(this_present_lastVersionSync && that_present_lastVersionSync))
        return false;
      if (this.lastVersionSync != that.lastVersionSync)
        return false;
    }

    boolean this_present_logTime = true;
    boolean that_present_logTime = true;
    if (this_present_logTime || that_present_logTime) {
      if (!(this_present_logTime && that_present_logTime))
        return false;
      if (this.logTime != that.logTime)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TAgentChangelogFileInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetDbId()).compareTo(other.isSetDbId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDbId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dbId, other.dbId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLastVersionSync()).compareTo(other.isSetLastVersionSync());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLastVersionSync()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lastVersionSync, other.lastVersionSync);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLogTime()).compareTo(other.isSetLogTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLogTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.logTime, other.logTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TAgentChangelogFileInfo(");
    boolean first = true;

    sb.append("dbId:");
    sb.append(this.dbId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("lastVersionSync:");
    sb.append(this.lastVersionSync);
    first = false;
    if (!first) sb.append(", ");
    sb.append("logTime:");
    sb.append(this.logTime);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TAgentChangelogFileInfoStandardSchemeFactory implements SchemeFactory {
    public TAgentChangelogFileInfoStandardScheme getScheme() {
      return new TAgentChangelogFileInfoStandardScheme();
    }
  }

  private static class TAgentChangelogFileInfoStandardScheme extends StandardScheme<TAgentChangelogFileInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TAgentChangelogFileInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DB_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.dbId = iprot.readI32();
              struct.setDbIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LAST_VERSION_SYNC
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.lastVersionSync = iprot.readI64();
              struct.setLastVersionSyncIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // LOG_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.logTime = iprot.readI64();
              struct.setLogTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TAgentChangelogFileInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(DB_ID_FIELD_DESC);
      oprot.writeI32(struct.dbId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LAST_VERSION_SYNC_FIELD_DESC);
      oprot.writeI64(struct.lastVersionSync);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LOG_TIME_FIELD_DESC);
      oprot.writeI64(struct.logTime);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TAgentChangelogFileInfoTupleSchemeFactory implements SchemeFactory {
    public TAgentChangelogFileInfoTupleScheme getScheme() {
      return new TAgentChangelogFileInfoTupleScheme();
    }
  }

  private static class TAgentChangelogFileInfoTupleScheme extends TupleScheme<TAgentChangelogFileInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TAgentChangelogFileInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetDbId()) {
        optionals.set(0);
      }
      if (struct.isSetLastVersionSync()) {
        optionals.set(1);
      }
      if (struct.isSetLogTime()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetDbId()) {
        oprot.writeI32(struct.dbId);
      }
      if (struct.isSetLastVersionSync()) {
        oprot.writeI64(struct.lastVersionSync);
      }
      if (struct.isSetLogTime()) {
        oprot.writeI64(struct.logTime);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TAgentChangelogFileInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.dbId = iprot.readI32();
        struct.setDbIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.lastVersionSync = iprot.readI64();
        struct.setLastVersionSyncIsSet(true);
      }
      if (incoming.get(2)) {
        struct.logTime = iprot.readI64();
        struct.setLogTimeIsSet(true);
      }
    }
  }

}

