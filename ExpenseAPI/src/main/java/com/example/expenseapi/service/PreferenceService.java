package com.example.expenseapi.service;

import com.example.expenseapi.dto.PreferenceDTO;
import com.example.expenseapi.pojo.Preference;

public interface PreferenceService extends GenericService<Preference, Long> {
    PreferenceDTO getUserPreferences();
    PreferenceDTO updateUserPreferences(PreferenceDTO preferenceDTO);
}
