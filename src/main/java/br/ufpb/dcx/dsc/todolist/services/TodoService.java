package br.ufpb.dcx.dsc.todolist.services;

import br.ufpb.dcx.dsc.todolist.models.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {
    private final ArrayList<Task> taskList= new ArrayList<>();

    TodoService(){
        taskList.add(new Task(1L, "Escrever Relatório", LocalDate.now()));
        taskList.add(new Task(1L, "Lavar a louça", LocalDate.now()));
        taskList.add(new Task(2L, "Estudar para prova", LocalDate.now()));
        taskList.add(new Task(2L, "Alimentar os gatos", LocalDate.now()));
    }
    public Task getTask(Long taskId){
        int index = this.getTaskIndexById(taskId);
        return taskList.get(index);
    }

    public List<Task> listTasks(Long userId){

        List<Task> selected = new ArrayList<>();
        if(userId != null){
            for (Task task: taskList) {
                if(task.getUserId().equals(userId)){
                    selected.add(task);
                }
            }
            return selected;
        }
        return taskList;

        //Outra forma de fazer usando streams
/*        return taskList.stream()
                .filter(task -> task.getUserId() == userId)
                .collect(Collectors.toList());*/
    }

    public List<Task> listTasks() {
        return taskList;
    }

    public Task saveTask(Task t) {
        taskList.add(t);
        return t;
    }

    public void deleteTask(Long taskId) {
        int index = this.getTaskIndexById(taskId);
        if(index == -1)
            return;
        taskList.remove(index);
    }

    public Task updateTask(Long id, Task t) {
        int index = this.getTaskIndexById(id);
        Task toUpdate = taskList.get(index);
        toUpdate.setDeadline(t.getDeadline());
        toUpdate.setNome(t.getNome());
        // taskList.add(toUpdate);
        return toUpdate;
    }

    private int getTaskIndexById(Long taskId){
        for( int i = 0; i < taskList.size(); i++){
            Task t = taskList.get(i);
            if(taskList.get(i).getId().equals(taskId))
                return i;
        }
        throw new NoSuchElementException("Task not found!");
    }
}
