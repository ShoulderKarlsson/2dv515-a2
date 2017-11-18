import java.util.ArrayList;

class Calculations {

    /**
     * Code from the third lecture
     */
    static double calcPearson3(Blog blogOne, Blog blogTwo) {
        double blogOneSum = 0;
        double blogOneSumSquared = 0;
        double blogTwoSum = 0;
        double blogTwoSumSquared = 0;

        for (Word w : blogOne.blogWords) {
            blogOneSum += w.getCount();
            blogOneSumSquared += Math.pow(w.getCount(), 2);
        }

        for (Word w : blogTwo.blogWords) {
            blogTwoSum += w.getCount();
            blogTwoSumSquared += Math.pow(w.getCount(), 2);
        }

        ArrayList<Word> blogOneWords = blogOne.blogWords;
        ArrayList<Word> blogTwoWords = blogOne.blogWords;

        int n = Math.min(blogOneWords.size(), blogTwoWords.size());
        double pSum = 0;
        for (int i = 0; i < n; i++) {
            pSum += blogOneWords.get(i).getCount() * blogTwoWords.get(i).getCount();
        }

        double num = pSum - (blogOneSum * blogTwoSum / n);
        double den = Math.sqrt(
                (blogOneSumSquared - Math.pow(blogOneSum, 2) / n) *
                (blogTwoSumSquared - Math.pow(blogTwoSum, 2) / n)
        );

        if (den == 0) return 0;

        return 1.0 - num / den;
    }

    static double calcPearson2() {

        return 1.0;
    }
}
