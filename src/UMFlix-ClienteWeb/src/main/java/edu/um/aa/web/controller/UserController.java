package edu.um.aa.web.controller;

/**
 * Interface that defines the action of a UserController.
 */

public interface UserController {

    /**
     *registry(String userName,String password) registry a user in the application and returns a string with de html page code.
     * */

    String registry(String userName, String password);

    /**
     *login(String userName,String password) logs a user in the application and returns a string with de html page code.
     * */

    String login(String userName, String password);


    /**
     * showMovie() returns an array of bytes which represents the clip of a movie.
     */

    byte[] showMovie();

    /**
     * listByName(String sFindByName) search for movies by its name and returns a string with de html page code.
     */

    String listByName(String sFindByName);

    /**
     * listByActor(String sFindByActor) search for movies by its actors and returns a string with de html page code.
     */

    String listByActor(String sFindByActor);

    /**
     * listByYear(String sFindByYear) search for movies by the year it was released and returns a string with de html page code.
     */

    String listByYear(String sFindByYear);

    /**
     * listByDirector(String sFindByDirector) search for movies by its director name and returns a string with de html page code.
     */

    String listByDirector(String sFindByDirector);
}