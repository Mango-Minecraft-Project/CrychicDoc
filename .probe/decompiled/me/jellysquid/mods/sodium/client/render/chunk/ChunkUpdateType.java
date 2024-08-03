package me.jellysquid.mods.sodium.client.render.chunk;

public enum ChunkUpdateType {

    INITIAL_BUILD(128), SORT(Integer.MAX_VALUE), IMPORTANT_SORT(Integer.MAX_VALUE), REBUILD(Integer.MAX_VALUE), IMPORTANT_REBUILD(Integer.MAX_VALUE);

    private final int maximumQueueSize;

    private ChunkUpdateType(int maximumQueueSize) {
        this.maximumQueueSize = maximumQueueSize;
    }

    @Deprecated
    public static boolean canPromote(ChunkUpdateType prev, ChunkUpdateType next) {
        return prev == null || prev == REBUILD && next == IMPORTANT_REBUILD;
    }

    public static ChunkUpdateType getPromotionUpdateType(ChunkUpdateType prev, ChunkUpdateType next) {
        if (prev == next) {
            return null;
        } else if (prev != null && prev != SORT) {
            return next != IMPORTANT_REBUILD && (prev != IMPORTANT_SORT || next != REBUILD) && (prev != REBUILD || next != IMPORTANT_SORT) ? null : IMPORTANT_REBUILD;
        } else {
            return next;
        }
    }

    public int getMaximumQueueSize() {
        return this.maximumQueueSize;
    }

    public boolean isImportant() {
        return this == IMPORTANT_REBUILD || this == IMPORTANT_SORT;
    }

    public boolean isSort() {
        return this == SORT || this == IMPORTANT_SORT;
    }
}