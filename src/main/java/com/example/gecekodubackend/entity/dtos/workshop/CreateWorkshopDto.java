package com.example.gecekodubackend.entity.dtos.workshop;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateWorkshopDto {

    @NotEmpty(message = "Çalışma adı boş olamaz!")
    @Size(min = 3, max = 20, message = "En az 3, en fazla 20 karakter girebilirsiniz!")
    private String workshopName;

    @NotEmpty(message = "Çalışma açıklaması boş olamaz!")
    @Size(min = 3, max = 50, message = "En az 3, en fazla 50 karakter girebilirsiniz!")
    private String description;

    @FutureOrPresent(message = "Geçmişteki bir tarihi giremezsiniz!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
}
