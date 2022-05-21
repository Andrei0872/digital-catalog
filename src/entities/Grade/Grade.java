package entities.Grade;

import utils.IHasIdentifier;

public class Grade implements IHasIdentifier {
    private int ID;
    private int value;
    private String insertedAt;
    private String modifiedAt;

    public Grade (int value, String insertedAt, String modifiedAt) {
        this.value = value;
        this.insertedAt = insertedAt;
        this.modifiedAt = modifiedAt;
    }

    public Grade(int value) {
        this.value = value;
    }

    @Override
    public int getId() {
        return this.ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(String insertedAt) {
        this.insertedAt = insertedAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Value: %s, Inserted at: %s, Modified at: %s", ID, this.value, this.insertedAt, this.modifiedAt);
    }
}
