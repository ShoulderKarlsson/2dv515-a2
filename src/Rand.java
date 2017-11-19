import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class Rand {
    private Random r = new Random();

    private HashMap<String, OccurrenceStorage> occurence = new HashMap<>();


    Rand(ArrayList<Blog> blogs) {
        this.generateSpanSequence(blogs);
    }

    /**
     * Generating a HashMap<String, OccurrenceStorage> that includes the word and
     * the min amount of occurence and max amout of occurences in
     * all blogs.
     *
     * This is used to generate centroid "positions" inside
     * the accepted span for max and min values (?)
     */
    private void generateSpanSequence(ArrayList<Blog> blogs) {
        for (int i = 0; i < blogs.get(0).blogWords.size(); i++) {
            int low = 0;
            int high = 0;
            String w = "";
            for (Blog b : blogs) {
                int frequency = (int) b.blogWords.get(i).getCount();
                w = b.blogWords.get(i).getWord();
                if (frequency < low) low = frequency;
                else if (frequency > high) high = frequency;
            }

            occurence.put(w, new OccurrenceStorage(high, low));
        }
    }

    Centroid generateRandomCentroid() {
        Blog b = new Blog("");
        occurence.keySet()
                .forEach(key -> {
                    OccurrenceStorage span = occurence.get(key);
                    double rnd = r.nextInt((int) (span.max - span.low)) + span.low;
                    b.blogWords.add(new Word(key, rnd));
        });

        return new Centroid(b);
    }

    private class OccurrenceStorage {
        final double max;
        final double low;
        OccurrenceStorage(double max, double min) {
            this.max = max;
            this.low = min;
        }
    }
}
