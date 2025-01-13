package com.example.expenseapi.web;


import com.example.expenseapi.dto.PreferenceDTO;
import com.example.expenseapi.service.PreferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preferences")
@Tag(name ="Preferences Controller", description = "Controller to setup user preferences")
public class PreferencesController {
    private final PreferenceService service;

    public PreferencesController(PreferenceService service) {
        this.service = service;
    }

    @GetMapping("/current")
    @Operation(summary = "Retrieve currently set preferences")
    public ResponseEntity<PreferenceDTO> getUserPreference() {
        return new ResponseEntity<>(service.getUserPreferences(), HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Setup user's preferences (methodOfPayment, currency)")
    public ResponseEntity<PreferenceDTO> updateUserPreferences(@RequestBody PreferenceDTO preferenceDTO) {
        return new ResponseEntity<>(service.updateUserPreferences(preferenceDTO), HttpStatus.OK);
    }

}
