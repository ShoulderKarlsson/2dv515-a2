package hello;

class Calculations {
    static double calc_Pearson(Blog a, Blog b) {
        double sumA = 0;
        double sumAsq = 0;
        for (Word w : a.blogWords) {
            sumA += w.count;
            sumAsq += Math.pow(w.count, 2);
        }

        double sumB = 0;
        double sumBsq = 0;
        for (Word w : b.blogWords) {
            sumB += w.count;
            sumBsq += Math.pow(w.count, 2);
        }

        double pSum = 0;
        int n = Math.min(a.blogWords.size(), b.blogWords.size());
        for (int i = 0; i < n; i++) {
            pSum += a.blogWords.get(i).count * b.blogWords.get(i).count;
        }

        double num = pSum - (sumA * sumB / n);
        double den = Math.sqrt((sumAsq - Math.pow(sumA, 2) / n) * (sumBsq - Math.pow(sumB, 2) / n));
        return den == 0
                ? 0
                : 1.0 - num / den;
    }
}
