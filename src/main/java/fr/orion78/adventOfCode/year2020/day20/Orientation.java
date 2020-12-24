package fr.orion78.adventOfCode.year2020.day20;

public enum Orientation {
    NORMAL_NORTH(0, false),
    NORMAL_EAST(1, false),
    NORMAL_SOUTH(2, false),
    NORMAL_WEST(3, false),
    FLIPPED_NORTH(0, true),
    FLIPPED_EAST(1, true),
    FLIPPED_SOUTH(2, true),
    FLIPPED_WEST(3, true);

    public static final Orientation[] NORMAL_ORIENTATIONS = {NORMAL_NORTH, NORMAL_EAST, NORMAL_SOUTH, NORMAL_WEST};
    public static final Orientation[] FLIPPED_ORIENTATIONS = {FLIPPED_NORTH, FLIPPED_EAST, FLIPPED_SOUTH, FLIPPED_WEST};

    private final int tabOffset;
    private final boolean flipped;

    Orientation(int tabOffset, boolean flipped) {
        this.tabOffset = tabOffset;
        this.flipped = flipped;
    }

    public int getTabOffset() {
        return tabOffset;
    }

    public boolean isFlipped() {
        return flipped;
    }
}
