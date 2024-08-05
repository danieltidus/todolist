package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.Task;
import br.ufpb.dcx.dsc.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TodoService {

//
//    private final ArrayList<Task> taskList= new ArrayList<>();
    private final TaskRepository taskRepository;

    TodoService(TaskRepository taskRepository){
    //TodoService() {
        this.taskRepository = taskRepository;
//        taskList.add(new Task(1L, "Escrever Relatório", LocalDate.now()));
//        taskList.add(new Task(1L, "Lavar a louça", LocalDate.now()));
//        taskList.add(new Task(2L, "Estudar para prova", LocalDate.now()));
//        taskList.add(new Task(2L, "Alimentar os gatos", LocalDate.now()));
//        System.out.println(taskList);
    }
    public Task getTask(Long taskId){
        return taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Item not found"));
//        int index = this.getTaskIndexById(taskId);
//        return taskList.get(index);
    }

    public List<Task> listTasks(Long userId){
        if(userId != null){
            return taskRepository.findAllByUserId(userId);
        }

        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
//
//        List<Task> selected = new ArrayList<>();
//        if(userId != null){
//            for (Task task: taskList) {
//                if(task.getUserId().equals(userId)){
//                    selected.add(task);
//                }
//            }
//            return selected;
//        }
//        return taskList;

    }

    public Task saveTask(Task t) {
        return taskRepository.save(t);
//        taskList.add(t);
//        return t;
    }

    public void deleteTask(Long taskId) {

        taskRepository.findById(taskId)
                .ifPresent((task) -> taskRepository.deleteById(task.getId()));
//        int index = this.getTaskIndexById(taskId);
//        if(index == -1)
//            return;
//        taskList.remove(index);
    }

    public Task updateTask(Long id, Task t) {

        Optional<Task> taskData = taskRepository.findById(id);
        Task toUpdate = taskData.orElseThrow(NoSuchElementException::new);
        toUpdate.setDeadline(t.getDeadline());
        toUpdate.setName(t.getName());
        taskRepository.save(toUpdate);
        return toUpdate;

//
//        int index = this.getTaskIndexById(id);
//        Task toUpdate = taskList.get(index);
//        toUpdate.setDeadline(t.getDeadline());
//        toUpdate.setNome(t.getNome());
//        return toUpdate;
    }

//    private int getTaskIndexById(Long taskId){
//        for( int i = 0; i < taskList.size(); i++){
//            Task t = taskList.get(i);
//            if(taskList.get(i).getId().equals(taskId))
//                return i;
//        }
//        throw new NoSuchElementException("Task not found!");
//    }
}
