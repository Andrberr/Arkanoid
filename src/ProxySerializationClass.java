import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;

public class ProxySerializationClass {
    public void serializeToTextFile(String filename, DisplayObject[] objects, Settings settings) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (DisplayObject object : objects) {
             object.serializeToTextFile(filename);
        }
        //settings.serializeToTextFile(filename);
    }

    public DisplayObject[] deserializeFromTextFile(String filename, Settings settings) {
        DisplayObject[] objects = new DisplayObject[47];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int i=0;
            while (true) {
                String className = reader.readLine();
                if (className == null) break;
                if (className.equals("Settings")) {
                    //  settings.deserializeFromTextFile(reader.readLine());
                } else {
                    Class<?> clazz = Class.forName(className);
                    DisplayObject date = (DisplayObject) clazz.newInstance();
                    date.deserializeFromTextFile(reader.readLine());
                    objects[i] = date;
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return objects;
    }

    public void serializeToJsonFile(String filename, DisplayObject[] objects, Settings settings) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ArrayNode objectsNode = root.putArray("objects");
        for (DisplayObject object : objects) {
            ObjectNode objectNode = mapper.valueToTree(object);
            objectNode.put("type", object.getClass().getName());
            objectsNode.add(objectNode);
        }

        try {
            File file = new File(filename);
            mapper.writeValue(file, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DisplayObject[] deserializeFromJsonFile(String filename, Settings settings) {
        DisplayObject[] newObjects = new DisplayObject[47];
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(new File(filename));
            JsonNode objectsNode = rootNode.get("objects");
            int i = 0;
            for (JsonNode objectNode : objectsNode) {
                String className = objectNode.get("type").asText();
                Class<?> clazz = Class.forName(className);
                DisplayObject object = (DisplayObject) mapper.readValue(objectNode.toString(), clazz);
                newObjects[i] = object;
                i++;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newObjects;
    }
}
