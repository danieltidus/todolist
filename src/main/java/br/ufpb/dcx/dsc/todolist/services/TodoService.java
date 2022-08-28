package br.ufpb.dcx.dsc.todolist.services;

import br.ufpb.dcx.dsc.todolist.models.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    List<Task> taskList = List.of(
            new Task(1L, "Escrever Relatório", LocalDateTime.now()),
            new Task(1L, "Lavar a louça", LocalDateTime.now()),
            new Task(2L, "Estudar para prova", LocalDateTime.now()),
            new Task(2L, "Alimentar os gatos", LocalDateTime.now())
    );
    public List<Task> listTasks(Long userId){

        List<Task> selected = new ArrayList<>();
        for (Task task: taskList) {
            if(task.getUserId() == userId){
                selected.add(task);
            }
        }
        return selected;

        //Outra forma de fazer usando streams
/*        return taskList.stream()
                .filter(task -> task.getUserId() == userId)
                .collect(Collectors.toList());*/
    }

    public List<Task> listTasks() {
        return taskList;
    }
}
