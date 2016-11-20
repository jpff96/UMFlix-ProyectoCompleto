package edu.um.aa.web.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/user", produces = MediaType.TEXT_HTML_VALUE)
public class UserControllerImpl implements UserController {


    @Override
    @RequestMapping(path = "/registry", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registry(String userName, String password) {
        try {
            String template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/buscador.html"));
            return template.replaceAll("UMFlix","UMFlix : "+userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @Override
    @RequestMapping(path = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(@RequestParam(name = "username") String userName,@RequestParam(name = "password") String password ) {
        try {
            String template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/buscador.html"));
            return template.replaceAll("UMFlix","UMFlix : "+userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @Override
    @RequestMapping(path = "/listByName", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String listByName(@RequestParam(name = "findMovieByName") String sFindByName) {
        String[] movieArray = {"Batman", "Deadpool", "Titanic", "Toy Story"};

        String moviesToAppend = "";

        for(int i =0; i < movieArray.length; i++){
            String newMovieToAppend = "    <tr>\n" +
                    "        <td><a href=\""+"http://localhost:8080/user/show"+" \">" + movieArray[i] + "</a> </td>\n" +
                    "    </tr>\n";

            moviesToAppend = moviesToAppend + newMovieToAppend;
        }

        try {
            String template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/listaPeliculas.html"));
            return template.replaceAll("toReplace", moviesToAppend);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @Override
    @RequestMapping(path = "/listByActor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String listByActor(@RequestParam(name = "findMovieByActor") String sFindByActor) {
        String[] movieArray = {"spiderman", "men in black", "Titanic", "kill bill"};

        String moviesToAppend = "";

        for(int i =0; i < movieArray.length; i++){

            String newMovieToAppend = "    <tr>\n" +
                    "        <td><a href=\" \">" + movieArray[i] + "</a> </td>\n" +
                    "    </tr>\n";

            moviesToAppend = moviesToAppend + newMovieToAppend;
        }

        try {
            String template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/listaPeliculas.html"));
            return template.replaceAll("toReplace", moviesToAppend);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @Override
    @RequestMapping(path = "/listByYear", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String listByYear(@RequestParam(name = "findMovieByYear") String sFindByYear) {
        String[] movieArray = {"superman", "alice in wonderland", "fast and furious"};

        String moviesToAppend = "";

        for(int i =0; i < movieArray.length; i++){

            String newMovieToAppend = "    <tr>\n" +
                    "        <td><a href=\" \">" + movieArray[i] + "</a> </td>\n" +
                    "    </tr>\n";

            moviesToAppend = moviesToAppend + newMovieToAppend;
        }

        try {
            String template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/listaPeliculas.html"));
            return template.replaceAll("toReplace", moviesToAppend);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @Override
    @RequestMapping(path = "/listByDirector", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String listByDirector(@RequestParam(name = "findMovieByDirector") String sFindByDirector) {
        String[] movieArray = {"breaking bad", "game of thrones", "how i met your mother", "home alone", "american history x"};

        String moviesToAppend = "";

        for(int i =0; i < movieArray.length; i++){

            String newMovieToAppend = "    <tr>\n" +
                    "        <td><a href=\" \">" + movieArray[i] + "</a> </td>\n" +
                    "    </tr>\n";

            moviesToAppend = moviesToAppend + newMovieToAppend;
        }

        try {
            String template = IOUtils.toString(UserControllerImpl.class.getResourceAsStream("/META-INF/resources/listaPeliculas.html"));
            return template.replaceAll("toReplace", moviesToAppend);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ";
    }

    @Override
    @RequestMapping(path = "/show", method = RequestMethod.GET, produces = "video/mp4")
    public byte [] showMovie() {

        try {
            return IOUtils.toByteArray(UserControllerImpl.class.getResourceAsStream("/VideoPrueba.mp4"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}