package me.arnost.hashcode;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class Main {

    public static List<String> names = new ArrayList<String>();
    public static List<String> titles = new ArrayList<String>();


    public static void main(String[] args) throws Exception, InterruptedException {

        for (int i = 0; i < 150_000; i++) {
            names.add("Author " + i + " Name ");
            titles.add("Title of the book " + i);

        }

        ExecutorService executor = new DirectExecutor();

        long generatedTotal = 0;
        long java7Total = 0;
        long guavaTotal = 0;

        for (int i = 0; i < 10_000; i++) {
//            final Future generatedFuture = executor.submit(new BookGeneratedRunner());
//            generatedTotal += (Long)generatedFuture.get();
//
//            final Future java7future = executor.submit(new BookJava7Runner());
//            java7Total += (Long)java7future.get();

            final long generated = (Long) (new BookGeneratedRunner()).call();
            final long java7 = (Long) (new BookJava7Runner()).call();
            final long guava = (Long) (new BookGuavaRunner()).call();
            final int iterationsToSkip = 10;
            if (i > iterationsToSkip) {
                generatedTotal += generated;
                java7Total += java7;
                guavaTotal += guava;        

                System.out.println(i + " Generated total " + generatedTotal + " : " + totalOfAll(generatedTotal, java7Total, guavaTotal) + " %");
                System.out.println(i + " Java7 total     " + java7Total + " : " + totalOfAll(java7Total, generatedTotal, guavaTotal) + " %");
                System.out.println(i + " Guava total     " + guavaTotal + " : " + totalOfAll(guavaTotal, generatedTotal, java7Total) + " %");
            } else {
                System.out.println("No measurement yet... (" + i + "/" + iterationsToSkip+")");
            }



        }
    }

    private static long totalOfAll(long thisOne, long otherOne, long anotherOne) {
        return (thisOne * 100) / (thisOne + otherOne + anotherOne);
    }


    private abstract static class BookRunner implements Callable {
        int authorInd, titleInd;
        Set set = new HashSet<Object>();

        private void addToSet(Object o) {
            set.add(o);
        }

        @Override
        public final Object call() throws Exception {
            long start = System.nanoTime();
            final int loopLength = 1_150_000;
            for (int i = 0; i < loopLength; i++) {
                final Object nextItem = createNextItem();
                addToSet(nextItem);
            }
            long end = System.nanoTime();
            final long duration = end - start;
            System.out.println("Elapsed time : " + duration + " ns in " + getClass() + ", " + set.size() + " items, " + loopLength + " iterations");
            return duration;
        }

        protected abstract Object createNextItem();

        public final String nextAuthor() {
            authorInd++;
//            return "Author " + authorInd + " Name ";
            return Main.names.get(authorInd % Main.names.size());
        }

        public final String nextTitle() {
            titleInd++;
//            return "Title of the Book " + titleInd;
            return Main.titles.get(titleInd % Main.titles.size());
        }

        public final int nextPages() {
            return 500;
        }
    }

    private static class BookGeneratedRunner extends BookRunner {
        @Override
        protected Object createNextItem() {
            return new BookGenerated(nextAuthor(), nextTitle(), nextPages());
            //return new BookGenerated(nextAuthor() + "X", nextTitle() + "Y", nextPages());
        }
    }

    private static class BookJava7Runner extends BookRunner {
        @Override
        protected Object createNextItem() {
            return new BookJava7(nextAuthor(), nextTitle(), nextPages());
        }
    }

    private static class BookGuavaRunner extends BookRunner {
        @Override
        protected Object createNextItem() {
            return new BookGuava(nextAuthor(), nextTitle(), nextPages());
        }
    }
}
