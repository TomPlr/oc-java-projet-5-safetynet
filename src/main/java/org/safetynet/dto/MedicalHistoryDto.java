package org.safetynet.dto;

import java.util.List;

public record MedicalHistoryDto(List<String> medications, List<String> allergies) {
}
