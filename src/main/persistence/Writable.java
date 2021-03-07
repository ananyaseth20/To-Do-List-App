package persistence;

import org.json.JSONObject;

// source: JSONSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
