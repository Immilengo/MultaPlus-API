package ao.multaplus.motorist.controller;

import ao.multaplus.motorist.dtos.MotoristDto;
import ao.multaplus.motorist.entity.Motorists;
import ao.multaplus.motorist.service.MotoristServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/motorist")
@Tag(name = "Motorist", description = "Motorist endpoints")
@RequiredArgsConstructor
public class MotoristController {
    private final MotoristServiceImpl motoristService;

    @PostMapping
    public void createMotorist(@RequestBody() MotoristDto motoristDto) {
        motoristService.registerMotorist(motoristDto);
    }

    @PutMapping("/{motoristIdentifier}")
    public void updateMotorist(@PathVariable String motoristIdentifier,
                               @RequestBody() MotoristDto motoristDto) {
        motoristService.updateMotorist(motoristIdentifier, motoristDto);
    }

    @GetMapping("/{userIdentifier}")
    public Motorists getMotorist(@PathVariable String userIdentifier) {
        return motoristService.getMotorist(userIdentifier);
    }

    @DeleteMapping("/{motoristIdentifier}")
    public void deleteMotorist(@PathVariable String motoristIdentifier) {
        motoristService.deleteMotorist(motoristIdentifier);
    }
}
