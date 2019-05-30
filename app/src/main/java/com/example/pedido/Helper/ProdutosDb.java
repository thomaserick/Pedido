package com.example.pedido.Helper;

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

    private static final String CREATE_PRODUTO = "CREATE TABLE produtos(id INTEGER PRIMARY KEY autoincrement, codigo INTEGER, descricao TEXT NOT NULL, quantidade INTEGER , preco FLOAT );";
    private static final String DELETE_PRODUTO = "drop table if exists produtos";



    public ProdutosDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_PRODUTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL(DELETE_PRODUTO);

    }


    //Listar produtos
    public ArrayList<Produtos> getLista(){
        String[] columns = {"id","codigo","descricao","quantidade","preco"};
        Cursor c = getReadableDatabase().query("produtos",columns,null,null,null,null,null,null);

        ArrayList<Produtos> produtos = new ArrayList<Produtos>();

        while (c.moveToNext()){
            Produtos produto = new Produtos();

            produto.setId(c.getLong(0));
            produto.setCodigo(c.getInt(1));
            produto.setDescricao(c.getString(2));
            produto.setQuantidade(c.getInt(3));
            produto.setPreco(c.getFloat(4));
            produtos.add(produto);

        }

        return produtos;


    }

    public Boolean insertProdutos(Produtos produto){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("codigo",produto.getCodigo());
        cv.put("descricao",produto.getDescricao());
        cv.put("quantidade",produto.getQuantidade());
        cv.put("preco",produto.getPreco());

       // getWritableDatabase().insert("produtos",null,cv);
        Long id = db.insert("produtos", null, cv);

        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean alterarProduto(Produtos produto){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("codigo",produto.getCodigo());
        cv.put("descricao",produto.getDescricao());
        cv.put("quantidade",produto.getQuantidade());
        cv.put("preco",produto.getPreco());

        String [] args = {produto.getId().toString()};

        //getWritableDatabase().update("produtos", cv, "id=?",args);

        int id = db.update("produtos", cv, "id=?",args);

        if (id == -1) {
            return false;
        } else {
            return true;
        }



    }

    public void deletarProduto(Produtos produto){

        String [] args = {produto.getId().toString()};

        getWritableDatabase().delete("produtos","id=?",args);
    }




}
