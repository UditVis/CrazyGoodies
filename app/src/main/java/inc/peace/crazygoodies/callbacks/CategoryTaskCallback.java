package inc.peace.crazygoodies.callbacks;

import org.json.JSONObject;

/**
 * Created by Udit on 1/7/2018.
 */

public interface CategoryTaskCallback {
    public boolean getCategoryCallback(JSONObject fromServer);
    public void setUpTabsAndViewPager();
    public void setCategories();
}
