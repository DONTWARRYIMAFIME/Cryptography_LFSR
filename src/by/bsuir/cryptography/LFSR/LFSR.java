package by.bsuir.cryptography.LFSR;

import java.util.BitSet;

public class LFSR {

    private final int[] discharges;
    private final FixedSizeBitSet register;
    private final int registerLength;

    private boolean generateBit(FixedSizeBitSet set) {
        boolean result = set.get(0);

        for (int i = 1; i < discharges.length; i++) {
            boolean next = set.get(set.getBits() - discharges[i]);
            result = result ^ next;
        }

        return result;
    }



    private byte[] getBytes(byte[] encrypted) {
        register.set(0, registerLength);
        System.out.println("Register : " + register);

        int messageLength = encrypted.length * 8;
        FixedSizeBitSet tempSet = new FixedSizeBitSet(messageLength);
        tempSet.set(Utils.clamp(messageLength - registerLength, 0, messageLength), messageLength);

        System.out.println("TempSet  : " + tempSet);

        for (int i = registerLength; i < messageLength; i++) {
            boolean discharge = generateBit(register);
            register.leftShift().set(register.getBits() - 1, discharge);
            System.out.println("Register : " + register);
            tempSet.leftShift().set(tempSet.getBits() - 1, discharge);
            System.out.println("TempSet  : " + tempSet);
        }

        BitSet messageSet = BitSet.valueOf(encrypted);
        System.out.println("Message  : " + Utils.toBinaryString(messageSet));

        tempSet.xor(messageSet);
        System.out.println("After xor: " + tempSet);

        System.out.println();
        System.out.println();
        System.out.println();

        return tempSet.toByteArray();
    }

    public LFSR(int registerLength, int[] discharges) {
        this.registerLength = registerLength;
        this.discharges = discharges;

        register = new FixedSizeBitSet(registerLength);
    }

    public byte[] encrypt(byte[] message) {
        return getBytes(message);
    }

    public byte[] decrypt(byte[] encrypted) {
        return getBytes(encrypted);
    }

}
