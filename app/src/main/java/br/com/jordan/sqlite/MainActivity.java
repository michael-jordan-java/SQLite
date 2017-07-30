package br.com.jordan.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");

            //Inserindo dados a tabela
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade)VALUES('Michael Jordan', 18)");
            bancoDados.execSQL("INSERT INTO pessoas(nome, idade)VALUES('Michael', 19)");

            //Buscando dados no banco
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM pessoas", null);

            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            //Fazendo o cursor voltar para o primeiro item
            cursor.moveToFirst();

            while (cursor != null) {
                cursor.getInt(indiceId);
                cursor.getString(indiceNome);
                cursor.getInt(indiceIdade);
                cursor.moveToNext();
            }

            //Atualizando dados
            bancoDados.execSQL("UPDATE pessoas SET nome = 'Michael Jordan Costa de Santana' WHERE id = 2");

            //Excluindo dados
            bancoDados.execSQL("DELETE FROM pessoas WHERE id = ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
