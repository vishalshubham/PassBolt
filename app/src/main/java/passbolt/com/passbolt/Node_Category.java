package passbolt.com.passbolt;

/**
 * Created by Vishal on 19/09/2015.
 */
public class Node_Category {
    String category_name;
    String category_icon;

    public Node_Category(String category_name, String category_icon) {
        this.category_name = category_name;
        this.category_icon = category_icon;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_icon() {
        return category_icon;
    }

    public void setCategory_icon(String category_icon) {
        this.category_icon = category_icon;
    }
}
