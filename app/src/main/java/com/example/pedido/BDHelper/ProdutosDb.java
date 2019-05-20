package com.example.pedido.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pedido.model.Produtos;

import java.util.ArrayList;

public class ProdutosDb extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pedido.db";


    public ProdutosDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String produto = "CREATE TABLE produtos(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,codigo INTEGER,descricao TEXT NOT NULL, quantidade INTEGER NOT NULL);";
        db.execSQL(produto);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String produto = "DROP TABLE IF EXISTS produtos";
        db.execSQL(produto);

    }


    //Listar produtos
    public ArrayList<Produtos> getLista(){
        String[] columns = {"id","codigo","descricao","quantidade"};
        Cursor c = getWritableDatabase().query("produtos",columns,null,null,null,null,null,null);

        ArrayList<Produtos> produtos = new ArrayList<Produtos>();

        while (c.moveToNext()){
            Produtos produto = new Produtos();

            produto.setId(c.getLong(0));
            produto.setCodigo(c.getInt(1));
            produto.setDescricao(c.getString(2));
            produto.setQuantidade(c.getInt(3));

            produtos.add(produto);

        }

        return produtos;


    }

    public void insertProdutos(Produtos produto){

        ContentValues values = new ContentValues();

        values.put("codigo",produto.getCodigo());
        values.put("descricao",produto.getDescricao());
        values.put("quantidade",produto.getQuantidade());

        getWritableDatabase().insert("produtos",null,values);


    }


}
