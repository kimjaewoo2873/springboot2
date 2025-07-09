package com.example.firstproject.repository;

import com.example.firstproject.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(value = "SELECT * FROM team WHERE group_id = :groupId",nativeQuery = true)
    List<Team> findByTeam(Long groupId);
}
