package com.example.pedido;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.pedido.model.Clientes;
import com.example.pedido.model.Pedido;
import com.example.pedido.model.PedidoItem;
import com.example.pedido.model.Produtos;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "pedido.db";

    //User
    private static final String CREATE_USER = "create table user (id integer primary key autoincrement, name TEXT, username TEXT, passwd TEXT);";
    private static final String DELETE_USER = "drop table if exists user";

    //Produtos
    private static final String CREATE_PRODUTO = "CREATE TABLE produtos(id INTEGER PRIMARY KEY autoincrement, codigo INTEGER, descricao TEXT NOT NULL, quantidade INTEGER , preco INTEGER );";
    private static final String DELETE_PRODUTO = "drop table if exists produtos";

    //Clientes
    private static final String CREATE_CLIENTE = "CREATE TABLE clientes(id INTEGER PRIMARY KEY autoincrement, nome TEXT NOT NULL, endereco TEXT NOT NULL, endnum INTEGER , telefone TEXT);";
    private static final String DELETE_CLIENTE = "drop table if exists clientes";

    //Pedido
    private static final String CREATE_PEDIDO = "CREATE TABLE pedidos(codigo INTEGER PRIMARY KEY autoincrement , idCliente INTEGER  NOT NULL, cliente TEXT NOT NULL , formapg TEXT NOT NULL ,data TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, status TEXT, valorTotal INTEGER);";
    private static final String DELETE_PEDIDO = "drop table if exists pedidos";

    //ItemPedido
    private static final String CREATE_ITEMPEDIDO = "CREATE TABLE itemPedidos(codigo INTEGER NOT NULL , item INTEGER  NOT NULL , idProduto INTEGER  NOT NULL, produto TEXT NOT NULL ,  quantidade INTEGER NOT NULL, valorUnit INTEGER NOT NULL , valorTotal INTEGER NOT NULL, PRIMARY KEY(codigo,item ));";
    private static final String DELETE_ITEMPEDIDO = "drop table if exists itemPedidos";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_PRODUTO);
        db.execSQL(CREATE_CLIENTE);
        db.execSQL(CREATE_PEDIDO);
        db.execSQL(CREATE_ITEMPEDIDO);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_USER);
        db.execSQL(DELETE_PRODUTO);
        db.execSQL(DELETE_CLIENTE);
        db.execSQL(DELETE_PEDIDO);
        db.execSQL(DELETE_ITEMPEDIDO);

    }

    public boolean insertUser(String name, String user, String passwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("username", user);
        cv.put("passwd", passwd);
        long id = db.insert("user", null, cv);

        if (id == -1) {
            return false;
        } else {
            return true;
        }

    }

    //verifica user
    public boolean chkUser(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select username from user where username=?", new String[]{user});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }

    }





    public String validarLogin(String user, String passwd) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT username,passwd from user WHERE username=? and passwd=?", new String[]{user, passwd});
        if (c.getCount() > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }


    //Listar produtos
    public ArrayList<Produtos> getLista() {
        String[] columns = {"id", "codigo", "descricao", "quantidade", "preco"};
        Cursor c = getReadableDatabase().query("produtos", columns, null, null, null, null, null, null);

        ArrayList<Produtos> produtos = new ArrayList<Produtos>();

        while (c.moveToNext()) {
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

    public Boolean insertProdutos(Produtos produto) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("codigo", produto.getCodigo());
        cv.put("descricao", produto.getDescricao());
        cv.put("quantidade", produto.getQuantidade());
        cv.put("preco", produto.getPreco());

        Long id = db.insert("produtos", null, cv);

        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean alterarProduto(Produtos produto) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("codigo", produto.getCodigo());
        cv.put("descricao", produto.getDescricao());
        cv.put("quantidade", produto.getQuantidade());
        cv.put("preco", produto.getPreco());

        String[] args = {produto.getId().toString()};

        //getWritableDatabase().update("produtos", cv, "id=?",args);

        int id = db.update("produtos", cv, "id=?", args);

        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void deletarProduto(Produtos produto) {

        String[] args = {produto.getId().toString()};

        getWritableDatabase().delete("produtos", "id=?", args);
    }


    //verifica Produto cadastrado
    public boolean chkProduto(Produtos produto) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cv = db.rawQuery("Select codigo from produtos where codigo=?", new String[]{String.valueOf(produto.getCodigo())});
        if (cv.getCount() > 0) {
            return false;
        } else {
            return true;
        }

    }


    //Listar Clientes
    public ArrayList<Clientes> getAllCliente() {
        String[] columns = {"id", "nome", "endereco", "endnum", "telefone"};
        Cursor c = this.getReadableDatabase().query("clientes", columns, null, null, null, null, null, null);

        ArrayList<Clientes> clientes = new ArrayList<Clientes>();

        while (c.moveToNext()) {
            Clientes cliente = new Clientes();

            cliente.setId(c.getLong(0));
            cliente.setNome(c.getString(1));
            cliente.setEndereco(c.getString(2));
            cliente.setEndnum(c.getInt(3));
            cliente.setTelefone(c.getString(4));


            clientes.add(cliente);

        }

        return clientes;

    }

    public Boolean insertClientes(Clientes cliente) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nome", cliente.getNome());
        cv.put("endereco", cliente.getEndereco());
        cv.put("endnum", cliente.getEndnum());
        cv.put("telefone", cliente.getTelefone());

        // getWritableDatabase().insert("clientes",null,cv);
        Long id = db.insert("clientes", null, cv);

        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean alterarClientes(Clientes cliente) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("nome", cliente.getNome());
        cv.put("endereco", cliente.getEndereco());
        cv.put("endnum", cliente.getEndnum());
        cv.put("telefone", cliente.getTelefone());

        String[] args = {cliente.getId().toString()};

        //getWritableDatabase().update("produtos", cv, "id=?",args);

        int id = db.update("clientes", cv, "id=?", args);

        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void deletarCliente(Clientes cliente) {

        String[] args = {cliente.getId().toString()};
        getWritableDatabase().delete("clientes", "id=?", args);
    }


    //verifica Cliente cadastrado
    public boolean chkCliente(Clientes cliente) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cv = db.rawQuery("Select nome from clientes where nome=?", new String[]{cliente.getNome()});
        if (cv.getCount() > 0) {
            return false;
        } else {
            return true;
        }

    }


