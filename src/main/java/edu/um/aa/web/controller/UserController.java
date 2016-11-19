package edu.um.aa.web.controller;

        public interface UserController {
    String registry(String userName, String password);
    String login(String userName, String password);
    byte[] showMovie(String sId);
    String listByName(String sFindByName);
    String listByActor(String sFindByActor);
    String listByYear(String sFindByYear);
    String listByDirector(String sFindByDirector);
}
