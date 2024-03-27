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

    //Variáveis

    //Flag para saber se o usuário pediu resultado
    private boolean resultado_mostrado = false;

    //Flag usada para saber qual operação será feita
    private int operacao = -1;

    //Caixa de resultado
    private TextView tvResultado;

    //Botões de números
    private Button[] btnNumeros = new Button[10];

    //Botão de limpar tela
    private Button btLimpar;

    //Botão de resultado
    private Button btResult;

    //Botões operadores (Soma, Subtração, Divisão, Multiplicação)
    private Button[] btnOperadores = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //================CAPTURA DE COMPONENTES DA TELA================
        tvResultado = findViewById(R.id.tvResult);

        btResult = findViewById(R.id.btnResult);

        btLimpar = findViewById(R.id.btnLimpa);

        btnNumeros[0] = findViewById(R.id.btn0);
        btnNumeros[1] = findViewById(R.id.btn1);
        btnNumeros[2] = findViewById(R.id.btn2);
        btnNumeros[3] = findViewById(R.id.btn3);
        btnNumeros[4] = findViewById(R.id.btn4);
        btnNumeros[5] = findViewById(R.id.btn5);
        btnNumeros[6] = findViewById(R.id.btn6);
        btnNumeros[7] = findViewById(R.id.btn7);
        btnNumeros[8] = findViewById(R.id.btn8);
        btnNumeros[9] = findViewById(R.id.btn9);

        btnOperadores[0] = findViewById(R.id.btnSoma);
        btnOperadores[1] = findViewById(R.id.btnSubtrair);
        btnOperadores[2] = findViewById(R.id.btnDivisao);
        btnOperadores[3] = findViewById(R.id.btnMult);
        //=======================================================================


        //===========================EVENT LISTENERS=============================

        //Botão zero necessita tratamento especial
        btnNumeros[0].setOnClickListener(view -> {

            //Captura texto da caixa de resultado
            String string = tvResultado.getText().toString();


            if (string.isEmpty() || string.endsWith(" ") || string.endsWith("-"))
                return;
            
            if (!resultado_mostrado) {
                string = tvResultado.getText() + String.valueOf(0);
                tvResultado.setText(string);
            }
        });

        for (int i = 1; i < btnNumeros.length; i++) {
            int finalI = i;

            btnNumeros[i].setOnClickListener(view -> {

                //Adiciona os números à caixa de resultado
                if (!resultado_mostrado) {
                    String string = tvResultado.getText() + String.valueOf(finalI);
                    tvResultado.setText(string);
                    return;
                }

                //Caso resultado foi mostrado, limpa caixa de resultado e adiciona números
                tvResultado.setText(String.valueOf(finalI));

                //Configura flag
                resultado_mostrado = false;
            });
        }

        for (int i = 0; i < btnOperadores.length; i++) {
            int finalI = i;
            btnOperadores[i].setOnClickListener(view -> {

                    //Captura conteúdo da caixa de resultado
                    String string = tvResultado.getText().toString();

                    //Verifica qual operação foi selecionada
                    switch (finalI) {
                        case 0:
                            if (validaCampoOperadores())
                                return;

                            string += " + ";
                            operacao = 0;
                            break;

                        //Subtração necessita tratamento especial, por causa dos negativos
                        case 1:
                            if(resultado_mostrado)
                                return;

                            if (string.isEmpty() || string.endsWith(" ")) {
                                string += "-";
                                break;
                            }

                            if(operacao != -1)
                                return;

                            if (!string.endsWith("-")) {
                                string += " - ";
                                operacao = 1;
                            }
                            break;

                        case 2:
                            if (validaCampoOperadores())
                                return;

                            string += " / ";
                            operacao = 2;
                            break;

                        case 3:
                            if (validaCampoOperadores())
                                return;

                            string += " * ";
                            operacao = 3;
                            break;
                    }
                    tvResultado.setText(string);

            });
        }

        //Limpar tela quando clica
        btLimpar.setOnClickListener(view -> {
            tvResultado.setText("");
            resultado_mostrado = false;
            operacao = -1;
        });

        //Mostra resultado quando clica no botão
        btResult.setOnClickListener(view -> {
            if (!resultado_mostrado) {

                String resultado = tvResultado.getText() + " = ";
                String[] splited = resultado.split(" ");


                //valida se os valores estão certos
                if (splited.length < 4)
                    return;


                Float op1 = Float.parseFloat(splited[0]);
                Float op2 = Float.parseFloat(splited[2]);
                Float result;

                //Verifica qual operação fazer
                switch (operacao) {
                    case 0:
                        resultado += String.valueOf(Math.round(op1 + op2));
                        break;
                    case 1:
                        resultado += String.valueOf(Math.round(op1 - op2));
                        break;
                    case 2:
                        result = op1 / op2;

                        if (op1 % op2 == 0) {
                            resultado += String.valueOf(Math.round(op1 / op2));
                        } else {
                            resultado += String.valueOf(result);
                        }
                        break;

                    case 3:
                        resultado += String.valueOf(Math.round(op1 * op2));
                        break;
                }

                //Mostra resultado e configura as flags
                tvResultado.setText(resultado);
                resultado_mostrado = true;
                operacao = -1;
            }
        });
    }
    //===========================================================================

    //=========================FUNÇÂO AUXILIAR===================================
    protected boolean validaCampoOperadores() {
        String texto = tvResultado.getText().toString();

        if (resultado_mostrado || operacao != -1 || texto.endsWith(" ") || texto.endsWith("-")) {
                return true;
        }

        return texto.isEmpty();
    }
    //=============================================================================
}





