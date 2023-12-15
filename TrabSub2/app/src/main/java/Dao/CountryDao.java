package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabsub.MainActivity;

import java.util.ArrayList;

import Helper.SQLiteDataHelper;
import Model.Country;

public class CountryDao implements IGenericDao<MainActivity> {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase baseDados;
    private String[] colunas = {"CODIGO", "DESCRICAO"};
    private String tabela = "PAIS";
    private Context context;
    private static CountryDao instancia;

    public static CountryDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new CountryDao(context);
        } else {
            return instancia;
        }
    }

    private CountryDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context,
                "PAIS", null, 1);
        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Country obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodio());
            valores.put(colunas[1], obj.getDescricao());
            return baseDados.insert(tabela, null, valores);
        } catch (SQLException ex) {
            Log.e("PAIS", "ERRO: PAISDAO.insert() " + ex.getMessage());
        }
        return 0;

    }

    @Override
    public long update(Country obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getCodio());
            valores.put(colunas[1], obj.getDescricao());
            return baseDados.insert(tabela, null, valores);
        } catch (SQLException ex) {
            Log.e("PAIS", "ERRO: PAISDAO.update() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Country obj) {
        try {
            String[] identificador = {String.valueOf(obj.getCodio())};
            return baseDados.delete(tabela,
                    colunas[3] + "= ?", identificador);
        } catch (SQLException ex) {
            Log.e("PAIS", "ERRO: PaisDao.delete() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Country> getAll() {
        ArrayList<Country> lista = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0] + " desc");

            if (cursor.moveToFirst()) {
                do {
                    Country pais = new Country();
                    pais.setCodio(cursor.getInt(0));
                    pais.setDescricao(cursor.getString(1));
                    lista.add(pais);

                } while (cursor.moveToNext());
            }

        } catch (SQLException ex) {
            Log.e("Pais", "ERRO: PaisDao.getAll() " + ex.getMessage());
        }

        return lista;
    }

    @Override
    public Country getById(int id) {
        try {
            String[] identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[3] + "= ?", identificador,
                    null, null, null);

            if (cursor.moveToFirst()) {
                Country pais = new Country();
                pais.setCodio(cursor.getInt(0));
                pais.setDescricao(cursor.getString(1));

                return pais;
            }

        } catch (SQLException ex) {
            Log.e("PAIS", "ERRO: Pais.getById() " + ex.getMessage());
        }
        return null;
    }
}
