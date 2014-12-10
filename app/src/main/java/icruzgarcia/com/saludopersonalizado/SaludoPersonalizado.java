package icruzgarcia.com.saludopersonalizado;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class SaludoPersonalizado extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo_personalizado);
        Button miboton=(Button)findViewById(R.id.b_saludo);//Cogemos la id del boton del layout
        miboton.setOnClickListener(new View.OnClickListener() {//Creamos el listener del evento y su acción
            @Override
            public void onClick(View view) {//Defino el metodo para accionar el evento.
                EditText cadena= (EditText) findViewById(R.id.entrada);//Cogemos la id de la caja de texto
                if ("".equals(cadena.getText().toString().trim())){
                    // mostrar dialogo
                    //showAlert();
                    //mostrar toast
                    showToast("Introduce texto");
                    return;
                }
                String textosaludo="";
                Button boton= (Button) findViewById(R.id.b_saludo);//Volvemos a coger la id del boton
                String saludo=getResources().getString(R.string.b_saludo);//Cogemos el string que aparece en el botón

                TextView texto = (TextView) findViewById(R.id.saludo);//Cogemos la id del texto de salida
                RadioGroup radio=(RadioGroup) findViewById(R.id.Radio);//Cogemos la id del grupo de botones radio
                textosaludo=textosaludo+saludo;
                if (radio.getCheckedRadioButtonId()==R.id.señor){
                    textosaludo=textosaludo+" "+getResources().getString(R.string.senor).toLowerCase();
                }else{
                    textosaludo=textosaludo+" "+getResources().getString(R.string.senora).toLowerCase();
                }
                textosaludo=textosaludo+" "+cadena.getText().toString();
                CheckBox tiempo=(CheckBox)findViewById(R.id.tiempo);//Aquí declaramos el checkbox y realizamos la comparación
                if(tiempo.isChecked()){
                    DatePicker fecha=(DatePicker)findViewById(R.id.fecha);
                    String muestraf=fecha.getDayOfMonth()+"/"+fecha.getMonth()+"/"+fecha.getYear();
                    TimePicker hora=(TimePicker)findViewById(R.id.hora);
                    muestraf=muestraf+" "+hora.getCurrentHour()+":"+hora.getCurrentMinute();
                    textosaludo=textosaludo+" "+muestraf;


                }


                texto.setText(textosaludo);//Introducimos el string que está en la caja de texto




            }
        });

        CheckBox checkbox=(CheckBox)findViewById(R.id.tiempo);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){//Defino el evento del checkbox para ver el dia y la hora
            int visibility=isChecked ? View.VISIBLE : View.GONE;
            View view =findViewById(R.id.hora);
            view.setVisibility(visibility);
            view=findViewById(R.id.fecha);
            view.setVisibility(visibility);

        }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.saludo_personalizado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showToast(String msg) {
        Context context = getApplicationContext();
        int duracion=Toast.LENGTH_SHORT;
        Toast toast= Toast.makeText(context,msg,duracion);
        toast.show();
    }

}
