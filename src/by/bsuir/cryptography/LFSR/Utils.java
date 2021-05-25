package by.bsuir.cryptography.LFSR;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.BitSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {
    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    public static String toBinaryString(BitSet bitSet) {
        if (bitSet == null) {
            return null;
        }
        return IntStream.range(0, bitSet.length())
                .mapToObj(b -> String.valueOf(bitSet.get(b) ? 1 : 0))
                .collect(Collectors.joining());
    }

    public static byte[] readFile(String path) {
        byte[] result = null;

        try (FileInputStream fin = new FileInputStream(path)){
            result = fin.readAllBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void writeFile(byte[] content, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath)){
            fos.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
