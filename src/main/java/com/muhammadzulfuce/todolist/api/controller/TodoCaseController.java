package com.muhammadzulfuce.todolist.api.controller;

import com.muhammadzulfuce.todolist.data.model.TodoCase;
import com.muhammadzulfuce.todolist.service.TodoCaseService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/todo")
public class TodoCaseController {

    private TodoCaseService todoCaseService;

    @Autowired
    public TodoCaseController(TodoCaseService todoCaseService) {
        this.todoCaseService = todoCaseService;
    }

    @GetMapping(value = "/deleteById")
    public Boolean deleteTodo(@RequestParam("id") Long id) {
        return todoCaseService.deleteTodoCase(id);
    }

    @GetMapping("/listByDay")
    public List<TodoCase> listByDay(@RequestParam("date") String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return todoCaseService.getToDoCaseByDate(LocalDate.parse(date,formatter));
    }

    @GetMapping("/listByToday")
    public List<TodoCase> listByToday(){
        return todoCaseService.getToDoCaseByDate(LocalDate.now());
    }

    @GetMapping("/listByThisWeek")
    public List<TodoCase> listByThisWeek(){
        return todoCaseService.getToDoCaseBetween(LocalDate.now(),LocalDate.now().plusDays(7));
    }


    @GetMapping("/listByBetween")
    public List<TodoCase> listByBetween(@RequestParam("fromDate") String fromDate,@RequestParam("toDate")String toDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return todoCaseService.getToDoCaseBetween(LocalDate.parse(fromDate,formatter),LocalDate.parse(toDate,formatter));
    }

    @PostMapping(value = "/addTodoCase")
    public TodoCase addNewTodoCase(@RequestBody TodoCase todoCase) {
        return todoCaseService.saveNewTodoCase(todoCase);
    }

    @GetMapping(value = "/changeCaseStatus")
    public TodoCase updateTodoCase(@RequestParam("id") Long id, @RequestParam("status") Boolean status) {
        return todoCaseService.changeToDoStatus(id, status);
    }
}
