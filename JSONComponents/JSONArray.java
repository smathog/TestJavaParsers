package com.Parsers.JSONComponents;

import java.util.ArrayList;

public class JSONArray extends JSONContainer {
    private final ArrayList<JSONComponent> elements;

    public JSONArray() {
        elements = new ArrayList<>();
    }

    public JSONComponent[] getElements() {
        return (JSONComponent[]) elements.toArray();
    }

    public void add(JSONComponent jc) {
        elements.add(jc);
    }

    @Override
    public String JSONRepresentation() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < elements.size(); ++i) {
            sb.append(' ');
            sb.append(elements.get(i).JSONRepresentation());
            if (i != elements.size() - 1)
                sb.append(',');
        }
        sb.append(']');
        return sb.toString();
    }
}
