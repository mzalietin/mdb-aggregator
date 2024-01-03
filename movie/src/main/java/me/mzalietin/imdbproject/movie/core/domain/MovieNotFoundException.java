package me.mzalietin.imdbproject.movie.core.domain;

public class MovieNotFoundException extends RuntimeException {
    private final String id;
    private final String name;

    private MovieNotFoundException(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public static MovieNotFoundException byId(String id) {
        return new MovieNotFoundException(id, null);
    }

    public static MovieNotFoundException byName(String name) {
        return new MovieNotFoundException(null, name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
