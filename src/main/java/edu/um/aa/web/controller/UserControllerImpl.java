package edu.um.aa.web.controller;

import com.umflix.movies.entities.Actor;
import com.umflix.movies.entities.Director;
import com.umflix.movies.entities.Genre;
import com.umflix.movies.entities.Movie;
import com.umflix.movies.exceptions.CantCreateException;
import com.umflix.movies.exceptions.NoMovieFoundException;
import com.umflix.movies.managers.MovieManager;
import com.umflix.movies.web.controllers.wrappers.CreateMovieRequest;
import edu.um.aa.umflix.entities.Token;
import edu.um.aa.umflix.entities.User;
import edu.um.aa.umflix.interfaces.UserMgt;
import edu.um.aa.umflix.persistence.DataBase;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user", produces = MediaType.TEXT_HTML_VALUE)
public class UserControllerImpl implements UserController {
   private UserMgt userMgt;
   private Token token ;
   private DataBase dataBase = DataBase.getDataBaseInstance();
   private MovieManager movieMgr;
   private List<Genre> genresBatman = new ArrayList<Genre>();
   private List<Genre> genresLordOfTheRings = new ArrayList<Genre>();
   private List<Genre> genresElZohan = new ArrayList<Genre>();
   private List<Genre> genresLasNueveReinas = new ArrayList<Genre>();
   private Genre ficcion = new Genre(1,"ficcion");
   private Genre comedia = new Genre(2,"comedia");
   private Genre suspenso = new Genre (3, "Suspenso");
   private Genre accion = new Genre (4, "accion");
   private Actor willSmith = new Actor(1,"Will Smith");
   private Actor ricardoDarin = new Actor(2,"Ricardo Darin");
   private Actor ianMcKellen = new Actor (3, "Ian McKellen");
   private Actor tomHanks = new Actor(4,"Tom Hanks");
   private Actor adamSandler = new Actor (5, "Adam Sandler");
   private Director stevenSpielberg = new Director(1,"Steven Spielberg");
   private Director clintEastwood = new Director(2,"Clint Eastwood");
   private Director peterJackson = new Director (3, "Peter Jackson");
   private Director dennisDugan = new Director (4, "Dennis Dugan");
   private Movie batman;
   private Movie lordOfTheRings;
   private Movie elZohan;
   private Movie lasNueveReinas;
    @Autowired
    public UserControllerImpl(UserMgt userMgt,MovieManager movieManager) {
        this.userMgt = userMgt;
        this.movieMgr = movieManager;
    }


