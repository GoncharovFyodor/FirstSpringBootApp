package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.entity.Profile;

import java.util.List;

public interface ProfileService {
    Profile findBy(Integer id);
    List<Profile> findAll();
    void save(Profile profile);
    void update(Profile profile,Integer id);
    void delete(Integer id);
}
