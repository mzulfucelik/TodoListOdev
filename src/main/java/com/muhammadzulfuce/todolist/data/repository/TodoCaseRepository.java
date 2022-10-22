package com.muhammadzulfuce.todolist.data.repository;

import com.muhammadzulfuce.todolist.data.model.TodoCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoCaseRepository extends JpaRepository<TodoCase, Long> {
    List<TodoCase> getTodoCaseByTargetDateBetween(LocalDateTime from, LocalDateTime to);

    List<TodoCase> getTodoCaseByTargetDate(LocalDateTime date);

}
