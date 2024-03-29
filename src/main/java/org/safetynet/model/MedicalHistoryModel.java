package org.safetynet.model;

import java.util.List;

public record MedicalHistoryModel(List<String> medications, List<String> allergies) {
}
