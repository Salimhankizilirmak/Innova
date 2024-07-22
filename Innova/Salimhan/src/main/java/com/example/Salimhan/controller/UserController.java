package com.example.Salimhan.controller;

import com.example.Salimhan.entities.User;
import com.example.Salimhan.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/kisiler")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<User>>getKisiler(){

        return new ResponseEntity<>(userService.getUsers(), OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<User> getUsers(@PathVariable Long id)
    {
        return new ResponseEntity<>(userService.getUserbyId(id), OK);

    }

    @PostMapping
    public ResponseEntity<User> createKisi(@RequestBody User newkisi){
        return new ResponseEntity<>(userService.createUser(newkisi), CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> getKisi(@PathVariable Long id,@RequestBody User newkisi){

        userService.UpdateUser(id, newkisi);
        return new ResponseEntity<>(OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKisi(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(OK);
    }
    private User getUserbyId(Long id){
        return   userService.getUserbyId(id);


    }
}
