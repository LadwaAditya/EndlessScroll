package com.ladwa.aditya.moviesearch.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Aditya on 25-Mar-17.
 */

public class MovieResponse {

    @SerializedName("Search")
    private List<Movie> Movie;
    @SerializedName("totalResults")
    private String totalResults;
    @SerializedName("Response")
    private String Response;

    public List<Movie> getMovie() {
        return Movie;
    }

    public void setMovie(List<Movie> Movie) {
        this.Movie = Movie;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String Response) {
        this.Response = Response;
    }

    public static class Movie {
        @SerializedName("Title")
        private String Title;
        @SerializedName("Year")
        private String Year;
        @SerializedName("imdbID")
        private String imdbID;
        @SerializedName("Type")
        private String Type;
        @SerializedName("Poster")
        private String Poster;

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getYear() {
            return Year;
        }

        public void setYear(String Year) {
            this.Year = Year;
        }

        public String getImdbID() {
            return imdbID;
        }

        public void setImdbID(String imdbID) {
            this.imdbID = imdbID;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getPoster() {
            return Poster;
        }

        public void setPoster(String Poster) {
            this.Poster = Poster;
        }
    }
}
