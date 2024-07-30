package br.ufpb.dcx.dsc.todolist.mappers;

import br.ufpb.dcx.dsc.todolist.dto.TaskDTO;
import br.ufpb.dcx.dsc.todolist.models.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    private final ModelMapper modelMapper;

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TaskDTO toDto(Task task) {
        return this.modelMapper.map(task, TaskDTO.class);
    }

    public Task toEntity(TaskDTO taskDTO) {
        return this.modelMapper.map(taskDTO, Task.class);
    }
}
