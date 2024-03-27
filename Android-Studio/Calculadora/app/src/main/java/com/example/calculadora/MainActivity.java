package com.example.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends Activity {

    private boolean resultado_mostrado;

    private int operacao;

    private TextView tvResultado;
    private Button[] btnNumeros = new Button[10];
    private Button btLimpar;
    private Button btResult;

    private Button[] btnOperandos = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvResultado = (TextView) findViewById(R.id.tvResult);

        btResult = (Button) findViewById(R.id.btnResult);

        btLimpar = (Button) findViewById(R.id.btnLimpa);

        btLimpar.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Eu quero gozar", Toast.LENGTH_LONG).show();
        });

        btnNumeros[0] = (Button) findViewById(R.id.btn0);
        btnNumeros[1] = (Button) findViewById(R.id.btn1);
        btnNumeros[2] = (Button) findViewById(R.id.btn2);
        btnNumeros[3] = (Button) findViewById(R.id.btn3);
        btnNumeros[4] = (Button) findViewById(R.id.btn4);
        btnNumeros[5] = (Button) findViewById(R.id.btn5);
        btnNumeros[6] = (Button) findViewById(R.id.btn6);
        btnNumeros[7] = (Button) findViewById(R.id.btn7);
        btnNumeros[8] = (Button) findViewById(R.id.btn8);
        btnNumeros[9] = (Button) findViewById(R.id.btn9);

        btnOperandos[0] = (Button) findViewById(R.id.btnSoma);
        btnOperandos[1] = (Button) findViewById(R.id.btnSubtrair);
        btnOperandos[2] = (Button) findViewById(R.id.btnDivisao);
        btnOperandos[3] = (Button) findViewById(R.id.btnMult);

        for (int i = 0; i < btnNumeros.length; i++) {
            int finalI = i;

            btnNumeros[i].setOnClickListener(view -> {
                if (validaCampoOperandos()) {
                    String string = tvResultado.getText() + String.valueOf(finalI);
                    tvResultado.setText(string);
                }
            });
        }

        for (int i = 0; i < btnOperandos.length; i++) {
            int finalI = i;
            btnOperandos[i].setOnClickListener(view -> {
                if (validaCampoOperadores()) {
                    String string = tvResultado.getText().toString();

                    switch (finalI) {
                        case 0:
                            string += " + ";
                            operacao = 0;
                            break;
                        case 1:
                            string += " - ";
                            operacao = 1;
                            break;

                        case 2:
                            string += " / ";
                            operacao = 2;
                            break;

                        case 3:
                            string += " * ";
                            operacao = 3;
                            break;
                    }
                    tvResultado.setText(string);
                }

            });
        }

        btLimpar.setOnClickListener(view -> {
            tvResultado.setText("");
        });

        btResult.setOnClickListener(view -> {
            if (!resultado_mostrado) {
                String resultado = tvResultado.getText() + " = ";

                String[] splited = resultado.split(" ");

                Float op1 = Float.parseFloat(splited[0]);
                Float op2 = Float.parseFloat(splited[2]);
                Float result;


                switch (operacao) {
                    case 0:
                        result = op1 + op2;
                        resultado += new DecimalFormat("#.000").format(result);
                        break;
                    case 1:
                        result = op1 - op2;
                        resultado += new DecimalFormat("#.000").format(result);
                        break;
                    case 2:
                        result = op1 / op2;
                        resultado += new DecimalFormat("#.000").format(result);
                        break;
                    case 3:
                        result = op1 * op2;
                        resultado += new DecimalFormat("#.000").format(result);
                        break;
                }
                tvResultado.setText(resultado);
            }
        });
    }

    protected boolean validaCampoOperadores() {
        String operadores = "X/+-";
        String texto = tvResultado.getText().toString();

        for (int i = 0; i < operadores.length(); i++) {

            boolean result = texto.contains(Character.toString(operadores.charAt(i)));

            if (result) {
                return false;
            }
        }

        return true;
    }

    protected boolean validaCampoOperandos() {
        String texto = tvResultado.getText().toString();
        return texto.split(" ").length < 3;
    }


}





