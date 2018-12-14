package com.android.launcher3.reflection.research.layers;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.android.launcher3.reflection.research.layers.i */
public class C0959i {
    /* renamed from: fi */
    static C0959i f341fi;
    /* renamed from: fj */
    public static boolean f342fj;
    /* renamed from: ff */
    private int f343ff = (Runtime.getRuntime().availableProcessors() / 2);
    /* renamed from: fg */
    private ExecutorService f344fg = Executors.newFixedThreadPool(this.f343ff);
    /* renamed from: fh */
    private boolean f345fh = false;
    /* renamed from: fk */
    private int f346fk;

    /* renamed from: com.android.launcher3.reflection.research.layers.i$a */
    private static class C0958a implements Callable<Boolean> {
        /* renamed from: fl */
        int f339fl;
        /* renamed from: fm */
        C0957h f340fm;
        int max;
        private int taskId;

        public /* synthetic */ Object call() throws Exception {
            int min = Math.min(this.max, (this.taskId + 1) * this.f339fl);
            for (int i = this.f339fl * this.taskId; i < min; i++) {
                this.f340fm.mo9225b(i);
            }
            return Boolean.valueOf(true);
        }

        public C0958a(int i, int i2, int i3, C0957h c0957h) {
            this.taskId = i;
            this.f339fl = i2;
            this.max = i3;
            this.f340fm = c0957h;
        }
    }

    private C0959i() {
    }

    /* renamed from: ak */
    public static C0959i m3052ak() {
        if (f341fi == null) {
            f341fi = new C0959i();
        }
        return f341fi;
    }

    /* renamed from: a */
    public final void mo9236a(int i, C0957h c0957h) throws InvalidValueException {
        if (f342fj && !this.f345fh) {
            if (i != 1) {
                m3053b(i, c0957h);
                return;
            }
        }
        for (int i2 = 0; i2 < i; i2++) {
            c0957h.mo9225b(i2);
        }
    }

    /* renamed from: b */
    private synchronized void m3053b(int i, C0957h c0957h) {
        int i2 = 1;
        this.f345fh = true;
        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(this.f344fg);
        if (i >= this.f343ff) {
            i2 = (int) Math.ceil((double) (((float) i) / ((float) this.f343ff)));
        }
        this.f346fk = Math.min(this.f343ff, i);
        for (int i3 = 0; i3 < this.f346fk; i3++) {
            executorCompletionService.submit(new C0958a(i3, i2, i, c0957h));
        }
        for (int i4 = 0; i4 < this.f346fk; i4++) {
            try {
                executorCompletionService.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e2) {
                PrintStream printStream = System.err;
                int i5 = this.f346fk;
                StringBuilder sb = new StringBuilder(48);
                sb.append("threadCount: ");
                sb.append(i5);
                sb.append(" for length: ");
                sb.append(i);
                printStream.println(sb.toString());
                e2.printStackTrace();
            }
        }
        this.f345fh = false;
    }
}
