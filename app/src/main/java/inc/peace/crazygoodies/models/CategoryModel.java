package inc.peace.crazygoodies.models;

/**
 * Created by Udit on 1/7/2018.
 */

public class CategoryModel {
    private String categoryName;
    private String categoryServerPath;
    private String categoryParent;

    public String getCategoryServerPath() {
        return categoryServerPath;
    }

    public void setCategoryServerPath(String categoryServerPath) {
        this.categoryServerPath = categoryServerPath;
    }

    public String getCategoryParent() {
        return categoryParent;
    }

    public void setCategoryParent(String categoryParent) {
        this.categoryParent = categoryParent;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