//Pedidos

    public Boolean insertPedidos(Pedido pedido) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("idCliente", pedido.getIdCliente());
        cv.put("Cliente", pedido.getCliente());
        cv.put("formapg", pedido.getFormapg());
        cv.put("status", pedido.getStatus());
        cv.put("valorTotal", 0);


        Long id = db.insert("pedidos", null, cv);


        if (id == -1) {
            return false;
        } else {
            pedido.setCodigo(id);
            return true;
        }
    }

    //Listar Pedidos
    public ArrayList<Pedido> getAllPedidos() {
        String[] columns = {"codigo", "idCliente", "Cliente", "formapg", "data", "status", "valorTotal"};
        Cursor c = this.getReadableDatabase().query("pedidos", columns, null, null, null, null, null, null);

        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

        while (c.moveToNext()) {
            Pedido pedido = new Pedido();

            pedido.setCodigo(c.getLong(0));
            pedido.setCliente(c.getString(2));

            pedidos.add(pedido);

        }

        return pedidos;

    }

    //Listar Item Pedidos
    public ArrayList<PedidoItem> getAllItemPedidos(PedidoItem itemPedido) {
        String[] columns = {"codigo", "item", "idProduto", "Produto", "quantidade", "valorUnit", "valorTotal"};
        String[] args = {itemPedido.getCodigo().toString()};
        Cursor c = this.getReadableDatabase().query("itemPedidos", columns, "codigo=?", args, null, null, null, null);

        ArrayList<PedidoItem> pedidoItem = new ArrayList<PedidoItem>();

        while (c.moveToNext()) {
            PedidoItem pedidoitem = new PedidoItem();

            pedidoitem.setCodigo(c.getLong(0));
            pedidoitem.setItem(c.getInt(1));
            pedidoitem.setProduto(c.getString(3));
            pedidoitem.setQuantidade(c.getInt(4));
            pedidoitem.setValorUnit(c.getDouble(5));
            pedidoitem.setValorTotal(c.getDouble(6));

            pedidoItem.add(pedidoitem);

        }
        return pedidoItem;

    }

    public Boolean insertItemPedido(PedidoItem pedidoItem) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("codigo", pedidoItem.getCodigo());
        cv.put("item", pedidoItem.getItem());
        cv.put("idProduto", pedidoItem.getIdProduto());
        cv.put("produto", pedidoItem.getProduto());
        cv.put("quantidade", pedidoItem.getQuantidade());
        cv.put("valorUnit", pedidoItem.getValorUnit());
        cv.put("valorTotal", pedidoItem.getValorTotal());


        Long id = db.insert("itemPedidos", null, cv);

        if (id == -1) {
            return false;
        } else {
            return true;
        }
    }


    public String ultItemPedido(String pedido) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select (MAX( item )+1) as ultItem  from itemPedidos where codigo=?", new String[]{pedido});

        if (c.getCount() > 0) {
            c.moveToFirst();
            Long item = c.getLong(0);
            StringBuilder conversor = new StringBuilder();
            conversor.append(item);
            return conversor.toString();
        }else {
            return "1";
        }


    }


    public void deletarPedido(Pedido pedido) {

        String[] args = {pedido.getCodigo().toString()};

        getWritableDatabase().delete("Itempedidos", "codigo=?", args);
        getWritableDatabase().delete("pedidos", "codigo=?", args);

    }

    public void deletarItemPedido(PedidoItem pedidoItem) {

        String[] args = {String.valueOf(pedidoItem.getItem())};
        getWritableDatabase().delete("Itempedidos", "item=?", args);
    }



}
