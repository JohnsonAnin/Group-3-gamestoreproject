package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.item.TShirt;

import java.util.List;

public interface TShirtDao extends ItemDao {
    public TShirt addtShirt(TShirt tShirt);
    public void deletetShirt(int id);
    public void updatetShirt(TShirt tShirt);
    public List<TShirt> findAlltShirts();
    public TShirt findById(int id);
    public List<TShirt> findtShirtByColor(String color);
    public List<TShirt> findtShirtBySize(String size);
}
