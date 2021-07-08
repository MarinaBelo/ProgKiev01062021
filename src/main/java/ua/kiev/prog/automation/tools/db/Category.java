package ua.kiev.prog.automation.tools.db;

import lombok.Builder;
import lombok.Value;
import org.apache.commons.text.StringEscapeUtils;
import ua.kiev.prog.automation.base.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Category {

    @Value//получаем только значение, не можем устанавливать его -> setter
    @Builder(toBuilder = true)
    public static class Item {
        Integer categoryId;
        Integer parentId;
        String name;
    }

    public Map<Integer, Item> getCategories() {
        Map<Integer, Item> result = new HashMap<>();
        ResultSet resultSet = Session.getInstance().mysql().executeQuery(
                "SELECT oc_category.category_id, oc_category.parent_id, oc_category_description.name\n" +
                        "FROM oc_category\n" +
                        "INNER JOIN oc_category_description\n" +
                        "ON oc_category.category_id = oc_category_description.category_id\n" +
                        "WHERE\n" +
                        "oc_category_description.language_id = 1"
        );
        try {
            while (resultSet.next()) {
                Integer categoryId = resultSet.getInt("category_id");
                Item item = Item.builder()
                        .categoryId(resultSet.getInt("category_id"))
                        .parentId(resultSet.getInt("parent_id"))
                        .name(StringEscapeUtils.unescapeHtml4(
                                resultSet.getString("name")))
                        //.name(resultSet.getString("name"))
                        .build();
                result.put(categoryId, item);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could nor read set", e);
        }
        return result;
    }
}
