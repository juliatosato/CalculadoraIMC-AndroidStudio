package br.fecaccp.calculadoraimc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class CalculoIMCActivity extends AppCompatActivity {
    private Button btnLimpar;
    private Button btnCalcular;
    private Button btnFechar;

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_imcactivity);

        // Função do botão Fechar
        btnFechar = findViewById(R.id.btnFechar);
        btnFechar.setOnClickListener(view -> {
            finish();
        });

        // Função do botão Limpar
        btnLimpar = findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(view -> {
            EditText campoPeso = findViewById(R.id.editTextPeso);
            EditText campoAltura = findViewById(R.id.editTextAltura);

            campoPeso.getText().clear();
            campoAltura.getText().clear();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnCalcular.findViewById(R.id.buttonCalcular);
        btnCalcular.setOnClickListener(view -> {
            // Obtenção dos valores inseridos e cálculo do IMC
            EditText campoPeso = findViewById(R.id.editTextPeso);
            EditText campoAltura = findViewById(R.id.editTextAltura);

            String peso = campoPeso.getText().toString();
            String altura = campoAltura.getText().toString();

            Double numPeso = Double.parseDouble(peso);
            Double numAltura = Double.parseDouble(altura);
            Double numImc = numPeso / (numAltura * numAltura);

            String imc = String.valueOf(numImc);

            DecimalFormat df = new DecimalFormat("##.##");
            imc = df.format(numImc);

            InformacoesUser infos = new InformacoesUser();
            infos.setAltura(altura);
            infos.setPeso(peso);
            infos.setImc(imc);

            // Bloco if-else que determina para qual página o usuário será redirecionado de acordo com o resultado do IMC
            if (numImc < 18.5) {
                Intent intent = new Intent(this, AbaixoDoPeso.class);
                bundle.putSerializable("infos", infos);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (numImc >= 18.5 && numImc < 25) {
                Intent intent = new Intent(this, PesoNormal.class);
                bundle.putSerializable("infos", infos);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (numImc >= 25 && numImc < 30) {
                Intent intent = new Intent(this, Sobrepeso.class);
                bundle.putSerializable("infos", infos);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (numImc >= 30 && numImc < 35) {
                Intent intent = new Intent(this, Obesidade1.class);
                bundle.putSerializable("infos", infos);
                intent.putExtras(bundle);
                startActivity(intent);
            } else if (numImc >= 35 && numImc < 40) {
                Intent intent = new Intent(this, Obesidade2.class);
                bundle.putSerializable("infos", infos);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, Obesidade3.class);
                bundle.putSerializable("infos", infos);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}