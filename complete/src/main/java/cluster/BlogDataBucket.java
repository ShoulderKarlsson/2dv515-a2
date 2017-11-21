package cluster;

import java.util.ArrayList;
import java.util.Arrays;

class BlogDataBucket {
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<Blog> blogs = new ArrayList<>();

    BlogDataBucket(String fileContent) {
        storeBlogsData(fileContent);
    }

    private void storeBlogsData(String blogData) {
        String[] rows = blogData.split(System.getProperty("line.separator"));
        words.addAll(Arrays.asList(rows[0].split("\\s+")));

        // Starting on index 1 since first row is all the words (see above)
        for (int i = 1; i < rows.length; i++) {
            String[] row = rows[i].split("\t");
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