    @Override
    @RequestMapping(path = "/registry", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registry(@RequestParam(name = "username") String userName,@RequestParam(name = "password") String password ) {
        try {
            userMgt.register(new User(userName,password));
            token = userMgt.logIn(userName,password);
            createMovies();
            movieMgr.createMovie(new CreateMovieRequest(batman,token));
            movieMgr.createMovie(new CreateMovieRequest(elZohan,token));
            movieMgr.createMovie(new CreateMovieRequest(lasNueveReinas,token));
            movieMgr.createMovie(new CreateMovieRequest(lordOfTheRings,token));
            String template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/buscador.html"));
            return template.replaceAll("UMFlix","UMFlix : "+userName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CantCreateException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @Override
    @RequestMapping(path = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(@RequestParam(name = "username") String userName,@RequestParam(name = "password") String password ) {
        String template;
        try {
           token = userMgt.logIn(userName,password);
            if(token == null) {
                template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/loginUsuarioNoEncontrado.html"));
                return template;
            }
           template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/buscador.html"));
           return template.replaceAll("UMFlix","UMFlix : "+userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @Override
    @RequestMapping(path = "/listByName", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String listByName(@RequestParam(name = "findMovieByName") String sFindByName) {
        List<Movie> movies = new ArrayList<Movie>();
        try {
            movies = movieMgr.findByName(sFindByName);
            String moviesToAppend = "";
            for(Movie movie : movies){
                String newMovieToAppend = "    <tr>\n" +
                        "        <td><a href=\"" + "http://localhost:8080/user/show?idMovie=" + movie.getId() + "\"> " + movie.getName() + "</a> </td>\n" +
                        "    </tr>\n";

                moviesToAppend = moviesToAppend + newMovieToAppend;
            }
            String template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/listaPeliculas.html"));
            return template.replaceAll("toReplace", moviesToAppend);
        } catch (NoMovieFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

      /*  for(int i =0; i < movieArray.length; i++){

            String newMovieToAppend = "    <tr>\n" +
                    "        <td><a href=\"" + "http://localhost:8080/user/show?idMovie=" + "1"  + "\"> " + movieArray[i] + "</a> </td>\n" +
                    "    </tr>\n";

            moviesToAppend = moviesToAppend + newMovieToAppend;
        }*/
        return " ";
    }

    @Override
    @RequestMapping(path = "/listByActor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String listByActor(@RequestParam(name = "findMovieByActor") String sFindByActor) {
        List<Movie> movies = new ArrayList<Movie>();
        try {
            movies = movieMgr.findByActor(sFindByActor);
            String moviesToAppend = "";
            for(Movie movie : movies){
                String newMovieToAppend = "    <tr>\n" +
                        "        <td><a href=\"" + "http://localhost:8080/user/show?idMovie=" + movie.getId() + "\"> " + movie.getName() + "</a> </td>\n" +
                        "    </tr>\n";

                moviesToAppend = moviesToAppend + newMovieToAppend;
            }
            String template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/listaPeliculas.html"));
            return template.replaceAll("toReplace", moviesToAppend);
        } catch (NoMovieFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @Override
    @RequestMapping(path = "/listByYear", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String listByYear(@RequestParam(name = "findMovieByYear") String sFindByYear) {
        List<Movie> movies = new ArrayList<Movie>();
        try {
            movies = movieMgr.findByYear(Integer.parseInt(sFindByYear));
            String moviesToAppend = "";
            for(Movie movie : movies){
                String newMovieToAppend = "    <tr>\n" +
                        "        <td><a href=\"" + "http://localhost:8080/user/show?idMovie=" + movie.getId() + "\"> " + movie.getName() + "</a> </td>\n" +
                        "    </tr>\n";

                moviesToAppend = moviesToAppend + newMovieToAppend;
            }
            String template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/listaPeliculas.html"));
            return template.replaceAll("toReplace", moviesToAppend);
        } catch (NoMovieFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @Override
    @RequestMapping(path = "/listByDirector", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String listByDirector(@RequestParam(name = "findMovieByDirector") String sFindByDirector) {
        List<Movie> movies = new ArrayList<Movie>();
        try {
            movies = movieMgr.findByDirector(sFindByDirector);
            String moviesToAppend = "";
            for(Movie movie : movies){
                String newMovieToAppend = "    <tr>\n" +
                        "        <td><a href=\"" + "http://localhost:8080/user/show?idMovie=" + movie.getId() + "\"> " + movie.getName() + "</a> </td>\n" +
                        "    </tr>\n";

                moviesToAppend = moviesToAppend + newMovieToAppend;
            }
            String template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/listaPeliculas.html"));
            return template.replaceAll("toReplace", moviesToAppend);
        } catch (NoMovieFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @Override
    @RequestMapping(path = "/show", method = RequestMethod.GET, produces = "video/mp4")
    public byte [] showMovie(@RequestParam(name="idMovie") String sId) {

        try {
            return IOUtils.toByteArray(UserControllerImpl.class.getResourceAsStream("/VideoPrueba.mp4"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createMovies(){
       genresBatman.add(accion);
       genresLordOfTheRings.add(ficcion);
       genresElZohan.add(comedia);
       genresLasNueveReinas.add(suspenso);
       batman = new Movie(1,"batman",tomHanks,2014,stevenSpielberg,genresBatman);
       lordOfTheRings = new Movie (2, "Lord of the rings", ianMcKellen, 2009, dennisDugan,genresLordOfTheRings);
       elZohan = new Movie(3,"El Zohan",adamSandler,2005,peterJackson,genresElZohan);
       lasNueveReinas = new Movie(4,"Las nueve reinas",ricardoDarin,2015,clintEastwood,genresLasNueveReinas);
    }
}
