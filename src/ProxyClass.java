import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Objects;

public class ProxyClass {
    public void serializeField(String filename, Object obj) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(obj.getClass().getName());
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (!Modifier.isTransient(field.getModifiers())) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    if (field.getType().equals(Bonus.class)) {
                        Object bonusValue = field.get(obj);
                        writer.println(field.getName() + "->" + bonusValue);
                        if (bonusValue instanceof Bonus) {
                            Bonus bonus = (Bonus) bonusValue;
                            for (Field bonusField : Bonus.class.getDeclaredFields()) {
                                bonusField.setAccessible(true);
                                Object bonusFieldValue = bonusField.get(bonus);
                                if (bonusFieldValue != null) {
                                    writer.println(bonusField.getName() + "->" + bonusFieldValue);
                                }
                            }
                            for (Field bonusField : Bonus.class.getSuperclass().getDeclaredFields()) {
                                bonusField.setAccessible(true);
                                Object bonusFieldValue = bonusField.get(bonus);
                                if (bonusFieldValue != null) {
                                    writer.println(bonusField.getName() + "->" + bonusFieldValue);
                                }
                            }
                        }
                    } else writer.println(field.getName() + "->" + value);
                }
            }
            for (Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(obj);
                writer.println(field.getName() + "->" + value);
            }
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private void setValue(Object obj, Field field, Object value) throws IllegalAccessException {
        field.setAccessible(true);
        if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
            field.set(obj, Integer.parseInt((String) value));
        } else if (field.getType().equals(double.class) || field.getType().equals(Double.class)) {
            field.set(obj, Double.parseDouble((String) value));
        } else if (field.getType().equals(boolean.class) || field.getType().equals(Boolean.class)) {
            field.set(obj, Boolean.parseBoolean((String) value));
        } else {
            field.set(obj, value);
        }
    }

    public void deserializeFields(String filename, ArrayList<GameFigure> figures) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (true) {
                String className = reader.readLine();
                if (className == null) break;
                Class<?> clazz = Class.forName(className);
                if (!className.equals("Settings") && !className.equals("StatusBar")) {
                    GameFigure figure = (GameFigure) clazz.newInstance();
                    Field[] fields = figure.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if (!Modifier.isTransient(field.getModifiers())) {
                            String value = reader.readLine().split("->")[1];
                            if (field.getType().equals(Bonus.class) && !Objects.equals(value, "null")) {
                                Bonus bonus = new Bonus();
                                Field[] bonusFields = Bonus.class.getDeclaredFields();
                                for (Field bonusField : bonusFields) {
                                    String bonusValue = reader.readLine().split("->")[1];
                                    System.out.println(bonusField.getType()+ bonusValue);
                                    setValue(bonus, bonusField, bonusValue);
                                }
                                bonusFields = Bonus.class.getSuperclass().getDeclaredFields();
                                for (Field bonusField : bonusFields) {
                                    String bonusValue = reader.readLine().split("->")[1];
                                    System.out.println(bonusField.getType()+ bonusValue);
                                    setValue(bonus, bonusField, bonusValue);
                                }
                                setValue(figure, field, bonus);
                            } else if (!Objects.equals(value, "null")){
                                setValue(figure, field, value);
                            }
                        }
                    }
                    fields = figure.getClass().getSuperclass().getDeclaredFields();
                    for (Field field : fields) {
                        String value = reader.readLine().split("->")[1];
                        setValue(figure, field, value);
                    }
                    figures.add(figure);
                } else if (!className.equals("StatusBar")) {
                    Settings figure = (Settings) clazz.newInstance();
                    Field[] fields = figure.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if (!Modifier.isTransient(field.getModifiers())) {
                            String value = reader.readLine().split("->")[1];
                            Class<?> fieldType = field.getType();
                            Object convertedValue = null;

                            if (fieldType == int.class || fieldType == Integer.class) {
                                convertedValue = Integer.parseInt(value);
                            } else if (fieldType == double.class || fieldType == Double.class) {
                                convertedValue = Double.parseDouble(value);
                            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                                convertedValue = Boolean.parseBoolean(value);
                            }
                            field.setAccessible(true);
                            field.set(figure, convertedValue);
                        }
                    }
                    Game.sett = figure;
                } else {
                    StatusBar figure = (StatusBar) clazz.newInstance();
                    Field[] fields = figure.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if (!Modifier.isTransient(field.getModifiers())) {
                            String value = reader.readLine().split("->")[1];
                            Class<?> fieldType = field.getType();
                            Object convertedValue = null;

                            if (fieldType == int.class || fieldType == Integer.class) {
                                convertedValue = Integer.parseInt(value);
                            } else if (fieldType == double.class || fieldType == Double.class) {
                                convertedValue = Double.parseDouble(value);
                            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                                convertedValue = Boolean.parseBoolean(value);
                            } else if (fieldType == String.class) {
                                convertedValue = value;
                            }
                            field.setAccessible(true);
                            field.set(figure, convertedValue);
                        }
                    }
                    Game.bar = figure;
                }

            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void serializeToJsonFile(String filename, ArrayList<GameFigure> figures, Settings settings, StatusBar statusBar) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();

        ObjectNode settingsNode = mapper.valueToTree(settings);
        root.set("settings", settingsNode);

        ObjectNode statusNode = mapper.valueToTree(statusBar);
        root.set("statusBar", statusNode);

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

    public void deserializeFromJsonFile(String filename, ArrayList<GameFigure> figures) {
        figures.clear();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(new File(filename));
            JsonNode settingsNode = rootNode.get("settings");
            Settings settings = new Settings();
            settings.setScreen(settingsNode.get("screen").asText());
            settings.setDifficulty(settingsNode.get("difficulty").asInt());
            Game.sett = settings;

            JsonNode statusBarNode = rootNode.get("statusBar");
            StatusBar statusBar = new StatusBar();
            statusBar.setName(statusBarNode.get("name").asText());
            statusBar.setSurname(statusBarNode.get("surname").asText());
            statusBar.setProgressBar(statusBarNode.get("progressBar").asText());
            statusBar.setTime(statusBarNode.get("time").asText());
            statusBar.setDestroyed(statusBarNode.get("destroyed").asText());
            Game.bar = statusBar;

            JsonNode objectsNode = rootNode.get("objects");
            for (JsonNode objectNode : objectsNode) {
                String className = objectNode.get("type").asText();
                Class<?> clazz = Class.forName(className);
                if (className.equals("Block")) {
                    Block object = mapper.readValue(objectNode.toString(), Block.class);
                    JsonNode bonusNode = objectNode.get("bonus");
                    if (bonusNode != null) {
                        Bonus bonus = mapper.readValue(bonusNode.toString(), Bonus.class);
                        object.setBonus(bonus);
                        figures.add(object);
                    }
                } else {
                    GameFigure object = (GameFigure) mapper.readValue(objectNode.toString(), clazz);
                    figures.add(object);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
