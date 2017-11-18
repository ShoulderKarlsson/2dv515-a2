import java.util.ArrayList;
import java.util.Random;

class Rand {
    private ArrayList<Double> randomCounts = new ArrayList<>();
    private Random r = new Random();

    Rand(ArrayList<Blog> blogs) {
        generateRandomSequence(blogs);
    }

    void generateRandomSequence(ArrayList<Blog> blogs) {
        for (int i = 0; i < blogs.size(); i++) {
            int low = 0;
            int high = 0;
            for (Blog blog : blogs) {
                int ocurence = (int) blog.blogWords.get(i).getCount();
                if (ocurence < low) low = ocurence;
                else if (ocurence > high) high = ocurence;
            }
            double random = r.nextInt(high - low) + low;

            // Not sure if this needs to be done?
            if (random == 0) random = 1;
            randomCounts.add(random);
        }
    }

    double getRandom() {
        return randomCounts.get(r.nextInt(randomCounts.size()));
    }
}
