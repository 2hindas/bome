package org.oopp.server.database;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Displays all users.
     */
    @GetMapping("/userid")
    public String getUserId(OAuth2Authentication authentication) {
        LinkedHashMap<String, Object> prop =
                (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();
        String id = (String) prop.get("id");
        return id;
    }

    /**
     * Checks if the user is already in the database.
     *
     * @param id of the wanted User.
     * @return true if user exists.
     */
    @GetMapping("/checkuser")
    public boolean checkUser(@RequestParam String id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns a specific user from database.
     *
     * @param id of the wanted User.
     * @return the wanted User.
     */
    @GetMapping("/getuser")
    public User getUser(@RequestParam String id) {
        User user = userRepository.findUserById(id);
        return user;
    }


    /**
     * Gets the id from Google and checks if such a user already exists:
     * if exists: finds the user in the database and returns it.
     * if does not exist: creates new user with the information from Google account and returns it.
     *
     * @param authentication from Google API
     * @return User object
     */
    @GetMapping("/newuser")
    public ResponseEntity<User> createUser(OAuth2Authentication authentication) {

        LinkedHashMap<String, Object> prop = (LinkedHashMap<String, Object>) authentication
                .getUserAuthentication().getDetails();
        String id = (String) prop.get("id");

        boolean answer = checkUser(id);

        if (answer == false) {
            System.out.println("Post request! (new User)");
            String fullName = (String) prop.get("name");
            String photo = (String) prop.get("picture");

            String email = (String) prop.get("email");
            // removing everything from the email after the character @ to make a username
            String username = email.substring(0, email.indexOf("@"));

            User user = new User(id, fullName, username, photo);
            userRepository.save(user);
            return ResponseEntity.accepted().body(user);
        } else {
            User user = getUser(id);
            return ResponseEntity.accepted().body(user);
        }

    }

    /**
     * Gets a Json string representing a User from an Android device
     * and checks if such a user already exists
     * if exists: finds the user in the database and returns it.
     * if does not exist: creates new user with the information from Google account and returns it.
     *
     * @param userJson from the Android device
     * @return User object
     */
    @PostMapping(value = "/newuserandroid")
    public ResponseEntity<User> createUserAndroid(@RequestBody String userJson) {
        System.out.println("Post request! (new User ANDROID)");

        Gson gson = new Gson();
        User user = gson.fromJson(userJson, User.class);

        boolean answer = checkUser(user.getUserId());

        if (!answer) {
            userRepository.save(user);
        }

        return ResponseEntity.accepted().body(user);

    }

    /**
     * Returns all the users with the given name.
     *
     * @param name name of the wanted user.
     * @return Array List of users.
     */
    @GetMapping("/getbyname")
    public ResponseEntity<ArrayList<User>> getUserByName(@RequestParam String name) {
        ArrayList<User> users = userRepository.findUserByName(name);
        return ResponseEntity.accepted().body(users);
    }

    /**
     * Return all the users with the given username.
     *
     * @param username username of the wanted user.
     * @return Array List of users.
     */
    @GetMapping("/getbyusername")
    public ResponseEntity<ArrayList<User>> getUserByUsername(@RequestParam String username) {
        ArrayList<User> users = userRepository.findUserByUsername(username);
        return ResponseEntity.accepted().body(users);
    }

    /**
     * Return all the users with the given username or name.
     * @param name name or username of the user.
     * @return Array List of users.
     */
    @GetMapping("/getbyboth")
    public ResponseEntity<ArrayList<User>> getUserByBoth(@RequestParam String name) {
        ArrayList<User> users = userRepository.findUserByBoth(name);
        return ResponseEntity.accepted().body(users);
    }

    /**
     * Updates a user in the database.
     *
     * @param user user to be updated.
     * @return updated user.
     */
    @PostMapping("/updateuser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.accepted().body(user);
    }

    /**
     * Updates a user received from an Android device in the database.
     *
     * @param userJson a Json string representing the user to be updated.
     * @return updated user.
     */
    @PostMapping("/updateuserandroid")
    public ResponseEntity<User> updateUserAndroid(@RequestBody String userJson) {
        Gson gson = new Gson();
        User user = gson.fromJson(userJson, User.class);
        userRepository.save(user);
        return ResponseEntity.accepted().body(user);
    }

}