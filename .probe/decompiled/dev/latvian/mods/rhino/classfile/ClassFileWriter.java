package dev.latvian.mods.rhino.classfile;

import dev.latvian.mods.rhino.ObjArray;
import dev.latvian.mods.rhino.UintMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class ClassFileWriter {

    public static final short ACC_PUBLIC = 1;

    public static final short ACC_PRIVATE = 2;

    public static final short ACC_PROTECTED = 4;

    public static final short ACC_STATIC = 8;

    public static final short ACC_FINAL = 16;

    public static final short ACC_SUPER = 32;

    public static final short ACC_SYNCHRONIZED = 32;

    public static final short ACC_VOLATILE = 64;

    public static final short ACC_TRANSIENT = 128;

    public static final short ACC_NATIVE = 256;

    public static final short ACC_ABSTRACT = 1024;

    private static final int SuperBlockStartsSize = 4;

    private static final int LineNumberTableSize = 16;

    private static final int ExceptionTableSize = 4;

    private static final int MajorVersion;

    private static final int MinorVersion;

    private static final boolean GenerateStackMap;

    private static final int FileHeaderConstant = -889275714;

    private static final boolean DEBUGSTACK = false;

    private static final boolean DEBUGLABELS = false;

    private static final boolean DEBUGCODE = false;

    private static final int MIN_LABEL_TABLE_SIZE = 32;

    private static final int MIN_FIXUP_TABLE_SIZE = 40;

    private final String generatedClassName;

    private final ConstantPool itsConstantPool;

    private final ObjArray itsMethods = new ObjArray();

    private final ObjArray itsFields = new ObjArray();

    private final ObjArray itsInterfaces = new ObjArray();

    private final short itsThisClassIndex;

    private final short itsSuperClassIndex;

    private int[] itsSuperBlockStarts = null;

    private int itsSuperBlockStartsTop = 0;

    private UintMap itsJumpFroms = null;

    private ExceptionTableEntry[] itsExceptionTable;

    private int itsExceptionTableTop;

    private int[] itsLineNumberTable;

    private int itsLineNumberTableTop;

    private byte[] itsCodeBuffer = new byte[256];

    private int itsCodeBufferTop;

    private ClassFileMethod itsCurrentMethod;

    private short itsStackTop;

    private short itsMaxStack;

    private short itsMaxLocals;

    private short itsFlags;

    private short itsSourceFileNameIndex;

    private int[] itsLabelTable;

    private int itsLabelTableTop;

    private long[] itsFixupTable;

    private int itsFixupTableTop;

    private ObjArray itsVarDescriptors;

    private ObjArray itsBootstrapMethods;

    private int itsBootstrapMethodsLength = 0;

    private char[] tmpCharBuffer = new char[64];

    static String getSlashedForm(String name) {
        return name.replace('.', '/');
    }

    public static String classNameToSignature(String name) {
        int nameLength = name.length();
        int colonPos = 1 + nameLength;
        char[] buf = new char[colonPos + 1];
        buf[0] = 'L';
        buf[colonPos] = ';';
        name.getChars(0, nameLength, buf, 1);
        for (int i = 1; i != colonPos; i++) {
            if (buf[i] == '.') {
                buf[i] = '/';
            }
        }
        return new String(buf, 0, colonPos + 1);
    }

    private static char arrayTypeToName(int type) {
        return switch(type) {
            case 4 ->
                'Z';
            case 5 ->
                'C';
            case 6 ->
                'F';
            case 7 ->
                'D';
            case 8 ->
                'B';
            case 9 ->
                'S';
            case 10 ->
                'I';
            case 11 ->
                'J';
            default ->
                throw new IllegalArgumentException("bad operand");
        };
    }

    private static String classDescriptorToInternalName(String descriptor) {
        return descriptor.substring(1, descriptor.length() - 1);
    }

    private static String descriptorToInternalName(String descriptor) {
        return switch(descriptor.charAt(0)) {
            case 'B', 'C', 'D', 'F', 'I', 'J', 'S', 'V', 'Z', '[' ->
                descriptor;
            default ->
                throw new IllegalArgumentException("bad descriptor:" + descriptor);
            case 'L' ->
                classDescriptorToInternalName(descriptor);
        };
    }

    static int putInt64(long value, byte[] array, int offset) {
        offset = putInt32((int) (value >>> 32), array, offset);
        return putInt32((int) value, array, offset);
    }

    private static void badStack(int value) {
        String s;
        if (value < 0) {
            s = "Stack underflow: " + value;
        } else {
            s = "Too big stack: " + value;
        }
        throw new IllegalStateException(s);
    }

    private static int sizeOfParameters(String pString) {
        int length = pString.length();
        int rightParenthesis = pString.lastIndexOf(41);
        if (3 <= length && pString.charAt(0) == '(' && 1 <= rightParenthesis && rightParenthesis + 1 < length) {
            boolean ok = true;
            int index = 1;
            int stackDiff = 0;
            int count = 0;
            label53: while (index != rightParenthesis) {
                switch(pString.charAt(index)) {
                    case 'D':
                    case 'J':
                        stackDiff--;
                    case 'B':
                    case 'C':
                    case 'F':
                    case 'I':
                    case 'S':
                    case 'Z':
                        stackDiff--;
                        count++;
                        index++;
                        break;
                    case 'E':
                    case 'G':
                    case 'H':
                    case 'K':
                    case 'M':
                    case 'N':
                    case 'O':
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'T':
                    case 'U':
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    default:
                        ok = false;
                        break label53;
                    case '[':
                        int c = pString.charAt(++index);
                        while (c == 91) {
                            c = pString.charAt(++index);
                        }
                        switch(c) {
                            case 66:
                            case 67:
                            case 68:
                            case 70:
                            case 73:
                            case 74:
                            case 83:
                            case 90:
                                stackDiff--;
                                count++;
                                index++;
                                continue;
                            case 69:
                            case 71:
                            case 72:
                            case 75:
                            case 77:
                            case 78:
                            case 79:
                            case 80:
                            case 81:
                            case 82:
                            case 84:
                            case 85:
                            case 86:
                            case 87:
                            case 88:
                            case 89:
                            default:
                                ok = false;
                                break label53;
                            case 76:
                        }
                    case 'L':
                        stackDiff--;
                        count++;
                        int semicolon = pString.indexOf(59, ++index);
                        if (index + 1 > semicolon || semicolon >= rightParenthesis) {
                            ok = false;
                            break label53;
                        }
                        index = semicolon + 1;
                }
            }
            if (ok) {
                switch(pString.charAt(rightParenthesis + 1)) {
                    case 'D':
                    case 'J':
                        stackDiff++;
                    case 'B':
                    case 'C':
                    case 'F':
                    case 'I':
                    case 'L':
                    case 'S':
                    case 'Z':
                    case '[':
                        stackDiff++;
                        break;
                    case 'E':
                    case 'G':
                    case 'H':
                    case 'K':
                    case 'M':
                    case 'N':
                    case 'O':
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'T':
                    case 'U':
                    case 'W':
                    case 'X':
                    case 'Y':
                    default:
                        ok = false;
                    case 'V':
                }
                if (ok) {
                    return count << 16 | 65535 & stackDiff;
                }
            }
        }
        throw new IllegalArgumentException("Bad parameter signature: " + pString);
    }

    static int putInt16(int value, byte[] array, int offset) {
        array[offset] = (byte) (value >>> 8);
        array[offset + 1] = (byte) value;
        return offset + 2;
    }

    static int putInt32(int value, byte[] array, int offset) {
        array[offset] = (byte) (value >>> 24);
        array[offset + 1] = (byte) (value >>> 16);
        array[offset + 2] = (byte) (value >>> 8);
        array[offset + 3] = (byte) value;
        return offset + 4;
    }

    private static int opcodeLength(int opcode, boolean wide) {
        switch(opcode) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 190:
            case 191:
            case 194:
            case 195:
            case 196:
            case 202:
            case 254:
            case 255:
                return 1;
            case 16:
            case 18:
            case 188:
                return 2;
            case 17:
            case 19:
            case 20:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 187:
            case 189:
            case 192:
            case 193:
            case 198:
            case 199:
                return 3;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 169:
                return wide ? 3 : 2;
            case 132:
                return wide ? 5 : 3;
            case 170:
            case 171:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 217:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
            case 226:
            case 227:
            case 228:
            case 229:
            case 230:
            case 231:
            case 232:
            case 233:
            case 234:
            case 235:
            case 236:
            case 237:
            case 238:
            case 239:
            case 240:
            case 241:
            case 242:
            case 243:
            case 244:
            case 245:
            case 246:
            case 247:
            case 248:
            case 249:
            case 250:
            case 251:
            case 252:
            case 253:
            default:
                throw new IllegalArgumentException("Bad opcode: " + opcode);
            case 185:
            case 186:
            case 200:
            case 201:
                return 5;
            case 197:
                return 4;
        }
    }

    private static int opcodeCount(int opcode) {
        switch(opcode) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 190:
            case 191:
            case 194:
            case 195:
            case 196:
            case 202:
            case 254:
            case 255:
                return 0;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 187:
            case 188:
            case 189:
            case 192:
            case 193:
            case 198:
            case 199:
            case 200:
            case 201:
                return 1;
            case 132:
            case 197:
                return 2;
            case 170:
            case 171:
                return -1;
            case 186:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 217:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
            case 226:
            case 227:
            case 228:
            case 229:
            case 230:
            case 231:
            case 232:
            case 233:
            case 234:
            case 235:
            case 236:
            case 237:
            case 238:
            case 239:
            case 240:
            case 241:
            case 242:
            case 243:
            case 244:
            case 245:
            case 246:
            case 247:
            case 248:
            case 249:
            case 250:
            case 251:
            case 252:
            case 253:
            default:
                throw new IllegalArgumentException("Bad opcode: " + opcode);
        }
    }

    private static int stackChange(int opcode) {
        switch(opcode) {
            case 0:
            case 47:
            case 49:
            case 95:
            case 116:
            case 117:
            case 118:
            case 119:
            case 132:
            case 134:
            case 138:
            case 139:
            case 143:
            case 145:
            case 146:
            case 147:
            case 167:
            case 169:
            case 177:
            case 178:
            case 179:
            case 184:
            case 186:
            case 188:
            case 189:
            case 190:
            case 192:
            case 193:
            case 196:
            case 200:
            case 202:
            case 254:
            case 255:
                return 0;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 11:
            case 12:
            case 13:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 23:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 34:
            case 35:
            case 36:
            case 37:
            case 42:
            case 43:
            case 44:
            case 45:
            case 89:
            case 90:
            case 91:
            case 133:
            case 135:
            case 140:
            case 141:
            case 168:
            case 187:
            case 197:
            case 201:
                return 1;
            case 9:
            case 10:
            case 14:
            case 15:
            case 20:
            case 22:
            case 24:
            case 30:
            case 31:
            case 32:
            case 33:
            case 38:
            case 39:
            case 40:
            case 41:
            case 92:
            case 93:
            case 94:
                return 2;
            case 46:
            case 48:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 56:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 67:
            case 68:
            case 69:
            case 70:
            case 75:
            case 76:
            case 77:
            case 78:
            case 87:
            case 96:
            case 98:
            case 100:
            case 102:
            case 104:
            case 106:
            case 108:
            case 110:
            case 112:
            case 114:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 128:
            case 130:
            case 136:
            case 137:
            case 142:
            case 144:
            case 149:
            case 150:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 170:
            case 171:
            case 172:
            case 174:
            case 176:
            case 180:
            case 181:
            case 182:
            case 183:
            case 185:
            case 191:
            case 194:
            case 195:
            case 198:
            case 199:
                return -1;
            case 55:
            case 57:
            case 63:
            case 64:
            case 65:
            case 66:
            case 71:
            case 72:
            case 73:
            case 74:
            case 88:
            case 97:
            case 99:
            case 101:
            case 103:
            case 105:
            case 107:
            case 109:
            case 111:
            case 113:
            case 115:
            case 127:
            case 129:
            case 131:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 173:
            case 175:
                return -2;
            case 79:
            case 81:
            case 83:
            case 84:
            case 85:
            case 86:
            case 148:
            case 151:
            case 152:
                return -3;
            case 80:
            case 82:
                return -4;
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 217:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
            case 226:
            case 227:
            case 228:
            case 229:
            case 230:
            case 231:
            case 232:
            case 233:
            case 234:
            case 235:
            case 236:
            case 237:
            case 238:
            case 239:
            case 240:
            case 241:
            case 242:
            case 243:
            case 244:
            case 245:
            case 246:
            case 247:
            case 248:
            case 249:
            case 250:
            case 251:
            case 252:
            case 253:
            default:
                throw new IllegalArgumentException("Bad opcode: " + opcode);
        }
    }

    private static String bytecodeStr(int code) {
        return "";
    }

    public ClassFileWriter(String className, String superClassName, String sourceFileName) {
        this.generatedClassName = className;
        this.itsConstantPool = new ConstantPool(this);
        this.itsThisClassIndex = this.itsConstantPool.addClass(className);
        this.itsSuperClassIndex = this.itsConstantPool.addClass(superClassName);
        if (sourceFileName != null) {
            this.itsSourceFileNameIndex = this.itsConstantPool.addUtf8(sourceFileName);
        }
        this.itsFlags = 33;
    }

    public final String getClassName() {
        return this.generatedClassName;
    }

    public void addInterface(String interfaceName) {
        short interfaceIndex = this.itsConstantPool.addClass(interfaceName);
        this.itsInterfaces.add(interfaceIndex);
    }

    public void setFlags(short flags) {
        this.itsFlags = flags;
    }

    public void addField(String fieldName, String type, short flags) {
        short fieldNameIndex = this.itsConstantPool.addUtf8(fieldName);
        short typeIndex = this.itsConstantPool.addUtf8(type);
        this.itsFields.add(new ClassFileField(fieldNameIndex, typeIndex, flags));
    }

    public void addField(String fieldName, String type, short flags, int value) {
        short fieldNameIndex = this.itsConstantPool.addUtf8(fieldName);
        short typeIndex = this.itsConstantPool.addUtf8(type);
        ClassFileField field = new ClassFileField(fieldNameIndex, typeIndex, flags);
        field.setAttributes(this.itsConstantPool.addUtf8("ConstantValue"), (short) 0, (short) 0, this.itsConstantPool.addConstant(value));
        this.itsFields.add(field);
    }

    public void addField(String fieldName, String type, short flags, long value) {
        short fieldNameIndex = this.itsConstantPool.addUtf8(fieldName);
        short typeIndex = this.itsConstantPool.addUtf8(type);
        ClassFileField field = new ClassFileField(fieldNameIndex, typeIndex, flags);
        field.setAttributes(this.itsConstantPool.addUtf8("ConstantValue"), (short) 0, (short) 2, this.itsConstantPool.addConstant(value));
        this.itsFields.add(field);
    }

    public void addField(String fieldName, String type, short flags, double value) {
        short fieldNameIndex = this.itsConstantPool.addUtf8(fieldName);
        short typeIndex = this.itsConstantPool.addUtf8(type);
        ClassFileField field = new ClassFileField(fieldNameIndex, typeIndex, flags);
        field.setAttributes(this.itsConstantPool.addUtf8("ConstantValue"), (short) 0, (short) 2, this.itsConstantPool.addConstant(value));
        this.itsFields.add(field);
    }

    public void addVariableDescriptor(String name, String type, int startPC, int register) {
        int nameIndex = this.itsConstantPool.addUtf8(name);
        int descriptorIndex = this.itsConstantPool.addUtf8(type);
        int[] chunk = new int[] { nameIndex, descriptorIndex, startPC, register };
        if (this.itsVarDescriptors == null) {
            this.itsVarDescriptors = new ObjArray();
        }
        this.itsVarDescriptors.add(chunk);
    }

    public void startMethod(String methodName, String type, short flags) {
        short methodNameIndex = this.itsConstantPool.addUtf8(methodName);
        short typeIndex = this.itsConstantPool.addUtf8(type);
        this.itsCurrentMethod = new ClassFileMethod(methodName, methodNameIndex, type, typeIndex, flags);
        this.itsJumpFroms = new UintMap();
        this.itsMethods.add(this.itsCurrentMethod);
        this.addSuperBlockStart(0);
    }

    public void stopMethod(short maxLocals) {
        if (this.itsCurrentMethod == null) {
            throw new IllegalStateException("No method to stop");
        } else {
            this.fixLabelGotos();
            this.itsMaxLocals = maxLocals;
            ClassFileWriter.StackMapTable stackMap = null;
            if (GenerateStackMap) {
                this.finalizeSuperBlockStarts();
                stackMap = new ClassFileWriter.StackMapTable();
                stackMap.generate();
            }
            int lineNumberTableLength = 0;
            if (this.itsLineNumberTable != null) {
                lineNumberTableLength = 8 + this.itsLineNumberTableTop * 4;
            }
            int variableTableLength = 0;
            if (this.itsVarDescriptors != null) {
                variableTableLength = 8 + this.itsVarDescriptors.size() * 10;
            }
            int stackMapTableLength = 0;
            if (stackMap != null) {
                int stackMapWriteSize = stackMap.computeWriteSize();
                if (stackMapWriteSize > 0) {
                    stackMapTableLength = 6 + stackMapWriteSize;
                }
            }
            int attrLength = 14 + this.itsCodeBufferTop + 2 + this.itsExceptionTableTop * 8 + 2 + lineNumberTableLength + variableTableLength + stackMapTableLength;
            if (attrLength > 65536) {
                throw new ClassFileWriter.ClassFileFormatException("generated bytecode for method exceeds 64K limit.");
            } else {
                byte[] codeAttribute = new byte[attrLength];
                int index = 0;
                int codeAttrIndex = this.itsConstantPool.addUtf8("Code");
                index = putInt16(codeAttrIndex, codeAttribute, index);
                attrLength -= 6;
                index = putInt32(attrLength, codeAttribute, index);
                index = putInt16(this.itsMaxStack, codeAttribute, index);
                index = putInt16(this.itsMaxLocals, codeAttribute, index);
                index = putInt32(this.itsCodeBufferTop, codeAttribute, index);
                System.arraycopy(this.itsCodeBuffer, 0, codeAttribute, index, this.itsCodeBufferTop);
                index += this.itsCodeBufferTop;
                if (this.itsExceptionTableTop > 0) {
                    index = putInt16(this.itsExceptionTableTop, codeAttribute, index);
                    for (int i = 0; i < this.itsExceptionTableTop; i++) {
                        ExceptionTableEntry ete = this.itsExceptionTable[i];
                        int startPC = this.getLabelPC(ete.itsStartLabel);
                        int endPC = this.getLabelPC(ete.itsEndLabel);
                        int handlerPC = this.getLabelPC(ete.itsHandlerLabel);
                        short catchType = ete.itsCatchType;
                        if (startPC == -1) {
                            throw new IllegalStateException("start label not defined");
                        }
                        if (endPC == -1) {
                            throw new IllegalStateException("end label not defined");
                        }
                        if (handlerPC == -1) {
                            throw new IllegalStateException("handler label not defined");
                        }
                        index = putInt16(startPC, codeAttribute, index);
                        index = putInt16(endPC, codeAttribute, index);
                        index = putInt16(handlerPC, codeAttribute, index);
                        index = putInt16(catchType, codeAttribute, index);
                    }
                } else {
                    index = putInt16(0, codeAttribute, index);
                }
                int attributeCount = 0;
                if (this.itsLineNumberTable != null) {
                    attributeCount++;
                }
                if (this.itsVarDescriptors != null) {
                    attributeCount++;
                }
                if (stackMapTableLength > 0) {
                    attributeCount++;
                }
                index = putInt16(attributeCount, codeAttribute, index);
                if (this.itsLineNumberTable != null) {
                    int lineNumberTableAttrIndex = this.itsConstantPool.addUtf8("LineNumberTable");
                    index = putInt16(lineNumberTableAttrIndex, codeAttribute, index);
                    int tableAttrLength = 2 + this.itsLineNumberTableTop * 4;
                    index = putInt32(tableAttrLength, codeAttribute, index);
                    index = putInt16(this.itsLineNumberTableTop, codeAttribute, index);
                    for (int i = 0; i < this.itsLineNumberTableTop; i++) {
                        index = putInt32(this.itsLineNumberTable[i], codeAttribute, index);
                    }
                }
                if (this.itsVarDescriptors != null) {
                    int variableTableAttrIndex = this.itsConstantPool.addUtf8("LocalVariableTable");
                    index = putInt16(variableTableAttrIndex, codeAttribute, index);
                    int varCount = this.itsVarDescriptors.size();
                    int tableAttrLength = 2 + varCount * 10;
                    index = putInt32(tableAttrLength, codeAttribute, index);
                    index = putInt16(varCount, codeAttribute, index);
                    for (int i = 0; i < varCount; i++) {
                        int[] chunk = (int[]) this.itsVarDescriptors.get(i);
                        int nameIndex = chunk[0];
                        int descriptorIndex = chunk[1];
                        int startPCx = chunk[2];
                        int register = chunk[3];
                        int length = this.itsCodeBufferTop - startPCx;
                        index = putInt16(startPCx, codeAttribute, index);
                        index = putInt16(length, codeAttribute, index);
                        index = putInt16(nameIndex, codeAttribute, index);
                        index = putInt16(descriptorIndex, codeAttribute, index);
                        index = putInt16(register, codeAttribute, index);
                    }
                }
                if (stackMapTableLength > 0) {
                    int stackMapTableAttrIndex = this.itsConstantPool.addUtf8("StackMapTable");
                    index = putInt16(stackMapTableAttrIndex, codeAttribute, index);
                    index = stackMap.write(codeAttribute, index);
                }
                this.itsCurrentMethod.setCodeAttribute(codeAttribute);
                this.itsExceptionTable = null;
                this.itsExceptionTableTop = 0;
                this.itsLineNumberTableTop = 0;
                this.itsCodeBufferTop = 0;
                this.itsCurrentMethod = null;
                this.itsMaxStack = 0;
                this.itsStackTop = 0;
                this.itsLabelTableTop = 0;
                this.itsFixupTableTop = 0;
                this.itsVarDescriptors = null;
                this.itsSuperBlockStarts = null;
                this.itsSuperBlockStartsTop = 0;
                this.itsJumpFroms = null;
            }
        }
    }

    public void add(int theOpCode) {
        if (opcodeCount(theOpCode) != 0) {
            throw new IllegalArgumentException("Unexpected operands");
        } else {
            int newStack = this.itsStackTop + stackChange(theOpCode);
            if (newStack < 0 || 32767 < newStack) {
                badStack(newStack);
            }
            this.addToCodeBuffer(theOpCode);
            this.itsStackTop = (short) newStack;
            if (newStack > this.itsMaxStack) {
                this.itsMaxStack = (short) newStack;
            }
            if (theOpCode == 191) {
                this.addSuperBlockStart(this.itsCodeBufferTop);
            }
        }
    }

    public void add(int theOpCode, int theOperand) {
        int newStack = this.itsStackTop + stackChange(theOpCode);
        if (newStack < 0 || 32767 < newStack) {
            badStack(newStack);
        }
        switch(theOpCode) {
            case 16:
                if ((byte) theOperand != theOperand) {
                    throw new IllegalArgumentException("out of range byte");
                }
                this.addToCodeBuffer(theOpCode);
                this.addToCodeBuffer((byte) theOperand);
                break;
            case 17:
                if ((short) theOperand != theOperand) {
                    throw new IllegalArgumentException("out of range short");
                }
                this.addToCodeBuffer(theOpCode);
                this.addToCodeInt16(theOperand);
                break;
            case 18:
            case 19:
            case 20:
                if (0 > theOperand || theOperand >= 65536) {
                    throw new ClassFileWriter.ClassFileFormatException("out of range index");
                }
                if (theOperand < 256 && theOpCode != 19 && theOpCode != 20) {
                    this.addToCodeBuffer(theOpCode);
                    this.addToCodeBuffer(theOperand);
                } else {
                    if (theOpCode == 18) {
                        this.addToCodeBuffer(19);
                    } else {
                        this.addToCodeBuffer(theOpCode);
                    }
                    this.addToCodeInt16(theOperand);
                }
                break;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 169:
                if (theOperand < 0 || 65536 <= theOperand) {
                    throw new ClassFileWriter.ClassFileFormatException("out of range variable");
                }
                if (theOperand >= 256) {
                    this.addToCodeBuffer(196);
                    this.addToCodeBuffer(theOpCode);
                    this.addToCodeInt16(theOperand);
                } else {
                    this.addToCodeBuffer(theOpCode);
                    this.addToCodeBuffer(theOperand);
                }
                break;
            case 167:
                this.addSuperBlockStart(this.itsCodeBufferTop + 3);
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 168:
            case 198:
            case 199:
                if ((theOperand & -2147483648) != Integer.MIN_VALUE && (theOperand < 0 || theOperand > 65535)) {
                    throw new IllegalArgumentException("Bad label for branch");
                }
                int branchPC = this.itsCodeBufferTop;
                this.addToCodeBuffer(theOpCode);
                if ((theOperand & -2147483648) != Integer.MIN_VALUE) {
                    this.addToCodeInt16(theOperand);
                    int target = theOperand + branchPC;
                    this.addSuperBlockStart(target);
                    this.itsJumpFroms.put(target, branchPC);
                } else {
                    int targetPC = this.getLabelPC(theOperand);
                    if (targetPC != -1) {
                        int offset = targetPC - branchPC;
                        this.addToCodeInt16(offset);
                        this.addSuperBlockStart(targetPC);
                        this.itsJumpFroms.put(targetPC, branchPC);
                    } else {
                        this.addLabelFixup(theOperand, branchPC + 1);
                        this.addToCodeInt16(0);
                    }
                }
                break;
            case 180:
            case 181:
                if (0 > theOperand || theOperand >= 65536) {
                    throw new IllegalArgumentException("out of range field");
                }
                this.addToCodeBuffer(theOpCode);
                this.addToCodeInt16(theOperand);
                break;
            case 188:
                if (0 > theOperand || theOperand >= 256) {
                    throw new IllegalArgumentException("out of range index");
                }
                this.addToCodeBuffer(theOpCode);
                this.addToCodeBuffer(theOperand);
                break;
            default:
                throw new IllegalArgumentException("Unexpected opcode for 1 operand");
        }
        this.itsStackTop = (short) newStack;
        if (newStack > this.itsMaxStack) {
            this.itsMaxStack = (short) newStack;
        }
    }

    public void addLoadConstant(int k) {
        switch(k) {
            case 0:
                this.add(3);
                break;
            case 1:
                this.add(4);
                break;
            case 2:
                this.add(5);
                break;
            case 3:
                this.add(6);
                break;
            case 4:
                this.add(7);
                break;
            case 5:
                this.add(8);
                break;
            default:
                this.add(18, this.itsConstantPool.addConstant(k));
        }
    }

    public void addLoadConstant(long k) {
        this.add(20, this.itsConstantPool.addConstant(k));
    }

    public void addLoadConstant(float k) {
        this.add(18, this.itsConstantPool.addConstant(k));
    }

    public void addLoadConstant(double k) {
        this.add(20, this.itsConstantPool.addConstant(k));
    }

    public void addLoadConstant(String k) {
        this.add(18, this.itsConstantPool.addConstant(k));
    }

    public void add(int theOpCode, int theOperand1, int theOperand2) {
        int newStack = this.itsStackTop + stackChange(theOpCode);
        if (newStack < 0 || 32767 < newStack) {
            badStack(newStack);
        }
        if (theOpCode == 132) {
            if (theOperand1 < 0 || 65536 <= theOperand1) {
                throw new ClassFileWriter.ClassFileFormatException("out of range variable");
            }
            if (theOperand2 < 0 || 65536 <= theOperand2) {
                throw new ClassFileWriter.ClassFileFormatException("out of range increment");
            }
            if (theOperand1 <= 255 && theOperand2 <= 127) {
                this.addToCodeBuffer(132);
                this.addToCodeBuffer(theOperand1);
                this.addToCodeBuffer(theOperand2);
            } else {
                this.addToCodeBuffer(196);
                this.addToCodeBuffer(132);
                this.addToCodeInt16(theOperand1);
                this.addToCodeInt16(theOperand2);
            }
        } else {
            if (theOpCode != 197) {
                throw new IllegalArgumentException("Unexpected opcode for 2 operands");
            }
            if (0 > theOperand1 || theOperand1 >= 65536) {
                throw new IllegalArgumentException("out of range index");
            }
            if (0 > theOperand2 || theOperand2 >= 256) {
                throw new IllegalArgumentException("out of range dimensions");
            }
            this.addToCodeBuffer(197);
            this.addToCodeInt16(theOperand1);
            this.addToCodeBuffer(theOperand2);
        }
        this.itsStackTop = (short) newStack;
        if (newStack > this.itsMaxStack) {
            this.itsMaxStack = (short) newStack;
        }
    }

    public void add(int theOpCode, String className) {
        int newStack = this.itsStackTop + stackChange(theOpCode);
        if (newStack < 0 || 32767 < newStack) {
            badStack(newStack);
        }
        switch(theOpCode) {
            case 187:
            case 189:
            case 192:
            case 193:
                short classIndex = this.itsConstantPool.addClass(className);
                this.addToCodeBuffer(theOpCode);
                this.addToCodeInt16(classIndex);
                this.itsStackTop = (short) newStack;
                if (newStack > this.itsMaxStack) {
                    this.itsMaxStack = (short) newStack;
                }
                return;
            case 188:
            case 190:
            case 191:
            default:
                throw new IllegalArgumentException("bad opcode for class reference");
        }
    }

    public void add(int theOpCode, String className, String fieldName, String fieldType) {
        int newStack = this.itsStackTop + stackChange(theOpCode);
        char fieldTypeChar = fieldType.charAt(0);
        int fieldSize = fieldTypeChar != 'J' && fieldTypeChar != 'D' ? 1 : 2;
        newStack = switch(theOpCode) {
            case 178, 180 ->
                newStack + fieldSize;
            case 179, 181 ->
                newStack - fieldSize;
            default ->
                throw new IllegalArgumentException("bad opcode for field reference");
        };
        if (newStack < 0 || 32767 < newStack) {
            badStack(newStack);
        }
        short fieldRefIndex = this.itsConstantPool.addFieldRef(className, fieldName, fieldType);
        this.addToCodeBuffer(theOpCode);
        this.addToCodeInt16(fieldRefIndex);
        this.itsStackTop = (short) newStack;
        if (newStack > this.itsMaxStack) {
            this.itsMaxStack = (short) newStack;
        }
    }

    public void addInvoke(int theOpCode, String className, String methodName, String methodType) {
        int parameterInfo = sizeOfParameters(methodType);
        int parameterCount = parameterInfo >>> 16;
        int stackDiff = (short) parameterInfo;
        int newStack = this.itsStackTop + stackDiff;
        newStack += stackChange(theOpCode);
        if (newStack < 0 || 32767 < newStack) {
            badStack(newStack);
        }
        switch(theOpCode) {
            case 182:
            case 183:
            case 184:
            case 185:
                this.addToCodeBuffer(theOpCode);
                if (theOpCode == 185) {
                    short ifMethodRefIndex = this.itsConstantPool.addInterfaceMethodRef(className, methodName, methodType);
                    this.addToCodeInt16(ifMethodRefIndex);
                    this.addToCodeBuffer(parameterCount + 1);
                    this.addToCodeBuffer(0);
                } else {
                    short methodRefIndex = this.itsConstantPool.addMethodRef(className, methodName, methodType);
                    this.addToCodeInt16(methodRefIndex);
                }
                this.itsStackTop = (short) newStack;
                if (newStack > this.itsMaxStack) {
                    this.itsMaxStack = (short) newStack;
                }
                return;
            default:
                throw new IllegalArgumentException("bad opcode for method reference");
        }
    }

    public void addInvokeDynamic(String methodName, String methodType, ClassFileWriter.MHandle bsm, Object... bsmArgs) {
        if (MajorVersion < 51) {
            throw new RuntimeException("Please build and run with JDK 1.7 for invokedynamic support");
        } else {
            int parameterInfo = sizeOfParameters(methodType);
            int stackDiff = (short) parameterInfo;
            int newStack = this.itsStackTop + stackDiff;
            if (newStack < 0 || 32767 < newStack) {
                badStack(newStack);
            }
            ClassFileWriter.BootstrapEntry bsmEntry = new ClassFileWriter.BootstrapEntry(bsm, bsmArgs);
            if (this.itsBootstrapMethods == null) {
                this.itsBootstrapMethods = new ObjArray();
            }
            int bootstrapIndex = this.itsBootstrapMethods.indexOf(bsmEntry);
            if (bootstrapIndex == -1) {
                bootstrapIndex = this.itsBootstrapMethods.size();
                this.itsBootstrapMethods.add(bsmEntry);
                this.itsBootstrapMethodsLength = this.itsBootstrapMethodsLength + bsmEntry.code.length;
            }
            short invokedynamicIndex = this.itsConstantPool.addInvokeDynamic(methodName, methodType, bootstrapIndex);
            this.addToCodeBuffer(186);
            this.addToCodeInt16(invokedynamicIndex);
            this.addToCodeInt16(0);
            this.itsStackTop = (short) newStack;
            if (newStack > this.itsMaxStack) {
                this.itsMaxStack = (short) newStack;
            }
        }
    }

    public void addPush(int k) {
        if ((byte) k == k) {
            if (k == -1) {
                this.add(2);
            } else if (0 <= k && k <= 5) {
                this.add((byte) (3 + k));
            } else {
                this.add(16, (byte) k);
            }
        } else if ((short) k == k) {
            this.add(17, (short) k);
        } else {
            this.addLoadConstant(k);
        }
    }

    public void addPush(boolean k) {
        this.add(k ? 4 : 3);
    }

    public void addPush(long k) {
        int ik = (int) k;
        if ((long) ik == k) {
            this.addPush(ik);
            this.add(133);
        } else {
            this.addLoadConstant(k);
        }
    }

    public void addPush(double k) {
        if (k == 0.0) {
            this.add(14);
            if (1.0 / k < 0.0) {
                this.add(119);
            }
        } else if (k != 1.0 && k != -1.0) {
            this.addLoadConstant(k);
        } else {
            this.add(15);
            if (k < 0.0) {
                this.add(119);
            }
        }
    }

    public void addPush(String k) {
        int length = k.length();
        int limit = this.itsConstantPool.getUtfEncodingLimit(k, 0, length);
        if (limit == length) {
            this.addLoadConstant(k);
        } else {
            String SB = "java/lang/StringBuilder";
            this.add(187, "java/lang/StringBuilder");
            this.add(89);
            this.addPush(length);
            this.addInvoke(183, "java/lang/StringBuilder", "<init>", "(I)V");
            int cursor = 0;
            while (true) {
                this.add(89);
                String s = k.substring(cursor, limit);
                this.addLoadConstant(s);
                this.addInvoke(182, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
                this.add(87);
                if (limit == length) {
                    this.addInvoke(182, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
                    return;
                }
                cursor = limit;
                limit = this.itsConstantPool.getUtfEncodingLimit(k, limit, length);
            }
        }
    }

    public boolean isUnderStringSizeLimit(String k) {
        return this.itsConstantPool.isUnderUtfEncodingLimit(k);
    }

    public void addIStore(int local) {
        this.xop(59, 54, local);
    }

    public void addLStore(int local) {
        this.xop(63, 55, local);
    }

    public void addFStore(int local) {
        this.xop(67, 56, local);
    }

    public void addDStore(int local) {
        this.xop(71, 57, local);
    }

    public void addAStore(int local) {
        this.xop(75, 58, local);
    }

    public void addILoad(int local) {
        this.xop(26, 21, local);
    }

    public void addLLoad(int local) {
        this.xop(30, 22, local);
    }

    public void addFLoad(int local) {
        this.xop(34, 23, local);
    }

    public void addDLoad(int local) {
        this.xop(38, 24, local);
    }

    public void addALoad(int local) {
        this.xop(42, 25, local);
    }

    public void addLoadThis() {
        this.add(42);
    }

    private void xop(int shortOp, int op, int local) {
        switch(local) {
            case 0:
                this.add(shortOp);
                break;
            case 1:
                this.add(shortOp + 1);
                break;
            case 2:
                this.add(shortOp + 2);
                break;
            case 3:
                this.add(shortOp + 3);
                break;
            default:
                this.add(op, local);
        }
    }

    public int addTableSwitch(int low, int high) {
        if (low > high) {
            throw new ClassFileWriter.ClassFileFormatException("Bad bounds: " + low + " " + high);
        } else {
            int newStack = this.itsStackTop + stackChange(170);
            if (newStack < 0 || 32767 < newStack) {
                badStack(newStack);
            }
            int entryCount = high - low + 1;
            int padSize = 3 & ~this.itsCodeBufferTop;
            int N = this.addReservedCodeSpace(1 + padSize + 4 * (3 + entryCount));
            int switchStart = N;
            for (this.itsCodeBuffer[N++] = -86; padSize != 0; padSize--) {
                this.itsCodeBuffer[N++] = 0;
            }
            N += 4;
            N = putInt32(low, this.itsCodeBuffer, N);
            putInt32(high, this.itsCodeBuffer, N);
            this.itsStackTop = (short) newStack;
            if (newStack > this.itsMaxStack) {
                this.itsMaxStack = (short) newStack;
            }
            return switchStart;
        }
    }

    public final void markTableSwitchDefault(int switchStart) {
        this.addSuperBlockStart(this.itsCodeBufferTop);
        this.itsJumpFroms.put(this.itsCodeBufferTop, switchStart);
        this.setTableSwitchJump(switchStart, -1, this.itsCodeBufferTop);
    }

    public final void markTableSwitchCase(int switchStart, int caseIndex) {
        this.addSuperBlockStart(this.itsCodeBufferTop);
        this.itsJumpFroms.put(this.itsCodeBufferTop, switchStart);
        this.setTableSwitchJump(switchStart, caseIndex, this.itsCodeBufferTop);
    }

    public final void markTableSwitchCase(int switchStart, int caseIndex, int stackTop) {
        if (0 <= stackTop && stackTop <= this.itsMaxStack) {
            this.itsStackTop = (short) stackTop;
            this.addSuperBlockStart(this.itsCodeBufferTop);
            this.itsJumpFroms.put(this.itsCodeBufferTop, switchStart);
            this.setTableSwitchJump(switchStart, caseIndex, this.itsCodeBufferTop);
        } else {
            throw new IllegalArgumentException("Bad stack index: " + stackTop);
        }
    }

    public void setTableSwitchJump(int switchStart, int caseIndex, int jumpTarget) {
        if (jumpTarget < 0 || this.itsCodeBufferTop < jumpTarget) {
            throw new IllegalArgumentException("Bad jump target: " + jumpTarget);
        } else if (caseIndex < -1) {
            throw new IllegalArgumentException("Bad case index: " + caseIndex);
        } else {
            int padSize = 3 & ~switchStart;
            int caseOffset;
            if (caseIndex < 0) {
                caseOffset = switchStart + 1 + padSize;
            } else {
                caseOffset = switchStart + 1 + padSize + 4 * (3 + caseIndex);
            }
            if (switchStart < 0 || this.itsCodeBufferTop - 16 - padSize - 1 < switchStart) {
                throw new IllegalArgumentException(switchStart + " is outside a possible range of tableswitch in already generated code");
            } else if ((255 & this.itsCodeBuffer[switchStart]) != 170) {
                throw new IllegalArgumentException(switchStart + " is not offset of tableswitch statement");
            } else if (caseOffset >= 0 && this.itsCodeBufferTop >= caseOffset + 4) {
                putInt32(jumpTarget - switchStart, this.itsCodeBuffer, caseOffset);
            } else {
                throw new ClassFileWriter.ClassFileFormatException("Too big case index: " + caseIndex);
            }
        }
    }

    public int acquireLabel() {
        int top = this.itsLabelTableTop;
        if (this.itsLabelTable == null || top == this.itsLabelTable.length) {
            if (this.itsLabelTable == null) {
                this.itsLabelTable = new int[32];
            } else {
                int[] tmp = new int[this.itsLabelTable.length * 2];
                System.arraycopy(this.itsLabelTable, 0, tmp, 0, top);
                this.itsLabelTable = tmp;
            }
        }
        this.itsLabelTableTop = top + 1;
        this.itsLabelTable[top] = -1;
        return top | -2147483648;
    }

    public void markLabel(int label) {
        if (label >= 0) {
            throw new IllegalArgumentException("Bad label, no biscuit");
        } else {
            label &= Integer.MAX_VALUE;
            if (label > this.itsLabelTableTop) {
                throw new IllegalArgumentException("Bad label");
            } else if (this.itsLabelTable[label] != -1) {
                throw new IllegalStateException("Can only mark label once");
            } else {
                this.itsLabelTable[label] = this.itsCodeBufferTop;
            }
        }
    }

    public void markLabel(int label, short stackTop) {
        this.markLabel(label);
        this.itsStackTop = stackTop;
    }

    public void markHandler(int theLabel) {
        this.itsStackTop = 1;
        this.markLabel(theLabel);
    }

    public int getLabelPC(int label) {
        if (label >= 0) {
            throw new IllegalArgumentException("Bad label, no biscuit");
        } else {
            label &= Integer.MAX_VALUE;
            if (label >= this.itsLabelTableTop) {
                throw new IllegalArgumentException("Bad label");
            } else {
                return this.itsLabelTable[label];
            }
        }
    }

    private void addLabelFixup(int label, int fixupSite) {
        if (label >= 0) {
            throw new IllegalArgumentException("Bad label, no biscuit");
        } else {
            label &= Integer.MAX_VALUE;
            if (label >= this.itsLabelTableTop) {
                throw new IllegalArgumentException("Bad label");
            } else {
                int top = this.itsFixupTableTop;
                if (this.itsFixupTable == null || top == this.itsFixupTable.length) {
                    if (this.itsFixupTable == null) {
                        this.itsFixupTable = new long[40];
                    } else {
                        long[] tmp = new long[this.itsFixupTable.length * 2];
                        System.arraycopy(this.itsFixupTable, 0, tmp, 0, top);
                        this.itsFixupTable = tmp;
                    }
                }
                this.itsFixupTableTop = top + 1;
                this.itsFixupTable[top] = (long) label << 32 | (long) fixupSite;
            }
        }
    }

    private void fixLabelGotos() {
        byte[] codeBuffer = this.itsCodeBuffer;
        for (int i = 0; i < this.itsFixupTableTop; i++) {
            long fixup = this.itsFixupTable[i];
            int label = (int) (fixup >> 32);
            int fixupSite = (int) fixup;
            int pc = this.itsLabelTable[label];
            if (pc == -1) {
                throw new RuntimeException("unlocated label");
            }
            this.addSuperBlockStart(pc);
            this.itsJumpFroms.put(pc, fixupSite - 1);
            int offset = pc - (fixupSite - 1);
            if ((short) offset != offset) {
                throw new ClassFileWriter.ClassFileFormatException("Program too complex: too big jump offset");
            }
            codeBuffer[fixupSite] = (byte) (offset >> 8);
            codeBuffer[fixupSite + 1] = (byte) offset;
        }
        this.itsFixupTableTop = 0;
    }

    public int getCurrentCodeOffset() {
        return this.itsCodeBufferTop;
    }

    public short getStackTop() {
        return this.itsStackTop;
    }

    public void setStackTop(short n) {
        this.itsStackTop = n;
    }

    public void adjustStackTop(int delta) {
        int newStack = this.itsStackTop + delta;
        if (newStack < 0 || 32767 < newStack) {
            badStack(newStack);
        }
        this.itsStackTop = (short) newStack;
        if (newStack > this.itsMaxStack) {
            this.itsMaxStack = (short) newStack;
        }
    }

    private void addToCodeBuffer(int b) {
        int N = this.addReservedCodeSpace(1);
        this.itsCodeBuffer[N] = (byte) b;
    }

    private void addToCodeInt16(int value) {
        int N = this.addReservedCodeSpace(2);
        putInt16(value, this.itsCodeBuffer, N);
    }

    private int addReservedCodeSpace(int size) {
        if (this.itsCurrentMethod == null) {
            throw new IllegalArgumentException("No method to add to");
        } else {
            int oldTop = this.itsCodeBufferTop;
            int newTop = oldTop + size;
            if (newTop > this.itsCodeBuffer.length) {
                int newSize = this.itsCodeBuffer.length * 2;
                if (newTop > newSize) {
                    newSize = newTop;
                }
                byte[] tmp = new byte[newSize];
                System.arraycopy(this.itsCodeBuffer, 0, tmp, 0, oldTop);
                this.itsCodeBuffer = tmp;
            }
            this.itsCodeBufferTop = newTop;
            return oldTop;
        }
    }

    public void addExceptionHandler(int startLabel, int endLabel, int handlerLabel, String catchClassName) {
        if ((startLabel & -2147483648) != Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Bad startLabel");
        } else if ((endLabel & -2147483648) != Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Bad endLabel");
        } else if ((handlerLabel & -2147483648) != Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Bad handlerLabel");
        } else {
            short catch_type_index = catchClassName == null ? 0 : this.itsConstantPool.addClass(catchClassName);
            ExceptionTableEntry newEntry = new ExceptionTableEntry(startLabel, endLabel, handlerLabel, catch_type_index);
            int N = this.itsExceptionTableTop;
            if (N == 0) {
                this.itsExceptionTable = new ExceptionTableEntry[4];
            } else if (N == this.itsExceptionTable.length) {
                ExceptionTableEntry[] tmp = new ExceptionTableEntry[N * 2];
                System.arraycopy(this.itsExceptionTable, 0, tmp, 0, N);
                this.itsExceptionTable = tmp;
            }
            this.itsExceptionTable[N] = newEntry;
            this.itsExceptionTableTop = N + 1;
        }
    }

    public void addLineNumberEntry(short lineNumber) {
        if (this.itsCurrentMethod == null) {
            throw new IllegalArgumentException("No method to stop");
        } else {
            int N = this.itsLineNumberTableTop;
            if (N == 0) {
                this.itsLineNumberTable = new int[16];
            } else if (N == this.itsLineNumberTable.length) {
                int[] tmp = new int[N * 2];
                System.arraycopy(this.itsLineNumberTable, 0, tmp, 0, N);
                this.itsLineNumberTable = tmp;
            }
            this.itsLineNumberTable[N] = (this.itsCodeBufferTop << 16) + lineNumber;
            this.itsLineNumberTableTop = N + 1;
        }
    }

    private int[] createInitialLocals() {
        int[] initialLocals = new int[this.itsMaxLocals];
        int localsTop = 0;
        if ((this.itsCurrentMethod.getFlags() & 8) == 0) {
            if ("<init>".equals(this.itsCurrentMethod.getName())) {
                initialLocals[localsTop++] = 6;
            } else {
                initialLocals[localsTop++] = TypeInfo.OBJECT(this.itsThisClassIndex);
            }
        }
        String type = this.itsCurrentMethod.getType();
        int lParenIndex = type.indexOf(40);
        int rParenIndex = type.indexOf(41);
        if (lParenIndex == 0 && rParenIndex >= 0) {
            int start = lParenIndex + 1;
            StringBuilder paramType = new StringBuilder();
            while (start < rParenIndex) {
                switch(type.charAt(start)) {
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'F':
                    case 'I':
                    case 'J':
                    case 'S':
                    case 'Z':
                        paramType.append(type.charAt(start));
                        start++;
                    case 'E':
                    case 'G':
                    case 'H':
                    case 'K':
                    case 'M':
                    case 'N':
                    case 'O':
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'T':
                    case 'U':
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    default:
                        break;
                    case 'L':
                        int end = type.indexOf(59, start) + 1;
                        String name = type.substring(start, end);
                        paramType.append(name);
                        start = end;
                        break;
                    case '[':
                        paramType.append('[');
                        start++;
                        continue;
                }
                String internalType = descriptorToInternalName(paramType.toString());
                int typeInfo = TypeInfo.fromType(internalType, this.itsConstantPool);
                initialLocals[localsTop++] = typeInfo;
                if (TypeInfo.isTwoWords(typeInfo)) {
                    localsTop++;
                }
                paramType.setLength(0);
            }
            return initialLocals;
        } else {
            throw new IllegalArgumentException("bad method type");
        }
    }

    public void write(OutputStream oStream) throws IOException {
        byte[] array = this.toByteArray();
        oStream.write(array);
    }

    private int getWriteSize() {
        int size = 0;
        if (this.itsSourceFileNameIndex != 0) {
            this.itsConstantPool.addUtf8("SourceFile");
        }
        size += 8;
        size += this.itsConstantPool.getWriteSize();
        size += 2;
        size += 2;
        size += 2;
        size += 2;
        size += 2 * this.itsInterfaces.size();
        size += 2;
        for (int i = 0; i < this.itsFields.size(); i++) {
            size += ((ClassFileField) this.itsFields.get(i)).getWriteSize();
        }
        size += 2;
        for (int i = 0; i < this.itsMethods.size(); i++) {
            size += ((ClassFileMethod) this.itsMethods.get(i)).getWriteSize();
        }
        size += 2;
        if (this.itsSourceFileNameIndex != 0) {
            size += 2;
            size += 4;
            size += 2;
        }
        if (this.itsBootstrapMethods != null) {
            size += 2;
            size += 4;
            size += 2;
            size += this.itsBootstrapMethodsLength;
        }
        return size;
    }

    public byte[] toByteArray() {
        short bootstrapMethodsAttrNameIndex = 0;
        int attributeCount = 0;
        short sourceFileAttributeNameIndex = 0;
        if (this.itsBootstrapMethods != null) {
            attributeCount++;
            bootstrapMethodsAttrNameIndex = this.itsConstantPool.addUtf8("BootstrapMethods");
        }
        if (this.itsSourceFileNameIndex != 0) {
            attributeCount++;
            sourceFileAttributeNameIndex = this.itsConstantPool.addUtf8("SourceFile");
        }
        int offset = 0;
        int dataSize = this.getWriteSize();
        byte[] data = new byte[dataSize];
        offset = putInt32(-889275714, data, offset);
        offset = putInt16(MinorVersion, data, offset);
        offset = putInt16(MajorVersion, data, offset);
        offset = this.itsConstantPool.write(data, offset);
        offset = putInt16(this.itsFlags, data, offset);
        offset = putInt16(this.itsThisClassIndex, data, offset);
        offset = putInt16(this.itsSuperClassIndex, data, offset);
        offset = putInt16(this.itsInterfaces.size(), data, offset);
        for (int i = 0; i < this.itsInterfaces.size(); i++) {
            int interfaceIndex = (Short) this.itsInterfaces.get(i);
            offset = putInt16(interfaceIndex, data, offset);
        }
        offset = putInt16(this.itsFields.size(), data, offset);
        for (int i = 0; i < this.itsFields.size(); i++) {
            ClassFileField field = (ClassFileField) this.itsFields.get(i);
            offset = field.write(data, offset);
        }
        offset = putInt16(this.itsMethods.size(), data, offset);
        for (int i = 0; i < this.itsMethods.size(); i++) {
            ClassFileMethod method = (ClassFileMethod) this.itsMethods.get(i);
            offset = method.write(data, offset);
        }
        offset = putInt16(attributeCount, data, offset);
        if (this.itsBootstrapMethods != null) {
            offset = putInt16(bootstrapMethodsAttrNameIndex, data, offset);
            offset = putInt32(this.itsBootstrapMethodsLength + 2, data, offset);
            offset = putInt16(this.itsBootstrapMethods.size(), data, offset);
            for (int i = 0; i < this.itsBootstrapMethods.size(); i++) {
                ClassFileWriter.BootstrapEntry entry = (ClassFileWriter.BootstrapEntry) this.itsBootstrapMethods.get(i);
                System.arraycopy(entry.code, 0, data, offset, entry.code.length);
                offset += entry.code.length;
            }
        }
        if (this.itsSourceFileNameIndex != 0) {
            offset = putInt16(sourceFileAttributeNameIndex, data, offset);
            offset = putInt32(2, data, offset);
            offset = putInt16(this.itsSourceFileNameIndex, data, offset);
        }
        if (offset != dataSize) {
            throw new RuntimeException();
        } else {
            return data;
        }
    }

    final char[] getCharBuffer(int minimalSize) {
        if (minimalSize > this.tmpCharBuffer.length) {
            int newSize = this.tmpCharBuffer.length * 2;
            if (minimalSize > newSize) {
                newSize = minimalSize;
            }
            this.tmpCharBuffer = new char[newSize];
        }
        return this.tmpCharBuffer;
    }

    private void addSuperBlockStart(int pc) {
        if (GenerateStackMap) {
            if (this.itsSuperBlockStarts == null) {
                this.itsSuperBlockStarts = new int[4];
            } else if (this.itsSuperBlockStarts.length == this.itsSuperBlockStartsTop) {
                int[] tmp = new int[this.itsSuperBlockStartsTop * 2];
                System.arraycopy(this.itsSuperBlockStarts, 0, tmp, 0, this.itsSuperBlockStartsTop);
                this.itsSuperBlockStarts = tmp;
            }
            this.itsSuperBlockStarts[this.itsSuperBlockStartsTop++] = pc;
        }
    }

    private void finalizeSuperBlockStarts() {
        if (GenerateStackMap) {
            for (int i = 0; i < this.itsExceptionTableTop; i++) {
                ExceptionTableEntry ete = this.itsExceptionTable[i];
                int handlerPC = this.getLabelPC(ete.itsHandlerLabel);
                this.addSuperBlockStart(handlerPC);
            }
            Arrays.sort(this.itsSuperBlockStarts, 0, this.itsSuperBlockStartsTop);
            int prev = this.itsSuperBlockStarts[0];
            int copyTo = 1;
            for (int i = 1; i < this.itsSuperBlockStartsTop; i++) {
                int curr = this.itsSuperBlockStarts[i];
                if (prev != curr) {
                    if (copyTo != i) {
                        this.itsSuperBlockStarts[copyTo] = curr;
                    }
                    copyTo++;
                    prev = curr;
                }
            }
            this.itsSuperBlockStartsTop = copyTo;
            if (this.itsSuperBlockStarts[copyTo - 1] == this.itsCodeBufferTop) {
                this.itsSuperBlockStartsTop--;
            }
        }
    }

    static {
        InputStream is = null;
        int major = 48;
        int minor = 0;
        try {
            is = ClassFileWriter.class.getResourceAsStream("ClassFileWriter.class");
            if (is == null) {
                is = ClassLoader.getSystemResourceAsStream("dev/latvian/repackaged/org/mozilla/classfile/ClassFileWriter.class");
            }
            byte[] header = new byte[8];
            int read = 0;
            while (read < 8) {
                int c = is.read(header, read, 8 - read);
                if (c < 0) {
                    throw new IOException();
                }
                read += c;
            }
            minor = header[4] << 8 | header[5] & 255;
            major = header[6] << 8 | header[7] & 255;
        } catch (Exception var14) {
        } finally {
            MinorVersion = minor;
            MajorVersion = major;
            GenerateStackMap = major >= 50;
            if (is != null) {
                try {
                    is.close();
                } catch (IOException var13) {
                }
            }
        }
    }

    final class BootstrapEntry {

        final byte[] code;

        BootstrapEntry(ClassFileWriter.MHandle bsm, Object... bsmArgs) {
            int length = 4 + bsmArgs.length * 2;
            this.code = new byte[length];
            ClassFileWriter.putInt16(ClassFileWriter.this.itsConstantPool.addMethodHandle(bsm), this.code, 0);
            ClassFileWriter.putInt16(bsmArgs.length, this.code, 2);
            for (int i = 0; i < bsmArgs.length; i++) {
                ClassFileWriter.putInt16(ClassFileWriter.this.itsConstantPool.addConstant(bsmArgs[i]), this.code, 4 + i * 2);
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof ClassFileWriter.BootstrapEntry && Arrays.equals(this.code, ((ClassFileWriter.BootstrapEntry) obj).code);
        }

        public int hashCode() {
            return ~Arrays.hashCode(this.code);
        }
    }

    public static class ClassFileFormatException extends RuntimeException {

        private static final long serialVersionUID = 1263998431033790599L;

        ClassFileFormatException(String message) {
            super(message);
        }
    }

    public static final class MHandle {

        final byte tag;

        final String owner;

        final String name;

        final String desc;

        public MHandle(byte tag, String owner, String name, String desc) {
            this.tag = tag;
            this.owner = owner;
            this.name = name;
            this.desc = desc;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            } else {
                return !(obj instanceof ClassFileWriter.MHandle mh) ? false : this.tag == mh.tag && this.owner.equals(mh.owner) && this.name.equals(mh.name) && this.desc.equals(mh.desc);
            }
        }

        public int hashCode() {
            return this.tag + this.owner.hashCode() * this.name.hashCode() * this.desc.hashCode();
        }

        public String toString() {
            return this.owner + "." + this.name + this.desc + " (" + this.tag + ")";
        }
    }

    final class StackMapTable {

        static final boolean DEBUGSTACKMAP = false;

        private int[] locals;

        private int localsTop;

        private int[] stack;

        private int stackTop;

        private SuperBlock[] workList;

        private int workListTop;

        private SuperBlock[] superBlocks = null;

        private byte[] rawStackMap;

        private int rawStackMapTop;

        private boolean wide;

        StackMapTable() {
            this.locals = this.stack = null;
            this.workList = null;
            this.rawStackMap = null;
            this.localsTop = 0;
            this.stackTop = 0;
            this.workListTop = 0;
            this.rawStackMapTop = 0;
            this.wide = false;
        }

        void generate() {
            this.superBlocks = new SuperBlock[ClassFileWriter.this.itsSuperBlockStartsTop];
            int[] initialLocals = ClassFileWriter.this.createInitialLocals();
            for (int i = 0; i < ClassFileWriter.this.itsSuperBlockStartsTop; i++) {
                int start = ClassFileWriter.this.itsSuperBlockStarts[i];
                int end;
                if (i == ClassFileWriter.this.itsSuperBlockStartsTop - 1) {
                    end = ClassFileWriter.this.itsCodeBufferTop;
                } else {
                    end = ClassFileWriter.this.itsSuperBlockStarts[i + 1];
                }
                this.superBlocks[i] = new SuperBlock(i, start, end, initialLocals);
            }
            this.verify();
        }

        private SuperBlock getSuperBlockFromOffset(int offset) {
            for (int i = 0; i < this.superBlocks.length; i++) {
                SuperBlock sb = this.superBlocks[i];
                if (sb == null) {
                    break;
                }
                if (offset >= sb.getStart() && offset < sb.getEnd()) {
                    return sb;
                }
            }
            throw new IllegalArgumentException("bad offset: " + offset);
        }

        private boolean isSuperBlockEnd(int opcode) {
            return switch(opcode) {
                case 167, 170, 171, 172, 173, 174, 176, 177, 191, 200 ->
                    true;
                default ->
                    false;
            };
        }

        private SuperBlock[] getSuperBlockDependencies() {
            SuperBlock[] deps = new SuperBlock[this.superBlocks.length];
            for (int i = 0; i < ClassFileWriter.this.itsExceptionTableTop; i++) {
                ExceptionTableEntry ete = ClassFileWriter.this.itsExceptionTable[i];
                int startPC = ClassFileWriter.this.getLabelPC(ete.itsStartLabel);
                int handlerPC = ClassFileWriter.this.getLabelPC(ete.itsHandlerLabel);
                SuperBlock handlerSB = this.getSuperBlockFromOffset(handlerPC);
                SuperBlock dep = this.getSuperBlockFromOffset(startPC);
                deps[handlerSB.getIndex()] = dep;
            }
            int[] targetPCs = ClassFileWriter.this.itsJumpFroms.getKeys();
            for (int i = 0; i < targetPCs.length; i++) {
                int targetPC = targetPCs[i];
                int branchPC = ClassFileWriter.this.itsJumpFroms.getInt(targetPC, -1);
                SuperBlock branchSB = this.getSuperBlockFromOffset(branchPC);
                SuperBlock targetSB = this.getSuperBlockFromOffset(targetPC);
                deps[targetSB.getIndex()] = branchSB;
            }
            return deps;
        }

        private SuperBlock getBranchTarget(int bci) {
            int target;
            if ((ClassFileWriter.this.itsCodeBuffer[bci] & 255) == 200) {
                target = bci + this.getOperand(bci + 1, 4);
            } else {
                target = bci + (short) this.getOperand(bci + 1, 2);
            }
            return this.getSuperBlockFromOffset(target);
        }

        private boolean isBranch(int opcode) {
            return switch(opcode) {
                case 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 198, 199, 200 ->
                    true;
                default ->
                    false;
            };
        }

        private int getOperand(int offset) {
            return this.getOperand(offset, 1);
        }

        private int getOperand(int start, int size) {
            int result = 0;
            if (size > 4) {
                throw new IllegalArgumentException("bad operand size");
            } else {
                for (int i = 0; i < size; i++) {
                    result = result << 8 | ClassFileWriter.this.itsCodeBuffer[start + i] & 255;
                }
                return result;
            }
        }

        private void verify() {
            int[] initialLocals = ClassFileWriter.this.createInitialLocals();
            this.superBlocks[0].merge(initialLocals, initialLocals.length, new int[0], 0, ClassFileWriter.this.itsConstantPool);
            this.workList = new SuperBlock[] { this.superBlocks[0] };
            this.workListTop = 1;
            this.executeWorkList();
            for (int i = 0; i < this.superBlocks.length; i++) {
                SuperBlock sb = this.superBlocks[i];
                if (!sb.isInitialized()) {
                    this.killSuperBlock(sb);
                }
            }
            this.executeWorkList();
        }

        private void killSuperBlock(SuperBlock sb) {
            int[] locals = new int[0];
            int[] stack = new int[] { TypeInfo.OBJECT("java/lang/Throwable", ClassFileWriter.this.itsConstantPool) };
            for (int i = 0; i < ClassFileWriter.this.itsExceptionTableTop; i++) {
                ExceptionTableEntry ete = ClassFileWriter.this.itsExceptionTable[i];
                int eteStart = ClassFileWriter.this.getLabelPC(ete.itsStartLabel);
                int eteEnd = ClassFileWriter.this.getLabelPC(ete.itsEndLabel);
                int handlerPC = ClassFileWriter.this.getLabelPC(ete.itsHandlerLabel);
                SuperBlock handlerSB = this.getSuperBlockFromOffset(handlerPC);
                if (sb.getStart() > eteStart && sb.getStart() < eteEnd || eteStart > sb.getStart() && eteStart < sb.getEnd() && handlerSB.isInitialized()) {
                    locals = handlerSB.getLocals();
                    break;
                }
            }
            for (int ix = 0; ix < ClassFileWriter.this.itsExceptionTableTop; ix++) {
                ExceptionTableEntry ete = ClassFileWriter.this.itsExceptionTable[ix];
                int eteStart = ClassFileWriter.this.getLabelPC(ete.itsStartLabel);
                if (eteStart == sb.getStart()) {
                    for (int j = ix + 1; j < ClassFileWriter.this.itsExceptionTableTop; j++) {
                        ClassFileWriter.this.itsExceptionTable[j - 1] = ClassFileWriter.this.itsExceptionTable[j];
                    }
                    ClassFileWriter.this.itsExceptionTableTop--;
                    ix--;
                }
            }
            sb.merge(locals, locals.length, stack, stack.length, ClassFileWriter.this.itsConstantPool);
            int end = sb.getEnd() - 1;
            ClassFileWriter.this.itsCodeBuffer[end] = -65;
            for (int bci = sb.getStart(); bci < end; bci++) {
                ClassFileWriter.this.itsCodeBuffer[bci] = 0;
            }
        }

        private void executeWorkList() {
            while (this.workListTop > 0) {
                SuperBlock work = this.workList[--this.workListTop];
                work.setInQueue(false);
                this.locals = work.getLocals();
                this.stack = work.getStack();
                this.localsTop = this.locals.length;
                this.stackTop = this.stack.length;
                this.executeBlock(work);
            }
        }

        private void executeBlock(SuperBlock work) {
            int bc = 0;
            int next = 0;
            int bci = work.getStart();
            while (bci < work.getEnd()) {
                bc = ClassFileWriter.this.itsCodeBuffer[bci] & 255;
                next = this.execute(bci);
                if (this.isBranch(bc)) {
                    SuperBlock targetSB = this.getBranchTarget(bci);
                    this.flowInto(targetSB);
                } else if (bc == 170) {
                    int switchStart = bci + 1 + (3 & ~bci);
                    int defaultOffset = this.getOperand(switchStart, 4);
                    SuperBlock targetSB = this.getSuperBlockFromOffset(bci + defaultOffset);
                    this.flowInto(targetSB);
                    int low = this.getOperand(switchStart + 4, 4);
                    int high = this.getOperand(switchStart + 8, 4);
                    int numCases = high - low + 1;
                    int caseBase = switchStart + 12;
                    for (int i = 0; i < numCases; i++) {
                        int label = bci + this.getOperand(caseBase + 4 * i, 4);
                        targetSB = this.getSuperBlockFromOffset(label);
                        this.flowInto(targetSB);
                    }
                }
                for (int i = 0; i < ClassFileWriter.this.itsExceptionTableTop; i++) {
                    ExceptionTableEntry ete = ClassFileWriter.this.itsExceptionTable[i];
                    int startPC = ClassFileWriter.this.getLabelPC(ete.itsStartLabel);
                    int endPC = ClassFileWriter.this.getLabelPC(ete.itsEndLabel);
                    if (bci >= startPC && bci < endPC) {
                        int handlerPC = ClassFileWriter.this.getLabelPC(ete.itsHandlerLabel);
                        SuperBlock sb = this.getSuperBlockFromOffset(handlerPC);
                        int exceptionType;
                        if (ete.itsCatchType == 0) {
                            exceptionType = TypeInfo.OBJECT(ClassFileWriter.this.itsConstantPool.addClass("java/lang/Throwable"));
                        } else {
                            exceptionType = TypeInfo.OBJECT(ete.itsCatchType);
                        }
                        sb.merge(this.locals, this.localsTop, new int[] { exceptionType }, 1, ClassFileWriter.this.itsConstantPool);
                        this.addToWorkList(sb);
                    }
                }
                bci += next;
            }
            if (!this.isSuperBlockEnd(bc)) {
                bci = work.getIndex() + 1;
                if (bci < this.superBlocks.length) {
                    this.flowInto(this.superBlocks[bci]);
                }
            }
        }

        private void flowInto(SuperBlock sb) {
            if (sb.merge(this.locals, this.localsTop, this.stack, this.stackTop, ClassFileWriter.this.itsConstantPool)) {
                this.addToWorkList(sb);
            }
        }

        private void addToWorkList(SuperBlock sb) {
            if (!sb.isInQueue()) {
                sb.setInQueue(true);
                sb.setInitialized(true);
                if (this.workListTop == this.workList.length) {
                    SuperBlock[] tmp = new SuperBlock[this.workListTop * 2];
                    System.arraycopy(this.workList, 0, tmp, 0, this.workListTop);
                    this.workList = tmp;
                }
                this.workList[this.workListTop++] = sb;
            }
        }

        private int execute(int bci) {
            int bc;
            int length;
            bc = ClassFileWriter.this.itsCodeBuffer[bci] & 255;
            length = 0;
            label151: switch(bc) {
                case 0:
                case 132:
                case 167:
                case 200:
                    break;
                case 1:
                    this.push(5);
                    break;
                case 18:
                case 19:
                case 20:
                    {
                        int index;
                        if (bc == 18) {
                            index = this.getOperand(bci + 1);
                        } else {
                            index = this.getOperand(bci + 1, 2);
                        }
                        byte constType = ClassFileWriter.this.itsConstantPool.getConstantType(index);
                        switch(constType) {
                            case 3:
                                this.push(1);
                                break label151;
                            case 4:
                                this.push(2);
                                break label151;
                            case 5:
                                this.push(4);
                                break label151;
                            case 6:
                                this.push(3);
                                break label151;
                            case 7:
                            default:
                                throw new IllegalArgumentException("bad const type " + constType);
                            case 8:
                                this.push(TypeInfo.OBJECT("java/lang/String", ClassFileWriter.this.itsConstantPool));
                                break label151;
                        }
                    }
                case 25:
                    this.executeALoad(this.getOperand(bci + 1, this.wide ? 2 : 1));
                    break;
                case 42:
                case 43:
                case 44:
                case 45:
                    this.executeALoad(bc - 42);
                    break;
                case 46:
                case 51:
                case 52:
                case 53:
                case 96:
                case 100:
                case 104:
                case 108:
                case 112:
                case 120:
                case 122:
                case 124:
                case 126:
                case 128:
                case 130:
                case 148:
                case 149:
                case 150:
                case 151:
                case 152:
                    this.pop();
                case 116:
                case 136:
                case 139:
                case 142:
                case 145:
                case 146:
                case 147:
                case 190:
                case 193:
                    this.pop();
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 16:
                case 17:
                case 21:
                case 26:
                case 27:
                case 28:
                case 29:
                    this.push(1);
                    break;
                case 47:
                case 97:
                case 101:
                case 105:
                case 109:
                case 113:
                case 121:
                case 123:
                case 125:
                case 127:
                case 129:
                case 131:
                    this.pop();
                case 117:
                case 133:
                case 140:
                case 143:
                    this.pop();
                case 9:
                case 10:
                case 22:
                case 30:
                case 31:
                case 32:
                case 33:
                    this.push(4);
                    break;
                case 48:
                case 98:
                case 102:
                case 106:
                case 110:
                case 114:
                    this.pop();
                case 118:
                case 134:
                case 137:
                case 144:
                    this.pop();
                case 11:
                case 12:
                case 13:
                case 23:
                case 34:
                case 35:
                case 36:
                case 37:
                    this.push(2);
                    break;
                case 49:
                case 99:
                case 103:
                case 107:
                case 111:
                case 115:
                    this.pop();
                case 119:
                case 135:
                case 138:
                case 141:
                    this.pop();
                case 14:
                case 15:
                case 24:
                case 38:
                case 39:
                case 40:
                case 41:
                    this.push(3);
                    break;
                case 50:
                    {
                        this.pop();
                        int typeIndex = this.pop() >>> 8;
                        String className = (String) ClassFileWriter.this.itsConstantPool.getConstantData(typeIndex);
                        if (className.charAt(0) != '[') {
                            throw new IllegalStateException("bad array type");
                        }
                        String elementDesc = className.substring(1);
                        String elementType = ClassFileWriter.descriptorToInternalName(elementDesc);
                        int var55 = ClassFileWriter.this.itsConstantPool.addClass(elementType);
                        this.push(TypeInfo.OBJECT(var55));
                        break;
                    }
                case 54:
                    this.executeStore(this.getOperand(bci + 1, this.wide ? 2 : 1), 1);
                    break;
                case 55:
                    this.executeStore(this.getOperand(bci + 1, this.wide ? 2 : 1), 4);
                    break;
                case 56:
                    this.executeStore(this.getOperand(bci + 1, this.wide ? 2 : 1), 2);
                    break;
                case 57:
                    this.executeStore(this.getOperand(bci + 1, this.wide ? 2 : 1), 3);
                    break;
                case 58:
                    this.executeAStore(this.getOperand(bci + 1, this.wide ? 2 : 1));
                    break;
                case 59:
                case 60:
                case 61:
                case 62:
                    this.executeStore(bc - 59, 1);
                    break;
                case 63:
                case 64:
                case 65:
                case 66:
                    this.executeStore(bc - 63, 4);
                    break;
                case 67:
                case 68:
                case 69:
                case 70:
                    this.executeStore(bc - 67, 2);
                    break;
                case 71:
                case 72:
                case 73:
                case 74:
                    this.executeStore(bc - 71, 3);
                    break;
                case 75:
                case 76:
                case 77:
                case 78:
                    this.executeAStore(bc - 75);
                    break;
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                    this.pop();
                case 159:
                case 160:
                case 161:
                case 162:
                case 163:
                case 164:
                case 165:
                case 166:
                case 181:
                    this.pop();
                case 87:
                case 153:
                case 154:
                case 155:
                case 156:
                case 157:
                case 158:
                case 179:
                case 194:
                case 195:
                case 198:
                case 199:
                    this.pop();
                    break;
                case 88:
                    this.pop2();
                    break;
                case 89:
                    {
                        int type = this.pop();
                        this.push(type);
                        this.push(type);
                        break;
                    }
                case 90:
                    {
                        int type = this.pop();
                        int type2 = this.pop();
                        this.push(type);
                        this.push(type2);
                        this.push(type);
                        break;
                    }
                case 91:
                    {
                        int type = this.pop();
                        long lType = this.pop2();
                        this.push(type);
                        this.push2(lType);
                        this.push(type);
                        break;
                    }
                case 92:
                    {
                        long lType = this.pop2();
                        this.push2(lType);
                        this.push2(lType);
                        break;
                    }
                case 93:
                    {
                        long lType = this.pop2();
                        int type = this.pop();
                        this.push2(lType);
                        this.push(type);
                        this.push2(lType);
                        break;
                    }
                case 94:
                    {
                        long lType = this.pop2();
                        long lType2 = this.pop2();
                        this.push2(lType);
                        this.push2(lType2);
                        this.push2(lType);
                        break;
                    }
                case 95:
                    {
                        int type = this.pop();
                        int type2 = this.pop();
                        this.push(type);
                        this.push(type2);
                        break;
                    }
                case 168:
                case 169:
                case 171:
                case 197:
                case 201:
                default:
                    throw new IllegalArgumentException("bad opcode: " + bc);
                case 170:
                    int switchStart = bci + 1 + (3 & ~bci);
                    int low = this.getOperand(switchStart + 4, 4);
                    int high = this.getOperand(switchStart + 8, 4);
                    length = 4 * (high - low + 4) + switchStart - bci;
                    this.pop();
                    break;
                case 172:
                case 173:
                case 174:
                case 175:
                case 176:
                case 177:
                    this.clearStack();
                    break;
                case 180:
                    this.pop();
                case 178:
                    {
                        int index = this.getOperand(bci + 1, 2);
                        FieldOrMethodRef f = (FieldOrMethodRef) ClassFileWriter.this.itsConstantPool.getConstantData(index);
                        String fieldType = ClassFileWriter.descriptorToInternalName(f.getType());
                        this.push(TypeInfo.fromType(fieldType, ClassFileWriter.this.itsConstantPool));
                        break;
                    }
                case 182:
                case 183:
                case 184:
                case 185:
                    {
                        int index = this.getOperand(bci + 1, 2);
                        FieldOrMethodRef m = (FieldOrMethodRef) ClassFileWriter.this.itsConstantPool.getConstantData(index);
                        String methodType = m.getType();
                        String methodName = m.getName();
                        int parameterCount = ClassFileWriter.sizeOfParameters(methodType) >>> 16;
                        for (int i = 0; i < parameterCount; i++) {
                            this.pop();
                        }
                        if (bc != 184) {
                            int instType = this.pop();
                            int tag = TypeInfo.getTag(instType);
                            if (tag == TypeInfo.UNINITIALIZED_VARIABLE(0) || tag == 6) {
                                if (!"<init>".equals(methodName)) {
                                    throw new IllegalStateException("bad instance");
                                }
                                int newType = TypeInfo.OBJECT(ClassFileWriter.this.itsThisClassIndex);
                                this.initializeTypeInfo(instType, newType);
                            }
                        }
                        int rParen = methodType.indexOf(41);
                        String returnType = methodType.substring(rParen + 1);
                        returnType = ClassFileWriter.descriptorToInternalName(returnType);
                        if (!returnType.equals("V")) {
                            this.push(TypeInfo.fromType(returnType, ClassFileWriter.this.itsConstantPool));
                        }
                        break;
                    }
                case 186:
                    {
                        int index = this.getOperand(bci + 1, 2);
                        String methodType = (String) ClassFileWriter.this.itsConstantPool.getConstantData(index);
                        int parameterCount = ClassFileWriter.sizeOfParameters(methodType) >>> 16;
                        for (int i = 0; i < parameterCount; i++) {
                            this.pop();
                        }
                        int rParen = methodType.indexOf(41);
                        String returnType = methodType.substring(rParen + 1);
                        returnType = ClassFileWriter.descriptorToInternalName(returnType);
                        if (!returnType.equals("V")) {
                            this.push(TypeInfo.fromType(returnType, ClassFileWriter.this.itsConstantPool));
                        }
                        break;
                    }
                case 187:
                    this.push(TypeInfo.UNINITIALIZED_VARIABLE(bci));
                    break;
                case 188:
                    {
                        this.pop();
                        char componentType = ClassFileWriter.arrayTypeToName(ClassFileWriter.this.itsCodeBuffer[bci + 1]);
                        int index = ClassFileWriter.this.itsConstantPool.addClass("[" + componentType);
                        this.push(TypeInfo.OBJECT((short) index));
                        break;
                    }
                case 189:
                    {
                        int index = this.getOperand(bci + 1, 2);
                        String className = (String) ClassFileWriter.this.itsConstantPool.getConstantData(index);
                        this.pop();
                        this.push(TypeInfo.OBJECT("[L" + className + ";", ClassFileWriter.this.itsConstantPool));
                        break;
                    }
                case 191:
                    {
                        int type = this.pop();
                        this.clearStack();
                        this.push(type);
                        break;
                    }
                case 192:
                    this.pop();
                    this.push(TypeInfo.OBJECT(this.getOperand(bci + 1, 2)));
                    break;
                case 196:
                    this.wide = true;
            }
            if (length == 0) {
                length = ClassFileWriter.opcodeLength(bc, this.wide);
            }
            if (this.wide && bc != 196) {
                this.wide = false;
            }
            return length;
        }

        private void executeALoad(int localIndex) {
            int type = this.getLocal(localIndex);
            int tag = TypeInfo.getTag(type);
            if (tag != 7 && tag != 6 && tag != 8 && tag != 5) {
                throw new IllegalStateException("bad local variable type: " + type + " at index: " + localIndex);
            } else {
                this.push(type);
            }
        }

        private void executeAStore(int localIndex) {
            this.setLocal(localIndex, this.pop());
        }

        private void executeStore(int localIndex, int typeInfo) {
            this.pop();
            this.setLocal(localIndex, typeInfo);
        }

        private void initializeTypeInfo(int prevType, int newType) {
            this.initializeTypeInfo(prevType, newType, this.locals, this.localsTop);
            this.initializeTypeInfo(prevType, newType, this.stack, this.stackTop);
        }

        private void initializeTypeInfo(int prevType, int newType, int[] data, int dataTop) {
            for (int i = 0; i < dataTop; i++) {
                if (data[i] == prevType) {
                    data[i] = newType;
                }
            }
        }

        private int getLocal(int localIndex) {
            return localIndex < this.localsTop ? this.locals[localIndex] : 0;
        }

        private void setLocal(int localIndex, int typeInfo) {
            if (localIndex >= this.localsTop) {
                int[] tmp = new int[localIndex + 1];
                System.arraycopy(this.locals, 0, tmp, 0, this.localsTop);
                this.locals = tmp;
                this.localsTop = localIndex + 1;
            }
            this.locals[localIndex] = typeInfo;
        }

        private void push(int typeInfo) {
            if (this.stackTop == this.stack.length) {
                int[] tmp = new int[Math.max(this.stackTop * 2, 4)];
                System.arraycopy(this.stack, 0, tmp, 0, this.stackTop);
                this.stack = tmp;
            }
            this.stack[this.stackTop++] = typeInfo;
        }

        private int pop() {
            return this.stack[--this.stackTop];
        }

        private void push2(long typeInfo) {
            this.push((int) (typeInfo & 16777215L));
            typeInfo >>>= 32;
            if (typeInfo != 0L) {
                this.push((int) (typeInfo & 16777215L));
            }
        }

        private long pop2() {
            long type = (long) this.pop();
            return TypeInfo.isTwoWords((int) type) ? type : type << 32 | (long) (this.pop() & 16777215);
        }

        private void clearStack() {
            this.stackTop = 0;
        }

        int computeWriteSize() {
            int writeSize = this.getWorstCaseWriteSize();
            this.rawStackMap = new byte[writeSize];
            this.computeRawStackMap();
            return this.rawStackMapTop + 2;
        }

        int write(byte[] data, int offset) {
            offset = ClassFileWriter.putInt32(this.rawStackMapTop + 2, data, offset);
            offset = ClassFileWriter.putInt16(this.superBlocks.length - 1, data, offset);
            System.arraycopy(this.rawStackMap, 0, data, offset, this.rawStackMapTop);
            return offset + this.rawStackMapTop;
        }

        private void computeRawStackMap() {
            SuperBlock prev = this.superBlocks[0];
            int[] prevLocals = prev.getTrimmedLocals();
            int prevOffset = -1;
            for (int i = 1; i < this.superBlocks.length; i++) {
                SuperBlock current = this.superBlocks[i];
                int[] currentLocals = current.getTrimmedLocals();
                int[] currentStack = current.getStack();
                int offsetDelta = current.getStart() - prevOffset - 1;
                if (currentStack.length != 0) {
                    if (currentStack.length == 1) {
                        if (Arrays.equals(prevLocals, currentLocals)) {
                            this.writeSameLocalsOneStackItemFrame(currentStack, offsetDelta);
                        } else {
                            this.writeFullFrame(currentLocals, currentStack, offsetDelta);
                        }
                    } else {
                        this.writeFullFrame(currentLocals, currentStack, offsetDelta);
                    }
                } else {
                    int last = prevLocals.length > currentLocals.length ? currentLocals.length : prevLocals.length;
                    int delta = Math.abs(prevLocals.length - currentLocals.length);
                    int j = 0;
                    while (j < last && prevLocals[j] == currentLocals[j]) {
                        j++;
                    }
                    if (j == currentLocals.length && delta == 0) {
                        this.writeSameFrame(offsetDelta);
                    } else if (j == currentLocals.length && delta <= 3) {
                        this.writeChopFrame(delta, offsetDelta);
                    } else if (j == prevLocals.length && delta <= 3) {
                        this.writeAppendFrame(currentLocals, delta, offsetDelta);
                    } else {
                        this.writeFullFrame(currentLocals, currentStack, offsetDelta);
                    }
                }
                prevLocals = currentLocals;
                prevOffset = current.getStart();
            }
        }

        private int getWorstCaseWriteSize() {
            return (this.superBlocks.length - 1) * (7 + ClassFileWriter.this.itsMaxLocals * 3 + ClassFileWriter.this.itsMaxStack * 3);
        }

        private void writeSameFrame(int offsetDelta) {
            if (offsetDelta <= 63) {
                this.rawStackMap[this.rawStackMapTop++] = (byte) offsetDelta;
            } else {
                this.rawStackMap[this.rawStackMapTop++] = -5;
                this.rawStackMapTop = ClassFileWriter.putInt16(offsetDelta, this.rawStackMap, this.rawStackMapTop);
            }
        }

        private void writeSameLocalsOneStackItemFrame(int[] stack, int offsetDelta) {
            if (offsetDelta <= 63) {
                this.rawStackMap[this.rawStackMapTop++] = (byte) (64 + offsetDelta);
            } else {
                this.rawStackMap[this.rawStackMapTop++] = -9;
                this.rawStackMapTop = ClassFileWriter.putInt16(offsetDelta, this.rawStackMap, this.rawStackMapTop);
            }
            this.writeType(stack[0]);
        }

        private void writeFullFrame(int[] locals, int[] stack, int offsetDelta) {
            this.rawStackMap[this.rawStackMapTop++] = -1;
            this.rawStackMapTop = ClassFileWriter.putInt16(offsetDelta, this.rawStackMap, this.rawStackMapTop);
            this.rawStackMapTop = ClassFileWriter.putInt16(locals.length, this.rawStackMap, this.rawStackMapTop);
            this.rawStackMapTop = this.writeTypes(locals);
            this.rawStackMapTop = ClassFileWriter.putInt16(stack.length, this.rawStackMap, this.rawStackMapTop);
            this.rawStackMapTop = this.writeTypes(stack);
        }

        private void writeAppendFrame(int[] locals, int localsDelta, int offsetDelta) {
            int start = locals.length - localsDelta;
            this.rawStackMap[this.rawStackMapTop++] = (byte) (251 + localsDelta);
            this.rawStackMapTop = ClassFileWriter.putInt16(offsetDelta, this.rawStackMap, this.rawStackMapTop);
            this.rawStackMapTop = this.writeTypes(locals, start);
        }

        private void writeChopFrame(int localsDelta, int offsetDelta) {
            this.rawStackMap[this.rawStackMapTop++] = (byte) (251 - localsDelta);
            this.rawStackMapTop = ClassFileWriter.putInt16(offsetDelta, this.rawStackMap, this.rawStackMapTop);
        }

        private int writeTypes(int[] types) {
            return this.writeTypes(types, 0);
        }

        private int writeTypes(int[] types, int start) {
            for (int i = start; i < types.length; i++) {
                this.rawStackMapTop = this.writeType(types[i]);
            }
            return this.rawStackMapTop;
        }

        private int writeType(int type) {
            int tag = type & 0xFF;
            this.rawStackMap[this.rawStackMapTop++] = (byte) tag;
            if (tag == 7 || tag == 8) {
                this.rawStackMapTop = ClassFileWriter.putInt16(type >>> 8, this.rawStackMap, this.rawStackMapTop);
            }
            return this.rawStackMapTop;
        }
    }
}