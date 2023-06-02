package com.wizeline.service;

import com.wizeline.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Description goes here.
 * Created by jose.vazquez on 01/06/23
 */

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public List<Character> getAllCharacters() {
        List<Character> characters = new ArrayList<>(characterRepository.findAll());
        return characters;

    }

}
