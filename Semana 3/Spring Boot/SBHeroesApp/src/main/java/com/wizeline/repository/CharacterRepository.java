package com.wizeline.repository;

import com.wizeline.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class Description goes here.
 * Created by jose.vazquez on 01/06/23
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
}
