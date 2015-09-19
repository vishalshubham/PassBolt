package passbolt.com.passbolt;

/**
 * Created by Vishal on 19/09/2015.
 */
public class Node_Item {
    String category_name;
    String item_name;
    String item_url;
    String item_icon;

    public Node_Item(String category_name, String item_name, String item_url, String item_icon) {
        this.category_name = category_name;
        this.item_name = item_name;
        this.item_url = item_url;
        this.item_icon = item_icon;
    }
}
