import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;


public class MovieClient {
    //to check whether a string only have digit numbers
    public static boolean onlyDigit(String str) {
        boolean digit = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                digit = true;
            }
        }
        return digit;
    }

    //to check whether a string can be parsable to a double value
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //search the RedBlackBST by year
    public static HashSet<Movie> searchByYear(RedBlackBST<Integer, HashSet<Movie>> tree, int year) {
        HashSet<Movie> set = new HashSet<>();
        if ( tree.contains(year)) {
            set = tree.get(year);
        }
        return set;
    }
    //search the RedBlackBST by score
    public static HashSet<Movie> searchByScore(RedBlackBST<Double, HashSet<Movie>> tree, double score) {
        HashSet<Movie> set = new HashSet<>();
        if ( tree.contains(score)) {
            set = tree.get(score);
        }
        return set;
    }
    //search the RedBlackBST by string values
    public static HashSet<Movie> searchByString(RedBlackBST<String, HashSet<Movie>> tree, String str) {
        HashSet<Movie> set = new HashSet<>();
        if ( tree.contains(str)) {
            set = tree.get(str);
        }
        return set;
    }
    //to find the elements that appear in both two sets
    public static HashSet<Movie> intersection(HashSet<Movie> set1, HashSet<Movie> set2) {
        HashSet<Movie> newSet = new HashSet<>();
        for (Movie m: set1) {
            if ( set2.contains(m)) {
                newSet.add(m);
            }
        }
        return newSet;
    }


    public static void main(String[] args) throws Exception {
        File file = new File("movies.csv");
        Movie[] movies;
        Scanner input = new Scanner(file);
        input.nextLine();
        movies = new Movie[6000];

        //to create a Movie[]
        for (int i = 0; i < 6000 && input.hasNextLine(); i++) {
            String line = input.nextLine();
            String newLines = line.replaceAll(",,", ",-,");
            String[] words = newLines.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            int id = Integer.parseInt(words[0]);
            String color = words[1];
            String title = words[2];
            int duration;
            if ( onlyDigit(words[3])) {
                duration = Integer.parseInt(words[3]);
            } else {
                words[3] = "" + 0;
                duration = Integer.parseInt(words[3]);
            }
            String director_name = words[4];
            String act1 = words[5];
            String act2 = words[6];
            String act3 = words[7];
            String link = words[8];
            String language = words[9];
            String country = words[10];
            String rating = words[11];
            int year;
            if (onlyDigit(words[12])) {
                year = Integer.parseInt(words[12]);
            } else {
                words[12] = "" + 0;
                year = Integer.parseInt(words[12]);
            }
            double score = Double.parseDouble(words[13]);
            movies[i] = new Movie(id, color, title, duration, director_name, act1, act2, act3, link,
                    language, country, rating, year, score);
        }
        //find the number of the valid elements
        int count = 0;
        for ( Movie m: movies) {
            if ( m != null) {
                count++;
            }
        }
        //copy to a new movies array
        Movie[] newMovies = new Movie[count];
        for (int i = 0; i < newMovies.length; i++) {
            newMovies[i] = movies[i];
        }

        //create a SeparateChainingHashST object
        SeparateChainingHashST<String, RedBlackBST> MovieHash = new SeparateChainingHashST<>(10);
        String[] keys = {"year", "score", "rating", "language"};

        //create 4 RedBlackBST object
        RedBlackBST<Integer, HashSet<Movie>> yearTree = new RedBlackBST<>();
        RedBlackBST<Double, HashSet<Movie>> scoreTree = new RedBlackBST<>();
        RedBlackBST<String, HashSet<Movie>> ratingTree = new RedBlackBST<>();
        RedBlackBST<String, HashSet<Movie>> languageTree = new RedBlackBST<>();

        for (int i = 0; i < newMovies.length; i++) {
            HashSet<Movie> yearSet = new HashSet<>();
            HashSet<Movie> scoreSet = new HashSet<>();
            HashSet<Movie> ratingSet = new HashSet<>();
            HashSet<Movie> languageSet = new HashSet<>();

            for ( int k = 0; k < newMovies.length; k++) {
                if ( newMovies[k].getTitle_year() == newMovies[i].getTitle_year()) {
                    yearSet.add(newMovies[k]);
                }
                if ( newMovies[k].getImdb_score() == newMovies[i].getImdb_score()) {
                    scoreSet.add(newMovies[k]);
                }
                if ( newMovies[k].getRating().equals(newMovies[i].getRating())) {
                    ratingSet.add(newMovies[k]);
                }
                if ( newMovies[k].getLanguage().equals(newMovies[i].getLanguage())) {
                    languageSet.add(newMovies[k]);
                }
            }
            yearTree.put(newMovies[i].getTitle_year(), yearSet);
            scoreTree.put(newMovies[i].getImdb_score(), scoreSet);
            ratingTree.put(newMovies[i].getRating(), ratingSet);
            languageTree.put(newMovies[i].getLanguage(), languageSet);
        }

        //put the keys and values to the hash table
        MovieHash.put(keys[0], yearTree);
        MovieHash.put(keys[1], scoreTree);
        MovieHash.put(keys[2], ratingTree);
        MovieHash.put(keys[3], languageTree);


        HashSet<Movie> movieSet = new HashSet<>();

        //create a HashSet that contains every elements of Movies
        for (int i = 0; i < newMovies.length; i++) {
            movieSet.add(newMovies[i]);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Year: ");
        String year = sc.next();

        System.out.print("Score: ");
        String score = sc.next();

        System.out.print("Content_rating: ");
        String rating = sc.next();

        System.out.print("Language: ");
        String language = sc.next();
        System.out.println();

        int year2 = 0;
        if (onlyDigit(year)) {
            year2 = Integer.parseInt(year);
        }
        double score2 = 0;
        if (score != "-" && isDouble(score)) {
            score2 = Double.parseDouble(score);
        }

        //to find the intersection of each search result
        HashSet<Movie> resultByYear = new HashSet<>();
        HashSet<Movie> resultByScore = new HashSet<>();
        HashSet<Movie> resultByRating = new HashSet<>();
        HashSet<Movie> resultByLanguage = new HashSet<>();
        HashSet<Movie> result = new HashSet<>();

        if (!year.equals("-") ) {
            resultByYear = intersection(movieSet, searchByYear(MovieHash.get(keys[0]), year2));
            result = resultByYear;
        } else {
            resultByYear = movieSet;
        }

        if (!score.equals("-") ) {
            resultByScore = intersection(movieSet, searchByScore(MovieHash.get(keys[1]), score2));
            result = resultByScore;
        } else {
            resultByScore = movieSet;
        }

        if (!rating.equals("-") ) {
            resultByRating = intersection(movieSet, searchByString(MovieHash.get(keys[2]), rating));
            result = resultByRating;
        } else {
            resultByRating = movieSet;
        }

        if (!language.equals("-")) {
            resultByLanguage = intersection(movieSet, searchByString(MovieHash.get(keys[3]), language));
            result = resultByLanguage;
        } else {
            resultByLanguage = movieSet;
        }
        //list all kinds of situations
        if ( !year.equals("-") && !score.equals("-") && rating.equals("-") && language.equals("-")) {
            result = intersection(resultByYear, resultByScore);
        }
        if ( !year.equals("-") && score.equals("-") && !rating.equals("-") && language.equals("-")) {
            result = intersection(resultByYear, resultByRating);
        }
        if ( !year.equals("-") && score.equals("-") && rating.equals("-") && !language.equals("-")) {
            result = intersection(resultByYear, resultByLanguage);
        }
        if (year.equals("-") && !score.equals("-") && !rating.equals("-") && language.equals("-")) {
            result = intersection(resultByScore, resultByRating);
        }
        if (year.equals("-") && !score.equals("-") && rating.equals("-") && !language.equals("-")) {
            result = intersection(resultByScore, resultByLanguage);
        }
        if (year.equals("-") && score.equals("-") && !rating.equals("-") && !language.equals("-")) {
            result = intersection(resultByRating, resultByLanguage);
        }
        if (!year.equals("-") && !score.equals("-") && !rating.equals("-") && language.equals("-")) {
            result = intersection(intersection(resultByYear, resultByScore), resultByRating);
        }
        if (!year.equals("-") && !score.equals("-") && rating.equals("-") && !language.equals("-")) {
            result = intersection(intersection(resultByYear, resultByScore), resultByLanguage);
        }
        if (!year.equals("-") && score.equals("-") && !rating.equals("-") && !language.equals("-")) {
            result = intersection(intersection(resultByYear, resultByRating), resultByLanguage);
        }
        if (year.equals("-") && !score.equals("-") && !rating.equals("-") && !language.equals("-")) {
            result = intersection(intersection(resultByScore, resultByRating), resultByLanguage);
        }
        if (!year.equals("-") && !score.equals("-") && !rating.equals("-") && !language.equals("-")){
            result = intersection(intersection(resultByYear, resultByScore),
                    intersection(resultByRating, resultByLanguage));
        }
        if (year.equals("-") && score.equals("-") && rating.equals("-") && !language.equals("-")){
            result = movieSet;
        }

        //print out the result
        Movie[] result1 =result.toArray(new Movie[result.size()]);
        StringBuilder output = new StringBuilder();

        output.append(result1.length+ " movies are found"+ "\n");
        String[] s1 = {year, score, rating, language};
        String[] s2 = {"year", "score", "rating", "language"};
        output.append("Result(Movies -> ");
        for ( int i = 0; i < s1.length; i++) {
            if (!s1[i].equals("-")) {
                output.append(s2[i] + ":" + s1[i] + " ");
            }
        }
        output.deleteCharAt(output.toString().lastIndexOf(" "));
        output.append(")" + "\n");

        output.append("[");
        for ( int i = 0; i < result1.length -1; i++) {
            output.append(result1[i].getId() + ", ");
        }
        if (result1.length == 0) {
            output.append("]");
        }
        if ( result1.length >= 1) {
            output.append(result1[result1.length - 1].getId());
            output.append("]" + "\n");
            output.append("-----------------------------------------" + "\n");
        }

        for (int i = 0; i < result1.length;i++) {
            output.append(result1[i].toString());
            output.append("-----------------------------------------" + "\n");

        }
        System.out.println(output.toString());

    }
}
