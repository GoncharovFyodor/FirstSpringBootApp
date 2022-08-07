package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.entity.Level;
import com.example.firstspringbootapp.entity.Profile;
import com.example.firstspringbootapp.exception.LevelNotFoundException;
import com.example.firstspringbootapp.exception.ProfileNotFoundException;
import com.example.firstspringbootapp.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile findBy(Integer id) {
        return profileRepository.findById(id)
                .orElseThrow(()->new ProfileNotFoundException("Profile with id "+id+" not found"));
    }

    @Override
    public List<Profile> findAll() {
        List<Profile> profiles = profileRepository.findAll();
        if (profiles.isEmpty())
            throw new ProfileNotFoundException("No profiles found in database");
        return profiles;
    }

    @Override
    public void save(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public void update(Profile profile, Integer id) {
        Profile profileById = findBy(id);
        profileById.setName(profile.getName());
        profileRepository.save(profileById);
    }

    @Override
    public void delete(Integer id) {
        profileRepository.delete(findBy(id));
    }
}
