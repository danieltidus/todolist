package br.ufpb.dcx.dsc.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private String name;
    private Long userId;
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate deadline;
}
