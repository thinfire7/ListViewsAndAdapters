package proyecto.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import proyecto.covid.Utilidades.Utilidades;

public class CambioEstadoUsuario extends AppCompatActivity {

    private RadioButton positivo;
    private RadioButton negativo;
    private EditText cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_estado_usuario);

        positivo = (RadioButton) findViewById(R.id.radioButtonPositivo);
        negativo = (RadioButton) findViewById(R.id.radioButtonNegativo);
        cedula = (EditText)findViewById(R.id.txtFieldCedulaDeClinica);
    }

    public void onClick(View view){
        if(view.getId()==R.id.btnCambiarEstado){
            cambiarEstado();
        }

    }
    public void cambiarEstado(){

        String estado ="";

        if(positivo.isChecked()){
            estado = "positivo";
        }if(negativo.isChecked()){
            estado = "negativo";
        }

        if(estado.equals("")==false){

            ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this,"bd_usuarios", null,1);

            SQLiteDatabase db = conexion.getWritableDatabase();

            String[] parametros = {cedula.getText().toString()};

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_ESTADO,estado);

            db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_CEDULA+"=?",parametros);

            db.close();
        }

        estado = "";

    }
}