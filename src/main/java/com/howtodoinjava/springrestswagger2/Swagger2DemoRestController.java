package com.howtodoinjava.springrestswagger2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
@Tag(name = "Student", description = "Endpoints for managing student")
public class Swagger2DemoRestController {

    List<Student> students = new ArrayList<>();

    {
        students.add(new Student("Sajal", "IV", "India"));
        students.add(new Student("Lokesh", "V", "India"));
        students.add(new Student("Kajal", "III", "USA"));
        students.add(new Student("Sukesh", "VI", "USA"));
    }

    @RequestMapping(value = "/getStudents")
    public List<Student> getStudents() {
        return students;
    }

    @RequestMapping(value = "/getStudent/{name}")
    @Operation(
            summary = "Finds a student",
            description = "Finds a student by their name",
            tags = { "Student" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Student.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Not found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public Student getStudent(@PathVariable(value = "name") String name) {
        return students
                .stream()
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList())
                .get(0);
    }

    @RequestMapping(value = "/getStudentByCountry/{country}")
    public List<Student> getStudentByCountry(@PathVariable(value = "country") String country) {
        return students
                .stream()
                .filter(x -> x.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());

    }

    @RequestMapping(value = "/getStudentByClass/{cls}")
    public List<Student> getStudentByClass(@PathVariable(value = "cls") String cls) {
        return students
                .stream()
                .filter(x -> x.getCls().equalsIgnoreCase(cls))
                .collect(Collectors.toList());
    }
}
