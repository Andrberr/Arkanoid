import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class ProxyClass {

    public void serializeField(String filename, Object obj) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(obj.getClass().getName());
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(obj);
                writer.println(field.getName() + ":" + value);
            }
            for (Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(obj);
                writer.println(field.getName() + ":" + value);
            }
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void deserializeFields(String filename, ArrayList<GameFigure> figures) {
        figures.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (true) {
                String className = reader.readLine();
                if (className == null) break;
                Class<?> clazz = Class.forName(className);
                GameFigure date = (GameFigure) clazz.newInstance();
                Field[] fields = date.getClass().getDeclaredFields();
                for (Field field : fields) {
                    String value = reader.readLine().split(":")[1];
                    Class<?> fieldType = field.getType();
                    Object convertedValue = null;

                    if (fieldType == int.class || fieldType == Integer.class) {
                        convertedValue = Integer.parseInt(value);
                    } else if (fieldType == double.class ||  fieldType == Double.class) {
                        convertedValue = Double.parseDouble(value);
                    } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                        convertedValue = Boolean.parseBoolean(value);
                    }
                    field.setAccessible(true);
                    field.set(date, convertedValue);
                }
                fields = date.getClass().getSuperclass().getDeclaredFields();
                for (Field field : fields) {
                    String value = reader.readLine().split(":")[1];
                    Class<?> fieldType = field.getType();
                    Object convertedValue = null;

                    if (fieldType == int.class ||  fieldType == Integer.class) {
                        convertedValue = Integer.parseInt(value);
                    } else if (fieldType == double.class || fieldType == Double.class) {
                        convertedValue = Double.parseDouble(value);
                    } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                        convertedValue = Boolean.parseBoolean(value);
                    }
                    field.setAccessible(true);
                    field.set(date, convertedValue);
                }
                date.deserializeFromField();
                figures.add(date);
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                 NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void serializeToJsonFile(String filename, ArrayList<GameFigure> figures, Settings settings) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode settingsNode = mapper.valueToTree(settings);
        root.set("settings", settingsNode);

        ArrayNode objectsNode = root.putArray("objects");
        for (GameFigure figure : figures) {
            ObjectNode objectNode = mapper.valueToTree(figure);
            objectNode.put("type", figure.getClass().getName());
            objectsNode.add(objectNode);
        }
        try {
            File file = new File(filename);
            mapper.writeValue(file, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializeFromJsonFile(String filename, ArrayList<GameFigure> figures, Settings settings) {
        figures.clear();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(new File(filename));
            JsonNode settingsNode = rootNode.get("settings");
            settings.setVolume(settingsNode.get("volume").asInt());
            settings.setBrightness(settingsNode.get("brightness").asInt());
            settings.setDifficulty(settingsNode.get("difficulty").asInt());
            JsonNode objectsNode = rootNode.get("objects");
            for (JsonNode objectNode : objectsNode) {
                String className = objectNode.get("type").asText();
                Class<?> clazz = Class.forName(className);
                GameFigure object = (GameFigure) mapper.readValue(objectNode.toString(), clazz);
                figures.add(object);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}