package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.model.Board;
import br.ufpb.dcx.dsc.todolist.model.Task;
import br.ufpb.dcx.dsc.todolist.repository.BoardRepository;
import br.ufpb.dcx.dsc.todolist.repository.TaskRepository;
import br.ufpb.dcx.dsc.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {


    private TaskRepository taskRepository;
    private BoardRepository boardRepository;

    TodoService(TaskRepository taskRepository, BoardRepository boardRepository)
    {
        this.taskRepository = taskRepository;
        this.boardRepository = boardRepository;
    }

    public Task getTask(Long taskId){
        return taskRepository.getReferenceById(taskId);
    }

    public List<Task> listTasks(){
        return taskRepository.findAll();
    }

    public Task saveTask(Long boardId, Task t) {
        Optional<Board> boardOpt = boardRepository.findById(boardId);
        if(boardOpt.isPresent()){
            t.setBoard(boardOpt.get());
            return taskRepository.save(t);
        }
        return null;
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Long id, Task t) {
        Optional<Task> taskData = taskRepository.findById(id);
        if(taskData.isPresent()){
            Task toUpdate = taskData.get();
            toUpdate.setDeadline(t.getDeadline());
            toUpdate.setNome(t.getNome());
            taskRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

}
