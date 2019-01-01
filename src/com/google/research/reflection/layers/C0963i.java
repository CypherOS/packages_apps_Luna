package com.google.research.reflection.layers;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.google.research.reflection.layers.i */
public class C0963i {
    /* renamed from: fi */
    static C0963i f341fi;
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

    /* renamed from: com.google.research.reflection.layers.i$a */
    private static class C0962a implements Callable<Boolean> {
        /* renamed from: fl */
        int f339fl;
        /* renamed from: fm */
        C0961h f340fm;
        int max;
        private int taskId;

        public /* synthetic */ Object call() throws Exception {
            int min = Math.min(this.max, (this.taskId + 1) * this.f339fl);
            for (int i = this.f339fl * this.taskId; i < min; i++) {
                this.f340fm.mo9243b(i);
            }
            return Boolean.valueOf(true);
        }

        public C0962a(int i, int i2, int i3, C0961h c0961h) {
            this.taskId = i;
            this.f339fl = i2;
            this.max = i3;
            this.f340fm = c0961h;
        }
    }

    private C0963i() {
    }

    /* renamed from: ak */
    public static C0963i m3067ak() {
        if (f341fi == null) {
            f341fi = new C0963i();
        }
        return f341fi;
    }

    /* renamed from: a */
    public final void mo9254a(int i, C0961h c0961h) throws InvalidValueException {
        if (f342fj && !this.f345fh) {
            if (i != 1) {
                m3068b(i, c0961h);
                return;
            }
        }
        for (int i2 = 0; i2 < i; i2++) {
            c0961h.mo9243b(i2);
        }
    }

    /* renamed from: b */
    private synchronized void m3068b(int i, C0961h c0961h) {
        int i2 = 1;
        this.f345fh = true;
        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(this.f344fg);
        if (i >= this.f343ff) {
            i2 = (int) Math.ceil((double) (((float) i) / ((float) this.f343ff)));
        }
        this.f346fk = Math.min(this.f343ff, i);
        for (int i3 = 0; i3 < this.f346fk; i3++) {
            executorCompletionService.submit(new C0962a(i3, i2, i, c0961h));
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
