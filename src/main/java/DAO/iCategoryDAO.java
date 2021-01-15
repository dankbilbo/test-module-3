package DAO;

import Model.Category;

import java.util.ArrayList;

public interface iCategoryDAO {
    public ArrayList<Category> selectAllCategories();
}
