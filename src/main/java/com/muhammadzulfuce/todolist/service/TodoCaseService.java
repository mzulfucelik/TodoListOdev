package com.muhammadzulfuce.todolist.service;

import com.muhammadzulfuce.todolist.data.model.TodoCase;
import com.muhammadzulfuce.todolist.data.repository.TodoCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoCaseService {
    private TodoCaseRepository todoCaseRepository;

    @Autowired
    public TodoCaseService(TodoCaseRepository todoCaseRepository) {
        this.todoCaseRepository = todoCaseRepository;
    }

    public TodoCase getById(Long id) {
        return todoCaseRepository.getById(id);
    }

    public TodoCase saveNewTodoCase(TodoCase todoCase) {
        return todoCaseRepository.save(todoCase);
    }

    public TodoCase changeToDoStatus(Long id, Boolean isDone) {
        TodoCase entity = getById(id);
        entity.setDone(isDone);
        return saveNewTodoCase(entity);
    }

    public Boolean deleteTodoCase(Long id) {
        todoCaseRepository.delete(getById(id));
        return true;
    }

    public List<TodoCase> getToDoCaseBetween(LocalDate from, LocalDate to) {
        return todoCaseRepository.getTodoCaseByTargetDateBetween(from.atStartOfDay(), to.atTime(23, 59));
    }

    public List<TodoCase> getToDoCaseByDate(LocalDate date) {
        return todoCaseRepository.getTodoCaseByTargetDateBetween(date.atStartOfDay(), date.atStartOfDay().plusDays(1));
    }
}
