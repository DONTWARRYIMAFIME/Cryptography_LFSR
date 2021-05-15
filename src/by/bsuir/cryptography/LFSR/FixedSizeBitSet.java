package by.bsuir.cryptography.LFSR;

import java.util.BitSet;
import java.util.stream.IntStream;

public class FixedSizeBitSet extends BitSet {
    private final int bits;

    public FixedSizeBitSet(final int bits) {
        super(bits);
        this.bits = bits;
    }

    public int getBits() {
        return bits;
    }

    public FixedSizeBitSet leftShift() {
        for (int i = 0; i < bits; i++) {
            this.set(i, this.get(i + 1));
        }
        this.set(bits, false);

        return this;
    }

    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder(bits);
        IntStream.range(0, bits).mapToObj(i -> get(i) ? '1' : '0').forEach(buffer::append);
        return buffer.toString();
    }
}
