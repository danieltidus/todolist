package br.ufpb.dcx.dsc.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {
    @NotBlank(message = "Photo URL is required")
    @Pattern(regexp = "^https?://.*\\.(jpg|jpeg|png|gif|bmp|webp)$", 
             message = "URL must be a valid image URL (jpg, jpeg, png, gif, bmp, webp)")
    private String url;
}
