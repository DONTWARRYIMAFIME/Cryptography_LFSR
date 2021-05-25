package by.bsuir.cryptography.LFSR;

public class Main {
    public static void main(String[] args) {

        //x^30 + x^16 + x^15 + x + 1
        int registerLength = 30;
        int[] discharges = new int[] {30, 16, 15, 1};

        //x^4 + x + 1
//        int registerLength = 4;
//        int[] discharges = new int[] {4, 1};
        LFSR lfsr = new LFSR(registerLength, discharges);

        byte[] message = Utils.readFile("input.txt");

        byte[] encrypted = lfsr.encrypt(message);
        Utils.writeFile(encrypted, "encrypted.txt");
        byte[] decrypted = lfsr.decrypt(encrypted);
        Utils.writeFile(decrypted, "decrypted.txt");

        System.out.println("Encrypted bytes : " + encrypted);
        System.out.println("Decrypted bytes : " + decrypted);
        System.out.println("Converted to string :" + new String(decrypted));
    }
}
