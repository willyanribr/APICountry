package Dao;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Model.Country;

public interface IGenericDao<M extends AppCompatActivity> {
    long insert(Country obj);

    long update(Country obj);

    long delete(Country obj);

    ArrayList<Country> getAll();

    Country getById(int id);
}
