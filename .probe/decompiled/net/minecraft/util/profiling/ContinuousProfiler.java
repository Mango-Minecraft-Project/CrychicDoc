package net.minecraft.util.profiling;

import java.util.function.IntSupplier;
import java.util.function.LongSupplier;

public class ContinuousProfiler {

    private final LongSupplier realTime;

    private final IntSupplier tickCount;

    private ProfileCollector profiler = InactiveProfiler.INSTANCE;

    public ContinuousProfiler(LongSupplier longSupplier0, IntSupplier intSupplier1) {
        this.realTime = longSupplier0;
        this.tickCount = intSupplier1;
    }

    public boolean isEnabled() {
        return this.profiler != InactiveProfiler.INSTANCE;
    }

    public void disable() {
        this.profiler = InactiveProfiler.INSTANCE;
    }

    public void enable() {
        this.profiler = new ActiveProfiler(this.realTime, this.tickCount, true);
    }

    public ProfilerFiller getFiller() {
        return this.profiler;
    }

    public ProfileResults getResults() {
        return this.profiler.getResults();
    }
}