package com.daimler.userbackend;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private List<User> users = new ArrayList();

    UserController(){
        this.users = buildUsers();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return this.users;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        Long nextId = 0L;
        if(this.users.size() != 0) {
            User lastUser = this.users.stream().skip(this.users.size() - 1).findFirst().orElse(null);
            nextId = lastUser.getId() + 1;
        }
        user.setId(nextId);
        this.users.add(user);
        return user;
    }

    List<User> buildUsers() {
        List<User> users = new ArrayList();
        User user1 = buildUser(1L, "John", "Doe", "john@email.com");
        User user2 = buildUser(2L, "Jon", "Smith", "smith@email.com");
        User user3 = buildUser(3L, "Will", "Craig", "will@email.com");
        User user4 = buildUser(4L, "Sam", "Lernorad", "sam@email.com");
        User user5 = buildUser(5L, "Ross", "Doe", "ross@email.com");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        return users;
    }

    User buildUser(Long id, String fname, String lname, String email) {
        User user = new User();
        user.setId(id);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setEmail(email);
        return user;
    }
}
