import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.concurrent.atomic.AtomicReference;
@SuppressWarnings("MagicNumber")
public final class RunMe {
    private RunMe() {
    }
    public static void main(final String[] args) {
        final byte[] password = parseArgs(args);
        key0(password);
        key1(password);
        key2(password);
        key3(password);
        key4(password);
        key5(password);
        key6(password);
        key7(password);
        key8(password);
        key9(password);
        key10(password);
        key11(password);
        key12(password);
        key13(password);
        key14(password);
        key15(password);
        key16(password);
        key17(password);
    }
    private static void key0(final byte[] password) {
        print(0, 0, password);
    } //Done
    private static void key1(final byte[] password) {
            print(1, 7580342570284508233L, password);
    } //Done
    private static void key2(final byte[] password) {
        print(2, 3235749584985489343L, password);
    } //Done
    private static void key3(final byte[] password) {
        print(3, 1120108712, password);
    } //Done
    private static void key4(final byte[] password) {
        print(4, 7832471309040296545L, password);
    } //Done
    private static void key5(final byte[] password) {
        print(5, 471517925, password);
    } //Done
    private static void key6(final byte[] password) {
        print(6, 13420980980L, password);
    } //Done
    private static void key7(final byte[] password) {
        print(7, -1460169134, password);
    } //Done
    private static void key8(final byte[] password) {
        print(8, 33508, password);
    } //Done
    private static void key9(final byte[] password) {
        print(9, 1214327552, password);
    } //Done
    private static void key10(final byte[] password) {
        print(10, -536870912, password);
    } //Done
    private static void key11(final byte[] password) {
        print(11, 3498732498723948739L, password);
    } //Done
    private static void key12(final byte[] password) {
        final long PRIME = 1073741783;
        long result = 0;
        final long year = -2023;
        final long prime = PRIME + 4;
        for (long i = 0; ; i++) {
            long temp = year * i + prime;
            if (temp <= 0) break;
            result += i * password[(int) (i % password.length)];
        }
        print(12, result, password);
    } //Done
    private static void key13(final byte[] password) {
        final long MAX_DEPTH = 100000000L;
        long result = 0;
        for (long depth = 0; depth < MAX_DEPTH; depth++) {
            result = (result ^ 475934873) + (result << 2) + depth * 17;
        }
        print(13, result, password);
    } //Done
    private static void key14(final byte[] password) {
        final Instant today = Instant.parse("2023-09-04T12:00:00Z");
        final BigInteger hours = BigInteger.valueOf(Duration.between(Instant.EPOCH, today).toHours());
        final long result = -(hours.longValue() / 12);
        print(14, result, password);
    } //Done
    private static void key15(final byte[] password) {
        print(15, 2387498237498232333L + password[2], password);
    } //Done
    private static void key16(final byte[] password) {
        byte a1 = (byte) (password[0] + password[1]);
        byte a2 = (byte) (password[2] + password[3]);
        byte a3 = (byte) (password[4] + password[5]);
        int amount = 0;
        int firsta1 = a1;
        int firsta2 = a2;
        int firsta3 = a3;
        while (true) {
            a1 ^= a2;
            a2 += a3 | a1;
            a3 -= a1;
            amount++;
            if (a1 == firsta1 && a2 == firsta2 && a3 == firsta3) {
                break;
            }
        }
        long i = 1000000000000000L + ByteBuffer.wrap(password).getInt();
        long num = i % amount;
        for (long l = num; l >= 0; l--) {
            a1 ^= a2;
            a2 += a3 | a1;
            a3 -= a1;
        }
        final String result = a1 + " " + a2 + " " + a3;
        print(16, result.hashCode(), password);
    } //Done
    private static void key17(final byte[] password) {
        print(17, calc17(Math.abs(Arrays.toString(password).hashCode() % 2022)), password);
    } //Done
    private static int calc17(final int n) {
        int i = 0;
        while (true) {
            if (((i / 23) - n) >= 0) {
                return i;
            }
            i++;
        }
    }
    private static void print(final int no, long result, final byte[] password) {
        final byte[] key = password.clone();
        for (int i = 0; i < 6; i++) {
            key[i] ^= result;
            result >>>= 8;
        }
        System.out.format("Key %d: https://www.kgeorgiy.info/courses/prog-intro/hw1/%s%n", no, key(SALT, key));
    }
    private static String key(final byte[] salt, final byte[] data) {
        DIGEST.update(salt);
        DIGEST.update(data);
        DIGEST.update(salt);
        final byte[] digest = DIGEST.digest();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (i != 0) {
                sb.append("-");
            }
            sb.append(KEYWORDS.get(digest[i] & 63));
        }
        return sb.toString();
    }
    private static byte[] parseArgs(final String[] args) {
        if (args.length != 6) {
            throw error("Expected 6 command line arguments, found: %d", args.length);
        }
        final byte[] bytes = new byte[args.length];
        for (int i = 0; i < args.length; i++) {
            final Byte value = VALUES.get(args[i].toLowerCase(Locale.US));
            if (value == null) {
                throw error("Expected keyword, found: %s", args[i]);
            }
            bytes[i] = value;
        }
        return bytes;
    }
    private static AssertionError error(final String format, final Object... args) {
        System.err.format(format, args);
        System.err.println();
        System.exit(1);
        throw new AssertionError();
    }
    private static final MessageDigest DIGEST;
    static {
        try {
            DIGEST = MessageDigest.getInstance("SHA-256");
        } catch (final NoSuchAlgorithmException e) {
            throw new AssertionError("Cannot create SHA-256 digest", e);
        }
    }
    private static final byte[] SALT = "jig6`wriusoonBaspaf9TuRutabyikUch/Bleir3".getBytes(StandardCharsets.UTF_8);
    private static final List<String> KEYWORDS = List.of("abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "false", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "true", "try", "var", "void", "volatile", "while", "Exception", "Error", "Object", "Number", "Integer", "Character", "String", "Math", "Runtime", "Throwable");
    private static final Map<String, Byte> VALUES = IntStream.range(0, KEYWORDS.size()).boxed().collect(Collectors.toMap(index -> KEYWORDS.get(index).toLowerCase(Locale.US), Integer::byteValue));
}