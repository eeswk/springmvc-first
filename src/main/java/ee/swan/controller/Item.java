package ee.swan.controller;

import java.io.Serializable;

public class Item implements Serializable {

    private static final long serialVersionUID = -1822448467465915637L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
