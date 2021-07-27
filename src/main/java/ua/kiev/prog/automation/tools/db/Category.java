package ua.kiev.prog.automation.tools.db;

import lombok.Builder;
import lombok.Value;
import org.apache.commons.text.StringEscapeUtils;
import ua.kiev.prog.automation.base.Session;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                        .name(StringEscapeUtils.unescapeHtml4(                  //убираем некрасивую кодировку
                                resultSet.getString("name")))
                        //.name(resultSet.getString("name"))
                        .build();
                result.put(categoryId, item);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Could not read set", e);
        }
        return result;
    }

    @Value
    @Builder(toBuilder = true)
    public static class TreeItem {
        Item           item;
        List<TreeItem> childs;
    }

    public static class CategoriesTree {
        final private List<TreeItem> treeItemList = new ArrayList<>();
        final private List<TreeItem> tree         = new ArrayList<>();
        final private Map<Integer, Item> _categories;

        public CategoriesTree(Map<Integer, Item> categories) {
            this._categories = categories;
        }

        final public List<TreeItem> getTree() {
            if (tree.isEmpty() && !_categories.isEmpty()) {
                for (Map.Entry<Integer, Item> category : _categories.entrySet()) {
                    Integer id  = category.getKey();
                    Item item   = category.getValue();
                    TreeItem parentTreeItem = getOrCreateParent(item);
                    if (parentTreeItem != null) {
                        TreeItem treeItem = TreeItem.builder().item(item).childs(new ArrayList<>()).build();
                        parentTreeItem.getChilds().add(treeItem);
                        treeItemList.add(treeItem);
                    }
                }
            }
            return tree;
        }

        final private TreeItem getOrCreateParent(Item item) {
            TreeItem parentTreeItem = null;
            // If item's parent id is zero then just add this item as root of tree
            if (item.getParentId().equals(0)) {
                TreeItem currentTreeItem = TreeItem.builder().item(item).childs(new ArrayList<>()).build();
                tree.add(currentTreeItem);
                treeItemList.add(currentTreeItem);
            }
            // Else try to find or create parent element
            else {
                // Try to find parent in already added elements, just return parent if found
                for (TreeItem treeItem : treeItemList) {
                    if (treeItem.getItem().getCategoryId().equals(item.getParentId())) {
                        return treeItem;
                    }
                }
                // Then parent is not found, we have to create new parent element
                Item parentItem = _categories.get(item.getParentId());
                parentTreeItem = TreeItem.builder().item(parentItem).childs(new ArrayList<>()).build();
                treeItemList.add(parentTreeItem);

                // Add parent as root of tree if parent id is zero
                if (parentItem.getParentId().equals(0)) {
                    tree.add(parentTreeItem);
                }
                // Else find parent of parent and add it to children
                else {
                    TreeItem parentOfParentTreeItem = getOrCreateParent(parentItem);
                    parentOfParentTreeItem.getChilds().add(parentTreeItem);
                }
            }

            return parentTreeItem;
        }
    }

    public List<TreeItem> getCategoriesTree() {
        CategoriesTree catTree = new CategoriesTree(this.getCategories());
        return catTree.getTree();
    }

}
