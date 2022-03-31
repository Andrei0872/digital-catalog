package entities;

import java.time.LocalDate;

public record Grade(int value, LocalDate assigned_at, int assignedBy, int classId) {};
