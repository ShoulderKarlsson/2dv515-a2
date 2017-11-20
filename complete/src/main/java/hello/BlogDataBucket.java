package hello;

import java.util.ArrayList;
import java.util.Arrays;

class BlogDataBucket {


    // Holds all the words that are present in the blog data set.
    private ArrayList<String> words = new ArrayList<>();

    // Bucket for all the blogs with all the words found in the blgo
    private ArrayList<Blog> blogs = new ArrayList<>();

    BlogDataBucket(String fileContent) {
        storeBlogsData(fileContent);
    }

    private void storeBlogsData(String blogData) {
        String[] rows = blogData.split(System.getProperty("line.separator"));

        // Adding first "row" of the data set, this "line" of the
        // dataset contains each word that are persent in the set.
        words.addAll(Arrays.asList(rows[0].split("\\s+")));

        // Starting on index 1 since first row is all the words (see above)
        for (int i = 1; i < rows.length; i++) {
            // Splitting on \t since each word count are separated by \t
            String[] row = rows[i].split("\t");

            // first occurrence of row is name of blog
            blogs.add(new Blog(row[0]));

            Blog blog = blogs.get(i - 1);
            for (int y = 1; y < row.length; y++) {
                blog.blogWords.add(new Word(words.get(y), Integer.parseInt(row[y])));
            }
        }
    }

    ArrayList<Blog> getBlogDataBucket() {
        return new ArrayList<>(blogs);
    }
}
